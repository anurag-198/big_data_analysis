package LMSDataLoader.dataModels;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CourseForums implements Serializable {
	Long forumId;
	String lmsName;
	String orgName;
	String courseName;
	String courseRun;
	String commentSysId;
	String commentType;
	String anonymousMode;
	Long lmsAuthorId;
	String lmsAuthorName;
	java.util.Date  createDateTime;
	java.util.Date   lastModDateTime;
	Integer upVoteCount;
	Integer totVoteCount;
	Integer commentCount;
	String threadType;
	//String threadTitle;
	String title;
	String commentableSysId;
	Boolean endorsed;
	Boolean closed;
	Boolean visible;
	
	public Long getForumId() {
		return this.forumId;
	}
	public String getLmsName() {
		return this.lmsName;
	}
	public String getOrgName() {
		return this.orgName;
	}
	public String getCourseName() {
		return this.courseName;
	}
	public String getCourseRun() {
		return this.courseRun;
	}
	public String getCommentSysId() {
		return this.commentSysId;
	}
	public String getCommentType() {
		return this.commentType;
	}
	public String getAnonymousMode() {
		return this.anonymousMode;
	}
	public Long getLmsAuthorId() {
		return this.lmsAuthorId;
	}
	public String getLmsAuthorName() {
		return this.lmsAuthorName;
	}
	public java.util.Date  getCreateDateTime() {
		return this.createDateTime;
	}
	public java.util.Date   getLastModDateTime() {
		return this.lastModDateTime;
	}
	public  Integer getUpVoteCount() {
		return this.upVoteCount;
	}
	public  Integer getTotVoteCount() {
		return this.totVoteCount;
	}
	public Integer getCommentCount() {
		return this.commentCount;
	}
	public String getThreadType() {
		return this.threadType;
	}
/*	public String getThreadTitle() {
		return this.threadTitle;
	}*/
	public String getTitle() {
		return this.title;
	}
	public String getCommentableSysId() {
		return this.commentableSysId;
	}
	public Boolean getEndorsed() {
		return this.endorsed;
	}
	public Boolean getClosed() {
		return this.closed;
	}
	public Boolean getVisible() {
		return this.visible;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	public void setLmsName(String lmsName) {
		this.lmsName = lmsName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setCourseRun(String courseRun) {
		this.courseRun = courseRun;
	}
	public void setCommentSysId(String commentSysId) {
		this.commentSysId = commentSysId;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public void setAnonymousMode(String anonymousMode) {
		this.anonymousMode = anonymousMode;
	}
	public void setLmsAuthorId(Long lmsAuthorId) {
		this.lmsAuthorId = lmsAuthorId;
	}
	public void setLmsAuthorName(String lmsAuthorName) {
		this.lmsAuthorName = lmsAuthorName;
	}
	public void setCreateDateTime(java.util.Date  createDateTime) {
		this.createDateTime = createDateTime;
	}
	public void setLastModDateTime(java.util.Date   lastModDateTime) {
		this.lastModDateTime = lastModDateTime;
	}
	public void setUpVoteCount( Integer upVoteCount) {
		this.upVoteCount = upVoteCount;
	}
	public void setTotVoteCount( Integer totVoteCount) {
		this.totVoteCount = totVoteCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public void setThreadType(String threadType) {
		this.threadType = threadType;
	}
/*	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}*/
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCommentableSysId(String commentableSysId) {
		this.commentableSysId = commentableSysId;
	}
	public void setEndorsed(Boolean endorsed) {
		this.endorsed = endorsed;
	}
	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
}