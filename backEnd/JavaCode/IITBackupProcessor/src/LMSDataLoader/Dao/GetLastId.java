package LMSDataLoader.Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetLastId {
		private static Connection conn = null;
		private static CallableStatement cs = null;
		ResultSet rs = null;
		Integer id=0;
		public GetLastId(Connection conn){		
				this.conn = conn;
				try {
					String simpleProc = "{ call getLastId(?) }";
				    cs = conn.prepareCall(simpleProc);
				} catch (SQLException e) {             
					e.printStackTrace(); 
					System.out.println("SQL Error in LastId " + e.toString());
				}
			}
		public Long copyLastId(String tableName){	
			
			Long id = -1l;
			try {
				String simpleProc = "{ call getLastId(?) }";
			//    cs = conn.prepareCall(simpleProc);
				System.out.println("getLASTID COPY LastId cs " + cs);
				cs.setString(1, tableName);
			    rs = cs.executeQuery();
			    if(rs.next()){
			    	id = rs.getLong(1);
			    }
			  //  cs.close();
			    //rs.close();
			    return id;
			} catch (SQLException e) {             
				e.printStackTrace(); 
				System.out.println("SQL Error in LastId " + e.toString());
				return -1l;
			}
			
		}
}
