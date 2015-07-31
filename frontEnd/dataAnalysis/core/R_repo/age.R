#coursename htmlFileName excelFile
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
qry=paste("select (YEAR(CURDATE())-birth_year)Age,(count)Num_OF_Stud from analytics.course_enrollment_birth_year_daily where (course_id='", courseName, "') and  birth_year != 0 and date =(select max(date) from analytics.course_enrollment_birth_year_daily where (course_id='", courseName, "')) ",sep="")
rs = dbSendQuery(mydb,qry)
data = fetch(rs, n=-1)
FRB <- gvisColumnChart(data,xvar = "Age", yvar="Num_OF_Stud",options=list(gvis.editor="Edit Chart!",vAxis = 
							"{title: 'No. of Students', titleTextStyle: {color: '#FF0000'}}",
								hAxis="{title: 'Age', titleTextStyle: {color: '#FF0000'}}",
								title = "Age Wise Analysis", height = "450", width="790"))
data$percentage = data$Num_OF_Stud/sum(data$Num_OF_Stud)*100
T <- gvisTable(data,options=list(width=300, height=460))
#GT <- gvisMerge(FRB,T, horizontal=TRUE)
cat(FRB$html$chart, file=htmlFileName)
cat(T$html$chart,file=excelFileName)


