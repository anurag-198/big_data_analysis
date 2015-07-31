package LMSDataLoader.dataModels;

public class EventEnrollment {
	
	Long eventId;
	String lmsName;
	String orgName;
	String courseName;
	String eventName;
	Long lmsUserId;
	String userName;
	String gender;
	String eduLevel;
	String activate;
	Integer adminUserId;
	java.util.Date accessDateTime;
	//Date dateTime;
	
	public	Long getEventId(){
		return this.eventId;
	}
	
	public	String getLmsName(){
		return this.lmsName;
	}
	public	String getOrgName(){
		return this.orgName;
	}

	public	String getCourseName(){
		return this.courseName;
	}
	public	String getEventName(){
		return this.eventName;
	}
	
	public	Long getLmsUserId(){
		return this.lmsUserId;
	}
	
	public	String getUserName(){
		return this.userName;
	}
	public	String getGender(){
		return this.gender;
	}
	public	String getEduLevel(){
		return this.eduLevel;
	}
	
	public	String getActivate(){
		return this.activate;
	}
	public	Integer getAdminUserId(){
		return this.adminUserId;
	}
	 public java.util.Date getAccessDateTime(){
		   	return this.accessDateTime;
		   } 
	
//set methods	
	public void setEventId(Long  eventId ){
		this.eventId = eventId;
	}
	public void setLmsName(String  lmsName ){
		this.lmsName = lmsName;
	}
	public void setOrgName(String currVar){
		this.orgName  = currVar;
	}
	public  void CourseName(String courseName){
		this.courseName  = courseName;
	}
	public  void setLmsUserId(Long lmsUserId){
		this.lmsUserId  = lmsUserId;
	}
	public  void setEventName(String eventName){
		this.eventName  = eventName;
	}
	public  void setUserName(String userName){
		this.userName  = userName;
	}
	public  void setGender(String gender){
		this.gender  = gender;
	}
	public  void setEduLevel(String eduLevel){
		this.eduLevel  = eduLevel;
	}
	public  void setActivate(String activate){
		this.activate  = activate;
	}
	public  void setAdminUserId(Integer adminUserId){
		this.adminUserId  = adminUserId;
	}
	public void  setAccessDateTime(java.util.Date currVar){

	   	  this.accessDateTime= currVar;
	    }
	
}


