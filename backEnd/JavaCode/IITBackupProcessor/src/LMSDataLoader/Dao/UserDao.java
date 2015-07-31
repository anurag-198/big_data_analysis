package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import LMSDataLoader.dataModels.User;

public class UserDao {
	private PreparedStatement psAdd = null, psUpdt = null;
	private Connection conn = null;
	private String sqlStmtAdd = null, sqlStmtUpdt = null;
	public UserDao(Connection conn){
		this.conn = conn;
		try{
			
			/*sqlStmtAdd = "insert into User (lmsUserId ,lmsName ,orgName ,name ,	gender ,birthDate ,	registrationDate ," +
					" emailId ,	mothertounge ,	highestEduDegree ,	goals ,location,	city ,	majorCity , state ," +
					" country ,	timezone ,	active ,	firstAccesDate  ,	 lastAccessDate, isStaff, " + 
					" isSuperUser, address, allowCert,consecutiveVisits, yearOfBirth) " +
					" values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? , ?,?,?,?,?,  ?,?,?,?,?,  ?)";*/
			
			
			sqlStmtAdd = "insert into User (lmsUserId ,lmsName ,orgName ,name ,	gender ,registrationDate ," +
			" emailId ,	mothertounge ,	highestEduDegree ,	goals ,city, state ,active ,"+
			" lastAccessDate, allowCert, yearOfBirth,pincode,aadharId) " +
			" values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? , ?)";
			System.out.println("sqlStmt " + sqlStmtAdd);
			psAdd = conn.prepareStatement(sqlStmtAdd);
			sqlStmtUpdt = "update User set gender = ?,mothertounge	= ?, highestEduDegree = ?, goals = ?, location = ?," +
					" city = ?, country = ?, address = ?, yearOfBirth =?, allowCert= ?   where lmsUserId = ? ";
			System.out.println("sqlStmtUpdt " + sqlStmtUpdt);
			psUpdt = conn.prepareStatement(sqlStmtUpdt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	public Boolean insertRec(User user) {
	
		try {
			 if(psAdd == null){
				 System.out.println("UserDao : ps is null in insert");
				 psAdd = conn.prepareStatement(sqlStmtAdd);
			 }

		/*		System.out.println("user.getLmsName() " + user.getLmsName());
				System.out.println("user.getOrgName() " + user.getOrgName()); 
				System.out.println("user.getCourseName() " +  user.getCourseName()); 
				System.out.println("user.getCourseTitle() " + user.getCourseTitle());
				System.out.println("user.getAuthorUserId() " + user.getAuthorUserId()); 
				System.out.println("user.getCurrConcepts() " + user.getCurrConcepts()); 
				System.out.println("user.getPrevConcepts() " + user.getPrevConcepts()); 
				//System.out.println("user.getCreationDate() " + user.getCreationDate()); 
				//System.out.println("user.getLastModifiedDate() " + user.getLastModifiedDate()); 
				System.out.println("user.getLanguage() " + user.getLanguage()); 
				System.out.println("user.getMinPrice() " + user.getMinPrice()); 
				System.out.println("user.getSuggestedPrices() " + user.getSuggestedPrices()); 
				System.out.println("user.getCurrencyCode() " + user.getCurrencyCode()); 
				System.out.println("user.getEndDate() " + user.getEndDate()); 
				System.out.println("courseData  .getStartDate() " + courseData  .getStartDate());
			*/
			
			psAdd.setLong(1, user.getLmsUserId());
			psAdd.setString(2, user.getLmsName());
			psAdd.setString(3, user.getOrgName()); 
			psAdd.setString(4, user.getName()); 
			psAdd.setString(5, user.getGender());
			//if(user.getBirthDate() == null)
				//psAdd.setNull(6, java.sql.Types.DATE);
			//else
				//psAdd.setDate(6, new java.sql.Date(user.getBirthDate().getTime())); 
			psAdd.setDate(6, new java.sql.Date(user.getRegistrationDate().getTime()));
			psAdd.setString(7, user.getEmailId()); 
			psAdd.setString(8, user.getMothertounge()); 
			psAdd.setString(9, user.getHighestEduDegree());
			psAdd.setString(10, user.getGoals());
			//psAdd.setString(12, user.getLocation());
			psAdd.setString(11, user.getCity()); 
			//psAdd.setString(14, user.getMajorCity());
			psAdd.setString(12, user.getState());
			//psAdd.setString(13, user.getCountry()); 
			//psAdd.setString(17, user.getTimezone()); 
			psAdd.setShort(13, user.getActive());
		/*	if(user.getFirstAccesDate() == null)
				psAdd.setNull(14, java.sql.Types.DATE);
			else
				psAdd.setDate(14,  new java.sql.Date(user.getFirstAccesDate().getTime()));*/ 
			if(user.getLastAccessDate() == null)
				psAdd.setNull(14, java.sql.Types.DATE);
			else
				psAdd.setDate(14, new java.sql.Date(user.getLastAccessDate().getTime()));
			//psAdd.setString(21, user.getCourseName());
			//psAdd.setString(21, user.getIsStaff());
			//psAdd.setString(22, user.getIsSuperUser());
			//psAdd.setString(23, user.getAddress());
		//	psAdd.setString(16, user.getUserFlag());
			psAdd.setShort(15, user.getAllowCert());
			//psAdd.setInt(25, user.getConsecutiveVisits());
			if(user.getYearOfBirth() == null)
				psAdd.setNull(16, java.sql.Types.INTEGER);
			else
				psAdd.setInt(16, user.getYearOfBirth());
			if(user.getPincode() == null)
				psAdd.setNull(17, java.sql.Types.INTEGER);
			else
				psAdd.setInt(17, user.getPincode());
			if(user.getAadharId() == null)
				psAdd.setNull(18, java.sql.Types.INTEGER);
			else
				psAdd.setInt(18, user.getAadharId());
			return psAdd.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in User Insert " + e.toString());
			return false;
		}
	}	
	public Boolean UpdateRec(User user, Long lmsUserId) {
		
		try {
			 if(psUpdt == null){
				 System.out.println("UserDao : ps is null in update");
				 psUpdt = conn.prepareStatement(sqlStmtUpdt);
			 }
			 
				psUpdt.setString(1, user.getGender());
				psUpdt.setString(2, user.getMothertounge());
				psUpdt.setString(3, user.getHighestEduDegree());
				psUpdt.setString(4, user.getGoals());
			//	psUpdt.setString(5, user.getLocation());	
				psUpdt.setString(6, user.getCity());
			//	psUpdt.setString(7, user.getCountry());
				//psUpdt.setString(8, user.getAddress());
				
				if(user.getYearOfBirth() != null)
					psUpdt.setInt(9, user.getYearOfBirth());
				else
					psUpdt.setNull(9, java.sql.Types.INTEGER);
				psUpdt.setShort(10, user.getAllowCert());
				psUpdt.setLong(11, lmsUserId);

				return psUpdt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in User Update " + e.toString());
			return false;
		}
	}	
	public int closeAll(){
		try {
			if(psAdd != null){
				psAdd.close();
			}
			if(psUpdt != null){
				psUpdt.close();
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

