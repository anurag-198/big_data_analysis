myArgs <- commandArgs(TRUE)
library(RMySQL)
library(googleVis)
con = dbConnect(MySQL(),user="root",password="root123",dbname="analytics",host="localhost")
on.exit(dbDisconnect(con))
courseName=as.character(myArgs[1])
videoSysId=as.character(myArgs[2])
htmlFileName =as.character(myArgs[3])
tableFileName=as.character(myArgs[4])

print(courseName)
print(videoSysId)

qry=paste("SELECT courseName,videoSysId,videoFrame,SUM(count) AS sum_count,SUM(timeSpent) AS sum_timeSpent FROM analytics.video_difficulty_analytics WHERE (courseName='", courseName, "') AND (videoSysId='", videoSysId, "') GROUP BY courseName,videoSysId,videoFrame ORDER BY videoFrame",sep="")
qu_data=dbSendQuery(con,qry)
dat=fetch(qu_data,n=-1)
Combo <- gvisComboChart(dat, xvar="videoFrame",
                        yvar=c("sum_count", "sum_timeSpent"),
                        options=list(seriesType="steppedArea",
                                     series='{1: {type:"line",targetAxisIndex: 1}}',
                                     height="500"))
plot(Combo)

