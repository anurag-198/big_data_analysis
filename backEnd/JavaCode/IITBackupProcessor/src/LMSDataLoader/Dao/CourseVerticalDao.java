package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import LMSDataLoader.dataModels.CourseVertical;

public class CourseVerticalDao {

	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public CourseVerticalDao(Connection conn){
		sqlStmt = "insert into CourseVertical (lmsName,orgName,courseName, sessionSysName, verticalSysName " +
				") values (?,?,?, ?,?)";
				System.out.println("sqlStmt " + sqlStmt);
		this.conn = conn;
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	//public Long insertRec(courseVertical courseVertical, GetLastId getLastid) {
	public Boolean insertRec(CourseVertical courseVertical) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
		/*				 
				System.out.println("courseVertical.getLmsName() " + courseVertical.getLmsName());
				System.out.println("courseVertical.getOrgName() " +  courseVertical.getOrgName()); 
				System.out.println("courseVertical.getCourseName() " +  courseVertical.getCourseName()); 
				System.out.println("courseVertical.getChapterSysName() " +  courseVertical.getChapterSysName());
				System.out.println("courseVertical.getSessionSysName() " +  courseVertical.getSessionSysName());
				System.out.println("courseVertical.getFileTitle() " +  courseVertical.getFileTitle());
				System.out.println("courseVertical.getFileSysName() " +  courseVertical.getFileSysName());
		*/
              
                ps.setString(1, courseVertical.getLmsName());
                ps.setString(2, courseVertical.getOrgName());
                ps.setString(3, courseVertical.getCourseName());
                ps.setString(4, courseVertical.getSessionSysName());
                ps.setString(5, courseVertical.getVerticalSysName());
               

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
