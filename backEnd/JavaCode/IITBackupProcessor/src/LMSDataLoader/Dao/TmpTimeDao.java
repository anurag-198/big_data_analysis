package LMSDataLoader.Dao;

import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import LMSDataLoader.dataModels.TmpTime;
import LMSDataLoader.dataModels.tmpProblem;

public class TmpTimeDao {
	private static PreparedStatement ps = null;
	private static Connection conn = null;
	private static String sqlStmt = null;
	public TmpTimeDao(Connection conn){
		sqlStmt = "insert into tmpTime (startDateTime,endDateTime, totTimeSpent, strTimeSpent) values (?,?, ?, ?)";
				System.out.println("sqlStmt " + sqlStmt);
		this.conn = conn;
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	//public Long insertRec(tmpProblemData tmpProblemData, GetLastId getLastid) {
	public Boolean insertRec(TmpTime tmpProblemData) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);

				System.out.println("tmpProblemData.getStartDateTime() " + tmpProblemData.getStartDateTime());
				System.out.println("tmpProblemData.getEndDateTime() " + tmpProblemData.getEndDateTime());
				System.out.println("tmpProblemData.getTotTimeSpent() " + tmpProblemData.getTotTimeSpent()); 
				
				Timestamp ts = new Timestamp(tmpProblemData.getStartDateTime().getTime());
				ps.setTimestamp(1, ts);
				ts = new Timestamp(tmpProblemData.getEndDateTime().getTime());
				ps.setTimestamp(2, ts);
				ps.setInt(3, tmpProblemData.getTotTimeSpent());
				ps.setString(4, tmpProblemData.getStrTimeSpent());
			return(ps.execute());
			//return getLastid.copyLastId("tmpProblem");
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

