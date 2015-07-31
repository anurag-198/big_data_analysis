package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import LMSDataLoader.dataModels.CourseOthers;

public class CourseOthersDao {

	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public CourseOthersDao(Connection conn){
		sqlStmt = "insert into CourseOthers (lmsName,orgName,courseName,title,htmlSysName,type,verticalSysName,chapterSysName,sessionSysName" + 
				") values (?,?,?,?,?, ?,?,?,?)";
		System.out.println("sqlStmt " + sqlStmt);
				
		this.conn = conn;
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	//public Long insertRec(courseOthers courseOthers, GetLastId getLastid) {
	public Boolean insertRec(CourseOthers courseOthers) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
			/*	
				System.out.println("courseOthers.getLmsName() " + courseOthers.getLmsName());
				System.out.println("courseOthers.getOrgName() " +  courseOthers.getOrgName()); 
				System.out.println("courseOthers.getCourseName() " +  courseOthers.getCourseName()); 
				System.out.println("courseOthers.getPdfTitle() " +  courseOthers.getPdfTitle());
				System.out.println("courseOthers.getPdfSysName() " +  courseOthers.getPdfSysName());
				*/
                ps.setString(1, courseOthers.getLmsName());
                ps.setString(2, courseOthers.getOrgName());
                ps.setString(3, courseOthers.getCourseName());
                ps.setString(4, courseOthers.getTitle());
                ps.setString(5, courseOthers.getHtmlSysName());
                ps.setString(6, courseOthers.getType());
                ps.setString(7, courseOthers.getVerticalSysName());
                ps.setString(8, courseOthers.getChapterSysName());
                ps.setString(9, courseOthers.getSessionSysName());
              
                
                
                
                return(ps.execute());
			//return getLastid.copyLastId("Course");
			// Find Latest Id
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return -1l;
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
