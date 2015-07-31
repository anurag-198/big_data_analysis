package LMSDataLoader.Dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import LMSDataLoader.dataModels.UserSessionOld;

public class UserSessionOldDao {
	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public UserSessionOldDao(Connection conn){
				try{
			sqlStmt = "insert into UserSessionOld (sessionSysName, lmsName, orgName, courseName,courseRun, lmsUserId, userName," + 
					"agent,	hostName, ipAddress, createDateTime, eventType, eventSource, eventName," + 
					" dataSource, oldVideoSpeed, currVideoSpeed, oldVideoTime, currVideoTime,videoNavigType," +
					" oldGrade, currGrade, maxGrade, attempts, maxNoAttempts, hintAvailable,hintUsed, currPosition,oldPosition, "+
					" chapterSysName, chapterTitle, sessSysName, sessTitle, moduleSysName, moduleTitle,  " +
					//" chapterTitle,sessTitle, moduleTitle, moduleType, " +
					" answerChoice, success, done, enrolmentMode, totDurationInSecs, eventNo,otherTitle, otherType, anonymous,anonymousToPeers,"+
					"eduLevel, gender,commentableId,commentType, commentSysId,aadhar,problemSubmissionTime, hintMode,currentSeekTime,queryText,noOfResults,lastModDateTime)" +
					" values (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,   ?,?,?,?,?,   ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?, ?,?,?,?,?, ?,?)";
			System.out.println("sqlStmt " + sqlStmt);
			ps = conn.prepareStatement(sqlStmt);
			this.conn = conn;
			//return 0;
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
			//return -1;
		}
	}
	public Boolean insertRec(UserSessionOld userSession) throws IOException {
	
		try {
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
		
			if(userSession.getSessionSysName() == null)
				ps.setNull(1, Types.VARCHAR);
			else
				ps.setString(1, userSession.getSessionSysName());
			ps.setString(2, userSession.getLmsName());
			ps.setString(3, userSession.getOrgName()); 
			ps.setString(4, userSession.getCourseName()); 
			ps.setString(5, userSession.getCourseRun());
			if(userSession.getLmsUserId() != null)
				ps.setLong(6, userSession.getLmsUserId());
			else
				ps.setNull(6, Types.BIGINT);
			if(userSession.getUserName() == null)
				ps.setNull(7, Types.VARCHAR);
			else
				ps.setString(7, userSession.getUserName());
			if(userSession.getAgent() == null)
				ps.setNull(8, Types.VARCHAR);
			else
				ps.setString(8, userSession.getAgent());
			if(userSession.getHostName() == null)
				ps.setNull(9, Types.VARCHAR);
			else
				ps.setString(9, userSession.getHostName());
			if(userSession.getIpAddress() == null)
				ps.setNull(10, Types.VARCHAR);
			else
				ps.setString(10, userSession.getIpAddress());
		/*	if(userSession.getUrl() == null)
				ps.setNull(11, Types.VARCHAR);
			else
				ps.setString(11, userSession.getUrl());
			*/ps.setTimestamp(11, userSession.getCreateDateTime());
			ps.setString(12, userSession.getEventType());
			if(userSession.getEventSource() == null)
				ps.setNull(13, Types.VARCHAR);
			else
				ps.setString(13, userSession.getEventSource());
			if(userSession.getEventName() == null)
				ps.setNull(14, Types.VARCHAR);
			else
				ps.setString(14, userSession.getEventName());
			//ps.setTimestamp(16, userSession.getlastModDateTime());
			
			ps.setString(15, userSession.getDataSource());
			
			if(userSession.getOldVideoSpeed() == null)
				ps.setNull(16, Types.FLOAT);
			else
				ps.setFloat(16, userSession.getOldVideoSpeed());
			
			if(userSession.getCurrVideoSpeed() == null)
				ps.setNull(17, Types.FLOAT);
			else
				ps.setFloat(17, userSession.getCurrVideoSpeed());
			if(userSession.getOldVideoTime() == null)
				ps.setNull(18, Types.FLOAT);
			else
				ps.setFloat(18, userSession.getOldVideoTime());
			if(userSession.getCurrVideoTime() == null)
				ps.setNull(19, Types.FLOAT);
			else
				ps.setFloat(19, userSession.getCurrVideoTime());
			if(userSession.getVideoNavigType() == null)
				ps.setNull(20, Types.VARCHAR);
			else
				ps.setString(20, userSession.getVideoNavigType());
			if(userSession.getOldGrade() == null)
				ps.setNull(21, Types.FLOAT);
			else
				ps.setFloat(21, userSession.getOldGrade());
			if(userSession.getCurrGrade() == null)
				ps.setNull(22, Types.FLOAT);
			else
				ps.setFloat(22, userSession.getCurrGrade());
			if(userSession.getMaxGrade() == null)
				ps.setNull(23, Types.FLOAT);
			else
				ps.setFloat(23, userSession.getMaxGrade());
			if(userSession.getAttempts() == null)
				ps.setNull(24, Types.INTEGER);
			else
				ps.setInt(24, userSession.getAttempts());
			if(userSession.getMaxNoAttempts() == null)
				ps.setNull(25, Types.INTEGER);
			else
				ps.setInt(25, userSession.getMaxNoAttempts());
			if(userSession.getHintAvailable() == null)
				ps.setNull(26, Types.VARCHAR);
			else
				ps.setString(26, userSession.getHintAvailable());
			if(userSession.getHintUsed() == null)
				ps.setNull(27, Types.VARCHAR);
			else
				ps.setString(27, userSession.getHintUsed());
			if(userSession.getCurrPosition() == null)
				ps.setNull(28, Types.INTEGER);
			else
				ps.setInt(28, userSession.getCurrPosition());
			if(userSession.getOldPosition() == null)
				ps.setNull(29, Types.INTEGER);
			else
				ps.setInt(29, userSession.getOldPosition());
			if(userSession.getChapterSysName() == null)
				ps.setNull(30, Types.VARCHAR);
			else
				ps.setString(30, userSession.getChapterSysName());
			if(userSession.getChapterTitle() == null)
				ps.setNull(31, Types.VARCHAR);
			else
				ps.setString(31, userSession.getChapterTitle());
			if(userSession.getSessSysName() == null)
				ps.setNull(32, Types.VARCHAR);
			else
				ps.setString(32, userSession.getSessSysName());
			if(userSession.getSessTitle() == null)
				ps.setNull(33, Types.VARCHAR);
			else
				ps.setString(33, userSession.getSessTitle());
			if(userSession.getModuleSysName() == null)
				ps.setNull(34, Types.VARCHAR);
			else
				ps.setString(34, userSession.getModuleSysName());
			if(userSession.getModuleTitle() == null)
				ps.setNull(35, Types.VARCHAR);
			else
				ps.setString(35, userSession.getModuleTitle());
			/*if(userSession.getModuleType() == null)
				ps.setNull(34, Types.VARCHAR);
			else
				ps.setString(34, userSession.getModuleType());
			*/if(userSession.getAnswerChoice() == null)
				ps.setNull(36, Types.VARCHAR);
			else
				ps.setString(36, userSession.getAnswerChoice());
			if(userSession.getSuccess() == null)
				ps.setNull(37, Types.VARCHAR);
			else
				ps.setString(37, userSession.getSuccess());
			if(userSession.getDone() == null)
				ps.setNull(38, Types.VARCHAR);
			else
				ps.setString(38, userSession.getDone());
			if(userSession.getEnrolmentMode() == null)
				ps.setNull(39, Types.VARCHAR);
			else
				ps.setString(39, userSession.getEnrolmentMode());
			if(userSession.getTotDurationInSecs() == null)
				ps.setNull(40, Types.INTEGER);
			else
				ps.setInt(40, userSession.getTotDurationInSecs());
			if(userSession.getEventNo() == null)
				ps.setNull(41, Types.SMALLINT);
			else
				ps.setShort(41, userSession.getEventNo());
			
			if(userSession.getOtherTitle() == null)
				ps.setNull(42, Types.VARCHAR);
			else
				ps.setString(42, userSession.getOtherTitle());
			
			if(userSession.getOtherType() == null)
				ps.setNull(43, Types.VARCHAR);
			else
				ps.setString(43, userSession.getOtherType());
			
			if(userSession.getAnonymous() == null)
				ps.setNull(44, Types.VARCHAR);
			else
				ps.setString(44, userSession.getAnonymous());
			
			if(userSession.getAnonymousToPeers() == null)
				ps.setNull(45, Types.VARCHAR);
			else
				ps.setString(45, userSession.getAnonymousToPeers());
			
			if(userSession.getEduLevel() == null)
				ps.setNull(46, Types.VARCHAR);
			else
				ps.setString(46, userSession.getEduLevel());
			
			if(userSession.getGender() == null)
				ps.setNull(47, Types.VARCHAR);
			else
				ps.setString(47, userSession.getGender());
			
			if(userSession.getCommentableId() == null)
				ps.setNull(48, Types.VARCHAR);
			else
				ps.setString(48, userSession.getCommentableId());
			if(userSession.getCommentType() == null)
				ps.setNull(49, Types.VARCHAR);
			else
				ps.setString(49, userSession.getCommentType());
			if(userSession.getCommentSysId() == null)
				ps.setNull(50, Types.VARCHAR);
			else
				ps.setString(50, userSession.getCommentSysId());
			
			if(userSession.getAadhar() == null)
				ps.setNull(51, Types.VARCHAR);
			else
				ps.setString(51, userSession.getAadhar());
			
				ps.setTimestamp(52, userSession.getProbSubmissionTime());
			
				if(userSession.getHintMode() == null)
					ps.setNull(53, Types.FLOAT);
				else
					ps.setString(53, userSession.getHintMode());
				
				if(userSession.getCurrentSeekTime() == null)
					ps.setNull(54, Types.FLOAT);
				else
					ps.setFloat(54, userSession.getCurrentSeekTime());
				
				if(userSession.getQueryText() == null)
					ps.setNull(55, Types.VARCHAR);
				else
					ps.setString(55, userSession.getQueryText());
				
				if(userSession.getNoOfResults() == null)
					ps.setNull(56, Types.INTEGER);
				else
					ps.setInt(56, userSession.getNoOfResults());
				
				
					ps.setTimestamp(57, userSession.getLastModDateTime());
				
				if(userSession.getAadhar() == null)
					ps.setNull(51, Types.VARCHAR);
				else
					ps.setString(51, userSession.getAadhar());
				
			//System.out.println(ps.toString() + "LLLLLLLLL");	
						
				
				
			return ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("SQL ERROR IN INSERT REC userSession " + e.toString());
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
	