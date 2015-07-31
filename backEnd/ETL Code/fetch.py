import MySQLdb
import exceptions
import traceback
import sys
db=MySQLdb.connect("localhost","root","sysadmin","IITBxDataAnalytics")
cursor=db.cursor()

def ChapterSess(sessionsysname, coursename):
        
    sql = "SELECT c.chapteTitle, s.sessionTitle, c.chapterSysName \
          FROM CourseChapter c join CourseChapterSession s\
          on c.courseName = s.courseName and c.chapterSysName = s.chapterSysName \
          where s.sessionSysName = '%s'  and c.courseName = '%s'"\
          % (sessionsysname, coursename)
    #print sql   
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()

def videoQuery(coursename, videosysname):
        
    sql = "SELECT v.videoTitle, c.chapteTitle FROM CourseVideos v,CourseChapter c where\
          v.courseName = c.courseName and v.chapterSysName = c.chapterSysName\
          and v.courseName = '%s' and v.videoSysName = '%s'"\
          %(coursename, videosysname)
    #print sql
    
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()

def CourseChapterSess(courseName,moduleSysName) :
    sql = "SELECT sessionSysName from CourseChapterSession where chapterSysName = '%s' and courseName = '%s'"\
          % (courseName, moduleSysName)
    
    #print sql
     
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()

   
def Chapter(coursename,chaptersysname) :
    sql = "SELECT chapteTitle FROM CourseChapter where courseName = '%s' and chapterSysName = '%s'"\
          % (coursename, chaptersysname)
    #print sql   
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()
                    
def Coursediscussions(discussionsysname, coursename):
    discussionsysname=str(discussionsysname)
    coursename=str(coursename)
    
    sql = "SELECT chapterSysName, discussionTitle from CourseDiscussions where discussionSysName = '%s' and courseName = '%s'"\
          % (discussionsysname, coursename)
    #print sql
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()

  
def Discuss(discussionsysid, coursename):
    sql = "SELECT discussionTitle FROM CourseDiscussions where discussionSysId = '%s' and courseName = '%s'"\
          % (discussionsysid, coursename)
    #print sql 
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()
            


def Comments(commentsysid, coursename):
    sql = "SELECT commentableSysId, commentType from CourseForums where commentSysId = '%s' and courseName = '%s' "% (commentsysid,coursename)
    #print sql 
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()


def CourseForums(commentsysid):
    sql = "SELECT lmsName,orgName,courseName from CourseForums where commentSysId = '%s'"\
          % (commentsysid)
    #print sql 
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()

def CourseVertical(verticalsysname, coursename):
    sql = "SELECT sessionSysName from CourseVertical where verticalSysName = '%s' and courseName = '%s'"\
          % (verticalsysname, coursename)
    #print sql 
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()

def User(username) :
    sql = "SELECT lmsUserId FROM User where name = '%s'"\
          % (username)
    #print sql 
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()
            
def Problem(quizSysName,courseName):
    sql="SELECT chapterSysName,quizTitle FROM CourseProblems where quizSysName = '%s' and courseName = '%s'" % (quizSysName,courseName)
    #print sql 
    try:
        cursor.execute(sql)
        results = cursor.fetchall()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()