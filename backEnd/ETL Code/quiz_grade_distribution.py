import pymongo
import traceback
import exceptions
import MySQLdb
import sys

db1 = MySQLdb.connect("localhost","root","root123","analytics")
cursor = db1.cursor()

mongoClient=pymongo.MongoClient("mongodb://localhost:27017/")
mongoDBName="edxapp"
db=mongoClient[mongoDBName]
edxCollection=db["modulestore"]

courseCourseQry = {}
courseCourseQry["_id.category"]="course"

courseCursor = edxCollection.find(courseCourseQry);

for courseDo in courseCursor:
	course = courseDo.get("_id").get("course")
	org = courseDo.get("_id").get("org")
	weight = courseDo.get("definition")
	if (weight) :
		data = weight.get("data")
		if (data):
			gp = data.get("grading_policy")
			if (gp) :
				grad = gp.get("GRADER")
				for val in grad:
					weig = val.get("weight")
					label = val.get("short_label")
					print course
					print weig
					print label
					sql = "INSERT INTO analytics.quiz_grade_distribution (organization_name,course_id,\
 quiz,percentage_marks)\
 VALUES ('%s','%s','%s','%s')" % \
(org,course,label,weig)
					try:
						cursor.execute(sql)
						db1.commit()
						print sql
					except Exception ,err:
						traceback.print_exc()
						db1.rollback()						
						print err

db1.close()



						
		



