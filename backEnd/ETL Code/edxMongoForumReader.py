import pymongo
import classes
import traceback
import exceptions

from dataloader import _decode_dict , _decode_list

class MongoForumReader(object):
    #code
    
    def __init__(self):
        self.serialVersionUID = 1260045197860625975L
        self.mongoClient=pymongo.MongoClient("mongodb://localhost:27017/")
        self.mongoDBName="cs_comments_service"
        self.db=mongoClient[mongoDbName]
        self.edxCollection=db["contents"]
        self.courseName = ""
        self.lmsName = "EDX"
        self.chapterSysName=""
        self.quizType=""
        self.showAnswer=""
        self.correctChoice=-1
        self.orgName=""
        self.courseTitle=""
        self.language=""
        self.currencyCode=""
        self.courseRun = None
        self.currCourseConcept=""
        self.prevCourseConcept=""
        self.currChapterConcept=""
        self.chapterTitle=""
        self.startDtStr=""
        self.endDtStr=""

    
    def processContents(self) :

        endDate =None
        startDate = None
        tmpStr=[]
        tmpStrArr=[]
        isoDateFormatStr = "EEE, MMM dd HH:mm:ss IST yyyy"
        
        forumContentQry = {}
        forumContentQry["_id.category"]="course"
        # ask they have not used query
        forumCursor = edxCollection.find(forumContentQry)


        try :
            for forumDO in forumCursor :
                courseForums = classes.Courseforums()
                courseForums.lmsname=self.lmsName
                forumDO=_decode_dict(forumDO)
                tmpObj =forumDO.get("_id")
                courseForums.commentsysid=str(tmpObj)
                courseForums.commenttype= str(forumDO.get("_type"))

                if(bool(forumDO.get("anonymous")) == False):
                    courseForums.anonymousmode="N"
                
        
                if (forumDO.get("author_id")!=None):
                    courseForums.lmsauthorid=long(forumDO.get("author_id"))
                
                
                if (forumDO.get("author_username")!=None):
                    courseForums.lmsauthorname=forumDO.get("author_username")
               
                    
                if (forumDO.get("closed")!=None):
                    courseForums.closed=bool(forumDO.get("closed"))
                
                if (forumDO.get("comment_count")!=None):
                    courseForums.commentCount=int(forumDO.get("comment_count"))

                if (forumDO.get("commentable_id")!=None):
                    courseForums.commentablesysid=forumDO.get("comment_id")


                
                tmpStrArr = forumDO.get("course_id").split("/")
                courseForums.orgname=tmpStrArr[0]
                courseForums.coursename=tmpStrArr[1]
                courseForums.courserun=tmpStrArr[2]
                
                if (forumDO.get("endorsed")!=None):
                    courseForums.endorsed= bool( forumDO.get("endorsed"))


                if (forumDO.get("created_at")!=None):
                    courseForums.createdatetime=forumDO.get("created_at")
                
                
                if (forumDO.get("last_activity_at")!=None) :
                    courseForums.lastmoddatetime=forumDO.get("last_activity_at")
                
                if (forumDO.get("thread_type")!=None):
                    courseForums.threadtype=forumDO.get("thread_type")
                
                if(forumDO.get("title")):
                    courseForums.title=forumDO.get("title")
                
                if(forumDO.get("visible")!=None):
                    courseForums.visible=bool(forumDO.get("visible"))


                if(forumDO.get("votes.count")!=None):
                    courseForums.totvotecount =int(forumDO.get("votes.count"))
                    
                if(forumDO.get("votes.up_count")!=None):
                    courseForums.upvotecount =int(forumDO.get("votes.up_count"))
                
                courseForums.save()
        except Exception ,err :
            traceback.print_exc()

if __name__ == '__main__':
    reader=MongoForumReader()
    reader.processContents()