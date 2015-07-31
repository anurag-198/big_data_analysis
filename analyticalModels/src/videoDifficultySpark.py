from pyspark import SparkContext
from pyspark.sql import SQLContext
from pyspark.sql import HiveContext
import mysql.connector
import math

from pyspark.sql import Row, StructField, StructType, StringType, IntegerType
sc = SparkContext("local", "videoDifficulty")
sqlContext = HiveContext(sc)

timeFrameSize=4
#timeFrameBuckets = [ [0,0.0] for x in range(0, int(math.ceil(duration/timeFrameSize)) ) ]

def get_partial_keys(dictionary, field_id, field_value):
	"""
	takes a dictionary with a complex key tuple, a list of the id's of the fields in the tuple,
	and a tuple for the partial key for which to find the corresponding value from the dictionary
	input: 	field_id must be a list, eg: [1], [0,2,3], etc.
			field_value must be tuple, eg: ('a,1.0,1)
	"""
	field_id.sort()
	keylist=list()
	for key in dictionary.keys():
		if tuple([ key[x] for x in field_id]) == field_value:#Get the value of the partial key from the list of keys forthe dict, and 
			keylist.append(key)#add it if it matches with the key value being sought.
	return keylist

#NOTE: ---v was the original intention. try and see later to implement this, so that it is faster.
#	return map( lambda key: key if tuple([ key[x] for x in field_id] ) == tuple(field_value) , dictionary.keys())
#print get_partial_keys(videoslist, [1], tuple(['CS101.1x']))




"""
This is the processing function defined for each video and each user, that was developed
as part of the data model.
"""

def processing( EventValue ):
	"""
	The entire process of cleaning the events and collecting the feature data 
	from the events for a user and a video is written inside this.
	"""
	
	activity, duration = EventValue[1][1], EventValue[1][0]
	video = EventValue[0][2]
	
#	print video
#	print EventValue[0]
	activityClean=list()
	flag=False
	timeFrameBuckets = [ [0,0.0] for x in range(0, int(math.ceil(duration/timeFrameSize)) ) ]
	
	
#	print activity
	"""
	0 eventType
	1 eventName
	2 oldVideoTime
	3 currVideoTime
	4 createDateTime
	"""
	
	for ind in range(0,len(activity)):
#		print activity[ind]
#		print str(activity[ind][1])+"  "+str(activity[ind+1][1])
	
		if flag and activity[ind][2]==None and activity[ind][3]==None and not (activity[ind][1]=='page_close'):
			continue
		
		if activity[ind][2]>duration or activity[ind][3]>duration:
			#To eliminate all the events that would have times outside the range of the video's duration. (eliminates ALL such)
			continue
		
		if flag and activity[ind][1]=='save_user_state':
			if activityClean[-1][1]=='save_user_state' and activityClean[-1][3] > activity[ind][4]:
				continue;
				#if the new save_user state records a lesser time then previous one in cleaned list, 
				#then it might be an error or older record... ignore it.
		
	#	if activity[ind][2]==video and ( activity[ind][1]=='show_transcript' or activity[ind][1]=='load_video' ):
	#		^------------ This part was in the original prototype because it saw all the page_close events too and decided from that if the 
	#		video event was related or not. However, we are running a preprocessing program to filter out only the results for this 
	#		user,video pair... so it is not required.
	#		Hence, only the second condition is used.
		if ( activity[ind][1]=='show_transcript' or activity[ind][1]=='load_video' ):
			flag=True#switched on flag
#			print "testing:   "+str(activity[ind])
			activityClean.append(activity[ind])
		
		elif flag and activity[ind][1]=='page_close':#To be removed later, when the video id is available with the save_user_state event.
			
			#Append the current event being examined to the end of the list
			i = ind;
			activityClean.append(activity[i])
			
			#If the save_user_event for this page close has not already been covered (immediately before, in case of no playing)
#XXX		if not activity[i-1][1]=='save_user_state':
			while not (i == len(activity) or (activity[i][1]=='save_user_state' and activity[i][2]<duration and activity[i][3]<duration )  and not activity[ind][2]==None and not activity[ind][3]==None):	
				#checking for all valid instances of the save_user_state events
				i+=1
			#error checking: do not check beyond scope of list index... If so, then leave searching for save_user_state..
			#maybe it has been covered, or it was never obtained in the logs.
			
			
			if not (i == len(activity)) :#Check which of the two save_user_state events is closer. Then append that to the end, even if repeated.
				
				if not activityClean[-2][1] == 'save_user_state':
					activityClean.append(activity[i])
					flag=False
					continue
		
				#print str(activity[ind][4])+" "+str(activity[ind-1][4])+" "+str(activity[i][4])+" "+str(activity[ind][4])
				elif activityClean[-2][3] > activity[i][3]:#	 and activity[ind][4]-activityClean[-2][4] < activity[i][4]-activity[ind][4]:
					activityClean.append(activityClean[-2])
				else:
					activityClean.append(activity[i])
			else:
				activityClean.append(activityClean[-2])
			
			#Set flag off for this video
			flag=False#switched off flag
		
		elif flag and not ( (ind+1) == len(activity) or (activity[ind][0],activity[ind][1],activity[ind][2],activity[ind][3]) == (activity[ind+1][0],activity[ind+1][1],activity[ind+1][2],activity[ind+1][3]) ):
		#the event names are the same, and so are the oldVideoTime and newVideoTime
			activityClean.append(activity[ind])
		
	
#	print "I'm right here as well"
	
	################
	def update_all_buckets(begin_time, end_time):#accesses the timeFrameBucket to update the frequency count
	
		nearest_beg = (begin_time/timeFrameSize)
		nearest_end = (end_time/timeFrameSize)
	
		if (int(nearest_beg)) == int(nearest_end):
			timeFrameBuckets[int(nearest_beg)][1]= math.fabs(end_time - begin_time)#Do not count a frame if it starts and stops in the same frame in an event.
		#	timeFrameBuckets[nearest_beg][0]+=1
			return;
	
		#Otherwise, calculate the difference between the begin and end point from the nearest time frame boundary
		beg_diff = math.fabs((math.ceil(nearest_beg)*timeFrameSize) - begin_time)
		end_diff = math.fabs(end_time - (math.floor(nearest_end)*timeFrameSize))
	
		timeFrameBuckets[int(begin_time/timeFrameSize)][1] += math.fabs(math.ceil(nearest_beg)*timeFrameSize-begin_time)
		timeFrameBuckets[int(end_time/timeFrameSize)][1]   += math.fabs(end_time-math.floor(nearest_end)*timeFrameSize)
	
		if beg_diff/timeFrameSize > 0.5:#starting point to nearest frame boundary covers more than 50% of the frame, count it as watched once.
			timeFrameBuckets[int(nearest_beg)][0] += 1
		if end_diff/timeFrameSize > 0.5:#end point to nearest frame boundary covers more than 50% of the frame, count it as watched once.
			timeFrameBuckets[int(nearest_end)][0] += 1
	
		#Add for all the time frames
		for x in range( int(math.ceil(nearest_beg)), int(math.floor(nearest_end)) ):
			timeFrameBuckets[x][0]+=1
			timeFrameBuckets[x][1]+=timeFrameSize
	##################
	
	
	
	
	
	
	
	#iterating over the log of a single user in terms of viewing his/her video activity
	flagPlayPause='paused' #True means playing, False means pause_video event encountered
	page='closed'
	oVT=0.0;#Stores Old video time
	cVT=0.0;#Stores Current video time - useful for calculating the time spent on a a video and the regions visited during a watch
	oldOVT=0.0#Stores the old video time before the event changed.
	
	"""
	Remark: event fields
	0	:	eventType
	1	:	eventName
	2	:	moduleSysName
	3	:	oldVideoTime
	4	:	currentVideoTime
	5	:	createDateTime
	"""
#	print "I'm right here again"
	
	
#	print activityClean
	
	
	for e in range(0,len(activityClean)):
		
#		print "I'm right here finally"
		event=activityClean[e]
#		print event
		if event[1]==u'load_video':#Events are already cleaned up, so these all belong to video type events
			oVT=0.0
			cVT=0.0
			flagPlayPaused='paused'
			page='open'
		
		
		
		
		
		elif event[1]==u'play_video':
			#print "I'm right here"
			if flagPlayPause=='paused':
				#Just started playing from this point
				flagPlayPause='playing'
				oVT=event[3]
			
			if cVT > event[3]:
				update_all_buckets(oVT, cVT)
				oVT=event[3]
			
			cVT=event[3]
			#At any point, cVT stores the amount of time watched upto that point, and oVT stores the last point of start.
		
		
		
		
		
		elif event[1]==u'pause_video':
			#if flagPlayPaused='playing':
			#	flagPlayPaused='paused'
				
			if cVT > event[3]:
				update_all_buckets(oVT, cVT)
				oVT = event[3]
				cVT = event[3]
			else:
				#in case of successive pause events, it may be that video as been opened 
				#in different tabs. So, we need to cover all the regions watched.
				cVT=event[3]
				update_all_buckets(oVT, cVT)
				oVT = event[3]
			
		
		
		
		elif event[1]==u'seek_video':
			#If the video is playing when seeking occurs
			if flagPlayPause=='playing':
				flagPlayPause='paused'
				if event[2] > cVT:
					cVT=event[2]
					update_all_buckets(oVT, cVT)
				oVT=event[3]
				
			else: #flagPlayPause=='paused', only slider is moved.. so change only the start time to the current end point.
				oVT=event[3]
		
		
		
		
		elif event[1]==u'stop_video':
			flagPlayPaused='paused'
			cVT = event[3]
			update_all_buckets(oVT, cVT)
		
		
		
		
		elif event[1]==u'save_user_state':
			if cVT < event[3]:
				cVT = event[3]
		
		
		
		
		elif event[1]==u'page_close' and flagPlayPause=='playing':
			#Events are already cleaned up, so these all belong to navigation type events
			#This would be fired if the page is closed before the video is stopped. Otherwise, 
			#if video is paused, then we already have the duration watched.
			#print "\n\n\nright hre"
			save_user_event=activityClean[e+1]
			
			if save_user_event[2]==0.0 and save_user_event[3]==0.0:
				continue;
			
			cVT = save_user_event[3]
			cVT = save_user_event[3]
			update_all_buckets(oVT, cVT)
			page='close'
			flagPlayPause='paused'
		
		
		
		
		
		
		
		#printstatus(event, flagPlayPause)
		#print timeFrameBuckets
	
	if page=='open':#indicates error situation; no page close event was recorded
		update_all_buckets(oVT, cVT)
#		timeWatched+=cVT-oVT

	"""
	Requirements still:
	To be able to get the video duration inside the processing function... how to access related keys of a value inside a reduce function.
			probably pass the video length along with the input.
	
	Get the data from the Hive Tables.		DONE
	Write the data to the Hive tables.		
	Write second MapReduce task for count
	Write the data to the MySQL tables.
	"""

	return (EventValue[0],timeFrameBuckets);













#==============================MAIN PROGRAM STARTS==============================================


"""
Okay people, this is how we're gonna play this game.
		
Step 0. Read well and read hard!!
		
Step 1. First, we get the video lengths of all the videos and
		put them in a list right here. We will be drawing this 
		data from the Hive tables too, just like the UserLog data.
		
Step 2.	Then, get all the events of the type video or page_close 
		events in the memory. Then, we start filtering from these 
		events	on the basis as planned:
			for each video and a user, append all the video events for that,
			and if a video has an event of the form save_user_state during 
			the	time the video is being watched, then add the event for the 
			user and video list of events. 
			As for page_close events, find out where the pages are related to 
			the moduleSysIds, and for the corresponding page_close, include it
			in the list of the user and video id.
		
Step 3.	This is then dealt with by the reduceByKey() for each user 
		video key and value being the list of events for the user 
		and video key. Then, output of reduce by key would be the
		list of tuples for each key, having a different key. This
		would be stored in the Hive tables.
		
Step 4.	Then do a mapreduce to find the total number of count
		and timeAccess to the video and the videoFrame, and
		write it to the MySQL Analytics summary table.
"""











"""
Step 1. Getting the video data from Hive.
"""

sqlVideo = "SELECT orgname,coursename, videosysname, videolength, videoTitle FROM coursevideos where videosysname is not null"
videoslist=dict(sqlContext.sql(sqlVideo).map(lambda v: ( (v[0],v[1],v[2],), (v[-2], v[-1]))  ).collect())
#unless you actually collect() the data from the RDD, you can't operate on it.
#So, to actually use the data, call a take(x) method or a collect() method on the RDD before you start.
#otherwise, to use RDD functions, DO NOT OPERATE ON IT IMMEDIATELY. it will all be done when the data is finally collected.

#1. To iterate over the data like a list, do a collect or take function first.
#2. To access the elements of a Row type object (result of collect), use the normal subscripts. That works just fine.
#But here, to make it easier, we decided to change the Rows to tuples in a dictionary so that they can be accessed via moduleSysName or videoSysName

#for video in videoslist:
#	print video, videoslist[video][0], videoslist[video][1]



















"""
Step 2. Getting the event data from Hive.
"""

"""
For small dataset: The query to get it is as follows (tablename userlogsmall):

//For getting only video and page close events!
create table userlogsmall as (select USOL.* from UserSessionOldLog USOL, (select userName, count(eventType) cet from UserSessionOldLog where userName is not NULL and userName not in ('') and courseName='CS101.1x' and (eventType='video' or eventType='navigation' and eventName='page_close') group by userName order by cet desc limit 30) t1 where USOL.userName = t1.userName and (eventType='video' or eventType='navigation' and eventName='page_close'));


//To get absolute realistic chunk of the dataset,
create table userlogsmall as (select USOL.* from UserSessionOldLog USOL, (select userName, count(eventType) cet from UserSessionOldLog where userName is not NULL and userName not in ('') and courseName='CS101.1x' group by userName order by cet desc limit 30) t1 where USOL.userName = t1.userName and courseName='CS101.1x');

===> Means, get the activities in CS101.1x of top 30 busy students in CS101.1x

"""



#eventSql="SELECT DISTINCT orgname, coursename, username, modulesysname, eventtype, eventname, oldvideotime, currvideotime, createdatetime FROM userlog WHERE eventtype='video' OR (eventtype='navigation' AND eventname='page_close') ORDER BY orgname, coursename, username, createdatetime"

eventSql="SELECT DISTINCT orgname, coursename, username, modulesysname, eventtype, eventname, oldvideotime, currvideotime, createdatetime FROM userlogsmall WHERE eventtype='video' OR (eventtype='navigation' AND eventname='page_close') ORDER BY orgname, coursename, username, createdatetime"

"""
MySQL Query=  SELECT orgName, courseName, userName, moduleSysName, eventType, eventName, oldVideoTime, currVideoTime, createDateTime FROM UserSessionOldLog WHERE eventType='video' and userName <> '' and orgName <> '' OR (eventType='navigation' AND eventName='page_close') and userName <> '' and orgName <> '' ORDER BY orgName, courseName, userName, createDateTime limit 100, 100;
"""
##Other queries are also there

tabledata=sqlContext.sql(eventSql).collect() ##Which returns the entire related data in the memory

"""
Note to self:
0	:	orgname
1	:	coursename
2	:	username
3	:	modulesysname
4	:	eventtype
5	:	eventname
6	:	oldvideotime
7	:	currvideotime
8	:	createdatetime
"""

#Now, it is time to process out the events related to each user and each video.

eventDict=dict()
"""
Dictionary object having 
key		=	orgname,coursename,videosysname,username and
value	=	eventtype, eventname, oldvideotime, currideotime, createdatetime
"""

lastEvent=None#stores the last event for the user and the video key in question.
lastKey=None#stores the last key encountered

for event in tabledata:
	
	key=(event[0], event[1], event[3], event[2])
	value=(event[4], event[5], event[6], event[7], event[8])
	
	"""
	Fields of key: orgname, coursename, modulesysname, username
	Fields of value: eventtype, eventname, oldvideotime, currvideotime, createdatetime
	"""
	
	
	#print ""+str( key if key[1]=='CS101.1x' else '')+"\n"+str(value if key[1]=='CS101.1x' else '')+"\n\n"
	if (lastEvent==None or not lastKey[3]==key[3]):
		##For now, checking only if the user is changing or not.... for same user, considering all they
		## events to be in the sequential order in which they appear.
		if not value[1]=='save_user_state' and not value[1]=='page_close':
			#So that even if it is not matching with the previous key, it should not be a save_user_state or a page_close event
			eventDict.setdefault(key, (videoslist[(key[0],key[1],key[2])][0],[]))
			#First set the dictionary key with an empty list if it isn't already there.
			eventDict.setdefault(key, (videoslist[(key[0],key[1],key[2])][0],[]))[1].append(value)
			#then append the event to the list.
			lastEvent=value
			lastKey=key
		continue;
	else:	
		if not value[1] == 'save_user_state' and not value[1] == 'page_close':
			eventDict.setdefault(key, (videoslist[(key[0],key[1],key[2])][0],[]))
			#First set the dictionary key with an empty list if it isn't already there.
			eventDict.setdefault(key, (videoslist[(key[0],key[1],key[2])][0],[]))[1].append(value)
			#then append the event to the list.
			lastEvent=value
			lastKey=key
		elif value[1] == 'save_user_state':
			#print "\n\n"+str(lastKey)
			#print str(value)+"\n\n"
			eventDict.setdefault(lastKey, (videoslist[(lastKey[0],lastKey[1],lastKey[2])][0],[]))
			#First set the dictionary key with an empty list if it isn't already there.
			eventDict.setdefault(lastKey, (videoslist[(lastKey[0],lastKey[1],lastKey[2])][0],[]))[1].append(value)
			#add to the dictionary of the event with the last key value observed.
		
		elif value[1] == 'page_close':
			#print event
			#print "\n\n"+str( lastKey if lastKey[1]=='CS101.1x' else '')+"\n"+str(value if lastKey[1]=='CS101.1x' else '')+"\n\n"
			eventDict.setdefault(lastKey, (videoslist[(lastKey[0],lastKey[1],lastKey[2])][0],[]))
			#First set the dictionary key with an empty list if it isn't already there.
			eventDict.setdefault(lastKey, (videoslist[(lastKey[0],lastKey[1],lastKey[2])][0],[]))[1].append(value)
			#add to the dictionary of the event with the last key value observed.




#Example of key of eventDict : [('IITBombayX', 'CS101.1x', '67a8559582864d6a8148e2ef5c997e8f', 'ricky')]
#for x in get_partial_keys(eventDict, [0,1,2,3], ('IITBombayX', 'CS101.1x', '67a8559582864d6a8148e2ef5c997e8f','AnitaB') ):
#	for y in eventDict[x][1]:
#		print str(x)+" "+str(eventDict[x][0])+" "+str(y)
#		
#	print "\n"





"""
Certain videos were found to have a NULL entry for things (Youtube ID, title, and hence, duration). Only one such video was found.
Removing all related instances of that video from the dictionaries.

TODO: Convert this into a query for later error detection.
"""

errorvideoId='2017625218ba4309b4cd42309f5d82e2'
for key in get_partial_keys(eventDict, [2], (errorvideoId,)):
	del eventDict[key]


#For Checking:
#for key in get_partial_keys(eventDict, [2], (errorvideoId,)):
#	print eventDict[key]







eventMap=sc.parallelize(eventDict.items()).map(lambda p: (p[0], p[1]))
#eventMap.groupByKey()

videoDetails = eventMap.map(processing)

"""
Now that all the processing etc. is complete, let's write the data decently to the Hive table

Hive table schema: 	orgname, coursename, videoname, videoTitle, username, timeFrameId (key)
					timeFrameAccessCount, timeFrameAccessDuration

"""

"""

Possible Hive query for the same.
"""
sql="INSERT INTO videodifficultydetails (id,orgname,coursename,videosysid,videotitle,videoframe,count,timespent) VALUES "

for key in videoDetails.collect():
	for i in range(0,len(key[1])):#Which contains all the timeFrame details
		sql+="( NULL, %s , %s, %s, %s, %d, %d, %f ), "%( key[0][0], key[0][1], key[0][2], videoslist[(key[0][0], key[0][1], key[0][2])][1], key[0][3], i, key[1][i][0], key[1][i][1] )
		#which translates to: orgname, coursename, videoname, videotitle username,     timeFrameId, timeFrameAccessCount, timeFrameAccessDuration

sql=sql[:-1]
sqlContext.sql(sql)

"""


Now perform another map reduce to calculate the total time spent by all the users on a video,
and the total number of accesses to a video by a user.

The videoDetails RDD is in the form [ ( (keytuple),  [timeFramelist] ]
MapREDUCE IT first
"""

cnx = mysql.connector.connect(user='root', password='', host='127.0.0.1', database='IITBxDataAnalytics')
cursor = cnx.cursor()
"""
def insertSQL(item):
	##which translates to: orgname, coursename, videoname, videotitle,  timeFrameId, timeFrameAccessCount, timeFrameAccessDuration
	sql = "INSERT INTO video_difficulty_analytics  (id,orgName,CourseName,videoSysId,videoTitle,videoFrame,count,timeSpent) VALUES (NULL, '%s','%s', '%s', '%s', '%d','%d','%f')" % ((item[0][0]), item[0][1], item[0][2], videoslist[(item[0][0], item[0][1], item[0][2])][1], item[0][3], item[1][0], item[1][1])
	try:
		cursor.execute(sql)
		cnx.commit()
	except Exception, err:
   		cnx.rollback()


videoUserTimeFrameAggregate = sc.parallelize(videoDetails.map(lambda x: [ (x[0], i, x[1][i]) for i in range(0,len(x[1])) ]).reduce(lambda a,b: a+b))

for x in videoUserTimeFrameAggregate.map( lambda x: ( (x[0][0], x[0][1], x[0][2], x[1] ), x[2] ) ).reduceByKey(lambda a,b: (a[0]+b[0], a[1]+b[1] ) ).collect():#For each did not work... dunno why
	insertSQL(x)
#.reduceByKey(lambda a,b: (a[-2]+b[-2], a[-1]+b[-1] )

#.foreach(insertSQL)

"""
"""
Now, write that data to the mysql table.
"""

cursor.close();
cnx.close();
