import datetime
from pyspark.sql import HiveContext
from pyspark import SparkContext
from pyspark.sql import Row, StructField, StructType, StringType, IntegerType

ACTIVE_LABEL = "ACTIVE"
PROBLEM_LABEL = "ATTEMPTED_PROBLEM"
PLAY_VIDEO_LABEL = "PLAYED_VIDEO"
POST_FORUM_LABEL = "POSTED_FORUM"

def mapp(course_id, username, date_string, eventName):
	for label in get_predicate_labels(eventName):
		yield _encode_tuple((course_id, username, date_string, label)) , 1
	#eventlist.append((_encode_tuple((course_id, username, date_string, label)) , 1)) 
	#return eventlist

def get_predicate_labels(event):
        """Creates labels by applying hardcoded predicates to a single event."""

	labels = [ACTIVE_LABEL]

    	if event == 'problem_check':
		labels.append(PROBLEM_LABEL)

        if event == 'thread_create':
                labels.append(POST_FORUM_LABEL)

        if event == 'play_video':
                labels.append(PLAY_VIDEO_LABEL)

        return labels

def _encode_tuple(values):
        """
        Convert values into a tuple containing encoded strings.
        Parameters:
            Values is a list or tuple.
        This enforces a standard encoding for the parts of the
        key. Without this a part of the key might appear differently
        in the key string when it is coerced to a string by luigi. For
        example, if the same key value appears in two different
        records, one as a str() type and the other a unicode() then
        without this change they would appear as u'Foo' and 'Foo' in
        the final key string. Although python doesn't care about this
        difference, hadoop does, and will bucket the values
        separately. Which is not what we want.
        """
        # TODO: refactor this into a utility function and update jobs
        # to always UTF8 encode mapper keys.
        if len(values) > 1:
            return tuple([value.encode('utf8') for value in values])
        else:
            return values[0].encode('utf8')

    
sc = SparkContext("local", "Course Activity")
	#sqlHC is the SQLHiveContext        
sqlHC = HiveContext(sc)

lines=sqlHC.sql(""" select courseName,lmsUserId,createDateTime,
		            eventType,eventName,eventNo from logdata where 
			    eventType not in ('enrollment','instructor','admin') 
			    and lmsUserId is not NULL 
   			    and courseName is not NULL 
			    and eventNo is not NULL limit 100""")


maplvl1=lines.flatMap(lambda p: mapp(p[0],str(p[1]),p[2].strftime('%Y-%m-%d'),p[4]))
for linet in maplvl1.collect():
	print linet

reduceRDD = maplvl1.reduceByKey(lambda a, b : a + b)

fo = open("tester","w")

for line in reduceRDD.collect():
	fo.write(line[0][0]+"\x01"+line[0][1]+"\x01"+line[0][2]+"\x01"+line[0][3]+"\x01"+str(line[1])+"\n")




