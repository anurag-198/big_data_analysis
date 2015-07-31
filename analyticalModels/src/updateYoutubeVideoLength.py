#Proxy settings - change accordingly
import os
os.environ['http_proxy']='http://arinjoy15:arinjoy15*@proxy.cse.iitb.ac.in:80'
os.environ['https_proxy']='http://arinjoy15:arinjoy15*@proxy.cse.iitb.ac.in:80'

from apiclient.discovery import build
from apiclient.errors import HttpError
import mysql.connector




#creating connection and obtaining cursor
cnx = mysql.connector.connect(user='root', password='', host='127.0.0.1', database='IITBxDataAnalytics')
cursor = cnx.cursor()





#accessing list of video id's from the table.
query = ("SELECT videoUTubeId FROM CourseVideos WHERE videoUTubeId IS NOT NULL")

cursor.execute(query);
videoslist=dict()

for video in cursor:
	videoslist.setdefault(str(video[0]),0.0)
#for x in videoslist.keys():
#	print x





#creating Youtube API service through the key
api_key = "AIzaSyBcCNeaGSZR-FNyC5xl0028uFLVRJVcurE"
youtube = build("youtube", "v3", developerKey=api_key)




#processing for each video
for video in videoslist.keys():
	timestring = str(youtube.videos().list(id=video, part="contentDetails,snippet").execute().get("items",[])[0]["contentDetails"]["duration"]).split("PT")[1]
	timestring = timestring.replace("H",":").replace("M",":").replace("S",":")
#	print video+" "+timestring
	timestring = timestring.split(":")
	
	second=0
	minute=0

	if not timestring[1]=="":
		second = int(timestring[1])
	minute = int(timestring[0])
	videoslist[video] = minute*60.0 + second
#	print video+" ----> "+str(minute*60.0 + second)+"    "+str(minute)+":"+str(second)	
#print videoslist



#updating the table columns
for videoName in videoslist.keys():
	query = ( "UPDATE CourseVideos SET videolength=%s WHERE videoUTubeId=%s" )#to change to include the list of video id's to process in a single query
	data = ( str(videoslist[videoName]), str(videoName) )
	cursor.execute(query, data)


cnx.commit()
#closing the cursor
cursor.close()
cnx.close()	
