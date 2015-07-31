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

import LMSDataLoader.dataModels.tmpProblem;

public class tmpProblemDao {
	private static PreparedStatement ps = null;
	private static Connection conn = null;
	private static String sqlStmt = null;
	public tmpProblemDao(Connection conn){
		sqlStmt = "insert into tmpProblem (state, startDateTime,endDateTime,lmsUserId, score, modSysName)" + 
				" values (?,?,?,?,?,?)";
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
	public Boolean insertRec(tmpProblem tmpProblemData) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);

		/*		System.out.println("tmpProblemData.getLmsName() " + tmpProblemData.getLmsName());
				System.out.println("tmpProblemData.getOrgName() " + tmpProblemData.getOrgName()); 
				System.out.println("tmpProblemData.gettmpProblemName() " +  tmpProblemData.gettmpProblemName()); 
				System.out.println("tmpProblemData.gettmpProblemTitle() " + tmpProblemData.gettmpProblemTitle());
				System.out.println("tmpProblemData.getAuthorUserId() " + tmpProblemData.getAuthorUserId()); 
				System.out.println("tmpProblemData.getCurrConcepts() " + tmpProblemData.getCurrConcepts()); 
				System.out.println("tmpProblemData.getPrevConcepts() " + tmpProblemData.getPrevConcepts()); 
				//System.out.println("tmpProblemData.getCreationDate() " + tmpProblemData.getCreationDate()); 
				//System.out.println("tmpProblemData.getLastModifiedDate() " + tmpProblemData.getLastModifiedDate()); 
				System.out.println("tmpProblemData.getLanguage() " + tmpProblemData.getLanguage()); 
				System.out.println("tmpProblemData.getMinPrice() " + tmpProblemData.getMinPrice()); 
				System.out.println("tmpProblemData.getSuggestedPrices() " + tmpProblemData.getSuggestedPrices()); 
				System.out.println("tmpProblemData.getCurrencyCode() " + tmpProblemData.getCurrencyCode()); 
				System.out.println("tmpProblemData.getEndDate() " + tmpProblemData.getEndDate()); 
				System.out.println("tmpProblemData  .getStartDate() " + tmpProblemData  .getStartDate());
				return true;*/

			ps.setString(1, tmpProblemData.getState());
			Timestamp ts = new Timestamp(tmpProblemData.getStartDateTime().getTime());
			ps.setTimestamp(2, ts); 
			ts = new Timestamp(tmpProblemData.getEndDateTime().getTime());
			ps.setTimestamp(3, ts); 
			ps.setLong(4, tmpProblemData.getLmsUserId());
			if(tmpProblemData.getScore() == null)
				ps.setNull(5, Types.INTEGER);
			else
				ps.setInt(5, tmpProblemData.getScore());
			ps.setString(6, tmpProblemData.getModSysName());
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
	public void updateRec(String[] colNames, String[] colValues, Long id) {		
		try {
			sqlStmt = "update tmpProblem ";
			for(int i=0; i < colNames.length; i++){
				if (i ==0)
					sqlStmt += " set " + colNames[i] + " = " + colValues[i];
				else
					sqlStmt += ", set " + colNames[i] + " = " + colValues[i];
			}
			sqlStmt += " where tmpProblemId = " + String.valueOf(id);
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

