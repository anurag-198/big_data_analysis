package LMSDataLoader.dataModels;

import java.sql.Timestamp;

public class EventCourseInteract {
	
	Long eventId;
	String lmsName;
	String orgName;
	String courseName;
	String courseRun;
	Long lmsUserId;
	String eventName;
	Short eventNo;
	String moduleType;
	String moduleSysName;
	String moduleTitle;
	String chapterSysName;
	String chapterTitle;
	java.sql.Timestamp createDateTime;
	java.sql.Timestamp modDateTime;
	Short oldPosition;
	Short curPosition;
	String source ;
	//java.sql.Timestamp accessDateTime;
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
	public	String getCourseRun(){
		return this.courseRun;
	}
	public	Long getLmsUserId(){
		return this.lmsUserId;
	}
	public	String getEventName(){
		return this.eventName;
	}
	public	Short getEventNo(){
		return this.eventNo;
	}
	public	String getModuleType(){
		return this.moduleType;
	}
	public	String getModuleSysName(){
		return this.moduleSysName;
	}
	public	String getModuleTitle(){
		return this.moduleTitle;
	}
	public	String getChapterSysName(){
		return this.chapterSysName;
	}
	public	String getChapterTitle(){
		return this.chapterTitle;
	}
	public java.sql.Timestamp getCreateDateTime(){
	   	return this.createDateTime;
	   } 
	public java.sql.Timestamp getModDateTime(){
	   	return this.modDateTime;
	   } 
	public	Short getOldPosition(){
		return this.oldPosition;
	}
	public	Short getCurPosition(){
		return this.curPosition;
	}
	public	String getSource(){
		return this.source;
	}
	
//set methods	
	public void setEventId(Long  eventId ){
		this.eventId = eventId;
	}
	public void setLmsName(String  lmsName ){
		this.lmsName = lmsName;
	}
	public void setOrgName(String orgName){
		this.orgName  = orgName;
	}
	public  void setCourseName(String courseName){
		this.courseName  = courseName;
	}
	public  void setCourseRun(String courseRun){
		this.courseRun  = courseRun;
	}
	public  void setLmsUserId(Long lmsUserId){
		this.lmsUserId  = lmsUserId;
	}
	public  void setEventName(String eventName){
		this.eventName  = eventName;
	}
	public  void setEventNo(Short eventNo){
		this.eventNo  = eventNo;
	}
	public  void setModuleType(String moduleType){
		this.moduleType  =  moduleType;
	}
	public  void setModuleSysName(String currVar){
		this.moduleSysName  =  currVar;
		
	}
	public  void setModuleTitle(String currVar){
		this.moduleTitle  =  currVar;
		
	}
	public  void setChapterSysName(String currVar){
		this.chapterSysName  =  currVar;
		
	}
	public  void setChapterTitle(String currVar){
		this.chapterTitle  =  currVar;
		
	}
	public  void setCreateDateTime(java.sql.Timestamp currVar){
		
		this.createDateTime  = currVar;
	}
	public void  setModDateTime(java.sql.Timestamp currVar){

	   	  this.modDateTime= currVar;
	    }
	public  void setOldPosition(Short oldPosition){
		this.oldPosition  = oldPosition;
		//System.out.println("inside dao old=="+oldPosition);
	}
	public  void setCurPosition(Short curPosition){
		this.curPosition  = curPosition;
		//System.out.println("inside dao new=="+curPosition);
	}
	
	public  void setSource(String source){
		this.source  = source;
	}
	
	
}


