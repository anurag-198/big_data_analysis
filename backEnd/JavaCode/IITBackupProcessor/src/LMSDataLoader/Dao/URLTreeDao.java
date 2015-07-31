package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import LMSDataLoader.dataModels.URLTree;

public class URLTreeDao {
	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public URLTreeDao(Connection conn){
		sqlStmt = "insert into URLTree (lmsName,orgName,courseName,courseRun,urlSysName,urlType, parentUrl) " + 
						" values (?,?,?,?,?, ?,?)";
		System.out.println("sqlStmt " + sqlStmt);
		this.conn = conn;
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	//public Long insertRec(URLTreeData URLTreeData, GetLastId getLastid) {
	public Boolean insertRec(URLTree URLTreeData) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);

			ps.setString(1, URLTreeData.getLmsName());
			ps.setString(2, URLTreeData.getOrgName()); 
			ps.setString(3, URLTreeData.getCourseName());
			ps.setString(4, URLTreeData.getCourseRun()); 
			ps.setString(5, URLTreeData.getUrlSysName());
			ps.setString(6, URLTreeData.getUrlType());
			if(URLTreeData.getParentUrl() == null)
				ps.setNull(7, java.sql.Types.VARCHAR);
			else
				ps.setString(7, URLTreeData.getParentUrl());
			return(ps.execute());
			//return getLastid.copyLastId("URLTree");
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
			sqlStmt = "update URLTree ";
			for(int i=0; i < colNames.length; i++){
				if (i ==0)
					sqlStmt += " set " + colNames[i] + " = " + colValues[i];
				else
					sqlStmt += ", set " + colNames[i] + " = " + colValues[i];
			}
			sqlStmt += " where URLTreeId = " + String.valueOf(id);
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

