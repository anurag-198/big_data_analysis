myArgs <- commandArgs(TRUE)
library(RMySQL)
library(googleVis)
mydb=dbConnect(MySQL(),user='root',password='root123',dbname='analytics',host='localhost')
courseName <- as.character(myArgs[1])
print(courseName)
htmlFileName <- as.character(myArgs[2])
excelFileName <- as.character(myArgs[3])
mode1="ACTIVE"
mode2="PLAYED-VIDEO"
mode3="ATTEMPTED-PROBLEM"
print(htmlFileName)
qry=paste("select distinct t.interval_end,IFNULL((select count from course_activity where label='active' and (course_id='", courseName, "')
and interval_end=t.interval_end),0) as ACTIVE_COUNT, IFNULL((select count from course_activity where label='played-video' and (course_id='", courseName, "')
and interval_end=t.interval_end),0) as VIDEO_COUNT, IFNULL((select count from course_activity where label='attempted-problem' and (course_id='", courseName, "')
and interval_end=t.interval_end),0) as PROBLEM_COUNT,IFNULL((select count from course_activity where label='posted-forum' and (course_id='", courseName, "')
and interval_end=t.interval_end),0) as FORUM_COUNT from course_activity as t",sep="")
rs = dbSendQuery(mydb,qry)
data = fetch(rs, n=-1)
cols <- c("End Day Of Week","No: Of Active Users","N0: Of Users Watched a Video","No: Of Students Attempted A Problem","No: Of Students Active On Forum")
colnames(data) <- cols
FRB=gvisLineChart(data,options=list(gvis.editor="Edit Chart!",vAxis = 
							"{title: 'No. of Students', titleTextStyle: {color: '#FF0000'}}",
								hAxis="{title: 'How Students Interact With the Course', titleTextStyle: {color: '#FF0000'}}",
								title = "Interaction Of Students With The Course", height = "460", width="700"))
T <- gvisTable(data, options=list(width="450", height="500"))
cat(FRB$html$chart, file=htmlFileName)
cat(T$html$chart,file=excelFileName)








