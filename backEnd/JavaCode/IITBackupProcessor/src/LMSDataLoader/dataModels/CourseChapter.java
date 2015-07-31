package LMSDataLoader.dataModels;

public class CourseChapter {
  Long chapterId;
  String lmsName;
  String orgName;
  String courseName;
  String chapterTitle;
  String chapterSysName;
  java.util.Date chapterStartDate;
  Integer position;
  
	public  Long getChapterId(){
		return this.chapterId;
	}
	public  String getLmsName(){
		return this.lmsName;
	}
	public  String getOrgName(){
		return this.orgName;
	}
	public  String getCourseName(){
		return this.courseName;
	}
	public  String getChapterTitle(){
		return this.chapterTitle;
	}
	public  String getChapterSysName(){
		return this.chapterSysName;
	}
	public  java.util.Date getChapterStartDate(){
		return this.chapterStartDate;
	}
	public  Integer getPosition(){
		return this.position;
	}
	
    public void setChapterId(Long chapterId){
    	this.chapterId = chapterId;
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
    public void setChapterTitle(String chapterTitle){
    	this.chapterTitle = chapterTitle;
    }
    public void setchapterSysName(String chapterSysName){
    	this.chapterSysName = chapterSysName;
   }
    public  void setChapterStartDate(java.util.Date chapterStartDate){
		this.chapterStartDate = chapterStartDate;
	}
    public void setPosition(Integer position){
    	this.position = position;
    }
    
}
