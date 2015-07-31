package LMSDataLoader.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import LMSDataLoader.dataModels.CourseForums;

public class CourseForumsDao {

	private static PreparedStatement ps = null;
	private Connection conn = null;
	private static String sqlStmt = null;

	public CourseForumsDao(Connection conn) {
		sqlStmt = "INSERT INTO CourseForums (lmsName, orgName,	courseName, courseRun, commentSysId," +
				"commentType, anonymousMode, lmsAuthorId, lmsAuthorName, createDateTime, lastModDateTime, upVoteCount," +
				" totVoteCount, commentCount, threadType, title, commentableSysId,	endorsed, closed, visible)" +
				" VALUES (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?  ,?,?,?,?,?);";
		System.out.println("sqlStmt " + sqlStmt);
		this.conn = conn;
		try {
			ps = conn.prepareStatement(sqlStmt);
		} catch (SQLException e) {
			System.out.println("Error in SQL " + e.toString());
			e.printStackTrace();
		}
	}

	public Boolean insertRec(CourseForums courseForums) {

		try {
			
			if (ps == null)
				ps = conn.prepareStatement(sqlStmt);

			ps.setString(1, courseForums.getLmsName());
			ps.setString(2, courseForums.getOrgName());
			ps.setString(3, courseForums.getCourseName());
			ps.setString(4, courseForums.getCourseRun());
			ps.setString(5, courseForums.getCommentSysId());
			ps.setString(6, courseForums.getCommentType());
			ps.setString(7, courseForums.getAnonymousMode());
			ps.setLong(8, courseForums.getLmsAuthorId());
			ps.setString(9, courseForums.getLmsAuthorName());
			ps.setDate(10, new java.sql.Date(courseForums.getCreateDateTime().getTime()));
			System.out.println("courseForums.getLastModDateTime() " + courseForums.getLastModDateTime());
			if(courseForums.getLastModDateTime() == null)
				ps.setNull(11, Types.DATE);
			else
				ps.setDate(11, new java.sql.Date(courseForums.getLastModDateTime().getTime()));
			if(courseForums.getUpVoteCount() == null)
				ps.setNull(12, Types.INTEGER);
			else
				ps.setInt(12, courseForums.getUpVoteCount());
			if(courseForums.getTotVoteCount() == null)
				ps.setNull(13, Types.INTEGER);
			else
				ps.setInt(13, courseForums.getTotVoteCount());
			if(courseForums.getCommentCount() == null)
				ps.setNull(14, Types.INTEGER);
			else
				ps.setInt(14, courseForums.getCommentCount());
			ps.setString(15, courseForums.getThreadType());
			ps.setString(16, courseForums.getTitle());
			ps.setString(17, courseForums.getCommentableSysId());
			if(courseForums.getEndorsed() == null)
				ps.setNull(18, Types.BIT );
			else
				ps.setBoolean(18, courseForums.getEndorsed());
			if(courseForums.getClosed() == null)
				ps.setNull(19, Types.BIT);
			else
				ps.setBoolean(19, courseForums.getClosed());
			ps.setBoolean(20, courseForums.getVisible());

			return (ps.execute());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public int closeAll() {
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
			e.printStackTrace();
			return -1;
		}
	}
}
