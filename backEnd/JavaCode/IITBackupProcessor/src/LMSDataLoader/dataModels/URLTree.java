package LMSDataLoader.dataModels;

public class URLTree {
	Long urlId;
	String lmsName;
	String orgName;
	String courseName;
	String courseRun;
	String urlSysName;
	String urlType;
	String parentUrl;
	
	public Long urlId(){
		return this.urlId;
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
	public String getCourseRun(){
		return this.courseRun;
	}
	public String getUrlSysName(){
		return this.urlSysName;
	}
	public String getUrlType(){
		return this.urlType;
	}
	public String getParentUrl(){
		return this.parentUrl;
	}

        public void setUrlId(Long urlId){
                 this.urlId = urlId;
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
        public void setCourseRun(String courseRun){
                 this.courseRun = courseRun;
        }
        public void setUrlSysName(String urlSysName){
                 this.urlSysName = urlSysName;
        }
        public void setUrlType(String urlType){
                 this.urlType = urlType;
        }
        public void setParentUrl(String parentUrl){
                 this.parentUrl = parentUrl;
        }

}
