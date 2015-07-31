package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import utility.GetLastId;
import LMSDataLoader.dataModels.EventEnrollment;


public class EventEnrollmentDao {
	private static PreparedStatement ps = null;
	private static Connection conn = null;
	private static String sqlStmt = null;
	public EventEnrollmentDao(Connection conn){
		try{
			
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/LMSDb");
			conn = ds.getConnection();
			
			sqlStmt = "insert into EventEnrollment ( lmsName, orgName, courseName, eventName," +
					" lmsUserId,  userName, gender, eduLevel, activate, adminUserId, accessDateTime ) values (?,?,?,?,?,  ?,?,?,?,?,  ?)";
			//System.out.println("sqlStmt " + sqlStmt);
			//System.out.println("after insert lavita");
			ps = conn.prepareStatement(sqlStmt);
			// get lastId
			//return 0;
		} catch (NamingException ne) {             
			System.out.println("ERror in Naming " + ne.toString());
			ne.printStackTrace();
			//return -1;
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
			//return -1;
		}
	}
	public long insertRec(EventEnrollment eventEnrollmentDetails,GetLastId eventId) {
	
		try {
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
			 System.out.println("inside insert recore labansjna");
			ps.setString(1, eventEnrollmentDetails.getLmsName());
			ps.setString(2, eventEnrollmentDetails.getOrgName()); 
			ps.setString(3, eventEnrollmentDetails.getCourseName()); 
			ps.setString(4, eventEnrollmentDetails.getEventName());
			ps.setLong(5, eventEnrollmentDetails.getLmsUserId()); 
			ps.setString(6, eventEnrollmentDetails.getUserName());
			ps.setString(7, eventEnrollmentDetails.getGender());
			ps.setString(8, eventEnrollmentDetails.getEduLevel());
			ps.setString(9, eventEnrollmentDetails.getActivate());

			if(eventEnrollmentDetails.getAdminUserId() == null)
				ps.setNull(10, java.sql.Types.BIGINT);
			else
				ps.setLong(10, eventEnrollmentDetails.getAdminUserId()); 
			ps.setDate(11, new java.sql.Date(eventEnrollmentDetails.getAccessDateTime().getTime()));
			ps.execute();
		    return eventId.copyLastId("EventEnrollment");
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

