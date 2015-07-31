"""
Emulates a luigi target, storing all data in memory.
"""

from contextlib import contextmanager
from StringIO import StringIO


class FakeTarget(object):
    """
    Fake luigi like target that saves data in memory, using a
    StringIO buffer.
    """
    def __init__(self, value=''):
        self.buffer = StringIO(value)
        # Rewind the buffer head so the value can be read
        self.buffer.seek(0)

    @contextmanager
    def open(self, *args, **kwargs):  # pylint: disable=unused-argument
        """
        Returns:
            A file-like object that can be used to read the data that is stored in the buffer.
        """
        try:
            yield self.buffer
        finally:
            self.buffer.seek(0)
