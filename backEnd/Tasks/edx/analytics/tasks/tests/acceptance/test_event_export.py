"""
End to end test of event exports.
"""

import os
import logging
import tempfile
import textwrap
import time
import shutil

from luigi.s3 import S3Target

from edx.analytics.tasks.tests.acceptance import AcceptanceTestCase
from edx.analytics.tasks.tests.acceptance.services import fs, shell
from edx.analytics.tasks.url import url_path_join


log = logging.getLogger(__name__)


class EventExportAcceptanceTest(AcceptanceTestCase):
    """Validate data flow for bulk export of events for research purposes."""

    INPUT_FILE = 'event_export_tracking.log'
    PROD_FOLDER = 'FakeServerGroup'
    EDGE_FOLDER = 'EdgeFakeServerGroup'
    NUM_REDUCERS = 1

    def setUp(self):
        super(EventExportAcceptanceTest, self).setUp()

        self.test_config_root = url_path_join(self.test_root, 'config')

        self.test_config = url_path_join(self.test_config_root, 'default.yaml')
        self.test_gpg_key_dir = url_path_join(self.test_config_root, 'gpg-keys')

        self.input_paths = [
            url_path_join(self.test_src, 'prod', self.PROD_FOLDER, 'tracking.log-20140515.gz'),
            url_path_join(self.test_src, 'prod', 'OtherFolder', 'tracking.log-20140515.gz'),
            url_path_join(self.test_src, 'edge', self.EDGE_FOLDER, 'tracking.log-20140516-12345456.gz')
        ]

        self.upload_data()
        self.write_config()
        self.upload_public_keys()

    def upload_data(self):
        with fs.gzipped_file(os.path.join(self.data_dir, 'input', self.INPUT_FILE)) as compressed_file_name:
            for dst in self.input_paths:
                self.s3_client.put(compressed_file_name, dst)

    def write_config(self):
        with S3Target(self.test_config).open('w') as target_file:
            target_file.write(
                textwrap.dedent(
                    """
                    ---
                    organizations:
                      edX:
                        recipients:
                          - daemon@edx.org
                      AcceptanceX:
                        recipients:
                          - daemon+2@edx.org
                    """
                )
            )

    def upload_public_keys(self):
        gpg_key_dir = os.path.join('gpg-keys')
        for key_filename in os.listdir(gpg_key_dir):
            full_local_path = os.path.join(gpg_key_dir, key_filename)
            remote_url = url_path_join(self.test_gpg_key_dir, key_filename)

            if not key_filename.endswith('.key'):
                self.s3_client.put(full_local_path, remote_url)

    def test_event_log_exports_using_manifest(self):
        config_override = {
            'manifest': {
                'threshold': 1
            }
        }

        folders = {
            'prod': self.PROD_FOLDER,
            'edge': self.EDGE_FOLDER
        }
        for environment in ['prod', 'edge']:
            self.task.launch([
                'EventExportTask',
                '--source', url_path_join(self.test_src, environment),
                '--output-root', self.test_out,
                '--config', self.test_config,
                '--environment', environment,
                '--interval', '2014-05',
                '--gpg-key-dir', self.test_gpg_key_dir,
                '--gpg-master-key', 'daemon+master@edx.org',
                '--required-path-text', folders[environment],
                '--n-reduce-tasks', str(self.NUM_REDUCERS),
            ], config_override)

        self.validate_output()

    def validate_output(self):
        for site in ['edge', 'edx']:
            for use_master_key in [False, True]:
                self.validate_output_file('2014-05-15', 'edx', site, use_master_key)
                self.validate_output_file('2014-05-16', 'edx', site, use_master_key)
                self.validate_output_file('2014-05-15', 'acceptancex', site, use_master_key)

    def validate_output_file(self, date, org_id, site, use_master_key=False):
        if use_master_key:
            key_filename = 'insecure_master_secret.key'
        else:
            if org_id == 'edx':
                key_filename = 'insecure_secret.key'
            else:
                key_filename = 'insecure_secret_2.key'

        self.temporary_dir = tempfile.mkdtemp()
        self.addCleanup(shutil.rmtree, self.temporary_dir)

        self.downloaded_outputs = os.path.join(self.temporary_dir, 'output')
        os.makedirs(self.downloaded_outputs)

        local_file_name = '{org}-{site}-events-{date}.log'.format(
            org=org_id,
            site=site,
            date=date,
        )

        year = str(date).split("-")[0]

        remote_url = url_path_join(self.test_out, org_id, site, "events", year, local_file_name + '.gz.gpg')

        # Files won't appear in S3 instantaneously, wait for the files to appear.
        # TODO: exponential backoff
        for _index in range(30):
            key = self.s3_client.get_key(remote_url)
            if key is not None:
                break
            else:
                time.sleep(2)

        if key is None:
            self.fail('Unable to find expected output file {0}'.format(remote_url))

        downloaded_output_path = os.path.join(self.downloaded_outputs, remote_url.split('/')[-1])
        key.get_contents_to_filename(downloaded_output_path)

        # first decrypt file
        decrypted_file_name = downloaded_output_path[:-len('.gpg')]
        fs.decrypt_file(downloaded_output_path, decrypted_file_name, key_filename)

        # now decompress file
        decompressed_file_name = decrypted_file_name[:-len(',gz')]
        fs.decompress_file(decrypted_file_name, decompressed_file_name)

        shell.run(['diff', decompressed_file_name, os.path.join(self.data_dir, 'output', local_file_name)])
