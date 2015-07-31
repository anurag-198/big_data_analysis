package LMSDataLoader.dataModels;

public class CourseVertical {

	Long vertId;
	String 	lmsName;
	String	orgName; 
	String	courseName;
	String sessionSysName; 
	String verticalSysName; 
	
	
	
	
	public 	Long 	getVertId(){
		return this.vertId; 
	}
	public 	String 	getLmsName(){
		return this.lmsName; 
	}

	public 	String	getOrgName(){
		return this.orgName; 
	} 
	public 	String	getCourseName(){
		return this.courseName; 
	}
	public 	String	getSessionSysName(){
		return this.sessionSysName; 
	}
	public 	String	getVerticalSysName(){
		return this.verticalSysName; 
	} 
	
	public void	setVertId(Long vertId){
	     this.vertId = vertId; 
	}
	
	public void 	setLmsName(String curVal){
		 this.lmsName = curVal; 
	}
	
	public void 	setOrgName(String curVal){
		 this.orgName = curVal; 
	} 
	public void 	setCourseName(String curVal){
		 this.courseName = curVal; 
	}
	public void 	setSessionSysName(String curVal){
		 this.sessionSysName = curVal; 
	}
	public void 	setVerticalSysName(String curVal){
		 this.verticalSysName = curVal; 
	} 
	
	
}
