package LMSDataLoader.dataModels;

public class UserSession {
	
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
	String url;
	java.sql.Timestamp createDateTime;
	String eventType;
	String eventSource;
	String eventName;
	Long eventId;
	java.sql.Timestamp lastModDateTime;
	String dataSource;
	Short eventNo;
	
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
    public Long getEventId(){
   	 return this.eventId;
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
	public String	getUserName(){
       return this.userName;
   }  
	public void setUserName(String currVar){
		userName = currVar;
   }
	public String	getUrl(){
	   return this.url;
	}  
	public Short	getEventNo(){
		   return this.eventNo;
		}  
	public void setUrl(String currVar){
	    url = currVar;
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
	public void setEventId(Long currVar){
   	  this.eventId = currVar;
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
   public void	setEventNo(Short eventNo){
	   this.eventNo = eventNo;
	}  
}
