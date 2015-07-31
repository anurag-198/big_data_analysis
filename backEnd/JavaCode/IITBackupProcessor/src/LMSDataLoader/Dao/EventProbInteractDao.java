package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import utility.GetLastId;
import LMSDataLoader.dataModels.EventProbInteract;

public class EventProbInteractDao {
	private static PreparedStatement ps = null;
	private Connection conn = null;
	
	private static String sqlStmt = "insert into EventProbInteract(	lmsName,orgName, courseName,lmsUserId," +
			"eventName,eventNo,quizzSysName,quizzTitle,chapterSysName,chapterTitle,hintAvailable,hintAvialabilty,inputType,responseType," +
			"variantId,oldScore,newScore,maxGrade,attempts,maxAttempts,choice, success, " +
			"source, probSubTime,done,createDateTime,lastModDateTime,courseRun) " +
			" values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? , ?,?,?)";
	
	public EventProbInteractDao(Connection conn){
		this.conn = conn;
		 try {
			ps = conn.prepareStatement(sqlStmt);
			System.out.println("ps is CREATED " + ps);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//public Boolean insertRec(EventProbInteract probInteractDetails) {
	public Long insertRec(EventProbInteract probInteractDetails, GetLastId  getLastId) {
		try {
			System.out.println("ps Value " + ps);
			 if(ps == null){
				 System.out.println("ps is null");
				 ps = conn.prepareStatement(sqlStmt);
			 }
			 ps.setString(1, probInteractDetails.getLmsName());
			ps.setString(2, probInteractDetails.getOrgName()); 
			ps.setString(3, probInteractDetails.getCourseName());
			ps.setLong(4, probInteractDetails.getLmsUserId());
			ps.setString(5, probInteractDetails.getEventName());
			if(probInteractDetails.getEventNo() ==null)
				ps.setNull(6, Types.SMALLINT);
			else
				ps.setShort(6, probInteractDetails.getEventNo());
			ps.setString(7, probInteractDetails.getQuizzSysName());
			ps.setString(8, probInteractDetails.getQuizzTitle());
			ps.setString(9, probInteractDetails.getChapterSysName());
			ps.setString(10, probInteractDetails.getChapterTitle());
			ps.setString(11, probInteractDetails.getHintAvailable());;
			ps.setString(12, probInteractDetails.getHintMode()); 
			ps.setString(13, probInteractDetails.getInputType());;
			ps.setString(14, probInteractDetails.getResponseType()); 
			ps.setString(15, probInteractDetails.getVariantId());
			if (probInteractDetails.getOldScore() == null)
				ps.setNull(16, Types.DOUBLE);
			else
				ps.setDouble(16, probInteractDetails.getOldScore());
			if (probInteractDetails.getNewScore() == null)
				ps.setNull(17, Types.DOUBLE);
			else
				ps.setDouble(17, probInteractDetails.getNewScore());
			if (probInteractDetails.getMaxGrade() == null)
				ps.setNull(18, Types.DOUBLE);
			else
				ps.setDouble(18, probInteractDetails.getMaxGrade());
			
			if (probInteractDetails.getAttempts() == null)
				ps.setNull(19, Types.INTEGER);
			else
				ps.setInt(19, probInteractDetails.getAttempts());
			if (probInteractDetails.getMaxAttempts() == null)
				ps.setNull(20, Types.INTEGER);
			else
				ps.setInt(20, probInteractDetails.getMaxAttempts());
			ps.setString(21, probInteractDetails.getChoice());
			ps.setString(22, probInteractDetails.getSuccess());
			ps.setString(23, probInteractDetails.getSource());
			if (probInteractDetails.getProbSubTime() == null)
				ps.setNull(24, Types.DATE);
			else
				ps.setDate(24, new java.sql.Date(probInteractDetails.getProbSubTime().getTime()));
			if(probInteractDetails.getDone() ==null)
				ps.setNull(25, Types.VARCHAR);
			else
				ps.setString(25, probInteractDetails.getDone());
			ps.setDate(26, new java.sql.Date(probInteractDetails.getCreateDateTime().getTime()));
			ps.setDate(27, new java.sql.Date(probInteractDetails.getLastModDateTime().getTime()));
			ps.setString(28, probInteractDetails.getCourseRun());
			ps.execute();
		/*	ResultSet rs  = stmt2.executeQuery(sqlStmt2);
			rs.next();
			Long id = rs.getLong(1);
			System.out.println("EVNT ID RETURNED (VIDEO) " + id);
			*/
			//Long eventId = getLastId.copyLastId("EventProbInteract");
			//System.out.println("EVNT ID RETURNED (VIDEO) " + eventId);
			return getLastId.copyLastId("EventProbInteract");
			//return -1l;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQL ERROR IN INSERTING REC IN EVENPROBINTERACT " + e.toString());
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
