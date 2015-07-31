package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import LMSDataLoader.dataModels.UserSession;

public class UserSessionDao {
	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public UserSessionDao(Connection conn){
		
		try{
			sqlStmt = "insert into UserSession (sessionSysName, lmsName, orgName, courseName,courseRun, lmsUserId, userName," + 
					"agent,	hostName, ipAddress, url, createDateTime, eventType, eventSource, eventName," + 
					"eventId, lastModDateTime,dataSource,eventNo) values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,  ?,?,?,?)";
			System.out.println("sqlStmt " + sqlStmt);
			ps = conn.prepareStatement(sqlStmt);
			this.conn = conn;
			//return 0;
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
			//return -1;
		}
	}
	public Boolean insertRec(UserSession userSession) {
	
		try {
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
/*
			System.out.println("userSession.getSessionSysName() " + userSession.getSessionSysName());
			System.out.println("userSession.getLmsName() " + userSession.getLmsName());
			System.out.println("userSession.getOrgName() " + userSession.getOrgName()); 
			System.out.println("userSession.getCourseName() " + userSession.getCourseName()); 
			System.out.println("userSession.getCourseRun() " + userSession.getCourseRun());
			System.out.println("userSession.getUserId() " + userSession.getUserId());
			System.out.println("userSession.getUserName() " + userSession.getUserName()); 
			System.out.println("userSession.getAgent() " + userSession.getAgent()); 
			System.out.println("userSession.getHostName() " + userSession.getHostName());
			System.out.println("userSession.getIpAddress() " + userSession.getIpAddress());
			System.out.println(" userSession.getUrl() " +  userSession.getUrl());
			System.out.println("userSession.getAccessDateTime() " + userSession.getAccessDateTime());
			System.out.println("userSession.getEventType() " + userSession.getEventType());
			System.out.println(" userSession.getEventSource() " + userSession.getEventSource());
			System.out.println("userSession.getEventName() " + userSession.getEventName());
			System.out.println("userSession.getEventId() " + userSession.getEventId());
			return true;*/
			 	
			ps.setString(1, userSession.getSessionSysName());
			ps.setString(2, userSession.getLmsName());
			ps.setString(3, userSession.getOrgName()); 
			ps.setString(4, userSession.getCourseName()); 
			ps.setString(5, userSession.getCourseRun());
			ps.setLong(6, userSession.getLmsUserId());
			ps.setString(7, userSession.getUserName()); 
			ps.setString(8, userSession.getAgent()); 
			ps.setString(9, userSession.getHostName());
			ps.setString(10, userSession.getIpAddress());
			ps.setString(11, userSession.getUrl());
			ps.setDate(12, new java.sql.Date(userSession.getCreateDateTime().getTime()));
			ps.setString(13, userSession.getEventType());
			ps.setString(14, userSession.getEventSource());
			ps.setString(15, userSession.getEventName());
			if(userSession.getEventId() == null)
				ps.setNull(16, Types.BIGINT);
			else
				ps.setLong(16, userSession.getEventId());
			ps.setDate(17, new java.sql.Date(userSession.getLastModDateTime().getTime()));
			ps.setString(18, userSession.getDataSource());
			ps.setShort(19, userSession.getEventNo());
			return ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("SQL ERROR IN INSERT REC userSession " + e.toString());
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
			System.out.println("SQL ERROR COSE ALL  userSession " + e.toString());
			return -1;
		}
	}
}
	