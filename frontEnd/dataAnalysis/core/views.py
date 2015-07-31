from django.shortcuts import render_to_response
from django.shortcuts import render, redirect
from django.template import Template, Context
from django.core import serializers
from django.http import HttpResponse
from django.template import RequestContext, loader
from django.contrib import auth
from django.core.context_processors import csrf
from django.contrib.auth import authenticate,login,logout
from django.contrib.sessions.models import Session
import hashlib
import datetime
from datetime import timedelta
from django.db import connection
from django.conf import settings
import json
import re
import collections
import os.path
import subprocess
from core.models import *
import datetime
import MySQLdb




def openFile(request,fileName):
	str1="storage/"+fileName+".html";
	return render_to_response(str1)

def enrollment(request):
	str1="Rscript"+" enrolled.R"
	ret1=subprocess.call(str1,shell=True)#for running r script
	return render_to_response('core/Activity.html')

def loadMainPage(request):
	return render_to_response('core/LandingPage.html')

def courseList(request):
	qry="SELECT courseName"
	courseObj=Course.objects.all()
	#return HttpResponse(courseObj[0])
	return render_to_response("core/CourseList.html",{'list':courseObj})

def courseHome(request):
	if request.method == 'GET':
		request.session['course_name']=request.GET.get('course_id')
		course_name=request.session.get("course_name")
		#return HttpResponse(course_name)
		return render_to_response('core/CourseHome.html',{'courseName':course_name})

def geography(request):
	course_name=request.session.get("course_name")
	rootPath = os.path.abspath(os.path.dirname(__file__))
	str1="Rscript "+str(rootPath) +"/R_repo/map_a.R "+str(course_name)+" storage/templates/storage/html1.html  storage/templates/storage/table1.html"
	subprocess.call(str1,shell=True)
	st="State Wise Enrollment Analysis Of Students"
	# return HttpResponse(str1)
	return render_to_response('core/EnrollmentActivity.html',{"var":st})

def age(request):
	course_name=request.session.get("course_name")
	rootPath = os.path.abspath(os.path.dirname(__file__))
	str1="Rscript "+str(rootPath) +"/R_repo/age.R "+str(course_name)+" storage/templates/storage/html1.html  storage/templates/storage/table1.html"
	subprocess.call(str1,shell=True)
	st="Age Wise Distribution Of Enrolled Students"
	db=MySQLdb.connect("localhost","root","root123","analytics")
	return render_to_response('core/EnrollmentActivity.html',{"var":st})
def education(request):
	course_name=request.session.get("course_name")
	rootPath = os.path.abspath(os.path.dirname(__file__))
	str1="Rscript "+str(rootPath) +"/R_repo/education_level.R "+str(course_name)+" storage/templates/storage/html1.html  storage/templates/storage/table1.html"
	subprocess.call(str1,shell=True)
	st="Education-Wise Analysis Of Students"
	db=MySQLdb.connect("localhost","root","root123","analytics")
	cursor=db.cursor()
	qry1="select (count(count)/(select count(count) from analytics.course_enrollment_education_level_daily where course_id="+"\""+str(course_name)+"\""+ " and date=(select max(date) from analytics.course_enrollment_education_level_daily where course_id="  +"\""+str(course_name)+"\""+"))*100)Percentage from analytics.course_enrollment_education_level_daily where course_id=" +"\""+str(course_name)+"\""+"and (education_level=\"junior_secondary\" or education_level=\"primary\" or education_level=\"secondary\")  and date=(select max(date) from analytics.course_enrollment_birth_year_daily where course_id="+"\""+str(course_name)+"\""+")"
	cursor.execute(qry1)
	data1=cursor.fetchone()
	qry2="select (count(count)/(select count(count) from analytics.course_enrollment_education_level_daily where course_id="+"\""+str(course_name)+"\""+ " and date=(select max(date) from analytics.course_enrollment_education_level_daily where course_id="  +"\""+str(course_name)+"\""+"))*100)Percentage from analytics.course_enrollment_education_level_daily where course_id=" +"\""+str(course_name)+"\""+"and (education_level=\"masters\" or  education_level=\"bachelors\")  and date=(select max(date) from analytics.course_enrollment_birth_year_daily where course_id="+"\""+str(course_name)+"\""+")"
	cursor.execute(qry2)
	data2=cursor.fetchone()
	qry3="select (count(count)/(select count(count) from analytics.course_enrollment_education_level_daily where course_id="+"\""+str(course_name)+"\""+ " and date=(select max(date) from analytics.course_enrollment_education_level_daily where course_id="  +"\""+str(course_name)+"\""+"))*100)Percentage from analytics.course_enrollment_education_level_daily where course_id=" +"\""+str(course_name)+"\""+"and (education_level=\"associates\" or education_level=\"doctorates\")  and date=(select max(date) from analytics.course_enrollment_birth_year_daily where course_id="+"\""+str(course_name)+"\""+")"
	cursor.execute(qry3)
	data3=cursor.fetchone()
	#return HttpResponse(data3)
	return render_to_response('core/EnrollmentActivity.html',{"var":st})

def gender(request):
	course_name=request.session.get("course_name")
	rootPath = os.path.abspath(os.path.dirname(__file__))
	str1="Rscript "+str(rootPath) +"/R_repo/gender.R "+str(course_name)+" storage/templates/storage/html1.html  storage/templates/storage/table1.html"
	subprocess.call(str1,shell=True)
	st="Gender Wise Analysis Of Students"
	# return HttpResponse(str1)
	return render_to_response('core/EnrollmentActivity.html',{"var":st})

def activity(request):
	course_name=request.session.get("course_name")
	rootPath = os.path.abspath(os.path.dirname(__file__))
	str1="Rscript "+str(rootPath) +"/R_repo/activity2.R "+str(course_name)+" storage/templates/storage/html1.html  storage/templates/storage/table1.html"
	subprocess.call(str1,shell=True)
	st="Analysis Of Enrolled Students in Honor and Verified mode"
	# return HttpResponse(str1)
	return render_to_response('core/EnrollmentActivity.html',{"var":st})

def engagement_content(request):
	course_name=request.session.get("course_name")
	rootPath = os.path.abspath(os.path.dirname(__file__))
	str1="Rscript "+str(rootPath) +"/R_repo/engagement.R "+str(course_name)+" storage/templates/storage/html1.html  storage/templates/storage/table1.html"
	subprocess.call(str1,shell=True)
	st="How Do Students Interact With The Course?"
	# return HttpResponse(str1)
	return render_to_response('core/EnrollmentActivity.html',{"var":st})

def engagement_navigation(request):
	course_name=request.session.get("course_name")
	rootPath = os.path.abspath(os.path.dirname(__file__))
	str1="Rscript "+str(rootPath) +"/R_repo/ggUserNavigNew.R "+str(course_name)+" storage/templates/storage/html1.html  storage/templates/storage/table1.html"
	subprocess.call(str1,shell=True)
	st="Navigation Of Users"
	# return HttpResponse(str1)
	return render_to_response('core/EnrollmentActivity.html',{"var":st})

def videoContent(request):
	course_name=request.session.get("course_name")
	rootPath = os.path.abspath(os.path.dirname(__file__))
	if request.method == 'GET':
		request.session['video_id']=request.GET.get('video_id')
		video_id=request.session.get("video_id")
	str1="Rscript "+str(rootPath) +"/R_repo/video_interaction.R "+str(course_name)+" "+str(video_id)+" storage/templates/storage/html1.html  storage/templates/storage/table1.html"
	subprocess.call(str1,shell=True)
	#return HttpResponse(video_id)
	return render_to_response('core/EnrollmentActivity.html')

def videoOpen(request):
	course_name=request.session.get("course_name")
        db=MySQLdb.connect("localhost","root","root123","analytics")
	cursor=db.cursor()
	qry1="select videoSysId from analytics.video_difficulty_analytics where CourseName="+"\""+str(course_name)+"\""
	cursor.execute(qry1)
	data=cursor.fetchall()
	data=list(data)
	obj=VideoDifficultyAnalytics.objects.filter(coursename=course_name)
	#return HttpResponse(str1)
	#return HttpResponse(obj[0].videotitle)
	st="Difficulty Analysis Of Video"
	return render_to_response('core/VideoContent.html',{'list':obj,"var":st})

def answer(request):
	course_name=request.session.get("course_name")
	st="Answer Distribution Of the Problem"
	quiz=AnswerDistribution.objects.filter(course_id=course_name)
	#return HttpResponse(quiz[0].quiz_name)
	return render_to_response('core/Performance.html',{'quiz':quiz,"var":st})

def answer_distribution(request):
	course_name=request.session.get("course_name")
	rootPath = os.path.abspath(os.path.dirname(__file__))
	if request.method == 'GET':
		request.session['quiz_id']=request.GET.get('quiz_id')
		quiz_id=request.session.get("quiz_id")
	str1="Rscript "+str(rootPath) +"/R_repo/answer_dist.R "+str(course_name)+" "+str(quiz_id)+" storage/templates/storage/html1.html  storage/templates/storage/table1.html"
	subprocess.call(str1,shell=True)
	#return HttpResponse(quiz_id)
	return render_to_response('core/EnrollmentActivityAns.html')
	
	




	





	
		

