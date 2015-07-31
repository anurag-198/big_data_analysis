package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import LMSDataLoader.dataModels.CourseQuizzes;

public class CourseQuizzesDao {
	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt;
	public  CourseQuizzesDao(Connection conn){
		this.conn = conn;
		try{
			sqlStmt = "insert into CourseQuizzes (lmsName, orgName,courseName, courseConcept, " + 
							  " chapterName, chapterConcept, quizTitle, quizType, quizWeight, noOfAttemptsAllowed, " + 
							  " quizMaxMarks, quizSysName,hintAvailable,hintVisibility)  " + 
							  " values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?)";

			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	public Boolean insertRec(CourseQuizzes courseQuizzes){
	
		try {
			 if(ps == null)	
				 ps = conn.prepareStatement(sqlStmt);

/*			System.out.println("courseQuizzes.getLmsName() " + courseQuizzes.getLmsName());
			System.out.println("courseQuizzes.getOrgName() " + courseQuizzes.getOrgName()); 
			System.out.println("courseQuizzes.getCourseName()" + courseQuizzes.getCourseName()); 
			System.out.println("courseQuizzes.getCourseConcept() " + courseQuizzes.getCourseConcept());
			System.out.println("courseQuizzes.getChapterName() " + courseQuizzes.getChapterName()); 
			System.out.println("courseQuizzes.getChapterConcept() " + courseQuizzes.getChapterConcept()); 
			System.out.println("courseQuizzes.getQuizTitle() " + courseQuizzes.getQuizTitle());
			System.out.println("courseQuizzes.getQuizType() " + courseQuizzes.getQuizType()); 
			System.out.println("courseQuizzes.getQuizWeight() " + courseQuizzes.getQuizWeight()); 
			System.out.println("courseQuizzes.getNoOfAttemptsAllowed() " + courseQuizzes.getNoOfAttemptsAllowed());
			System.out.println(" courseQuizzes.getQuizMaxMarks() " +  courseQuizzes.getQuizMaxMarks());
			return true;*/
			// System.out.println("courseQuizzes.getQuizTitle() Length " + courseQuizzes.getQuizTitle().length());

			ps.setString(1, courseQuizzes.getLmsName());
			ps.setString(2, courseQuizzes.getOrgName()); 
			ps.setString(3, courseQuizzes.getCourseName()); 
			ps.setString(4, courseQuizzes.getCourseConcept());
			ps.setString(5, courseQuizzes.getChapterName()); 
			ps.setString(6, courseQuizzes.getChapterConcept()); 
			ps.setString(7, courseQuizzes.getQuizTitle());
			ps.setString(8, courseQuizzes.getQuizType());
			if(courseQuizzes.getQuizWeight() != null)
				ps.setFloat(9, courseQuizzes.getQuizWeight());
			else
				ps.setNull(9, Types.FLOAT);
			if(courseQuizzes.getNoOfAttemptsAllowed() != null)
				ps.setInt(10, courseQuizzes.getNoOfAttemptsAllowed());
			else
				ps.setNull(10, Types.INTEGER);
			if(courseQuizzes.getQuizMaxMarks() != null)
				ps.setFloat(11, courseQuizzes.getQuizMaxMarks());
			else
				ps.setNull(11, Types.FLOAT);
			ps.setString(12, courseQuizzes.getQuizSysName());
			ps.setString(13, courseQuizzes.getHintAvailable());
			ps.setString(14, courseQuizzes.getHintVisibility());
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
