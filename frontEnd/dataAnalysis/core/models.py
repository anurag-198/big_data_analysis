# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
#
# Also note: You'll have to insert the output of 'django-admin.py sqlcustom [app_label]'
# into your database.
from __future__ import unicode_literals

from django.db import models


class Course(models.Model):
    courseid = models.IntegerField(db_column='courseId', primary_key=True)  # Field name made lowercase.
    lmsname = models.CharField(db_column='lmsName', max_length=6)  # Field name made lowercase.
    orgname = models.CharField(db_column='orgName', max_length=50)  # Field name made lowercase.
    coursename = models.CharField(db_column='courseName', max_length=50)  # Field name made lowercase.
    coursetitle = models.CharField(db_column='courseTitle', max_length=255)  # Field name made lowercase.
    authoruserid = models.IntegerField(db_column='authorUserId', blank=True, null=True)  # Field name made lowercase.
    currconcepts = models.TextField(db_column='currConcepts', blank=True)  # Field name made lowercase.
    prevconcepts = models.TextField(db_column='prevConcepts', blank=True)  # Field name made lowercase.
    courselang = models.CharField(db_column='courseLang', max_length=2, blank=True)  # Field name made lowercase.
    minprice = models.IntegerField(db_column='minPrice', blank=True, null=True)  # Field name made lowercase.
    suggestedprice = models.IntegerField(db_column='suggestedPrice', blank=True, null=True)  # Field name made lowercase.
    currencycode = models.CharField(db_column='currencyCode', max_length=3, blank=True)  # Field name made lowercase.
    enddate = models.DateTimeField(db_column='endDate', blank=True, null=True)  # Field name made lowercase.
    startdate = models.DateTimeField(db_column='startDate', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Course'


class AnswerDistribution(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    course_id = models.CharField(max_length=255)
    part_id = models.CharField(max_length=255)
    quiz_name = models.TextField(blank=True)
    module_id = models.CharField(max_length=255)
    correct = models.CharField(max_length=50, blank=True)
    variant = models.IntegerField(blank=True, null=True)
    problem_display_name = models.TextField(blank=True)
    first_response_count = models.IntegerField()
    last_response_count = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'answer_distribution'


class AuthGroup(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    name = models.CharField(unique=True, max_length=80)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    group = models.ForeignKey(AuthGroup)
    permission = models.ForeignKey('AuthPermission')

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'


class AuthPermission(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    name = models.CharField(max_length=50)
    content_type = models.ForeignKey('DjangoContentType')
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'


class AuthUser(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField()
    is_superuser = models.IntegerField()
    username = models.CharField(unique=True, max_length=30)
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=30)
    email = models.CharField(max_length=75)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'


class AuthUserGroups(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    user = models.ForeignKey(AuthUser)
    group = models.ForeignKey(AuthGroup)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'


class AuthUserUserPermissions(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    user = models.ForeignKey(AuthUser)
    permission = models.ForeignKey(AuthPermission)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'


class AuthtokenToken(models.Model):
    key = models.CharField(primary_key=True, max_length=40)
    created = models.DateTimeField()
    user = models.ForeignKey(AuthUser, unique=True)

    class Meta:
        managed = False
        db_table = 'authtoken_token'


class CourseActivity(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    course_id = models.CharField(max_length=255)
    interval_start = models.DateTimeField()
    interval_end = models.DateTimeField()
    label = models.CharField(max_length=255)
    count = models.IntegerField()
    created = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'course_activity'


class CourseEnrollment(models.Model):
    datestamp = models.CharField(max_length=50, blank=True)
    course_id = models.CharField(max_length=50, blank=True)
    user_id = models.IntegerField(blank=True, null=True)
    enrolled_at_end = models.IntegerField(blank=True, null=True)
    change_since_last_day = models.IntegerField(blank=True, null=True)
    enrollment_mode = models.CharField(max_length=50, blank=True)
    id = models.IntegerField(primary_key=True)  # AutoField?

    class Meta:
        managed = False
        db_table = 'course_enrollment'


class CourseEnrollmentBirthYearDaily(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    course_id = models.CharField(max_length=255)
    date = models.DateField()
    count = models.IntegerField()
    cumulative_count = models.IntegerField()
    created = models.DateTimeField()
    birth_year = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'course_enrollment_birth_year_daily'


class CourseEnrollmentDaily(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    course_id = models.CharField(max_length=255)
    date = models.DateField()
    count = models.IntegerField()
    cumulative_count = models.IntegerField(blank=True, null=True)
    created = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'course_enrollment_daily'


class CourseEnrollmentEducationLevelDaily(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    course_id = models.CharField(max_length=255)
    date = models.DateField()
    count = models.IntegerField()
    cumulative_count = models.IntegerField(blank=True, null=True)
    created = models.DateTimeField()
    education_level = models.CharField(max_length=255, blank=True)

    class Meta:
        managed = False
        db_table = 'course_enrollment_education_level_daily'


class CourseEnrollmentGenderDaily(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    course_id = models.CharField(max_length=255)
    date = models.DateField()
    count = models.IntegerField()
    cumulative_count = models.IntegerField(blank=True, null=True)
    created = models.DateTimeField()
    gender = models.CharField(max_length=255, blank=True)

    class Meta:
        managed = False
        db_table = 'course_enrollment_gender_daily'


class CourseState(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    course_id = models.CharField(max_length=255)
    date = models.DateField()
    count = models.IntegerField()
    created = models.DateTimeField()
    state_id = models.IntegerField(blank=True, null=True)
    state = models.CharField(max_length=255)
    cumulative_count = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'course_enrollment_location_current_state'


class CourseEnrollmentModeDaily(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    course_id = models.CharField(max_length=255)
    date = models.DateField()
    count = models.IntegerField()
    cumulative_count = models.IntegerField(blank=True, null=True)
    created = models.DateTimeField()
    mode = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'course_enrollment_mode_daily'


class DjangoAdminLog(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.IntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', blank=True, null=True)
    user = models.ForeignKey(AuthUser)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    name = models.CharField(max_length=100)
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'


class DjangoMigrations(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


class DjangoSite(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    domain = models.CharField(max_length=100)
    name = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'django_site'


class GradeDistribution(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    module_id = models.CharField(max_length=255)
    course_id = models.CharField(max_length=255)
    grade = models.IntegerField()
    max_grade = models.IntegerField()
    count = models.IntegerField()
    created = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'grade_distribution'


class SequentialOpenDistribution(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    module_id = models.CharField(max_length=255)
    course_id = models.CharField(max_length=255)
    count = models.IntegerField()
    created = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'sequential_open_distribution'


class Video(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    pipeline_video_id = models.CharField(max_length=255)
    created = models.DateTimeField()
    course_id = models.CharField(max_length=255)
    encoded_module_id = models.CharField(max_length=255)
    duration = models.IntegerField()
    segment_length = models.IntegerField()
    users_at_start = models.IntegerField()
    users_at_end = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'video'


class VideoTimeline(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    pipeline_video_id = models.CharField(max_length=255)
    created = models.DateTimeField()
    segment = models.IntegerField()
    num_users = models.IntegerField()
    num_views = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'video_timeline'

class VideoDifficultyAnalytics(models.Model):
    id = models.IntegerField(primary_key=True)  # AutoField?
    orgname = models.CharField(db_column='orgName', max_length=255, blank=True)  # Field name made lowercase.
    coursename = models.CharField(db_column='CourseName', max_length=255, blank=True)  # Field name made lowercase.
    videosysid = models.CharField(db_column='videoSysId', max_length=255, blank=True)  # Field name made lowercase.
    videoframe = models.IntegerField(db_column='videoFrame', blank=True, null=True)
    videotitle = models.CharField(db_column='videoTitle', max_length=255, blank=True)  # Field name made lowercase.

    count = models.IntegerField(blank=True, null=True)
    timespent = models.FloatField(db_column='timeSpent', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'video_difficulty_analytics'
