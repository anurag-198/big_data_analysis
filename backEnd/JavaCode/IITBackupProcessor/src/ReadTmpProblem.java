import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import LMSDataLoader.Dao.TmpTimeDao;
import LMSDataLoader.dataModels.TmpTime;

@WebServlet("/ReadTmpProblem")
public class ReadTmpProblem extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4635509202984233484L;
	private TmpTime tmpTime = null;
	private TmpTimeDao tmpTimeDao = null;
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		  doGet(request, response);
		}
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		PreparedStatement ps = null;
		String sqlStmt = "select  state, startDateTime,endDateTime,lmsUserId, score from tmpProblem " + 
				" where lmsUserId= ? order by lmsUserId, startDateTime";
		//System.out.println("parameter lmsUserID " + (String)request.getParameter("lmsUserId"));
		
		JSONParser jparse = new JSONParser();
		JSONObject jState = null;
		String state, prvState=null;
		Timestamp startTStmp, prvStartTStmp=null;
		Timestamp endTStmp, prvEndTStmp=null;
		Integer score, prvScore = null;
		int dupRec = 0;
		Long lmsUserId, prvLmsUserId =null;
		lmsUserId = Long.parseLong((String)request.getParameter("lmsUserId"));
		Object objDone=null, prvObjDone = null;
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/LMSDb");
			Connection conn = ds.getConnection();
			tmpTimeDao = new TmpTimeDao(conn);
			ps = conn.prepareStatement(sqlStmt);
			ps.setLong(1,lmsUserId);
			ResultSet rs = ps.executeQuery();
			Byte flags;
			while (rs.next()){
				state = rs.getString(1);
				//System.out.println("STAET " + state);
				startTStmp = rs.getTimestamp(2);
				endTStmp = rs.getTimestamp(3);
				lmsUserId = rs.getLong(4);
				score = rs.getInt(5);
				if(prvState != null){
				
					System.out.println("(startTStmp.getTime() == prvStartTStmp.getTime()) " + (startTStmp.getTime() == prvStartTStmp.getTime()));
					System.out.println("(state.equals(prvState) " + (state.equals(prvState)));
					System.out.println("(endTStmp.getTime() == prvEndTStmp.getTime()) " + (endTStmp.getTime() == prvEndTStmp.getTime()));
					System.out.println("(score == prvScore) " + (score == prvScore));
					System.out.println("(lmsUserId == prvLmsUserId)  " + (lmsUserId == prvLmsUserId) );
					//System.out.println("(lmsUserId == prvLmsUserId)  " + (lmsUserId == prvLmsUserId) );
				}
				if((prvState != null) && (startTStmp.getTime() == prvStartTStmp.getTime()) &&
							(state.equals(prvState)) &&  (endTStmp.getTime() == prvEndTStmp.getTime())
							&& (score == prvScore) && (lmsUserId.equals(prvLmsUserId) ) ) {
						System.out.println("DUplicate Record  Skipping ");
						dupRec++;
					
				}
				else {
					prvState = state;
					prvStartTStmp = startTStmp;
					prvEndTStmp = endTStmp;
					prvLmsUserId = lmsUserId;
					prvScore = score;
					try {
						jState = (JSONObject) jparse.parse(state);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objDone = jState.get("done");
					
					Iterator<?> itr = jState.keySet().iterator();
					//System.out.print("Keys  are \n");
					while(itr.hasNext()){
						Object objKey = itr.next();
						System.out.print(objKey.toString() + " , ");
						//System.out.print("\n");
						//System.out.print( "Values are " + jState.get(objKey));
					}
					// Get only done here, if done is true, get start & end time , calculate total time 
					// Get Score , get attempts all will come in the first key arrays
					// correctmap is not null if done is not null & then score comes automatically
					// attempts key 
					//Timestamp t1 = new Timestamp("2010-10-10 10:10:00")
					
					System.out.println("objDone " + objDone);
					if(objDone != null){
						// the problems are attempted, get all values
						long totTimeTkn = endTStmp.getTime() - startTStmp.getTime(); // in milisecs
						int allSecs = (int)(totTimeTkn/1000l);
						System.out.println("totTimeTkn " + totTimeTkn + " secs " + allSecs);
						int mins = (int)(allSecs/60);
						int hrs = (int)(mins /60);
						int secs = allSecs % 60;
						System.out.println(" secs " + secs + " mins " + mins + " hrs " + hrs);
						mins = mins % 60;
						int dys = (int)(hrs/24);
						hrs = hrs % 24;
						System.out.println(" mins " + mins + " hrs " + hrs + " dys " + dys);
						String sTemp = String.format("%3d %s %2d %s %2d %s %2d %s", dys, "Dys ", hrs , " Hrs" , mins , "Mins", secs , "Secs" );
						System.out.println("sRemp " + sTemp);
						tmpTime = new TmpTime();
						tmpTime.setStartDateTime(new java.util.Date(startTStmp.getTime()));
						tmpTime.setEndDateTime(new java.util.Date(endTStmp.getTime()));
						tmpTime.setTotTimeSpent(allSecs);
						tmpTime.setStrTimeSpent(sTemp);
						tmpTimeDao.insertRec(tmpTime);
						//Timestamp totTimeTkn = endTStmp - startTStmp;
					}
					else {
						// problems are not done, get time taken or 
					}
					System.out.print("\n");
					String inputState = jState.get("input_state").toString();
					//System.out.println("inputState " + inputState);
					Object obj = jState.get("correct_map");
					System.out.println("done  " + jState.get("done"));
					if(obj == null)
						System.out.println("correctMap is null");
					else{
						String correctMap = jState.get("correct_map").toString();
						System.out.println("correctMap " + correctMap);
					}
				}
			}
				
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total DUPLIACTE RECORDS " + dupRec);
	}
}
