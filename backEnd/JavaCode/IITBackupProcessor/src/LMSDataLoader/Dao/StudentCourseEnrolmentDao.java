package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import LMSDataLoader.dataModels.StudentCourseEnrolment;

public class StudentCourseEnrolmentDao {
	private PreparedStatement ps = null;
	private Connection conn = null;
	private String sqlStmt = null;
	public StudentCourseEnrolmentDao(Connection conn){
		this.conn = conn;
		try{
			
			sqlStmt = "insert into StudentCourseEnrolment(lmsName,	orgName, courseName, courseRun,lmsUserId,"
					+ "enrolmentDate, active,mode)  values (?,?,?,?,?,  ?,?,?)";
			System.out.println("sqlStmt " + sqlStmt);
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	public Boolean insertRec(StudentCourseEnrolment studentCourseEnrolment) {
	
		try {
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
/*
			System.out.println( "StudentCourseEnrolment.getLmsName() " + studentCourseEnrolment.getLmsName());
			System.out.println("StudentCourseEnrolment.getOrgName() " + studentCourseEnrolment.getOrgName()); 
			System.out.println("StudentCourseEnrolment.getCourseName() " + studentCourseEnrolment.getCourseName()); 
			System.out.println("StudentCourseEnrolment.getCourseRun() " + studentCourseEnrolment.getCourseRun());
			System.out.println(" StudentCourseEnrolment.getProbSysName() " + studentCourseEnrolment.getLmsUserId()); 
			System.out.println("StudentCourseEnrolment.getDate() " + studentCourseEnrolment.getEnrolmentDate());  
			System.out.println("StudentCourseEnrolment.getActive() " + studentCourseEnrolment.getActive());
			System.out.println("StudentCourseEnrolment.getMode() " + studentCourseEnrolment.getMode());
			return true;*/
			 
			ps.setString(1, studentCourseEnrolment.getLmsName());
			if(studentCourseEnrolment.getOrgName() == null)
				ps.setNull(2, Types.VARCHAR); 
			else
				ps.setString(2, studentCourseEnrolment.getOrgName());
			ps.setString(3, studentCourseEnrolment.getCourseName()); 
			ps.setString(4, studentCourseEnrolment.getCourseRun());
			ps.setLong(5, studentCourseEnrolment.getLmsUserId());
			ps.setDate(6, new java.sql.Date(studentCourseEnrolment.getEnrolmentDate().getTime()));
			ps.setInt(7, studentCourseEnrolment.getActive()); 
			ps.setString(8, studentCourseEnrolment.getMode());
			 
			
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

