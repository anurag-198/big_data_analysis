package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import LMSDataLoader.dataModels.CourseVideos;

public class CourseVideosDao {
	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;
	public CourseVideosDao(Connection conn){
		this.conn = conn;
		sqlStmt = "insert into CourseVideos (lmsName,orgName,courseName,chapterSysName, " +
				//"sessionSysName,videoSysName,videoUTubeId,videoPath," + 
				"videoSysName,videoUTubeId," +
				//" videoDownload, videoTrackDownLoad,videoSubTitle,videoTitle,videoUTubeId075,videoUTubeId125,videoUTubeId15) " +
				" videoDownload, videoTrackDownLoad,videoTitle,videoUTubeId075,videoUTubeId125,videoUTubeId15) " +
				" values (?,?,?,?,?, ?,?,?,?,?, ?,?)";
				System.out.println("sqlStmt " + sqlStmt);
		try{
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	}
	//public Long insertRec(courseVideo courseVideo, GetLastId getLastid) {
	public Boolean insertRec(CourseVideos courseVideo) {
	
		try {
			if(conn == null)
				System.out.println("CONN IS NULL");
			else
				System.out.println("CONN IS NOTT NULL");
			 if(ps == null)
				 ps = conn.prepareStatement(sqlStmt);
/*
		
			System.out.println("courseVideo.getLmsName() " + courseVideo.getLmsName());
			System.out.println("courseVideo.getOrgName() " +  courseVideo.getOrgName()); 
			System.out.println("courseVideo.getCourseName() " +  courseVideo.getCourseName()); 
			System.out.println("courseVideo.getVideoSysName() " +  courseVideo.getVideoSysName());
			System.out.println("courseVideo.getVideoUTubeId() " +  courseVideo.getVideoUTubeId());
			System.out.println("courseVideo.getVideoPath() " +  courseVideo.getVideoPath());
			System.out.println("courseVideo.getVideoDownload() " +  courseVideo.getVideoDownload());
			System.out.println("courseVideo.getVideoTrackDownLoad() " +  courseVideo.getVideoTrackDownLoad());
			System.out.println("courseVideo.getVideoSubTitle() " + courseVideo.getVideoSubTitle());
			System.out.println(" courseVideo.getVideoTitle() " +  courseVideo.getVideoTitle());
			System.out.println("courseVideo.getVideoUTubeId075() " + courseVideo.getVideoUTubeId075());
			System.out.println("courseVideo.getVideoUTubeId125() " + courseVideo.getVideoUTubeId125());
			System.out.println(" courseVideo.getVideoUTubeId15() " +  courseVideo.getVideoUTubeId15());
	*/	
                        ps.setString(1, courseVideo.getLmsName());
                        ps.setString(2, courseVideo.getOrgName());
                        ps.setString(3, courseVideo.getCourseName());
                        ps.setString(4, courseVideo.getChapterSysName());
                     //   ps.setString(5, courseVideo.getSessionSysName());
                        ps.setString(5, courseVideo.getVideoSysName());
                        if(courseVideo.getVideoUTubeId() == null)
                        	ps.setNull(6, Types.VARCHAR);
                        else
                        	ps.setString(6, courseVideo.getVideoUTubeId());
                     //   ps.setString(8, courseVideo.getVideoPath());
                        ps.setInt(7, courseVideo.getVideoDownload());
                        if(courseVideo.getVideoTrackDownLoad() != null)
                        	ps.setInt(8, courseVideo.getVideoTrackDownLoad());
                        else
                        	ps.setNull(8, Types.INTEGER);
                     //   ps.setString(11, courseVideo.getVideoSubTitle());
                        ps.setString(9, courseVideo.getVideoTitle());
                        if(courseVideo.getVideoUTubeId075() != null)
                        	ps.setString(10, courseVideo.getVideoUTubeId075());
                        else
                        	ps.setNull(10, Types.VARCHAR);
                        if(courseVideo.getVideoUTubeId125() != null)
                        	ps.setString(11, courseVideo.getVideoUTubeId125());
                        else
                        	ps.setNull(11, Types.VARCHAR);
                        if(courseVideo.getVideoUTubeId15() != null)
                        	ps.setString(12, courseVideo.getVideoUTubeId15());
                        else
                        	ps.setNull(12, Types.VARCHAR);

			return(ps.execute());
			//return getLastid.copyLastId("Course");
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
