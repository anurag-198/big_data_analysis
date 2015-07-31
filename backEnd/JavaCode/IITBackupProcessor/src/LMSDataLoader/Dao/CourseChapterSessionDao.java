package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import LMSDataLoader.dataModels.CourseChapterSession;

public class CourseChapterSessionDao {
	
	
	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public CourseChapterSessionDao(Connection conn){
		this.conn = conn;
		sqlStmt = "insert into CourseChapterSession ( lmsName, orgName,courseName,chapterSysName,sessionSysName," + 
			//	"sessionTitle,sessionStartDate, position, sessionType) values (?,?,?,?,?, ?,?,?,?)";
			"sessionTitle,sessionStartDate, position) values (?,?,?,?,?, ?,?,?)";
				System.out.println("sqlStmt " + sqlStmt);
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	//public Long insertRec(courseChapterSession courseChapterSession, GetLastId getLastid) {
	public Boolean insertRec(CourseChapterSession courseChapterSession) {
	
		try {
		/*	if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");*/
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
		/*				 
				System.out.println("courseChapterSession.getLmsName() " + courseChapterSession.getLmsName());
				System.out.println("courseChapterSession.getOrgName() " +  courseChapterSession.getOrgName()); 
				System.out.println("courseChapterSession.getCourseName() " +  courseChapterSession.getCourseName()); 
				System.out.println("courseChapterSession.getChapterSysName() " +  courseChapterSession.getChapterSysName());
				System.out.println("courseChapterSession.getSessionSysName() " +  courseChapterSession.getSessionSysName());
				System.out.println("courseChapterSession.getSessionTitle() " +  courseChapterSession.getSessionTitle());
				System.out.println("courseChapterSession.getSessionStartDate() " +  courseChapterSession.getSessionStartDate());
		*/
                ps.setString(1, courseChapterSession.getLmsName());
                ps.setString(2, courseChapterSession.getOrgName());
                ps.setString(3, courseChapterSession.getCourseName());
                ps.setString(4, courseChapterSession.getChapterSysName());
                ps.setString(5, courseChapterSession.getSessionSysName());
                ps.setString(6, courseChapterSession.getSessionTitle());
                if(courseChapterSession.getSessionStartDate() == null)
                	ps.setNull(7, java.sql.Types.DATE);
                else
                	ps.setDate(7, new java.sql.Date(courseChapterSession.getSessionStartDate().getTime()));
                ps.setShort(8, courseChapterSession.getPosition());
                //ps.setString(9, courseChapterSession.getSessionType());
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
