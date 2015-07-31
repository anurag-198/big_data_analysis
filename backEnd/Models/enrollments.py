from pyspark import SparkContext
from pyspark.sql import SQLContext
from pyspark.sql import Row, StructField, StructType, StringType, IntegerType
import eventlog
import logging
import datetime
import MySQLdb
import sys
import traceback

db = MySQLdb.connect("localhost","root","root123","analytics")
cursor = db.cursor()

log = logging.getLogger(__name__)
DEACTIVATED = 'edx.course.enrollment.deactivated'
ACTIVATED = 'edx.course.enrollment.activated'
MODE_CHANGED = 'edx.course.enrollment.mode_changed'

def timestampstrip(timestamp):
	return timestamp[1:len(timestamp)-1]

class EnrollmentEvent(object):
    """The critical information necessary to process the event in the event stream."""

    def __init__(self, timestamp, event_type, event_no, mode):
        self.timestamp = timestamp
        self.datestamp = eventlog.timestamp_to_datestamp(timestamp)
        self.event_type = event_type
        self.mode = mode
	self.event_no = event_no

class DaysEnrolledForEvents(object):
    ENROLLED = 1
    UNENROLLED = 0
    MODE_UNKNOWN = 'unknown'

    def __init__(self,key,events,interval):
	self.course_id, self.user_id = key
	self.sorted_events=sorted(events)
	
	self.sorted_events = [
        EnrollmentEvent(timestamp, event_type, event_no, mode) for timestamp, event_type, event_no, mode in self.sorted_events]
	self.sorted_events.append(EnrollmentEvent(interval, None,None, None))
	self.first_event = self.sorted_events[0]	
	self.state = self.previous_state = self.UNENROLLED
 	self.mode = self.MODE_UNKNOWN	
			
    def days_enrolled(self):
	"""
        A record is yielded for each day during which the user was enrolled in the course.
        Yields:
            tuple: An enrollment record for each day during which the user was enrolled in the course.
        """
        # The last element of the list is a placeholder indicating the end of the interval. Don't process it.
        for index in range(len(self.sorted_events) - 1):
            self.event = self.sorted_events[index]
            self.next_event = self.sorted_events[index + 1]

            self.change_state()

            if self.event.datestamp != self.next_event.datestamp:
                change_since_last_day = self.state - self.previous_state

                # There may be a very wide gap between this event and the next event. If the user is currently
                # enrolled, we can assume they continue to be enrolled at least until the next day we see an event.
                # Emit records for each of those intermediary days. Since the end of the interval is represented by
                # a dummy event at the end of the list of events, it will be represented by self.next_event when
                # processing the last real event in the stream. This allows the records to be produced up to the end
                # of the interval if the last known state was "ENROLLED".
                for datestamp in self.all_dates_between(self.event.datestamp, self.next_event.datestamp):
                    yield self.enrollment_record(
                        datestamp,
                        self.state,
                        change_since_last_day if datestamp == self.event.datestamp else 0,
                        self.mode
                    )

                self.previous_state = self.state

    def all_dates_between(self, start_date_str, end_date_str):
        """
        All dates from the start date up to the end date.
        Yields:
            str: ISO 8601 datestamp for each date from the first date (inclusive) up to the end date (exclusive).
        """
        current_date = self.parse_date_string(start_date_str)
        end_date = self.parse_date_string(end_date_str)

        while current_date < end_date:
            yield current_date.isoformat()
            current_date += datetime.timedelta(days=1)

    def parse_date_string(self, date_str):
        """Efficiently parse an ISO 8601 date stamp into a datetime.date() object."""
        date_parts = [int(p) for p in date_str.split('-')[:3]]
        return datetime.date(*date_parts)

    def enrollment_record(self, datestamp, enrolled_at_end, change_since_last_day, mode_at_end):
        """A complete enrollment record."""
        return (datestamp, self.course_id, self.user_id, enrolled_at_end, change_since_last_day, mode_at_end)

    def change_state(self):
        """Change state when appropriate.
        Note that in spite of our best efforts some events might be lost, causing invalid state transitions.
        """
        self.mode = self.event.mode

        if self.state == self.ENROLLED and self.event.event_type == DEACTIVATED:
            self.state = self.UNENROLLED
        elif self.state == self.UNENROLLED and self.event.event_type == ACTIVATED:
            self.state = self.ENROLLED
        elif self.event.event_type == MODE_CHANGED:
            pass


#select courseName, lmsUserId, createDateTime, eventType, eventName, eventNo,enrolmentMode
#from UserSessionOldLog where eventNo in (255,256,257) and enrolmentMode is not null order by lmsUserId,createDateTime;




sc = SparkContext("local", "gender")
sqlContext = SQLContext(sc)
interval = '2015-06-27 00:00:00'
lines = sc.textFile("Enrollments.csv")


parts = lines.map(lambda l: l.split(","))
users = parts.map(lambda p: ((p[0],p[1]),(timestampstrip(p[2]),p[4],p[5],p[6])))

def intialize(key,events):
	 eventlist = []
         i = 0
	 event_stream_processor = DaysEnrolledForEvents(key,events,interval)
	 for day_enrolled_record in event_stream_processor.days_enrolled():
		eventlist.append(day_enrolled_record)
	 return eventlist
	 
	 
	  

records = users.groupByKey()
records1 = records.map( lambda p : ( intialize(p[0],list(p[1]))))


for ele in records1.collect():
	for val in ele:
		sql = "INSERT INTO course_enrollment (datestamp,course_id,user_id,enrolled_at_end, change_since_last_day,enrollment_mode) VALUES ('%s','%s','%d','%d','%d','%s')" % ((val[0]),(val[1]),(int)(val[2]),(int)(val[3]),(int)(val[4]),(val[5]))
		try:
      			cursor.execute(sql)
      			db.commit()
								

		except Exception, err:
        		traceback.print_exc()
        		db.rollback()
		
#Analysis Result Based on Birth Year
sql1 = " insert into analytics.course_enrollment_birth_year_daily (date, course_id, birth_year, count, cumulative_count) SELECT ce.datestamp, ce.course_id, IF(p.yearofBirth != '',p.yearOfBirth,0) as yb, SUM(ce.enrolled_at_end), COUNT(ce.user_id) FROM analytics.course_enrollment ce LEFT OUTER JOIN IITBxDataAnalytics.User p ON p.lmsUserId = ce.user_id GROUP BY ce.datestamp, ce.course_id,yb "


#Analysis Result Based on Gender
sql2 = " insert into analytics.course_enrollment_gender_daily (date, course_id, gender , count, cumulative_count) SELECT ce.datestamp, ce.course_id, IF(p.gender != '', p.gender, NULL), SUM(ce.enrolled_at_end), COUNT(ce.user_id) FROM analytics.course_enrollment ce LEFT OUTER JOIN IITBxDataAnalytics.User p ON p.lmsUserId = ce.user_id GROUP BY ce.datestamp, ce.course_id, IF(p.gender != '', p.gender, NULL) "


#Analysis Result Based on Education Level
sql3 = """ insert into analytics.course_enrollment_education_level_daily (date, course_id, education_level , count, cumulative_count)  SELECT
                ce.datestamp,
                ce.course_id,
                CASE p.highestEduDegree
                    WHEN 'el'    THEN 'primary'
                    WHEN 'jhs'   THEN 'junior_secondary'
                    WHEN 'hs'    THEN 'secondary'
                    WHEN 'a'     THEN 'associates'
                    WHEN 'b'     THEN 'bachelors'
                    WHEN 'm'     THEN 'masters'
                    WHEN 'p'     THEN 'doctorate'
                    WHEN 'p_se'  THEN 'doctorate'
                    WHEN 'p_oth' THEN 'doctorate'
                    WHEN 'none'  THEN 'none'
                    WHEN 'other' THEN 'other'
                    ELSE NULL
                END,
                SUM(ce.enrolled_at_end),
                COUNT(ce.user_id)
            FROM analytics.course_enrollment ce
            LEFT OUTER JOIN IITBxDataAnalytics.User p ON p.lmsUserId = ce.user_id
            GROUP BY
                ce.datestamp,
                ce.course_id,
                CASE p.highestEduDegree
                    WHEN 'el'    THEN 'primary'
                    WHEN 'jhs'   THEN 'junior_secondary'
                    WHEN 'hs'    THEN 'secondary'
                    WHEN 'a'     THEN 'associates'
                    WHEN 'b'     THEN 'bachelors'
                    WHEN 'm'     THEN 'masters'
                    WHEN 'p'     THEN 'doctorate'
                    WHEN 'p_se'  THEN 'doctorate'
                    WHEN 'p_oth' THEN 'doctorate'
                    WHEN 'none'  THEN 'none'
                    WHEN 'other' THEN 'other'
                    ELSE NULL
                END """

#Analysis Result Based on Mode
sql4 =""" insert into analytics.course_enrollment_mode_daily (date,course_id, mode , count, cumulative_count) SELECT
                ce.datestamp,
                ce.course_id,
                ce.enrollment_mode,
                SUM(ce.enrolled_at_end),
                COUNT(ce.user_id)
            FROM analytics.course_enrollment ce
            GROUP BY
                ce.datestamp,
                ce.course_id,
                ce.enrollment_mode """

#Analysis Result Based on Normal Analysis
sql5 = """ insert into analytics.course_enrollment_daily (course_id, date , count, cumulative_count) SELECT
                ce.course_id,
                ce.datestamp,
                SUM(ce.enrolled_at_end),
                COUNT(ce.user_id)
            FROM analytics.course_enrollment ce
            GROUP BY
                ce.course_id,
                ce.datestamp """

#Analysis ResultBased on State Analysis
sql6 = """  insert into analytics.course_enrollment_location_current_state (date, course_id, state, count, cumulative_count) SELECT ce.datestamp, ce.course_id, p.state ,SUM(ce.enrolled_at_end), COUNT(ce.user_id) 
FROM analytics.course_enrollment ce LEFT OUTER JOIN IITBxDataAnalytics.User as  p ON p.lmsUserId = ce.user_id GROUP BY ce.datestamp, ce.course_id, p.state """

sql7 = """ set sql_safe_updates = 0 """

sql8 = """ update analytics.course_enrollment set state_id=(select id from IITBxDataAnalytics.States where name=analytics.course_enrollment.state) """
try:
	cursor.execute(sql1)
	cursor.execute(sql2)
	cursor.execute(sql3)
	cursor.execute(sql4)
	cursor.execute(sql5)
 	db.commit()
	cursor.execute(sql6)
 	db.commit()
	
except Exception, err:
 	traceback.print_exc()
 	db.rollback()

db.close()

