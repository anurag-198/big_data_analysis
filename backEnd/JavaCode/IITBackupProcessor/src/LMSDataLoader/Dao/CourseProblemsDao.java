package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import LMSDataLoader.dataModels.CourseProblems;

public class CourseProblemsDao {
	private PreparedStatement ps = null;
	private Connection conn = null;
	private String sqlStmt;
	public  CourseProblemsDao(Connection conn){
		this.conn = conn;
		try{
			
			sqlStmt = "insert into CourseProblems (lmsName, orgName,courseName, chapterSysName, " + 
							  " quizSysName, quizTitle, quizType, quizWeight, noOfAttemptsAllowed, " + 
							  " quizMaxMarks,hintAvailable,correctChoice, hintMode )  " + 
							  " values (?,?,?,?,?, ?,?,?,?,?, ?,?,?)";

			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	public Boolean insertRec(CourseProblems courseProblems){
	
		try {
			 if(ps == null)	
				 ps = conn.prepareStatement(sqlStmt);

/*			System.out.println("courseProblems.getLmsName() " + courseProblems.getLmsName());
			System.out.println("courseProblems.getOrgName() " + courseProblems.getOrgName()); 
			System.out.println("courseProblems.getCourseName()" + courseProblems.getCourseName()); 
			System.out.println("courseProblems.getCourseConcept() " + courseProblems.getCourseConcept());
			System.out.println("courseProblems.getChapterName() " + courseProblems.getChapterName()); 
			System.out.println("courseProblems.getChapterConcept() " + courseProblems.getChapterConcept()); 
			System.out.println("courseProblems.getQuizTitle() " + courseProblems.getQuizTitle());
			System.out.println("courseProblems.getQuizType() " + courseProblems.getQuizType()); 
			System.out.println("courseProblems.getQuizWeight() " + courseProblems.getQuizWeight()); 
			System.out.println("courseProblems.getNoOfAttemptsAllowed() " + courseProblems.getNoOfAttemptsAllowed());
			System.out.println(" courseProblems.getQuizMaxMarks() " +  courseProblems.getQuizMaxMarks());
			return true;*/
			// System.out.println("courseProblems.getQuizTitle() Length " + courseProblems.getQuizTitle().length());
			 			 
			ps.setString(1, courseProblems.getLmsName());
			ps.setString(2, courseProblems.getOrgName()); 
			ps.setString(3, courseProblems.getCourseName()); 
			ps.setString(4, courseProblems.getChapterSysName());
			ps.setString(5, courseProblems.getQuizSysName());
			//ps.setString(5, courseProblems.getSessionSysName()); 
			//ps.setString(6, courseProblems.getQuizSysName());
			ps.setString(6, courseProblems.getQuizTitle());
			ps.setString(7, courseProblems.getQuizType());
			if(courseProblems.getQuizWeight() != null)
				ps.setFloat(8, courseProblems.getQuizWeight());
			else
				ps.setNull(8, Types.FLOAT);
			if(courseProblems.getNoOfAttemptsAllowed() != null)
				ps.setInt(9, courseProblems.getNoOfAttemptsAllowed());
			else
				ps.setNull(9, Types.INTEGER);
			if(courseProblems.getQuizMaxMarks() != null)
				ps.setFloat(10, courseProblems.getQuizMaxMarks());
			else
				ps.setNull(10, Types.FLOAT);
			
			if(courseProblems.getHintAvailable() != null)
				ps.setShort(11, courseProblems.getHintAvailable());
			else
				ps.setNull(11, Types.VARCHAR);
			if(courseProblems.getCorrectChoice() != null)
				ps.setShort(12, courseProblems.getCorrectChoice());
			else
				ps.setNull(12, Types.SMALLINT);
				
			if(courseProblems.getHintMode() != null)
				ps.setString(13, courseProblems.getHintMode());
			else
				ps.setNull(13, Types.VARCHAR);
			//ps.setString(12, courseProblems.getHintAvailable());
			//ps.setString(13, courseProblems.getHintVisibility());
			
			Boolean bl = ps.execute();
			//System.out.println("EXECUTED EXECUTED ||||");
			return bl;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}	
	public int closeAll(){
		try {
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
				return 0;
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

}
