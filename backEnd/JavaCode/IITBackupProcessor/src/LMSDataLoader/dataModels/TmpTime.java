package LMSDataLoader.dataModels;

public class TmpTime {
	Integer idTmpTime;
	java.util.Date startDateTime;
	java.util.Date endDateTime;
	Integer  totTimeSpent ;
	String strTimeSpent;
	
	public 	Integer getIdTmpTime (){
		return this.idTmpTime;
	}
	public 	java.util.Date getStartDateTime(){
		return this.startDateTime;
	}
	public 	java.util.Date getEndDateTime(){
		return this.endDateTime;
	}
	public 	Integer  getTotTimeSpent (){
		return this.totTimeSpent;
	}
	public 	String  getStrTimeSpent (){
		return this.strTimeSpent;
	}
	public void setIdTmpTime(Integer idTmpTime){
		this.idTmpTime = idTmpTime;
	}
	public 	void setStartDateTime(java.util.Date startDateTime){
		this.startDateTime = startDateTime;
	}
	public 	void setEndDateTime(java.util.Date endDateTime){
		this.endDateTime = endDateTime;
	}
	public 	void setTotTimeSpent (Integer totTimeSpent ){
		this.totTimeSpent = totTimeSpent;
	}
	public 	void setStrTimeSpent (String strTimeSpent ){
		this.strTimeSpent = strTimeSpent;
	}
}
