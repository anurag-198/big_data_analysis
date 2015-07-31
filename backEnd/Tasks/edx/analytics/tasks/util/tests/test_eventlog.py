"""
Tests for utilities that parse event logs.
"""

import edx.analytics.tasks.util.eventlog as eventlog
from edx.analytics.tasks.tests import unittest


class ParseEventLogTest(unittest.TestCase):
    """
    Verify that event log parsing works correctly.
    """

    def test_parse_valid_json_event(self):
        line = '{"username": "successful"}'
        result = eventlog.parse_json_event(line)
        self.assertTrue(isinstance(result, dict))

    def test_parse_json_event_truncated(self):
        line = '{"username": "unsuccessful'
        result = eventlog.parse_json_event(line)
        self.assertIsNone(result)

    def test_parse_json_event_with_cruft(self):
        line = 'leading cruft here {"username": "successful"}  '
        result = eventlog.parse_json_event(line)
        self.assertTrue(isinstance(result, dict))

    def test_parse_json_event_with_nonascii(self):
        line = '{"username": "b\ufffdb"}'
        result = eventlog.parse_json_event(line)
        self.assertTrue(isinstance(result, dict))
        self.assertEquals(result['username'], u'b\ufffdb')


class TimestampTest(unittest.TestCase):
    """Verify timestamp-related functions."""

    def test_datestamp_from_timestamp(self):
        timestamp = "2013-12-17T15:38:32.805444"
        self.assertEquals(eventlog.timestamp_to_datestamp(timestamp), "2013-12-17")

    def test_missing_datetime(self):
        item = {"something else": "not an event"}
        self.assertIsNone(eventlog.get_event_time(item))

    def test_good_datetime_with_microseconds_and_timezone(self):
        item = {"time": "2013-12-17T15:38:32.805444+00:00"}
        dt_value = eventlog.get_event_time(item)
        self.assertIsNotNone(dt_value)
        self.assertEquals(eventlog.datetime_to_timestamp(dt_value), "2013-12-17T15:38:32.805444")
        self.assertEquals(eventlog.datetime_to_datestamp(dt_value), "2013-12-17")

    def test_good_datetime_with_timezone(self):
        item = {"time": "2013-12-17T15:38:32+00:00"}
        dt_value = eventlog.get_event_time(item)
        self.assertIsNotNone(dt_value)
        self.assertEquals(eventlog.datetime_to_timestamp(dt_value), "2013-12-17T15:38:32")
        self.assertEquals(eventlog.datetime_to_datestamp(dt_value), "2013-12-17")

    def test_good_datetime_with_microseconds(self):
        item = {"time": "2013-12-17T15:38:32.805444"}
        dt_value = eventlog.get_event_time(item)
        self.assertIsNotNone(dt_value)
        self.assertEquals(eventlog.datetime_to_timestamp(dt_value), "2013-12-17T15:38:32.805444")
        self.assertEquals(eventlog.datetime_to_datestamp(dt_value), "2013-12-17")

    def test_good_datetime_with_no_microseconds_or_timezone(self):
        item = {"time": "2013-12-17T15:38:32"}
        dt_value = eventlog.get_event_time(item)
        self.assertIsNotNone(dt_value)
        self.assertEquals(eventlog.datetime_to_timestamp(dt_value), "2013-12-17T15:38:32")
        self.assertEquals(eventlog.datetime_to_datestamp(dt_value), "2013-12-17")


class GetEventDataTest(unittest.TestCase):
    """Verify that get_event_data works as expected."""

    def test_missing_event_data(self):
        item = {"something else": "not an event"}
        self.assertIsNone(eventlog.get_event_data(item))

    def test_get_bad_string_event_data(self):
        item = {"event": "a string but not JSON"}
        self.assertIsNone(eventlog.get_event_data(item))

    def test_get_empty_string_event_data(self):
        item = {"event": ''}
        self.assertEquals(eventlog.get_event_data(item), {})

    def test_get_json_string_event_data(self):
        item = {"event": '{ "a string": "that is JSON"}'}
        self.assertEquals(eventlog.get_event_data(item), {"a string": "that is JSON"})

    def test_event_data_with_unknown_type(self):
        item = {"event": ["a list", "of strings"]}
        self.assertIsNone(eventlog.get_event_data(item))

    def test_get_dict_event_data(self):
        item = {"event": {"a dict": "that has strings"}}
        self.assertEquals(eventlog.get_event_data(item), {"a dict": "that has strings"})
