#args
myArgs <- commandArgs(TRUE)

#library
library(RMySQL)
library(googleVis)

#conection
con = dbConnect(MySQL(),user="root",password="root123",dbname="analytics",host="localhost")
on.exit(dbDisconnect(con))



courseName=as.character(myArgs[1]) #"CS101.1x"
moduleName= as.character(myArgs[2])#"Quiz 1"
print(moduleName)
variant=2
htmlFileName =as.character(myArgs[3])#"ans_dist.html"
tableFileName=as.character(myArgs[4])#"ans_dist_table.html"

#begin
qry=paste("SELECT part_id,last_response_count,correct FROM analytics.answer_distribution WHERE (course_id='", courseName, "') AND(module_id='", moduleName, "') AND (variant='", variant, "') ORDER BY part_id ",sep="")
qu_data=dbSendQuery(con,qry)
#dataframe
dat=fetch(qu_data,n=-1)
dat1<- subset(dat,correct==1)
dat2<- subset(dat,correct==0)

#merge
dat1$incorrect<-dat2$last_response_count
dat1$correct<-NULL
names(dat1)<-c("Problem","Correct","Incorrect")

CC<-gvisColumnChart(dat1,xvar="Problem",yvar=c("Correct","Incorrect"),options=list(isStacked=TRUE))

#plot(steppedBar)


Tab <- gvisTable(dat1, options=list(width=300, height=400,title="Performance Table "))
plot(Tab)
cat(CC$html$chart, file=htmlFileName)
cat(Tab$html$chart, file=tableFileName)



