package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utility.GetLastId;
import LMSDataLoader.dataModels.EventForumInteract;

public class EventForumInteractDao {
	private PreparedStatement ps = null;
	private Connection conn = null;
	private String sqlStmt;
	public  EventForumInteractDao(Connection conn){
		this.conn = conn;
		try{
			
			sqlStmt = "INSERT INTO EventForumInteract(lmsName,orgName,courseName,eventName," +
						"commentThreadId,lmsUserId,queryText,noOfResults) VALUES(?,?,?,?,?, ?,?,?)";

			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	public Long insertRec(EventForumInteract eventForumInteract,GetLastId getLastId){
	
		try {
			 if(ps == null)	
				 ps = conn.prepareStatement(sqlStmt);	 
			ps.setString(1, eventForumInteract.getLmsName());
			ps.setString(2, eventForumInteract.getOrgName()); 
			ps.setString(3, eventForumInteract.getCourseName()); 
			ps.setString(4, eventForumInteract.getEventName());
			ps.setString(5, eventForumInteract.getCommentThreadId());
			ps.setLong(6, eventForumInteract.getLmsUserId());
			ps.setString(7, eventForumInteract.getQueryText());
			ps.setInt(8, eventForumInteract.getNoOfResults());
			/*
			if(eventForumInteract.getQuizWeight() != null)
				ps.setFloat(7, eventForumInteract.getQuizWeight());
			else
				ps.setNull(7, Types.FLOAT);
			*/
			Boolean bl = ps.execute();
			Long lastid = getLastId.copyLastId("EventCourseInteract");
			System.out.println("IN eventCourseInteractdao last id " +  lastid);
		    //return eventId.copyLastId("EventCourseInteract");
			return lastid;
			//System.out.println("EXECUTED EXECUTED ||||");
			//return bl;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1l;
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
