"""
Helper classes to specify file dependencies for input and output.

Supports inputs from S3 and local FS.
Supports outputs to HDFS, S3, and local FS.

"""

import boto
import datetime
import fnmatch
import logging
import os
import re

import luigi
import luigi.hdfs
import luigi.format
import luigi.task
import url
from url import ExternalURL

from luigi.date_interval import DateInterval

#log = logging.getLogger(__name__)


class PathSetTask(luigi.Task):
    """
    A task to select a subset of files in an S3 bucket or local FS.

    Parameters:

      src: a URL pointing to a folder in s3:// or local FS.
      include:  a list of patterns to use to select.  Multiple patterns are OR'd.
      manifest: a URL pointing to a manifest file location.
    """
    src = luigi.Parameter(
        is_list=True,
        #default_from_config={'section': 'event-logs', 'name': 'source'}
    )
    include = luigi.Parameter(is_list=True, default=('*',))
    manifest = luigi.Parameter(default=None)

    def __init__(self, *args, **kwargs):
        super(PathSetTask, self).__init__(*args, **kwargs)
        self.s3_conn = None

    def generate_file_list(self):
        """Yield each individual path given a source folder and a set of file-matching expressions."""
        for src in self.src:
            if src.startswith('s3'):
                # connect lazily as needed:
                if self.s3_conn is None:
                    self.s3_conn = boto.connect_s3()
                for _bucket, _root, path in generate_s3_sources(self.s3_conn, src, self.include):
                    source = url_path_join(src, path)
                    yield ExternalURL(source)
            elif src.startswith('hdfs'):
                for source in luigi.hdfs.listdir(src):
                    if any(fnmatch.fnmatch(source, include_val) for include_val in self.include):
                        yield ExternalURL(source)
            else:
                # Apply the include patterns to the relative path below the src directory.
                for dirpath, _dirnames, files in os.walk(src):
                    for filename in files:
                        filepath = os.path.join(dirpath, filename)
                        relpath = os.path.relpath(filepath, src)
                        if any(fnmatch.fnmatch(relpath, include_val) for include_val in self.include):
                            yield ExternalURL(filepath)

    def manifest_file_list(self):
        """Write each individual path to a manifest file and yield the path to that file."""
        manifest_target = get_target_from_url(self.manifest)
        if not manifest_target.exists():
            with manifest_target.open('w') as manifest_file:
                for external_url_task in self.generate_file_list():
                    manifest_file.write(external_url_task.url + '\n')

        yield ExternalURL(self.manifest)

    def requires(self):
        if self.manifest is not None:
            return self.manifest_file_list()
        else:
            return self.generate_file_list()

    def complete(self):
        # An optimization: just declare that the task is always
        # complete, by definition, because it is whatever files were
        # requested that match the filter, not a set of files whose
        # existence needs to be checked or generated again.
        return True

    def output(self):
        return [task.output() for task in self.requires()]



