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


class Sqlreader(object):
    
    #code
    def __init__(self):
        self.serialVersionUID = 1260045197860645956L
        self.lmsName = "IITEDX"
        self.db=None
        self.cursor=None
        self.studentCourseEnrolment = classes.Studentcourseenrolment
        self.stateDao = None
        self.state = classes.States()
        self.mongoDateFormat=""
        #// private EventCourseInteractDao eventCourseInteractDao = null
        #// private EventProbInteractDao eventProbInteractDao = null
        #// private EventVideoInteractDao eventVideoInteractDao = null
        #private UserSessionOldDao userSessionDao = null
        self.eventId = -1
        self.eventProbInteract = classes.Eventprobinteract()
        self.answers = ''
        self.eventVideoInteract = classes.Eventvideointeract()
        self.eventCourseInteract = classes.Eventcourseinteract()
        self.userSession = classes.Usersessionold()
        self.moduleType=""
        self.moduleId = ""
        self.stateStr = ""
        self.key = ""
        self.inputkeys=[]
        self.currSeekTime=0.0

        self.city = classes.Cities()
        
        self.tmpStrArr=[]
        self.keySet=[]
        self.evnt_Save_Video_Position=0
        self.recNo=0
        self.firstFlag=0
        self.user=classes.User()
        self.currEventNo = 0
        self.birthDatefrmt = ""
        self.dsf="{%H:%M:%S}"
        self.tmpDate=""
        self.getLastId = None
        self.itr1=None
        self.itr2=None
        self.properties={}
        self.propertyFile="EdxParams.properties"
        self.correctMapJson =None
        self.studentAnswerJson = None
        self.correctMapKeyValJson = None

        '''private PreparedStatement psVideo = null, psVideoInteract = null,
        psChapterSess = null
        private PreparedStatement psProblem = null, psChapter = null,
        psDiscuss = null
        private PreparedStatement psCourseInteract = null, psProbInteract = null,
        psUserSession = null
        private ResultSet rsVideo = null, rsTmp
        private String sqlStmt = null

        private Properties properties = new Properties()

        
        private JSONObject correctMapJson = null, studentAnswerJSon = null,
                correctMapKeyValJSon = null
        // private JSONObject inputJson=null
        private JSONObject stateJson = null
        private JSONParser jparse = new JSONParser() 
        
        
        
        
        
        // StudentCourseGrades studentCourseGrades = new StudentCourseGrades()
        // StudentCourseGradeDao studentCourseGradeDao = new
        // StudentCourseGradeDao()
        
    
        private Connection connIIT = null
        private Connection connEDX = null
        private Statement stmt = null'''

    def parse_line(self,inp):
        key, value = inp.split('=')
        key = key.strip()  # handles key = value as well as key=value
        value = value.strip()
        return key, value
       
    def loadProperty(self):
        #filename = "EdxParams.properties"
        
        with open(self.propertyFile) as fp:
            for line in fp:
                inp = line.strip()
                if not line or line.startswith('#'):
                    continue

            key, value = self.parse_line(inp)
            self.properties[key] = value
            
        self.birthDatefrmt=self.properties.get("BIRTHDATEFORMAT")
        self.mongoDateFormat=self.properties.get("MONGODATEFORMAT")
        self.evnt_Save_Video_Position=self.properties.get("EVNT_SAVE_VIDEO_POSITION")
        
    def sqlConnect(self) :
        self.db=MySQLdb.connect("localhost","root","1234","edxapp")
        self.cursor=self.db.cursor()
        
    def fun(self):
        '''#userSessionDao = new UserSessionOldDao(connIIT)
            System.out.println("BEFORE PREP STA")
            
        
           
            psVideoInteract = connIIT
            .prepareStatement("insert into EventVideoInteract( sessionSysName, lmsName, orgName, courseName, courseRun, lmsUserId,"
            + "eventName,eventNo, videoSysName, videoTitle, chapterSysName,chapterTitle, oldSeekTime, currSeekTime, videoNavigType, "
            + " oldSpeed, currSpeed, source,createDateTime, lastModDateTime) "
            + " values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)")

            psCourseInteract = connIIT
            .prepareStatement("insert into EventCourseInteract ( lmsName, orgName, courseName, courseRun, lmsUserId,"
            + "eventName,eventNo,  moduleType, moduleSysName,moduleTitle,chapterSysName,chapterTitle,"
            + "createDateTime, modDateTime, oldPosition,  curPosition,  source) "
            + " values (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?)")
            psProbInteract = connIIT
            .prepareStatement("insert into EventProbInteract(    lmsName,orgName, courseName,lmsUserId,"
            + "eventName,eventNo,quizzSysName,quizzTitle,chapterSysName,chapterTitle,hintAvailable,hintMode,inputType,responseType,"
            + "variantId,oldScore,newScore,maxGrade,attempts,maxAttempts,choice, success, "
            + "source, probSubTime,done,createDateTime,lastModDateTime,courseRun) "
            + " values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? , ?,?,?)")
            psUserSession = connIIT
            .prepareStatement("insert into UserSession (sessionSysName, lmsName, orgName, courseName,courseRun, lmsUserId, userName,"
            + "agent,    hostName, ipAddress, url, createDateTime, eventType, eventSource, eventName,"
            + "eventId, lastModDateTime,dataSource,eventNo) values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,  ?,?,?,?)")
            System.out.println(psCourseInteract)
            stmt = connEDX.createStatement()
            } catch (IOException e1) {
            // TODO Auto-generated catch block
            System.out.println("I/O Exception " + e1.toString())
            } catch (SQLException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace()
            System.out.println("SQL Exception " + e.toString())}
            // Query edx tables courseware_studentmodule, Create all Event Tables
            // table of LMS
            // /******** End Of Insert into User Table of LMS'''
            
        sqlStmt = "select module_type,module_id,student_id,state,grade,created,modified,max_grade,\
                      done, course_id,userName from courseware_studentmodule s join auth_user u \
                      on s.student_id = u.id"
            #// Get userName from auth_user'''
            #System.out.println("sqlSTmt " + sqlStmt)

        try :
            self.cursor.execute(sqlStmt)
            result=self.cursor.fetchall()
            #System.out.println("RECORD NO : rs executed" + recNo)

            for rs in result :
                self.currEventNo = 0
                self.userSession.datasource="SQL"
                self.userSession.lmsname="EDX"
                self.recNo+=1
                #System.out.println("RECORD NO : " + recNo)
                self.moduleType = rs[0]
                #System.out.println("MODULE TYPE : " + moduleType)
                self.moduleId = rs[1]
                self.userSession.lmsuserid=long(rs[2])
                
                # doubt format of stateStr
                stateStr = rs[3]
                self.stateJson = json.loads(stateStr)     #doubt
                
                self.stateJson=dataloader._decode_dict(self.stateJson)
                self.userSession.createdatetime=rs[5]
                self.userSession.lastmoddatetime=rs[6]
                self.userSession.username=rs[10]
                self.tmpStrArr = rs[9].split("/")
                self.userSession.orgname=self.tmpStrArr[0]
                self.userSession.coursename=self.tmpStrArr[1]
                self.userSession.courserun=self.tmpStrArr[2]
                
                print self.moduleType, self.stateJson.get("last_submission_time")
                if ((self.moduleType == "chapter") or (self.moduleType == "course" ) or (self.moduleType == "sequential" )) :
                    #System.out.println("moduleType is course")
                    self.userSession.eventtype="course"
                    self.eventCourseInteract.lmsname=self.lmsName
                    self.eventCourseInteract.orgname=self.userSession.orgname
                    self.eventCourseInteract.coursename=self.userSession.coursename
                    self.eventCourseInteract.lmsuserid=self.userSession.lmsuserid
                    self.eventCourseInteract.eventname=self.moduleType
                    self.userSession.eventname=self.moduleType
                    self.eventCourseInteract.moduletype=self.moduleType
                    self.eventCourseInteract.modulesysname=self.moduleId[self.moduleId.rfind("/") + 1:]
                    
                    print self.eventCourseInteract.modulesysname
                    
                    self.userSession.modulesysname=self.eventCourseInteract.modulesysname
                    if self.properties.get("EVNT_GOTO_POSITION")!=None:
                        self.currEventNo = int(self.properties.get("EVNT_GOTO_POSITION"))

                    if (self.moduleType == "chapter") : 
                        self.eventCourseInteract.chaptersysname=self.eventCourseInteract.modulesysname
                        rsTmp=sqlFetch.psChapter(self.userSession.coursename,self.eventCourseInteract.modulesysname)
                        
                        if(rsTmp!=None):
                            self.eventCourseInteract.chaptertitle=rsTmp[0]
                            self.userSession.chaptertitle=rsTmp[0]
                         
                    elif (self.moduleType=="session"): 
                    
                            self.eventCourseInteract.chaptersysname=self.eventCourseInteract.modulesysname
                            self.userSession.chaptersysname=self.eventCourseInteract.chaptersysname
                            rsTmp = psChapterSess(self.userSession.coursename,self.eventCourseInteract.modulesysname)
                            
                            if (rsTmp!=None): 
                            
                                chapTitle = rsTmp[0]
                                moduleTitle = rsTmp[1]
                                chapSysName = rsTmp[2]
                                self.eventCourseInteract.chaptertitle=chapTitle
                                self.eventCourseInteract.moduletitle=moduleTitle
                                self.eventCourseInteract.chaptersysname=chapSysName
                                
                                self.userSession.chaptertitle=chapTitle
                                self.userSession.moduletitle=moduleTitle
                                self.userSession.chapterSysName=chapSysName
    
                            
                     
                    elif self.moduleType == "course": 
                    
                            chapTitle = self.userSession.coursename
                            moduleTitle = self.userSession.coursename
                            chapSysName = self.eventCourseInteract.modulesysname
    
                            self.eventCourseInteract.chaptersysname=self.eventCourseInteract.modulesysname
                            self.eventCourseInteract.chaptertitle=self.userSession.coursename
                            self.eventCourseInteract.moduletitle=self.userSession.coursename
    
                            self.userSession.chaptertitle=chapTitle
                            self.userSession.moduletitle=moduleTitle
                            self.userSession.chaptersysname=chapSysName                    
                    
                    self.eventCourseInteract.eventno=self.currEventNo
                    self.userSession.eventno=self.currEventNo

                    self.eventCourseInteract.curposition=int( str(self.stateJson.get("position") ))
                    self.userSession.currposition=int( str(self.stateJson.get("position")) )
                    self.eventCourseInteract.source="SQL"
                    self.userSession.eventsource="SQL"
                        
                    self.eventCourseInteract.courserun=self.userSession.courserun
                    self.eventCourseInteract.createdatetime=self.userSession.createdatetime
                    self.eventCourseInteract.moddatetime=self.userSession.lastmoddatetime
                            
                    #self.insertCourse()
                    self.eventCourseInteract.save()

                elif self.moduleType=="openassessment": 
                
                    self.userSession.eventtype="openassess"
                
                elif self.moduleType=="problem": 
                
                    #System.out.println("moduleType is problem")
                    self.userSession.eventtype="problem"
                    self.userSession.eventname="Quizz"
                    
                    self.eventProbInteract.done=rs[8]
                    self.userSession.done=rs[8]
                    self.eventProbInteract.lmsname=self.lmsName
                    self.eventProbInteract.orgname=self.userSession.orgname
                    self.eventProbInteract.coursename=self.userSession.coursename
                        
                    self.eventProbInteract.lmsuserid=self.userSession.lmsuserid
                    self.eventProbInteract.eventname="problem"
                    self.eventProbInteract.quizzsysname=self.moduleId[ self.moduleId.rfind("/") + 1 ]
                    
                    self.userSession.modulesysname=self.eventProbInteract.quizzsysname

                    rsTmp = sqlFetch.psProblem( self.userSession.coursename, self.eventProbInteract.quizzsysname )
                    
                    if (rsTmp!=None): 
                    
                        self.eventProbInteract.chaptersysname=rsTmp[0]
                        self.eventProbInteract.chaptertitle=rsTmp[1]
                        self.eventProbInteract.quizztitle=rsTmp[2]
                        
                        self.userSession.chaptersysname=rsTmp[0]
                        self.userSession.chaptertitle=rsTmp[1]
                        self.userSession.moduletitle=self.eventProbInteract.quizztitle

                    
                    self.eventProbInteract.maxgrade=rs[7]
                    self.eventProbInteract.newscore=rs[4]
                
                    self.eventProbInteract.source="SQL"
                    self.eventProbInteract.courserun=self.userSession.courserun
                    
                    self.eventProbInteract.createdatetime=self.userSession.createdatetime
                    self.eventProbInteract.lastmoddatetime=self.userSession.lastmoddatetime
                        
                    if rs[7]!=None:
                        self.userSession.maxgrade=float(rs[7])
                    if rs[4]!=None:
                        self.userSession.currgrade=float(rs[4])
                    
                    self.userSession.eventsource="SQL"

                    self.studentAnswerJson = json.dumps( self.stateJson.get("student_answers") )
                    self.correctMapJson = json.dumps( self.stateJson.get("correct_map") )

                    
                    if (self.stateJson.get("last_submission_time") != None): 
                    
                        #self.eventProbInteract.probsubtime=new java.sql.Timestamp( mongoDateFormat.parse( stateJson.get("last_submission_time").toString()).getTime()))
                        #no idea about the format of the string
                        #changed mongoDateFormat in EdxParams.Properties 
                        
                        print  'i am here',self.stateJson.get("last_submission_time")
                        #self.eventProbInteract.probsubtime= datetime.datetime.strftime( self.mongoDateFormat, self.stateJson.get("last_submission_time") )
                        #self.eventProbInteract.probsubtime=datetime.datetime.fromtimestamp( self.stateJson.get("last_submission_time") ).strftime('%Y-%m-%d %H:%M:%S')
                        self.eventProbInteract.probsubtime=datetime.datetime.strptime(self.stateJson.get("last_submission_time"),'%Y-%m-%dT%H:%M:%SZ')

                        self.userSession.probsubmissiontime=self.eventProbInteract.probsubtime
                    
                
                    if (self.stateJson.get("attempts") != None): 

                        self.eventProbInteract.attempts=int( str(self.stateJson.get("attempts")) ) 
                        self.userSession.attempts=self.eventProbInteract.attempts
                    
                
                    if (self.stateJson.get("done") == None): 
                    
                        self.eventProbInteract.done=None
                        self.userSession.done=None
                     
             
                    elif ( str(self.stateJson.get("done") )=="false"): 
                    
                        self.eventProbInteract.done="N"
                        self.userSession.done="N"
                     
             
                    elif str(self.stateJson.get("done") )=="true": 
                    
                        self.eventProbInteract.done="Y"
                        self.userSession.done="Y"
                    
                        
                    if self.correctMapJson != 'null': 
                
                        mydecoder=json.JSONDecoder()
                        
                        #self.keySet = self.correctMapJson.keys()
                        self.correctMapJson=mydecoder.decode(self.correctMapJson)
                        self.keySet = self.correctMapJson.keys()

                        if len(self.keySet)!=0: 
                        
                            
                            for itr in self.keySet: 
                            
                                self.correctMapKeyValJson = json.dumps( self.correctMapJson.get(itr) )
                                self.correctMapKeyValJson=mydecoder.decode(self.correctMapKeyValJson)
                                
                                if (self.correctMapKeyValJson != None) :
                                
                                    if (self.correctMapKeyValJson.get("correctness") != None): 
                                    
                                        if str(self.correctMapKeyValJson.get("correctness"))=="correct": 
                                        
                                            self.eventProbInteract.success="Y"
                                            self.userSession.success="Y"
                                         
                                        else: 
                                        
                                            self.eventProbInteract.success="N"
                                            self.userSession.success="N"
                                        
                                        if (self.correctMapKeyValJson.get("hint") != None): 
                                        
                                            self.eventProbInteract.hintavailable=str(self.correctMapKeyValJson.get("hint"))
                                        
                                            self.userSession.hintavailable=self.eventProbInteract.hintavailable
                                        
                                        
                                        if (self.correctMapKeyValJson.get("hintmode") != None): 
                                        
                                            self.eventProbInteract.hintmode=str( self.correctMapKeyValJson.get("hintmode") )
                                                            
                                            self.userSession.hintmode=self.eventProbInteract.hintmode
                    

                    if (self.studentAnswerJson != 'null'): 
                        
                        mydecoder=json.JSONDecoder()
                            
                        #self.keySet = self.studentAnswerJson.keys()
                        self.studentAnswerJson=mydecoder.decode(self.studentAnswerJson)
                        self.keySet = self.studentAnswerJson.keys()

                        if len(self.keySet)!=0: 
                        
                            self.firstFlag = True
                            self.answers= self.answers[ len(self.answers): ]
                                
                            for itr in self.keySet:
                            
                                if (self.firstFlag): 
                                
                                    self.answers.append( str(self.studentAnswerJson.get(itr)) )
                                    self.firstFlag = False
                                 
                                else:
                                
                                    self.answers.append(",")
                                    self.answers.append( str(self.studentAnswerJson.get(itr)) )
                                
                            
                            print("answers " + self.answers)
                            self.eventProbInteract.choice=str(self.answers)
                            self.userSession.answerchoice=self.eventProbInteract.choice
                        
                        else:
                            print("studentAnswerJSon.get(key) is empty ")
                        
                        if self.stateJson.get("done")== None or self.stateJson.get("done")=="false": 
                                                    
                            self.currEventNo = int(self.properties.get("EVNT_PROBLEM_SAVE"))
                            self.eventProbInteract.eventno=self.currEventNo
                            self.userSession.eventno=self.currEventNo

                         
                        elif str(self.stateJson.get("done"))=="true": 
                        
                            self.currEventNo = int(self.properties.get("EVNT_SAVE_PROBLEM_SUCCESS"))
                            self.eventProbInteract.eventno=self.currEventNo
                            self.userSession.eventno=self.currEventNo
                        
                    else: 
                        if self.properties.get("EVNT_PROBLEM_GET")!=None:
                            self.currEventNo = int(self.properties.get("EVNT_PROBLEM_GET"))
                        self.eventProbInteract.eventno=self.currEventNo
                        self.userSession.eventno=self.currEventNo
                    
                    print("Evemt No " + str(self.currEventNo) + " stateJson.get(done) " + str(self.stateJson.get("done")) )
                    #self.insertProbRec()
                    self.eventProbInteract.save()
                    
                    
                elif self.moduleType=="video": 
                    
                    self.userSession.eventtype="video"
                    self.eventVideoInteract.eventname="video"

                    if (self.stateJson.get("saved_video_position") != None): 
                    
                        self.currEventNo = self.evnt_Save_Video_Position
                        self.userSession.eventname="saved_video_position"
                        self.userSession.eventno=self.currEventNo
                        self.eventVideoInteract.eventno=self.currEventNo
                        
                        #doubt
                        #tmpDate = dsf.parse(stateJson.get("saved_video_position").toString())
                        self.tmpDate=  datetime.datetime.strptime(str(self.stateJson.get('saved_video_position')), "%Y-%m-%d %H:%M:%S")

                        self.eventVideoInteract.currseektime=float( str(self.tmpDate.hour*3600 + self.tmpDate.minute*600+ self.tmpDate.second)) 
                        #doubt
                        self.userSession.currentseektime=self.eventVideoInteract.currseektime
                    
                    
                    if (self.stateJson.get("speed") != None): 
                    
                        self.eventVideoInteract.currspeed=float( str(self.stateJson.get("speed")) )
                        self.userSession.currvideospeed=self.eventVideoInteract.currspeed
                    
                    self.eventVideoInteract.lmsname=self.lmsName
                    self.eventVideoInteract.orgname=self.userSession.orgname
                    self.eventVideoInteract.coursename=self.userSession.coursename
                    
                    self.eventVideoInteract.lmsuserid=self.userSession.lmsuserid
                    
                    self.eventVideoInteract.eventname=self.moduleType
                    self.eventVideoInteract.videosysname=self.moduleId[ self.moduleId.rfind("/") + 1 ]
                    self.userSession.eventname=self.moduleType
                    
                    self.userSession.modulesysname=self.eventVideoInteract.videosysname
                    rsVideo = psVideo( self.userSession.coursename, self.eventVideoInteract.videosysname )
                    
                    if rsVideo!=None:
                    
                        self.eventVideoInteract.videotitle=rsVideo[0]
                        self.eventVideoInteract.chaptersysname=rsVideo[1]
                        self.eventVideoInteract.chaptertitle=rsVideo[2]
                        self.userSession.moduletitle=self.eventVideoInteract.videotitle
                        self.userSession.chaptersysname=self.eventVideoInteract.chaptersysname
                        self.userSession.chaptertitle=self.eventVideoInteract.chaptertitle
                    
                    self.eventVideoInteract.source="SQL"
                    self.eventVideoInteract.courserun=self.userSession.courserun
                    self.eventVideoInteract.createdatetime=self.userSession.createdatetime
                    self.eventVideoInteract.lastmoddatetime=self.userSession.lastmoddatetime
                    self.userSession.eventsource="SQL"

                    #self.insertVideoRec()
                    self.eventVideoInteract.save()
                

                self.userSession.sessionid=self.eventId
                self.userSession.eventno=self.currEventNo
                print("BEFORE USERSESSION RECORD NO " + str(self.recNo) + " eventId " + str(self.eventId) )
                print("BEFORE USERSESSION currEventNo " + str(self.currEventNo) + " moduleType " + str(self.userSession.eventtype) )
                
                self.userSession.save()
                print 'yahoo'
                #userSessionDao.insertRec(self.userSession)
             
#         catch (SQLException e) 
#         {
#             // TODO Auto-generated catch block
#             System.out.println("SQL Exception " + e.toString())
#         } 
#         catch (ParseException e) 
#         {
#             // TODO Auto-generated catch block
#             System.out.println("Parse Exception " + e.toString())
#         } 
#         catch (java.text.ParseException e) 
#         {
#             // TODO Auto-generated catch block
#             System.out.println("Text Parse Exception " + e.toString())// e.printStackTrace()
#         } 

        except Exception, e:
            print 'error1'
            print("Exception " + str(e))
        finally :
            pass
#             try:
#                 if (sqlFetch.psVideo != None):
#                     sqlFetch.psVideo.close()
#                 if (sqlFetch.psVideoInteract != None): 
#                     sqlFetch.psVideoInteract.close()
#                 #if (connIIT != None):
#                 #    connIIT.close()
#                 #if (connEDX != None):
#                 #    connEDX.close()
                    
# #             catch (SQLException e) 
# #             {
# #                 // TODO Auto-generated catch block
# #                 System.out.println("SQL EXCEPTION " + e.toString())
# # 
# #             }

#             except Exception,e:
#                 print('Exception' + str(e))
    	return
    
    
    
    
    
    

    def insertUserSession(self):
        try:
            try:
                tmp=self.userSession.sessionid
            except AttributeError:
                self.userSession.sessionid=long(0)
        
        	self.userSession.save()
        except Exception, e:
            print("SQL ERROR IN INSERT REC userSession " + str(e))
        
        
      
    def insertVideoRec(self):
        
        try:
            
            try:
                tmp=self.eventVideoInteract.eventno
            except AttributeError:
                self.eventVideoInteract.eventno=0

            try:
                tmp=self.eventVideoInteract.oldseektime
            except AttributeError:
                self.eventVideoInteract.oldseektime=0.0
                
            try:
                tmp=self.eventVideoInteract.currseektime
            except AttributeError:
                self.eventVideoInteract.currseektime=0.0
                
            try:
                tmp=self.eventVideoInteract.oldspeed
            except AttributeError:
                self.eventVideoInteract.oldspeed=0.0
            
            try:
                tmp=self.eventVideoInteract.currspeed
            except AttributeError:
                self.eventVideoInteract.currspeed=0.0
                            
            
            self.eventVideoInteract.save()

            # sqlFetch.psVideoInteract( str(self.eventVideoInteract.eventid),\
            #                  str(self.eventVideoInteract.sessionsysname), str(self.eventVideoInteract.lmsname), \
            #                  str(self.eventVideoInteract.orgname), \
            #                  str(self.eventVideoInteract.coursename), str(self.eventVideoInteract.courserun),\
            #                  long(self.eventVideoInteract.lmsuserid), \
            #                  str(self.eventVideoInteract.eventname), int(self.eventVideoInteract.eventno),\
            #                  str(self.eventVideoInteract.videosysname), \
            #                  str(self.eventVideoInteract.videotitle), str(self.eventVideoInteract.chaptersysname), \
            #                  str(self.eventVideoInteract.chaptertitle), float(self.eventVideoInteract.oldseektime), \
            #                  float(self.eventVideoInteract.currseektime), str(self.eventVideoInteract.videonavigtype), \
            #                  float(self.eventVideoInteract.oldspeed), float(self.eventVideoInteract.currspeed), 
            #                  str(self.eventVideoInteract.source), \
            #                  datetime.datetime(self.eventVideoInteract.createdatetime), \
            #                  datetime.datetime(self.eventVideoInteract.lastmoddatetime) )
            
        except Exception, e:
            print "Insert Video Error" + str(e)
    
    
    
        
    def insertCourse(self) :
        try: 
            try:
                tmp=self.eventCourseInteract.lmsuserid
            except AttributeError:
                self.eventCourseInteract.lmsuserid=long(0)
            
            try:
                tmp=self.eventCourseInteract.eventno
            except AttributeError:
                self.eventCourseInteract.eventno=int(0)

            try:
                tmp=self.eventCourseInteract.oldposition
            except AttributeError:
                self.eventCourseInteract.oldposition=int(0)

            try:
                tmp=self.eventCourseInteract.curposition
            except AttributeError:
                self.eventCourseInteract.curposition=int(0)

            self.eventCourseInteract.save()
			#self.eventCourseInteract.save()         
            
            # sqlFetch.psCourseInteract( str(self.eventCourseInteract.eventid),\
            #              str(self.eventCourseInteract.lmsname), \
            #              str(self.eventCourseInteract.orgname), \
            #              str(self.eventCourseInteract.coursename), str(self.eventCourseInteract.courserun),\
            #              long(self.eventCourseInteract.lmsuserid), \
            #              str(self.eventCourseInteract.eventname), int(self.eventCourseInteract.eventno),\
            #              str(self.eventCourseInteract.moduletype),str(self.eventCourseInteract.modulesysname), \
            #              str(self.eventCourseInteract.moduletitle), str(self.eventCourseInteract.chaptersysname), \
            #              str(self.eventCourseInteract.chaptertitle), \
            #              datetime.datetime(self.eventCourseInteract.createdatetime), \
            #              datetime.datetime(self.eventCourseInteract.moddatetime),
            #              int(self.eventCourseInteract.oldposition), \
            #              int(self.eventCourseInteract.curposition), str(self.eventCourseInteract.source) )
                            
        except Exception, e:
            print str(e)



    def insertProbRec(self) :
            try:
                
                try:
                    tmp=self.eventProbInteract.eventno
                except AttributeError:
                    self.eventProbInteract.eventno=int(0)
                    
                try:
                    tmp=self.eventProbInteract.oldscore
                except AttributeError:
                    self.eventProbInteract.oldscore=float(0)
                    
                try:
                    tmp=self.eventProbInteract.newscore
                except AttributeError:
                    self.eventProbInteract.newscore=float(0)


                try:
                    tmp=self.eventProbInteract.maxgrade
                except AttributeError:
                    self.eventProbInteract.maxgrade=float(0)

                try:
                    tmp=self.eventProbInteract.attempts
                except AttributeError:
                    self.eventProbInteract.attempts=int(0)

                try:
                    tmp=self.eventProbInteract.maxattempts
                except AttributeError:
                    self.eventProbInteract.maxattempts=int(0)

                try:
                    tmp=self.eventProbInteract.probsubtime
                except AttributeError:
                    self.eventProbInteract.probsubtime=datetime.date(0,0,0)


                try:
                    tmp=self.eventProbInteract.done
                except AttributeError:
                    self.eventProbInteract.done=str('')
                    
            	
            	self.eventProbInteract.save()


                # sqlFetch.psProbInteract( str(self.eventProbInteract.lmsname), \
                #              str(self.eventProbInteract.orgname), \
                #              str(self.eventProbInteract.coursename), \
                #              long(self.eventProbInteract.lmsuserid), \
                #              str(self.eventProbInteract.eventname), int(self.eventProbInteract.eventno),\
                #              str(self.eventProbInteract.quizzsysname), \
                #              str(self.eventProbInteract.quizztitle), str(self.eventProbInteract.chaptersysname), \
                #              str(self.eventProbInteract.chaptertitle), \
                #              str(self.eventProbInteract.hintavailable), str(self.eventProbInteract.hintmode), \
                #              str(self.eventProbInteract.inputtype), str(self.eventProbInteract.responsetype), \
                #              str(self.eventProbInteract.variantid),\
                #              float(self.eventProbInteract.oldscore), float(self.eventProbInteract.newscore),\
                #              float(self.eventProbInteract.maxgrade),\
                #              int(self.eventProbInteract.attempts), str(self.eventProbInteract.maxattempts), \
                #              str(self.eventProbInteract.choice), str(self.eventProbInteract.success), \
                #              str(self.eventProbInteract.source), \
                #              datetime.date(self.eventProbInteract.probsubtime),\
                #              str(self.eventProbInteract.done), datetime.date(self.eventProbInteract.createdatetime), \
                #              datetime.date(self.eventProbInteract.lastmoddatetime), str(self.eventProbInteract.courserun) )
                     
                 

                #    psProbInteract.setDate(24, new java.sql.Date(eventProbInteract.getProbSubTime().getTime()));

            except Exception, e:
                print("SQL ERROR IN INSERTING REC IN EVENPROBINTERACT " + str(e))
                

    




def extractDataFromLine(line):
    user_data=json.loads(line)
    user_data=_decode_dict(user_data)
    
    #print user_data
    reader.fun()


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




def extractDataFromFile(file_path):
    with open(file_path) as f:
        for line in f:
            extractDataFromLine(line)

    return 


def extractDataFromFileList(source_folder,file_list):

    for file in file_list :
    	#print file
    	if file=="tracking.log-20150219-1424287021":
    		#print file
        	file_path=source_folder+"/"+file
        	extractDataFromFile(file_path)

    return 

def file_reader():
    source_folder="/home/little_pirate/LMS-2"

    filelist=os.listdir(source_folder)

    extractDataFromFileList(source_folder,filelist)
    
    


#reader=Sqlreader()
#file_reader()


if __name__=="__main__":
    reader=Sqlreader()
    reader.loadProperty()
    reader.sqlConnect()

    reader.fun()


