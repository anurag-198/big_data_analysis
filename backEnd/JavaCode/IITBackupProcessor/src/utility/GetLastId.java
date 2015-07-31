package utility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GetLastId {
		private static Connection conn = null;
		private CallableStatement cs = null;
		private static String simpleProc = "{ call getLastId(?) }";
		ResultSet rs = null;
		
		
		public GetLastId(Connection conn){		
			try{
				this.conn = conn;
			    cs = conn.prepareCall(simpleProc);
			}catch (SQLException e) {             
				e.printStackTrace(); 
				System.out.println("SQL Error in creating LastId " + e.toString());
			}
		}
		public Long copyLastId(String tableName){		
			Long id = -1l;
			try {
				if(cs == null){
					//System.out.println("LASTIS cs is null");
					cs = conn.prepareCall(simpleProc);
				}
				cs.setString(1, tableName);
			    rs = cs.executeQuery();
			    if(rs.next()){
			    	id = rs.getLong(1);
			    }
			    return id;
			} catch (SQLException e) {             
				//e.printStackTrace(); 
				System.out.println("SQL Error in LastId " + e.toString());
				return -1l;
			}
			
		}
}
