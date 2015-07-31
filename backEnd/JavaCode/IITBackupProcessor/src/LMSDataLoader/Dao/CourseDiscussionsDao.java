package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import LMSDataLoader.dataModels.CourseDiscussions;

public class CourseDiscussionsDao {
	
	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public CourseDiscussionsDao(Connection conn){
		//sqlStmt = "insert into CourseDiscussions ( lmsName, orgName, courseName, chapterSysName, sessionSysName," + 
		sqlStmt = "insert into CourseDiscussions ( lmsName, orgName, courseName, chapterSysName, " +
			//	"discussionTitle, discussionSysName, discussCategory, discussionSysId) values (?,?,?,?,?, ?,?,?,?)";
			"discussionTitle, discussionSysName,discussionSysId) values (?,?,?,?,?, ?,?)";
				System.out.println("sqlStmt " + sqlStmt);
		this.conn = conn;
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		} 
	}
	//public Long insertRec(courseDiscussions courseDiscussions, GetLastId getLastid) {
	public Boolean insertRec(CourseDiscussions courseDiscussions) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
			 
			/*	System.out.println("courseDiscussions.getLmsName() " + courseDiscussions.getLmsName());
				System.out.println("courseDiscussions.getOrgName() " +  courseDiscussions.getOrgName()); 
				System.out.println("courseDiscussions.getCourseName() " +  courseDiscussions.getCourseName()); 
				System.out.println("courseDiscussions.getChapterSysName() " +  courseDiscussions.getChapterSysName());
				System.out.println("courseDiscussions.getSessionSysName() " +  courseDiscussions.getSessionSysName());
				System.out.println("courseDiscussions.getDiscussionTitle() " +  courseDiscussions.getDiscussionTitle());
				System.out.println("courseDiscussions.getDiscussionSysName() " +  courseDiscussions.getDiscussionSysName());
		*/
                ps.setString(1, courseDiscussions.getLmsName());
                ps.setString(2, courseDiscussions.getOrgName());
                ps.setString(3, courseDiscussions.getCourseName());
                ps.setString(4, courseDiscussions.getChapterSysName());
              //  ps.setString(5, courseDiscussions.getSessionSysName());
                ps.setString(5, courseDiscussions.getDiscussionTitle());
                ps.setString(6, courseDiscussions.getDiscussionSysName());
                //ps.setString(8, courseDiscussions.getDiscussCategory());
               ps.setString(7, courseDiscussions.getDiscussSysId());

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
