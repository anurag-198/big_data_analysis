#args
myArgs <- commandArgs(TRUE)

#library
library(RMySQL)
library(googleVis)

#conection
con = dbConnect(MySQL(),user="root",password="root123",dbname="analytics",host="localhost")
on.exit(dbDisconnect(con))

#cmd_line variables
courseName=as.character(myArgs[1])
videoSysId=as.character(myArgs[2])
htmlFileName =as.character(myArgs[3])
tableFileName=as.character(myArgs[4])

print(courseName)
print(videoSysId)

#qry
qry=paste("SELECT courseName,videoTitle,videoFrame,SUM(count) AS No_Of_Video_Accesses,SUM(timeSpent) AS Time_Spent FROM analytics.video_difficulty_analytics WHERE (courseName='", courseName, "') AND (videoTitle='", videoSysId, "') GROUP BY courseName,videoTitle,videoFrame ORDER BY videoFrame",sep="")
print(qry)
qu_data=dbSendQuery(con,qry)
#fetch
dat=fetch(qu_data,n=-1)
print(dat)
#graph
Combo <- gvisComboChart(dat, xvar="videoFrame",
                        yvar=c("No_Of_Video_Accesses", "Time_Spent"),
                        options=list(seriesType="steppedArea",
                                     series='{1: {type:"line",targetAxisIndex: 1}}',
				  #  vAxis="{0:{title:'Number of Video Accesses'} 1:{title:'Time'}}"
                                    hAxis="{title: 'Video Frames', titleTextStyle: {color: '#FF0000'}}",
								title = "Video Difficulty Analysis", height = "450", width="790"))
T <- gvisTable(dat,options=list(width=300, height=700))
#GT <- gvisMerge(FRB,T, horizontal=TRUE)
cat(Combo$html$chart, file=htmlFileName)
cat(T$html$chart,file=tableFileName)



