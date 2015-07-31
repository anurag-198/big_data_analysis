package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import LMSDataLoader.dataModels.StudentCourseGrades;

public class StudentCourseGradeDao {
	private PreparedStatement ps = null;
	private Connection conn = null;
	private String sqlStmt = null;
	public StudentCourseGradeDao(Connection conn){
		this.conn = conn;
		try{
			sqlStmt = "insert into StudentCourseGrades(lmsName,	orgName, courseName, courseRun,lmsUserId, lmsUserName, moduleType, moduleSysName," +
					" score, maxScore,noOfAttempts, hintAvailable, hintUsed, state, goals, createDateTime, lastModDateTime"
					+ ", totSessDuraInSecs )" +
					" values (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?, ?,?,?)";
			System.out.println("sqlStmt " + sqlStmt);
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	public Boolean insertRec(StudentCourseGrades studentCourseGrades) {
	
		try {
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);

/*			System.out.println( "studentCourseGrades.getLmsName() " + studentCourseGrades.getLmsName());
			System.out.println("studentCourseGrades.getOrgName() " + studentCourseGrades.getOrgName()); 
			System.out.println("studentCourseGrades.getCourseName() " + studentCourseGrades.getCourseName()); 
			System.out.println("studentCourseGrades.getCourseRun() " + studentCourseGrades.getCourseRun());
			System.out.println(" studentCourseGrades.getProbSysName() " + studentCourseGrades.getProbSysName()); 
			System.out.println("studentCourseGrades.getScore() " + studentCourseGrades.getScore());  
			System.out.println("studentCourseGrades.getMaxScore() " + studentCourseGrades.getMaxScore());
			return true;*/

			ps.setString(1, studentCourseGrades.getLmsName());
			ps.setString(2, studentCourseGrades.getOrgName()); 
			ps.setString(3, studentCourseGrades.getCourseName()); 
			ps.setString(4, studentCourseGrades.getCourseRun());
			ps.setLong(5, studentCourseGrades.getLmsUserId());
			ps.setString(6, studentCourseGrades.getLmsUserName());
			ps.setString(7, studentCourseGrades.getModuleType()); 
			ps.setString(8, studentCourseGrades.getModuleSysName());
			if(studentCourseGrades.getScore() == null)
				ps.setNull(9, Types.INTEGER);
			else
				ps.setInt(9, studentCourseGrades.getScore());
			if(studentCourseGrades.getMaxScore() == null)
				ps.setNull(10, Types.INTEGER);
			else
				ps.setInt(10, studentCourseGrades.getMaxScore()); 
			if(studentCourseGrades.getNoOfAttempts() == null)
				ps.setNull(11, Types.INTEGER);
			else
				ps.setInt(11, studentCourseGrades.getNoOfAttempts());
			ps.setString(12, studentCourseGrades.getHintAvailable()); 
			ps.setString(13, studentCourseGrades.getHintUsed());
			ps.setString(14, studentCourseGrades.getState()); 
			ps.setString(15, studentCourseGrades.getGoals());
			Timestamp timestamp = new Timestamp(studentCourseGrades.getCreateDateTime().getTime());
			ps.setTimestamp(16, timestamp);
			timestamp= new Timestamp(studentCourseGrades.getLastModDateTime().getTime());
			ps.setTimestamp(17, timestamp);
			ps.setInt(18, studentCourseGrades.getTotSessDuraInSecs());
			return ps.execute();
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

