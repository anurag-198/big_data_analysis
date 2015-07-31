package LMSDataLoader.dataModels;

public class UserSessionOldLog {
	
	Long sessionId;
	String sessionSysName;
	String lmsName;
	String orgName;
	String courseName;
	String courseRun;
	Long lmsUserId;
	String userName;
	String agent;
	String hostName;
	String ipAddress;
//	String url;
	java.sql.Timestamp createDateTime, probSubmissionTime;
	String eventType;
	String eventSource;
	String eventName;
	java.sql.Timestamp lastModDateTime;
	String dataSource;
	Float oldVideoSpeed;
	Float currVideoSpeed;
	Float oldVideoTime;
	Float currVideoTime;
	String videoNavigType;
	Float oldGrade;
	Float currGrade;
	Float maxGrade;
	Integer attempts;
	Integer maxNoAttempts;
	String hintAvailable;
	String hintUsed;
	Integer currPosition;
	Integer  oldPosition;
	String chapterSysName;
	String chapterTitle;
	String sessSysName;
	String sessTitle;
	String moduleSysName;
	String moduleTitle;
	//String moduleType;
	String answerChoice;
	String success;
	String done;
	String enrolmentMode;
	Integer totDurationInSecs;
	Short eventNo;
	
	String otherTitle;
	String otherType;
	String anonymous;
	String anonymousToPeers;
	String eduLevel;
	String commentableId;
	String commentSysId;
	String commentType;
	String gender;
	String aadhar;
	String hintMode;
	Float currentSeekTime;
	String queryText;
	Integer noOfResults;
	
	
	public java.sql.Timestamp getProbSubmissionTime(){
	   	return this.probSubmissionTime;
	} 
	
	public void setProbSubmissionTime(java.sql.Timestamp cur){
	    this.probSubmissionTime  = cur;
	} 
	

	 public void setHintMode(String cur){
	    	this.hintMode = cur;
	    }
	 
	 public String getHintMode(){
	    	return this.hintMode;
	    } 
	 
	 public Float getCurrentSeekTime(){
	    	return this.currentSeekTime;
	    } 
	 
	 
	 public String getQueryText(){
	    	return this.queryText;
	    } 
	 
	 
	 public Integer getNoOfResults(){
	    	return this.noOfResults;
	    } 
	 
	 public void setCurrentSeekTime(Float cur){
	    	this.currentSeekTime = cur;
	    }
	 
	 public void setQueryText(String cur){
	    	this.queryText = cur;
	    }
	 
	 public void setNoOfResults(Integer cur){
	    	this.noOfResults = cur;
	    }
	 
	 public void setGender(String cur){
	    	this.gender = cur;
	    } 
	 
	 public void setAadhar(String cur){
	    	this.aadhar = cur;
	    } 
	

	public String getGender(){
	    	return this.gender;
	    }
	
	 public void setOtherTitle(String cur){
	    	this.otherTitle = cur;
	    } 
	 public void setOtherType(String cur){
	    	this.otherType = cur;
	    } 
	 public void setAnonymous(String cur){
	    	this.anonymous = cur;
	    } 
	 public void setAnonymousToPeers(String cur){
	    	this.anonymousToPeers = cur;
	    } 
	 public void setEduLevel(String cur){
	    	this.eduLevel = cur;
	    } 
	 public void setCommentableId(String cur){
	    	this.commentableId = cur;
	    } 
	 public void setCommentSysId(String cur){
	    	this.commentSysId = cur;
	    } 
	 public void setCommentType(String cur){
	    	this.commentType = cur;
	    } 
	 
	
	public String getOtherTitle(){
    	return this.otherTitle;
    }  
	public String getOtherType(){
    	return this.otherType;
    }  
	public String getAnonymous(){
    	return this.anonymous;
    }  
	public String getAnonymousToPeers(){
    	return this.anonymousToPeers;
    }  
	public String getEduLevel(){
    	return this.eduLevel;
    }  
	public String getCommentableId(){
    	return this.commentableId;
    }  
	public String getCommentSysId(){
    	return this.commentSysId;
    }  
	public String getCommentType(){
    	return this.commentType;
    } 
	public String getAadhar(){
    	return this.aadhar;
    } 
	
	
	
	
	public Long getSessionId(){
    	return this.sessionId;
    }  
    public void setSessionId(Long sessionId){
    	this.sessionId = sessionId;
    }  
    public String	getSessionSysName(){
      	 return this.sessionSysName;
       } 
    public String	getLmsName(){
   	 return this.lmsName;
    } 
    public String	getOrgName(){
      	 return this.orgName;
    } 
    public String	getCourseName(){
     	 return this.courseName;
   } 
    public String	getCourseRun(){
    	 return this.courseRun;
  }
     
    public Long	getLmsUserId(){
   	 return this.lmsUserId;
    }  
    public String	getEventType(){
   	 return this.eventType;
    }  
    public String	getEventSource(){
   	 return this.eventSource;
    }  
    public String	getEventName(){
      	 return this.eventName;
    }  
    public String	getHostName(){
   	 return this.hostName;
    }  
    public String	getIpAddress(){     
   	 return this. ipAddress;
    }  
   public java.sql.Timestamp getCreateDateTime(){
   	return this.createDateTime;
   }  
  public java.sql.Timestamp getLastModDateTime(){
	   	return this.lastModDateTime;
	   }  
	public String	getAgent(){
       return this.agent;
   } 
   public String	getDataSource(){
    	 return this.dataSource;
     }
   public String	getVideoNavigType(){
  	 return this.videoNavigType;
   }
	public String	getUserName(){
       return this.userName;
   }  
	
	
	
	public void setUserName(String currVar){
		userName = currVar;
   }
	
	public void setSessionSysName(String currVar){
		sessionSysName = currVar;
   }
	public void setLmsName(String currVar){
		lmsName = currVar;
   }
	
	
	public void setOrgName(String currVar){
		orgName = currVar;
   }
	public void setCourseName(String currVar){
		courseName = currVar;
   }
	public void setCourseRun(String currVar){
		courseRun = currVar;
   }
	
    public void setLmsUserId(Long currVar){
   	 this.lmsUserId = currVar;
    }
    public void setEventType(String currVar){
     	  this.eventType = currVar;
    }
    public void setEventSource(String currVar){
    	  this.eventSource= currVar;
    }
    public void setEventName(String currVar){
  	  this.eventName= currVar;
    }
    public void      setHostName(String currVar){
   	  this.hostName= currVar;
    }
    public void      setIpAddress(String currVar){
  	  this.ipAddress= currVar;
    }
   public void  setCreateDateTime(java.sql.Timestamp currVar){

   	  this.createDateTime= currVar;
    }
   public void  setLastModDateTime(java.sql.Timestamp currVar){

	   	  this.lastModDateTime= currVar;
	}
   public void setDataSource(String currVar){
	  	  this.dataSource= currVar;
	  }
   public void setVideoNavigType(String currVar){
	  	  this.videoNavigType= currVar;
	  }
   public Float getOldVideoSpeed(){
		return this.oldVideoSpeed;
	}
	public Float getCurrVideoSpeed(){
		return this.currVideoSpeed;
	}
	public Float getOldVideoTime(){
		return this.oldVideoTime;
	}
	public Float getCurrVideoTime(){
		return this.currVideoTime;
	}
	public Float getOldGrade(){
		return this.oldGrade;
	}
	public Float getCurrGrade(){
		return this.currGrade;
	}
	public Float getMaxGrade(){
		return this.maxGrade;
	}
	public Integer getAttempts(){
		return this.attempts;
	}
	public Integer getMaxNoAttempts(){
		return this.maxNoAttempts;
	}
	public String getHintAvailable(){
		return this.hintAvailable;
	}
	public String getHintUsed(){
		return this.hintUsed;
	}
	public String getChapterTitle(){
		return this.chapterTitle;
	}
	public String getChapterSysName(){
		return this.chapterSysName;
	}
	public String getSessSysName(){
		return this.sessSysName;
	}
	public String getModuleSysName(){
		return this.moduleSysName;
	}
	public String getSessTitle(){
		return this.sessTitle;
	}
	public String getModuleTitle(){
		return this.moduleTitle;
	}
	/*public String getModuleType(){
		return this.moduleType;
	}*/
	public String getAnswerChoice(){
		return this.answerChoice;
	}
	public String getSuccess(){
		return this.success;
	}
	public String getDone(){
		return this.done;
	}
	public String getEnrolmentMode(){
		return this.enrolmentMode;
	}
	public Integer getCurrPosition(){
		return this.currPosition;
	}
	public Integer getOldPosition(){
		return this.oldPosition;
	}
	public Integer getTotDurationInSecs(){
		return this.totDurationInSecs;
	}
	
	public void setOldVideoSpped(Float oldVideoSpeed){
		this.oldVideoSpeed = oldVideoSpeed;
	}
	public void setCurrVideoSpeed(Float currVideoSpeed){
		this.currVideoSpeed = currVideoSpeed;
	}
	public void setOldVideoTime(Float oldVideoTime){
		this.oldVideoTime = oldVideoTime;
	}
	public void setCurrVideoTime(Float currVideoTime){
		this.currVideoTime = currVideoTime;
	}
	public void setOldGrade(Float oldGrade){
		this.oldGrade = oldGrade;
	}
	public void setCurrGrade(Float currGrade){
		this.currGrade = currGrade;
	}
	public void setMaxGrade(Float maxGrade){
		this.maxGrade = maxGrade;
	}
	public void setAttempts(Integer attempts){
		this.attempts = attempts;
	}
	public void setMaxNoAttempts(Integer maxNoAttempts){
		this.maxNoAttempts = maxNoAttempts;
	}
	public void  setHintAvailable(String hintAvailable){
		this.hintAvailable = hintAvailable;
	}
	public void  setHintUsed(String hintUsed){
		this.hintUsed = hintUsed; 
	}
	public void setCurrPosition(Integer currPosition){
		this.currPosition = currPosition;
	}
	public void setOldPosition(Integer oldPosition ){
		this.oldPosition =oldPosition; 
	}
	public void setTotDurationInSecs(Integer totDurationInSecs ){
		this.totDurationInSecs =totDurationInSecs; 
	}
	public void setChapterTitle(String chapterTitle ){
		this.chapterTitle = chapterTitle; 
	}
	public void setChapterSysName(String chapterSysName ){
		this.chapterSysName = chapterSysName; 
	}
	public void setSessSysName(String sessSysName ){
		this.sessSysName = sessSysName; 
	}
	public void setModuleSysName(String moduleSysName ){
		this.moduleSysName = moduleSysName; 
	}
	public void setSessTitle(String sessTitle ){
		this.sessTitle = sessTitle; 
	}
	public void setModuleTitle(String moduleTitle ){
		this.moduleTitle = moduleTitle; 
	}
/*	public void setModuleType(String moduleType ){
		this.moduleType = moduleType; 
	}*/
	public void setAnswerChoice(String answerChoice ){
		this.answerChoice = answerChoice; 
	}
	public void setSuccess(String success ){
		this.success = success; 
	}
	public void setDone(String done ){
		this.done = done; 
	}
	public void setEnrolmentMode(String enrolmentMode ){
		this.enrolmentMode = enrolmentMode; 
	}
	public Short getEventNo(){
		return this.eventNo;
	}
	public void setEventNo(Short eventNo){
		this.eventNo = eventNo;
	}
}
