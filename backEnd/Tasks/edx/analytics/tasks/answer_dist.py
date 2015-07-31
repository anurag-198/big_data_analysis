import math
import csv
import hashlib
import html5lib
import json
from operator import itemgetter

import luigi
import luigi.hdfs
import luigi.s3
from luigi.configuration import get_config

from edx.analytics.tasks.mapreduce import MapReduceJobTask, MultiOutputMapReduceJobTask, MapReduceJobTaskMixin
from edx.analytics.tasks.pathutil import PathSetTask
from edx.analytics.tasks.url import ExternalURL, IgnoredTarget
from edx.analytics.tasks.url import get_target_from_url, url_path_join
from edx.analytics.tasks.mysql_load import MysqlInsertTask, MysqlInsertTaskMixin
import edx.analytics.tasks.util.eventlog as eventlog
import edx.analytics.tasks.util.opaque_key_util as opaque_key_util

################################
# Helper methods
################################
def try_str_to_float(value_str):
    try:
        float_val = float(value_str)
        # infinity values and NaN actually break mysql-connector (because they look like a string), so make those None
        if math.isinf(float_val) or math.isnan(float_val):
            return None
        return float_val
    except (ValueError, TypeError):
        return None


def get_problem_check_event(line):
    """
    Generates output values for explicit problem_check events.
    Args:
        line: text line from a tracking event log.
    Returns:
        (problem_id, username), (timestamp, problem_check_info)
        where timestamp is in ISO format, with resolution to the millisecond
        and problem_check_info is a JSON-serialized dict containing
        the contents of the problem_check event's 'event' field,
        augmented with entries for 'timestamp', 'username', and
        'context' from the event.
        or None if there is no valid problem_check event on the line.
    Example:
            (i4x://edX/DemoX/Demo_Course/problem/PS1_P1, dummy_username), (2013-09-10T00:01:05.123456, blah)
    """
    # Parse the line into a dict.
    event = eventlog.parse_json_server_event(line, 'problem_check')
    if event is None:
        return None

    # Get the "problem data".  This is the event data, the context, and anything else that would
    # be useful further downstream.  (We could just pass the entire event dict?)

    # Get the user from the username, not from the user_id in the
    # context.  While we are currently requiring context (as described
    # above), we might not in future.  Older events will not have
    # context information, so we can't rely on user_id from there.
    # And we don't expect problem_check events to occur without a
    # username, and don't expect them to occur with the wrong user
    # (i.e. one user acting on behalf of another, as in an instructor
    # acting on behalf of a student).
    augmented_data_fields = ['context', 'username', 'timestamp']
    problem_data = eventlog.get_augmented_event_data(event, augmented_data_fields)
    if problem_data is None:
        return None

    # Get the course_id from context.  We won't work with older events
    # that do not have context information, since they do not directly
    # provide course_id information.  (The problem_id/answer_id values
    # contain the org and course name, but not the run.)  Course_id
    # information could be found from other events, but it would
    # require expanding the events being selected.
    course_id = problem_data.get('context').get('course_id')
    if course_id is None:
        log.error("encountered explicit problem_check event with missing course_id: %s", event)
        return None

    if not opaque_key_util.is_valid_course_id(course_id):
        log.error("encountered explicit problem_check event with bogus course_id: %s", event)
        return None

    # Get the problem_id from the event data.
    problem_id = problem_data.get('problem_id')
    if problem_id is None:
        log.error("encountered explicit problem_check event with bogus problem_id: %s", event)
        return None

    if len(event.get('event', {}).get('answers', [])) == 0:
        return None

    problem_data_json = json.dumps(problem_data)
    key = (course_id, problem_id, problem_data.get('username'))
    value = (problem_data.get('timestamp'), problem_data_json)

    return key, value
