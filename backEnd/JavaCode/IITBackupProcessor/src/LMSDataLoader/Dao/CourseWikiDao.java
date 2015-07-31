package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import LMSDataLoader.dataModels.CourseWiki;

public class CourseWikiDao {
	

	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public CourseWikiDao(Connection conn){
		this.conn = conn;
		sqlStmt = "insert into CourseWiki(lmsName,orgName,courseName,wikiSlug) values (?,?,?,?)";
				System.out.println("sqlStmt " + sqlStmt);
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		} 
	}
	//public Long insertRec(courseWiki courseWiki, GetLastId getLastid) {
	public Boolean insertRec(CourseWiki courseWiki) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
				
		/*		System.out.println("courseWiki.getLmsName() " + courseWiki.getLmsName());
				System.out.println("courseWiki.getOrgName() " +  courseWiki.getOrgName()); 
				System.out.println("courseWiki.getCourseName() " +  courseWiki.getCourseName()); 
				System.out.println("courseWiki.getWikiSlug() " +  courseWiki.getWikiSlug());
			*/	
				ps.setString(1, courseWiki.getLmsName());
                ps.setString(2, courseWiki.getOrgName());
                ps.setString(3, courseWiki.getCourseName());
                ps.setString(4, courseWiki.getWikiSlug());
                
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
