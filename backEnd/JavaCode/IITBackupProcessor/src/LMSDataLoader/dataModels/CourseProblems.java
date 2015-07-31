package LMSDataLoader.dataModels;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CourseProblems {
	Long 	problemId;
	String 	lmsName;
	String	orgName; 
	String	courseName;
	String	chapterSysName;
	//String	sessionSysName;
	String quizSysName;
	String	quizTitle; 
	String	quizType ;
	Float  quizWeight;
	Integer	noOfAttemptsAllowed;
	Float quizMaxMarks;
	Short hintAvailable;
	Short correctChoice;
	String hintMode;
	
	
	public 	Long 	getProblemId(){
		return this.problemId; 
	}
	public 	String 	getLmsName(){
		return this.lmsName; 
	}
	public 	String 	getQuizType(){
		return this.quizType; 
	}
	public 	String 	getChapterSysName(){
		return this.chapterSysName; 
	}
	public 	String	getOrgName(){
		return this.orgName; 
	} 
	public 	String	getCourseName(){
		return this.courseName; 
	}
/*	public 	String	getSessionSysName(){
		return this.sessionSysName; 
	}
*/	public 	String	getQuizTitle(){
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
	public 	Short	getHintAvailable(){
		return this.hintAvailable; 
	}
	public 	String	getHintMode(){
		return this.hintMode; 
	}
	public 	Short	getCorrectChoice(){
		return this.correctChoice; 
	}
	public void 	setProblemId(Long 	problemId){
		 this.problemId = problemId; 
	}
	public void 	setLmsName(String 	lmsName){
		 this.lmsName = lmsName; 
	}
	public void 	setQuizType(String 	quizType){
		 this.quizType = quizType; 
	}
	public void 	setChapterSysName(String 	chapterSysName){
		 this.chapterSysName = chapterSysName; 
	}
	public void 	setOrgName(String	orgName){
		 this.orgName = orgName; 
	} 
	public void 	setCourseName(String	courseName){
		 this.courseName = courseName; 
	}
/*	public void 	setSessionSysName(String	sessionSysName){
		 this.sessionSysName = sessionSysName; 
	}
*/	public void 	setQuizSysName(String	quizSysName){
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
	public void 	setHintAvailable(Short	hintAvailable){
		 this.hintAvailable =hintAvailable; 
	} 
	public void 	setHintMode(String	hintMode){
		 this.hintMode =hintMode; 
	} 
	public void 	setCorrectChoice(Short	correctChoice){
		 this.correctChoice =correctChoice; 
	} 
	
}
