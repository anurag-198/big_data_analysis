package LMSDataLoader.dataModels;

public class CourseOthers {
	Long otherId;
	String lmsName;
	String orgName;
	String courseName;
	String htmlSysName;
	String chapterSysName;
	String sessionSysName;
	String verticalSysName;
	String title;
	String type;
	

	public Long getOtherfId(){
		return this.otherId;
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
	public String getHtmlSysName(){
		return this.htmlSysName;
	}
	public String getChapterSysName(){
		return this.chapterSysName;
	}
	public String getSessionSysName(){
		return this.sessionSysName;
	}
	public String getVerticalSysName(){
		return this.verticalSysName;
	}
	
	public String getTitle(){
		return this.title;
	}
	public String getType(){
		return this.type;
	}


	public void setOtherId(Long otherId){
		this.otherId = otherId;
	}
	
	public void setHtmlSysName(String cur){
		this.htmlSysName = cur;
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
	public void setTitle(String title){
		this.title = title;
	}
	public void setType(String type){
		this.type = type;
	}
	public void setVerticalSysName(String verticalSysName){
		this.verticalSysName = verticalSysName;
	}
}
