myArgs <- commandArgs(TRUE)
library(RMySQL)
library(googleVis)
mydb=dbConnect(MySQL(),user='root',password='root123',dbname='analytics',host='localhost')
courseName <- as.character(myArgs[1])
print(courseName)
htmlFileName <- as.character(myArgs[2])
tableFileName <- as.character(myArgs[3])
print(htmlFileName)
qry=paste("select (education_level)Quali,(count)Num_OF_Stud from analytics.course_enrollment_education_level_daily where (course_id='", courseName, "') and date=(select max(date) from analytics.course_enrollment_education_level_daily where (course_id='", courseName, "'))",sep="")
rs = dbSendQuery(mydb,qry)
data = fetch(rs, n=-1)
data[is.na(data)] <- "Not Specified"
data$Percentage = data$Num_OF_Stud/sum(data$Num_OF_Stud)*100
FRB <- gvisColumnChart(data,xvar = "Quali", yvar="Percentage",options=list(gvis.editor="Edit Chart!",vAxis = 
							"{title: 'No. of Students', titleTextStyle: {color: '#FF0000'}}",
								hAxis="{title: 'Education Level Of Students', titleTextStyle: {color: '#FF0000'}}",
								title = "Education Level-Wise Analysis", height = "450", width="790"))
 T <- gvisTable(data, options=list(width=350, height=800))
#GT <- gvisMerge(FRB,T, horizontal=TRUE)
cat(FRB$html$chart, file=htmlFileName)
cat(T$html$chart, file=tableFileName)
