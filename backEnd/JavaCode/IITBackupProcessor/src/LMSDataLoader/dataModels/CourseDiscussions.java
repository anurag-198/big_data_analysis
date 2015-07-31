package LMSDataLoader.dataModels;

public class CourseDiscussions {
	Long discussionId;
	String lmsName;
	String orgName;
	String courseName;
	String chapterSysName;
	//String sessionSysName;
	String discussionTitle;
	String discussionSysName;
	//String discussCategory;
	String discussSysId;

	public Long getDiscussionId(){
		return this.discussionId;
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
	/*public String getSessionSysName(){
		return this.sessionSysName;
	}
	public String getDiscussCategory(){
		return this.discussCategory;
	}
	*/public String getDiscussionTitle(){
		return this.discussionTitle;
	}
	public String getDiscussionSysName(){
		return this.discussionSysName;
	}
	public String getDiscussSysId(){
		return this.discussSysId;
	}

	public void setDiscussionId(Long discussionId ){
		 this.discussionId = discussionId;
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
	/*public void setSessionSysName(String sessionSysName){
		 this.sessionSysName = sessionSysName;
	}
	public void setDiscussCategory(String discussCategory){
		 this.discussCategory = discussCategory;
	}*/
	public void setDiscussionTitle(String discussionTitle ){
		 this.discussionTitle = discussionTitle;
	}
	public void setDiscussionSysName(String discussionSysName){
		 this.discussionSysName = discussionSysName;
	}
	public void setDiscussSysId(String discussSysId){
		 this.discussSysId = discussSysId;
	}

}
