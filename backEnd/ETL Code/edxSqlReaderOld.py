'''
Created on 24-Jun-2015

@author: sagar, ashwani
'''
import MySQLdb
import exceptions
import traceback
import sys
import json

import classes
import dataloader
import sqlFetch

import datetime
import os

#from datetime import datetime.strftime as strftime


class Sqlreaderold(object):

	def __init__(self):
		self.serialVersionUID = 1260045197860645956L;
		self.lmsName="IITEDX";
		
		#private StudentCourseEnrolmentDao studentCourseEnrolmentDao = null;
		#private StudentCourseEnrolment studentCourseEnrolment = new StudentCourseEnrolment();
		#private StateDao stateDao = null;
		#private States state = new States();
		
		#private CityDao cityDao = null;                    #not yet used
		#private SimpleDateFormat mongoDateFormat;          #not yet used
		
		#private EventCourseInteractDao eventCourseInteractDao = null;()
		#private EventProbInteractDao eventProbInteractDao = null;
		#private EventVideoInteractDao eventVideoInteractDao = null;
		#private UserSessionDao userSessionDao = null;

		self.studentCourseEnrolment=classes.Studentcourseenrolment()
		self.state=classes.States()

		
		self.eventId = -1L;
		
		#private EventProbInteract eventProbInteract = new EventProbInteract();
		#private EventVideoInteract eventVideoInteract = new EventVideoInteract();
		#private EventCourseInteract eventCourseInteract = new EventCourseInteract();
		#private UserSession userSession = new UserSession();
		self.eventProbInteract = classes.Eventprobinteract()
		self.eventVideoInteract = classes.Eventvideointeract()
        	self.eventCourseInteract = classes.Eventcourseinteract()
        	self.userSession = classes.Usersession()
        	self.moduleType=""
        	self.moduleId=""
        	self.stateStr=""
       	 	self.key=""

        	self.inputkeys=[]
        	self.currSeekTime=0.0

        	#private PreparedStatement psVideo=null;
        	#private ResultSet rsVideo = null;
        	self.evnt_Load_Video=0
        	self.evnt_Pause_Video=0
        	self.evnt_Play_Video=0
        	self.evnt_Seek_Video=0

        	self.evnt_Speed_Change_Video=0
        	self.evnt_Stop_Video=0
        	self.evnt_Hide_Transcript=0
		
		self.evnt_Show_Transcript=0
		self.evnt_Save_User_State=0
		self.evnt_Transcript_Translation=0
		self.evnt_Transcript_Download=0

		self.evnt_Save_Video_Position=0

		self.evnt_Problem_Check=0
		self.evnt_Problem_Check_Fail=0
		self.evnt_Problem_Reset=0
		self.evnt_Problem_Rescore=0

		self.evnt_Problem_Rescore_Fail=0
		self.evnt_Problem_Save=0
		self.evnt_Problem_Show=0
		self.evnt_Reset_Problem=0

		self.evnt_Reset_Problem_Fail=0
		self.evnt_Save_Problem_Success=0
		self.evnt_Problem_Graded=0

		self.evnt_Showanswer=0
		self.evnt_Save_Problem_Fail=0
		self.evnt_Problem_Get=0

		self.evnt_Seq_Goto=0
		self.evnt_Seq_Next=0
		self.evnt_Seq_Prev=0
		self.evnt_Page_Close=0
		self.evnt_Goto_Position=0

		self.evnt_Dashboard=0
		self.evnt_Root=0
		self.evnt_Jsi18n=0
		self.evnt_I18n_Js=0

		self.evnt_Courseware=0
		self.evnt_Chapter=0
		self.evnt_Session=0

		self.evnt_Threads=0
		self.evnt_Users=0

		self.evnt_List_Staff=0
		self.evnt_Dump_Graded_Assignments_Config=0
		self.evnt_Course_Enrollment_Activated=0

		self.evnt_Course_Enrollment_Mode_Changed=0
		self.evnt_Change_Enrollment=0
		self.evnt_Register=0

		self.evnt_Create_Account=0
		self.evnt_Logout=0
		self.evnt_Admin=0

		self.tmpStrArr=[]

		self.keySet = []
		self.keySet2 = []
		self.recNo = 0
		
		self.correctMapJson = None 
		self.studentAnswerJSon = None 
		self.correctMapKeyValJSon = None
		self.stateJson = None
		
		#private JSONParser jparse = new JSONParser();		
		
		self.city = classes.Cities()
		self.user = classes.User()
		
		self.currEventNo = 0;
		
		self.answers = ""
		
		#private Connection connIIT = null;
		#private Connection connEDX = null;
		
		self.stmt= None
		
		#private SimpleDateFormat dsf = new SimpleDateFormat("HH:mm:ss");
		self.dsf="{%H:%M:%S}"
		self.tmpDate = ""

		self.properties={}
        	self.propertyFile="EdxParams.properties"



	def parse_line(self,inp):
        	key, value = inp.split('=')
        	key = key.strip()  # handles key = value as well as key=value
        	value = value.strip()
        	return key, value
       
    	def loadProperty(self):
        	with open(self.propertyFile) as fp:
            		for line in fp:
                		inp = line.strip()
                		if not line or line.startswith('#'):
                    			continue

            			key, value = self.parse_line(inp)
            			self.properties[key] = value
            
        	self.birthDatefrmt=self.properties.get("BIRTHDATEFORMAT")
        	self.mongoDateFormat=self.properties.get("MONGODATEFORMAT")
        	#self.evnt_Save_Video_Position=self.properties.get("EVNT_SAVE_VIDEO_POSITION")


    	def sqlConnect(self) :
        	self.db=MySQLdb.connect("localhost","root","1234","edxapp")
        	self.cursor=self.db.cursor()
        
    	def fun(self):
        	
        	sqlStmt = "select  a.id, a.username, a.email, a.is_active, a.last_login, a.date_joined,  \
				p.language, p.gender, p.year_of_birth, p.level_of_education, p.goals,p.allow_certificate \
				,s.name, c.name, m.pincode, m.aadhar_id \
				from auth_user a join auth_userprofile p on a.id = p.user_id join student_mooc_person m  \
				on a.id = m.user_id join student_mooc_city c on m.city_id = c.id  \
				join student_mooc_state s on m.state_id = s.id"
		
		sqlEnrol = "SELECT user_id,course_id,created,is_active,mode FROM student_courseenrollment"
		sqlCity = "SELECT name, state_id FROM student_mooc_city"
		sqlState = "SELECT name  FROM student_mooc_state"

		try :

            		self.cursor.execute(sqlStmt)
            		result=self.cursor.fetchall()
            		#System.out.println("RECORD NO : rs executed" + recNo)
            
            		for rs in result :
                		self.currEventNo = 0
				self.user.lmsuserid=long(rs[0])
				self.user.name=str(rs[1])
				self.user.emailid=str(rs[2])
				self.user.active=int(rs[3])
								
				#doubt
				#self.user.lastaccessdate=datetime.time(rs[4])
				#self.user.registrationdate=datetime.date(rs[5])

				#doubt cleared
				self.user.lastaccessdate=rs[4].time()
				self.user.registrationdate=rs[5].time()
				
				self.user.mothertounge=str(rs[6])
				self.user.gender=str(rs[7])

				if rs[8]!=None:
					self.user.yearofbirth=int(rs[8])
				
				self.user.highestedudegree=str(rs[9])
				self.user.goals=str(rs[10])

				self.user.allowcert=int(rs[11])
				self.user.state=str(rs[12])
				self.user.city=str(rs[13])
				
				if( rs[14] != None):
					self.user.pincode=int( str(rs[14]) )
				if( rs[15] != None):
					self.user.aadharid=int( str(rs[15]) )
				
				self.user.lmsname="IITEDX"
				
				self.user.save()
			


			self.cursor.execute(sqlEnrol)
           		result=self.cursor.fetchall()
			
			tmpStrArr=[]

			for rs in result:
				
				self.studentCourseEnrolment.lmsuserid=long(rs[0])
				tmpStrArr = str(rs[1]).split("/")
				self.studentCourseEnrolment.coursename=tmpStrArr[1]
				self.studentCourseEnrolment.courserun=tmpStrArr[2]

				self.studentCourseEnrolment.enrolmentdate=rs[2].time()

				print rs[3]
				self.studentCourseEnrolment.active=int(rs[3])
				self.studentCourseEnrolment.mode=str(rs[4])
				self.studentCourseEnrolment.lmsname="EDX"
				
				self.studentCourseEnrolment.save()
				#studentCourseEnrolmentDao.insertRec(studentCourseEnrolment);
		
			print 'yahoo'
			self.cursor.execute(sqlCity)
            		result=self.cursor.fetchall()
			
			for rs in result:
			
				self.city.name=str(rs[0])
				self.city.stateid=str(rs[1])
				self.city.save()
				#cityDao.insertRec(city);
			

			self.cursor.execute(sqlState)
            		result=self.cursor.fetchall()
			
			#rs = stmt.executeQuery(sqlState);
			for rs in result:
			
				self.state.name=str(rs[0])
				self.state.save()
			
		except Exception, e:
			print("Exception " + str(e) )

		#catch (SQLException e)
		#{
		#	// TODO Auto-generated catch block
		#	e.printStackTrace();
		#} 
		
	def getEventNos(self):
	
		self.evnt_Load_Video = int(self.properties.get("EVNT_LOAD_VIDEO"))
		self.evnt_Pause_Video=int(self.properties.get("EVNT_PAUSE_VIDEO"))
		self.evnt_Play_Video=int(self.properties.get("EVNT_PLAY_VIDEO"))
		self.evnt_Seek_Video =int(self.properties.get("EVNT_SEEK_VIDEO"))
		self.evnt_Speed_Change_Video=int(self.properties.get("EVNT_SPEED_CHANGE_VIDEO"))
		self.evnt_Stop_Video=int(self.properties.get("EVNT_STOP_VIDEO"))
		self.evnt_Hide_Transcript=int(self.properties.get("EVNT_HIDE_TRANSCRIPT"))
		self.evnt_Show_Transcript=int(self.properties.get("EVNT_SHOW_TRANSCRIPT"))
		self.evnt_Save_User_State=int(self.properties.get("EVNT_SAVE_USER_STATE"))
		self.evnt_Transcript_Translation=int(self.properties.get("EVNT_TRANSCRIPT_TRANSLATION"))
		self.evnt_Transcript_Download=int(self.properties.get("EVNT_TRANSCRIPT_DOWNLOAD"))
		self.evnt_Save_Video_Position = int(self.properties.get("EVNT_SAVE_VIDEO_POSITION"))
		 
		self.evnt_Problem_Check= int(self.properties.get("EVNT_PROBLEM_CHECK"))
		self.evnt_Problem_Check_Fail=int(self.properties.get("EVNT_PROBLEM_CHECK_FAIL"))
		self.evnt_Problem_Reset=int(self.properties.get("EVNT_PROBLEM_RESET"))
		self.evnt_Problem_Rescore =int(self.properties.get("EVNT_PROBLEM_RESCORE"))
		self.evnt_Problem_Rescore_Fail=int(self.properties.get("EVNT_PROBLEM_RESCORE_FAIL"))
		self.evnt_Problem_Save=int(self.properties.get("EVNT_PROBLEM_SAVE"))
		self.evnt_Problem_Show=int(self.properties.get("EVNT_PROBLEM_SHOW"))
		self.evnt_Reset_Problem =int(self.properties.get("EVNT_RESET_PROBLEM"))
		self.evnt_Reset_Problem_Fail=int(self.properties.get("EVNT_RESET_PROBLEM_FAIL"))
		self.evnt_Save_Problem_Success=int(self.properties.get("EVNT_SAVE_PROBLEM_SUCCESS"))
		self.evnt_Problem_Graded =int(self.properties.get("EVNT_PROBLEM_GRADED"))
		self.evnt_Showanswer=int(self.properties.get("EVNT_SHOWANSWER"))
		self.evnt_Save_Problem_Fail=int(self.properties.get("EVNT_SAVE_PROBLEM_FAIL"))
		self.evnt_Problem_Get =int(self.properties.get("EVNT_PROBLEM_GET"))

		self.evnt_Seq_Goto=int(self.properties.get("EVNT_SEQ_GOTO"))
		self.evnt_Seq_Next=int(self.properties.get("EVNT_SEQ_NEXT"))
		self.evnt_Seq_Prev=int(self.properties.get("EVNT_SEQ_PREV"))
		self.evnt_Page_Close=int(self.properties.get("EVNT_PAGE_CLOSE"))
		self.evnt_Goto_Position=int(self.properties.get("EVNT_GOTO_POSITION"))
		self.evnt_Dashboard=int(self.properties.get("EVNT_DASHBOARD"))
		self.evnt_Root=	int(self.properties.get("EVNT_ROOT"))
		self.evnt_Jsi18n=int(self.properties.get("EVNT_JSI18N"))
		self.evnt_I18n_Js =int(self.properties.get("EVNT_I18N_JS"))

		self.evnt_Courseware=int(self.properties.get("EVNT_COURSEWARE"))
		self.evnt_Chapter=int(self.properties.get("EVNT_CHAPTER"))
		self.evnt_Session=int(self.properties.get("EVNT_SESSION"))

		self.evnt_Threads=int(self.properties.get("EVNT_THREADS"))
		self.evnt_Users=int(self.properties.get("EVNT_USERS"))

		self.evnt_List_Staff=int(self.properties.get("EVNT_LIST-STAFF"))
		self.evnt_Dump_Graded_Assignments_Config=int(self.properties.get("EVNT_DUMP-GRADED-ASSIGNMENTS-CONFIG"))
		self.evnt_Course_Enrollment_Activated=int(self.properties.get("EVNT_EDX.COURSE.ENROLLMENT.ACTIVATED"))
		self.evnt_Course_Enrollment_Mode_Changed=int(self.properties.get("EVNT_EDX.COURSE.ENROLLMENT.MODE_CHANGED"))
		self.evnt_Change_Enrollment=int(self.properties.get("EVNT_CHANGE_ENROLLMENT"))
		self.evnt_Register=int(self.properties.get("EVNT_REGISTER"))
		self.evnt_Create_Account=int(self.properties.get("EVNT_CREATE_ACCOUNT"))
		self.evnt_Logout=int(self.properties.get("EVNT_LOGOUT"))
		self.evnt_Admin=int(self.properties.get("EVNT_ADMIN"))



if __name__=="__main__":
    reader=Sqlreaderold()
    reader.loadProperty()
    reader.sqlConnect()

    reader.fun()
