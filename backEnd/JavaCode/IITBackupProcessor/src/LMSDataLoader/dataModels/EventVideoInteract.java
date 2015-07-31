package LMSDataLoader.dataModels;

public class EventVideoInteract {
	Long eventId;
	String sessionSysName;
	String lmsName;
	String orgName;
	String courseName;
	String courseRun;
	Long lmsUserId;
	String eventName;
	Short eventNo;
	String videoSysName;
	String videoTitle;
	String chapterSysName;
	String chapterTitle;
	Float oldSeekTime;
	Float currSeekTime;
	String videoNavigType;
	Float oldSpeed;
	Float currSpeed;
	String source;
	//java.sql.Time videoPosition;
	java.sql.Timestamp createDateTime;
	java.sql.Timestamp lastModDateTime;

	public Long getEventId() {
		return this.eventId;
	}

	public String getSessionSysName() {
		return this.sessionSysName;
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

	public Long getLmsUserId() {
		return this.lmsUserId;
	}

	public String getVideoSysName() {
		return this.videoSysName;
	}
	public String getVideoTitle() {
		return this.videoTitle;
	}
	public String getChapterTitle() {
		return this.chapterTitle;
	}
	public String getChapterSysName() {
		return this.chapterSysName;
	}
	public String getEventName() {
		return this.eventName;
	}
	public Short getEventNo() {
		return this.eventNo;
	}

	public Float getOldSeekTime() {
		return this.oldSeekTime;
	}

	public Float getCurrSeekTime() {
		return this.currSeekTime;
	}

	public String getVideoNavigType() {
		return this.videoNavigType;
	}

	public Float getOldSpeed() {
		return this.oldSpeed;
	}

	public Float getCurrSpeed() {
		return this.currSpeed;
	}

	public String getSource() {
		return this.source;
	}

	/*public java.sql.Time getVideoPosition() {
		return this.videoPosition;
	}*/

	public java.sql.Timestamp getCreateDateTime() {
		return this.createDateTime;
	}

	public java.sql.Timestamp getLastModDateTime() {
		return this.lastModDateTime;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public void setVideoSysName(String videoSysName) {
		this.videoSysName = videoSysName;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public void setChapterSysName(String chapterSysName) {
		this.chapterSysName = chapterSysName;
	}
	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}
	
	public void setSessionSysName(String sessionSysName) {
		this.sessionSysName = sessionSysName;
	}

	public void setLmsUserId(Long lmsUserId) {
		this.lmsUserId = lmsUserId;
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

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setEventNo(Short eventNo) {
		this.eventNo = eventNo;
	}

	public void setOldSeekTime(Float oldSeekTime) {
		this.oldSeekTime = oldSeekTime;
	}

	public void setCurrSeekTime(Float currSeekTime) {
		this.currSeekTime = currSeekTime;
	}

	public void setVideoNavigType(String videoNavigType) {
		this.videoNavigType = videoNavigType;
	}

	public void setOldSpeed(Float oldSpeed) {
		this.oldSpeed = oldSpeed;
	}

	public void setCurrSpeed(Float currSpeed) {
		this.currSpeed = currSpeed;
	}

	public void setSource(String source) {
		this.source = source;
	}

/*	public void setVideoPosition(java.sql.Time videoPosition) {
		this.videoPosition = videoPosition;
	}*/

	public void setCreateDateTime(java.sql.Timestamp createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setLastModDateTime(java.sql.Timestamp lastModDateTime) {
		this.lastModDateTime = lastModDateTime;
	}

}
