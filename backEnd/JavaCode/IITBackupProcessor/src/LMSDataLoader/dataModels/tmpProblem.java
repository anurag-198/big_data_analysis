package LMSDataLoader.dataModels;

public class tmpProblem {
	String state;
	java.util.Date startDateTime;
	java.util.Date  endDateTime ;
	Long lmsUserId ;
	Integer score;
	String modSysName;
	
	public 	String getState(){
		return this.state;
	}

	public 	java.util.Date getStartDateTime(){
		return this.startDateTime;
	}

	public 	java.util.Date  getEndDateTime (){
		return this.endDateTime;
	}

	public 	Long getLmsUserId (){
		return this.lmsUserId;
	}
	
	public 	Integer getScore (){
		return this.score;
	}
	
	public 	String getModSysName(){
		return this.modSysName;
	}

	public 	void setState(String state){
		this.state = state;
	}

	public 	void setStartDateTime(java.util.Date startDateTime){
		this.startDateTime = startDateTime;
	}

	public 	void setEndDateTime (java.util.Date endDateTime ){
		this.endDateTime = endDateTime;
	}

	public void setLmsUserId (	Long lmsUserId){
		this.lmsUserId = lmsUserId;
	}
	public void setScore(Integer score){
		this.score = score;
	}
	public void setModSysName(String modSysName){
		this.modSysName = modSysName;
	}
	
}
