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
qry=paste("select (state)State,(count)Number_of_Students from analytics.course_enrollment_location_current_state where (course_id='", courseName, "') and date=(select max(date) from analytics.course_enrollment_location_current_state where (course_id='", courseName, "'))",sep="")
rs = dbSendQuery(mydb,qry)
data = fetch(rs, n=-1)
FRB <- gvisGeoChart(data, locationvar="State",colorvar="Number_of_Students",
				options=list(gvis.editor="Edit Chart!",region="IN", displayMode="region", #datalessRegionColor= '#f8bbd0',
				colorAxis="{colors:['#33CCFF','#C71585']}",
				resolution="provinces",width=600, height=400))
plot(FRB)
data$percentage = data$Number_of_Students/sum(data$Number_of_Students)*100
T <- gvisTable(data,options=list(width=250, height=400))
#GT <- gvisMerge(FRB,T, horizontal=TRUE)s
cat(FRB$html$chart, file=htmlFileName)
cat(T$html$chart,file=excelFileName)


