from django.conf.urls import patterns, include, url

from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Examples:
    # url(r'^$', 'dataAnalysis.views.home', name='home'),
    # url(r'^blog/', include('blog.urls')),

    url(r'^admin/', include(admin.site.urls)),
    url(r'openFile/(\w+)','core.views.openFile'),
    url(r'loadMainPage','core.views.loadMainPage'),
    url(r'courseList','core.views.courseList'),
    url(r'courseHome','core.views.courseHome'),
    url(r'enroll/geography','core.views.geography'),
    url(r'enroll/education','core.views.education'),
    url(r'enroll/age','core.views.age'),
    url(r'enroll/gender','core.views.gender'),
    url(r'enroll/activity','core.views.activity'),
    url(r'engagement/content','core.views.engagement_content'),
    url(r'engagement/navigation','core.views.engagement_navigation'),
    url(r'perform/video','core.views.videoOpen'),
    url(r'videoName','core.views.videoContent'),
    url(r'answer','core.views.answer'),
    url(r'quizName','core.views.answer_distribution'),
)
