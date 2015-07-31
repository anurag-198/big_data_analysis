myArgs <- commandArgs(TRUE)
library(RMySQL)
library(googleVis)
con = dbConnect(MySQL(),user="root",password="root123",dbname="analytics",host="localhost")
on.exit(dbDisconnect(con))



courseName=as.character(myArgs[1])
htmlFileName =as.character(myArgs[2])
tableFileName=as.character(myArgs[3])

print(courseName)
qry1=paste("SELECT t1.date,IFNULL(t1.count,0) AS honor_count,IFNULL(t2.count,0) AS verified_count 
FROM (SELECT date,count,mode FROM course_enrollment_mode_daily where mode='honor' AND (course_id='", courseName, "')) AS t1
LEFT OUTER JOIN (SELECT date,count,mode FROM course_enrollment_mode_daily where mode='verified' AND (course_id='", courseName, "')) AS t2
ON t1.date=t2.date
UNION
SELECT t1.date,IFNULL(t1.count,0) AS honor_count,IFNULL(t2.count,0) AS verified_count 
FROM (SELECT date,count,mode FROM course_enrollment_mode_daily where mode='honor'AND (course_id='", courseName, "')) AS t1
RIGHT OUTER JOIN (SELECT date,count,mode FROM course_enrollment_mode_daily where mode='verified'AND (course_id='", courseName, "')) AS t2
ON t1.date=t2.date
",sep="")
qu1_data=dbSendQuery(con,qry1)
dat1=fetch(qu1_data,n=-1)

CC<-SteppedArea <- gvisSteppedAreaChart(dat1,xvar = "date", yvar=c("honor_count","verified_count"),options=list(vAxis = 
                                                                                                   "{title: 'No. of enrolled students', titleTextStyle: {color: '#FF0000'}}",
                                                                                                 hAxis="{title: 'Date', titleTextStyle: {color: '#FF0000'}}",
                                                                                                 title = "Date-Wise enrollment Analysis", height = "470", width="750",isStacked=TRUE))                                                                    
Tab <- gvisTable(dat1, options=list(width=300, height=400,title="Tabular representation of enrollment"))
cat(CC$html$chart, file=htmlFileName)
cat(Tab$html$chart, file=tableFileName)

