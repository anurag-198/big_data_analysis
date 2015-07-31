import MySQLdb
import exceptions
import traceback
import sys


db=MySQLdb.connect("localhost","root","1234","IITBxDataAnalytics")
cursor=db.cursor()






class Cities():
    
    name = ""
    stateid = 0

    class Meta:
        managed = True
        db_table = 'Cities'
        
    def save(self):
        
        sql="Insert into Cities (name,stateId) VALUES ( '%s' , '%s' )" % (self.name,self.stateid)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

class Cities2():
    
    name = ""
    stateid = 0

    class Meta:
        managed = True
        db_table = 'Cities'
        
    def save(self):
        
        sql="Insert into Cities2 (name,stateId) VALUES ( '%s' , '%s' )" % (self.name,self.stateid)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()



class Course():
    
    courseid = 0
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=6)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50)  # Field name made lowercase.
    coursename ="" # ""   #models.CharFielddb_column='courseName', max_length=50)  # Field name made lowercase.
    coursetitle ="" # ""   #models.CharFielddb_column='courseTitle', max_length=255)  # Field name made lowercase.
    authoruserid =-1 # 0 #models.IntegerField(db_column='authorUserId', blank=True, null=True)  # Field name made lowercase.
    currconcepts = "" #"" #models.TextField(db_column='currConcepts', blank=True, null=True)  # Field name made lowercase.
    prevconcepts ="" #"" #models.TextField(db_column='prevConcepts', blank=True, null=True)  # Field name made lowercase.
    courselang = ""# ""   #models.CharFielddb_column='courseLang', max_length=2, blank=True, null=True)  # Field name made lowercase.
    minprice =-1 # 0 #models.IntegerField(db_column='minPrice', blank=True, null=True)  # Field name made lowercase.
    suggestedprice =-1 #0 #models.IntegerField(db_column='suggestedPrice', blank=True, null=True)  # Field name made lowercase.
    currencycode ="" # ""   #models.CharFielddb_column='currencyCode', max_length=3, blank=True, null=True)  # Field name made lowercase.
    enddate ="" #"" #models.DateTimeField(db_column='endDate', blank=True, null=True)  # Field name made lowercase.
    startdate ="" #"" #models.DateTimeField(db_column='startDate', blank=True, null=True)  # Field name made lowercase.

    def save(self):
        
        sql = "insert into Course (lmsName, orgName,courseName,courseTitle, \
                authorUserId,currConcepts,prevConcepts,courseLang,\
                minPrice,suggestedPrice, currencyCode,endDate, startDate \
                values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s', '%s','%s','%s')"%\
                (self.coursename,self.orgname,self.coursename,self.coursetitle,self.authoruserid,\
                self.currconcepts,self.prevconcepts,self.courselang,self.minprice,self.suggestedprice,\
                self.currencycode,self.enddate,self.startdate)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()


    class Meta:
        managed = True
        db_table = 'Course'


class Coursecategory():
    
    coursecatgid =0 # 0 #models.AutoField(db_column='courseCatgid', primary_key=True)  # Field name made lowercase.
    categoryname = "" # ""   #models.CharFielddb_column='categoryName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    coursecounts = 0 #0 #models.IntegerField(db_column='courseCounts', blank=True, null=True)  # Field name made lowercase.
    parentid = -1 #0 #models.IntegerField(db_column='parentId')  # Field name made lowercase.
    
    def save(self):
        
        sql="Insert into CourseCategory (categoryName,courseCounts,parentId)\
            VALUES ( '%s' , '%s' , '%s' )" % (self.categoryname,self.coursecounts,self.parentid)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    class Meta:
        managed = True
        db_table = 'CourseCategory'


class Coursechapter():

    chapterid =0 # 0 #models.AutoField(db_column='chapterId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50)  # Field name made lowercase.
    chaptertitle ="" # ""   #models.CharFielddb_column='chapteTitle', max_length=255)  # Field name made lowercase.
    chaptersysname ="" # ""   #models.CharFielddb_column='chapterSysName', max_length=50)  # Field name made lowercase.
    chapterstartdate ="" #"" #models.DateTimeField(db_column='chapterStartDate', blank=True, null=True)  # Field name made lowercase.
    position = -1 # 0    #models.SmallIntegerField(blank=True, null=True)
        
    def save(self):
        
        sql="Insert into CourseChapter (lmsName,orgName,courseName,chapterTitle,\
            chapterSysName,chapterStartDate,position) VALUES ( '%s' , '%s' , '%s' , '%s' ,\
            '%s' , '%s' ,'%s' )" % (self.lmsname,self.orgname,self.coursename,self.chaptertitle,\
                                    self.chaptersysname,self.chapterstartdate,self.position)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    
    class Meta:
        managed = True
        db_table = 'CourseChapter'


class Coursechaptersession():

    sessionid =0  # 0 #models.AutoField(db_column='sessionId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50)  # Field name made lowercase.
    chaptersysname = "" # ""   #models.CharFielddb_column='chapterSysName', max_length=50)  # Field name made lowercase.
    sessionsysname = "" # ""   #models.CharFielddb_column='sessionSysName', max_length=50)  # Field name made lowercase.
    sessiontitle = "" # ""   #models.CharFielddb_column='sessionTitle', max_length=255)  # Field name made lowercase.
    sessionstartdate ="" #models.DateField(db_column='sessionStartDate', blank=True, null=True)  # Field name made lowercase.
    position = 0 # 0    #models.SmallIntegerField(blank=True, null=True)
        
    def save(self):
        
        sql="Insert into CourseChapterSession (lmsName,orgName,courseName,\
            chapterSysName,sessionSysName,sessionTitle,sessionStartDate,position) \
            VALUES ( '%s' , '%s' , '%s' , '%s' ,'%s' , '%s' ,'%s','%s' )"\
            % (self.lmsname,self.orgname,self.coursename,self.chaptersysname,\
                                    self.sessionsysname,self.sessiontitle,self.sessionstartdate,\
                                    self.position)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()


    class Meta:
        managed = True
        db_table = 'CourseChapterSession'


class Coursediscussions():

    discussionid =0 #0 #models.AutoField(db_column='discussionId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    chaptersysname = "" # ""   #models.CharFielddb_column='chapterSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    discussiontitle ="" #  ""   #models.CharFielddb_column='discussionTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    discussionsysname ="" # ""   #models.CharFielddb_column='discussionSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    discussionsysid = "" # ""   #models.CharFielddb_column='discussionSysId', max_length=45, blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        
        sql="Insert into CourseDiscussions (lmsName,orgName,courseName,\
            chapterSysName,discussionTitle,discussionSysName,discussionSysId) VALUES ( '%s' , '%s' , '%s' , '%s' ,\
            '%s' , '%s' ,'%s' )" % (self.lmsname,self.orgname,self.coursename,self.chaptersysname,\
                                    self.discussiontitle,self.discussionsysname,self.discussionsysid)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    
    class Meta:
        managed = True
        db_table = 'CourseDiscussions'


class Coursefiles():

    fileid = 0 #0 #models.AutoField(db_column='fileId', primary_key=True)  # Field name made lowercase.
    lmsname ="" #  ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname ="" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename ="" # ""   #models.CharFielddb_column='courseName', max_length=50)  # Field name made lowercase.
    chaptersysname ="" #  ""   #models.CharFielddb_column='chapterSysName', max_length=50)  # Field name made lowercase.
    sessionsysname =""  # ""   #models.CharFielddb_column='sessionSysName', max_length=50)  # Field name made lowercase.
    filetitle = "" # ""   #models.CharFielddb_column='fileTitle', max_length=255)  # Field name made lowercase.
    filesysname = "" # ""   #models.CharFielddb_column='fileSysName', max_length=50)  # Field name made lowercase.
    
    def save(self):
        
        sql="Insert into CourseFiles (lmsName,orgName,courseName,\
            chapterSysName,sessionSysName,filetitle,fileSysName) VALUES ( '%s' , '%s' , '%s' , '%s' ,\
            '%s' , '%s' ,'%s' )" % (self.lmsname,self.orgname,self.coursename,self.chaptersysname,\
                                    self.sessionsysname,self.filetitle,self.filesysname)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    class Meta:
        managed = True
        db_table = 'CourseFiles'


class Courseforums():

    forumid =0 # 0 #models.BigIntegerField(db_column='forumId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    courserun = "" # ""   #models.CharFielddb_column='courseRun', max_length=50, blank=True, null=True)  # Field name made lowercase.
    commentsysid = "" #  ""   #models.CharFielddb_column='commentSysId', max_length=50, blank=True, null=True)  # Field name made lowercase.
    commenttype = "" # ""   #models.CharFielddb_column='commentType', max_length=45, blank=True, null=True)  # Field name made lowercase.
    anonymousmode = "" # ""   #models.CharFielddb_column='anonymousMode', max_length=1, blank=True, null=True)  # Field name made lowercase.
    lmsauthorid = 0 #0 #models.BigIntegerField(db_column='lmsAuthorId', blank=True, null=True)  # Field name made lowercase.
    lmsauthorname = "" # ""   #models.CharFielddb_column='lmsAuthorName', max_length=120, blank=True, null=True)  # Field name made lowercase.
    createdatetime = "" #"" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    lastmoddatetime = "" #"" #models.DateTimeField(db_column='lastModDateTime', blank=True, null=True)  # Field name made lowercase.
    upvotecount = "" # ""   #models.CharFielddb_column='upVoteCount', max_length=45, blank=True, null=True)  # Field name made lowercase.
    totvotecount = "" # ""   #models.CharFielddb_column='totVoteCount', max_length=45, blank=True, null=True)  # Field name made lowercase.
    commentcount = "" #0 #models.IntegerField(db_column='commentCount', blank=True, null=True)  # Field name made lowercase.
    threadtype = "" # ""   #models.CharFielddb_column='threadType', max_length=50, blank=True, null=True)  # Field name made lowercase.
    title = "" # ""   #models.CharFieldmax_length=255, blank=True, null=True)
    commentablesysid = "" # ""   #models.CharFielddb_column='commentableSysId', max_length=45, blank=True, null=True)  # Field name made lowercase.
    endorsed = "" # ""   #models.CharFieldmax_length=1, blank=True, null=True)
    closed = "" #"" #models.TextField(blank=True, null=True)  # This field type is a guess.
    visible = "" #"" #models.TextField(blank=True, null=True)  # This field type is a guess.
    
    def save(self):
        
        sql = "INSERT INTO CourseForums (lmsName, orgName,	courseName, courseRun, commentSysId,\
            commentType, anonymousMode, lmsAuthorId, lmsAuthorName, createDateTime, lastModDateTime, \
                upVoteCount,totVoteCount, commentCount, threadType, title, commentableSysId,\
                endorsed, closed, visible)\
                VALUES ('%s','%s','%s','%s','%s', '%s','%s','%d','%s','%s',  '%s','%s','%s','%d','%s'  ,'%s','%s','%s',\
                '%s','%s')" % (\
                               self.lmsname,self.orgname,self.coursename,self.courserun,self.commentsysid,\
                               self.commenttype,self.anonymousmode,self.lmsauthorid,self.lmsauthorname,self.createdatetime,\
                               self.lastmoddatetime,self.upvotecount,self.totvotecount,self.commentcount,self.threadtype,\
                               self.title,self.commentablesysid,self.endorsed,self.closed,self.visible)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    
    
    
    class Meta:
        managed = True
        db_table = 'CourseForums'


class Courseothers():

    otherid = 0 #0 #models.AutoField(db_column='otherId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    title = "" # ""   #models.CharFieldmax_length=255, blank=True, null=True)
    htmlsysname = "" # ""   #models.CharFielddb_column='htmlSysName', max_length=50, blank=True, null=True)  # Fi eld name made lowercase.
    type1 = "" # ""   #models.CharFieldmax_length=45, blank=True, null=True)
    verticalsysname ="" #  ""   #models.CharFielddb_column='verticalSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    chaptersysname = "" #  ""   #models.CharFielddb_column='chapterSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    sessionsysname = "" # ""   #models.CharFielddb_column='sessionSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    filename = "" # ""   #models.CharFielddb_column='fileName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    
    def save(self):
        
        sql = "INSERT INTO CourseOthers (lmsName, orgName,	courseName,title, htmlsysname,\
            type, verticalsysname, chaptersysname, sessionsysname, filename)\
                VALUES ('%s','%s','%s','%s','%s',  '%s','%s','%s','%s','%s')" % (\
                               self.lmsname,self.orgname,self.coursename,self.title,self.htmlsysname,\
                               self.type1,self.verticalsysname,self.chaptersysname,self.sessionsysname,self.filename)
        
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()
    class Meta:
        managed = True
        db_table = 'CourseOthers'


class Courseproblems():
    
    problemid = 0 # 0 #models.BigIntegerField(db_column='problemId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" #  ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    chaptersysname = "" # ""   #models.CharFielddb_column='chapterSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    sessionsysname = "" # ""   #models.CharFielddb_column='sessionSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    quizsysname = "" # ""   #models.CharFielddb_column='quizSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    quiztitle = "" #"" #models.TextField(db_column='quizTitle', blank=True, null=True)  # Field name made lowercase.
    quiztype = "" # ""   #models.CharFielddb_column='quizType', max_length=50, blank=True, null=True)  # Field name made lowercase.
    quizweight = 0.0 #0.0 #models.FloatField(db_column='quizWeight', blank=True, null=True)  # Field name made lowercase.
    noofattemptsallowed = 0 # 0 #models.IntegerField(db_column='noOfAttemptsAllowed', blank=True, null=True)  # Field name made lowercase.
    quizmaxmarks = 0.0 #0.0 #models.FloatField(db_column='quizMaxMarks', blank=True, null=True)  # Field name made lowercase.
    hintavailable = 0 # 0    #models.SmallIntegerField(db_column='hintAvailable', blank=True, null=True)  # Field name made lowercase.
    correctchoice = 0 # 0    #models.SmallIntegerField(db_column='correctChoice', blank=True, null=True)  # Field name made lowercase.
    hintmode = "" # ""   #models.CharFielddb_column='hintMode', max_length=15, blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        sql = "insert into CourseProblems (problemId,lmsName, orgName,courseName, chapterSysName,sessionSysName\
            quizSysName, quizTitle, quizType, quizWeight, noOfAttemptsAllowed \
            quizMaxMarks,hintAvailable,correctChoice, hintMode )\
            values ('%s','%s','%s','%s','%s', '%s','%s','%s','%s','%f','%d', '%f','%d','%d' ,'%s' ) "%\
            (self.problemid,self.lmsname,self.orgname,self.coursename,self.chaptersysname,\
            self.quizsysname,self.quiztitle,self.quiztype,self.quizweight,self.noofattemptsallowed,\
                               self.quizmaxmarks,self.hintavailable,self.correctchoice,self.hintmode)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()
    class Meta:
        managed = True
        db_table = 'CourseProblems'

# not required
class Courserun():

    courserunid = 0 #0 #models.AutoField(db_column='courseRunid', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=6)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = -1 #0 #models.IntegerField(db_column='courseName')  # Field name made lowercase.
    courserun = "" # ""   #models.CharFielddb_column='courseRun', max_length=150, blank=True, null=True)  # Field name made lowercase.
    willbegraded = "" #Field(db_column='gradePass', blank=True, null=True)  # Field name made lowercase.
    actualprice = 0 #0 #models.IntegerField(db_column='actualPrice', blank=True, null=True)  # Field name made lowercase.
    currencycode = "" # ""   #models.CharFielddb_column='currencyCode', max_length=3)  # Field name made lowercase.
    startdate = "" #models.DateField(db_column='startDate', blank=True, null=True)  # Field name made lowercase.
    enddate = "" #models.DateField(db_column='endDate', blank=True, null=True)  # Field name made lowercase.
    
    
  
  
        
    class Meta:
        managed = True
        db_table = 'CourseRun'

# not required
class Courseversion():

    courseversionid = 0 #models.AutoField(db_column='courseVersionid', primary_key=True)  # Field name made lowercase.
    courseid = 0 #models.IntegerField(db_column='courseId')  # Field name made lowercase.
    description = "" #models.TextField(db_column='Description', blank=True, null=True)  # Field name made lowercase.
    createdon = ""#models.DateField(db_column='CreatedOn', blank=True, null=True)  # Field name made lowercase.
    lastmodified = ""#models.DateField(db_column='LastModified', blank=True, null=True)  # Field name made lowercase.
    filepathname =  ""   #models.CharFielddb_column='filePathName', max_length=100, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'CourseVersion'


class Coursevertical():

    vertid = 0 #0 #models.BigIntegerField(db_column='vertId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname ="" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename ="" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    sessionsysname =""  # ""   #models.CharFielddb_column='sessionSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    verticalsysname = "" # ""   #models.CharFielddb_column='verticalSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        sql = "insert into CourseVertical (vertId ,lmsName, orgName,courseName, sessionSysName,\
            verticalSysName )\
            values ('%d','%s','%s','%s','%s','%s' ) "%\
            (self.vertid,self.lmsname,self.orgname,self.coursename,self.sessionsysname,\
            self.verticalsysname)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()
  
    
    class Meta:
        managed = True
        db_table = 'CourseVertical'


class Coursevideos():

    videoid = 0 #0 #models.AutoField(db_column='videoId', primary_key=True)  # Field name made lowercase.
    lmsname = "" #"" # ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    chaptersysname = "" # ""   #models.CharFielddb_column='chapterSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    videosysname = "" # ""   #models.CharFielddb_column='videoSysName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    videoutubeid = "" # ""   #models.CharFielddb_column='videoUTubeId', max_length=50, blank=True, null=True)  # Field name made lowercase.
    videodownload = 0 #models.IntegerField(db_column='videoDownload', blank=True, null=True)  # Field name made lowercase.
    videotrackdownload = 0 #models.IntegerField(db_column='videoTrackDownLoad', blank=True, null=True)  # Field name made lowercase.
    videotitle = "" # ""   #models.CharFielddb_column='videoTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    videoutubeid075 = "" # ""   #models.CharFielddb_column='videoUTubeId075', max_length=50, blank=True, null=True)  # Field name made lowercase.
    videoutubeid125 = "" # ""   #models.CharFielddb_column='videoUTubeId125', max_length=50, blank=True, null=True)  # Field name made lowercase.
    videoutubeid15 = "" # ""   #models.CharFielddb_column='videoUTubeId15', max_length=50, blank=True, null=True)  # Field name made lowercase.
    videolength = 0.0 #models.FloatField(blank=True, null=True)
        
    def save(self):
        sql = "insert into CourseVideos (lmsName, orgName,courseName, chapterSysName,\
            videoSysName, videoUTubeId, videoDownload, videoTrackDownload, videoTitle \
            videoUtubeId075,videoUtubeId125,videoUtube15, videolength )\
            values ('%s','%s','%s','%s','%s',  '%s','%d','%d','%s','%s',  '%s','%s','%s' ) "%\
            (self.lmsname,self.orgname,self.coursename,self.chaptersysname,\
            self.videosysname,self.videoutubeid,self.videodownload,self.videotrackdownload,self.videotitle,\
                               self.videoutubeid075,self.videoutubeid125,self.videoutubeid15,self.videolength)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()
  

    class Meta:
        managed = True
        db_table = 'CourseVideos'

# ask it
class Coursewiki():

    wikiid = 0 #models.AutoField(db_column='wikiId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50)  # Field name made lowercase.
    wikislug = "" # ""   #models.CharFielddb_column='wikiSlug', max_length=50)  # Field name made lowercase.
    lmswikiid = 0 #models.BigIntegerField(db_column='lmsWikiId', blank=True, null=True)  # Field name made lowercase.
    createddate = "" #models.DateTimeField(db_column='createdDate', blank=True, null=True)  # Field name made lowercase.
    lastmoddate = "" #models.DateTimeField(db_column='lastModDate', blank=True, null=True)  # Field name made lowercase.
    lastrevid = 0 #models.IntegerField(db_column='lastRevId', blank=True, null=True)  # Field name made lowercase.
    ownerid = 0 #models.BigIntegerField(db_column='ownerId', blank=True, null=True)  # Field name made lowercase.
    groupid = 0 #models.BigIntegerField(db_column='groupId', blank=True, null=True)  # Field name made lowercase.
    groupread = 0 #models.IntegerField(db_column='groupRead', blank=True, null=True)  # Field name made lowercase.
    groupwrite = 0 #models.IntegerField(db_column='groupWrite', blank=True, null=True)  # Field name made lowercase.
    otherread = 0 #models.IntegerField(db_column='otherRead', blank=True, null=True)  # Field name made lowercase.
    otherwrite = 0 #models.IntegerField(db_column='otherWrite', blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        sql = "insert into CourseWiki(lmsName,orgName,courseName,wikiSlug) values ('%s','%s','%s','%s')"%\
            (self.lmsname,self.orgname,self.coursename,self.wikislug)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()
  

    class Meta:
        managed = True
        db_table = 'CourseWiki'


class Eventcourseinteract():
    
    lmsuserid=0L
    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    courserun = "" # ""   #models.CharFielddb_column='courseRun', max_length=50, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId')  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    eventno =  0    #models.SmallIntegerField(db_column='eventNo', blank=True, null=True)  # Field name made lowercase.
    moduletype = "" # ""   #models.CharFielddb_column='moduleType', max_length=50, blank=True, null=True)  # Field name made lowercase.
    modulesysname = "" # ""   #models.CharFielddb_column='moduleSysName', max_length=50)  # Field name made lowercase.
    moduletitle = "" #models.TextField(db_column='moduleTitle', blank=True, null=True)  # Field name made lowercase.
    chaptersysname = "" # ""   #models.CharFielddb_column='chapterSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    chaptertitle = "" #models.TextField(db_column='chapterTitle', blank=True, null=True)  # Field name made lowercase.
    createdatetime = "" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    moddatetime = "" #models.DateTimeField(db_column='modDateTime', blank=True, null=True)  # Field name made lowercase.
    oldposition =  0    #models.SmallIntegerField(db_column='oldPosition', blank=True, null=True)  # Field name made lowercase.
    curposition =  0    #models.SmallIntegerField(db_column='curPosition', blank=True, null=True)  # Field name made lowercase.
    source = "" # ""   #models.CharFieldmax_length=15, blank=True, null=True)
        
    def save(self):
        
        sql = "Insert into EventCourseInteract (lmsName, orgName,courseName,courseRun,lmsUserId,\
                 eventName,eventNo,moduleType,modulesysName,moduleTitle,chapterSysName,chapterTitle,\
                 createDateTime,moddateTime,oldPosition,curPosition,source )\
                VALUES ('%s','%s','%s','%s',  '%d','%s','%d','%s','%s', '%s','%s','%s','%s','%s', '%d','%d','%s')" %\
                (self.lmsname,self.orgname,self.coursename,self.courserun,self.lmsuserid,
                    self.eventname,self.eventno,self.moduletype,self.modulesysname,self.moduletitle,
                    self.chaptersysname,self.chaptertitle,self.createdatetime,self.moddatetime,
                    self.oldposition,self.curposition,self.source)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()
    
    
    class Meta:
        managed = True
        db_table = 'EventCourseInteract'


class Eventdiscussion():

    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        
        sql = "Insert into EventDiscussion (eventId, lmsName,orgName,courseName, eventName )\
                values ('%d','%s','%s','%s','%s')" %\
                (self.eventid,self.lmsname,self.orgname,self.coursename,self.eventname)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    class Meta:
        managed = True
        db_table = 'EventDiscussion'


class Eventenrollment():

    eventid = 0 #models.BigIntegerField(db_column='ventId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', blank=True, null=True)  # Field name made lowercase.
    username = "" # ""   #models.CharFielddb_column='userName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    gender = "" # ""   #models.CharFieldmax_length=1, blank=True, null=True)
    edulevel = "" # ""   #models.CharFielddb_column='eduLevel', max_length=30, blank=True, null=True)  # Field name made lowercase.
    activate = "" # ""   #models.CharFieldmax_length=1, blank=True, null=True)
    adminuserid = 0 #models.BigIntegerField(db_column='adminUserId', blank=True, null=True)  # Field name made lowercase.
    datetime = "" #models.DateTimeField(db_column='dateTime', blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        
        sql = "Insert into EventEnrollment (eventID,lmsName, orgName,courseName,eventName,lmUserId,\
                 userName,gender ,eduLevel, activate  ,adminUserId,dateTime)\
                VALUES ('%d','%s','%s','%s','%s',   '%d','%s','%s','%s','%s' ,  '%d','%s')" %\
                (self.eventid,self.lmsname,self.orgname,self.coursename,self.eventname,
                    self.lmsuserid,self.username,self.gender,self.edulevel,
                    self.activate,self.adminuserid,self.datetime)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()


    class Meta:
        managed = True
        db_table = 'EventEnrollment'


class Eventforuminteract():

    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    commentthreadid = "" # ""   #models.CharFielddb_column='commentThreadId', max_length=50, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', blank=True, null=True)  # Field name made lowercase.
    querytext = "" #models.TextField(db_column='queryText', blank=True, null=True)  # Field name made lowercase.
    noofresults =  0    #models.SmallIntegerField(db_column='noOfResults', blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        
        sql = "Insert into EventForumInteract (eventID,lmsName, orgName,courseName,eventName,commentThreadId,\
                 lmsUserId,queryText,noOfResults)\
                VALUES ('%d','%s','%s','%s','%s','%s','%d','%s','%d')" %\
                (self.eventid,self.lmsname,self.orgname,self.coursename,self.eventname,self.commentthreadid,
                    self.lmsuserid,self.querytext,self.noOfresults)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    class Meta:
        managed = True
        db_table = 'EventForumInteract'


class Eventinstructor():

    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        
        sql = "Insert into EventInstructor (eventID,lmsName, orgName,courseName,eventName)\
                VALUES ('%d','%s','%s','%s','%s')" %\
                (self.eventid,self.lmsname,self.orgname,self.coursename,self.eventname)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()
    class Meta:
        managed = True
        db_table = 'EventInstructor'


class Eventnavigation():

    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        
        sql = "Insert into EventNavigation (eventID,lmsName, orgName,courseName,eventName)\
                VALUES ('%d','%s','%s','%s','%s')" %\
                (self.eventid,self.lmsname,self.orgname,self.coursename,self.eventname)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    class Meta:
        managed = True
        db_table = 'EventNavigation'


class Eventpdfinteract():

    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        
        sql = "Insert into EventPDFInteract (eventID,lmsName, orgName,courseName,eventName)\
                VALUES ('%d','%s','%s','%s','%s')" %\
                (self.eventid,self.lmsname,self.orgname,self.coursename,self.eventname)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    class Meta:
        managed = True
        db_table = 'EventPDFInteract'


class Eventprobinteract():

    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', blank=True, null=True)  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    eventno =  0    #models.SmallIntegerField(db_column='eventNo', blank=True, null=True)  # Field name made lowercase.
    quizzsysname = "" # ""   #models.CharFielddb_column='quizzSysName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    quizztitle = "" #models.TextField(db_column='quizzTitle', blank=True, null=True)  # Field name made lowercase.
    chaptersysname = "" # ""   #models.CharFielddb_column='chapterSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    chaptertitle = "" #models.TextField(db_column='chapterTitle', blank=True, null=True)  # Field name made lowercase.
    hintavailable = "" # ""   #models.CharFielddb_column='hintAvailable', max_length=1, blank=True, null=True)  # Field name made lowercase.
    hintmode = "" # ""   #models.CharFielddb_column='hintMode', max_length=50, blank=True, null=True)  # Field name made lowercase.
    inputtype = "" # ""   #models.CharFielddb_column='inputType', max_length=255, blank=True, null=True)  # Field name made lowercase.
    responsetype = "" # ""   #models.CharFielddb_column='responseType', max_length=255, blank=True, null=True)  # Field name made lowercase.
    variantid = "" # ""   #models.CharFielddb_column='variantId', max_length=255, blank=True, null=True)  # Field name made lowercase.
    oldscore = 0.0 #models.FloatField(db_column='oldScore', blank=True, null=True)  # Field name made lowercase.
    newscore = 0.0 #models.FloatField(db_column='newScore', blank=True, null=True)  # Field name made lowercase.
    maxgrade = 0.0 #models.FloatField(db_column='maxGrade', blank=True, null=True)  # Field name made lowercase.
    attempts = 0 #models.IntegerField(blank=True, null=True)
    maxattempts = 0 #models.IntegerField(db_column='maxAttempts', blank=True, null=True)  # Field name made lowercase.
    choice = "" # ""   #models.CharFieldmax_length=50, blank=True, null=True)
    success = "" # ""   #models.CharFieldmax_length=1, blank=True, null=True)
    source = "" # ""   #models.CharFieldmax_length=4, blank=True, null=True)
    probsubtime = "" #models.DateTimeField(db_column='probSubTime', blank=True, null=True)  # Field name made lowercase.
    done = "" # ""   #models.CharFieldmax_length=1, blank=True, null=True)
    createdatetime = "" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    lastmoddatetime = "" #models.DateTimeField(db_column='lastModDateTime', blank=True, null=True)  # Field name made lowercase.
    courserun = "" # ""   #models.CharFielddb_column='courseRun', max_length=45, blank=True, null=True)  # Field name made lowercase.
    
    def save(self):
        
        sql = "Insert into EventProbInteract (lmsName, orgName,courseName,lmsuserId,   eventName,\
                eventNo, quizzSysName,quizzTitle,chapterSysName,  chapterTitle,hintAvailable, \
                hintMode,inputType,responseType,  variantId,oldScore,newScore,maxGrade,attempts ,\
                maxAttempts,choice, success, source ,probSubTime,    done,createDateTime,\
                lastModDatetime,courseRun)\
                VALUES ('%s','%s','%s','%s',    '%s','%s','%s','%s','%s',   '%s','%s','%s','%s','%s',  '%s','%s','%s',\
                '%s','%s',   '%s','%s','%s','%s','%s',    '%s','%s','%s','%s')" % \
                 (self.lmsname,self.orgname,self.coursename,self.lmsuserid,self.eventname,self.eventno,
                   self.quizzsysname,self.quizztitle,self.chaptersysname,self.chaptertitle,self.hintavailable,
                   self.hintmode,self.inputtype,self.responsetype,self.variantid,self.oldscore,self.newscore,
                   self.maxgrade,self.attempts,self.maxattempts,self.choice,self.success,self.source,
                   self.probsubtime,self.done,self.createdatetime,self.lastmoddatetime,self.courserun) 
    
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()
    
    
    class Meta:
        managed = True
        db_table = 'EventProbInteract'


class Eventvideointeract():
    
    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    sessionsysname = "" # ""   #models.CharFielddb_column='sessionSysName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    courserun = "" # ""   #models.CharFielddb_column='courseRun', max_length=45, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', blank=True, null=True)  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    eventno =  0    #models.SmallIntegerField(db_column='eventNo', blank=True, null=True)  # Field name made lowercase.
    videosysname = "" # ""   #models.CharFielddb_column='videoSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    videotitle = "" #models.TextField(db_column='videoTitle', blank=True, null=True)  # Field name made lowercase.
    chaptersysname = "" # ""   #models.CharFielddb_column='chapterSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    chaptertitle = "" #models.TextField(db_column='chapterTitle', blank=True, null=True)  # Field name made lowercase.
    oldseektime = 0.0 #models.FloatField(db_column='oldSeekTime', blank=True, null=True)  # Field name made lowercase.
    currseektime = 0.0 #models.FloatField(db_column='currSeekTime', blank=True, null=True)  # Field name made lowercase.
    videonavigtype = "" # ""   #models.CharFielddb_column='videoNavigType', max_length=50, blank=True, null=True)  # Field name made lowercase.
    oldspeed = 0.0 #models.FloatField(db_column='oldSpeed', blank=True, null=True)  # Field name made lowercase.
    currspeed = 0.0 #models.FloatField(db_column='currSpeed', blank=True, null=True)  # Field name made lowercase.
    source = "" # ""   #models.CharFieldmax_length=4, blank=True, null=True)
    createdatetime = "" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    lastmoddatetime = "" #models.DateTimeField(db_column='lastModDateTime', blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        
        sql = "Insert into EventvideoInteract (eventID,sessionsysName,lmsName, orgName, courseName,  courseRun,lmuserId,\
                eventName,eventNo, videoSysName,    videoTitle,chapterSysName,chapterTitle,oldSeekTime, \
                currseekTime,   videoNavigType,oldSpeed,currSpeed,source,createDateTime,   lastModDateTime\
                values ('%d','%s','%s','%s','%s',  '%s','%d','%s','%d','%s',   '%s', '%s','%s','%f','%f',  '%s','%f','%f',\
                '%s','%s',  '%s')" %\
                (self.eventid,self.sessionsysname,self.lmsname,self.orgname,self.coursename,self.courserun,self.lmsuserid,\
                    self.eventname,self.eventno,self.videosysname,self.videotitle,self.chaptersysname,self.chapterTitle,\
                    self.oldSeekTime,self.currseektime,self.videonavigtype,self.oldspeed,self.currspeed,\
                    self.source,self.createdatetime,self.lastmoddatetime)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()
    
    
    class Meta:
        managed = True
        db_table = 'EventVideoInteract'


class Eventwikiinteract():

    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    lmsname = "" # ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname = "" # ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename = "" # ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    eventname = "" # ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
        
    def save(self):
        
        sql = "Insert into Eventwikiinteract (eventID, lmsName,orgName,courseName,eventName)\
                values ('%d','%s','%s','%s','%s')" % \
                (self.eventid,self.lmsname,self.orgname,self.coursename,self.eventname)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()


    class Meta:
        managed = True
        db_table = 'EventWikiInteract'


class Lmslist():

    lmsshortname =  ""   #models.CharFielddb_column='LMSShortName', primary_key=True, max_length=6)  # Field name made lowercase.
    lmsfullname =  ""   #models.CharFielddb_column='LMSFullName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    datetimeformat =  ""   #models.CharFielddb_column='DateTimeFormat', max_length=80)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'LMSList'


class States():

    name =  ""   #models.CharFieldunique=True, max_length=255)
    
    def save(self):
        
        sql="Insert into States (name) VALUES ('%s')" % (self.name)
        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()

    class Meta:
        managed = True
        db_table = 'States'

class Studentcourseenrolment():
    
    enrolid = 0 #models.BigIntegerField(db_column='enrolId', primary_key=True)  # Field name made lowercase.
    lmsname =  ""   #models.CharFielddb_column='lmsName', max_length=6, blank=True, null=True)  # Field name made lowercase.
    orgname =  ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename =  ""   #models.CharFielddb_column='courseName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    courserun =  ""   #models.CharFielddb_column='courseRun', max_length=50, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', blank=True, null=True)  # Field name made lowercase.
    enrolmentdate = "" #models.DateTimeField(db_column='enrolmentDate', blank=True, null=True)  # Field name made lowercase.
    active =  ""   #models.CharFieldmax_length=1, blank=True, null=True)
    mode =  ""   #models.CharFieldmax_length=15, blank=True, null=True)

    def save(self):

        sql="insert into StudentCourseEnrolment(lmsName, orgName, courseName, courseRun,lmsUserId, \
            enrolmentDate, active,mode)  values ('%s','%s','%s','%s','%s','%s','%s','%s')" % \
            (self.lmsname,self.orgname,self.coursename,self.courserun,self.lmsuserid,self.enrolmentdate, \
                self.active, self.mode) 

        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()


    class Meta:
        managed = True
        db_table = 'StudentCourseEnrolment'

class Studentcourseaccessrole():
    
    lmsuserid = 0 #models.IntegerField(db_column='lmsUserId')  # Field name made lowercase.
    orgname =  ""   #models.CharFielddb_column='orgName', max_length=64)  # Field name made lowercase.
    coursename =  ""   #models.CharFielddb_column='courseName', max_length=255)  # Field name made lowercase.
    courserun =  ""   #models.CharFielddb_column='courseRun', max_length=45, blank=True, null=True)  # Field name made lowercase.
    role =  ""   #models.CharFieldmax_length=64)

    class Meta:
        managed = True
        db_table = 'StudentCourseAccessRole'
        unique_together = ['lmsuserid', 'orgname', 'coursename', 'role']




class Studentcoursegrades():
    
    #it was id, not eventid
    eventid = 0 #models.BigIntegerField(primary_key=True)
    lmsname =  ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname =  ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename =  ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    courserun =  ""   #models.CharFielddb_column='courseRun', max_length=255, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', blank=True, null=True)  # Field name made lowercase.
    lmsusername =  ""   #models.CharFielddb_column='lmsUserName', max_length=100, blank=True, null=True)  # Field name made lowercase.
    moduletype =  ""   #models.CharFielddb_column='moduleType', max_length=45, blank=True, null=True)  # Field name made lowercase.
    modulesysname =  ""   #models.CharFielddb_column='moduleSysName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    score = 0 #models.IntegerField(blank=True, null=True)
    maxscore = 0 #models.IntegerField(db_column='maxScore', blank=True, null=True)  # Field name made lowercase.
    noofattempts = 0 #models.IntegerField(db_column='noOfAttempts', blank=True, null=True)  # Field name made lowercase.
    hintused =  ""   #models.CharFielddb_column='hintUsed', max_length=255, blank=True, null=True)  # Field name made lowercase.
    hintavailable =  ""   #models.CharFielddb_column='hintAvailable', max_length=255, blank=True, null=True)  # Field name made lowercase.
    state = "" #models.TextField(blank=True, null=True)
    goals =  ""   #models.CharFieldmax_length=50, blank=True, null=True)
    createdatetime = "" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    lastmoddatetime = "" #models.DateTimeField(db_column='lastModDateTime', blank=True, null=True)  # Field name made lowercase.
    totsessdurainsecs = 0 #models.IntegerField(db_column='totSessDuraInSecs', blank=True, null=True)  # Field name made lowercase.
    done =  ""   #models.CharFieldmax_length=45, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'StudentCourseGrades'


class Urltree():

    urlid = 0 #models.BigIntegerField(db_column='urlId', primary_key=True)  # Field name made lowercase.
    lmsname =  ""   #models.CharFielddb_column='lmsName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    orgname =  ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename =  ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    courserun =  ""   #models.CharFielddb_column='courseRun', max_length=50, blank=True, null=True)  # Field name made lowercase.
    urlsysname =  ""   #models.CharFielddb_column='urlSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    urltype =  ""   #models.CharFielddb_column='urlType', max_length=20, blank=True, null=True)  # Field name made lowercase.
    parenturl =  ""   #models.CharFielddb_column='parentUrl', max_length=50, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'URLTree'


class User():

    userid = 0 #models.BigIntegerField(db_column='userId', primary_key=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', unique=True)  # Field name made lowercase.
    lmsname =  ""   #models.CharFielddb_column='lmsName', max_length=6)  # Field name made lowercase.
    orgname =  ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    name =  ""   #models.CharFieldmax_length=100)
    gender =  ""   #models.CharFieldmax_length=1, blank=True, null=True)
    registrationdate = ""#models.DateField(db_column='registrationDate', blank=True, null=True)  # Field name made lowercase.
    emailid =  ""   #models.CharFielddb_column='emailId', max_length=100)  # Field name made lowercase.
    mothertounge =  ""   #models.CharFieldmax_length=2, blank=True, null=True)
    highestedudegree =  ""   #models.CharFielddb_column='highestEduDegree', max_length=6, blank=True, null=True)  # Field name made lowercase.
    goals = "" #models.TextField(blank=True, null=True)
    city =  ""   #models.CharFieldmax_length=120, blank=True, null=True)
    state =  ""   #models.CharFieldmax_length=120, blank=True, null=True)
    active =  0    #models.SmallIntegerField()
    firstaccesdate = "" #models.DateTimeField(db_column='firstAccesDate', blank=True, null=True)  # Field name made lowercase.
    lastaccessdate = "" #models.DateTimeField(db_column='lastAccessDate', blank=True, null=True)  # Field name made lowercase.
    allowcert =  0    #models.SmallIntegerField(db_column='allowCert', blank=True, null=True)  # Field name made lowercase.
    yearofbirth =  0    #models.SmallIntegerField(db_column='yearOfBirth', blank=True, null=True)  # Field name made lowercase.
    pincode = 0 #models.IntegerField(blank=True, null=True)
    aadharid =  ""   #models.CharFielddb_column='aadharId', max_length=45, blank=True, null=True)  # Field name made lowercase.


    def save(self):

        sql= "insert into User(lmsUserId,name , gender ,registrationDate , \
            emailId , mothertounge ,  highestEduDegree ,  goals ,city, state ,active ,\
            lastAccessDate, allowCert, yearOfBirth,pincode,aadharId) \
            values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')" % \
            (self.lmsuserid,self.name,self.gender,self.registrationdate,self.emailid,self.mothertounge, \
                self.highestedudegree,self.goals,self.city,self.state,self.active,self.lastaccessdate, \
                self.allowcert,self.yearofbirth,self.pincode,self.aadharid)

        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()


    class Meta:
        managed = True
        db_table = 'User'





class Usersession():

    sessionid = 0 #models.BigIntegerField(db_column='sessionId', primary_key=True)  # Field name made lowercase.
    sessionsysname =  ""   #models.CharFielddb_column='sessionSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    lmsname =  ""   #models.CharFielddb_column='lmsName', max_length=6)  # Field name made lowercase.
    orgname =  ""   #models.CharFielddb_column='orgName', max_length=50)  # Field name made lowercase.
    coursename =  ""   #models.CharFielddb_column='courseName', max_length=50)  # Field name made lowercase.
    courserun =  ""   #models.CharFielddb_column='courseRun', max_length=150, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId')  # Field name made lowercase.
    username =  ""   #models.CharFielddb_column='userName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    agent =  ""   #models.CharFieldmax_length=255, blank=True, null=True)
    hostname =  ""   #models.CharFielddb_column='hostName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    ipaddress =  ""   #models.CharFielddb_column='ipAddress', max_length=50, blank=True, null=True)  # Field name made lowercase.
    url =  ""   #models.CharFieldmax_length=255, blank=True, null=True)
    createdatetime = "" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    eventtype =  ""   #models.CharFielddb_column='eventType', max_length=100, blank=True, null=True)  # Field name made lowercase.
    eventsource =  ""   #models.CharFielddb_column='eventSource', max_length=50, blank=True, null=True)  # Field name made lowercase.
    eventname =  ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    eventid = 0 #models.BigIntegerField(db_column='eventId', blank=True, null=True)  # Field name made lowercase.
    lastmoddatetime = "" #models.DateTimeField(db_column='lastModDateTime', blank=True, null=True)  # Field name made lowercase.
    datasource =  ""   #models.CharFielddb_column='dataSource', max_length=3, blank=True, null=True)  # Field name made lowercase.
    eventno =  0    #models.SmallIntegerField(db_column='eventNo', blank=True, null=True)  # Field name made lowercase.


    def save(self):

        sql="insert into UserSession (sessionSysName, lmsName, orgName, courseName,courseRun, lmsUserId, userName," \
                    "agent, hostName, ipAddress, url, createDateTime, eventType, eventSource, eventName," \
                    "eventId, lastModDateTime,dataSource,eventNo) values ('%s','%s','%s','%s','%s','%d','%s','%s','%s', \
                    '%s','%s','%s','%s','%s','%s','%d','%s','%s','%d')" % \
                    (self.sessionsysname,self.lmsname,self.orgname,self.coursename,self.courserun,self.lmsuserid,self.username, \
                        self.agent,self.hostname,self.ipaddress,self.url,self.createdatetime,self.eventtype,self.eventsource, \
                        self.eventname, self.eventid, self.lastmoddatetime, self.datasource, self.eventno)

        try:
            cursor.execute(sql)
            db.commit()
        except Exception ,err :
            traceback.print_exc()
            db.rollback()



    class Meta:
        managed = True
        db_table = 'UserSession'


class Usersessionold():


    sessionid = 0 #models.BigIntegerField(db_column='sessionId', primary_key=True)  # Field name made lowercase.
    sessionsysname =  ""   #models.CharFielddb_column='sessionSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    lmsname =  ""   #models.CharFielddb_column='lmsName', max_length=6)  # Field name made lowercase.
    orgname =  ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename =  ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    courserun =  ""   #models.CharFielddb_column='courseRun', max_length=150, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', blank=True, null=True)  # Field name made lowercase.
    username =  ""   #models.CharFielddb_column='userName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    agent =  ""   #models.CharFieldmax_length=255, blank=True, null=True)
    hostname =  ""   #models.CharFielddb_column='hostName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    ipaddress =  ""   #models.CharFielddb_column='ipAddress', max_length=50, blank=True, null=True)  # Field name made lowercase.
    createdatetime = "" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    eventtype =  ""   #models.CharFielddb_column='eventType', max_length=100, blank=True, null=True)  # Field name made lowercase.
    eventsource =  ""   #models.CharFielddb_column='eventSource', max_length=50)  # Field name made lowercase.
    eventname =  ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    datasource =  ""   #models.CharFielddb_column='dataSource', max_length=5, blank=True, null=True)  # Field name made lowercase.
    oldvideospeed = 0.0 #models.FloatField(db_column='oldVideoSpeed', blank=True, null=True)  # Field name made lowercase.
    currvideospeed = 0.0 #models.FloatField(db_column='currVideoSpeed', blank=True, null=True)  # Field name made lowercase.
    oldvideotime = 0.0 #models.FloatField(db_column='oldVideoTime', blank=True, null=True)  # Field name made lowercase.
    currvideotime = 0.0 #models.FloatField(db_column='currVideoTime', blank=True, null=True)  # Field name made lowercase.
    videonavigtype =  ""   #models.CharFielddb_column='videoNavigType', max_length=15, blank=True, null=True)  # Field name made lowercase.
    oldgrade = 0.0 #models.FloatField(db_column='oldGrade', blank=True, null=True)  # Field name made lowercase.
    currgrade = 0.0 #models.FloatField(db_column='currGrade', blank=True, null=True)  # Field name made lowercase.
    maxgrade = 0.0 #models.FloatField(db_column='maxGrade', blank=True, null=True)  # Field name made lowercase.
    attempts = 0 #models.IntegerField(blank=True, null=True)
    maxnoattempts = 0 #models.IntegerField(db_column='maxNoAttempts', blank=True, null=True)  # Field name made lowercase.
    hintavailable =  ""   #models.CharFielddb_column='hintAvailable', max_length=10, blank=True, null=True)  # Field name made lowercase.
    hintused = "" #models.TextField(db_column='hintUsed', blank=True, null=True)  # Field name made lowercase.
    currposition = 0 #models.IntegerField(db_column='currPosition', blank=True, null=True)  # Field name made lowercase.
    oldposition = 0 #models.IntegerField(db_column='oldPosition', blank=True, null=True)  # Field name made lowercase.
    chaptersysname =  ""   #models.CharFielddb_column='chapterSysName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    chaptertitle =  ""   #models.CharFielddb_column='chapterTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    sesssysname =  ""   #models.CharFielddb_column='sessSysName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    sesstitle =  ""   #models.CharFielddb_column='sessTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    modulesysname =  ""   #models.CharFielddb_column='moduleSysName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    moduletitle =  ""   #models.CharFielddb_column='moduleTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    answerchoice = "" #models.TextField(db_column='answerChoice', blank=True, null=True)  # Field name made lowercase.
    success =  ""   #models.CharFieldmax_length=10, blank=True, null=True)
    done =  ""   #models.CharFieldmax_length=6, blank=True, null=True)
    enrolmentmode =  ""   #models.CharFielddb_column='enrolmentMode', max_length=8, blank=True, null=True)  # Field name made lowercase.
    totdurationinsecs = 0 #models.IntegerField(db_column='totDurationInSecs', blank=True, null=True)  # Field name made lowercase.
    eventno =  0    #models.SmallIntegerField(db_column='eventNo', blank=True, null=True)  # Field name made lowercase.
    othertitle =  ""   #models.CharFielddb_column='otherTitle', max_length=50, blank=True, null=True)  # Field name made lowercase.
    othertype =  ""   #models.CharFielddb_column='otherType', max_length=50, blank=True, null=True)  # Field name made lowercase.
    anonymous =  ""   #models.CharFieldmax_length=1, blank=True, null=True)
    anonymoustopeers =  ""   #models.CharFielddb_column='anonymousToPeers', max_length=1, blank=True, null=True)  # Field name made lowercase.
    edulevel =  ""   #models.CharFielddb_column='eduLevel', max_length=30, blank=True, null=True)  # Field name made lowercase.
    gender =  ""   #models.CharFieldmax_length=1, blank=True, null=True)
    commentableid =  ""   #models.CharFielddb_column='commentableId', max_length=50, blank=True, null=True)  # Field name made lowercase.
    commenttype =  ""   #models.CharFielddb_column='commentType', max_length=50, blank=True, null=True)  # Field name made lowercase.
    commentsysid =  ""   #models.CharFielddb_column='commentSysId', max_length=50, blank=True, null=True)  # Field name made lowercase.
    aadhar =  ""   #models.CharFieldmax_length=45, blank=True, null=True)
    problemsubmissiontime = "" #models.DateTimeField(db_column='problemSubmissionTime', blank=True, null=True)  # Field name made lowercase.
    hintmode =  ""   #models.CharFielddb_column='hintMode', max_length=50, blank=True, null=True)  # Field name made lowercase.
    currentseektime = 0.0 #models.FloatField(db_column='current`SeekTime', blank=True, null=True)  # Field name made lowercase.
    querytext = "" #models.TextField(db_column='queryText', blank=True, null=True)  # Field name made lowercase.
    noofresults =  0    #models.SmallIntegerField(db_column='noOfResults', blank=True, null=True)  # Field name made lowercase.
    lastmoddatetime = "" #models.DateTimeField(db_column='lastModDateTime', blank=True, null=True)  # Field name made lowercase.

    

    def save(self):

        
        sql="insert into UserSessionOld (sessionSysName, lmsName, orgName, courseName,courseRun, lmsUserId, userName, \
                    agent, hostName, ipAddress, createDateTime, eventType, eventSource, eventName, \
                    dataSource, oldVideoSpeed, currVideoSpeed, oldVideoTime, currVideoTime,videoNavigType, \
                    oldGrade, currGrade, maxGrade, attempts, maxNoAttempts, hintAvailable,hintUsed, currPosition,oldPosition, \
                    chapterSysName, chapterTitle, sessSysName, sessTitle, moduleSysName, moduleTitle, \
                    answerChoice, success, done, enrolmentMode, totDurationInSecs, eventNo,otherTitle, \
                    otherType, anonymous,anonymousToPeers, \
                    eduLevel, gender,commentableId,commentType, commentSysId,aadhar,problemSubmissionTime, hintMode, \
                    currentSeekTime,queryText,noOfResults,lastModDateTime) \
                    values ('%s','%s','%s','%s','%s',  '%s','%s','%s','%s','%s',  '%s','%s','%s','%s','%s', '%s','%s','%s','%s','%s', \
                         '%s','%s','%s','%s','%s',  '%s','%s','%s','%s','%s',  '%s','%s','%s','%s','%s',  '%s','%s','%s','%s','%s', \
                         '%s','%s','%s','%s','%s',  '%s','%s','%s','%s','%s', '%s','%s','%s','%s','%s', '%s','%s')" % \
                    (self.sessionsysname, self.lmsname, self.orgname, self.coursename, self.courserun, \
                        self.lmsuserid, self.username, self.agent, self.hostname, self.ipaddress, \
                        self.createdatetime, self.eventtype, self.eventsource, self.eventname, self.datasource, \
                        self.oldvideospeed, self.currvideospeed, self.oldvideotime, self.currvideotime, self.videonavigtype, \
                        self.oldgrade, self.currgrade, self.maxgrade, self.attempts, self.maxnoattempts, \
                        self.hintavailable, self.hintused, self.currposition, self.oldposition, self.chaptersysname, \
                        self.chaptertitle, self.sesssysname, self.sesstitle, self.modulesysname, self.moduletitle, \
                        self.answerchoice, self.success, self.done, self.enrolmentmode, self.totdurationinsecs, \
                        self.eventno, self.othertitle, self.othertype, self.anonymous, self.anonymoustopeers, \
                        self.edulevel, self.gender, self.commentableid, self.commenttype, self.commentsysid, \
                        self.aadhar, self.problemsubmissiontime, self.hintmode, self.currentseektime, self.querytext, \
                        self.noofresults, self.lastmoddatetime  )
        

        try:
            cursor.execute(sql)
            db.commit()
        except TypeError, e:
            print e
        except Exception ,err :
            traceback.print_exc()
            db.rollback()



    class Meta:
        managed = True
        db_table = 'UserSessionOld'


class Usersessionoldlog():

    sessionid = 0 #models.BigIntegerField(db_column='sessionId', primary_key=True)  # Field name made lowercase.
    sessionsysname =  ""   #models.CharFielddb_column='sessionSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    lmsname =  ""   #models.CharFielddb_column='lmsName', max_length=6)  # Field name made lowercase.
    orgname =  ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename =  ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    courserun =  ""   #models.CharFielddb_column='courseRun', max_length=150, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', blank=True, null=True)  # Field name made lowercase.
    username =  ""   #models.CharFielddb_column='userName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    agent =  ""   #models.CharFieldmax_length=255, blank=True, null=True)
    hostname =  ""   #models.CharFielddb_column='hostName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    ipaddress =  ""   #models.CharFielddb_column='ipAddress', max_length=50, blank=True, null=True)  # Field name made lowercase.
    createdatetime = "" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    eventtype =  ""   #models.CharFielddb_column='eventType', max_length=100, blank=True, null=True)  # Field name made lowercase.
    eventsource =  ""   #models.CharFielddb_column='eventSource', max_length=50)  # Field name made lowercase.
    eventname =  ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    datasource =  ""   #models.CharFielddb_column='dataSource', max_length=5, blank=True, null=True)  # Field name made lowercase.
    oldvideospeed = 0.0 #models.FloatField(db_column='oldVideoSpeed', blank=True, null=True)  # Field name made lowercase.
    currvideospeed = 0.0 #models.FloatField(db_column='currVideoSpeed', blank=True, null=True)  # Field name made lowercase.
    oldvideotime = 0.0 #models.FloatField(db_column='oldVideoTime', blank=True, null=True)  # Field name made lowercase.
    currvideotime = 0.0 #models.FloatField(db_column='currVideoTime', blank=True, null=True)  # Field name made lowercase.
    videonavigtype =  ""   #models.CharFielddb_column='videoNavigType', max_length=15, blank=True, null=True)  # Field name made lowercase.
    oldgrade = 0.0 #models.FloatField(db_column='oldGrade', blank=True, null=True)  # Field name made lowercase.
    currgrade = 0.0 #models.FloatField(db_column='currGrade', blank=True, null=True)  # Field name made lowercase.
    maxgrade = 0.0 #models.FloatField(db_column='maxGrade', blank=True, null=True)  # Field name made lowercase.
    attempts = 0 #models.IntegerField(blank=True, null=True)
    maxnoattempts = 0 #models.IntegerField(db_column='maxNoAttempts', blank=True, null=True)  # Field name made lowercase.
    hintavailable =  ""   #models.CharFielddb_column='hintAvailable', max_length=10, blank=True, null=True)  # Field name made lowercase.
    hintused = "" #models.TextField(db_column='hintUsed', blank=True, null=True)  # Field name made lowercase.
    currposition = 0 #models.IntegerField(db_column='currPosition', blank=True, null=True)  # Field name made lowercase.
    oldposition = 0 #models.IntegerField(db_column='oldPosition', blank=True, null=True)  # Field name made lowercase.
    chaptersysname =  ""   #models.CharFielddb_column='chapterSysName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    chaptertitle =  ""   #models.CharFielddb_column='chapterTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    sesssysname =  ""   #models.CharFielddb_column='sessSysName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    sesstitle =  ""   #models.CharFielddb_column='sessTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    modulesysname =  ""   #models.CharFielddb_column='moduleSysName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    moduletitle =  ""   #models.CharFielddb_column='moduleTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    answerchoice = "" #models.TextField(db_column='answerChoice', blank=True, null=True)  # Field name made lowercase.
    success =  ""   #models.CharFieldmax_length=10, blank=True, null=True)
    done =  ""   #models.CharFieldmax_length=6, blank=True, null=True)
    enrolmentmode =  ""   #models.CharFielddb_column='enrolmentMode', max_length=8, blank=True, null=True)  # Field name made lowercase.
    totdurationinsecs = 0 #models.IntegerField(db_column='totDurationInSecs', blank=True, null=True)  # Field name made lowercase.
    eventno =  0    #models.SmallIntegerField(db_column='eventNo', blank=True, null=True)  # Field name made lowercase.
    othertitle =  ""   #models.CharFielddb_column='otherTitle', max_length=50, blank=True, null=True)  # Field name made lowercase.
    othertype =  ""   #models.CharFielddb_column='otherType', max_length=50, blank=True, null=True)  # Field name made lowercase.
    anonymous =  ""   #models.CharFieldmax_length=1, blank=True, null=True)
    anonymoustopeers =  ""   #models.CharFielddb_column='anonymousToPeers', max_length=1, blank=True, null=True)  # Field name made lowercase.
    edulevel =  ""   #models.CharFielddb_column='eduLevel', max_length=30, blank=True, null=True)  # Field name made lowercase.
    gender =  ""   #models.CharFieldmax_length=1, blank=True, null=True)
    commentableid =  ""   #models.CharFielddb_column='commentableId', max_length=50, blank=True, null=True)  # Field name made lowercase.
    commenttype =  ""   #models.CharFielddb_column='commentType', max_length=50, blank=True, null=True)  # Field name made lowercase.
    commentsysid =  ""   #models.CharFielddb_column='commentSysId', max_length=50, blank=True, null=True)  # Field name made lowercase.
    aadhar =  ""   #models.CharFieldmax_length=45, blank=True, null=True)
    problemsubmissiontime = "" #models.DateTimeField(db_column='problemSubmissionTime', blank=True, null=True)  # Field name made lowercase.
    hintmode =  ""   #models.CharFielddb_column='hintMode', max_length=50, blank=True, null=True)  # Field name made lowercase.
    currentseektime = 0.0 #models.FloatField(db_column='currentSeekTime', blank=True, null=True)  # Field name made lowercase.
    querytext = "" #models.TextField(db_column='queryText', blank=True, null=True)  # Field name made lowercase.
    noofresults =  0    #models.SmallIntegerField(db_column='noOfResults', blank=True, null=True)  # Field name made lowercase.
    lastmoddatetime = "" #models.DateTimeField(db_column='lastModDateTime', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'UserSessionOldLog'



class AuthGroup():

    name =  ""   #models.CharFieldunique=True, max_length=80)

    class Meta:
        managed = True
        db_table = 'auth_group'

class AuthGroupPermissions():
    
    group = ""#models.ForeignKey(AuthGroup)
    permission = ""#models.ForeignKey('AuthPermission')

    class Meta:
        managed = True
        db_table = 'auth_group_permissions'
        unique_together = ['group', 'permission']


class AuthPermission():
    
    name =  ""   #models.CharFieldmax_length=255)
    content_type = ""#models.ForeignKey('DjangoContentType')
    codename =  ""   #models.CharFieldmax_length=100)

    class Meta:
        managed = True
        db_table = 'auth_permission'
        unique_together = ['content_type', 'codename']


class AuthUser():
    
    password =  ""   #models.CharFieldmax_length=128)
    last_login = "" #models.DateTimeField(blank=True, null=True)
    is_superuser = 0 #models.IntegerField()
    username =  ""   #models.CharFieldunique=True, max_length=30)
    first_name =  ""   #models.CharFieldmax_length=30)
    last_name =  ""   #models.CharFieldmax_length=30)
    email =  ""   #models.CharFieldmax_length=254)
    is_staff = 0 #models.IntegerField()
    is_active = 0 #models.IntegerField()
    date_joined = "" #models.DateTimeField()

    class Meta:
        managed = True
        db_table = 'auth_user'





class AuthUserGroups():
    
    user = ""#models.ForeignKey(AuthUser)
    group = ""#models.ForeignKey(AuthGroup)

    class Meta:
        managed = True
        db_table = 'auth_user_groups'
        unique_together = ['user', 'group']


class AuthUserUserPermissions():

    user = ""#models.ForeignKey(AuthUser)
    permission = ""#models.ForeignKey(AuthPermission)

    class Meta:
        managed = True
        db_table = 'auth_user_user_permissions'
        unique_together = ['user', 'permission']



class DjangoAdminLog():
    
    action_time = "" #models.DateTimeField()
    object_id = "" #models.TextField(blank=True, null=True)
    object_repr =  ""   #models.CharFieldmax_length=200)
    action_flag =  0    #models.SmallIntegerField()
    change_message = "" #models.TextField()
    content_type = ""#models.ForeignKey('DjangoContentType', blank=True, null=True)
    user = ""#models.ForeignKey(AuthUser)

    class Meta:
        managed = True
        db_table = 'django_admin_log'


class DjangoContentType():

    app_label =  ""   #models.CharFieldmax_length=100)
    model =  ""   #models.CharFieldmax_length=100)

    class Meta:
        managed = True
        db_table = 'django_content_type'
        unique_together = ['app_label', 'model']


class DjangoMigrations():

    app =  ""   #models.CharFieldmax_length=255)
    name =  ""   #models.CharFieldmax_length=255)
    applied = "" #models.DateTimeField()

    class Meta:
        managed = True
        db_table = 'django_migrations'


class DjangoSession():

    session_key =  ""   #models.CharFieldprimary_key=True, max_length=40)
    session_data = "" #models.TextField()
    expire_date = "" #models.DateTimeField()

    class Meta:
        managed = True
        db_table = 'django_session'


class Eventcolor():

    eventtypeid =  0    #models.SmallIntegerField(db_column='eventTYpeId', primary_key=True)  # Field name made lowercase.
    eventtype =  ""   #models.CharFielddb_column='eventType', max_length=45, blank=True, null=True)  # Field name made lowercase.
    eventcolor =  ""   #models.CharFielddb_column='eventColor', max_length=45, blank=True, null=True)  # Field name made lowercase.
    colorcode =  ""   #models.CharFielddb_column='colorCode', max_length=45, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'eventColor'


class Eventnotype():

    eventno =  0    #models.SmallIntegerField(db_column='eventNo', blank=True, null=True)  # Field name made lowercase.
    eventtypeid =  0    #models.SmallIntegerField(db_column='eventTypeId', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'eventNoTYpe'


class Myusersession():

    sessionid = 0 #models.BigIntegerField(db_column='sessionId', primary_key=True)  # Field name made lowercase.
    sessionsysname =  ""   #models.CharFielddb_column='sessionSysName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    lmsname =  ""   #models.CharFielddb_column='lmsName', max_length=6)  # Field name made lowercase.
    orgname =  ""   #models.CharFielddb_column='orgName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    coursename =  ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    courserun =  ""   #models.CharFielddb_column='courseRun', max_length=150, blank=True, null=True)  # Field name made lowercase.
    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId', blank=True, null=True)  # Field name made lowercase.
    username =  ""   #models.CharFielddb_column='userName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    agent =  ""   #models.CharFieldmax_length=255, blank=True, null=True)
    hostname =  ""   #models.CharFielddb_column='hostName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    ipaddress =  ""   #models.CharFielddb_column='ipAddress', max_length=50, blank=True, null=True)  # Field name made lowercase.
    createdatetime = "" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    eventtype =  ""   #models.CharFielddb_column='eventType', max_length=100, blank=True, null=True)  # Field name made lowercase.
    eventsource =  ""   #models.CharFielddb_column='eventSource', max_length=50)  # Field name made lowercase.
    eventname =  ""   #models.CharFielddb_column='eventName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    datasource =  ""   #models.CharFielddb_column='dataSource', max_length=5, blank=True, null=True)  # Field name made lowercase.
    oldvideospeed = 0.0 #models.FloatField(db_column='oldVideoSpeed', blank=True, null=True)  # Field name made lowercase.
    currvideospeed = 0.0 #models.FloatField(db_column='currVideoSpeed', blank=True, null=True)  # Field name made lowercase.
    oldvideotime = 0.0 #models.FloatField(db_column='oldVideoTime', blank=True, null=True)  # Field name made lowercase.
    currvideotime = 0.0 #models.FloatField(db_column='currVideoTime', blank=True, null=True)  # Field name made lowercase.
    videonavigtype =  ""   #models.CharFielddb_column='videoNavigType', max_length=15, blank=True, null=True)  # Field name made lowercase.
    oldgrade = 0.0 #models.FloatField(db_column='oldGrade', blank=True, null=True)  # Field name made lowercase.
    currgrade = 0.0 #models.FloatField(db_column='currGrade', blank=True, null=True)  # Field name made lowercase.
    maxgrade = 0.0 #models.FloatField(db_column='maxGrade', blank=True, null=True)  # Field name made lowercase.
    attempts = 0 #models.IntegerField(blank=True, null=True)
    maxnoattempts = 0 #models.IntegerField(db_column='maxNoAttempts', blank=True, null=True)  # Field name made lowercase.
    hintavailable =  ""   #models.CharFielddb_column='hintAvailable', max_length=10, blank=True, null=True)  # Field name made lowercase.
    hintused =  ""   #models.CharFielddb_column='hintUsed', max_length=10, blank=True, null=True)  # Field name made lowercase.
    currposition = 0 #models.IntegerField(db_column='currPosition', blank=True, null=True)  # Field name made lowercase.
    oldposition = 0 #models.IntegerField(db_column='oldPosition', blank=True, null=True)  # Field name made lowercase.
    chaptersysname =  ""   #models.CharFielddb_column='chapterSysName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    chaptertitle =  ""   #models.CharFielddb_column='chapterTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    sesssysname =  ""   #models.CharFielddb_column='sessSysName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    sesstitle =  ""   #models.CharFielddb_column='sessTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    modulesysname =  ""   #models.CharFielddb_column='moduleSysName', max_length=45, blank=True, null=True)  # Field name made lowercase.
    moduletitle =  ""   #models.CharFielddb_column='moduleTitle', max_length=255, blank=True, null=True)  # Field name made lowercase.
    answerchoice =  ""   #models.CharFielddb_column='answerChoice', max_length=10, blank=True, null=True)  # Field name made lowercase.
    success =  ""   #models.CharFieldmax_length=10, blank=True, null=True)
    done =  ""   #models.CharFieldmax_length=6, blank=True, null=True)
    enrolmentmode =  ""   #models.CharFielddb_column='enrolmentMode', max_length=8, blank=True, null=True)  # Field name made lowercase.
    totdurationinsecs = 0 #models.IntegerField(db_column='totDurationInSecs', blank=True, null=True)  # Field name made lowercase.
    eventno =  0    #models.SmallIntegerField(db_column='eventNo', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'myUserSession'


class Tmpcoursetable():

    lmsuserid = 0 #models.BigIntegerField(db_column='lmsUserId')  # Field name made lowercase.
    coursename =  ""   #models.CharFielddb_column='courseName', max_length=50, blank=True, null=True)  # Field name made lowercase.
    createdatetime = "" #models.DateTimeField(db_column='createDateTime', blank=True, null=True)  # Field name made lowercase.
    moddatetime = "" #models.DateTimeField(db_column='modDateTime', blank=True, null=True)  # Field name made lowercase.
    modulesysname =  ""   #models.CharFielddb_column='moduleSysName', max_length=50)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'tmpCourseTable'


class Tmpeventdescrip():

    eventtype =  ""   #models.CharFielddb_column='eventType', primary_key=True, max_length=255)  # Field name made lowercase.
    eventstring = "" #models.TextField(db_column='eventString', blank=True, null=True)  # Field name made lowercase.
    logdirname =  ""   #models.CharFielddb_column='logdirName', max_length=200, blank=True, null=True)  # Field name made lowercase.
    logfilename =  ""   #models.CharFielddb_column='logfileName', max_length=200, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'tmpEventDescrip'


class Tmpprobinteract():

    eventid = 0 #models.BigIntegerField(db_column='eventId', primary_key=True)  # Field name made lowercase.
    usrid = 0 #models.BigIntegerField(db_column='usrId', blank=True, null=True)  # Field name made lowercase.
    problemid = 0 #models.BigIntegerField(db_column='problemId', blank=True, null=True)  # Field name made lowercase.
    newscore = 0 #models.IntegerField(db_column='newScore', blank=True, null=True)  # Field name made lowercase.
    maxgrade = 0 #models.IntegerField(db_column='maxGrade', blank=True, null=True)  # Field name made lowercase.
    attempts = 0 #models.IntegerField(blank=True, null=True)
    success =  ""   #models.CharFieldmax_length=1, blank=True, null=True)
    done =  ""   #models.CharFieldmax_length=1, blank=True, null=True)

    class Meta:
        managed = True
        db_table = 'tmpProbInteract'


class Tmpproblem():

    problemid = 0 #models.BigIntegerField(db_column='problemId', primary_key=True)  # Field name made lowercase.
    quiztitle = "" #models.TextField(db_column='quizTitle', blank=True, null=True)  # Field name made lowercase.
    noofattemptsallowed = 0 #models.IntegerField(db_column='noOfAttemptsAllowed', blank=True, null=True)  # Field name made lowercase.
    quizmaxmarks = 0.0 #models.FloatField(db_column='quizMaxMarks', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'tmpProblem'


class Tmptime():

    idtmptime = 0 #models.AutoField(db_column='idTmpTime', primary_key=True)  # Field name made lowercase.
    startdatetime = "" #models.DateTimeField(db_column='startDateTime', blank=True, null=True)  # Field name made lowercase.
    enddatetime = "" #models.DateTimeField(db_column='endDateTime', blank=True, null=True)  # Field name made lowercase.
    tottimespent = 0 #models.IntegerField(db_column='totTimeSpent', blank=True, null=True)  # Field name made lowercase.
    strtimespent =  ""   #models.CharFielddb_column='strTimeSpent', max_length=45, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'tmpTime'
