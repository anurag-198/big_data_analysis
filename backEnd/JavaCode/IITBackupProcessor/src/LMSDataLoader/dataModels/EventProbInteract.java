package LMSDataLoader.dataModels;

public class EventProbInteract {
	
	Long eventId;
	String lmsName;
	String orgName;
	String courseName;
	Long lmsUserId;
	String eventName;
	Short eventNo;
	String quizzSysName;
	String quizzTitle;
	String chapterSysName;
	String chapterTitle;
	String hintAvailable;
	String hintMode;
	String inputType;
	String responseType;
	String variantId;
	Double oldScore;
	Double newScore;
	Double maxGrade;
	Integer attempts;
	Integer maxAttempts;
	String choice;
	String success;
	String source;
	java.sql.Timestamp probSubTime;
	String done;
	java.sql.Timestamp createDateTime;
	java.sql.Timestamp lastModDateTime;
	String courseRun;

    public Long getEventId(){
    	return this.eventId;
    }  
    public void setEventId(Long eventId){
    	this.eventId = eventId;
    }  
    public Long getLmsUserId(){
    	return this.lmsUserId;
    }  
    public void setLmsUserId(Long lmsUserId){
    	this.lmsUserId = lmsUserId;
    } 
    public String getOrgName(){
    	return this.orgName;
    }  
    public String getLmsName(){
    	return this.lmsName;
    }  
    public String getCourseName(){
    	return this.courseName;
    }  
    public String getEventName(){
    	return this.eventName;
    }  
    public Short getEventNo(){
    	return this.eventNo;
    }  
    public String getHintAvailable(){
    	return this.hintAvailable;
    }  
    public String getHintMode(){
    	return this.hintMode;
    }  
    public String getInputType(){
    	return this.inputType;
    }  
    public String getResponseType(){
    	return this.responseType;
    }  
    public String getVariantId(){
    	return this.variantId;
    }  
    public String getQuizzSysName(){
    	return this.quizzSysName;
    }  
    public String getQuizzTitle(){
    	return this.quizzTitle;
    }  
    public String getChapterSysName(){
    	return this.chapterSysName;
    }  
    public String getChapterTitle(){
    	return this.chapterTitle;
    }  
    public Integer getAttempts(){
    	return this.attempts;
    }  
    public Double getMaxGrade(){
    	return this.maxGrade;
    }  
    public Integer getMaxAttempts(){
     
     return this.maxAttempts;
    }  
    
    public Double getOldScore(){
    	return this.oldScore;
     }  
    public Double getNewScore(){
        return this.newScore;
    }  
    public String	getChoice(){
        return this.choice;
    }  
    public String	getSuccess(){
        return this.success;
    }  
    public String	getSource(){
        return this.source;
    }  
    public String	getDone(){
        return this.done;
    }  
     public String	getCourseRun(){
        return this.courseRun;
    } 
    public java.sql.Timestamp getProbSubTime(){
        return this.probSubTime;
    }  
    public java.sql.Timestamp getCreateDateTime(){
        return this.createDateTime;
    }  
    public java.sql.Timestamp getLastModDateTime(){
        return this.lastModDateTime;
    }  
    public void setOrgName(String currVar){
  	  this.orgName= currVar;
    }
    public void setLmsName(String currVar){
    	  this.lmsName= currVar;
      }
    public void setCourseName(String currVar){
    	  this.courseName= currVar;
      }
    public void setEventName(String currVar){
  	  this.eventName= currVar;
    }
    public void setEventNo(Short currVar){
    	  this.eventNo= currVar;
      }
      public void setHintAvailable(String currVar){
    	  this.hintAvailable= currVar;
      }
    public void setHintMode(String currVar){
    	  this.hintMode= currVar;
      }
    public void setInputType(String currVar){
    	  this.inputType= currVar;
      }
    public void setResponseType(String currVar){
    	  this.responseType= currVar;
      }
    public void setVariantId(String currVar){
  	  this.variantId= currVar;
    }
  public void setQuizzSysName(String currVar){
  	  this.quizzSysName= currVar;
    }
  public void setQuizzTitle(String currVar){
  	  this.quizzTitle= currVar;
    }
  public void setChapterSysName(String currVar){
  	  this.chapterSysName= currVar;
    }
  public void setChapterTitle(String currVar){
  	  this.chapterTitle= currVar;
    }
    public void setMaxGrade(Double currVar){
    	  this.maxGrade= currVar;
     }
    public void setAttempts(Integer currVar){
  	  this.attempts= currVar;
   }
    public void setMaxAttempts(Integer currVar){
  	  this.maxAttempts= currVar;
     }
    public void setOldScore(Double currVar){
  	  this.oldScore= currVar;
     }

    public void	setNewScore(Double currVar){
  	  this.newScore= currVar;
     }  
    public void	setChoice(String currVar){
    	  this.choice= currVar;
     }  
    public void	setSuccess(String currVar){
  	  this.success= currVar;
    }
    public void	setDone(String currVar){
    	  this.done= currVar;
      }
     public void setCourseRun(String courseRun){
    	  this.courseRun= courseRun;
      }
     public void	setSource(String currVar){
    	  this.source= currVar;
      }
      public void	setProbSubTime(java.sql.Timestamp currVar){
    	  this.probSubTime= currVar;
      }
      public void	setCreateDateTime(java.sql.Timestamp currVar){
    	  this.createDateTime= currVar;
      }
    public void	setLastModDateTime(java.sql.Timestamp currVar){
    	  this.lastModDateTime= currVar;
      }
}
