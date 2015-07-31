package LMSDataLoader.dataModels;

public class CourseWiki {
	Long wikiId;
	String lmsName;
	String orgName;
	String courseName;
	String wikiSlug;
	Long lmsWikiId;
	java.sql.Date createdDate;
	java.sql.Date lastModDate;
	Long lastRevId;
	Long ownerId;
	Long groupId;
	Integer groupRead;
	Integer groupWrite;
	Integer otherRead;
	Integer otherWrite;

	public Long getWikiId(){
		return this.wikiId ;
	}
	public String getLmsName(){
		return this.lmsName ;
	}
	public String getOrgName(){
		return this.orgName ;
	}
	public String getCourseName(){
		return this.courseName ;
	}
	public String getWikiSlug(){
		return this.wikiSlug ;
	}
	public void setWikiId(Long wikiId ){
		this.wikiId = wikiId ;
	}
	public void setLmsName(String lmsName){
		this.lmsName = lmsName ;
	}
	public void setOrgName(String orgName){
		this.orgName = orgName ;
	}
	public void setCourseName(String courseName){
		this.courseName = courseName ;
	}
	public void setWikiSlug(String wikiSlug){
		this.wikiSlug = wikiSlug ;
	}
}
