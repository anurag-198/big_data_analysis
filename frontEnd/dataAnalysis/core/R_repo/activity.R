#args
myArgs <- commandArgs(TRUE)

#library
library(RMySQL)
library(googleVis)

#conection
con=dbConnect(MySQL(),user='root',password='root123',dbname='analytics',host='localhost')
on.exit(dbDisconnect(con))



courseName=as.character(myArgs[1])
htmlFileName =as.character(myArgs[2])
tableFileName=as.character(myArgs[3])

print(courseName)
#begin
qry1=paste("SELECT date,count FROM course_enrollment_mode_daily WHERE (course_id='", courseName, "') AND mode='honor'",sep="")
qu1_data=dbSendQuery(con,qry1)
#dataframe
dat1=fetch(qu1_data,n=-1)

#graph
# SteppedArea2 = gvisSteppedAreaChart(dat1,xvar="date",yvar="cumulative_count",options=list(height="automatic", width="automatic",title = "Student enrollment graph",
#                                                                                           hAxis="{title: 'Date', titleTextStyle: {color: '#FF0000'}}",
#                                                                                                 vAxis="{title: 'No. of students', titleTextStyle: {color: '#FF0000'}}"))



CC<-gvisSteppedAreaChart(dat1,xvar = "date", yvar="count",options=list(vAxis = 
                                                                                    "{title: 'No. of Enrolled Students', titleTextStyle: {color: '#FF0000'}}",
                                                                                  hAxis="{title: 'Date', titleTextStyle: {color: '#FF0000'}}",
                                                                                  title = "Date-wise Analysis of Enrolled Students", height = "400", width="600"))
                                                                       
                                                                                
#plot(SteppedArea2)
plot(CC)
#saving

Tab <- gvisTable(dat1, options=list(width=280, height=460,title="Tabular representation of enrollment"))
plot(Tab)
cat(CC$html$chart, file=htmlFileName)
cat(Tab$html$chart, file=tableFileName)

