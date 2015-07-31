myArgs <- commandArgs(TRUE)
# Rscript ggRegistrationList.R images/img1.png  downloads/html1.html  downloads/excel1.xls 1 CS101.1x 0 1 TR
# Rscript ggRegistrationList.R images/img1.png  downloads/html1.html  downloads/excel1.xls 1 CS101.1x 12 2 Mumbai Pune 2 TR AR
# Rscript ggRegistrationList.R images/img1.png  downloads/html1.html  downloads/excel1.xls 1 CS101.1x 12 2 TR AR 2 Mumbai Pune
# Rscript ggRegistrationList.R images/EnrolImgS5.png images/EnrolTxtS5.txt downloads/EnrolHtmlS5.html downloads/EnrolExcelS5.xls 3 CS101.1x EE210.1x IITBombayX 0 0 2 ST FR
# Rscript ggRegistrationList.R images/EnrolImgS6.png downloads/EnrolHtmlS6.html downloads/EnrolExcelS6.xls 1 CS101.1x 21 10 Kolhapur Latur # Karjat Pune Mumbai Nasik Aurangabad Ahmednagar Akola Jalgaon 1 MR

# myArgs[0] - absolute path of R script with fileName
# myArgs[1] - absolute path of R object path
# myArgs[2] - image file name
# myArgs[3] - text filename
# myArgs[4] - htmlFileName
# myArgs[5] - courseNo
# myArgs[6].. - Courses
# myArgs[6 + n]-- stateId
# myArgs[7+n] - City No ( if statetId !~= 0)
# myArgs[8+n]... - Citys ( if statetId !~= 0)
# myArgs[8+n+m] - Report No
# myArgs[8 + n]-- myArgs[8+ m -1 ] - Report Types
# 
# Next No Of Cities
# City List
# stateId == 0 means for all state
###################################
# Get all Libraries
######################################


library(RMySQL) 
#library(xlsx)
library(googleVis)

print("HERE")
qryStr <- ""
con2<- dbConnect(MySQL(), user="root" , password="root123", dbname="IITBxDataAnalytics" , host="localhost")

imageFileName <- as.character(myArgs[1])
htmlFileName <- as.character(myArgs[2])
excelFileName <- as.character(myArgs[3])
courseTotNo <- as.numeric(myArgs[4])
print(htmlFileName)
print(paste("excelFileName ",excelFileName))

#EXCEL FILE
#wb <- createWorkbook()
#csSheetTitle <- CellStyle(wb) + Font(wb, heightInPoints=14, isBold=TRUE)
#csSheetSubTitle <- CellStyle(wb) + Font(wb, heightInPoints=12, isItalic=TRUE, isBold=FALSE)
#csTableRowNames <- CellStyle(wb) + Font(wb, isBold=TRUE)
#csTableColNames <- CellStyle(wb) + Font(wb, isBold=TRUE) + Alignment(wrapText=TRUE, h="ALIGN_CENTER") + Border(color="black", position=c("TOP", "BOTTOM"), pen=c("BORDER_THIN", "BORDER_THICK"))

#cs1 <- CellStyle(wb) + Font(wb) + Alignment(h="ALIGN_LEFT")
#cs2 <- CellStyle(wb) + Alignment(h="ALIGN_LEFT")
#cs3 <- CellStyle(wb) + Font(wb, , heightInPoints=12, isBold=TRUE) + Border() + Alignment(h="ALIGN_CENTER")

whereCourseClause <- ''
subTitle <- ''
cbPalette <- c("#999999", "#E69F00", "#56B4E9", "#009E73", "#F0E442", "#0072B2", "#D55E00", "#CC79A7")
if(courseTotNo > 0){
	for(i in 5:(courseTotNo + 4)){
		if(whereCourseClause == ''){
			whereCourseClause <- paste(" where courseName in (",myArgs[i],sep="'")
			subTitle = paste("FOR", myArgs[i], sep = " ")
		}
		else {
			whereCourseClause <- paste(whereCourseClause,"','", myArgs[i], sep="")
			subTitle = paste(subTitle, myArgs[i], sep = ",")
		}
	}
	whereCourseClause <- paste(whereCourseClause,")",sep="'")
}

print(whereCourseClause)
print (paste("subTitle " , subTitle))

textStartFlag <- FALSE
totCityNo <- 0
# stateId
stateId <- as.numeric(myArgs[5+courseTotNo]) 
print(paste("staeId ",stateId))
plotStartNo <- 0
totPlotNo <- 0
if(stateId != 0) {
	totCityNo <- as.numeric(myArgs[6+courseTotNo])
	print(paste("cityNo ", as.character(totCityNo)))
	cityTitle <- ''
	cityStart <- courseTotNo + 6 
	print(paste("CITY SATRT " ,cityStart))
	whereClause <- whereCourseClause
	print(paste("whereClause " ,whereClause))
#	if (totCityNo > 0) { has to be > 0)
#		print("IN TOTCITY >0")
		for(i in (cityStart + 1):(cityStart + totCityNo)){
			if(i==	(cityStart + 1)){
				if (whereClause != '') 
					whereClause = paste(whereClause, " and city in('", myArgs[cityStart+1] ,"'",sep="")
				else 
					whereClause = paste(" where city in ('", myArgs[i] ,"'",sep="")
				cityTitle = paste("For ", myArgs[i] )
			} else {
				whereClause = paste(whereClause, ",'", myArgs[i] ,"'",sep="")
				cityTitle = paste(cityTitle, ",",myArgs[i] , sep="")
			}
		}
		whereClause = paste(whereClause, ")")
		print(paste("whereCluase " , whereClause))
		print(paste("cityTitle " , cityTitle))
		totPlotNo <- as.numeric(myArgs[7+courseTotNo+ totCityNo])
		plotStartNo <- 8+courseTotNo+ totCityNo
} else  {
		totPlotNo <- as.numeric(myArgs[6+courseTotNo])
		plotStartNo <- 7+courseTotNo
}
plotStartFlag <- FALSE
noDataFlag <- FALSE
finalPlot <- ''

#stateFlag <- FALSE
cbPalette <- c("#999999", "#E69F00", "#56B4E9", "#009E73", "#F0E442", "#0072B2", "#D55E00", "#CC79A7")
print (paste("totPlotNo ",totPlotNo))
#print(table(regData$courseName, regData$lmsUserId))
for(i in (plotStartNo):(plotStartNo + totPlotNo -1)){
	print(myArgs[i])
############################## 
# ************FR REPORT
#################################
	if(as.character(myArgs[i]) == "TR"){
		mainTitle<- "COURSE & STATE WISE STUDENT REGISTRATION"
		if(stateId == 0) {
				qryStr <- paste("select state, courseName FROM User u ",
							" join StudentCourseEnrolment e  on u.lmsUserId = e.lmsUserId " , whereCourseClause, sep  =" ")
				print("ST QRYSTR")
				print(qryStr)
				stateFlag <- TRUE
				regData <- dbGetQuery(con2, qryStr)
				FRX = as.data.frame.matrix(table(regData$state, regData$courseName))
				c <- names(FRX)
				FRN <- cbind(rownames(FRX),FRX)	
				names(FRN) <- c("State", names(FRX))
				n <- names(FRN)
				gtable <- as.data.frame(table(regData$state))
				names(gtable) <- c('state','RegCount')
				G <- gvisGeoChart(FRN, locationvar="State",n[2],
				options=list(region="IN", displayMode="region", #datalessRegionColor= '#f8bbd0',
				colorAxis="{colors:['purple', 'red', 'orange', 'grey']}",
				resolution="provinces",width=600, height=400))
				T <- gvisTable(FRN, options=list(width=250, height=400))
			# 	if(plotStartFlag == FALSE){
			# 		finalPlot <- gvisMerge(G,T, horizontal=TRUE) 
			# 		plotStartFlag = TRUE
			# 	} else {
			# 		GT <- gvisMerge(G,T, horizontal=TRUE) 
			# 		finalPlot <- gvisMerge(finalPlot,GT, horizontal=FALSE) 
			# 	}
				
			# 	FRB <- gvisBarChart(FRN,xvar = "State", yvar=c,options=list(vAxis = 
			# 				"{title: 'State Names', titleTextStyle: {color: '#FF0000'}}",
			# 					hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
			# 					title = mainTitle, height = "1000", width="800"))
			# #	FRT <- gvisTable(FRN , options=list(width=300, height=500, legend.position='in')) 
			# #	FRBFRT <- gvisMerge(FRB,FRT, horizontal=TRUE)		
			# 	if(plotStartFlag == FALSE){
			# 		finalPlot <- FRB
			# 		plotStartFlag = TRUE;
			# 	} else {
			# 		finalPlot <- gvisMerge(finalPlot,FRB, horizontal=FALSE)		
				# }
# create exceljkfhjiewhfewjhjfhne
	#		sheet1 <- createSheet(wb, sheetName="Total Registration")
		#	addDataFrame(FRN, sheet1,  col.names=TRUE, row.names=FALSE, startRow=1, startColumn=1, 
		#	colStyle=NULL, colnamesStyle = csTableColNames)
		#	setColumnWidth(sheet1,colIndex=1,colWidth=50)
		#	setColumnWidth(sheet1,colIndex=c(2:ncol(FRN) -1),colWidth=10)

# Copied out
		# } else { # State Not Zero, totCity No not zero
		# 				mainTitle<- paste(mainTitle, cityTitle, sep=' ')
		# 				qryStr = paste("SELECT e.courseName, u.city FROM StudentCourseEnrolment e ",
		# 							" join User u on e.lmsUserId = u.lmsUserId ",
		# 							whereClause, sep = " ")
		# 				print("QRYSTR")
		# 				print(qryStr)
		# 				regData <- dbGetQuery(con2, qryStr)		
		# 				if( nrow(regData) != 0){
		# 					FRX = as.data.frame.matrix(table(regData$city, regData$courseName))
		# 					c <- names(FRX)
		# 					FRN <- cbind(rownames(FRX),FRX)											
		# 					names(FRN) <- c("City", names(FRX))
		# 					print(names(FRN))
		# 					print(FRN)
							
		# 					if(totCityNo <= 10){
		# 						FRB <- gvisBarChart(FRN, xvar = "City", yvar=c, options=list(
		# 						hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
		# 						vAxis = "{title: 'City Names', titleTextStyle: {color: '#FF0000'}}",
		# 						title = mainTitle, height = "250", width="600"))
		# 						FRT <- gvisTable(FRN , options=list(width=250, height=250, legend.position='in')) 		
		# 					} else if (totCityNo <= 20){
		# 						FRB <- gvisBarChart(FRN, xvar = "City", yvar=c, options=list(
		# 						hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
		# 						vAxis = "{title: 'City Names', titleTextStyle: {color: '#FF0000'}}",
		# 						title = mainTitle, height = "350", width="600"))
		# 						FRT <- gvisTable(FRN , options=list(width=250, height=350, legend.position='in')) 		
		# 					} else if(totCityNo <= 30){
		# 						FRB <- gvisBarChart(FRN, xvar = "City", yvar=c, options=list(
		# 						hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
		# 						vAxis = "{title: 'City Names', titleTextStyle: {color: '#FF0000'}}",
		# 						title = mainTitle, height = "450", width="600"))
		# 						FRT <- gvisTable(FRN , options=list(width=250, height=450, legend.position='in')) 		
		# 					} else if(totCityNo <= 40){
		# 						FRB <- gvisBarChart(FRN, xvar = "City", yvar=c, options=list(
		# 						hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
		# 						vAxis = "{title: 'City Names', titleTextStyle: {color: '#FF0000'}}",
		# 						title = mainTitle, height = "550", width="600"))
		# 						FRT <- gvisTable(FRN , options=list(width=250, height=550, legend.position='in')) 		
		# 					} else if(totCityNo > 40){
		# 						FRB <- gvisBarChart(FRN, xvar = "City", yvar=c, options=list(
		# 						hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
		# 						vAxis = "{title: 'City Names', titleTextStyle: {color: '#FF0000'}}",
		# 						title = mainTitle, height = "900", width="600"))
		# 						FRT <- gvisTable(FRN , options=list(width=250, height=900, legend.position='in')) 		
		# 					}
						
		# 					FRBFRT <- gvisMerge(FRB,FRT, horizontal=TRUE)		
		# 					if(plotStartFlag == FALSE){
		# 							finalPlot <- FRBFRT
		# 							plotStartFlag = TRUE;
		# 					} else {
		# 							finalPlot <- gvisMerge(finalPlot,FRBFRT, horizontal=FALSE)		
		# 					}
}
					else { #no data
						noDataFlag <- TRUE
						cat("<center><h1>No Data Found</h1><h3>Aborting</h3></center>",file=htmlFileName,sep="\n")
					}
			#sheet1 <- createSheet(wb, sheetName="Total Registration")
			#addDataFrame(FRN, sheet1,  col.names=TRUE, row.names=FALSE, startRow=1, startColumn=1, 
			#colStyle=NULL, colnamesStyle = csTableColNames)
			#setColumnWidth(sheet1,colIndex=1,colWidth=50)
			#setColumnWidth(sheet1,colIndex=c(2:ncol(FRN) -1),colWidth=10)
				}
			
	} else 	if(as.character(myArgs[i]) == "MR"){
			mainTitle<- "COURSE WISE MONTH WISE REGISTRATION"
			if(stateId == 0) {
					qryStr<-paste("select courseName,(concat(month(registrationDate),'-',substring(year(registrationDate),3))) regTime ",
			 	" FROM User u join StudentCourseEnrolment e on u.lmsUserId = e.lmsUserId ", whereCourseClause,  sep = " ")
				regData <- dbGetQuery(con2, qryStr)
			#print(table(regData$courseName, regData$state))
				TPX = as.data.frame.matrix(table(regData$courseName, regData$regTime))
				c <- names(TPX)
			TPN <- cbind(rownames(TPX),TPX)	
			names(TPN) <- c("Course Name", names(TPX))
			TPB <- gvisBarChart(TPN, xvar = "Course Name", yvar=c, options=list(
				hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
				tooltip = "{textStyle: {color: '#FF0000'}, showColorCode: true}",
				vAxis = "{title: 'Months(MM-YY)', titleTextStyle: {color: '#FF0000'}}",
				title = mainTitle, height = "400", width="700", legend.position='in', 
				colors='["#999999", "#E69F00", "#56B4E9", "#009E73", "#F0E442", "#0072B2", "#D55E00", "#CC79A7"]'))
			TPT <- gvisTable(TPN , options=list(width=250, height=300, legend.position='in')) 
			TPBTPT <- gvisMerge(TPB,TPT, horizontal=TRUE)		
				if(plotStartFlag == FALSE){
					finalPlot <- TPBTPT
					plotStartFlag = TRUE;
				} else {
					finalPlot <- gvisMerge(finalPlot,TPBTPT, horizontal=FALSE)		
				}
			} else { # stateId !=0
				mainTitle <- paste(mainTitle, cityTitle, sep=' ')
					qryStr<-paste("select courseName,(concat(month(registrationDate),'-',substring(year(registrationDate),3))) regTime ",
			 	" FROM User u join StudentCourseEnrolment e on u.lmsUserId = e.lmsUserId ", whereClause,  sep = " ")
				regData <- dbGetQuery(con2, qryStr)
			print(paste("Rows ", nrow(regData)))
			if(nrow(regData) > 0){
				#print(table(regData$courseName, regData$state))
					TPX = as.data.frame.matrix(table(regData$courseName, regData$regTime))
					c <- names(TPX)
				TPN <- cbind(rownames(TPX),TPX)	
				names(TPN) <- c("Course Name", names(TPX))
				TPB <- gvisBarChart(TPN, xvar = "Course Name", yvar=c, options=list(
					hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
					tooltip = "{textStyle: {color: '#FF0000'}, showColorCode: true}",
					vAxis = "{title: 'Months(MM-YY)', titleTextStyle: {color: '#FF0000'}}",
					title = mainTitle, height = "400", width="700", legend.position='in', 
					colors='["#999999", "#E69F00", "#56B4E9", "#009E73", "#F0E442", "#0072B2", "#D55E00", "#CC79A7"]'))
				TPT <- gvisTable(TPN , options=list(width=250, height=300, legend.position='in')) 
				TPBTPT <- gvisMerge(TPB,TPT, horizontal=TRUE)		
					if(plotStartFlag == FALSE){
						finalPlot <- TPBTPT
						plotStartFlag = TRUE;
					} else {
						finalPlot <- gvisMerge(finalPlot,TPBTPT, horizontal=FALSE)		
					}
				} else { # no Data
						noDataFlag <- TRUE
						cat("<center><h1>No Data Found</h1><h3>Aborting</h3></center>",file=htmlFileName,sep="\n")
				}
		}
	} else 	if(as.character(myArgs[i]) == "AR"){
			mainTitle<- "COURSE WISE CITY WISE ACTIVE STUDENT REGISTRATION"			
			if(stateId == 0) {
			#if(stateFlag  == FALSE){
				mainTitle<- "COURSE WISE ACTIVE STUDENT NUMBER"
				qryStr = paste("SELECT state, courseName FROM User u join StudentCourseEnrolment e ",
						"on u.lmsUserId = e.lmsUserId ", whereCourseClause, " and (e.active=1)", sep = " ")
				print("QRYSTR")
				print(qryStr)
				regData <- dbGetQuery(con2, qryStr)
				
				FRX = as.data.frame.matrix(table(regData$state, regData$courseName))
				c <- names(FRX)
				FRN <- cbind(rownames(FRX),FRX)	
				names(FRN) <- c("State", names(FRX))
				n <- names(FRN)
				gtable <- as.data.frame(table(regData$state))
				names(gtable) <- c('state','ActiveCount')
				G <- gvisGeoChart(FRN, locationvar="State",n[2],
				options=list(region="IN", displayMode="region", #datalessRegionColor= '#f8bbd0',
				colorAxis="{colors:['purple', 'red', 'orange', 'grey']}",
				resolution="provinces",width=600, height=400))
				T <- gvisTable(FRN, options=list(width=250, height=400))
				if(plotStartFlag == FALSE){
					finalPlot <- gvisMerge(G,T, horizontal=TRUE) 
					plotStartFlag = TRUE
				} else {
					GT <- gvisMerge(G,T, horizontal=TRUE) 
					finalPlot <- gvisMerge(finalPlot,GT, horizontal=FALSE) 
				}

				FRB <- gvisBarChart(FRN,xvar = "State", yvar=c,options=list(vAxis = 
							"{title: 'State Names', titleTextStyle: {color: '#FF0000'}}",
								hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
								title = mainTitle, height = "1000", width="800"))

			#	FRX <- as.data.frame(table(regData$courseName))
			#	names(FRX) = c("Course Name","No.Of Students")		
			#	FRB <- gvisBarChart(FRX,options=list(hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
			#	title = mainTitle, height = "300", width="850"))
			#	FRT <- gvisTable(FRX , options=list(width=210, height=300)) 
			#	FRBFRT <- gvisMerge(FRB,FRT, horizontal=TRUE) 
				if(plotStartFlag == FALSE){
					finalPlot <- FRB
					plotStartFlag = TRUE;
				} else {
					finalPlot <- gvisMerge(finalPlot,FRB, horizontal=FALSE)		
				}
					
		} else { # state not zero totCity No not zero
			mainTitle<- paste(mainTitle, cityTitle, sep=' ')
			
			qryStr = paste("SELECT e.courseName,u.city, e.active FROM StudentCourseEnrolment e ",
						" join User u on e.lmsUserId = u.lmsUserId ",
						whereClause, "and (e.active=1) ", sep = " ")
			print("QRYSTR")
			print(qryStr)
			regData <- dbGetQuery(con2, qryStr)		
			if(nrow(regData) > 0) {
				FRX = as.data.frame.matrix(table(regData$city, regData$courseName))
					c <- names(FRX)
					print(c)
					FRN <- cbind(rownames(FRX),FRX)	
					print(FRN)							
					names(FRN) <- c("City", names(FRX))
					print(FRN)
					print(names(FRN))	
					FRB <- gvisBarChart(FRN, xvar = "City", yvar=c, options=list(
						hAxis="{title: 'No. Of Students', titleTextStyle: {color: '#FF0000'}}",
						tooltip = "{textStyle: {color: '#FF0000'}, showColorCode: true}",
						vAxis = "{title: 'State Names', titleTextStyle: {color: '#FF0000'}}",
						title = mainTitle, height = "600", width="800",legend.position='in'))
				print("FRB")
					FRT <- gvisTable(FRN , options=list(width=250, height=300)) 
					FRBFRT <- gvisMerge(FRB,FRT, horizontal=TRUE)		
					if(plotStartFlag == FALSE){
						finalPlot <- FRBFRT
						plotStartFlag = TRUE;
					} else {
						finalPlot <- gvisMerge(finalPlot,FRBFRT, horizontal=FALSE)		
					}
				} else { # no Data
						noDataFlag <- TRUE
						cat("<center><h1>No Data Found</h1><h3>Aborting</h3></center>",file=htmlFileName,sep="\n")
				}

		}	

	}
}


########################QRYSTR STOOPED X PLOT Write For All Reports ##################### 

	cat(G$html$chart, file=htmlFileName)

#saveWorkbook(wb, excelFileName)
#write.xls(excelDbs, excelFileName,col.widths = 120 )
dbDisconnect(con2)


