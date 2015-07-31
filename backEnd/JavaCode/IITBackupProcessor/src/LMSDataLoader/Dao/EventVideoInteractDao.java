package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;

import utility.GetLastId;
import utility.UtilDateSql;
import LMSDataLoader.dataModels.EventVideoInteract;

public class EventVideoInteractDao {
	//private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;

	public EventVideoInteractDao(Connection conn) {
		//try {
			this.conn = conn;
			
			
			sqlStmt = "insert into EventVideoInteract( sessionSysName, lmsName, orgName, courseName, courseRun, lmsUserId,"
					+ "eventName,eventNo, videoSysName, videoTitle, chapterSysName,chapterTitle, oldSeekTime, currSeekTime, videoNavigType, "
					+ " oldSpeed, currSpeed, source,createDateTime, lastModDateTime) "
					+ " values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)";
			System.out.println("sqlStmt " + sqlStmt);
		/*	ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}*/
	}

	// public Boolean insertRec(EventVideoInteract videoInteractDetails) {
	public Long insertRec(EventVideoInteract videoInteractDetails,GetLastId getLastId, PreparedStatement ps) {
		try {
			//if (ps == null)
				//ps = conn.prepareStatement(sqlStmt);
			
			ps.setString(1, videoInteractDetails.getSessionSysName());
			ps.setString(2, videoInteractDetails.getLmsName());
			ps.setString(3, videoInteractDetails.getOrgName());
			ps.setString(4, videoInteractDetails.getCourseName());
			ps.setString(5, videoInteractDetails.getCourseRun());
			ps.setLong(6, videoInteractDetails.getLmsUserId());
			ps.setString(7, videoInteractDetails.getEventName());
			if(videoInteractDetails.getEventNo() == null)
				ps.setNull(8, Types.SMALLINT);
			else
				ps.setShort(8, videoInteractDetails.getEventNo());
			ps.setString(9, videoInteractDetails.getVideoSysName());
			ps.setString(10, videoInteractDetails.getVideoTitle());
			ps.setString(11, videoInteractDetails.getChapterSysName());
			ps.setString(12, videoInteractDetails.getChapterTitle());
			if (videoInteractDetails.getOldSeekTime() == null)
				ps.setNull(13, Types.FLOAT);
			else
				ps.setFloat(13, videoInteractDetails.getOldSeekTime());
			if (videoInteractDetails.getCurrSeekTime() == null)
				ps.setNull(14, Types.FLOAT);
			else
				ps.setFloat(14, videoInteractDetails.getCurrSeekTime());
			ps.setString(15, videoInteractDetails.getVideoNavigType());
			if (videoInteractDetails.getOldSpeed() == null)
				ps.setNull(16, Types.FLOAT);
			else
				ps.setFloat(16, videoInteractDetails.getOldSpeed());
			if (videoInteractDetails.getCurrSpeed() == null)
				ps.setNull(17, Types.FLOAT);
			else
				ps.setFloat(17, videoInteractDetails.getCurrSpeed());
			ps.setString(18, videoInteractDetails.getSource());
	/*		if (videoInteractDetails.getVideoPosition() == null)
				ps.setNull(15, Types.TIME);
			else
				ps.setTime(15, new java.sql.Time(videoInteractDetails
						.getVideoPosition().getTime()));*/
			
				ps.setTimestamp(19, videoInteractDetails.getCreateDateTime());
				ps.setTimestamp(20, videoInteractDetails.getLastModDateTime());
				ps.execute();
				return getLastId.copyLastId("EventVideoInteract");
			//return -1L;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1l;
		}
	}

	/*public int closeAll() {
		try {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
				return 0;
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}*/
}
