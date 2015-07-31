##Contant declaration
timeFrameSize = 4;
#userList=[('ricky',)]
#userList=[('AnitaB',)]
#userList=[('Janvhi',)]
#userList=[u'Learn2015']
#userList=[(u'Arun_K',)	]
#userList=[('SANDIP_JANA',)]


#            bucket      bucket         save_user       save_user
#userList=['GirishB', u'Preetham1', u'SupriyaSankpal',u'AnandAlok',]
#userList=[u'AnitaB', u'SANDIP_JANA', u'firuza', u'SARA', u'yashika', u'Ph03n1x', u'Shivani1996', u'ricky', u'Smriti', u'MdAshfaqHussain', u'nikhilnj96', u'sanjana9', u'RajShah', u'sushant', u'VarmaGCS', u'LizaDas', u'dewangp5', u'akanksha01', u'Learn2015', u'morning', u'koushikreddy', u'Anitha94', u'Madhurima540', u'SaniaAnjum', u'rehansam21', u'Arun_K', u'deepak_kumar',  u'Janvhi', u'loose', u'udaypatil', u'krishna_bagaria', u'rusty3699', u'subramanyamr', u'dbp', u'manojsewak', u'prabaht123', u'sandeepvarma', u'ayushsomani', u'canon', u'AvinashP', u'vigy123', u'vanitha', u'ashishmalik', u'meghanabuddharaju', u'adi', u'rachitb08']

#userList=[u'AnitaB', u'SANDIP_JANA', u'firuza', u'SARA', u'yashika', u'Ph03n1x', u'Shivani1996', u'ricky', u'Smriti', u'MdAshfaqHussain', u'nikhilnj96', u'sanjana9', u'RajShah', u'sushant', u'VarmaGCS', u'LizaDas', u'dewangp5', u'GirishB', u'akanksha01', u'Learn2015', u'morning', u'koushikreddy', u'Anitha94', u'Preetham1', u'Madhurima540', u'SaniaAnjum', u'rehansam21', u'Arun_K', u'deepak_kumar', u'SupriyaSankpal', u'AnandAlok', u'Janvhi', u'loose', u'udaypatil', u'krishna_bagaria', u'rusty3699', u'subramanyamr', u'dbp', u'manojsewak', u'prabaht123', u'sandeepvarma', u'ayushsomani', u'canon', u'AvinashP', u'vigy123', u'vanitha', u'ashishmalik', u'meghanabuddharaju', u'adi', u'rachitb08']

def printstatus(event,flag):
	print str(event)
	print str(oldOVT)+"  "+str(oVT)+"  "+str(cVT)+"  "+str(timeWatched)+" "+flag+" "+page

def update_all_buckets(begin_time, end_time):#accesses the timeFrameBucket to update the frequency count
	#timeFrameBuckets is used in here from global context
	"""	for x in range( int(begin_time/timeFrameSize), int(end_time/timeFrameSize)):
		timeFrameBuckets[x][0]+=1
	
	if (int(begin_time/timeFrameSize)) == (int(end_time/timeFrameSize)):
		timeFrameBuckets[int(begin_time/timeFrameSize)][1]= end_time - begin_time
		return;
	
	timeFrameBuckets[int(begin_time/timeFrameSize)][1] += math.ceil(begin_time/timeFrameSize)*timeFrameSize-begin_time
	timeFrameBuckets[int(end_time/timeFrameSize)][1]   += end_time-math.floor(end_time/timeFrameSize)*timeFrameSize
	
	for x in range( int(math.ceil(begin_time/timeFrameSize)), int(math.floor(end_time/timeFrameSize)) ):
		timeFrameBuckets[x][1]+=timeFrameSize
	"""	
	nearest_beg = (begin_time/timeFrameSize)
	nearest_end = (end_time/timeFrameSize)
	
	if (int(nearest_beg)) == int(nearest_end):
		timeFrameBuckets[int(nearest_beg)][1]= end_time - begin_time#Do not count a frame if it starts and stops in the same frame in an event.
	#	timeFrameBuckets[nearest_beg][0]+=1
		return;
	
	#Otherwise, calculate the difference between the begin and end point from the nearest time frame boundary
	beg_diff = (math.ceil(nearest_beg)*timeFrameSize) - begin_time
	end_diff = end_time - (math.floor(nearest_end)*timeFrameSize)
	
	timeFrameBuckets[int(begin_time/timeFrameSize)][1] += math.ceil(nearest_beg)*timeFrameSize-begin_time
	timeFrameBuckets[int(end_time/timeFrameSize)][1]   += end_time-math.floor(nearest_end)*timeFrameSize
	
	if beg_diff/timeFrameSize > 0.5:#starting point to nearest frame boundary covers more than 50% of the frame, count it as watched once.
		timeFrameBuckets[int(nearest_beg)][0] += 1
	if end_diff/timeFrameSize > 0.5:#end point to nearest frame boundary covers more than 50% of the frame, count it as watched once.
		timeFrameBuckets[int(nearest_end)][0] += 1
	
	#Add for all the time frames
	for x in range( int(math.ceil(nearest_beg)), int(math.floor(nearest_end)) ):
		timeFrameBuckets[x][0]+=1
		timeFrameBuckets[x][1]+=timeFrameSize

##end of function definition
#####################################################################################################################################



import mysql.connector
import itertools as it
import math

#Setting up the connection to the IITBxDataAnalytics table
cnx = mysql.connector.connect(user='root', password='', host='127.0.0.1', database='IITBxDataAnalytics')
cursor = cnx.cursor()


#testing for the top 10 videos one by one - the list thing will be added later. TODO
video='67a8559582864d6a8148e2ef5c997e8f'
#video='641407821f3a44ee9530a3ea900e2e80'
#video='a43751d47a934a9da245f28a94e1759e'
#video='e03113c234f14a848f725a44f8d1265d'
#video='a3d6a7442ff949409f2d34dddea9a984'
#video='8caa9cdac6b744518ce80d2b618bf6f8'
#video='cfb296bd686b408bb997fb14696235fa'
#video='8f892e4415d548308b9519e909ebc673'
#video='bd155f3df2254815ac1f6812f9b4e4bd'
#video='4f62458015584c0bbf904ce09caacc5d'




#Getting the video duration for an individual video in a single course
query = ("select videolength from CourseVideos where courseName=%s and videoSysName=%s")
data = ('CS101.1x', video)
cursor.execute(query, data)
duration = cursor.fetchone()[0]
print "Time to play: "+str(duration)



timeFrameBuckets = [ [0,0.0] for x in range(0, int(math.ceil(duration/timeFrameSize)) ) ]
print "Number of timeFrameBuckets for this video:"+str(len(timeFrameBuckets))



timeWatched=0.0
flagPlayPause='paused' #True means playing, False means pause_video event encountered
oVT=0.0;#Stores Old video time
cVT=0.0;#Stores Current video time - useful for calculating the time spent on a a video and the regions visited during a watch
oldOVT=0.0#Stores the old video time before the event changed.

flag=False








### Now, getting the list of all the users from the UserSessionOldLog table for the video.
query = ("select userName, count(eventName) watched from UserSessionOldLog where courseName=%s and moduleSysName=%s and eventType=%s group by userName order by watched desc limit 50")
data = ('CS101.1x', video, 'video')
cursor.execute(query, data)

userList=cursor.fetchmany(size=50)










for user in userList:
	
	print user[0]
	#Now, getting the cleaned entries from the MySQL table for a single user (as lots of redundant repetitive entries are there).
	query = ( "select distinct eventType, eventName, moduleSysName, oldVideoTime, currVideoTime, createDateTime from UserSessionOldLog where userName=%s and ((eventType in (%s) and moduleSysName=%s) or (eventType in (%s, %s) and eventName in (%s, %s)) ) order by createDateTime;" )
	data  = ( str(user[0]), 'video', video, 'video', 'navigation', 'save_user_state', 'page_close' )
	cursor.execute(query, data)
	activity = list()

	for element in cursor:#because there unknown number of such elements - a method exists to find the number, the behaviour for it would have to be explored
		activity.append(element)

	
	activityClean=list()
	flag=False
	
	
	
	
	
	
	
	for ind in range(0,len(activity)):
	#	print str(activity[ind][1])+"  "+str(activity[ind-1][1])
	
		if flag and activity[ind][3]==None and activity[ind][4]==None and not (activity[ind][1]=='page_close'):
			continue
		
		if activity[ind][3]>duration or activity[ind][4]>duration:#To eliminate all the events that would have times outside the range of the video's duration. (eliminates ALL such)
			continue
		
		if flag and activity[ind][1]=='save_user_state':
			if activityClean[-1][1]=='save_user_state' and activityClean[-1][4] > activity[ind][4]:
				continue;#if the new save_user state records a lesser time then previous one in cleaned list, then it might be an error or older record... ignore it.
		
		if activity[ind][2]==video and ( activity[ind][1]=='show_transcript' or activity[ind][1]=='load_video' ):
			flag=True#switched on flag
			#print "testing:   "+str(activity[ind])
			activityClean.append(activity[ind])
		
		elif flag and activity[ind][1]=='page_close':#To be removed later, when the video id is available with the save_user_state event.
			
			#Append the current event being examined to the end of the list
			i = ind;
			activityClean.append(activity[i])
			
			#If the save_user_event for this page close has not already been covered (immediately before, in case of no playing)
#XXX		if not activity[i-1][1]=='save_user_state':
			while not (i == len(activity) or (activity[i][1]=='save_user_state' and activity[i][3]<duration and activity[i][4]<duration )  and not activity[ind][3]==None and not activity[ind][4]==None):	
				#checking for all valid instances of the save_user_state events
				i+=1
			#error checking: do not check beyond scope of list index... If so, then leave searching for save_user_state..
			#maybe it has been covered, or it was never obtained in the logs.
			
			
			if not (i == len(activity)) :#Check which of the two save_user_state events is closer. Then append that to the end, even if repeated.
				
				if not activityClean[-2][1] == 'save_user_state':
					activityClean.append(activity[i])
					flag=False
					continue
		
		#		print str(activity[ind][5])+" "+str(activity[ind-1][5])+" "+str(activity[i][5])+" "+str(activity[ind][5])
				elif activityClean[-2][4] > activity[i][4]:#	 and activity[ind][5]-activityClean[-2][5] < activity[i][5]-activity[ind][5]:
					activityClean.append(activityClean[-2])
				else:
					activityClean.append(activity[i])
			else:
				activityClean.append(activityClean[-2])
			
			#Set flag off for this video
			flag=False#switched off flag
		
		elif flag and not ( (ind+1) == len(activity) or (activity[ind][0],activity[ind][1],activity[ind][3],activity[ind][4]) == (activity[ind+1][0],activity[ind+1][1],activity[ind+1][3],activity[ind+1][4]) ):
		#the event names are the same, and so are the oldVideoTime and newVideoTime
			activityClean.append(activity[ind])
		

#	for event in activityClean:
#		print event
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	#iterating over the log of a single user in terms of viewing his/her video activity
	timeWatched=0.0
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
	
	
	for e in range(0,len(activityClean)):
		event=activityClean[e]
#		print event
		if event[1]==u'load_video':#Events are already cleaned up, so these all belong to video type events
			oVT=0.0
			cVT=0.0
			flagPlayPaused='paused'
			page='open'
		
		
		
		
		
		elif event[1]==u'play_video':
		#	print "I'm right here"
			if flagPlayPause=='paused':
				#Just started playing from this point
				flagPlayPause='playing'
				oVT=event[4]
			
			if cVT > event[4]:
				#calculate difference upto that point
				timeWatched+=cVT-oVT
				update_all_buckets(oVT, cVT)
				oVT=event[4]
			
			cVT=event[4]
			#At any point, cVT stores the amount of time watched upto that point, and oVT stores the last point of start.
		
		
		
		
		
		elif event[1]==u'pause_video':
		#	if flagPlayPaused='playing':
		#		flagPlayPaused='paused'
				
			if cVT > event[4]:
				#calculate difference upto that point
				timeWatched+=cVT-oVT
				update_all_buckets(oVT, cVT)
				oVT = event[4]
				cVT = event[4]
			else:#in case of successive pause events, it may be that video as been opened in different tabs. So, we need to cover all the regions watched.
				cVT=event[4]
				#calculate difference upto that point
				timeWatched+=cVT-oVT
				update_all_buckets(oVT, cVT)
				oVT = event[4]
			
		
		
		
		
		
		
		
		
		elif event[1]==u'seek_video':
			#If the video is playing when seeking occurs
			if flagPlayPause=='playing':
				flagPlayPause='paused'
				if event[3] > cVT:
					#calculate difference
					timeWatched+=cVT-oVT
					cVT=event[3]
					update_all_buckets(oVT, cVT)
				oVT=event[4]
				
			else: #flagPlayPause=='paused', only slider is moved.. so change only the start time to the current end point.
				oVT=event[4]
		
		
		
		
		elif event[1]==u'stop_video':
			flagPlayPaused='paused'
			#calculate difference upto that point
			timeWatched+=cVT-oVT
			cVT = event[4]
			update_all_buckets(oVT, cVT)
		
		
		
		
		elif event[1]==u'save_user_state':
			if cVT < event[4]:
				cVT = event[4]
		
		
		
		
		elif event[1]==u'page_close' and flagPlayPause=='playing':#Events are already cleaned up, so these all belong to navigation type events
			#This would be fired if the page is closed before the video is stopped. Otherwise, if video is paused, then we already have the duration watched.
#			print "\n\n\nright hre"
			save_user_event=activityClean[e+1]
			
			if save_user_event[3]==0.0 and save_user_event[4]==0.0:
				continue;
			
			cVT = save_user_event[4]
			#calculate difference upto that point
			timeWatched+=cVT-oVT
			cVT = save_user_event[4]
			update_all_buckets(oVT, cVT)
			page='close'
			flagPlayPause='paused'
		
		
		
		
		
		
		
	#	printstatus(event, flagPlayPause)
	#	print timeFrameBuckets
	
	if page=='open':#indicates error situation; no page close event was recorded
		update_all_buckets(oVT, cVT)
		timeWatched+=cVT-oVT
		
	print "Total time spent by "+user[0]+" on this video: "+str(timeWatched)

### End of processing


###Showing summary for a single video	
for timeFrame in range(len(timeFrameBuckets)):
	print "Time Frame "+str(timeFrame*timeFrameSize)+" to "+str((timeFrame+1)*timeFrameSize-1)+"\t:: count:  "+str(timeFrameBuckets[timeFrame][0])+"\t  time spent:  "+str(timeFrameBuckets[timeFrame][1])

