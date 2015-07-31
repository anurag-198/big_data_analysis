package LMSDataLoader.dataModels;

public class CourseFiles {
	Long fileId;
	String lmsName;
	String orgName;
	String courseName;
	String chapterSysName;
	String sessionSysName;
	String fileTitle;
	String fileSysName;

	public Long getFileId(){
		return this.fileId;
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
	public String getFileTitle(){
		return this.fileTitle;
	}
	public String getFileSysName(){
		return this.fileSysName;
	}

	public void setFileId(Long fileId){
		this.fileId = fileId;
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
	public void setFileTitle(String fileTitle){
		this.fileTitle = fileTitle;
	}
	public void setFileSysName(String fileSysName){
		this.fileSysName = fileSysName;
	}

}
