import math
import csv
import hashlib
import html5lib
import json
from operator import itemgetter
import MySQLdb

import pyspark
from pyspark import SparkContext
from pyspark.sql import SQLContext, HiveContext

db = MySQLdb.connect("localhost","root","root123","analytics")
cursor = db.cursor()

sc = SparkContext("local","answer_distribution")
hq = HiveContext(sc)
sq = SQLContext(sc)
"""
def initializefunc(key,event):	
	values = sorted(event)
	course_id=values[0][1]
	problem_id = key[0]
	eventlist=[]
	eventlist.append(values[0])
	if len(values) > 1:
		eventlist.append(values[-1])
	for ele in eventlist:
		yield (course_id,problem_id),(ele[0],ele[2:])
	
def calc(events):
	correct_first = 0
	correct_last = 0
	incorrect_first = 0
	incorrect_last = 0
	for ele in events :
		if ((ele[1] == 'correct') and (ele[0]== 1)):
			correct_first = correct_first+1
		elif ((ele[1] == 'incorrect') and (ele[0]==1)):
			incorrect_first = incorrect_first+1
		elif (ele[1] == 'correct'):
			correct_last = correct_last+1
		elif (ele[1] == 'incorrect'):
			incorrect_last = incorrect_last+1
	correct_tuple = ('correct',correct_first,correct_last)
	incorrect_tuple = ('incorrect',incorrect_first,incorrect_last)
	tuple_list = []
	tuple_list.append(correct_tuple)
	tuple_list.append(incorrect_tuple)
	return tuple_list
"""

lines = hq.sql (""" select courseName,lmsUserId,eventType,eventName,eventNo,createDateTime,createDateTime from LogData where courseName is not NULL and lmsUserId is not NULL limit 100""")
#(problem_id,userid)(DateTimeStamp,coursename,chaptertitle,moduletitle,attempts,sucess)

for line in lines.collect() :
	print line
maplvl1=lines.map(lambda p: ((p[0],p[1]),(p[2],p[0],p[4],p[6],p[3],p[7])))
"""maplvl2 = maplvl1.groupByKey()
maplvl3 = maplvl2.map(lambda p: (p[0],list(p[1])))
maplvl4=maplvl3.flatMap(lambda p: initializefunc( p[0],p[1] ) )
maplvl5=maplvl4.map(lambda p :((p[0][0],p[0][1],p[1][1][0],p[1][1][1]),(p[1][1][2],p[1][1][3])))
maplvl6=maplvl5.groupByKey()
maplvl7=maplvl6.map(lambda p: (p[0],list(p[1])))
maplvl8=maplvl7.map(lambda p : (p[0], calc(p[1])))
for lin in maplvl8.collect() :
	for ele in lin[1]:
		sql="INSERT INTO analytics.answer_distribution (course_id,part_id,module_id,correct,problem_display_name,first_response_count,last_response_count) VALUES ('%s','%s','%s','%s','%s','%d','%d')" % ((lin[0][0]),(lin[0][2]),(lin[0][1]),(ele[0]),(lin[0][3]),(int)(ele[1]),(int)(ele[2]))
		try:
      			cursor.execute(sql)
      			db.commit()
								

		except Exception, err:
        		traceback.print_exc()
        		db.rollback()

db.close() 

SET SQL_SAFE_UPDATES = 0;
update analytics.answer_distribution t set quiz_name = IFNULL((select distinct chapteTitle from IITBxDataAnalytics.CourseChapter
where chapterSysName=t.parT_id),NULL) """
