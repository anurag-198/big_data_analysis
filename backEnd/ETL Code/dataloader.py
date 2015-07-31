__author__ = 'virus'

import traceback
import classes
import json
import fetch
import os


"""def chapterSess(sessionSysName,courseName):
	result_courseChapter=models.Coursechapter.objects.filter(coursename=courseName)
	result_sessionSysName=models.Coursechaptersession.objects.filter(sessionsysname=sessionSysName)
	result={}
	for r1 in result_courseChapter :
		for r2 in result_sessionSysName :
			if r1.coursename == r2.coursename and r1.chaptersysname==r2.chaptersysname :
				result['chaptertitle']=r1.chaptertitle
				result['sessiontitle']=r2.sessiontitle
				result['chaptersysname']=r1.chaptersysname
				return result


	return result
"""

"""def videoQuery(coursename,videosysname):
	result_courseVideos=models.Coursevideo.objects.filter(coursename=coursename,videosysname=videosysname)
	result_courseChapter=models.Coursechapter.objects.all()
	result={}
	for r1 in result_courseVideos :
		for r2 in result_courseChapter :
			if r1.coursename == r2.coursename and r1.chaptersysname==r2.chaptersysname :
				result['videotitle']=r1.videotitle
				result['chaptertitle']=r2.chaptertitle
				result['chaptersysname']=r1.chaptersysname
				return result

	return result
"""


def _decode_list(data):
	rv = []
	for item in data:
		if isinstance(item, unicode):
			item = item.encode('utf-8')
		elif isinstance(item, list):
			item = _decode_list(item)
		elif isinstance(item, dict):
			item = _decode_dict(item)
		rv.append(item)
	return rv


def _decode_dict(data):
	rv ={}
	for key, value in data.iteritems():
		if isinstance(key, unicode):
			key = key.encode('utf-8')
		if isinstance(value, unicode):
			value = value.encode('utf-8')
		elif isinstance(value, list):
			value = _decode_list(value)
		elif isinstance(value, dict):
			value = _decode_dict(value)
		rv[key] = value
	return rv


def processCourses(usersession,event_type_str) :

	try :

		tmpStr = event_type_str.split("/")

		if len(tmpStr) > 0 :
			if tmpStr[len(tmpStr) - 1] == "courseware" :

				usersession.modulesysname="courseware"

				usersession.eventname="courseWare"
				eventName = "courseWare"
				usersession.chaptertitle="courseware"


		if len(tmpStr) > 1 :

			if tmpStr[len(tmpStr) - 2]== "courseware" :
				usersession.modulesysname=tmpStr[len(tmpStr) - 1]
				usersession.chaptersysname=tmpStr[len(tmpStr)-1]
				usersession.eventname="chapter"
				eventName = "chapter"
				courseName=usersession.coursename
				moduleSysName=usersession.modulesysname

				result_CourseChapter = fetch.Chapter(courseName,moduleSysName)



				if  len(result_CourseChapter[0]) > 0 :
					usersession.chaptertitle=result_CourseChapter[0][0]




		if len(tmpStr)> 2 :
			if tmpStr[len(tmpStr) - 3] == "courseware" :
				usersession.modulesysname=tmpStr[len(tmpStr) - 1]
				eventName = "session"
				usersession.eventname=eventName
				moduleSysName = tmpStr[len(tmpStr) - 1]
				usersession.modulesysname=moduleSysName

				result=fetch.ChapterSess(moduleSysName,usersession.coursename)
				if (len(result) > 1) :
					usersession.chaptertitle=result[0][0]
					usersession.sesstitle=result[0][1]
					usersession.chaptersysname=result[0][2]
				usersession.sesssysname=moduleSysName





		if len(tmpStr)> 4 :
			if tmpStr[len(tmpStr) - 5]=="courses" :
				usersession.eventtype="courses"
				usersession.modulesysname=tmpStr[len(tmpStr) - 1]
				usersession.eventname="courses_chapter"
				courseName=usersession.coursename
				moduleSysName=usersession.modulesysname

				result_CourseChapter = fetch.Chapter(courseName,moduleSysName)

				if  len(result_CourseChapter) > 0 :
					usersession.chaptertitle=result_CourseChapter[0][0]


		if len(tmpStr) > 3 :
			if tmpStr[len(tmpStr) - 4]=="courses" :
				usersession.eventtype="courses"
				usersession.modulesysname="courses"
				usersession.eventname="courses_access"
				usersession.chaptertitle="courses"




		if len(tmpStr) > 2   :
			if tmpStr[len(tmpStr) - 3]=="courses" :
				usersession.eventtype="courses"
				usersession.eventname="courses_access"
				usersession.coursename=tmpStr[len(tmpStr) - 1]
				usersession.orgname=tmpStr[len(tmpStr) - 2]


		if len(tmpStr) > 1 :
			if tmpStr[len(tmpStr) - 2]=="courses" :
				usersession.eventtype="courses"
				usersession.eventname="courses_access"
				usersession.orgname=tmpStr[len(tmpStr) - 1]






	except :
		traceback.print_exc()

	return



def processProblem(usersession,event_type_str,event_str,user_data) :
	stateJson=None
	submissionJson=None
	correctJson=None
	answerJson=None
	otherJson=None
	#event_str=json.dumps(event_str)
	#print user_data.get("event")
	eventJson=json.loads(str(user_data.get("event")))
	#eventJson=_decode_dict(eventJson)
	#print eventJson.get("submission")
	usersession.attempts=eventJson.get("attempts")

	#resultset not yet there   ResultSet rs

	inputkeys=""             #string
	KeySet=None              #set
	

	try:
		if usersession.eventname == "problem_get" :
			usersession.modulesysname=event_type_str[ event_type_str.find("_problem;_")+10 : event_type_str.find("/handler") ]
		elif usersession.eventname=="problem_save":
			if usersession.eventsource=="browser":
				if event_str.find("-problem-")>0:
					tmpStr1=event_str[event_str.find("-problem-")+9:]
					usersession.modulesysname=tmpStr1[ 0:tmpStr1.find("_") ]
			else:
			   usersession.modulesysname=event_type_str[ event_type_str.find("_problem_;")+10 : event_type_str.find("/handler") ]

		elif usersession.eventname=="problem_check_fail" or usersession.eventname=="problem_check":
			if usersession.eventsource=="browser" and usersession.eventname=="problem_check":

				if event_str.find("-problem-")>0:
					tmpStr1=event_str[ event_str.find("-problem-")+9: ]
					usersession.modulesysname=tmpStr1[ 0:tmpStr1.find("_")]

					if len(tmpStr1) > tmpStr1.find("=")+3 :
						usersession.answerchoice=tmpStr1[tmpStr1.find("=")+1 : len(tmpStr1)-2]

				else:
					inputkeys=user_data.get("page").split("/")
					if "courseware" in user_data.get("page"):
						if inputkeys[ len(inputkeys)-1 ]=="courseware":
							usersession.modulesysname="courseware"
							usersession.chaptertitle="courseware"
					elif inputkeys[ len(inputkeys)-2 ]=="courseware":
						usersession.modulesysname=inputkeys[len(inputkeys)-1]
						#psChapter.setString(1,courseName)
						#psChapter.setString(2,userSession.getModuleSysName())
						#rs=self.psChapter.executeQuery()
						
						result = fetch.Chapter(usersession.coursename, usersession.modulesysname);
						usersession.chaptertitle=result[0][0];
						
						usersession.chaptersysname=inputkeys[ len(inputkeys)-1 ]
						

						#if rs.next():
						#  ChapterTitle(rs.getString(1)
					elif inputkeys[ len(inputkeys)-3 ]=="courseware":
						usersession.modulesysname=inputkeys[ len(inputkeys)-1 ]
					
					elif inputkeys[ len(inputkeys)-4 ]=="courseware":
						usersession.modulesysname=inputkeys[ len(inputkeys)-2 ]
					
					


			else:
				#print eventJson
				eventJson=str(user_data.get("event"))
				#eventJson=json.dumps(eventJson)
				eventJson=json.loads(eventJson)
				#eventJson=_decode_dict(eventJson)
				#print eventJson
				
				#eventJson=_decode_dict(eventJson)
				if event_type_str!="problem_check" and event_type_str!="problem_check_fail":
					if len(event_type_str)>0:
						usersession.modulesysname=event_type_str[ event_type_str.find("_problem;_")+10 : event_type_str.find("/handler") ]
						
						#print user_data.get("event")
						try:
							eventJson2=json.loads(user_data.get("event"))
							eventJson2=_decode_dict(eventJson2)
						
							answerJson=eventJson2.get("POST")

						#print answerJson
						
						#answerJson=_decode_dict(answerJson)
						except :
							pass           #some logs may have unterminated strings
							#print user_data.get("event")
							#traceback.print_exc()


				else:
					tmpStr1=eventJson.get("problem_id")
					usersession.modulesysname=tmpStr1[ tmpStr1.rfind("/")+1 :]

					stateJson=  eventJson.get("state")
					#print stateJson
					#stateJson=_decode_dict(stateJson)
					if stateJson.get("done")==None:
						usersession.done=""
					else:
						usersession.done=stateJson.get("done")

					if event_type_str=="problem_check_fail":
						correctJson=stateJson.get("correct_map")
						
					else:
						correctJson=eventJson.get("correct_map")
						eventJson=user_data.get("event")
					#variable not yet declared anywhere
					KeySet=correctJson.keys()

					#code for the line : inputkeys = (String[]) KeySet.toArray(new String[KeySet.size()]);
					inputkeys=[]
					for elem in KeySet:
						inputkeys.append(elem)

					tmpStr1=""
					tmpStr2=""
					tmpStr3=""

					for i in range(0, len(inputkeys) ):
						otherJson=correctJson.get( inputkeys[i] ) 
						#otherJson=_decode_dict(otherJson)
						if i==0:
							tmpStr1=str( otherJson.get("hint") )
							if otherJson.get("hintmode")==None:
								tmpStr2+="null"
							else :
								tmpStr2+=str( otherJson.get("hintmode") )
								tmpStr3+=str( otherJson.get("correctness") )
						else:
							tmpStr1+=","  +str( otherJson["hint"] )
							if otherJson["hintmode"]==None:
								tmpStr2+=",null"
								tmpStr2+="," + str( otherJson["hintmode"] )
							else:
								tmpStr3+=","+str( otherJson["correctness"] )

					usersession.hintavailable=tmpStr1
					usersession.hintused=tmpStr2
					usersession.success=tmpStr3


					if event_type_str=="problem_check":
						usersession.currgrade=int(eventJson["grade"])        #convert it to float : doubt
						#self.currGrade( str( self.eventJson["grade"] ) )
						usersession.success=eventJson["success"]
						usersession.attempts=eventJson.get("attempts")     #convert it to int : doubt
						usersession.maxgrade=int(eventJson["max_grade"] )  #convert it to float : doubt

					answerJson=eventJson["answers"] 
					
				
				KeySet=[]
				if(answerJson!=None):
					KeySet=answerJson.keys()


			   #doubtful here : inputkeys = (String[]) KeySet.toArray(new String[KeySet.size()]);

				inputkeys=[]
				for elem in KeySet:
				  inputkeys.append(elem)

				tmpStr1=""
				tmpStr2=""

				for i in range(0, len(inputkeys) ):
					tmpStr1=str( answerJson.get( inputkeys[i]) )
					if "choice" in tmpStr1:
						tmpStr1=tmpStr1[2:len(tmpStr1)-2]
					if i==0:
						tmpStr2+=tmpStr1
					else:
						tmpStr2+="," + tmpStr1

				usersession.answerchoice=tmpStr2
				if(usersession.answerchoice.startswith("'")):
					usersession.answerchoice=usersession.answerchoice[1:]

		elif usersession.eventname=="problem_reset":
			pass
		elif usersession.eventname=="problem_rescore":
			pass
		elif usersession.eventname=="reset_problem_fail":
			pass
		elif usersession.eventname=="reset_problem":
			pass
		elif usersession.eventname=="save_problem_success":
			answerJson=eventJson.get("answers")
			KeySet=answerJson.keys()
			
			#print answerJson.keys()
			#print eventJson 
			#print answerJson
			answers=""
			inputkeys=[]
			for elem in KeySet:
				inputkeys.append(elem)
			
			#print inputkeys
				
				
			for  i in range(0,len(inputkeys)):
				#//	System.out.println("POST KEYS ARE " + answerJson.get(inputkeys[i]));
				
					tmp=answerJson.get(inputkeys[i])
					if isinstance(tmp,list):
						tmp=tmp[0]
					if(i==0):
						answers+= tmp
					else :
						answers+= "," + tmp
				
			usersession.answerchoice=answers
			stateJson = eventJson.get("state")
			
			if(stateJson.get("done") == None):
					usersession.done=""
			else:
				usersession.done=stateJson.get("done")
				#// correctness, hint, hintavailable
			correctJson = stateJson.get("correct_map")
			KeySet = correctJson.keys()					
			inputkeys = []
			for key in KeySet :
				inputkeys.append(key)
			
			tmpStr1 = ""
			tmpStr2=""
			tmpStr3=""
			for  i in range(0,len(inputkeys)):
				#//	System.out.println("correct_map KEYS ARE " + answerJson.get(inputkeys[i]));
				otherJson = correctJson.get(inputkeys[i])
				if(i==0):
					tmpStr1 += str(otherJson.get("hint"))
					if(otherJson.get("hintmode") == None):
							tmpStr2 += "null"
					else:
						tmpStr2 += str(otherJson.get("hintmode"))
					tmpStr3 += str(otherJson.get("correctness"))
					
				else :
					tmpStr1 += "," + str(otherJson.get("hint"))
					if(otherJson.get("hintmode") == None) :
						tmpStr2 += ",null";
					else :
						tmpStr2 += "," + otherJson.get("hintmode")
							
					
					tmpStr3 += "," + str(otherJson.get("correctness"))
				
			usersession.hintavailable=tmpStr1
			usersession.hintused=tmpStr2
			usersession.success=tmpStr3
			if(otherJson!=None):
				usersession.hintmode=otherJson.get("hintmode")
			tmpStr1 = str(eventJson.get("problem_id"))
			usersession.modulesysname=tmpStr1[tmpStr1.rfind("/")+1:]
			
		elif(usersession.eventname=="problem_graded"):
			tmpStr1 = event_str[event_str.find("-problem-") + 9:]
			usersession.modulesysname=tmpStr1[0:tmpStr1.find("_")]
		elif(usersession.eventname=="problem_show"):
			if(usersession.eventsource == "server"):
				usersession.modulesysname=event_type_str[event_type_str.find("_problem;_") + 10 : event_type_str.find("/handler")]
			else :
				eventJson = user_data.get("event")
				eventJson=json.loads(eventJson)
				inputkeys = str(eventJson.get("problem")).split("/")
			
				usersession.modulesysname=inputkeys[len(inputkeys)- 1]
				usersession.modulesysname=event_type_str[event_type_str.find("_problem;_") + 10: event_type_str.find("/handler")]
				
		elif(usersession.eventname == "showanswer"):
				inputkeys = str(eventJson.get("problem_id")).split("/")
				usersession.modulesysname=inputkeys[len(inputkeys) - 1]
			
		
			
		rsPS = fetch.Problem(usersession.modulesysname,usersession.coursename)
		#print rsPS
		if(len(rsPS)!=0):		
				usersession.chaptertitle=rsPS[0][0]
				usersession.moduletitle=rsPS[0][1]
			
		if(user_data.get("metadata") != None ):
			metadata = mainRecJson.get("metadata")
			if(metadata.get("max_attempts") != None):
				usersession.maxnoattempts=int(str(metadata.get("max_attempts")))
			if(metadata.get("display_name") != None):
				usersession.moduletitle=str(metadata.get("display_name"))
			if(metadata.get("weight") != None):
				userSession.maxgrade=float(str(metadata.get("weight")))
			
	except:
		traceback.print_exc()
	return


def processVideo(usersession,user_data):

	eventTypeStr=user_data.get('event_type')
	eventStr=user_data.get('event')
	#ResultSet rs;  #no idea what this is
	try:
		eventJson=json.loads(str(user_data.get("event")))
		#eventJson=_decode_dict(eventJson)
		#eventJson=json.loads(eventJson)
		#print eventJson
		eventJson=_decode_dict(eventJson)
		
		#user_data=_decode_dict(user_data)
		#eventJson = user_data
	except:
		traceback.print_exc()
		
	#catch (ParseException e1)
	#{
	#  // TODO Auto-generated catch block
	#  e1.printStackTrace();
	#}

	#  /** VIDEO SYS NAME */
	try:
			if (eventJson.has_key("id")):
				tmpStr=eventJson["id"]
				usersession.modulesysname=tmpStr.split("-")[4]

			else:

				if ("/transcript/download" in eventTypeStr) or ("/transcript/translation/" in eventTypeStr):

					usersession.modulesysname=eventTypeStr[eventTypeStr.find("_video;_") + 8 : eventTypeStr.find("/handler/")]

				else:
					usersession.modulesysname=None

			if(usersession.modulesysname != None):
	
				if(usersession.coursename[0]=="W"):
					usersession.coursename = usersession.coursename[1:]

			result=fetch.videoQuery(usersession.coursename,usersession.modulesysname)


			if (len(result) > 0 and len(result[0]) > 1):
				  usersession.moduletitle=result[0][0];
				  usersession.chaptertitle=result[0][1];

	   
	except :
		   traceback.print_exc()



	if (eventJson.get("currentTime") != None):


		 currseektime = float(eventJson["currentTime"])

	elif ("speed_change_video" in eventTypeStr):

		 currseektime = float(eventJson.get("current_time"))
	else:
		 currseektime = float(0)


	  #/** event = seek_video */
	if ("seek_video" in eventTypeStr):

		if(eventJson.get("new_time") != None):
			currseektime = float(eventJson["new_time"])
		if(eventJson.get("old_time") != None):
			oldseektime = float(eventJson["old_time"])
		if(eventJson.get("type") != None):
			videonavigtype = eventJson["type"]
		else:
			videonavigtype = None
	else:

		 oldseektime = float(0)
		 videonavigtype = None



	  #/** OLD SPEED AND CURRENT SPEED */
	if ("speed_change_video" in eventTypeStr):

		oldspeed = float(eventJson.get("old_speed"))
		currspeed = float(eventJson.get("new_speed"))

	else:

		 oldspeed = float(0)
		 currspeed = float(0)

	  #     /** VIDEO POSITION  == currVideoTime*/
	  #//videoPosition = null;

	if ("save_user_state" in eventTypeStr):

		 #System.out.println( " IN save_user_state");
		 
		 
		videoPostString = eventJson.get("POST")
		try:

			#user_data=json.loads(videoPostString)
			#user_data=_decode_dict(user_data)
			videoPostJson=videoPostString
			#videoPostJson=_decode_dict(videoPostJson)
			#print videoPostJson.get("saved_video_position")
			

			
			if (videoPostJson.has_key("saved_video_position")):

				tmpStr = videoPostJson["saved_video_position"]
				tmpStr= tmpStr[0]
				#print tmpStr

				if "-" not in tmpStr:
					currseektime = float(str(int(tmpStr[0:2]) * 3600 +int(tmpStr[3:5]) * 60 + int(tmpStr[6:8])))
					usersession.currentseektime=currSeekTime
				else:

				  tmpStr = str(videoPostJson["saved_video_position"])
				  #print tmpStr
				  tmpStr1 = tmpStr[tmpStr.find(":") + 1:]
				  currSeekTime = float(str(int(tmpStr[tmpStr.find("-") +1: tmpStr.find(":")]) * 3600 +int(tmpStr[tmpStr.find("-")+1: tmpStr.find(":")]) * 60 +int(tmpStr[tmpStr.find("-")+1: len(tmpStr) -2] )))
				  usersession.currentseektime=currSeekTime

			else:
			   pass
		except:
			traceback.print_exc()
	
	

	if(currseektime != None):
		 usersession.currvideotime=currseektime
	if(oldseektime != None):
		 usersession.oldvideotime=oldseektime
	if(currspeed != None):
		 usersession.currvideospeed=currspeed
	if(oldspeed != None):
		 usersession.oldvideospeed=oldspeed
	if(videonavigtype != None):
		 usersession.videonavigtype=videonavigtype

	return

def processOthers(usersession,user_data):      #throws parse exception

   try:
	  tmpStr=[]
	  eventTypeStr=user_data.get('event_type')
	  eventStr=user_data.get('event')

	  #ResultSet rs

	  if(eventTypeStr == "/admin/"):

		 usersession.eventtype = "admin"
		 usersession.eventname = "admin"

		 #/* changed eventtype to instructor from admin in next two ---------*/

	  elif(eventTypeStr=="list-staff"):

		 usersession.eventtype = "instructor"
		 usersession.eventname = "list-staff"

		 #/* changed eventtype to instructor from admin in next two ---------*/

	  elif (eventTypeStr=="dump-graded-assignments-config"):

		 usersession.eventtype = "instructor"
		 usersession.eventname = "dump-graded-assignments-config"

	  elif ((eventTypeStr=="edx.course.enrollment.mode_changed") or
			(eventTypeStr=="edx.course.enrollment.activated") or
			(eventTypeStr=="edx.course.enrollment.deactivated")):

		 try:
			eventJson=json.dumps(eventStr)
			eventJson=json.loads(eventJson)
			eventJson=_decode_dict(eventJson)
			

			usersession.eventname = eventTypeStr
			usersession.eventtype = "enrollment"
			usersession.enrolmentmode=eventJson.get("mode")

			if (eventJson.has_key("user_id")):

			   user_id = eventJson.get("user_id")         # .trim() function was used here, not yet included

			   if (user_id != 0):
				usersession.lmsuserid=long(user_id)



			coursename = str(eventJson.get("course_id"))
			if (len(coursename) != 0) :

			   tmpStr = coursename.split("/")
			   coursename = tmpStr[1]
			   usersession.coursename=coursename
			   usersession.courserun=tmpStr[2]

		 except:
			traceback.print_exc()


		 #catch (ParseException e)
		 #{
		 #  // TODO Auto-generated catch block
		 #  e.printStackTrace();
		 #}

	  elif (eventTypeStr=="/accounts/login"):
		 try :

			eventJson=json.loads(eventStr)
			eventJson=_decode_dict(eventJson)
			


			usersession.eventname = eventTypeStr[eventTypeStr.rfind("/") + 1:]
			#//System.out.println("eventName " + eventName);
			usersession.eventtype = "navigation"

			tmpJson = eventJson.get("GET")

			tmpStr1 = tmpJson.get("next")
			
			if(isinstance(tmpStr1,list)):
				tmpStr1=tmpStr1[0]
			
			if(tmpStr1!=None):
				tmpStr = tmpStr1[3:tmpStr1.rfind('"]')].split("/")

			if("courseware" in str(tmpJson)):

			   if("courseware" in tmpStr[len(tmpStr) - 1]):

				  usersession.modulesysname="courseware"
				  usersession.chaptertitle="courseware"
				  #//userSession.setModuleType("courseware");

			   elif("courseware" in tmpStr[len(tmpStr) - 2]):

				  usersession.modulesysname=tmpStr[len(tmpStr) - 1][0:len(tmpStr[len(tmpStr) - 1]) -1]

				  result_CourseChapter = fetch.Chapter(usersession.coursename,usersession.modulesysname)



				  if  (result_CourseChapter !=None and len(result_CourseChapter) > 0) :
					usersession.chaptertitle=result_CourseChapter[0]
		
				  usersession.chaptersysname=tmpStr[len(tmpStr) - 1]


			   elif("courseware" in tmpStr[len(tmpStr) - 3]):

				  usersession.modulesysname=tmpStr[len(tmpStr) - 1][0:len(tmpStr[len(tmpStr) - 1]) -1]

				  result=fetch.ChapterSess(usersession.modulesysname,usersession.coursename)
				  
				  if ((result !=None) and len(result)>0) :
					usersession.chaptertitle=result[0][0]
					usersession.moduletitle=result[0][1]
					usersession.chaptersysname=result[0][2]


				  #psChapterSess.setString(1, userSession.getModuleSysName());
				  #psChapterSess.setString(2, courseName);
				  #rsChapterSess = psChapterSess.executeQuery();

				  #if(rsChapterSess.next())
				  #{
				  #  userSession.setChapterTitle(rsChapterSess.getString(1));
				  #  userSession.setModuleTitle(rsChapterSess.getString(2));
				  #  userSession.setChapterSysName(rsChapterSess.getString(3));
				  #}

			elif(tmpStr!=None and len(tmpStr)>0):
			   usersession.chaptertitle=tmpStr[len(tmpStr) -1]

		 #//    System.out.println("tuserSession.setModuleSysName " + userSession.getModuleSysName());
		 #//    System.out.println("INFO title " + userSession.getChapterTitle() + " Module " +
		 #  //    userSession.getModuleTitle() + " chapt " + userSession.getChapterSysName());

		 except:
			traceback.print_exc()
		
		 #catch (ParseException e)
		 #{
		 #  #// TODO Auto-generated catch block
		 #  e.printStackTrace();
		 #}
		 #catch (SQLException e)
		 #{
		 #  // TODO Auto-generated catch block
		 #  e.printStackTrace();
		 #}
	  elif (eventTypeStr=="/"):

		 usersession.eventtype="navigation"
		 usersession.eventname = "/"

	  elif (eventTypeStr=="/jsi18n/"):

		 usersession.eventtype="navigation"
		 usersession.eventname = "jsi18n"

	  elif (eventTypeStr=="/i18n.js"):

		 usersession.eventtype="navigation"
		 usersession.eventname = "i18n"

	  elif (eventTypeStr=="/dashboard"):

		 usersession.eventtype = "navigation"
		 usersession.eventname = "dashboard"

	  elif (eventTypeStr=="/change_enrollment"):

		 usersession.eventtype="enrollment"
		 usersession.eventname = "change_enrollment"

	  elif (eventTypeStr=="edx.forum.searched"):

		 usersession.eventtype="discussion"
		 usersession.eventname = "forum_searched"

		 eventJson=json.dumps(user_data.get("event"))
		 eventJson=json.loads(eventJson)
		 eventJson=_decode_dict(eventJson)
		 


		 if ("query" in eventStr) and ("total_results" in eventStr):

			queryText = eventJson.get("query")
			#print eventJson.get("total_results")
			noOfResults = int(eventJson.get("total_results"))
			#print noOfResults
			usersession.querytext=queryText
			usersession.noofresults=noOfResults

	  elif ("/notification_prefs/status/" in eventTypeStr):

		 usersession.eventtype="admin"
		 usersession.eventname = "pref_status"

	  elif (eventTypeStr=="/register"):

		 usersession.eventtype="enrollment"
		 usersession.eventname = "register"

	  elif ("/activate/" in eventTypeStr):

		 usersession.eventtype="enrollment"
		 usersession.eventname = "activate"

	  elif (eventTypeStr=="/create_account"):

		 #//event.post.username

		 usersession.eventtype="enrollment"
		 usersession.eventname = "create_account"

#Checkfrom here
		 
		 events = eventStr.split(",")


		 for i in range( len(events) ):
			shorteve = events[i].split(":")

			if (len(shorteve) > 0) :
			   if ("aadhar_id" in shorteve[0]):

				  if (len(shorteve) > 1):

					 if (len(shorteve[1]) > 10):

						aadharId = shorteve[1][3: len(shorteve[1])-2]
						usersession.aadhar=aadharId

			if (len(shorteve) > 0):
			   if ("level_of_education" in shorteve[0]):

				  if (len(shorteve) > 1):
					 if (len(shorteve[1]) > 4):
						
						eduLevel = shorteve[1][3:len(shorteve[1])-2]
						#print eduLevel
						usersession.edulevel=eduLevel

			if (len(shorteve) > 0):
			   if ("gender" in shorteve[0]):

				  if (len(shorteve) > 1):

					 if (len(shorteve[1]) > 4):

						gender = shorteve[1][3: len(shorteve[1])-2]
						usersession.gender=gender

			if (len(shorteve) > 1):
			   if ("username" in shorteve[1]):

				  if (len(shorteve) > 2):

					 if (len(shorteve[2]) > 6):
						username = shorteve[2][3:len(shorteve[2])-2]
						usersession.username=username

				  if (usersession.username==""):
					 pass
				  else:
					 try:
						if(usersession.username!="" and "'" in usersession.username):
							usersession.username=usersession.username[:usersession.username.rfind("'")]
						result_User=fetch.User(usersession.username)

						if(result_User!=None  and len(result_User)>0) :
						   usersession.lmsuserid=result_User[0]
						#psUserSession.setString(1,username);
						#rs = psUserSession.executeQuery();

						#if (rs.next())
						#{
						#
						#  userSession.setLmsUserId(rs.getLong(1));
						#}

					 except:
						traceback.print_exc()
					 #catch (SQLException e)
					 #{
					 #  #// TODO Auto-generated catch block
					 #  e.printStackTrace();
					 #}

		 usersession.eventname="create_account"
		 usersession.eventtype="enrollment"

	  elif (eventTypeStr=="/how_it_works"):

		 usersession.eventtype="navigation"
		 usersession.eventname = "howItWorks"
	  elif (eventTypeStr=="/calculate"):
		 usersession.eventtype="navigation"
		 usersession.eventname = "calculate"
	  elif (eventTypeStr=="/password_reset/"):
		 usersession.eventtype="accounts"
		 usersession.eventname = "passwordReset"
	  elif ("/password_reset_confirm/" in eventTypeStr):

		 usersession.eventtype="accounts"
		 usersession.eventname = "passwordResetConfirm"


	  elif (eventTypeStr=="/logout"):
		 usersession.eventtype="navigation"
		 usersession.eventname = "logout"
	  elif "/city_ajax" in eventTypeStr:

		 usersession.eventtype="navigation"
		 usersession.eventname = "city"

   except:    #ParseException
	
	  traceback.print_exc()












def processNavig(usersession,user_data,event_str):
	eventTypeStr=user_data.get('event_type')

	tmpStr=[]

	try:
		if usersession.eventname=="page_close":


			tmpStr = user_data.get("page").split("/")

			if("courseware" in user_data.get("page")):

			   if(tmpStr[len(tmpStr) - 1]=="courseware"):

				  usersession.modulesysname="courseware"
				  usersession.chaptertitle="courseware"

			   elif(tmpStr[len(tmpStr) - 2]=="courseware"):

				  usersession.modulesysname=tmpStr[len(tmpStr) - 1]

				  #psChapter.setString(1, courseName);
				  #psChapter.setString(2, userSession.getModuleSysName());

				  #rs = psChapter.executeQuery();
				  courseName=usersession.coursename
				  moduleSysName=usersession.modulesysname

				  result_CourseChapter = fetch.Chapter(courseName,moduleSysName)



				  if  ((len(result_CourseChapter) > 0) and len(result_CourseChapter[0]) > 0) :
					 usersession.chaptertitle=result_CourseChapter[0][0]


				  chaptersysname=tmpStr[len(tmpStr) - 1]
				  usersession.chaptersysname=chaptersysname

				  #if (rs.next())
				  #{
				  #  userSession.setChapterTitle(rs.getString(1));
				  #}

			   elif(tmpStr[len(tmpStr) - 3]=="courseware"):

				  usersession.modulesysname=tmpStr[len(tmpStr) - 1]

				  result=fetch.ChapterSess(usersession.modulesysname,usersession.coursename)
				  if (len(result) > 0 and len(result[0]) > 2) :
					usersession.chaptertitle=result[0][0]
					usersession.sesstitle=result[0][1]
					usersession.chaptersysname=result[0][2]

				  #psChapterSess.setString(1, userSession.getModuleSysName());
				  #psChapterSess.setString(2, courseName);
				  #rsChapterSess = psChapterSess.executeQuery();

				  #if (rsChapterSess.next())
				  #{
				  #  userSession.setChapterTitle(rsChapterSess.getString(1));
				  #  userSession.setModuleTitle(rsChapterSess.getString(2));
				  #  userSession.setChapterSysName(rsChapterSess.getString(3));
				  #}

			   elif(tmpStr[len(tmpStr) - 4]=="courseware"):

				  usersession.modulesysname=tmpStr[len(tmpStr) - 2]
				  result=fetch.ChapterSess(usersession.modulesysname,usersession.coursename)
				  #print result
				  if ( result!=None and len(result) > 2) :
					usersession.chaptertitle=result[0][0]
					usersession.moduletitle=result[0][1]
					usersession.chaptersysname=result[0][2]

				  #psChapterSess.setString(1, userSession.getModuleSysName());
				  #psChapterSess.setString(2, courseName);
				  #rsChapterSess = psChapterSess.executeQuery();

				  #if (rsChapterSess.next())
				  #{
				  #  userSession.setChapterTitle(rsChapterSess.getString(1));
				  #  userSession.setModuleTitle(rsChapterSess.getString(2));
				  #  userSession.setChapterSysName(rsChapterSess.getString(3));
				  #}

		if((usersession.eventname=="seq_next") or (usersession.eventname=="seq_prev") or (usersession.eventname=="seq_goto")):

			eventJson=json.loads(event_str)
			eventJson=_decode_dict(eventJson)
			

			currPosition=int(eventJson.get("new"))
			usersession.currposition=currPosition

			if(eventJson.get("old") != None):
			   usersession.oldposition=int(eventJson.get("old"))
			tmpStr = eventJson.get("id").split("/")

			usersession.modulesysname=tmpStr[len(tmpStr) -1]


		if(usersession.eventname=="goto_position"):
			#eventJson=json.dumps(event_str)
			eventJson=json.loads(user_data.get("event"))
			eventJson=_decode_dict(eventJson)
		


			if(eventJson.get("POST") != None):

			   postJson = eventJson.get("POST")
			   if(postJson.get("position") != None):
				  text=postJson.get('position')
				  
				  if(isinstance(text,list)):
					currPosition=int(text[0])
				  else :	
				     currPosition=int(text[text.find('["')+1:text.find('"]')])
				  usersession.currposition=currPosition

			tmpStr = eventTypeStr.split(";_")

			usersession.modulesysname=tmpStr[len(tmpStr) - 1][0:tmpStr[len(tmpStr) - 1].find("/")];


		 #psChapterSess.setString(1, userSession.getModuleSysName());
		 #psChapterSess.setString(2, courseName);
		rsChapterSess =fetch.ChapterSess(usersession.modulesysname,usersession.coursename)
		
	
		if(rsChapterSess!=None and len(rsChapterSess)>0):
			
			usersession.chaptertitle=rsChapterSess[0][0]
			usersession.moduletitle=rsChapterSess[0][1]
			usersession.chaptersysname=rsChapterSess[0][2]
			
		 #if(rsChapterSess.next())
		 #{
		 #  userSession.setChapterTitle(rsChapterSess.getString(1));
		 #  userSession.setModuleTitle(rsChapterSess.getString(2));
		 #  userSession.setChapterSysName(rsChapterSess.getString(3));


	except :
		
		traceback.print_exc()

	return


def convertEventToNumber(eventName) :

	eventNo=0
	if eventName == "load_video" :
		eventNo = 11
	if eventName == "pause_video" :
		eventNo = 12

	if eventName == "play_video" :
		eventNo = 13

	if eventName == "seek_video" :
		eventNo = 14

	if eventName == "speed_change_video" :
		eventNo = 15

	if eventName == "stop_video" :
		eventNo = 16

	if eventName == "hide_transcript" :
		eventNo = 17

	if eventName == "show_transcript" :
		eventNo = 18

	if eventName == "save_user_state" :
		eventNo = 19

	if eventName == "transcript_translation" :
		eventNo = 20

	if eventName == "transcript_download" :
		eventNo = 21

	# PROBLEM 45 - 65

	if eventName == "problem_check" :
		eventNo = 45

	if eventName == "problem_check_fail" :
		eventNo = 46

	if eventName == "problem_reset" :
		eventNo = 47

	if eventName == "problem_rescore" :
		eventNo = 48

	if eventName == "problem_rescore_fail" :
		eventNo = 49

	if eventName == "problem_save" :
		eventNo = 50

	if eventName == "problem_show" :
		eventNo = 51

	if eventName == "reset_problem" :
		eventNo = 52

	if eventName == "reset_problem_fail" :
		eventNo = 53

	if eventName=="save_problem_success" :
		eventNo = 54

	if eventName=="problem_graded" :
		eventNo = 55

	if eventName=="showanswer" :
		eventNo = 56


	if eventName=="save_problem_fail" :
		eventNo = 57

	if eventName=="problem_get" :
		eventNo = 58


	# NAVIGATION - 80 - 105
	if eventName=="seq_goto" :
		eventNo = 80

	if eventName=="seq_next" :
		eventNo = 81

	if eventName=="seq_prev" :
		eventNo = 82
	if eventName=="page_close" :
		eventNo = 83

	if eventName=="goto_position" :
		eventNo = 84

	if eventName=="dashboard" :
		eventNo = 85

	if eventName=="/" :
		eventNo = 86

	if eventName=="jsi18n" :
		eventNo = 87

	if eventName=="i18n" :
		eventNo = 88

	elif eventName=="howItWorks" :
		eventNo = 93

	elif eventName=="calculate" :
		eventNo = 94

	elif eventName=="logout" :
		eventNo = 92

	elif eventName=="city" :
		eventNo = 95

	elif eventName=="jump_to_discussion" :
		eventNo = 89

	elif eventName=="jump_to_vertical" :
		eventNo = 96
	elif eventName=="login" :
		eventNo = 97

	elif eventName=="progress" :
		eventNo = 90

	if eventName=="view_courses" :
		eventNo = 91

	# DISCUSSION 120 - 135
	elif eventName=="reply" :
		eventNo = 122

	elif eventName=="thread_create" :
		eventNo = 123
	elif eventName=="wiki" :
		eventNo = 175

	elif eventName == "threads" :
		eventNo = 120

	elif eventName == "users" :
		eventNo = 121

	elif eventName == "forum_searched" :
		eventNo = 129

	elif eventName=="upvote" :
		eventNo = 124

	elif eventName=="flagAbuse" :
		eventNo = 125
	elif eventName=="follow" :
		eventNo = 126

	elif eventName=="unfollow" :
		eventNo = 127

	elif eventName=="upload" :
		eventNo = 128

	# COURSE 150 - 160

	if eventName=="courseware" :
		eventNo = 150

	if eventName=="chapter" :
		eventNo = 151

	if eventName=="session" :
		eventNo = 152

	if eventName=="courses_chapter" :
		eventNo = 153

	if eventName=="courses_access" :
		eventNo = 154



	#ENROLLMENT - 255 - 265
	if eventName=="edx.course.enrollment.activated" :
		eventNo = 255

	if eventName=="edx.course.enrollment.deactivated" :
		eventNo = 256

	if eventName=="edx.course.enrollment.mode_changed" :
		eventNo = 257

	if eventName=="change_enrollment" :
		eventNo = 258

	if eventName=="register" :
		eventNo = 259

	elif eventName=="create_account" :
		eventNo = 260



	# INSTRUCTOR 195 - 205
	if eventName=="list-staff" :
		eventNo = 196

	elif eventName=="dump-graded-assignments-config" :
		eventNo = 197

	elif  eventName=="list_instructor_tasks" :
		eventNo = 195


	# ADMIN 101 - 125
	elif eventName=="admin" :
		eventNo = 290

	elif eventName=="pref_status" :
		eventNo = 291

	elif eventName=="passwordReset" :
		eventNo = 292
	elif eventName=="passwordResetConfirm" :
		eventNo = 293

	# Process Others

	# OpenAssessment 220 - 240
	if  eventName=="render_self_assessment" :
		eventNo = 220

	elif eventName=="render_submission" :
		eventNo = 221

	elif eventName=="render_leaderboard" :
		eventNo = 222

	elif eventName=="render_peer_assessment" :
		eventNo = 223

	elif eventName=="render_message" :
		eventNo = 224

	elif eventName=="render_grade" :
		eventNo = 225

	elif eventName=="render_student_training" :
		eventNo = 226

	elif eventName=="save_submission" :
		eventNo = 227

	elif eventName=="submit_feedback" :
		eventNo = 228

	elif eventName=="training_assess" :
		eventNo = 229

	elif eventName=="peer_assess" :
		eventNo = 230

	elif eventName=="submit" :
			 eventNo = 231

	elif eventName=="render_staff_info" :
		eventNo = 232

	elif   eventName=="self_assess" :
		eventNo = 233



	# TEXTBOOK interaction starts here

	if  eventName=="book" :
		eventNo = 280


	return eventNo


def extractDataFromLine(line):
	user_data=json.loads(line)
	
	user_data=_decode_dict(user_data)
	#print user_data
	#event= user_data.get("event")
	#print event.get("state")
	#print context.get("state")
	#event= user_data.get("event")
	#print event.get("POST")
	
	#print context.get('course_id')
	#for k in user_data :
	#	print k , user_data[k]
	
	
	
	#print user_data
	
	eventName=""
	eventType=""
	flag=False                   # to determine whether entry is successful or not
	usersession=classes.Usersessionoldlog()
	lmsname="EDX"
#	print user_data.get('host')
	usersession.datasource="SQL"
	usersession.eventsource=str(user_data.get('event_source'))
	usersession.hostname=str(user_data.get('host'))
	usersession.ipaddress=str(user_data.get('ip'))

	usersession.username=str(user_data.get('username'))
	usersession.sessionsysname=str(user_data.get('session'))

	usersession.lmsname=lmsname

	contextJson=user_data.get('context')
	usersession.orgname=str(contextJson.get('org_id'))
	usersession.agent=str(user_data.get('agent'))




	#print usersession.eventsource

	#course name and course run retrieval

	coursename=str(contextJson.get('course_id'))
	
	course_run=""
	if len(coursename) != 0 :
		dummylist = coursename.split("/")
		coursename =dummylist[1]
		if len(dummylist)>1 :
			course_run=dummylist[2]
	else :
		pass

	usersession.coursename=coursename
	usersession.courserun=course_run

	#print usersession.coursename
	#print usersession.courserun
	# setting user_id

	user_id=str(contextJson.get('user_id'))



	if user_id == "" :
		usersession.lmsuserid=0


	else :
		user_id.strip()
		usersession.lmsuserid=long(user_id)

	createdatetime=user_data.get("time")
	
	tmp=createdatetime.split("T")  #logdate format yyyy-MM-dd'T'HH:mm:ss
	
	#createdatetime=createdatetime[:index]+" "+createdatetime[index+3:]
	createdatetime=tmp[0]+" "+tmp[1][:6]
	usersession.createdatetime=createdatetime



	# setting data source

	usersession.datasource="LOG"


	# setting event type

	event_type_str= user_data.get('event_type')
	event_str=str(user_data.get('event'))

	#print event_type_str
	eventJson=str(user_data.get("event"))
	eventJson=json.loads(eventJson)

	event_arr=event_type_str.split("/")

	if "jump_to" in event_type_str :
		substr=event_type_str.split("/")

		if len(substr) > 1 :
			check=substr[len(substr)-2]
			usersession.eventtype="navigation"

			if check== "vertical" :
				sysid=substr[len(substr)-1]
				#print sysid
				modulesysname=sysid
				usersession.modulesysname=modulesysname

				usersession.eventname="jump_to_vertical"

				result=fetch.CourseVertical(modulesysname , usersession.coursename)

				sessionSys=""

				if (len(result) > 0 and len(result[0])>0) :
					sessionSys=result[0][0]


				result=fetch.ChapterSess(sessionSys,usersession.coursename)
				if (len(result) > 0 and len(result[0])>2) :
					usersession.chaptertitle=result[0][0]
					usersession.sesstitle=result[0][1]
					usersession.chaptersysname=result[0][2]




			if check == "discussion" :
				# line no 526
				# line no 574
				eventName="jump_to_discussion"
				usersession.eventname=eventName
				sysid= substr[-1]
				usersession.modulesysname=sysid
				result_courseDiscussions=fetch.Coursediscussions(usersession.modulesysname,usersession.coursename)
				chaptersysname=""

				if ( result_courseDiscussions!=None and len(result_courseDiscussions) > 0 ) :
					chaptersysname=str(result_courseDiscussions[0][0])
					usersession.moduletitle=result_courseDiscussions[0][1]
					usersession.chaptersysname=chaptersysname

					result_CourseChapter = fetch.Chapter(usersession.coursename,usersession.chaptersysname)



					if (len(result_CourseChapter) > 0 and len(result_CourseChapter[0]) > 0) :
						usersession.chaptertitle=result_CourseChapter[0][0]

					result_courseChapterSession=fetch.CourseChapterSess(usersession.chaptersysname,usersession.coursename)

					if (len(result_courseChapterSession) > 0 and len(result_courseChapterSession[0])>0) :
						sessionSysName=result_courseChapterSession[0][0]
						usersession.sessionsysname=sessionSysName


					result=fetch.ChapterSess(usersession.sessionsysname,usersession.coursename)

					if ((result !=None ) and len(result) > 0) :
						usersession.sesstitle=result[0][1]


	if "all_courses" in event_type_str or event_type_str=="/courses/all" or event_type_str=="/courses" or event_type_str=="/courses/":
		if "all" in event_type_str or event_type_str=="/courses" or event_type_str=="/courses/" :
			eventType="navigation"
			eventName="view_courses"

			usersession.eventtype=eventType
			usersession.eventname=eventName



			# event problem interact starts here



	if "problem" in   event_type_str or "showanswer" in event_type_str :
		eventType="problem"



		if "problem_check" in event_type_str :
			eventName="problem_check"

		elif "problem_get" in event_type_str :
			eventName="problem_get"

		elif "problem_save" in event_type_str :
			eventName="problem_save"

		elif "problem_show" in event_type_str :
			eventName="problem_show"

		elif "problem" in event_type_str and "input_ajax" in event_type_str :
			eventName="problem_get"

		else :
			eventName=event_type_str


		usersession.eventtype=eventType
		usersession.eventname=eventName


		processProblem(usersession,event_type_str,event_str,user_data)

	elif "instructor" in event_type_str:
		eventType="instructor"
		eventName="list_instructor_tasks"
		usersession.eventtype=eventType
		usersession.eventname=eventName

		 # video interaction

	elif   "video" in event_type_str or "trancript" in event_type_str or "save_user_state" in event_type_str :
		eventType="video"

		if "save_user_state" in event_type_str :
			eventName="save_user_state"

		elif "transcript/translation" in event_type_str :
			eventName="transcript_translation"

		elif "transcript/download" in event_type_str :
			eventName="transcript_download"

		else :
			eventName=event_type_str


		usersession.eventname=eventName
		usersession.eventtype=eventType

		processVideo(usersession,user_data)


			# open_assesment event



	elif "_openassessment" in event_type_str :

		eventType="_openassessment"
		eventName=""

		if  "render_self_assessment" in event_type_str :
			eventName="render_self_assessment"

		elif "render_submission" in event_type_str :
			eventName="render_submission"


		elif "handler/save_submission" in event_type_str :
			eventName="save_submission"

		elif "handler/submit_feedback" in event_type_str :
			eventName="submit_feedback"

		elif "handler/training_assess" in event_type_str :
			eventName="training_assess"

		elif "handler/submit" in event_type_str :
			eventName="submit"

		elif "render_leaderboard" in event_type_str :
			eventName="render_leaderboard"


		elif "render_peer_assessment" in event_type_str :
			eventName="render_peer_assessment"

		elif "render_message" in event_type_str :
			eventName="render_message"


		elif "render_grade" in event_type_str :
			eventName="render_grade"


		elif "peer_assess" in event_type_str :
			eventName="peer_assess"

		elif "render_student_training" in event_type_str :
			eventName="render_student_training"


		elif "render_staff_info" in event_type_str :
			eventName="render_staff_info"

		elif "self_assess" in event_type_str :
			eventName="self_assess"


		usersession.eventtype=eventType
		usersession.eventname=eventName


		sub = event_type_str.split("/")
		openSys=""

		if len(sub)>=3 :
			openSys=sub[len(sub)-3]
			subType=openSys.split(";")
			openSys1=subType[len(subType)-1]
			openSysName=openSys1[1:]
			usersession.modulesysname=openSysName


		# course interaction

	elif "seq_goto" in event_type_str or "seq_next" in event_type_str or "seq_prev" in event_type_str or "page_close" in event_type_str or "goto_position" in event_type_str:
		eventType="navigation"
		eventName=""

		if "goto_position" in event_type_str:
			eventName="goto_position"

		else :
			eventName=event_type_str


		usersession.eventname=eventName
		usersession.eventtype=eventType

		processNavig(usersession,user_data,event_str)



	elif "courses" in event_type_str:
		if "discussion/threads" in event_type_str :
			if "threads" in event_type_str :
				eventType="discussion"
				eventName="reply"

			elif "reply" in event_type_str:
				eventName="reply"

			elif "follow" in event_type_str :
				eventName="follow"

			elif "unfollow" in event_type_str:
				eventName="unfollow"


			eve=event_type_str.split("/")

			commentSysId=eve[len(eve)-2]
			usersession.commentsysid=commentSysId


			result_courseForum = fetch.CourseForums(commentSysId)

			if (len(result_courseForum) > 0 and len(result_courseForum[0]) > 0) :
				usersession.lmsname=str(result_courseForum[0][0])
				usersession.orgname = str(result_courseForum[0][1])
				usersession.coursename= str(result_courseForum[0][2])

				result_courseForum2=fetch.Comments(commentSysId,usersession.coursename)


				if (result_courseForum2 != None  and (len(result_courseForum2) > 0)) :
					usersession.commentableid=result_courseForum2[0][0]
					usersession.commenttype=result_courseForum2[0][1]


			usersession.eventname=eventName

			usersession.eventtype=eventType

		if "discussion/comments" in event_type_str or "discussion/threads" in event_type_str :
			eventType="discussion"
			eventName=""

			if "upvote" in  event_type_str :
				eventName="upvote"

			elif "flagAbuse" in event_type_str :
				eventName="flagAbuse"



			eve=event_type_str.split("/")

			commentSysId=eve[len(eve)-2]


			result_courseForum = fetch.CourseForums(commentSysId)

			if (result_courseForum!=None and len(result_courseForum) > 0) :
				if (len(result_courseForum[0]) >2) :

					usersession.lmsname=str(result_courseForum[0][0])
					usersession.orgname=str(result_courseForum[0][1])
					usersession.coursename=str(result_courseForum[0][2])

					result_courseForum2=fetch.Comments(commentSysId,usersession.coursename)

					if (len(result_courseForum2) > 0) :
						if (len(result_courseForum2[0]) > 1) :
							usersession.commentableid=result_courseForum2[0][0]
							usersession.commenttype=result_courseForum2[0][1]


			usersession.eventname=eventName
			usersession.eventtype=eventType


		if "discussion" in event_type_str :
			if "threads/create" in event_type_str :
				eventType="discussion"

				eventName="thread_create"

				usersession.eventtype=eventType
				usersession.eventname=eventName

				event_json={}
				try:
					event_json=json.loads(str(user_data.get("event")))
					event_json=_decode_dict(event_json)

				except :
					traceback.print_exc()



				getEvent=event_json.get('POST')
				
				if getEvent !=None :


					if "anonymous" in event_str :
						anonymous=str(getEvent.get('anonymous'))

						if anonymous == "[\"false\"]":
							usersession.anonymous = "N"
						elif anonymous == "[\"true\"]":
							usersession.anonymous= "Y"

					if "anonymous_to_peers" in event_str :
						anonymous_to_peers =str(getEvent.get('anonymous_to_peers'))

						if anonymous_to_peers == "[\"false\"]":
							usersession.anonymoustopeers = "N"
						elif anonymous_to_peers == "[\"true\"]":
							usersession.anonymoustopeers= "Y"

			if "upload" in event_type_str :
				eventType="discussion"
				eventName="uploads"

				usersession.eventtype=eventType
				usersession.eventname=eventName
		
		event_arr=event_type_str.split("/")

		if "pdfbook" in event_type_str  or "book" in event_type_str :

			event_json=""
			try:
				event_json=json.loads(event_str)
				event_json=_decode_dict(event_json)

			except :
				traceback.print_exc()


			getEvent=event_json.get('GET')


			eve=""

			bk1=""

			if getEvent.get("file") == None :
				pass

			else :

				eve=str(getEvent.get("file"))

				part=eve.split("/")

				bk=part[len(part)-1]
				bk1=bk[:-2]

			eventType="textbook_interaction"
			eventName="book"
			usersession.eventtype=eventType
			usersession.eventname=eventName

			usersession.othertitle=bk1

			if "pdfbook" in event_type_str:
				usersession.othertype="PDF"
			elif "book" in event_type_str :
				usersession.othertype="PNG"


		if "progress" in event_type_str:
			eventType="navigation"
			eventName="progress"
			usersession.eventtype=eventType
			usersession.eventname=eventName

		if "discussion/users" in event_type_str :
			eventType="discussion"
			eventName="view"

			usersession.eventtype=eventType
			usersession.eventname=eventName

		if "discussion/forum" in event_type_str :
			eventType="discussion"

			tmpStr=event_type_str.split("/")

			if "threads" in event_type_str :
				eventName="threads"
				modulesysname=""

				try :
					modulesysname=tmpStr[len(tmpStr)-3]
				except :
					traceback.print_exc()

				usersession.modulesysname=modulesysname

				#print (modulesysname, usersession.coursename)
				result_courseDiscussions=fetch.Discuss(usersession.modulesysname,usersession.coursename)

				#print result_courseDiscussions
				
				#if (len(result_courseDiscussions)>0) :
				
				if (result_courseDiscussions!=None and len(result_courseDiscussions)>0) :
					usersession.moduletitle=result_courseDiscussions[0]


			else :
				eventName="users"


			usersession.eventname=eventName
			usersession.eventtype=eventType

		elif "courseware" in event_type_str :
			eventType="courses"
			usersession.eventtype=eventType

			processCourses(usersession,event_type_str)
		
		
		
		
		elif event_arr[len(event_arr)-1] == "progress" :
			eventType="navigation"
			eventName="progress"

			usersession.eventname=eventName
			usersession.eventtype=eventType

		elif "wiki" in event_type_str :
			eventType="wiki"
			eventName="wiki"
			usersession.eventname=eventName
			usersession.eventtype=eventType

		else :
			if "about" in event_type_str :
				eventType="courses"
				eventName="about"
				usersession.eventname=eventName
				usersession.eventtype=eventType
				usersession.chaptertitle="about"

			elif "info" in event_type_str :
				eventType="courses"
				eventName="info"
				usersession.eventname=eventName
				usersession.eventtype=eventType
				usersession.chaptertitle="info"

			else :
				processCourses(usersession,event_type_str)

	else :
		processOthers(usersession,user_data)

	if len(str(usersession.eventtype)) >0  :
		eventNumber=convertEventToNumber(usersession.eventname)
		usersession.eventno=eventNumber
		if(isinstance(usersession.answerchoice,list)):
			usersession.answerchoice=usersession.answerchoice[0]
		else :
			usersession.answerchoice=usersession.answerchoice[:8]
			
		if isinstance(usersession.noofresults,tuple):
			usersession.noofresults=usersession.noofresults[1]
			
			
		#print usersession.noofresults
		
		if isinstance(usersession.lmsuserid,tuple):
			usersession.lmsuserid=usersession.lmsuserid[0]
		
		if("']" in usersession.answerchoice):
			usersession.answerchoice=usersession.answerchoice[:usersession.find("']")]
		if("['" in usersession.answerchoice):
			usersession.answerchoice=usersession.answerchoice[usersession.find("['")+1:]
		
		

		usersession.save()
		flag=True
		#print usersession.username
		#print usersession.eventtype
		#print usersession.agent


	else :
		flag=False



	return flag



def extractDataFromFile(file_path):
	with open(file_path) as f:
		for line in f:
			try :
				extractDataFromLine(line)
			except :
				pass

	return 


def extractDataFromFileList(source_folder,file_list):

	for file in file_list :
		file_path=source_folder+"/"+file
		extractDataFromFile(file_path)
		

	return 

def file_reader():
	source_folder="/home/virus/LMS-2"

	filelist=os.listdir(source_folder)

	extractDataFromFileList(source_folder,filelist)
	
	
if __name__ == '__main__':
	file_reader()


