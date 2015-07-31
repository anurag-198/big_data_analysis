myArgs <- commandArgs(TRUE)
# Rscript ggUserNavig.R images/image1.png images/out1.txt images/userNavig.html downloads CS101.1x 2015-01-14 2015-01-20 desc 100

# myArgs[0] - absolute path of R script with fileName
# myArgs[1] - absolute path of R object path
# myArgs[2] - image file name
# myArgs[3] - text filename
# myArgs[4] - downloadpath
# myArgs[5] - courseName
# myArgs[6] - startDate
# myArgs[7] - endDate 
# myArgs[8] - asc/desc 
# myArgs[9]--no.Of Students
library(RMySQL) 
library(googleVis)

courseName <- myArgs[1]
htmlFileName <- as.character(myArgs[2])
excelFileName <- as.character(myArgs[3])
#reportDate <- myArgs[4]

con2<- dbConnect(MySQL(), user="root" , password="root123", dbname="analytics" , host="localhost")
finalPlot <- ''
plotStartFlag <- FALSE
	#qryStr <- paste("SELECT userName, totDurationInSecs,time_to_sec(createDateTime) TimeInSecinDay,distinct(eventType),eventNo",
		#		" FROM UserNavig where courseName='", courseName, "' and date(createDateTime) = '",
			#			reportDate, "'", sep ="")
qryStr <- paste("SELECT userName, eventType, totDurationInSecs, TimeInSecinDay, eventNo ",
								" FROM analytics.UserNavig order by TimeInSecinDay ", sep ="")
	print(qryStr)
		navigData <- dbGetQuery(con2, qryStr)
print(navigData)


	B <- gvisBubbleChart(navigData, idvar="userName", 
				                      xvar="TimeInSecinDay", yvar="eventNo",
				                     colorvar="eventType", sizevar="totDurationInSecs",
				                    options=list(title="User Navigation Chart",width=700, height=450,
 hAxis="{title: 'Time in Secs In Day',minValue: 22600, maxValue:37000}",
					 vAxis="{title: '40 : Courses, 80 : Videos, 120 : Problems, 160 : Navigation, 200 : Discussions'}"
				                     
					))
	cat(B$html$chart, file=htmlFileName)
	T <- gvisTable(navigData, options=list(width="450", height="500"))
	cat(T$html$chart,file=excelFileName)
	#M <- gvisMotionChart(navigData, idvar = "userName", timevar = "TimeInSecinDay")
		#		                  yvar="eventNo", colorvar="eventType", sizevar="totDurationInSecs")

#s	cat(M$html$chart, file="test.html")
#}
dbDisconnect(con2)

