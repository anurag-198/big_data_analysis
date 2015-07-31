package LMSDataLoader.dataModels;

public class StudentCourseGrades {
	Long id;
	String lmsName;
	String orgName;
	String  courseName;
	String  courseRun;
	Long lmsUserId;
	String lmsUserName;
	String  moduleType;
	String  moduleSysName;
	Integer  score;
	Integer maxScore;
	Integer noOfAttempts;
	String hintAvailable;
	String hintUsed;
	String state;
	String goals;
	java.util.Date createDateTime;
	java.util.Date lastModDateTime;
	Integer totSessDuraInSecs;

	public  	Long getId(){
		return this.id;
	}
	public  	Long getLmsUserId(){
		return this.lmsUserId;
	}
	public         String getLmsName(){
		return this.lmsName;
	}
	public         String getLmsUserName(){
		return this.lmsUserName;
	}
	public         String getOrgName(){
		return this.orgName;
	}
	public         String getCourseName(){
		return this.courseName;
	}
	public         String getCourseRun(){
		return this.courseRun;
	}
	public         String getModuleType(){
		return this.moduleType;
	}
	public         String getModuleSysName(){
		return this.moduleSysName;
	}
	public         Integer getScore(){
		return this.score;
	}
	public         Integer getMaxScore(){
		return this.maxScore;
	}
	public  Integer getNoOfAttempts(){
		return this.noOfAttempts;
	}
	public String getHintAvailable(){
		return this.hintAvailable;
    }
    public String getHintUsed(){
    	return this.hintUsed;
    }
    public String getState(){
        return this.state;
    }
    public String getGoals(){
        return this.goals;
    }
    public java.util.Date getCreateDateTime(){
        return this.createDateTime;
    }
    public java.util.Date getLastModDateTime(){
        return this.lastModDateTime;
    }
    public  Integer getTotSessDuraInSecs(){
		return this.totSessDuraInSecs;
	}
	
 	public void  setId(Long id){
                this.id = id;
        }
        public void setLmsName(String lmsName){
                this.lmsName = lmsName;
        }
     	public void  setLmsUserId(Long lmsUserId){
            this.lmsUserId = lmsUserId;
    }
    public void setLmsUserName(String lmsUserName){
            this.lmsUserName = lmsUserName;
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
        public void setModuleType(String moduleType){
                this.moduleType = moduleType;
        }
        public void setModuleSysName(String moduleSysName){
            this.moduleSysName = moduleSysName;
        }
        public void setScore(Integer score){
                this.score =score; 
        }
        public void setMaxScore(Integer maxScore){
                this.maxScore = maxScore;
        }
        public void setNoOfAttempts(Integer noOfAttempts){
            this.noOfAttempts = noOfAttempts;
        }
        public void setHintAvailable(String hintAvailable){
            this.hintAvailable = hintAvailable;
        }
        public void setHintUsed(String hintUsed){
            this.hintUsed = hintUsed;
        }
        public void setState(String state){
            this.state = state;
        }
        public void setGoals(String goals){
            this.goals = goals;
        }
        public void setCreateDateTime(java.util.Date createDateTime){
            this.createDateTime = createDateTime;
        }
        public void setLastModDateTime(java.util.Date lastModDateTime){
            this.lastModDateTime = lastModDateTime;
        }
        public void setTotSessDuraInSecs(Integer totSessDuraInSecs){
            this.totSessDuraInSecs = totSessDuraInSecs;
        }

}
