# analytics
#IIT Data Analytics Developed on the basis of OpenEdx Data Analytics Package. 

The edx data analytics package is made of following four parts & a Result Store in MySQL database called analytics

edx-analytics-pipeline
edx-analytics-data-api
edx-analytics-data-api-client
edx-analytics-dashboard

edx-analytics-pipeline

The edX Analytics Pipeline reads the MySQL database used by the LMS as well as the tracking log files produced by the LMS. The data is processed by a schedular running periodically and the resulting summary data is published to the result store which is a MySQL database called 'analytics'. Most of the computation performed by the edX Analytics Pipeline is implemented as Map Reduce jobs executed by a Hadoop cluster placed on Amazon cloud. Creation of individual query data has been implemented as luigi task & executions of those tasks are accomplished by periodically execution of shell commands like cron. /Jenkins.

edx-analytics-data-api

The edX Analytics Data API provides an HTTP interface to the clients for accessing data in the result store using API. The data in the result store is updated periodically by the edX Analytics Pipeline.

edx-analytics-data-api-client

This is the client program which at the behest of the edx-analytics-dashboard, accesses data api using data api url from edx-analytics-data-api server & sends back data to edx-analytics-dashboard.

edx-analytics-dashboard

This is the front end which renders the visulization of the summary data created at Result Store using a menu structure which divides the analysis of student data in three paradigms 'enrollment analysis', 'engagement' & 'performance'

IIT data analysis 
It has kept the essence of the openEdx edx-analytics architecture like creating listed query results at the back end as batch process & keeps the resultant data in the 'analytics' database of MySQL. The big data storage is hive storage as in edx. The places it diifers from the original edx are as follows

1. Like edx data anlytics it is keeping big data (all logs) on hadoop cluster. Unlike edx data analytics this cluster is not installed on Amazon cloud, but is locally installed & managed.

2. Instead of pushing the raw log files, it does some cleaning & send cleaned data to hadoop cluster. This stops repeated reading of entire log files where queries are generated.
3. Edx data anlysis does map reduce on hadoop, here it is done on Sparks map reduce which is much faster.
4. The front end uses R data analysis results & uses R library of visualization.
5. The edx front end is also available as option.


