package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utility.GetLastId;
import LMSDataLoader.dataModels.EventCourseInteract;



public class EventCourseInteractDao {
	private static PreparedStatement ps = null;
	private static Connection conn = null;
	private static String sqlStmt = null;
	
	public EventCourseInteractDao(Connection conn){
		try{
			
			this.conn = conn;
			
			
			sqlStmt = "insert into EventCourseInteract ( lmsName, orgName, courseName, courseRun, lmsUserId," +
					"eventName,eventNo,  moduleType, moduleSysName,moduleTitle,chapterSysName,chapterTitle," +
					"createDateTime, modDateTime, oldPosition,  curPosition,  source) values (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?  ?,?)";
			//System.out.println("sqlStmt " + sqlStmt);
			//System.out.println("after insert lavita");
			ps = conn.prepareStatement(sqlStmt);
			// get lastId
			//return 0;
		}  catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
			//return -1;
		}
	}
	public long insertRec(EventCourseInteract eventCourseInteractDetails,GetLastId eventId) {
	
		try {
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
			 System.out.println("inside insert recore labansjna");
			ps.setString(1, eventCourseInteractDetails.getLmsName());
			ps.setString(2, eventCourseInteractDetails.getOrgName()); 
			ps.setString(3, eventCourseInteractDetails.getCourseName()); 
			ps.setString(4, eventCourseInteractDetails.getCourseRun());
			if(eventCourseInteractDetails.getLmsUserId() == null)
				ps.setNull(5, java.sql.Types.BIGINT);
			else
				ps.setLong(5, eventCourseInteractDetails.getLmsUserId()); 
			ps.setString(6, eventCourseInteractDetails.getEventName());
			ps.setShort(7, eventCourseInteractDetails.getEventNo());
			ps.setString(8, eventCourseInteractDetails.getModuleType());
			ps.setString(9, eventCourseInteractDetails.getModuleSysName());
			ps.setString(10, eventCourseInteractDetails.getModuleTitle());
			ps.setString(11, eventCourseInteractDetails.getChapterSysName());
			ps.setString(12, eventCourseInteractDetails.getChapterTitle());
			ps.setDate(13, new java.sql.Date(eventCourseInteractDetails.getCreateDateTime().getTime()));
			ps.setDate(14, new java.sql.Date(eventCourseInteractDetails.getModDateTime().getTime()));
			if(eventCourseInteractDetails.getOldPosition() == null)
				ps.setNull(15, java.sql.Types.BIGINT);
			else
				ps.setLong(15, eventCourseInteractDetails.getOldPosition());
			if(eventCourseInteractDetails.getCurPosition() == null)
				ps.setNull(16, java.sql.Types.BIGINT);
			else
				ps.setLong(16, eventCourseInteractDetails.getCurPosition()); 
			ps.setString(17, eventCourseInteractDetails.getSource());
			ps.execute();
			//Long lastid = eventId.copyLastId("EventCourseInteract");
			//System.out.println("IN eventCourseInteractdao last id " +  lastid);
		    return eventId.copyLastId("EventCourseInteract");
			//return lastid;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			   System.out.println(e.toString());
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

