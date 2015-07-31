package LMSDataLoader.dataModels;

public class CourseChapterSession {
	Long sessionId;
	String lmsName;
	String orgName;
	String courseName;
	String chapterSysName;
	String sessionSysName;
	String sessionTitle;
	java.util.Date sessionStartDate;
	Short position;
	//String sessionType;
	
	public Long getSessionId(){
		return this.sessionId;
	}
	 public String getLmsName(){
		return this.lmsName;
	}
	 public String getOrgName(){
		return this.orgName;
	}
	 public String getCourseName(){
		return this.courseName;
	}
	 public String getChapterSysName(){
		return this.chapterSysName;
	}
	 public String getSessionSysName(){
		return this.sessionSysName;
	}
	 public String getSessionTitle(){
		return this.sessionTitle;
	}
	 //public String getSessionType(){
		//	return this.sessionType;
	//}
	 public java.util.Date getSessionStartDate(){
		return this.sessionStartDate;
	}
	public Short getPosition(){
		return this.position;
		}

	 public void setSessionId(Long sessionId){
		 this.sessionId = sessionId;
	}
	 public void setLmsName(String lmsName){
		 this.lmsName = lmsName;
	}
	 public void setOrgName(String orgName){
		 this.orgName = orgName;
	}
	 public void setCourseName(String courseName){
		 this.courseName = courseName;
	}
	 public void setChapterSysName(String chapterSysName){
		 this.chapterSysName = chapterSysName;
	}
	 public void setSessionSysName(String sessionSysName){
		 this.sessionSysName = sessionSysName;
	}
	 public void setSessionTitle(String sessionTitle){
		 this.sessionTitle = sessionTitle;
	}
//	 public void setSessionType(String sessionType){
	//	 this.sessionType = sessionType;
	//}
	 public void setSessionStartDate(java.util.Date sessionStartDate ){
		 this.sessionStartDate = sessionStartDate;
	}
	 public void setPosition(Short positiond){
		 this.position = positiond;
	}
}
