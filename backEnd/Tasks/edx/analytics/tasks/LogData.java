// ORM class for table 'LogData'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Jun 29 19:19:53 IST 2015
// For connector: org.apache.sqoop.manager.DirectMySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LogData extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  protected ResultSet __cur_result_set;
  private Long sessionId;
  public Long get_sessionId() {
    return sessionId;
  }
  public void set_sessionId(Long sessionId) {
    this.sessionId = sessionId;
  }
  public LogData with_sessionId(Long sessionId) {
    this.sessionId = sessionId;
    return this;
  }
  private String sessionSysName;
  public String get_sessionSysName() {
    return sessionSysName;
  }
  public void set_sessionSysName(String sessionSysName) {
    this.sessionSysName = sessionSysName;
  }
  public LogData with_sessionSysName(String sessionSysName) {
    this.sessionSysName = sessionSysName;
    return this;
  }
  private String lmsName;
  public String get_lmsName() {
    return lmsName;
  }
  public void set_lmsName(String lmsName) {
    this.lmsName = lmsName;
  }
  public LogData with_lmsName(String lmsName) {
    this.lmsName = lmsName;
    return this;
  }
  private String orgName;
  public String get_orgName() {
    return orgName;
  }
  public void set_orgName(String orgName) {
    this.orgName = orgName;
  }
  public LogData with_orgName(String orgName) {
    this.orgName = orgName;
    return this;
  }
  private String courseName;
  public String get_courseName() {
    return courseName;
  }
  public void set_courseName(String courseName) {
    this.courseName = courseName;
  }
  public LogData with_courseName(String courseName) {
    this.courseName = courseName;
    return this;
  }
  private String courseRun;
  public String get_courseRun() {
    return courseRun;
  }
  public void set_courseRun(String courseRun) {
    this.courseRun = courseRun;
  }
  public LogData with_courseRun(String courseRun) {
    this.courseRun = courseRun;
    return this;
  }
  private Long lmsUserId;
  public Long get_lmsUserId() {
    return lmsUserId;
  }
  public void set_lmsUserId(Long lmsUserId) {
    this.lmsUserId = lmsUserId;
  }
  public LogData with_lmsUserId(Long lmsUserId) {
    this.lmsUserId = lmsUserId;
    return this;
  }
  private String userName;
  public String get_userName() {
    return userName;
  }
  public void set_userName(String userName) {
    this.userName = userName;
  }
  public LogData with_userName(String userName) {
    this.userName = userName;
    return this;
  }
  private String agent;
  public String get_agent() {
    return agent;
  }
  public void set_agent(String agent) {
    this.agent = agent;
  }
  public LogData with_agent(String agent) {
    this.agent = agent;
    return this;
  }
  private String hostName;
  public String get_hostName() {
    return hostName;
  }
  public void set_hostName(String hostName) {
    this.hostName = hostName;
  }
  public LogData with_hostName(String hostName) {
    this.hostName = hostName;
    return this;
  }
  private String ipAddress;
  public String get_ipAddress() {
    return ipAddress;
  }
  public void set_ipAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
  public LogData with_ipAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }
  private java.sql.Timestamp createDateTime;
  public java.sql.Timestamp get_createDateTime() {
    return createDateTime;
  }
  public void set_createDateTime(java.sql.Timestamp createDateTime) {
    this.createDateTime = createDateTime;
  }
  public LogData with_createDateTime(java.sql.Timestamp createDateTime) {
    this.createDateTime = createDateTime;
    return this;
  }
  private String eventType;
  public String get_eventType() {
    return eventType;
  }
  public void set_eventType(String eventType) {
    this.eventType = eventType;
  }
  public LogData with_eventType(String eventType) {
    this.eventType = eventType;
    return this;
  }
  private String eventSource;
  public String get_eventSource() {
    return eventSource;
  }
  public void set_eventSource(String eventSource) {
    this.eventSource = eventSource;
  }
  public LogData with_eventSource(String eventSource) {
    this.eventSource = eventSource;
    return this;
  }
  private String eventName;
  public String get_eventName() {
    return eventName;
  }
  public void set_eventName(String eventName) {
    this.eventName = eventName;
  }
  public LogData with_eventName(String eventName) {
    this.eventName = eventName;
    return this;
  }
  private String dataSource;
  public String get_dataSource() {
    return dataSource;
  }
  public void set_dataSource(String dataSource) {
    this.dataSource = dataSource;
  }
  public LogData with_dataSource(String dataSource) {
    this.dataSource = dataSource;
    return this;
  }
  private Float oldVideoSpeed;
  public Float get_oldVideoSpeed() {
    return oldVideoSpeed;
  }
  public void set_oldVideoSpeed(Float oldVideoSpeed) {
    this.oldVideoSpeed = oldVideoSpeed;
  }
  public LogData with_oldVideoSpeed(Float oldVideoSpeed) {
    this.oldVideoSpeed = oldVideoSpeed;
    return this;
  }
  private Float currVideoSpeed;
  public Float get_currVideoSpeed() {
    return currVideoSpeed;
  }
  public void set_currVideoSpeed(Float currVideoSpeed) {
    this.currVideoSpeed = currVideoSpeed;
  }
  public LogData with_currVideoSpeed(Float currVideoSpeed) {
    this.currVideoSpeed = currVideoSpeed;
    return this;
  }
  private Float oldVideoTime;
  public Float get_oldVideoTime() {
    return oldVideoTime;
  }
  public void set_oldVideoTime(Float oldVideoTime) {
    this.oldVideoTime = oldVideoTime;
  }
  public LogData with_oldVideoTime(Float oldVideoTime) {
    this.oldVideoTime = oldVideoTime;
    return this;
  }
  private Float currVideoTime;
  public Float get_currVideoTime() {
    return currVideoTime;
  }
  public void set_currVideoTime(Float currVideoTime) {
    this.currVideoTime = currVideoTime;
  }
  public LogData with_currVideoTime(Float currVideoTime) {
    this.currVideoTime = currVideoTime;
    return this;
  }
  private String videoNavigType;
  public String get_videoNavigType() {
    return videoNavigType;
  }
  public void set_videoNavigType(String videoNavigType) {
    this.videoNavigType = videoNavigType;
  }
  public LogData with_videoNavigType(String videoNavigType) {
    this.videoNavigType = videoNavigType;
    return this;
  }
  private Float oldGrade;
  public Float get_oldGrade() {
    return oldGrade;
  }
  public void set_oldGrade(Float oldGrade) {
    this.oldGrade = oldGrade;
  }
  public LogData with_oldGrade(Float oldGrade) {
    this.oldGrade = oldGrade;
    return this;
  }
  private Float currGrade;
  public Float get_currGrade() {
    return currGrade;
  }
  public void set_currGrade(Float currGrade) {
    this.currGrade = currGrade;
  }
  public LogData with_currGrade(Float currGrade) {
    this.currGrade = currGrade;
    return this;
  }
  private Float maxGrade;
  public Float get_maxGrade() {
    return maxGrade;
  }
  public void set_maxGrade(Float maxGrade) {
    this.maxGrade = maxGrade;
  }
  public LogData with_maxGrade(Float maxGrade) {
    this.maxGrade = maxGrade;
    return this;
  }
  private Integer attempts;
  public Integer get_attempts() {
    return attempts;
  }
  public void set_attempts(Integer attempts) {
    this.attempts = attempts;
  }
  public LogData with_attempts(Integer attempts) {
    this.attempts = attempts;
    return this;
  }
  private Integer maxNoAttempts;
  public Integer get_maxNoAttempts() {
    return maxNoAttempts;
  }
  public void set_maxNoAttempts(Integer maxNoAttempts) {
    this.maxNoAttempts = maxNoAttempts;
  }
  public LogData with_maxNoAttempts(Integer maxNoAttempts) {
    this.maxNoAttempts = maxNoAttempts;
    return this;
  }
  private String hintAvailable;
  public String get_hintAvailable() {
    return hintAvailable;
  }
  public void set_hintAvailable(String hintAvailable) {
    this.hintAvailable = hintAvailable;
  }
  public LogData with_hintAvailable(String hintAvailable) {
    this.hintAvailable = hintAvailable;
    return this;
  }
  private String hintUsed;
  public String get_hintUsed() {
    return hintUsed;
  }
  public void set_hintUsed(String hintUsed) {
    this.hintUsed = hintUsed;
  }
  public LogData with_hintUsed(String hintUsed) {
    this.hintUsed = hintUsed;
    return this;
  }
  private Integer currPosition;
  public Integer get_currPosition() {
    return currPosition;
  }
  public void set_currPosition(Integer currPosition) {
    this.currPosition = currPosition;
  }
  public LogData with_currPosition(Integer currPosition) {
    this.currPosition = currPosition;
    return this;
  }
  private Integer oldPosition;
  public Integer get_oldPosition() {
    return oldPosition;
  }
  public void set_oldPosition(Integer oldPosition) {
    this.oldPosition = oldPosition;
  }
  public LogData with_oldPosition(Integer oldPosition) {
    this.oldPosition = oldPosition;
    return this;
  }
  private String chapterSysName;
  public String get_chapterSysName() {
    return chapterSysName;
  }
  public void set_chapterSysName(String chapterSysName) {
    this.chapterSysName = chapterSysName;
  }
  public LogData with_chapterSysName(String chapterSysName) {
    this.chapterSysName = chapterSysName;
    return this;
  }
  private String chapterTitle;
  public String get_chapterTitle() {
    return chapterTitle;
  }
  public void set_chapterTitle(String chapterTitle) {
    this.chapterTitle = chapterTitle;
  }
  public LogData with_chapterTitle(String chapterTitle) {
    this.chapterTitle = chapterTitle;
    return this;
  }
  private String sessSysName;
  public String get_sessSysName() {
    return sessSysName;
  }
  public void set_sessSysName(String sessSysName) {
    this.sessSysName = sessSysName;
  }
  public LogData with_sessSysName(String sessSysName) {
    this.sessSysName = sessSysName;
    return this;
  }
  private String sessTitle;
  public String get_sessTitle() {
    return sessTitle;
  }
  public void set_sessTitle(String sessTitle) {
    this.sessTitle = sessTitle;
  }
  public LogData with_sessTitle(String sessTitle) {
    this.sessTitle = sessTitle;
    return this;
  }
  private String moduleSysName;
  public String get_moduleSysName() {
    return moduleSysName;
  }
  public void set_moduleSysName(String moduleSysName) {
    this.moduleSysName = moduleSysName;
  }
  public LogData with_moduleSysName(String moduleSysName) {
    this.moduleSysName = moduleSysName;
    return this;
  }
  private String moduleTitle;
  public String get_moduleTitle() {
    return moduleTitle;
  }
  public void set_moduleTitle(String moduleTitle) {
    this.moduleTitle = moduleTitle;
  }
  public LogData with_moduleTitle(String moduleTitle) {
    this.moduleTitle = moduleTitle;
    return this;
  }
  private String answerChoice;
  public String get_answerChoice() {
    return answerChoice;
  }
  public void set_answerChoice(String answerChoice) {
    this.answerChoice = answerChoice;
  }
  public LogData with_answerChoice(String answerChoice) {
    this.answerChoice = answerChoice;
    return this;
  }
  private String success;
  public String get_success() {
    return success;
  }
  public void set_success(String success) {
    this.success = success;
  }
  public LogData with_success(String success) {
    this.success = success;
    return this;
  }
  private String done;
  public String get_done() {
    return done;
  }
  public void set_done(String done) {
    this.done = done;
  }
  public LogData with_done(String done) {
    this.done = done;
    return this;
  }
  private String enrolmentMode;
  public String get_enrolmentMode() {
    return enrolmentMode;
  }
  public void set_enrolmentMode(String enrolmentMode) {
    this.enrolmentMode = enrolmentMode;
  }
  public LogData with_enrolmentMode(String enrolmentMode) {
    this.enrolmentMode = enrolmentMode;
    return this;
  }
  private Integer totDurationInSecs;
  public Integer get_totDurationInSecs() {
    return totDurationInSecs;
  }
  public void set_totDurationInSecs(Integer totDurationInSecs) {
    this.totDurationInSecs = totDurationInSecs;
  }
  public LogData with_totDurationInSecs(Integer totDurationInSecs) {
    this.totDurationInSecs = totDurationInSecs;
    return this;
  }
  private Integer eventNo;
  public Integer get_eventNo() {
    return eventNo;
  }
  public void set_eventNo(Integer eventNo) {
    this.eventNo = eventNo;
  }
  public LogData with_eventNo(Integer eventNo) {
    this.eventNo = eventNo;
    return this;
  }
  private String otherTitle;
  public String get_otherTitle() {
    return otherTitle;
  }
  public void set_otherTitle(String otherTitle) {
    this.otherTitle = otherTitle;
  }
  public LogData with_otherTitle(String otherTitle) {
    this.otherTitle = otherTitle;
    return this;
  }
  private String otherType;
  public String get_otherType() {
    return otherType;
  }
  public void set_otherType(String otherType) {
    this.otherType = otherType;
  }
  public LogData with_otherType(String otherType) {
    this.otherType = otherType;
    return this;
  }
  private String anonymous;
  public String get_anonymous() {
    return anonymous;
  }
  public void set_anonymous(String anonymous) {
    this.anonymous = anonymous;
  }
  public LogData with_anonymous(String anonymous) {
    this.anonymous = anonymous;
    return this;
  }
  private String anonymousToPeers;
  public String get_anonymousToPeers() {
    return anonymousToPeers;
  }
  public void set_anonymousToPeers(String anonymousToPeers) {
    this.anonymousToPeers = anonymousToPeers;
  }
  public LogData with_anonymousToPeers(String anonymousToPeers) {
    this.anonymousToPeers = anonymousToPeers;
    return this;
  }
  private String eduLevel;
  public String get_eduLevel() {
    return eduLevel;
  }
  public void set_eduLevel(String eduLevel) {
    this.eduLevel = eduLevel;
  }
  public LogData with_eduLevel(String eduLevel) {
    this.eduLevel = eduLevel;
    return this;
  }
  private String gender;
  public String get_gender() {
    return gender;
  }
  public void set_gender(String gender) {
    this.gender = gender;
  }
  public LogData with_gender(String gender) {
    this.gender = gender;
    return this;
  }
  private String commentableId;
  public String get_commentableId() {
    return commentableId;
  }
  public void set_commentableId(String commentableId) {
    this.commentableId = commentableId;
  }
  public LogData with_commentableId(String commentableId) {
    this.commentableId = commentableId;
    return this;
  }
  private String commentType;
  public String get_commentType() {
    return commentType;
  }
  public void set_commentType(String commentType) {
    this.commentType = commentType;
  }
  public LogData with_commentType(String commentType) {
    this.commentType = commentType;
    return this;
  }
  private String commentSysId;
  public String get_commentSysId() {
    return commentSysId;
  }
  public void set_commentSysId(String commentSysId) {
    this.commentSysId = commentSysId;
  }
  public LogData with_commentSysId(String commentSysId) {
    this.commentSysId = commentSysId;
    return this;
  }
  private String aadhar;
  public String get_aadhar() {
    return aadhar;
  }
  public void set_aadhar(String aadhar) {
    this.aadhar = aadhar;
  }
  public LogData with_aadhar(String aadhar) {
    this.aadhar = aadhar;
    return this;
  }
  private java.sql.Timestamp problemSubmissionTime;
  public java.sql.Timestamp get_problemSubmissionTime() {
    return problemSubmissionTime;
  }
  public void set_problemSubmissionTime(java.sql.Timestamp problemSubmissionTime) {
    this.problemSubmissionTime = problemSubmissionTime;
  }
  public LogData with_problemSubmissionTime(java.sql.Timestamp problemSubmissionTime) {
    this.problemSubmissionTime = problemSubmissionTime;
    return this;
  }
  private String hintMode;
  public String get_hintMode() {
    return hintMode;
  }
  public void set_hintMode(String hintMode) {
    this.hintMode = hintMode;
  }
  public LogData with_hintMode(String hintMode) {
    this.hintMode = hintMode;
    return this;
  }
  private Float currentSeekTime;
  public Float get_currentSeekTime() {
    return currentSeekTime;
  }
  public void set_currentSeekTime(Float currentSeekTime) {
    this.currentSeekTime = currentSeekTime;
  }
  public LogData with_currentSeekTime(Float currentSeekTime) {
    this.currentSeekTime = currentSeekTime;
    return this;
  }
  private String queryText;
  public String get_queryText() {
    return queryText;
  }
  public void set_queryText(String queryText) {
    this.queryText = queryText;
  }
  public LogData with_queryText(String queryText) {
    this.queryText = queryText;
    return this;
  }
  private Integer noOfResults;
  public Integer get_noOfResults() {
    return noOfResults;
  }
  public void set_noOfResults(Integer noOfResults) {
    this.noOfResults = noOfResults;
  }
  public LogData with_noOfResults(Integer noOfResults) {
    this.noOfResults = noOfResults;
    return this;
  }
  private java.sql.Timestamp lastModDateTime;
  public java.sql.Timestamp get_lastModDateTime() {
    return lastModDateTime;
  }
  public void set_lastModDateTime(java.sql.Timestamp lastModDateTime) {
    this.lastModDateTime = lastModDateTime;
  }
  public LogData with_lastModDateTime(java.sql.Timestamp lastModDateTime) {
    this.lastModDateTime = lastModDateTime;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LogData)) {
      return false;
    }
    LogData that = (LogData) o;
    boolean equal = true;
    equal = equal && (this.sessionId == null ? that.sessionId == null : this.sessionId.equals(that.sessionId));
    equal = equal && (this.sessionSysName == null ? that.sessionSysName == null : this.sessionSysName.equals(that.sessionSysName));
    equal = equal && (this.lmsName == null ? that.lmsName == null : this.lmsName.equals(that.lmsName));
    equal = equal && (this.orgName == null ? that.orgName == null : this.orgName.equals(that.orgName));
    equal = equal && (this.courseName == null ? that.courseName == null : this.courseName.equals(that.courseName));
    equal = equal && (this.courseRun == null ? that.courseRun == null : this.courseRun.equals(that.courseRun));
    equal = equal && (this.lmsUserId == null ? that.lmsUserId == null : this.lmsUserId.equals(that.lmsUserId));
    equal = equal && (this.userName == null ? that.userName == null : this.userName.equals(that.userName));
    equal = equal && (this.agent == null ? that.agent == null : this.agent.equals(that.agent));
    equal = equal && (this.hostName == null ? that.hostName == null : this.hostName.equals(that.hostName));
    equal = equal && (this.ipAddress == null ? that.ipAddress == null : this.ipAddress.equals(that.ipAddress));
    equal = equal && (this.createDateTime == null ? that.createDateTime == null : this.createDateTime.equals(that.createDateTime));
    equal = equal && (this.eventType == null ? that.eventType == null : this.eventType.equals(that.eventType));
    equal = equal && (this.eventSource == null ? that.eventSource == null : this.eventSource.equals(that.eventSource));
    equal = equal && (this.eventName == null ? that.eventName == null : this.eventName.equals(that.eventName));
    equal = equal && (this.dataSource == null ? that.dataSource == null : this.dataSource.equals(that.dataSource));
    equal = equal && (this.oldVideoSpeed == null ? that.oldVideoSpeed == null : this.oldVideoSpeed.equals(that.oldVideoSpeed));
    equal = equal && (this.currVideoSpeed == null ? that.currVideoSpeed == null : this.currVideoSpeed.equals(that.currVideoSpeed));
    equal = equal && (this.oldVideoTime == null ? that.oldVideoTime == null : this.oldVideoTime.equals(that.oldVideoTime));
    equal = equal && (this.currVideoTime == null ? that.currVideoTime == null : this.currVideoTime.equals(that.currVideoTime));
    equal = equal && (this.videoNavigType == null ? that.videoNavigType == null : this.videoNavigType.equals(that.videoNavigType));
    equal = equal && (this.oldGrade == null ? that.oldGrade == null : this.oldGrade.equals(that.oldGrade));
    equal = equal && (this.currGrade == null ? that.currGrade == null : this.currGrade.equals(that.currGrade));
    equal = equal && (this.maxGrade == null ? that.maxGrade == null : this.maxGrade.equals(that.maxGrade));
    equal = equal && (this.attempts == null ? that.attempts == null : this.attempts.equals(that.attempts));
    equal = equal && (this.maxNoAttempts == null ? that.maxNoAttempts == null : this.maxNoAttempts.equals(that.maxNoAttempts));
    equal = equal && (this.hintAvailable == null ? that.hintAvailable == null : this.hintAvailable.equals(that.hintAvailable));
    equal = equal && (this.hintUsed == null ? that.hintUsed == null : this.hintUsed.equals(that.hintUsed));
    equal = equal && (this.currPosition == null ? that.currPosition == null : this.currPosition.equals(that.currPosition));
    equal = equal && (this.oldPosition == null ? that.oldPosition == null : this.oldPosition.equals(that.oldPosition));
    equal = equal && (this.chapterSysName == null ? that.chapterSysName == null : this.chapterSysName.equals(that.chapterSysName));
    equal = equal && (this.chapterTitle == null ? that.chapterTitle == null : this.chapterTitle.equals(that.chapterTitle));
    equal = equal && (this.sessSysName == null ? that.sessSysName == null : this.sessSysName.equals(that.sessSysName));
    equal = equal && (this.sessTitle == null ? that.sessTitle == null : this.sessTitle.equals(that.sessTitle));
    equal = equal && (this.moduleSysName == null ? that.moduleSysName == null : this.moduleSysName.equals(that.moduleSysName));
    equal = equal && (this.moduleTitle == null ? that.moduleTitle == null : this.moduleTitle.equals(that.moduleTitle));
    equal = equal && (this.answerChoice == null ? that.answerChoice == null : this.answerChoice.equals(that.answerChoice));
    equal = equal && (this.success == null ? that.success == null : this.success.equals(that.success));
    equal = equal && (this.done == null ? that.done == null : this.done.equals(that.done));
    equal = equal && (this.enrolmentMode == null ? that.enrolmentMode == null : this.enrolmentMode.equals(that.enrolmentMode));
    equal = equal && (this.totDurationInSecs == null ? that.totDurationInSecs == null : this.totDurationInSecs.equals(that.totDurationInSecs));
    equal = equal && (this.eventNo == null ? that.eventNo == null : this.eventNo.equals(that.eventNo));
    equal = equal && (this.otherTitle == null ? that.otherTitle == null : this.otherTitle.equals(that.otherTitle));
    equal = equal && (this.otherType == null ? that.otherType == null : this.otherType.equals(that.otherType));
    equal = equal && (this.anonymous == null ? that.anonymous == null : this.anonymous.equals(that.anonymous));
    equal = equal && (this.anonymousToPeers == null ? that.anonymousToPeers == null : this.anonymousToPeers.equals(that.anonymousToPeers));
    equal = equal && (this.eduLevel == null ? that.eduLevel == null : this.eduLevel.equals(that.eduLevel));
    equal = equal && (this.gender == null ? that.gender == null : this.gender.equals(that.gender));
    equal = equal && (this.commentableId == null ? that.commentableId == null : this.commentableId.equals(that.commentableId));
    equal = equal && (this.commentType == null ? that.commentType == null : this.commentType.equals(that.commentType));
    equal = equal && (this.commentSysId == null ? that.commentSysId == null : this.commentSysId.equals(that.commentSysId));
    equal = equal && (this.aadhar == null ? that.aadhar == null : this.aadhar.equals(that.aadhar));
    equal = equal && (this.problemSubmissionTime == null ? that.problemSubmissionTime == null : this.problemSubmissionTime.equals(that.problemSubmissionTime));
    equal = equal && (this.hintMode == null ? that.hintMode == null : this.hintMode.equals(that.hintMode));
    equal = equal && (this.currentSeekTime == null ? that.currentSeekTime == null : this.currentSeekTime.equals(that.currentSeekTime));
    equal = equal && (this.queryText == null ? that.queryText == null : this.queryText.equals(that.queryText));
    equal = equal && (this.noOfResults == null ? that.noOfResults == null : this.noOfResults.equals(that.noOfResults));
    equal = equal && (this.lastModDateTime == null ? that.lastModDateTime == null : this.lastModDateTime.equals(that.lastModDateTime));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LogData)) {
      return false;
    }
    LogData that = (LogData) o;
    boolean equal = true;
    equal = equal && (this.sessionId == null ? that.sessionId == null : this.sessionId.equals(that.sessionId));
    equal = equal && (this.sessionSysName == null ? that.sessionSysName == null : this.sessionSysName.equals(that.sessionSysName));
    equal = equal && (this.lmsName == null ? that.lmsName == null : this.lmsName.equals(that.lmsName));
    equal = equal && (this.orgName == null ? that.orgName == null : this.orgName.equals(that.orgName));
    equal = equal && (this.courseName == null ? that.courseName == null : this.courseName.equals(that.courseName));
    equal = equal && (this.courseRun == null ? that.courseRun == null : this.courseRun.equals(that.courseRun));
    equal = equal && (this.lmsUserId == null ? that.lmsUserId == null : this.lmsUserId.equals(that.lmsUserId));
    equal = equal && (this.userName == null ? that.userName == null : this.userName.equals(that.userName));
    equal = equal && (this.agent == null ? that.agent == null : this.agent.equals(that.agent));
    equal = equal && (this.hostName == null ? that.hostName == null : this.hostName.equals(that.hostName));
    equal = equal && (this.ipAddress == null ? that.ipAddress == null : this.ipAddress.equals(that.ipAddress));
    equal = equal && (this.createDateTime == null ? that.createDateTime == null : this.createDateTime.equals(that.createDateTime));
    equal = equal && (this.eventType == null ? that.eventType == null : this.eventType.equals(that.eventType));
    equal = equal && (this.eventSource == null ? that.eventSource == null : this.eventSource.equals(that.eventSource));
    equal = equal && (this.eventName == null ? that.eventName == null : this.eventName.equals(that.eventName));
    equal = equal && (this.dataSource == null ? that.dataSource == null : this.dataSource.equals(that.dataSource));
    equal = equal && (this.oldVideoSpeed == null ? that.oldVideoSpeed == null : this.oldVideoSpeed.equals(that.oldVideoSpeed));
    equal = equal && (this.currVideoSpeed == null ? that.currVideoSpeed == null : this.currVideoSpeed.equals(that.currVideoSpeed));
    equal = equal && (this.oldVideoTime == null ? that.oldVideoTime == null : this.oldVideoTime.equals(that.oldVideoTime));
    equal = equal && (this.currVideoTime == null ? that.currVideoTime == null : this.currVideoTime.equals(that.currVideoTime));
    equal = equal && (this.videoNavigType == null ? that.videoNavigType == null : this.videoNavigType.equals(that.videoNavigType));
    equal = equal && (this.oldGrade == null ? that.oldGrade == null : this.oldGrade.equals(that.oldGrade));
    equal = equal && (this.currGrade == null ? that.currGrade == null : this.currGrade.equals(that.currGrade));
    equal = equal && (this.maxGrade == null ? that.maxGrade == null : this.maxGrade.equals(that.maxGrade));
    equal = equal && (this.attempts == null ? that.attempts == null : this.attempts.equals(that.attempts));
    equal = equal && (this.maxNoAttempts == null ? that.maxNoAttempts == null : this.maxNoAttempts.equals(that.maxNoAttempts));
    equal = equal && (this.hintAvailable == null ? that.hintAvailable == null : this.hintAvailable.equals(that.hintAvailable));
    equal = equal && (this.hintUsed == null ? that.hintUsed == null : this.hintUsed.equals(that.hintUsed));
    equal = equal && (this.currPosition == null ? that.currPosition == null : this.currPosition.equals(that.currPosition));
    equal = equal && (this.oldPosition == null ? that.oldPosition == null : this.oldPosition.equals(that.oldPosition));
    equal = equal && (this.chapterSysName == null ? that.chapterSysName == null : this.chapterSysName.equals(that.chapterSysName));
    equal = equal && (this.chapterTitle == null ? that.chapterTitle == null : this.chapterTitle.equals(that.chapterTitle));
    equal = equal && (this.sessSysName == null ? that.sessSysName == null : this.sessSysName.equals(that.sessSysName));
    equal = equal && (this.sessTitle == null ? that.sessTitle == null : this.sessTitle.equals(that.sessTitle));
    equal = equal && (this.moduleSysName == null ? that.moduleSysName == null : this.moduleSysName.equals(that.moduleSysName));
    equal = equal && (this.moduleTitle == null ? that.moduleTitle == null : this.moduleTitle.equals(that.moduleTitle));
    equal = equal && (this.answerChoice == null ? that.answerChoice == null : this.answerChoice.equals(that.answerChoice));
    equal = equal && (this.success == null ? that.success == null : this.success.equals(that.success));
    equal = equal && (this.done == null ? that.done == null : this.done.equals(that.done));
    equal = equal && (this.enrolmentMode == null ? that.enrolmentMode == null : this.enrolmentMode.equals(that.enrolmentMode));
    equal = equal && (this.totDurationInSecs == null ? that.totDurationInSecs == null : this.totDurationInSecs.equals(that.totDurationInSecs));
    equal = equal && (this.eventNo == null ? that.eventNo == null : this.eventNo.equals(that.eventNo));
    equal = equal && (this.otherTitle == null ? that.otherTitle == null : this.otherTitle.equals(that.otherTitle));
    equal = equal && (this.otherType == null ? that.otherType == null : this.otherType.equals(that.otherType));
    equal = equal && (this.anonymous == null ? that.anonymous == null : this.anonymous.equals(that.anonymous));
    equal = equal && (this.anonymousToPeers == null ? that.anonymousToPeers == null : this.anonymousToPeers.equals(that.anonymousToPeers));
    equal = equal && (this.eduLevel == null ? that.eduLevel == null : this.eduLevel.equals(that.eduLevel));
    equal = equal && (this.gender == null ? that.gender == null : this.gender.equals(that.gender));
    equal = equal && (this.commentableId == null ? that.commentableId == null : this.commentableId.equals(that.commentableId));
    equal = equal && (this.commentType == null ? that.commentType == null : this.commentType.equals(that.commentType));
    equal = equal && (this.commentSysId == null ? that.commentSysId == null : this.commentSysId.equals(that.commentSysId));
    equal = equal && (this.aadhar == null ? that.aadhar == null : this.aadhar.equals(that.aadhar));
    equal = equal && (this.problemSubmissionTime == null ? that.problemSubmissionTime == null : this.problemSubmissionTime.equals(that.problemSubmissionTime));
    equal = equal && (this.hintMode == null ? that.hintMode == null : this.hintMode.equals(that.hintMode));
    equal = equal && (this.currentSeekTime == null ? that.currentSeekTime == null : this.currentSeekTime.equals(that.currentSeekTime));
    equal = equal && (this.queryText == null ? that.queryText == null : this.queryText.equals(that.queryText));
    equal = equal && (this.noOfResults == null ? that.noOfResults == null : this.noOfResults.equals(that.noOfResults));
    equal = equal && (this.lastModDateTime == null ? that.lastModDateTime == null : this.lastModDateTime.equals(that.lastModDateTime));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.sessionId = JdbcWritableBridge.readLong(1, __dbResults);
    this.sessionSysName = JdbcWritableBridge.readString(2, __dbResults);
    this.lmsName = JdbcWritableBridge.readString(3, __dbResults);
    this.orgName = JdbcWritableBridge.readString(4, __dbResults);
    this.courseName = JdbcWritableBridge.readString(5, __dbResults);
    this.courseRun = JdbcWritableBridge.readString(6, __dbResults);
    this.lmsUserId = JdbcWritableBridge.readLong(7, __dbResults);
    this.userName = JdbcWritableBridge.readString(8, __dbResults);
    this.agent = JdbcWritableBridge.readString(9, __dbResults);
    this.hostName = JdbcWritableBridge.readString(10, __dbResults);
    this.ipAddress = JdbcWritableBridge.readString(11, __dbResults);
    this.createDateTime = JdbcWritableBridge.readTimestamp(12, __dbResults);
    this.eventType = JdbcWritableBridge.readString(13, __dbResults);
    this.eventSource = JdbcWritableBridge.readString(14, __dbResults);
    this.eventName = JdbcWritableBridge.readString(15, __dbResults);
    this.dataSource = JdbcWritableBridge.readString(16, __dbResults);
    this.oldVideoSpeed = JdbcWritableBridge.readFloat(17, __dbResults);
    this.currVideoSpeed = JdbcWritableBridge.readFloat(18, __dbResults);
    this.oldVideoTime = JdbcWritableBridge.readFloat(19, __dbResults);
    this.currVideoTime = JdbcWritableBridge.readFloat(20, __dbResults);
    this.videoNavigType = JdbcWritableBridge.readString(21, __dbResults);
    this.oldGrade = JdbcWritableBridge.readFloat(22, __dbResults);
    this.currGrade = JdbcWritableBridge.readFloat(23, __dbResults);
    this.maxGrade = JdbcWritableBridge.readFloat(24, __dbResults);
    this.attempts = JdbcWritableBridge.readInteger(25, __dbResults);
    this.maxNoAttempts = JdbcWritableBridge.readInteger(26, __dbResults);
    this.hintAvailable = JdbcWritableBridge.readString(27, __dbResults);
    this.hintUsed = JdbcWritableBridge.readString(28, __dbResults);
    this.currPosition = JdbcWritableBridge.readInteger(29, __dbResults);
    this.oldPosition = JdbcWritableBridge.readInteger(30, __dbResults);
    this.chapterSysName = JdbcWritableBridge.readString(31, __dbResults);
    this.chapterTitle = JdbcWritableBridge.readString(32, __dbResults);
    this.sessSysName = JdbcWritableBridge.readString(33, __dbResults);
    this.sessTitle = JdbcWritableBridge.readString(34, __dbResults);
    this.moduleSysName = JdbcWritableBridge.readString(35, __dbResults);
    this.moduleTitle = JdbcWritableBridge.readString(36, __dbResults);
    this.answerChoice = JdbcWritableBridge.readString(37, __dbResults);
    this.success = JdbcWritableBridge.readString(38, __dbResults);
    this.done = JdbcWritableBridge.readString(39, __dbResults);
    this.enrolmentMode = JdbcWritableBridge.readString(40, __dbResults);
    this.totDurationInSecs = JdbcWritableBridge.readInteger(41, __dbResults);
    this.eventNo = JdbcWritableBridge.readInteger(42, __dbResults);
    this.otherTitle = JdbcWritableBridge.readString(43, __dbResults);
    this.otherType = JdbcWritableBridge.readString(44, __dbResults);
    this.anonymous = JdbcWritableBridge.readString(45, __dbResults);
    this.anonymousToPeers = JdbcWritableBridge.readString(46, __dbResults);
    this.eduLevel = JdbcWritableBridge.readString(47, __dbResults);
    this.gender = JdbcWritableBridge.readString(48, __dbResults);
    this.commentableId = JdbcWritableBridge.readString(49, __dbResults);
    this.commentType = JdbcWritableBridge.readString(50, __dbResults);
    this.commentSysId = JdbcWritableBridge.readString(51, __dbResults);
    this.aadhar = JdbcWritableBridge.readString(52, __dbResults);
    this.problemSubmissionTime = JdbcWritableBridge.readTimestamp(53, __dbResults);
    this.hintMode = JdbcWritableBridge.readString(54, __dbResults);
    this.currentSeekTime = JdbcWritableBridge.readFloat(55, __dbResults);
    this.queryText = JdbcWritableBridge.readString(56, __dbResults);
    this.noOfResults = JdbcWritableBridge.readInteger(57, __dbResults);
    this.lastModDateTime = JdbcWritableBridge.readTimestamp(58, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.sessionId = JdbcWritableBridge.readLong(1, __dbResults);
    this.sessionSysName = JdbcWritableBridge.readString(2, __dbResults);
    this.lmsName = JdbcWritableBridge.readString(3, __dbResults);
    this.orgName = JdbcWritableBridge.readString(4, __dbResults);
    this.courseName = JdbcWritableBridge.readString(5, __dbResults);
    this.courseRun = JdbcWritableBridge.readString(6, __dbResults);
    this.lmsUserId = JdbcWritableBridge.readLong(7, __dbResults);
    this.userName = JdbcWritableBridge.readString(8, __dbResults);
    this.agent = JdbcWritableBridge.readString(9, __dbResults);
    this.hostName = JdbcWritableBridge.readString(10, __dbResults);
    this.ipAddress = JdbcWritableBridge.readString(11, __dbResults);
    this.createDateTime = JdbcWritableBridge.readTimestamp(12, __dbResults);
    this.eventType = JdbcWritableBridge.readString(13, __dbResults);
    this.eventSource = JdbcWritableBridge.readString(14, __dbResults);
    this.eventName = JdbcWritableBridge.readString(15, __dbResults);
    this.dataSource = JdbcWritableBridge.readString(16, __dbResults);
    this.oldVideoSpeed = JdbcWritableBridge.readFloat(17, __dbResults);
    this.currVideoSpeed = JdbcWritableBridge.readFloat(18, __dbResults);
    this.oldVideoTime = JdbcWritableBridge.readFloat(19, __dbResults);
    this.currVideoTime = JdbcWritableBridge.readFloat(20, __dbResults);
    this.videoNavigType = JdbcWritableBridge.readString(21, __dbResults);
    this.oldGrade = JdbcWritableBridge.readFloat(22, __dbResults);
    this.currGrade = JdbcWritableBridge.readFloat(23, __dbResults);
    this.maxGrade = JdbcWritableBridge.readFloat(24, __dbResults);
    this.attempts = JdbcWritableBridge.readInteger(25, __dbResults);
    this.maxNoAttempts = JdbcWritableBridge.readInteger(26, __dbResults);
    this.hintAvailable = JdbcWritableBridge.readString(27, __dbResults);
    this.hintUsed = JdbcWritableBridge.readString(28, __dbResults);
    this.currPosition = JdbcWritableBridge.readInteger(29, __dbResults);
    this.oldPosition = JdbcWritableBridge.readInteger(30, __dbResults);
    this.chapterSysName = JdbcWritableBridge.readString(31, __dbResults);
    this.chapterTitle = JdbcWritableBridge.readString(32, __dbResults);
    this.sessSysName = JdbcWritableBridge.readString(33, __dbResults);
    this.sessTitle = JdbcWritableBridge.readString(34, __dbResults);
    this.moduleSysName = JdbcWritableBridge.readString(35, __dbResults);
    this.moduleTitle = JdbcWritableBridge.readString(36, __dbResults);
    this.answerChoice = JdbcWritableBridge.readString(37, __dbResults);
    this.success = JdbcWritableBridge.readString(38, __dbResults);
    this.done = JdbcWritableBridge.readString(39, __dbResults);
    this.enrolmentMode = JdbcWritableBridge.readString(40, __dbResults);
    this.totDurationInSecs = JdbcWritableBridge.readInteger(41, __dbResults);
    this.eventNo = JdbcWritableBridge.readInteger(42, __dbResults);
    this.otherTitle = JdbcWritableBridge.readString(43, __dbResults);
    this.otherType = JdbcWritableBridge.readString(44, __dbResults);
    this.anonymous = JdbcWritableBridge.readString(45, __dbResults);
    this.anonymousToPeers = JdbcWritableBridge.readString(46, __dbResults);
    this.eduLevel = JdbcWritableBridge.readString(47, __dbResults);
    this.gender = JdbcWritableBridge.readString(48, __dbResults);
    this.commentableId = JdbcWritableBridge.readString(49, __dbResults);
    this.commentType = JdbcWritableBridge.readString(50, __dbResults);
    this.commentSysId = JdbcWritableBridge.readString(51, __dbResults);
    this.aadhar = JdbcWritableBridge.readString(52, __dbResults);
    this.problemSubmissionTime = JdbcWritableBridge.readTimestamp(53, __dbResults);
    this.hintMode = JdbcWritableBridge.readString(54, __dbResults);
    this.currentSeekTime = JdbcWritableBridge.readFloat(55, __dbResults);
    this.queryText = JdbcWritableBridge.readString(56, __dbResults);
    this.noOfResults = JdbcWritableBridge.readInteger(57, __dbResults);
    this.lastModDateTime = JdbcWritableBridge.readTimestamp(58, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(sessionId, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(sessionSysName, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(lmsName, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(orgName, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(courseName, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(courseRun, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(lmsUserId, 7 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(userName, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(agent, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(hostName, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ipAddress, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(createDateTime, 12 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(eventType, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(eventSource, 14 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(eventName, 15 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(dataSource, 16 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(oldVideoSpeed, 17 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(currVideoSpeed, 18 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(oldVideoTime, 19 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(currVideoTime, 20 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeString(videoNavigType, 21 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(oldGrade, 22 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(currGrade, 23 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(maxGrade, 24 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(attempts, 25 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(maxNoAttempts, 26 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(hintAvailable, 27 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(hintUsed, 28 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeInteger(currPosition, 29 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(oldPosition, 30 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(chapterSysName, 31 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(chapterTitle, 32 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sessSysName, 33 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sessTitle, 34 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(moduleSysName, 35 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(moduleTitle, 36 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(answerChoice, 37 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeString(success, 38 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(done, 39 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(enrolmentMode, 40 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(totDurationInSecs, 41 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(eventNo, 42 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeString(otherTitle, 43 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(otherType, 44 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(anonymous, 45 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(anonymousToPeers, 46 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(eduLevel, 47 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(gender, 48 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(commentableId, 49 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(commentType, 50 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(commentSysId, 51 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(aadhar, 52 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(problemSubmissionTime, 53 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(hintMode, 54 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(currentSeekTime, 55 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeString(queryText, 56 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeInteger(noOfResults, 57 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeTimestamp(lastModDateTime, 58 + __off, 93, __dbStmt);
    return 58;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeLong(sessionId, 1 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(sessionSysName, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(lmsName, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(orgName, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(courseName, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(courseRun, 6 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeLong(lmsUserId, 7 + __off, -5, __dbStmt);
    JdbcWritableBridge.writeString(userName, 8 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(agent, 9 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(hostName, 10 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ipAddress, 11 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(createDateTime, 12 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(eventType, 13 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(eventSource, 14 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(eventName, 15 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(dataSource, 16 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(oldVideoSpeed, 17 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(currVideoSpeed, 18 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(oldVideoTime, 19 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(currVideoTime, 20 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeString(videoNavigType, 21 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(oldGrade, 22 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(currGrade, 23 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeFloat(maxGrade, 24 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeInteger(attempts, 25 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(maxNoAttempts, 26 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(hintAvailable, 27 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(hintUsed, 28 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeInteger(currPosition, 29 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(oldPosition, 30 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(chapterSysName, 31 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(chapterTitle, 32 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sessSysName, 33 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sessTitle, 34 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(moduleSysName, 35 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(moduleTitle, 36 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(answerChoice, 37 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeString(success, 38 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(done, 39 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(enrolmentMode, 40 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(totDurationInSecs, 41 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(eventNo, 42 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeString(otherTitle, 43 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(otherType, 44 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(anonymous, 45 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(anonymousToPeers, 46 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(eduLevel, 47 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(gender, 48 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(commentableId, 49 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(commentType, 50 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(commentSysId, 51 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(aadhar, 52 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(problemSubmissionTime, 53 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeString(hintMode, 54 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeFloat(currentSeekTime, 55 + __off, 7, __dbStmt);
    JdbcWritableBridge.writeString(queryText, 56 + __off, -1, __dbStmt);
    JdbcWritableBridge.writeInteger(noOfResults, 57 + __off, 5, __dbStmt);
    JdbcWritableBridge.writeTimestamp(lastModDateTime, 58 + __off, 93, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.sessionId = null;
    } else {
    this.sessionId = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.sessionSysName = null;
    } else {
    this.sessionSysName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.lmsName = null;
    } else {
    this.lmsName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.orgName = null;
    } else {
    this.orgName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.courseName = null;
    } else {
    this.courseName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.courseRun = null;
    } else {
    this.courseRun = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.lmsUserId = null;
    } else {
    this.lmsUserId = Long.valueOf(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.userName = null;
    } else {
    this.userName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.agent = null;
    } else {
    this.agent = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.hostName = null;
    } else {
    this.hostName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ipAddress = null;
    } else {
    this.ipAddress = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.createDateTime = null;
    } else {
    this.createDateTime = new Timestamp(__dataIn.readLong());
    this.createDateTime.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.eventType = null;
    } else {
    this.eventType = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.eventSource = null;
    } else {
    this.eventSource = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.eventName = null;
    } else {
    this.eventName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.dataSource = null;
    } else {
    this.dataSource = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.oldVideoSpeed = null;
    } else {
    this.oldVideoSpeed = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.currVideoSpeed = null;
    } else {
    this.currVideoSpeed = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.oldVideoTime = null;
    } else {
    this.oldVideoTime = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.currVideoTime = null;
    } else {
    this.currVideoTime = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.videoNavigType = null;
    } else {
    this.videoNavigType = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.oldGrade = null;
    } else {
    this.oldGrade = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.currGrade = null;
    } else {
    this.currGrade = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.maxGrade = null;
    } else {
    this.maxGrade = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.attempts = null;
    } else {
    this.attempts = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.maxNoAttempts = null;
    } else {
    this.maxNoAttempts = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.hintAvailable = null;
    } else {
    this.hintAvailable = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.hintUsed = null;
    } else {
    this.hintUsed = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.currPosition = null;
    } else {
    this.currPosition = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.oldPosition = null;
    } else {
    this.oldPosition = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.chapterSysName = null;
    } else {
    this.chapterSysName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.chapterTitle = null;
    } else {
    this.chapterTitle = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.sessSysName = null;
    } else {
    this.sessSysName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.sessTitle = null;
    } else {
    this.sessTitle = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.moduleSysName = null;
    } else {
    this.moduleSysName = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.moduleTitle = null;
    } else {
    this.moduleTitle = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.answerChoice = null;
    } else {
    this.answerChoice = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.success = null;
    } else {
    this.success = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.done = null;
    } else {
    this.done = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.enrolmentMode = null;
    } else {
    this.enrolmentMode = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.totDurationInSecs = null;
    } else {
    this.totDurationInSecs = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.eventNo = null;
    } else {
    this.eventNo = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.otherTitle = null;
    } else {
    this.otherTitle = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.otherType = null;
    } else {
    this.otherType = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.anonymous = null;
    } else {
    this.anonymous = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.anonymousToPeers = null;
    } else {
    this.anonymousToPeers = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.eduLevel = null;
    } else {
    this.eduLevel = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.gender = null;
    } else {
    this.gender = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.commentableId = null;
    } else {
    this.commentableId = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.commentType = null;
    } else {
    this.commentType = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.commentSysId = null;
    } else {
    this.commentSysId = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.aadhar = null;
    } else {
    this.aadhar = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.problemSubmissionTime = null;
    } else {
    this.problemSubmissionTime = new Timestamp(__dataIn.readLong());
    this.problemSubmissionTime.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.hintMode = null;
    } else {
    this.hintMode = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.currentSeekTime = null;
    } else {
    this.currentSeekTime = Float.valueOf(__dataIn.readFloat());
    }
    if (__dataIn.readBoolean()) { 
        this.queryText = null;
    } else {
    this.queryText = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.noOfResults = null;
    } else {
    this.noOfResults = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.lastModDateTime = null;
    } else {
    this.lastModDateTime = new Timestamp(__dataIn.readLong());
    this.lastModDateTime.setNanos(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.sessionId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.sessionId);
    }
    if (null == this.sessionSysName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sessionSysName);
    }
    if (null == this.lmsName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, lmsName);
    }
    if (null == this.orgName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, orgName);
    }
    if (null == this.courseName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, courseName);
    }
    if (null == this.courseRun) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, courseRun);
    }
    if (null == this.lmsUserId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.lmsUserId);
    }
    if (null == this.userName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, userName);
    }
    if (null == this.agent) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, agent);
    }
    if (null == this.hostName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, hostName);
    }
    if (null == this.ipAddress) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ipAddress);
    }
    if (null == this.createDateTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.createDateTime.getTime());
    __dataOut.writeInt(this.createDateTime.getNanos());
    }
    if (null == this.eventType) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, eventType);
    }
    if (null == this.eventSource) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, eventSource);
    }
    if (null == this.eventName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, eventName);
    }
    if (null == this.dataSource) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, dataSource);
    }
    if (null == this.oldVideoSpeed) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.oldVideoSpeed);
    }
    if (null == this.currVideoSpeed) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.currVideoSpeed);
    }
    if (null == this.oldVideoTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.oldVideoTime);
    }
    if (null == this.currVideoTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.currVideoTime);
    }
    if (null == this.videoNavigType) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, videoNavigType);
    }
    if (null == this.oldGrade) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.oldGrade);
    }
    if (null == this.currGrade) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.currGrade);
    }
    if (null == this.maxGrade) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.maxGrade);
    }
    if (null == this.attempts) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.attempts);
    }
    if (null == this.maxNoAttempts) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.maxNoAttempts);
    }
    if (null == this.hintAvailable) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, hintAvailable);
    }
    if (null == this.hintUsed) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, hintUsed);
    }
    if (null == this.currPosition) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.currPosition);
    }
    if (null == this.oldPosition) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.oldPosition);
    }
    if (null == this.chapterSysName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, chapterSysName);
    }
    if (null == this.chapterTitle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, chapterTitle);
    }
    if (null == this.sessSysName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sessSysName);
    }
    if (null == this.sessTitle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sessTitle);
    }
    if (null == this.moduleSysName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, moduleSysName);
    }
    if (null == this.moduleTitle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, moduleTitle);
    }
    if (null == this.answerChoice) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, answerChoice);
    }
    if (null == this.success) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, success);
    }
    if (null == this.done) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, done);
    }
    if (null == this.enrolmentMode) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, enrolmentMode);
    }
    if (null == this.totDurationInSecs) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.totDurationInSecs);
    }
    if (null == this.eventNo) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.eventNo);
    }
    if (null == this.otherTitle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, otherTitle);
    }
    if (null == this.otherType) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, otherType);
    }
    if (null == this.anonymous) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, anonymous);
    }
    if (null == this.anonymousToPeers) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, anonymousToPeers);
    }
    if (null == this.eduLevel) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, eduLevel);
    }
    if (null == this.gender) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, gender);
    }
    if (null == this.commentableId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, commentableId);
    }
    if (null == this.commentType) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, commentType);
    }
    if (null == this.commentSysId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, commentSysId);
    }
    if (null == this.aadhar) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, aadhar);
    }
    if (null == this.problemSubmissionTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.problemSubmissionTime.getTime());
    __dataOut.writeInt(this.problemSubmissionTime.getNanos());
    }
    if (null == this.hintMode) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, hintMode);
    }
    if (null == this.currentSeekTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.currentSeekTime);
    }
    if (null == this.queryText) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, queryText);
    }
    if (null == this.noOfResults) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.noOfResults);
    }
    if (null == this.lastModDateTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.lastModDateTime.getTime());
    __dataOut.writeInt(this.lastModDateTime.getNanos());
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.sessionId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.sessionId);
    }
    if (null == this.sessionSysName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sessionSysName);
    }
    if (null == this.lmsName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, lmsName);
    }
    if (null == this.orgName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, orgName);
    }
    if (null == this.courseName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, courseName);
    }
    if (null == this.courseRun) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, courseRun);
    }
    if (null == this.lmsUserId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.lmsUserId);
    }
    if (null == this.userName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, userName);
    }
    if (null == this.agent) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, agent);
    }
    if (null == this.hostName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, hostName);
    }
    if (null == this.ipAddress) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ipAddress);
    }
    if (null == this.createDateTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.createDateTime.getTime());
    __dataOut.writeInt(this.createDateTime.getNanos());
    }
    if (null == this.eventType) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, eventType);
    }
    if (null == this.eventSource) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, eventSource);
    }
    if (null == this.eventName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, eventName);
    }
    if (null == this.dataSource) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, dataSource);
    }
    if (null == this.oldVideoSpeed) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.oldVideoSpeed);
    }
    if (null == this.currVideoSpeed) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.currVideoSpeed);
    }
    if (null == this.oldVideoTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.oldVideoTime);
    }
    if (null == this.currVideoTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.currVideoTime);
    }
    if (null == this.videoNavigType) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, videoNavigType);
    }
    if (null == this.oldGrade) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.oldGrade);
    }
    if (null == this.currGrade) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.currGrade);
    }
    if (null == this.maxGrade) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.maxGrade);
    }
    if (null == this.attempts) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.attempts);
    }
    if (null == this.maxNoAttempts) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.maxNoAttempts);
    }
    if (null == this.hintAvailable) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, hintAvailable);
    }
    if (null == this.hintUsed) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, hintUsed);
    }
    if (null == this.currPosition) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.currPosition);
    }
    if (null == this.oldPosition) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.oldPosition);
    }
    if (null == this.chapterSysName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, chapterSysName);
    }
    if (null == this.chapterTitle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, chapterTitle);
    }
    if (null == this.sessSysName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sessSysName);
    }
    if (null == this.sessTitle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sessTitle);
    }
    if (null == this.moduleSysName) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, moduleSysName);
    }
    if (null == this.moduleTitle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, moduleTitle);
    }
    if (null == this.answerChoice) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, answerChoice);
    }
    if (null == this.success) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, success);
    }
    if (null == this.done) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, done);
    }
    if (null == this.enrolmentMode) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, enrolmentMode);
    }
    if (null == this.totDurationInSecs) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.totDurationInSecs);
    }
    if (null == this.eventNo) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.eventNo);
    }
    if (null == this.otherTitle) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, otherTitle);
    }
    if (null == this.otherType) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, otherType);
    }
    if (null == this.anonymous) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, anonymous);
    }
    if (null == this.anonymousToPeers) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, anonymousToPeers);
    }
    if (null == this.eduLevel) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, eduLevel);
    }
    if (null == this.gender) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, gender);
    }
    if (null == this.commentableId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, commentableId);
    }
    if (null == this.commentType) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, commentType);
    }
    if (null == this.commentSysId) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, commentSysId);
    }
    if (null == this.aadhar) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, aadhar);
    }
    if (null == this.problemSubmissionTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.problemSubmissionTime.getTime());
    __dataOut.writeInt(this.problemSubmissionTime.getNanos());
    }
    if (null == this.hintMode) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, hintMode);
    }
    if (null == this.currentSeekTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeFloat(this.currentSeekTime);
    }
    if (null == this.queryText) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, queryText);
    }
    if (null == this.noOfResults) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.noOfResults);
    }
    if (null == this.lastModDateTime) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.lastModDateTime.getTime());
    __dataOut.writeInt(this.lastModDateTime.getNanos());
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(sessionId==null?"\\N":"" + sessionId, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(sessionSysName==null?"\\N":sessionSysName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(lmsName==null?"\\N":lmsName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(orgName==null?"\\N":orgName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(courseName==null?"\\N":courseName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(courseRun==null?"\\N":courseRun, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lmsUserId==null?"\\N":"" + lmsUserId, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(userName==null?"\\N":userName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(agent==null?"\\N":agent, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(hostName==null?"\\N":hostName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(ipAddress==null?"\\N":ipAddress, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(createDateTime==null?"\\N":"" + createDateTime, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(eventType==null?"\\N":eventType, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(eventSource==null?"\\N":eventSource, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(eventName==null?"\\N":eventName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(dataSource==null?"\\N":dataSource, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(oldVideoSpeed==null?"\\N":"" + oldVideoSpeed, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currVideoSpeed==null?"\\N":"" + currVideoSpeed, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(oldVideoTime==null?"\\N":"" + oldVideoTime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currVideoTime==null?"\\N":"" + currVideoTime, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(videoNavigType==null?"\\N":videoNavigType, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(oldGrade==null?"\\N":"" + oldGrade, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currGrade==null?"\\N":"" + currGrade, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(maxGrade==null?"\\N":"" + maxGrade, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(attempts==null?"\\N":"" + attempts, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(maxNoAttempts==null?"\\N":"" + maxNoAttempts, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(hintAvailable==null?"\\N":hintAvailable, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(hintUsed==null?"\\N":hintUsed, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currPosition==null?"\\N":"" + currPosition, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(oldPosition==null?"\\N":"" + oldPosition, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(chapterSysName==null?"\\N":chapterSysName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(chapterTitle==null?"\\N":chapterTitle, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(sessSysName==null?"\\N":sessSysName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(sessTitle==null?"\\N":sessTitle, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(moduleSysName==null?"\\N":moduleSysName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(moduleTitle==null?"\\N":moduleTitle, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(answerChoice==null?"\\N":answerChoice, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(success==null?"\\N":success, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(done==null?"\\N":done, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(enrolmentMode==null?"\\N":enrolmentMode, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(totDurationInSecs==null?"\\N":"" + totDurationInSecs, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(eventNo==null?"\\N":"" + eventNo, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(otherTitle==null?"\\N":otherTitle, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(otherType==null?"\\N":otherType, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(anonymous==null?"\\N":anonymous, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(anonymousToPeers==null?"\\N":anonymousToPeers, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(eduLevel==null?"\\N":eduLevel, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(gender==null?"\\N":gender, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(commentableId==null?"\\N":commentableId, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(commentType==null?"\\N":commentType, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(commentSysId==null?"\\N":commentSysId, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(aadhar==null?"\\N":aadhar, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(problemSubmissionTime==null?"\\N":"" + problemSubmissionTime, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(hintMode==null?"\\N":hintMode, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currentSeekTime==null?"\\N":"" + currentSeekTime, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(queryText==null?"\\N":queryText, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(noOfResults==null?"\\N":"" + noOfResults, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lastModDateTime==null?"\\N":"" + lastModDateTime, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(sessionId==null?"\\N":"" + sessionId, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(sessionSysName==null?"\\N":sessionSysName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(lmsName==null?"\\N":lmsName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(orgName==null?"\\N":orgName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(courseName==null?"\\N":courseName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(courseRun==null?"\\N":courseRun, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lmsUserId==null?"\\N":"" + lmsUserId, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(userName==null?"\\N":userName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(agent==null?"\\N":agent, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(hostName==null?"\\N":hostName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(ipAddress==null?"\\N":ipAddress, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(createDateTime==null?"\\N":"" + createDateTime, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(eventType==null?"\\N":eventType, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(eventSource==null?"\\N":eventSource, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(eventName==null?"\\N":eventName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(dataSource==null?"\\N":dataSource, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(oldVideoSpeed==null?"\\N":"" + oldVideoSpeed, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currVideoSpeed==null?"\\N":"" + currVideoSpeed, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(oldVideoTime==null?"\\N":"" + oldVideoTime, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currVideoTime==null?"\\N":"" + currVideoTime, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(videoNavigType==null?"\\N":videoNavigType, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(oldGrade==null?"\\N":"" + oldGrade, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currGrade==null?"\\N":"" + currGrade, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(maxGrade==null?"\\N":"" + maxGrade, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(attempts==null?"\\N":"" + attempts, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(maxNoAttempts==null?"\\N":"" + maxNoAttempts, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(hintAvailable==null?"\\N":hintAvailable, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(hintUsed==null?"\\N":hintUsed, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currPosition==null?"\\N":"" + currPosition, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(oldPosition==null?"\\N":"" + oldPosition, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(chapterSysName==null?"\\N":chapterSysName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(chapterTitle==null?"\\N":chapterTitle, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(sessSysName==null?"\\N":sessSysName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(sessTitle==null?"\\N":sessTitle, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(moduleSysName==null?"\\N":moduleSysName, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(moduleTitle==null?"\\N":moduleTitle, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(answerChoice==null?"\\N":answerChoice, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(success==null?"\\N":success, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(done==null?"\\N":done, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(enrolmentMode==null?"\\N":enrolmentMode, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(totDurationInSecs==null?"\\N":"" + totDurationInSecs, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(eventNo==null?"\\N":"" + eventNo, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(otherTitle==null?"\\N":otherTitle, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(otherType==null?"\\N":otherType, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(anonymous==null?"\\N":anonymous, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(anonymousToPeers==null?"\\N":anonymousToPeers, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(eduLevel==null?"\\N":eduLevel, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(gender==null?"\\N":gender, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(commentableId==null?"\\N":commentableId, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(commentType==null?"\\N":commentType, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(commentSysId==null?"\\N":commentSysId, " ", delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(aadhar==null?"\\N":aadhar, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(problemSubmissionTime==null?"\\N":"" + problemSubmissionTime, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(hintMode==null?"\\N":hintMode, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(currentSeekTime==null?"\\N":"" + currentSeekTime, delimiters));
    __sb.append(fieldDelim);
    // special case for strings hive, replacing delimiters \n,\r,\01 with ' ' from strings
    __sb.append(FieldFormatter.hiveStringReplaceDelims(queryText==null?"\\N":queryText, " ", delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(noOfResults==null?"\\N":"" + noOfResults, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lastModDateTime==null?"\\N":"" + lastModDateTime, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.sessionId = null; } else {
      this.sessionId = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.sessionSysName = null; } else {
      this.sessionSysName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.lmsName = null; } else {
      this.lmsName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.orgName = null; } else {
      this.orgName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.courseName = null; } else {
      this.courseName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.courseRun = null; } else {
      this.courseRun = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lmsUserId = null; } else {
      this.lmsUserId = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.userName = null; } else {
      this.userName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.agent = null; } else {
      this.agent = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.hostName = null; } else {
      this.hostName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ipAddress = null; } else {
      this.ipAddress = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.createDateTime = null; } else {
      this.createDateTime = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.eventType = null; } else {
      this.eventType = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.eventSource = null; } else {
      this.eventSource = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.eventName = null; } else {
      this.eventName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.dataSource = null; } else {
      this.dataSource = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.oldVideoSpeed = null; } else {
      this.oldVideoSpeed = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currVideoSpeed = null; } else {
      this.currVideoSpeed = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.oldVideoTime = null; } else {
      this.oldVideoTime = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currVideoTime = null; } else {
      this.currVideoTime = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.videoNavigType = null; } else {
      this.videoNavigType = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.oldGrade = null; } else {
      this.oldGrade = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currGrade = null; } else {
      this.currGrade = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.maxGrade = null; } else {
      this.maxGrade = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.attempts = null; } else {
      this.attempts = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.maxNoAttempts = null; } else {
      this.maxNoAttempts = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.hintAvailable = null; } else {
      this.hintAvailable = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.hintUsed = null; } else {
      this.hintUsed = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currPosition = null; } else {
      this.currPosition = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.oldPosition = null; } else {
      this.oldPosition = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.chapterSysName = null; } else {
      this.chapterSysName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.chapterTitle = null; } else {
      this.chapterTitle = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.sessSysName = null; } else {
      this.sessSysName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.sessTitle = null; } else {
      this.sessTitle = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.moduleSysName = null; } else {
      this.moduleSysName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.moduleTitle = null; } else {
      this.moduleTitle = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.answerChoice = null; } else {
      this.answerChoice = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.success = null; } else {
      this.success = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.done = null; } else {
      this.done = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.enrolmentMode = null; } else {
      this.enrolmentMode = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.totDurationInSecs = null; } else {
      this.totDurationInSecs = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.eventNo = null; } else {
      this.eventNo = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.otherTitle = null; } else {
      this.otherTitle = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.otherType = null; } else {
      this.otherType = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.anonymous = null; } else {
      this.anonymous = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.anonymousToPeers = null; } else {
      this.anonymousToPeers = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.eduLevel = null; } else {
      this.eduLevel = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.gender = null; } else {
      this.gender = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.commentableId = null; } else {
      this.commentableId = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.commentType = null; } else {
      this.commentType = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.commentSysId = null; } else {
      this.commentSysId = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.aadhar = null; } else {
      this.aadhar = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.problemSubmissionTime = null; } else {
      this.problemSubmissionTime = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.hintMode = null; } else {
      this.hintMode = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currentSeekTime = null; } else {
      this.currentSeekTime = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.queryText = null; } else {
      this.queryText = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.noOfResults = null; } else {
      this.noOfResults = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lastModDateTime = null; } else {
      this.lastModDateTime = java.sql.Timestamp.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.sessionId = null; } else {
      this.sessionId = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.sessionSysName = null; } else {
      this.sessionSysName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.lmsName = null; } else {
      this.lmsName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.orgName = null; } else {
      this.orgName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.courseName = null; } else {
      this.courseName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.courseRun = null; } else {
      this.courseRun = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lmsUserId = null; } else {
      this.lmsUserId = Long.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.userName = null; } else {
      this.userName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.agent = null; } else {
      this.agent = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.hostName = null; } else {
      this.hostName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.ipAddress = null; } else {
      this.ipAddress = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.createDateTime = null; } else {
      this.createDateTime = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.eventType = null; } else {
      this.eventType = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.eventSource = null; } else {
      this.eventSource = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.eventName = null; } else {
      this.eventName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.dataSource = null; } else {
      this.dataSource = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.oldVideoSpeed = null; } else {
      this.oldVideoSpeed = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currVideoSpeed = null; } else {
      this.currVideoSpeed = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.oldVideoTime = null; } else {
      this.oldVideoTime = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currVideoTime = null; } else {
      this.currVideoTime = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.videoNavigType = null; } else {
      this.videoNavigType = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.oldGrade = null; } else {
      this.oldGrade = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currGrade = null; } else {
      this.currGrade = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.maxGrade = null; } else {
      this.maxGrade = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.attempts = null; } else {
      this.attempts = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.maxNoAttempts = null; } else {
      this.maxNoAttempts = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.hintAvailable = null; } else {
      this.hintAvailable = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.hintUsed = null; } else {
      this.hintUsed = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currPosition = null; } else {
      this.currPosition = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.oldPosition = null; } else {
      this.oldPosition = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.chapterSysName = null; } else {
      this.chapterSysName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.chapterTitle = null; } else {
      this.chapterTitle = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.sessSysName = null; } else {
      this.sessSysName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.sessTitle = null; } else {
      this.sessTitle = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.moduleSysName = null; } else {
      this.moduleSysName = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.moduleTitle = null; } else {
      this.moduleTitle = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.answerChoice = null; } else {
      this.answerChoice = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.success = null; } else {
      this.success = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.done = null; } else {
      this.done = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.enrolmentMode = null; } else {
      this.enrolmentMode = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.totDurationInSecs = null; } else {
      this.totDurationInSecs = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.eventNo = null; } else {
      this.eventNo = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.otherTitle = null; } else {
      this.otherTitle = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.otherType = null; } else {
      this.otherType = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.anonymous = null; } else {
      this.anonymous = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.anonymousToPeers = null; } else {
      this.anonymousToPeers = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.eduLevel = null; } else {
      this.eduLevel = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.gender = null; } else {
      this.gender = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.commentableId = null; } else {
      this.commentableId = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.commentType = null; } else {
      this.commentType = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.commentSysId = null; } else {
      this.commentSysId = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.aadhar = null; } else {
      this.aadhar = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.problemSubmissionTime = null; } else {
      this.problemSubmissionTime = java.sql.Timestamp.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.hintMode = null; } else {
      this.hintMode = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.currentSeekTime = null; } else {
      this.currentSeekTime = Float.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null")) { this.queryText = null; } else {
      this.queryText = __cur_str;
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.noOfResults = null; } else {
      this.noOfResults = Integer.valueOf(__cur_str);
    }

    __cur_str = __it.next();
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lastModDateTime = null; } else {
      this.lastModDateTime = java.sql.Timestamp.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    LogData o = (LogData) super.clone();
    o.createDateTime = (o.createDateTime != null) ? (java.sql.Timestamp) o.createDateTime.clone() : null;
    o.problemSubmissionTime = (o.problemSubmissionTime != null) ? (java.sql.Timestamp) o.problemSubmissionTime.clone() : null;
    o.lastModDateTime = (o.lastModDateTime != null) ? (java.sql.Timestamp) o.lastModDateTime.clone() : null;
    return o;
  }

  public void clone0(LogData o) throws CloneNotSupportedException {
    o.createDateTime = (o.createDateTime != null) ? (java.sql.Timestamp) o.createDateTime.clone() : null;
    o.problemSubmissionTime = (o.problemSubmissionTime != null) ? (java.sql.Timestamp) o.problemSubmissionTime.clone() : null;
    o.lastModDateTime = (o.lastModDateTime != null) ? (java.sql.Timestamp) o.lastModDateTime.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new TreeMap<String, Object>();
    __sqoop$field_map.put("sessionId", this.sessionId);
    __sqoop$field_map.put("sessionSysName", this.sessionSysName);
    __sqoop$field_map.put("lmsName", this.lmsName);
    __sqoop$field_map.put("orgName", this.orgName);
    __sqoop$field_map.put("courseName", this.courseName);
    __sqoop$field_map.put("courseRun", this.courseRun);
    __sqoop$field_map.put("lmsUserId", this.lmsUserId);
    __sqoop$field_map.put("userName", this.userName);
    __sqoop$field_map.put("agent", this.agent);
    __sqoop$field_map.put("hostName", this.hostName);
    __sqoop$field_map.put("ipAddress", this.ipAddress);
    __sqoop$field_map.put("createDateTime", this.createDateTime);
    __sqoop$field_map.put("eventType", this.eventType);
    __sqoop$field_map.put("eventSource", this.eventSource);
    __sqoop$field_map.put("eventName", this.eventName);
    __sqoop$field_map.put("dataSource", this.dataSource);
    __sqoop$field_map.put("oldVideoSpeed", this.oldVideoSpeed);
    __sqoop$field_map.put("currVideoSpeed", this.currVideoSpeed);
    __sqoop$field_map.put("oldVideoTime", this.oldVideoTime);
    __sqoop$field_map.put("currVideoTime", this.currVideoTime);
    __sqoop$field_map.put("videoNavigType", this.videoNavigType);
    __sqoop$field_map.put("oldGrade", this.oldGrade);
    __sqoop$field_map.put("currGrade", this.currGrade);
    __sqoop$field_map.put("maxGrade", this.maxGrade);
    __sqoop$field_map.put("attempts", this.attempts);
    __sqoop$field_map.put("maxNoAttempts", this.maxNoAttempts);
    __sqoop$field_map.put("hintAvailable", this.hintAvailable);
    __sqoop$field_map.put("hintUsed", this.hintUsed);
    __sqoop$field_map.put("currPosition", this.currPosition);
    __sqoop$field_map.put("oldPosition", this.oldPosition);
    __sqoop$field_map.put("chapterSysName", this.chapterSysName);
    __sqoop$field_map.put("chapterTitle", this.chapterTitle);
    __sqoop$field_map.put("sessSysName", this.sessSysName);
    __sqoop$field_map.put("sessTitle", this.sessTitle);
    __sqoop$field_map.put("moduleSysName", this.moduleSysName);
    __sqoop$field_map.put("moduleTitle", this.moduleTitle);
    __sqoop$field_map.put("answerChoice", this.answerChoice);
    __sqoop$field_map.put("success", this.success);
    __sqoop$field_map.put("done", this.done);
    __sqoop$field_map.put("enrolmentMode", this.enrolmentMode);
    __sqoop$field_map.put("totDurationInSecs", this.totDurationInSecs);
    __sqoop$field_map.put("eventNo", this.eventNo);
    __sqoop$field_map.put("otherTitle", this.otherTitle);
    __sqoop$field_map.put("otherType", this.otherType);
    __sqoop$field_map.put("anonymous", this.anonymous);
    __sqoop$field_map.put("anonymousToPeers", this.anonymousToPeers);
    __sqoop$field_map.put("eduLevel", this.eduLevel);
    __sqoop$field_map.put("gender", this.gender);
    __sqoop$field_map.put("commentableId", this.commentableId);
    __sqoop$field_map.put("commentType", this.commentType);
    __sqoop$field_map.put("commentSysId", this.commentSysId);
    __sqoop$field_map.put("aadhar", this.aadhar);
    __sqoop$field_map.put("problemSubmissionTime", this.problemSubmissionTime);
    __sqoop$field_map.put("hintMode", this.hintMode);
    __sqoop$field_map.put("currentSeekTime", this.currentSeekTime);
    __sqoop$field_map.put("queryText", this.queryText);
    __sqoop$field_map.put("noOfResults", this.noOfResults);
    __sqoop$field_map.put("lastModDateTime", this.lastModDateTime);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("sessionId", this.sessionId);
    __sqoop$field_map.put("sessionSysName", this.sessionSysName);
    __sqoop$field_map.put("lmsName", this.lmsName);
    __sqoop$field_map.put("orgName", this.orgName);
    __sqoop$field_map.put("courseName", this.courseName);
    __sqoop$field_map.put("courseRun", this.courseRun);
    __sqoop$field_map.put("lmsUserId", this.lmsUserId);
    __sqoop$field_map.put("userName", this.userName);
    __sqoop$field_map.put("agent", this.agent);
    __sqoop$field_map.put("hostName", this.hostName);
    __sqoop$field_map.put("ipAddress", this.ipAddress);
    __sqoop$field_map.put("createDateTime", this.createDateTime);
    __sqoop$field_map.put("eventType", this.eventType);
    __sqoop$field_map.put("eventSource", this.eventSource);
    __sqoop$field_map.put("eventName", this.eventName);
    __sqoop$field_map.put("dataSource", this.dataSource);
    __sqoop$field_map.put("oldVideoSpeed", this.oldVideoSpeed);
    __sqoop$field_map.put("currVideoSpeed", this.currVideoSpeed);
    __sqoop$field_map.put("oldVideoTime", this.oldVideoTime);
    __sqoop$field_map.put("currVideoTime", this.currVideoTime);
    __sqoop$field_map.put("videoNavigType", this.videoNavigType);
    __sqoop$field_map.put("oldGrade", this.oldGrade);
    __sqoop$field_map.put("currGrade", this.currGrade);
    __sqoop$field_map.put("maxGrade", this.maxGrade);
    __sqoop$field_map.put("attempts", this.attempts);
    __sqoop$field_map.put("maxNoAttempts", this.maxNoAttempts);
    __sqoop$field_map.put("hintAvailable", this.hintAvailable);
    __sqoop$field_map.put("hintUsed", this.hintUsed);
    __sqoop$field_map.put("currPosition", this.currPosition);
    __sqoop$field_map.put("oldPosition", this.oldPosition);
    __sqoop$field_map.put("chapterSysName", this.chapterSysName);
    __sqoop$field_map.put("chapterTitle", this.chapterTitle);
    __sqoop$field_map.put("sessSysName", this.sessSysName);
    __sqoop$field_map.put("sessTitle", this.sessTitle);
    __sqoop$field_map.put("moduleSysName", this.moduleSysName);
    __sqoop$field_map.put("moduleTitle", this.moduleTitle);
    __sqoop$field_map.put("answerChoice", this.answerChoice);
    __sqoop$field_map.put("success", this.success);
    __sqoop$field_map.put("done", this.done);
    __sqoop$field_map.put("enrolmentMode", this.enrolmentMode);
    __sqoop$field_map.put("totDurationInSecs", this.totDurationInSecs);
    __sqoop$field_map.put("eventNo", this.eventNo);
    __sqoop$field_map.put("otherTitle", this.otherTitle);
    __sqoop$field_map.put("otherType", this.otherType);
    __sqoop$field_map.put("anonymous", this.anonymous);
    __sqoop$field_map.put("anonymousToPeers", this.anonymousToPeers);
    __sqoop$field_map.put("eduLevel", this.eduLevel);
    __sqoop$field_map.put("gender", this.gender);
    __sqoop$field_map.put("commentableId", this.commentableId);
    __sqoop$field_map.put("commentType", this.commentType);
    __sqoop$field_map.put("commentSysId", this.commentSysId);
    __sqoop$field_map.put("aadhar", this.aadhar);
    __sqoop$field_map.put("problemSubmissionTime", this.problemSubmissionTime);
    __sqoop$field_map.put("hintMode", this.hintMode);
    __sqoop$field_map.put("currentSeekTime", this.currentSeekTime);
    __sqoop$field_map.put("queryText", this.queryText);
    __sqoop$field_map.put("noOfResults", this.noOfResults);
    __sqoop$field_map.put("lastModDateTime", this.lastModDateTime);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if ("sessionId".equals(__fieldName)) {
      this.sessionId = (Long) __fieldVal;
    }
    else    if ("sessionSysName".equals(__fieldName)) {
      this.sessionSysName = (String) __fieldVal;
    }
    else    if ("lmsName".equals(__fieldName)) {
      this.lmsName = (String) __fieldVal;
    }
    else    if ("orgName".equals(__fieldName)) {
      this.orgName = (String) __fieldVal;
    }
    else    if ("courseName".equals(__fieldName)) {
      this.courseName = (String) __fieldVal;
    }
    else    if ("courseRun".equals(__fieldName)) {
      this.courseRun = (String) __fieldVal;
    }
    else    if ("lmsUserId".equals(__fieldName)) {
      this.lmsUserId = (Long) __fieldVal;
    }
    else    if ("userName".equals(__fieldName)) {
      this.userName = (String) __fieldVal;
    }
    else    if ("agent".equals(__fieldName)) {
      this.agent = (String) __fieldVal;
    }
    else    if ("hostName".equals(__fieldName)) {
      this.hostName = (String) __fieldVal;
    }
    else    if ("ipAddress".equals(__fieldName)) {
      this.ipAddress = (String) __fieldVal;
    }
    else    if ("createDateTime".equals(__fieldName)) {
      this.createDateTime = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("eventType".equals(__fieldName)) {
      this.eventType = (String) __fieldVal;
    }
    else    if ("eventSource".equals(__fieldName)) {
      this.eventSource = (String) __fieldVal;
    }
    else    if ("eventName".equals(__fieldName)) {
      this.eventName = (String) __fieldVal;
    }
    else    if ("dataSource".equals(__fieldName)) {
      this.dataSource = (String) __fieldVal;
    }
    else    if ("oldVideoSpeed".equals(__fieldName)) {
      this.oldVideoSpeed = (Float) __fieldVal;
    }
    else    if ("currVideoSpeed".equals(__fieldName)) {
      this.currVideoSpeed = (Float) __fieldVal;
    }
    else    if ("oldVideoTime".equals(__fieldName)) {
      this.oldVideoTime = (Float) __fieldVal;
    }
    else    if ("currVideoTime".equals(__fieldName)) {
      this.currVideoTime = (Float) __fieldVal;
    }
    else    if ("videoNavigType".equals(__fieldName)) {
      this.videoNavigType = (String) __fieldVal;
    }
    else    if ("oldGrade".equals(__fieldName)) {
      this.oldGrade = (Float) __fieldVal;
    }
    else    if ("currGrade".equals(__fieldName)) {
      this.currGrade = (Float) __fieldVal;
    }
    else    if ("maxGrade".equals(__fieldName)) {
      this.maxGrade = (Float) __fieldVal;
    }
    else    if ("attempts".equals(__fieldName)) {
      this.attempts = (Integer) __fieldVal;
    }
    else    if ("maxNoAttempts".equals(__fieldName)) {
      this.maxNoAttempts = (Integer) __fieldVal;
    }
    else    if ("hintAvailable".equals(__fieldName)) {
      this.hintAvailable = (String) __fieldVal;
    }
    else    if ("hintUsed".equals(__fieldName)) {
      this.hintUsed = (String) __fieldVal;
    }
    else    if ("currPosition".equals(__fieldName)) {
      this.currPosition = (Integer) __fieldVal;
    }
    else    if ("oldPosition".equals(__fieldName)) {
      this.oldPosition = (Integer) __fieldVal;
    }
    else    if ("chapterSysName".equals(__fieldName)) {
      this.chapterSysName = (String) __fieldVal;
    }
    else    if ("chapterTitle".equals(__fieldName)) {
      this.chapterTitle = (String) __fieldVal;
    }
    else    if ("sessSysName".equals(__fieldName)) {
      this.sessSysName = (String) __fieldVal;
    }
    else    if ("sessTitle".equals(__fieldName)) {
      this.sessTitle = (String) __fieldVal;
    }
    else    if ("moduleSysName".equals(__fieldName)) {
      this.moduleSysName = (String) __fieldVal;
    }
    else    if ("moduleTitle".equals(__fieldName)) {
      this.moduleTitle = (String) __fieldVal;
    }
    else    if ("answerChoice".equals(__fieldName)) {
      this.answerChoice = (String) __fieldVal;
    }
    else    if ("success".equals(__fieldName)) {
      this.success = (String) __fieldVal;
    }
    else    if ("done".equals(__fieldName)) {
      this.done = (String) __fieldVal;
    }
    else    if ("enrolmentMode".equals(__fieldName)) {
      this.enrolmentMode = (String) __fieldVal;
    }
    else    if ("totDurationInSecs".equals(__fieldName)) {
      this.totDurationInSecs = (Integer) __fieldVal;
    }
    else    if ("eventNo".equals(__fieldName)) {
      this.eventNo = (Integer) __fieldVal;
    }
    else    if ("otherTitle".equals(__fieldName)) {
      this.otherTitle = (String) __fieldVal;
    }
    else    if ("otherType".equals(__fieldName)) {
      this.otherType = (String) __fieldVal;
    }
    else    if ("anonymous".equals(__fieldName)) {
      this.anonymous = (String) __fieldVal;
    }
    else    if ("anonymousToPeers".equals(__fieldName)) {
      this.anonymousToPeers = (String) __fieldVal;
    }
    else    if ("eduLevel".equals(__fieldName)) {
      this.eduLevel = (String) __fieldVal;
    }
    else    if ("gender".equals(__fieldName)) {
      this.gender = (String) __fieldVal;
    }
    else    if ("commentableId".equals(__fieldName)) {
      this.commentableId = (String) __fieldVal;
    }
    else    if ("commentType".equals(__fieldName)) {
      this.commentType = (String) __fieldVal;
    }
    else    if ("commentSysId".equals(__fieldName)) {
      this.commentSysId = (String) __fieldVal;
    }
    else    if ("aadhar".equals(__fieldName)) {
      this.aadhar = (String) __fieldVal;
    }
    else    if ("problemSubmissionTime".equals(__fieldName)) {
      this.problemSubmissionTime = (java.sql.Timestamp) __fieldVal;
    }
    else    if ("hintMode".equals(__fieldName)) {
      this.hintMode = (String) __fieldVal;
    }
    else    if ("currentSeekTime".equals(__fieldName)) {
      this.currentSeekTime = (Float) __fieldVal;
    }
    else    if ("queryText".equals(__fieldName)) {
      this.queryText = (String) __fieldVal;
    }
    else    if ("noOfResults".equals(__fieldName)) {
      this.noOfResults = (Integer) __fieldVal;
    }
    else    if ("lastModDateTime".equals(__fieldName)) {
      this.lastModDateTime = (java.sql.Timestamp) __fieldVal;
    }
    else {
      throw new RuntimeException("No such field: " + __fieldName);
    }
  }
  public boolean setField0(String __fieldName, Object __fieldVal) {
    if ("sessionId".equals(__fieldName)) {
      this.sessionId = (Long) __fieldVal;
      return true;
    }
    else    if ("sessionSysName".equals(__fieldName)) {
      this.sessionSysName = (String) __fieldVal;
      return true;
    }
    else    if ("lmsName".equals(__fieldName)) {
      this.lmsName = (String) __fieldVal;
      return true;
    }
    else    if ("orgName".equals(__fieldName)) {
      this.orgName = (String) __fieldVal;
      return true;
    }
    else    if ("courseName".equals(__fieldName)) {
      this.courseName = (String) __fieldVal;
      return true;
    }
    else    if ("courseRun".equals(__fieldName)) {
      this.courseRun = (String) __fieldVal;
      return true;
    }
    else    if ("lmsUserId".equals(__fieldName)) {
      this.lmsUserId = (Long) __fieldVal;
      return true;
    }
    else    if ("userName".equals(__fieldName)) {
      this.userName = (String) __fieldVal;
      return true;
    }
    else    if ("agent".equals(__fieldName)) {
      this.agent = (String) __fieldVal;
      return true;
    }
    else    if ("hostName".equals(__fieldName)) {
      this.hostName = (String) __fieldVal;
      return true;
    }
    else    if ("ipAddress".equals(__fieldName)) {
      this.ipAddress = (String) __fieldVal;
      return true;
    }
    else    if ("createDateTime".equals(__fieldName)) {
      this.createDateTime = (java.sql.Timestamp) __fieldVal;
      return true;
    }
    else    if ("eventType".equals(__fieldName)) {
      this.eventType = (String) __fieldVal;
      return true;
    }
    else    if ("eventSource".equals(__fieldName)) {
      this.eventSource = (String) __fieldVal;
      return true;
    }
    else    if ("eventName".equals(__fieldName)) {
      this.eventName = (String) __fieldVal;
      return true;
    }
    else    if ("dataSource".equals(__fieldName)) {
      this.dataSource = (String) __fieldVal;
      return true;
    }
    else    if ("oldVideoSpeed".equals(__fieldName)) {
      this.oldVideoSpeed = (Float) __fieldVal;
      return true;
    }
    else    if ("currVideoSpeed".equals(__fieldName)) {
      this.currVideoSpeed = (Float) __fieldVal;
      return true;
    }
    else    if ("oldVideoTime".equals(__fieldName)) {
      this.oldVideoTime = (Float) __fieldVal;
      return true;
    }
    else    if ("currVideoTime".equals(__fieldName)) {
      this.currVideoTime = (Float) __fieldVal;
      return true;
    }
    else    if ("videoNavigType".equals(__fieldName)) {
      this.videoNavigType = (String) __fieldVal;
      return true;
    }
    else    if ("oldGrade".equals(__fieldName)) {
      this.oldGrade = (Float) __fieldVal;
      return true;
    }
    else    if ("currGrade".equals(__fieldName)) {
      this.currGrade = (Float) __fieldVal;
      return true;
    }
    else    if ("maxGrade".equals(__fieldName)) {
      this.maxGrade = (Float) __fieldVal;
      return true;
    }
    else    if ("attempts".equals(__fieldName)) {
      this.attempts = (Integer) __fieldVal;
      return true;
    }
    else    if ("maxNoAttempts".equals(__fieldName)) {
      this.maxNoAttempts = (Integer) __fieldVal;
      return true;
    }
    else    if ("hintAvailable".equals(__fieldName)) {
      this.hintAvailable = (String) __fieldVal;
      return true;
    }
    else    if ("hintUsed".equals(__fieldName)) {
      this.hintUsed = (String) __fieldVal;
      return true;
    }
    else    if ("currPosition".equals(__fieldName)) {
      this.currPosition = (Integer) __fieldVal;
      return true;
    }
    else    if ("oldPosition".equals(__fieldName)) {
      this.oldPosition = (Integer) __fieldVal;
      return true;
    }
    else    if ("chapterSysName".equals(__fieldName)) {
      this.chapterSysName = (String) __fieldVal;
      return true;
    }
    else    if ("chapterTitle".equals(__fieldName)) {
      this.chapterTitle = (String) __fieldVal;
      return true;
    }
    else    if ("sessSysName".equals(__fieldName)) {
      this.sessSysName = (String) __fieldVal;
      return true;
    }
    else    if ("sessTitle".equals(__fieldName)) {
      this.sessTitle = (String) __fieldVal;
      return true;
    }
    else    if ("moduleSysName".equals(__fieldName)) {
      this.moduleSysName = (String) __fieldVal;
      return true;
    }
    else    if ("moduleTitle".equals(__fieldName)) {
      this.moduleTitle = (String) __fieldVal;
      return true;
    }
    else    if ("answerChoice".equals(__fieldName)) {
      this.answerChoice = (String) __fieldVal;
      return true;
    }
    else    if ("success".equals(__fieldName)) {
      this.success = (String) __fieldVal;
      return true;
    }
    else    if ("done".equals(__fieldName)) {
      this.done = (String) __fieldVal;
      return true;
    }
    else    if ("enrolmentMode".equals(__fieldName)) {
      this.enrolmentMode = (String) __fieldVal;
      return true;
    }
    else    if ("totDurationInSecs".equals(__fieldName)) {
      this.totDurationInSecs = (Integer) __fieldVal;
      return true;
    }
    else    if ("eventNo".equals(__fieldName)) {
      this.eventNo = (Integer) __fieldVal;
      return true;
    }
    else    if ("otherTitle".equals(__fieldName)) {
      this.otherTitle = (String) __fieldVal;
      return true;
    }
    else    if ("otherType".equals(__fieldName)) {
      this.otherType = (String) __fieldVal;
      return true;
    }
    else    if ("anonymous".equals(__fieldName)) {
      this.anonymous = (String) __fieldVal;
      return true;
    }
    else    if ("anonymousToPeers".equals(__fieldName)) {
      this.anonymousToPeers = (String) __fieldVal;
      return true;
    }
    else    if ("eduLevel".equals(__fieldName)) {
      this.eduLevel = (String) __fieldVal;
      return true;
    }
    else    if ("gender".equals(__fieldName)) {
      this.gender = (String) __fieldVal;
      return true;
    }
    else    if ("commentableId".equals(__fieldName)) {
      this.commentableId = (String) __fieldVal;
      return true;
    }
    else    if ("commentType".equals(__fieldName)) {
      this.commentType = (String) __fieldVal;
      return true;
    }
    else    if ("commentSysId".equals(__fieldName)) {
      this.commentSysId = (String) __fieldVal;
      return true;
    }
    else    if ("aadhar".equals(__fieldName)) {
      this.aadhar = (String) __fieldVal;
      return true;
    }
    else    if ("problemSubmissionTime".equals(__fieldName)) {
      this.problemSubmissionTime = (java.sql.Timestamp) __fieldVal;
      return true;
    }
    else    if ("hintMode".equals(__fieldName)) {
      this.hintMode = (String) __fieldVal;
      return true;
    }
    else    if ("currentSeekTime".equals(__fieldName)) {
      this.currentSeekTime = (Float) __fieldVal;
      return true;
    }
    else    if ("queryText".equals(__fieldName)) {
      this.queryText = (String) __fieldVal;
      return true;
    }
    else    if ("noOfResults".equals(__fieldName)) {
      this.noOfResults = (Integer) __fieldVal;
      return true;
    }
    else    if ("lastModDateTime".equals(__fieldName)) {
      this.lastModDateTime = (java.sql.Timestamp) __fieldVal;
      return true;
    }
    else {
      return false;    }
  }
}
