myArgs <- commandArgs(TRUE)
library(RMySQL)
library(googleVis)
mydb=dbConnect(MySQL(),user='root',password='root123',dbname='analytics',host='localhost')
courseName <- as.character(myArgs[1])
print(courseName)
htmlFileName <- as.character(myArgs[2])
excelFileName <- as.character(myArgs[3])
print(date)
print(htmlFileName)
qry=paste("select (gender)Gender,(count)Num_OF_Stud from analytics.course_enrollment_gender_daily where (course_id='", courseName, "') and date=(select max(date) from analytics.course_enrollment_gender_daily)",sep="");
rs = dbSendQuery(mydb,qry)
data = fetch(rs, n=-1)
data[is.na(data)] <- "Not Specified"
qry=paste("select date,(gender)Gender,(count)Num_OF_Stud from analytics.course_enrollment_gender_daily where (course_id='", courseName, "')",sep="");
rs=dbSendQuery(mydb,qry)
data2=fetch(rs,n=-1)
data2[is.na(data2)] <- "Not Specified"
FRB <- gvisColumnChart(data,xvar = "Gender", yvar="Num_OF_Stud",options=list(gvis.editor="Edit Chart!",vAxis = 
							"{title: 'No. of Students', titleTextStyle: {color: '#FF0000'}}",
								hAxis="{title: 'Gender Of Students', titleTextStyle: {color: '#FF0000'}}",
								title = "Gender-Wise Analysis", height = "500", width="400"))
 T <- gvisTable(data2, options=list(width="450", height="500"))
#GT <- gvisMerge(FRB,T, horizontal=TRUE)
cat(FRB$html$chart, file=htmlFileName)
cat(T$html$chart,file=excelFileName)

