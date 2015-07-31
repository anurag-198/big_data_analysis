import MySQLdb
import exceptions
import traceback
import sys
import os



#from dataloader import *

db=MySQLdb.connect("localhost","root","1234","IITBxDataAnalytics")
cursor=db.cursor()


def psVideo(courseName,videoSysName) :
    sql="SELECT v.videoTitle, v.chapterSysName,c.chapteTitle \
            FROM CourseVideos v, CourseChapter c where v.courseName = c.courseName and\
                v.chapterSysName = c.chapterSysName and v.courseName = '%s' and v.videoSysName = '%s'" %(courseName,videoSysName)
    
    try:
        cursor.execute(sql)
        results = cursor.fetchone()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()
            

def psChapterSess(courseName,sessionSysName):
    sql="SELECT c.chapteTitle, s.sessionTitle, c.chapterSysName \
        FROM CourseChapter c join CourseChapterSession s \
        on c.courseName = s.courseName and c.chapterSysName = s.chapterSysName \
        where  c.courseName = '%s' and s.sessionSysName = '%s' " % (courseName,sessionSysName)
    
    try:
        cursor.execute(sql)
        results = cursor.fetchone()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()
            

def psProblem(courseName,quizSysName):
    sql="SELECT p.chapterSysName,c.chapteTitle, p.quizTitle FROM CourseProblems p\
        join CourseChapter c on p.chapterSysName = c.chapterSysName \
        where p.courseName = '%s' and quizSysName = '%s'" % (courseName,quizSysName)
    
    try:
        cursor.execute(sql)
        results = cursor.fetchone()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()


def psChapter(courseName,chapterSysName):
    sql="SELECT chapteTitle FROM CourseChapter where courseName = '%s' and chapterSysName = '%s'" % (courseName,chapterSysName)
    
    try:
        cursor.execute(sql)
        results = cursor.fetchone()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()

def psDiscuss(discussionSysId,courseName):
    sql="SELECT discussionTitle FROM CourseDiscussions where discussionSysId = '%s'\
        and courseName = '%s'" % (discussionSysId,courseName)
    
    try:
        cursor.execute(sql)
        results = cursor.fetchone()
        return results
    except Exception ,err :
            traceback.print_exc()
            db.rollback()



def psVideoInteract(sessionsysname,lmsname,orgname,coursename,courserun,lmsuserid,\
                    eventname,eventno,videosysname,videotitle,chaptersysname,chapterTitle,\
                    oldSeekTime,currseektime,videonavigtype,oldspeed,currspeed,\
                    source,createdatetime,lastmoddatetime):
    sql = "Insert into EventvideoInteract (sessionsysName,lmsName, orgName, courseName,  courseRun,lmuserId,\
                eventName,eventNo, videoSysName,    videoTitle,chapterSysName,chapterTitle,oldSeekTime, \
                currseekTime,   videoNavigType,oldSpeed,currSpeed,source,createDateTime,   lastModDateTime)\
                values ('%d','%s','%s','%s','%s',  '%s','%d','%s','%d','%s',   '%s', '%s','%s','%f','%f',  '%s','%f','%f',\
                '%s','%s',  '%s')" %\
                (sessionsysname,lmsname,orgname,coursename,courserun,lmsuserid,\
                    eventname,eventno,videosysname,videotitle,chaptersysname,chapterTitle,\
                    oldSeekTime,currseektime,videonavigtype,oldspeed,currspeed,\
                    source,createdatetime,lastmoddatetime)
    

    try:
        cursor.execute(sql)
    except Exception ,err :
            traceback.print_exc()
            db.rollback()
    


def psCourseInteract(lmsname,orgname,coursename,courserun,lmuserid,\
                    eventname,eventno,moduletype,modulesysname,moduletitle,\
                    chaptersysname,chaptertitle,createdatetime,moddatetime,\
                    oldposition,curposition,source):
    
    sql = "Insert into EventCourseInteract (lmsName, orgName,courseName,courseRun,lmUserId,\
                 eventName,eventNo,moduleType,modulesysName,moduleTitle,chapterSysName,chapterTitle,\
                 createdDateTime,moddateTime,oldPosition,curPosition,source )\
                VALUES ('%d','%s','%s','%s','%s',  '%d','%s','%d','%s','%s', '%s','%s','%s','%s','%s', '%d','%d','%s')" %\
                (lmsname,orgname,coursename,courserun,lmuserid,\
                    eventname,eventno,moduletype,modulesysname,moduletitle,\
                    chaptersysname,chaptertitle,createdatetime,moddatetime,\
                    oldposition,curposition,source)
    

    try:
        cursor.execute(sql)
    except Exception ,err :
            traceback.print_exc()
            db.rollback()




def psProbInteract(lmsname,orgname,coursename,lmsuserid,eventname,eventno,\
                   quizzsysname,quiztitle,chaptersysname,chaptertitle,hintavailable,\
                   hintmode,inputtype,responsetype,variantid,oldscore,newscore,\
                   maxgrade,attempts,maxattempts,choice,success,source,\
                   probsubtime,done,createdatetime,lastmoddatetime,courserun) :
    
    sql = "Insert into EventprobInteract (lmsName, orgName,courseName,lmsuserId,   eventName,\
                eventNo, quizzSysName,quizTitle,chapterSysName,  chapterTitle,hintAvailable, \
                hintMode,inputType,responseType,  variantId,oldscore,newscore,maxGrade,attempts ,\
                maxAttempts,choice, success, source ,probSubTime,    done,createDateTime,\
                lastModDatetime,courseRun)\
                VALUES ('%d','%s','%s','%s','%d',    '%s','%d','%s','%s','%s',   '%s','%s','%s','%s','%s',  '%s','%f','%f',\
                '%f','%d',   '%d','%s','%s','%s','%s',    '%s','%s','%s','%s',)" %\
                 (lmsname,orgname,coursename,lmsuserid,eventname,eventno,\
                   quizzsysname,quiztitle,chaptersysname,chaptertitle,hintavailable,\
                   hintmode,inputtype,responsetype,variantid,oldscore,newscore,\
                   maxgrade,attempts,maxattempts,choice,success,source,\
                   probsubtime,done,createdatetime,lastmoddatetime,courserun) 

    

    try:
        cursor.execute(sql)
    except Exception ,err :
            traceback.print_exc()
            db.rollback()









