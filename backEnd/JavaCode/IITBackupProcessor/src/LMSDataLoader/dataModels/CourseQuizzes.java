package LMSDataLoader.dataModels;

public class CourseQuizzes {

	Long 	quizId;
	String 	lmsName;
	String	orgName; 
	String	courseName;
	String	courseConcept;
	String	chapterName;
	String	chapterConcept;
	String	quizTitle; 
	String	quizType ;
	Float  quizWeight;
	Integer	noOfAttemptsAllowed;
	Float quizMaxMarks;
	String quizSysName;
	String hintAvailable;
	String hintVisibility;
	
	
	
	public 	Long 	getQuizId(){
		return this.quizId; 
	}
	public 	String 	getLmsName(){
		return this.lmsName; 
	}
	public 	String 	getQuizType(){
		return this.quizType; 
	}
	public 	String 	getChapterConcept(){
		return this.chapterConcept; 
	}
	public 	String 	getCourseConcept(){
		return this.courseConcept; 
	}
	public 	String	getOrgName(){
		return this.orgName; 
	} 
	public 	String	getCourseName(){
		return this.courseName; 
	}
	public 	String	getChapterName(){
		return this.chapterName; 
	}
	public 	String	getQuizTitle(){
		return this.quizTitle; 
	} 
	public 	Integer	getNoOfAttemptsAllowed(){
		return this.noOfAttemptsAllowed; 
	}
	public 	Float	getQuizWeight(){
		return this.quizWeight; 
	}
	public 	Float	getQuizMaxMarks(){
		return this.quizMaxMarks; 
	}
	public 	String	getQuizSysName(){
		return this.quizSysName; 
	} 
	public 	String	getHintAvailable(){
		return this.hintAvailable; 
	}
	public 	String	getHintVisibility(){
		return this.hintVisibility; 
	}
	public void 	setQuizId(Long 	quizId){
		 this.quizId = quizId; 
	}
	public void 	setLmsName(String 	lmsName){
		 this.lmsName = lmsName; 
	}
	public void 	setQuizType(String 	quizType){
		 this.quizType = quizType; 
	}
	public void 	setChapterConcept(String 	chapterConcept){
		 this.chapterConcept = chapterConcept; 
	}
	public void 	setCourseConcept(String 	courseConcept){
		 this.courseConcept = courseConcept; 
	}
	public void 	setOrgName(String	orgName){
		 this.orgName = orgName; 
	} 
	public void 	setCourseName(String	courseName){
		 this.courseName = courseName; 
	}
	public void 	setChapterName(String	chapterName){
		 this.chapterName = chapterName; 
	}
	public void 	setQuizSysName(String	quizSysName){
		 this.quizSysName =quizSysName; 
	} 
	public void 	setNoOfAttemptsAllowed(Integer	noOfAttemptsAllowed){
		 this.noOfAttemptsAllowed = noOfAttemptsAllowed; 
	}
	public void 	setQuizWeight(Float	quizWeight){
		 this.quizWeight = quizWeight; 
	}
	public void 	setQuizMaxMarks(Float	quizMaxMarks){
		 this.quizMaxMarks = quizMaxMarks; 
	}
	public void 	setQuizTitle(String	quizTitle){
		 this.quizTitle =quizTitle; 
	} 
	public void 	setHintAvailable(String	hintAvailable){
		 this.hintAvailable =hintAvailable; 
	} 
	public void 	setHintVisibility(String	hintVisibility){
		 this.hintVisibility =hintVisibility; 
	} 
	
}
