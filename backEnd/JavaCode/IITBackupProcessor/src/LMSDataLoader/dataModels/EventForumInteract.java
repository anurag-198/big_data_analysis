package LMSDataLoader.dataModels;

public class EventForumInteract {

	 Long eventId;
	 String lmsName ;
	 String orgName;
	 String courseName;
	 String eventName;
	 String commentThreadId;
	 Long lmsUserId;
	 String queryText;
	 Integer noOfResults;
	 
	public Long getEventId( ){
		return eventId;
	}
	 public String getLmsName (){
		return lmsName;
	}
	 public String getOrgName(){
		return orgName;
	}
	 public String getCourseName(){
		return courseName;
	}
	 public String getEventName(){
		return eventName;
	}
	 public String getCommentThreadId(){
		return commentThreadId;
	}
	 public Long getLmsUserId(){
		return lmsUserId;
	}
	 public Long queryText(){
		return lmsUserId;
	}
	 public String getQueryText(){
		return queryText;
	}
	 public Integer getNoOfResults(){
		return noOfResults;
	}
	 public void setEventId(Long eventId){
			this.eventId = eventId;
		}
		 public void setLmsName (String lmsName){
			this.lmsName = lmsName;
		}
		 public void setOrgName(String orgName){
			this.orgName = orgName;
		}
		 public void setCourseName(String courseName){
			this.courseName = courseName;
		}
		 public void setEventName(String eventName){
			this.eventName = eventName;
		}
		 public void setCommentThreadId(String commentThreadId){
			this.commentThreadId = commentThreadId;
		}
		 public void setLmsUserId(Long lmsUserId){
			this.lmsUserId = lmsUserId;
		}
		 public void setQueryText(String queryText){
			this.queryText = queryText;
		}
		 public void setNoOfResults(Integer noOfResults){
			this.noOfResults = noOfResults;
	}
}
