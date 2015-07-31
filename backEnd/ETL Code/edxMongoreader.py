import pymongo
import classes
import traceback
import exceptions

from dataloader import _decode_dict , _decode_list 

class Mongoreader(object):

    #code
    
    def __init__(self):
        self.serialVersionUID = 1260045197860625975
    
        self.mongoClient=pymongo.MongoClient("mongodb://localhost:27017/")
        self.mongoDBName="edxapp"
        self.db=mongoClient[mongoDbName]
        self.edxCollection=db["modulestore"]
        self.courseName = ""
        self.lmsName = ""
        self.edxCourse = ""
        self.chapterSysName=""
        self.quizType=""
        self.showAnswer=""
        self.quizTitle=""
        self.correctChoice=0
        self.orgName=""
        self.courseTitle=""
        self.language=""
        self.currencyCode=""
        self.courseRun = ""
        self.currCourseConcept=""
        self.prevCourseConcept=""
        self.currChapterConcept=""
        self.chapterTitle=""
        self.startDtStr=""
        self.endDtStr=""
        self.sessionType=""
        #courseDao = classes.Course()
        
        '''courseOthersDao = classes.Courseothers()
        courseVerticalDao = classes.Coursevertical()
        courseChapterDao = classes.Coursechapter()
        courseProblemsDao = classes.Courseproblems()
        courseVideosDao = classes.Coursevideo()
        courseDiscussDao = classes.Coursediscuss()
        courseChapterSessionDao = classes.CourseChapterSession()'''

    
    def processCourses(self):
        
         # Taking only courseTitle, startDate & endDate	  
        self.endDate = ""
        self.startDate = ""
        courseCourseQry = {}
        courseCourseQry["_id.category"]="course"
        #//courseCourseQry.put("_id.course",courseName);
        #//BasicDBObject aboutQuery = new BasicDBObject("_id.category","about");
        #BasicDBObject courseQuery = new BasicDBObject(courseCourseQry);
        courseCursor = self.edxCollection.find(courseQuery);
        #//System.out.println(" course and course count " + courseCursor.count());
        
        for courseDo in courseCursor:
            edxCourse = classes.Course()
            courseDo = _decode_dict(courseDo)
            dbId = courseDo.get("_id")
            dbId=_decode_dict(dbId)
            self.courseName = dbId.get("course")
            self.orgName = dbId.get("org")
            self.courseRun = dbId.get("name")
            defs = courseDo.get("definition")
            childStr = defs.get("children")
            childStrs = childStr.split(",")
            meta = courseDo.get("metadata")
            meta=_decode_dict(meta)
            self.courseTitle = meta.get("display_name")
            # date is in the format YYYY:MM:DDTHH:MM:SSZ
            try :
                endDatetmp  = meta.get("end")
                
                if(endDatetmp != None) :
                    tmp=endDatetmp.split("T");
                    self.endDate=tmp[0]+" "+tmp[:-1]
                
                startDatetmp=meta.get("start")
                
                if(startDate != None) :
                    tmp=startDatetmp.split("T");
                    self.startDate=tmp[0]+" "+tmp[:-1]
                    
            except Exception ,err :
                traceback.print_exc()
            
            processAboutShort();
            edxCourse.coursename=self.courseName
            edxCourse.coursetitle=self.courseTitle
            edxCourse.currconcepts=self.currCourseConcept
            #//edxCourse.setCurrencyCode(childStr);
            edxCourse.enddate=self.endDate
            #//edxCourse.setLanguage(childStr);
            edxCourse.lmsname=self.lmsName
            edxCourse.minprice=0
            edxCourse.orgname=self.orgName
            edxCourse.prevconcepts=self.prevCourseConcept
            edxCourse.startdate=self.startDate
            edxCourse.startdate=self.startDate
            edxCourse.enddate=self.endDate
            edxCourse.save();
            processChapters(childStrs);
 
    
    
    def processAboutShort(self):
        self.currCourseConcept=""
        self.prevCourseConcept = ""
        aboutCourseQry = {}
        aboutCourseQry["_id.category"]="about"
        aboutCourseQry["_id.course"]=self.courseName;
        aboutCourseQry["_id.name"]="short_description"
        aboutCursor = self.edxCollection.find(aboutCourseQry);
        
        for aboutDo in aboutCursor :
            dbDef = aboutDo.get("definition")
            abt =  dbDef.get("data")
            abtStr = abt.get("data")
            tmpStr=[]
            tmpStr1=[]
            tmpStr2=[]
            if(abtStr != None) :
                abtSplitStr = "<section class=\"about\""
                preSplitStr = "<section class=\"prerequisites\""
                if(abtSplitStr in abtStr):
                    tmpStr = abtStr.split(abtSplitStr)
                    tmpStr1 = tmpStr[1].split("<p align=justify>")
                    tmpStr2 = tmpStr1[1].split("</p>")
                    self.currCourseConcept = tmpStr2[0]
                    
                if(preSplitStr in abtStr ) :
                    tmpStr = abtStr.split(preSplitStr);
                    tmpStr1 = tmpStr[1].split("<p align=justify>")
                    tmpStr2 = tmpStr1[1].split("</p>")
                    self.prevCourseConcept = tmpStr2[0]
                    
    
    def processChapters(self,childStrs):
        #// Will enter as many chapters as available
        position =0
        chapterStartDate = ""

        if(((len(childStrs) == 1) and ("chapter" in childStrs[0] )) or (len(childStrs)> 1)) :
            z=len(childStrs)
            for  iCourseChild in range (0,z) :
                self.chapterSysName = (childStrs.get(iCourseChild))[childStrs[iCourseChild].find("chapter") + 8:childStrs[iCourseChild].find("chapter") + 40]
                courseChapterQry = {}
                courseChapterQry["_id.category"]="chapter"
                courseChapterQry["_id.course"]=self.courseName
                courseChapterQry["_id.name"]=self.chapterSysName
                chapterCursor = self.edxCollection.find(courseChapterQry)
                position +=1


                for courseDo in chapterCursor :
                    courseChapter = classes.Coursechapter();

                    meta = courseDo.get("metadata")
                    id1 = courseDo.get("_id")
                    defi = courseDo.get("definition")
                    childChapterStr = defi.get("children")

                    tmpStr = childChapterStr.split(",")
                    seqArray = []
                    childFlag = False
                    
                    tmpStr_length=len(tmpStr)
                    for iSeq  in range(0,tmpStr_length) :
                        seqArray.append("")
                        if(iSeq != len(tmpStr[iSeq])) :
                            if(tmpStr[iSeq].rfind("/") > 0):
                                seqArray[iSeq]=((tmpStr[iSeq])[tmpStr[iSeq].rfind("/")+ 1 : tmpStr[iSeq].rfind("\"")])
                                childFlag = True;


                        else :
                            if(tmpStr[iSeq].rfind("/") > 0):
                                seqArray[iSeq]=tmpStr[iSeq][tmpStr[iSeq].rfind("/") + 1: (tmpStr[iSeq].rfind("\"")) - 1]
                                childFlag = True;

                    if(childFlag == True) :
                        seqArray_length=len(seqArray)
                        for  iSeq  in range(0,seqArray_length):
                            processSequential(seqArray[iSeq],iSeq)

	    	


                    chapterTitle = meta.get("display_name")
                    try : 
                        if(meta.get("start") != None) :
                            
                            tmpDate= str(meta.get("start")).split("T")
                            chapterStartDate=tmpDate[0]+" "+tmpDate[1][:-1]
                            
                        else :
                            chapterStartDate = ""
                    except Exception ,err  :
                        traceback.print_exc()

                    self.currChapterConcept =self.chapterTitle 
                    self.chapterSysName = id1.get("name")
                    courseChapter.chaptersysname=self.chapterSysName
                    courseChapter.chaptertitle=self.chapterTitle
                    courseChapter.coursename=self.courseName
                    courseChapter.lmsname=self.lmsName
                    courseChapter.orgname=self.orgName
                    courseChapter.position=position
                    courseChapter.chapterstartdate=chapterStartDate
                    courseChapter.save()

    def processSequential(self, seqName,position) :
        #// Will enter as many chapters as available
        courseSeqQry = {} 
        courseSeqQry["_id.category"]="sequential"
        courseSeqQry["_id.course"]=self.courseName
        courseSeqQry["_id.name"]=seqName
     
        seqCursor = self.edxCollection.find(courseSeqQry)
        
        for seqDo in seqCursor :
            meta = seqDo.get("metadata")
            meta=_decode_dict(meta)
            seqTitle = meta.get("display_name")
            courseChapterSession = classes.Coursechaptersession()
            courseChapterSession.chaptersysname=self.chapterSysName
            courseChapterSession.coursename=self.courseName
            courseChapterSession.lmsname=self.lmsName
            courseChapterSession.orgname=self.orgName
            courseChapterSession.position=position

            if(meta.get("start") != None) :
                try:
                    tmp=meta.get("start")
                    if tmp != None:
                        tmparr=tmp.split("T")
                        courseChapterSession.sessionstartdate=tmparr[0]+" "+tmparr[:-1]
                except Exception ,err:
                    traceback.print_exc()

            else :
                courseChapterSession.sessionstartdate=""
            courseChapterSession.sessionsysname=seqName
            courseChapterSession.sessiontitle=seqTitle
            courseChapterSession.save()
            defs = seqDo.get("definition")
            childStr = defs.get("children")

            tmpStr = childStr.split(",")
            vertArray = []
            tmpStr_len=len(tmpStr)
            for iSeq  in range(0,tmpStr_len) :
                vertArray.append("")

                if(tmpStr[iSeq].rfind("/") != -1):
                    if(iSeq != len(tmpStr[iSeq])) :
                        vertArray[iSeq] = tmpStr[iSeq][tmpStr[iSeq].rfind("/")+ 1 : tmpStr[iSeq].rfind("\"")]
                    else :
                        vertArray[iSeq] = tmpStr[iSeq][tmpStr[iSeq].rfind("/") + 1 : tmpStr[iSeq].rfind("\"") - 1]

            vertArray_len=len(vertArray)
            for  iSeq in range(0,vertArray_len):
                processVertical(vertArray[iSeq], seqName)
                
                
    
    def processVertical( self ,vertName,seqName) :
        #// Will enter as many chapters as available
        courseVertQry = {}
        courseVertQry["_id.category"]="vertical"
        courseVertQry["_id.course"]=self.courseName
        courseVertQry["_id.name"]=self.vertName

        vertCursor = self.edxCollection.find(courseVertQry)

        for seqDo in vertCursor :
            seqDo=_decode_dict(seqDo)
            meta = seqDo.get("metadata");
            vertTitle = meta.get("display_name")
            defs = seqDo.get("definition")
            childStr = defs.get("children")
            courseVertical = classes.CourseVertical()
            courseVertical.coursename=self.courseName
            courseVertical.lmsname=self.lmsName
            courseVertical.orgname=self.orgName
            courseVertical.sessionsysname=seqName
            courseVertical.verticalsysname=vertName
            courseVertical.save()
            tmpStr = childStr.split(",")
            vertArray = [];
            tmpStr_len=len(tmpStr)
            for  iSeq  in range(0,tmpStr_len):
                vertArray.append("")
                tmpStr1 = tmpStr[iSeq].split("/")
                noOfSplits = len(tmpStr1) 
                
                if(noOfSplits >=2 ) :
                    vertType = tmpStr1[noOfSplits - 2];
                    vertArray[iSeq] = tmpStr1[noOfSplits - 1][0:tmpStr1[noOfSplits - 1].find("\"") ]


                    if(vertType=="problem") :
                        self.correctChoice = 0
                        processProblem(vertArray[iSeq])


                    elif(vertType=="html" ) :
                        processHtmls(vertArray[iSeq], vertName, seqName);

                    elif(vertType == "discussion"):
                        processDiscuss(vertArray[iSeq]);

                    elif(vertType == "video" ) :
                        processVideos(vertArray[iSeq])
                        
                        
    
    def processHtmls( self ,htmlName,vertName,seqName):
        courseHtmlQry = {}
        courseHtmlQry["_id.category"]="html"
        courseHtmlQry["_id.course"]=self.courseName
        courseHtmlQry["_id.name"]=htmlName 
        htmlCursor = self.edxCollection.find(courseHtmlQry)

        for htmlDo in htmlCursor :
            defDO = htmlDo.get("definition")
            defDo=_decode_dict(defDo)
            metaDO = htmlDo.get("metadata")
            dataDO = defDO.get("data")
            source = ""
            
            if(dataDO.get("data") != None):
                source = str(dataDO.get("data"))
            
            type1 = ""

            if (".pdf\\" in source ) :
                type1 = "pdf"

            elif (".jpg\\" in source) :
                type1 = "jpg"
            else :
                type1 = "html"
            
            vertTitle=""

            if(metaDO.get("display_name") != None) :
                vertTitle = str(metaDO.get("display_name"))

            courseOthers = classes.CourseOthers()

            courseOthers.type=type1
            courseOthers.title=vertTitle
            courseOthers.coursename=self.courseName
            courseOthers.lmsname=self.lmsName
            courseOthers.orgname=self.orgName
            courseOthers.chaptersysname=self.chapterSysName
            courseOthers.htmlsysname=self.htmlName
            courseOthers.sessionsysname=seqName
            courseOthers.verticalsysname=vertName
            courseOthers.save()


    def processDiscuss(self,discussName):
        discussTitle = ""
        discussSysId = ""
        courseDiscussQry = {}
        courseDiscussQry["_id.category"]="discussion"
        courseDiscussQry["_id.course"]=self.courseName
        courseDiscussQry["_id.name"]=discussName
        DiscussCursor = self.edxCollection.find(courseDiscussQry)
        
        for discussDo in  DiscussCursor :
            courseDiscuss = classes.CourseDiscussions()

            defDO = discussDo.get("definition")
            defDo=_decode_dict(defDo)
            metaDO =  discussDo.get("metadata")
            metaDo=_decode_dict(metaDo)
            discussTitle=""
            if(metaDO.get("discussion_target") != None) :
                discussTitle = str(metaDO.get("discussion_target"))
            else :
                discussTitle = ""
            discussSysId = metaDO.get("discussion_id")
            courseDiscuss.lmsname=self.lmsName
            courseDiscuss.orgname=self.orgName
            courseDiscuss.coursename=self.courseName
            courseDiscuss.discussiontitle=discussTitle
            courseDiscuss.chaptersysname=self.chapterSysName
            courseDiscuss.discussionsysname=discussName
            courseDiscuss.discusssysid=discussSysId
            courseDiscuss.save()
            
    
    def processVideos(self,videoName):
        uTube1 =""
        uTube2 = ""
        uTube3 = ""
        uTube4 = ""
        videoTitle = ""
        videoDownLoad = 0
        videoTrackDownLoad = 0
        courseVideoQry = {}
        courseVideoQry["_id.category"]="video"
        courseVideoQry["_id.course"]=self.courseName
        courseVideoQry["_id.name"]=videoName 
        videoCursor = self.edxCollection.find(videoQuery)

        for videoDo in videoCursor :
            courseVideos = classes.CourseVideos()
            defDO = videoDo.get("definition")
            metaDO = videoDo.get("metadata")

            if(metaDO.get("youtube_id_1_0") != None) :
                uTube1 = str(metaDO.get("youtube_id_1_0"))
            else :
                uTube1 = ""

            if(metaDO.get("youtube_id_1_25") != None) :
                uTube2 = str(metaDO.get("youtube_id_1_25"))
            else :
                uTube2 = None
            if(metaDO.get("youtube_id_1_5") != None) :
                uTube3 = str(metaDO.get("youtube_id_1_5"))
            else :
                uTube2 = ""
            
            if(metaDO.get("youtube_id_0_75") != None) :
                uTube4 = str(metaDO.get("youtube_id_0_75"))
            else :
                uTube4 = ""
            if(metaDO.get("display_name") != None) :
                videoTitle= str(metaDO.get("display_name"))
            else :
                videoTitle = ""


            if (metaDO.get("download_video") != None):
                if (str(metaDO.get("download_video"))=="true") :
                    videoDownLoad = 1
                else :
                    videoDownLoad = 0
            else :
                videoTrackDownLoad = -1
                
            if (metaDO.get("download_track") != None) :
                if (str(metaDO.get("download_track"))=="true"):
                    videoTrackDownLoad = 1;
                else :
                    videoTrackDownLoad = 0;
            else :
                videoTrackDownLoad = -1;
        courseVideos.lmsname=self.lmsName
        
        courseVideos.orgname=self.orgName
        courseVideos.coursename=self.courseName
        courseVideos.chaptersysname=self.chapterSysName
        courseVideos.videotitle=videoTitle
        courseVideos.videoutubeid=uTube1
        courseVideos.videoutubeid125=uTube2
        courseVideos.videoutubeid15=uTube3
        courseVideos.videoutubeId075=uTube4
        courseVideos.videosysname=videoName
        courseVideos.videodownload=videoDownLoad
        courseVideos.videotrackdownload=videoTrackDownLoad
        courseVideos.save()

    
    def processProblem(self ,problemName) :
        #// Will enter as many chapters as available
        courseProblemQry = {}
        courseProblemQry["_id.category"]="problem"
        courseProblemQry["_id.course"]=self.courseName
        courseProblemQry["_id.name"]=problemName
        problemCursor = self.edxCollection.find(problemQuery)

        for problemDo in problemCursor :
            courseProblems = classes.CourseProblems()
            defDO = problemDo.get("definition")
            metaDO = problemDo.get("metadata")
            metaDO=_decode_dict(metaDO)
            maxAttempt=-1
            if(metaDO.get("max_attempts") != None) :
                maxAttempt = int(str(metaDO.get("max_attempts")).strip())
            else :
                maxAttempt = -1
            weight=0.0
            
            if(metaDO.get("weight") != None) :
                weight = float(str(metaDO.get("weight")).strip())

            else :
                weight = 0.0
            if(metaDO.get("showanswer") != None):
                if((str(metaDO.get("showanswer")).strip()) == "never" ) :
                    courseProblems.hintavailable=0
                else :
                    courseProblems.hintavailable=1
                    courseProblems.hintmode=str(metaDO.get("showanswer")).strip()

            dataDO = defDO.get("data")
            courseProblems.lmsname=self.lmsName
            courseProblems.orgname=self.orgName
            courseProblems.coursenames=self.courseName
            courseProblems.chaptersysname=self.chapterSysName
            self.quizType = None
            self.quizTitle = None
            self.correctChoice = None
            if(dataDO.get("data") != None) :
                parseXMLString(str(dataDO.get("data")))

            if(metaDO.get("display_name") != None):
                if (self.quizTitle != None) :
                    courseProblems.quiztitle=str(metaDO.get("display_name")) + " " + self.quizTitle
                else :
                    courseProblems.quiztitle=str(metaDO.get("display_name"))

            else :
                if (self.quizTitle != None) :
                    courseProblems.quiztitle=self.quizTitle
                else :
                    courseProblems.quiztitle=None


            courseProblems.quiztype=self.quizType
            courseProblems.quizweight=weight
            courseProblems.noofattemptsallowed=maxAttempt
            courseProblems.quizmaxmarks=weight
            courseProblems.correctchoice=self.correctChoice
            courseProblems.quizsysname=problemName
            courseProblems.save()

    '''  def parseXMLString(self,strXML):
	  SAXParserFactory factory = SAXParserFactory.newInstance();
      try {
		SAXParser saxParser = factory.newSAXParser();
		ProblemStringHandler probStrHandle = new ProblemStringHandler();
		ByteArrayInputStream in = new ByteArrayInputStream(strXML.getBytes());
		InputSource is = new InputSource();
		is.setByteStream(in);
		saxParser.parse(is, probStrHandle); 
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
  }'''

if __name__ == '__main__':
    reader=Mongoreader()
    reader.processCourses()
    
    

