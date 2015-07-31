__author__ = 'virus'

import gzip
import glob
import os
import os.path

for gzip_path in glob.glob("/users/extusr/dewang15/zip_test/tracking" + "/*.gz"):
    if os.path.isdir(gzip_path) == False:
        inF = gzip.open(gzip_path, 'rb')
        # uncompress the gzip_path INTO THE 's' variable
        s = inF.read()
        inF.close()

        # get gzip filename (without directories)
        gzip_fname = os.path.basename(gzip_path)
        # get original filename (remove 3 characters from the end: ".gz")
        fname = gzip_fname[:-3]
        uncompressed_path = os.path.join("/users/extusr/dewang15/zip_test/tracking", fname)

        # store uncompressed file data from 's' variable
        open(uncompressed_path, 'w').write(s)