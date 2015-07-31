package LMSDataLoader.dataModels;

public class StudentCourseEnrolment {
	Long enrolId;
	String lmsName;
	String orgName;
	String  courseName;
	String  courseRun;
	Long lmsUserId;
	java.util.Date enrolmentDate;
	Integer active;
	String mode;

	
	public  Long getEnrolId(){
		return this.enrolId;
	}
	public  	Long getLmsUserId(){
		return this.lmsUserId;
	}
	public         String getLmsName(){
		return this.lmsName;
	}
	public         String getOrgName(){
		return this.orgName;
	}
	public String getCourseName(){
		return this.courseName;
	}
	public String getCourseRun(){
		return this.courseRun;
	}
	public  java.util.Date getEnrolmentDate(){
		return this.enrolmentDate;
	}
	public Integer getActive(){
		return this.active;
    }
    public String getMode(){
    	return this.mode;
    }
 	public void  setEnrolId(Long enrolId){
                this.enrolId = enrolId;
        }
        public void setLmsName(String lmsName){
                this.lmsName = lmsName;
        }
     	public void  setLmsUserId(Long lmsUserId){
            this.lmsUserId = lmsUserId;
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
        public void setEnrolmentDate(java.util.Date enrolmentDate){
                this.enrolmentDate = enrolmentDate;
        }
        public void setActive(Integer active){
            this.active = active;
        }
        public void setMode(String mode){
            this.mode = mode;
        }
}
