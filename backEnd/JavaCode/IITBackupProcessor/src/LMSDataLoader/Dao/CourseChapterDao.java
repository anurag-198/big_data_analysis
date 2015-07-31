package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import LMSDataLoader.dataModels.CourseChapter;

public class CourseChapterDao {
	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt;
	public CourseChapterDao(Connection conn){
		this.conn = conn;
		try{
			sqlStmt = "insert into CourseChapter (lmsName, orgName,courseName," + 
					"chapteTitle,chapterSysName, chapterStartDate, position) values (?,?,?,?,?, ?,?)";
			//"chapteTitle,chapterSysName) values (?,?,?,?,?)";

			ps = conn.prepareStatement(sqlStmt);
			
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	//public Long insertRec(CourseChapter courseChapter, GetLastId getLastId){
	public Boolean insertRec(CourseChapter courseChapter){
		try {
		/*	System.out.println(" courseChapter.getLmsName() " + courseChapter.getLmsName());
			System.out.println("courseChapter.getOrgName() " + courseChapter.getOrgName()); 
			System.out.println("courseChapter.getCourseName() " + courseChapter.getCourseName()); 
			System.out.println("courseChapter.getChapterTitle() " + courseChapter.getChapterTitle()); 
			System.out.println("courseChapter.getChapterSysName() " + courseChapter.getChapterSysName());
			*/
			
			ps = conn.prepareStatement(sqlStmt);
			ps.setString(1, courseChapter.getLmsName());
			ps.setString(2, courseChapter.getOrgName()); 
			ps.setString(3, courseChapter.getCourseName()); 
			ps.setString(4, courseChapter.getChapterTitle()); 
			ps.setString(5, courseChapter.getChapterSysName());
			if(courseChapter.getChapterStartDate() == null)
				ps.setNull(6, Types.DATE);
			else
				ps.setDate(6, new java.sql.Date(courseChapter.getChapterStartDate().getTime()));
			ps.setInt(7, courseChapter.getPosition());
			return(ps.execute());
			//return getLastId.copyLastId("CourseChapter");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		//return true;
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
