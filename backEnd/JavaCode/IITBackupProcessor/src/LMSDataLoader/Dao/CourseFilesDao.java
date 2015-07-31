package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import LMSDataLoader.dataModels.CourseFiles;

public class CourseFilesDao {

	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public CourseFilesDao(Connection conn){
		sqlStmt = "insert into CourseFiles (lmsName,orgName,courseName, chapterSysName, sessionSysName, fileTitle," +
				"fileSysName) values (?,?,?,?,?, ?,?)";
				System.out.println("sqlStmt " + sqlStmt);
		this.conn = conn;
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	//public Long insertRec(courseFiles courseFiles, GetLastId getLastid) {
	public Boolean insertRec(CourseFiles courseFiles) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
		/*				 
				System.out.println("courseFiles.getLmsName() " + courseFiles.getLmsName());
				System.out.println("courseFiles.getOrgName() " +  courseFiles.getOrgName()); 
				System.out.println("courseFiles.getCourseName() " +  courseFiles.getCourseName()); 
				System.out.println("courseFiles.getChapterSysName() " +  courseFiles.getChapterSysName());
				System.out.println("courseFiles.getSessionSysName() " +  courseFiles.getSessionSysName());
				System.out.println("courseFiles.getFileTitle() " +  courseFiles.getFileTitle());
				System.out.println("courseFiles.getFileSysName() " +  courseFiles.getFileSysName());
		*/
                ps.setString(1, courseFiles.getLmsName());
                ps.setString(2, courseFiles.getOrgName());
                ps.setString(3, courseFiles.getCourseName());
                ps.setString(4, courseFiles.getChapterSysName());
                ps.setString(5, courseFiles.getSessionSysName());
                ps.setString(6, courseFiles.getFileTitle());
                ps.setString(7, courseFiles.getFileSysName());

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
