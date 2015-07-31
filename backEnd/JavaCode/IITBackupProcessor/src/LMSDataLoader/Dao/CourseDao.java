package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import LMSDataLoader.dataModels.Course;

public class CourseDao {
	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public CourseDao(Connection conn){
		sqlStmt = "insert into Course (lmsName, orgName,courseName,courseTitle," + 
				" authorUserId,currConcepts,prevConcepts,courseLang," + 
				" minPrice,suggestedPrice, currencyCode,endDate, startDate)" + 
				" values (?,?,?,?,?, ?,?,?,?,?, ?,?,?)";
				System.out.println("sqlStmt " + sqlStmt);
		this.conn = conn;
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	//public Long insertRec(CourseData courseData, GetLastId getLastid) {
	public Boolean insertRec(Course courseData) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);

		/*		System.out.println("courseData.getLmsName() " + courseData.getLmsName());
				System.out.println("courseData.getOrgName() " + courseData.getOrgName()); 
				System.out.println("courseData.getCourseName() " +  courseData.getCourseName()); 
				System.out.println("courseData.getCourseTitle() " + courseData.getCourseTitle());
				System.out.println("courseData.getAuthorUserId() " + courseData.getAuthorUserId()); 
				System.out.println("courseData.getCurrConcepts() " + courseData.getCurrConcepts()); 
				System.out.println("courseData.getPrevConcepts() " + courseData.getPrevConcepts()); 
				//System.out.println("courseData.getCreationDate() " + courseData.getCreationDate()); 
				//System.out.println("courseData.getLastModifiedDate() " + courseData.getLastModifiedDate()); 
				System.out.println("courseData.getLanguage() " + courseData.getLanguage()); 
				System.out.println("courseData.getMinPrice() " + courseData.getMinPrice()); 
				System.out.println("courseData.getSuggestedPrices() " + courseData.getSuggestedPrices()); 
				System.out.println("courseData.getCurrencyCode() " + courseData.getCurrencyCode()); 
				System.out.println("courseData.getEndDate() " + courseData.getEndDate()); 
				System.out.println("courseData  .getStartDate() " + courseData  .getStartDate());
				return true;*/

			ps.setString(1, courseData.getLmsName());
			ps.setString(2, courseData.getOrgName()); 
			ps.setString(3, courseData.getCourseName()); 
			ps.setString(4, courseData.getCourseTitle());
			if(courseData.getAuthorUserId() == null)
				ps.setNull(5, java.sql.Types.BIGINT);
			else
				ps.setLong(5, courseData.getAuthorUserId()); 
			ps.setString(6, courseData.getCurrConcepts()); 
			ps.setString(7, courseData.getPrevConcepts()); 
			//ps.setDate(8, new java.sql.Date(courseData.getCreationDate().getTime())); 
			//ps.setDate(9,  new java.sql.Date(courseData.getLastModifiedDate().getTime())); 
			if(courseData.getLanguage() ==  null)
				ps.setNull(8, Types.VARCHAR);
			else
				ps.setString(8, courseData.getLanguage());
			if(courseData.getMinPrice() == null)
				ps.setNull(9, java.sql.Types.INTEGER);
			else
				ps.setInt(9, courseData.getMinPrice());
			if(courseData.getSuggestedPrices() == null)
				ps.setNull(10, java.sql.Types.INTEGER);
			else
				ps.setInt(10, courseData.getSuggestedPrices()); 
			ps.setString(11, courseData.getCurrencyCode()); 
			//ps.setString(12, courseData.getCourseRun());
			if(courseData.getEndDate() != null)
				ps.setDate(12,  new java.sql.Date(courseData.getEndDate().getTime()));
			else
				ps.setNull(12, java.sql.Types.DATE);
			if(courseData.getStartDate() != null)
				ps.setDate(13, new java.sql.Date(courseData.getStartDate().getTime()));
			else
				ps.setNull(13, java.sql.Types.DATE);
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
	public void updateRec(String[] colNames, String[] colValues, Long id) {		
		try {
			sqlStmt = "update Course ";
			for(int i=0; i < colNames.length; i++){
				if (i ==0)
					sqlStmt += " set " + colNames[i] + " = " + colValues[i];
				else
					sqlStmt += ", set " + colNames[i] + " = " + colValues[i];
			}
			sqlStmt += " where courseId = " + String.valueOf(id);
			System.out.println("sqlStmt " + sqlStmt);
			ps = conn.prepareStatement(sqlStmt);
			ps.executeUpdate();
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
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

