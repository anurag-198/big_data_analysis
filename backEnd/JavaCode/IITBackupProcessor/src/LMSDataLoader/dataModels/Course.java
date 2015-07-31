package LMSDataLoader.dataModels;

public class Course {
	Long courseId;
	String lmsName;
	String orgName;
	String courseTitle;
	String courseName;
	Long authorUserId;
	String  currConcepts;
	String  prevConcepts;
	//java.util.Date creationDate;
	//java.util.Date lastModifiedDate;
	String  language;
	Integer minPrice;
	Integer suggestedPrices;
	String currencyCode;
	//String courseRun;
	java.util.Date endDate;
	java.util.Date startDate;
	
	public 	 Long getCourseId(){
		return courseId;
	}
	public 	 String getLmsName(){
		return lmsName;
	}
	public 	 String getOrgName(){
		return orgName;
	}
	public 	 String getCourseTitle(){
		return this.courseTitle;
	}
	public 	 String getCourseName(){
		return this.courseName;
	}
	public 	 Long getAuthorUserId(){
		return this.authorUserId;
	}
	public 	 String getCurrConcepts(){
		return this.currConcepts;
	}
	public 	 String getPrevConcepts(){
		return this.prevConcepts;
	}
/*	public 	java.util.Date getCreationDate(){
		return this.creationDate;
	}
	public 	java.util.Date getLastModifiedDate(){
		return this.lastModifiedDate;
	}*/
	public 	 String getLanguage(){
		return this.language;
	}
	public 	Integer getMinPrice(){
		return this.minPrice;
	}
	public 	Integer getSuggestedPrices(){
		return this.suggestedPrices;
	}
	public 	 String getCurrencyCode(){
		return this.currencyCode;
	}
	/*public 	 String getCourseRun(){
		return this.courseRun;
	}*/
	public 	java.util.Date getEndDate(){
		return this.endDate;
	}
	public 	java.util.Date getStartDate(){
		return this.startDate;
	}

	public void  setCourseId(Long courseId){
		this.courseId = courseId;
	}
	public void setLmsName(String lmsName){
		this.lmsName = lmsName;
	}
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	public void  setCourseTitle(String courseTitle){
		this.courseTitle = courseTitle; 
	}
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}
	public void setAuthorUserId(Long authorUserId){
		this.authorUserId = authorUserId;
	}
	public void setCurrConcepts(String currConcepts){
		this.currConcepts = currConcepts;
	}
	public void setPrevConcepts(String prevConcepts){
		this.prevConcepts = prevConcepts;
	}
/*	public void setCreationDate(java.util.Date creationDate){
		this.creationDate = creationDate;
	}
	public void setLastModifiedDate(java.util.Date lastModifiedDate){
		this.lastModifiedDate = lastModifiedDate;
	}*/
	public void setLanguage(String language){
		this.language = language;
	}
	public void setMinPrice(Integer minPrice){
		this.minPrice = minPrice; }
	public void setSuggestedPrices(Integer suggestedPrices){
		this.suggestedPrices = suggestedPrices;
	}
	public void setCurrencyCode(String currencyCode){
		this.currencyCode = currencyCode;
	}
	/*public void setCourseRun(String courseRun){
		this.courseRun = courseRun;
	}*/
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}
	
}
