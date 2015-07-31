package EDXDataStaging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Scanner;
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

import utility.GetLastId;
import utility.UtilDateSql;
import LMSDataLoader.Dao.EventForumInteractDao;
import LMSDataLoader.Dao.EventProbInteractDao;
import LMSDataLoader.Dao.EventVideoInteractDao;
import LMSDataLoader.Dao.UserSessionOldDao;
import LMSDataLoader.Dao.UserSessionOldLogDao;
import LMSDataLoader.dataModels.Course;
import LMSDataLoader.dataModels.CourseForums;
import LMSDataLoader.dataModels.EventCourseInteract;
import LMSDataLoader.dataModels.EventForumInteract;
import LMSDataLoader.dataModels.EventProbInteract;
import LMSDataLoader.dataModels.EventVideoInteract;
import LMSDataLoader.dataModels.UserSessionOld;
import LMSDataLoader.dataModels.UserSessionOldLog;
import LMSDataLoader.Dao.EventCourseInteractDao;

@WebServlet("/EdxLogReader")
public class EdxLogReader extends HttpServlet {

	private static final long serialVersionUID = 1260045197860625956L;
	private String eventName,eventStr, eventTypeStr, eventType, lmsName = null;
	private static final Short VIDEO_START=0, PROBLEM_START=30, NAVIGATION_START=60,DISCUSS_START=90, COURSE_START=120, OTHER_START=150;
	private String eventSource = null;
	String logFileDir = null;
	JSONParser parserJson = new JSONParser();
	GetLastId geteventId = null;
	private UserSessionOldLog userSession;
	
	private CourseForums courseForums;
	
	private UserSessionOldLogDao userSessionDao ;
	private String moduleSysName;
	private ResultSet rsDiscuss = null;
	private Short eventNo = null;
	private String tmpStr1,tmpStr2,tmpStr3;
	
	/* EventType, EventName is translated into numbers, each of the event will be given a number & eventType will be the range of 
	 * Numbers
	 * VIDEO								10 - 30
	 * ============================================
	 * load_video - 						11
	 * pause_video - 						12
	 * play_video							13
	 * seek_video							14
	 * speed_change_video					15
	 * stop_video							16
	 * hide_transcript						17
	 * show_transcript						18
	 * save_user_state						19
	 * transcript_translation				20
	 * transcript_download					21
	 * /transcript/translation/				22 (in edx properties save_video_position)
	 *  
	 * /transcript/download")				23
	 * 
	 * PROBLEM								45 - 65
	 * ===============================================
	 * problem_check						45
	 * problem_check_fail					46
	 * problem_reset						47
	 * problem_rescore						48
	 * problem_rescore_fail					49
	 * problem_save							50
	 * problem_show							51
	 * reset_problem						52
	 * reset_problem_fail					53
	 * save_problem_success					54
	 * problem_graded						55
	 * showanswer							56
	 * save_problem_fail					57
	 * problem_get							58	
	 * 
	 * NAVIGATION 							80 - 105
	 * ===============================================
	 * seq_goto								80
	 * seq_next								81
	 * seq_prev								82
	 * page_close							83
	 * goto_position						84
	 *  /dashboard							85
	 *  /									86
	 * /jsi18n/								87
	 * /i18n.js								88
	 * jump_to_discussion					89
	 * progress								90
	 * view_courses							91
	 * logout 								92
	 * how_it_works							93
	 * calculate							94
	 * city									95
	 * jump_to_vertical						96
	 * login								97
	 * 
	 * DISCUSSION							120 - 135
	 * ================================================
	 * threads								120 
	 * users								121
	 * reply 								122
	 * thread_create						123
	 * upvote 								124
	 * flagAbuse							125
	 * follow								126
	 * unfollow								127
	 * upload								128
	 * forum_searched						129
	 * 
	 * COURSE								150 - 160
	 * ==============================================
	 * courseware							150
	 * chapter								151
	 * session								152
	 * 
	 * courses_chapter						153
	 * courses_access						154
	 * 
	 * 
	 * WIKI  								175 - 180
	 * ===================================================
	 * wiki 								175
	 * 
	 * 
	 *INSTRUCTOR EVENTS						195 - 205
	 *====================================================
	 *
	 *list_instructor_tasks					195
	 *list-staff							196
	 *dump-graded-assignments-config		197
	 * 
	 *
	 *OPEN ASSESMENT                      220 - 240
	 * ========================================================
	 * render_self_assessment				220
	 * render_submission					221
	 * render_leaderboard					222
	 * render_peer_assessment				223
	 * render_message						224
	 * render_grade							225
	 * render_student_training				226
	 * save_submission						227
	 * submit_feedback						228
	 * training_assess						229
	 * peer_assess							230
	 * submit								231
	 * render_staff_info					232
	 * 
	 * 
	 * ENROLLMENT 							255 - 265
	 * ====================================================
	 *  
	 *  edx.course.enrollment.activated	    255
	 *  edx.course.enrollment.deactivated   256
	 *  edx.course.enrollment.mode_changed	257
	 *  change_enrollment					258
	 *  /register							259
	 *  /create_account						260
	 *  
	 *  TEXTBOOK INTERACTION EVENTS				280 - 285
	 *  ===============================================
	 *  book 								280
	 *  
	 *  
	 *
	 * ADMIN								290 - 300
	 * ===============================================
	 * /admin								290
	 * pref_status							291
	 * password_reset						292
	 * password_reset_confirm				293
	 * 
	 */
	/*EventProbInteract eventProbInteract = new EventProbInteract();
	EventProbInteractDao eventProbInteractDao = null;
	
	EventVideoInteract eventVideoInteract = new EventVideoInteract();
	EventVideoInteractDao eventVideoInteractDao = null;
	
	EventCourseInteract eventCourseInteract = new EventCourseInteract();
	EventCourseInteractDao EventCourseInteractDao = null;
	
	EventForumInteract eventForumInteract = new EventForumInteract();
	EventForumInteractDao eventForumInteractDao = null;
	*/
	
	SimpleDateFormat logDateFormat, timeFormat;
	SimpleDateFormat edxDateFormat;

	String courseName = null;
	//String courseRun = null;
	//Long lmsUserId = 0l;
	
	String source;
	java.sql.Timestamp createDateTime = null;
	java.sql.Timestamp lastModDateTime = null;

	/** Problem event fields */
	
	
	/** Video event fields */
	//String videoSysName;
	Float oldSeekTime;
	Float currSeekTime;
	String videoNavigType;
	Float oldSpeed;
	Float currSpeed;
	java.sql.Time videoPosition = null;
	private String[] tmpStr;
	
	int irec, con;
	
	String videoPostString;

	JSONObject mainRecJson = null;
	JSONObject contextJson = null;
	
	JSONObject videoPostJson = null;
	JSONObject eventJson = null;
	JSONObject enrollJson = null;
	JSONObject correctJson   = null;
	JSONObject inputJson = null;
	JSONObject stateJson = null;
	
	private Connection conn = null, conn1=null;
	private PreparedStatement psChapterSess = null, psProblem = null, psChapter = null, psVideo = null, psDiscuss = null, psUserSession = null, psCourseForum = null, psJump_to = null, psCourseChapterSess = null,psVertical = null, psComment = null;
	private ResultSet rsChapterSess = null, rsPS = null;
	public void init() throws ServletException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
		//	DataSource ds = (DataSource) envContext.lookup("jdbc/EDXDb");
			DataSource ds = (DataSource) envContext.lookup("jdbc/IITBxDb");
			conn = ds.getConnection();
			//conn1 = ds1.getConnection();
		//	userSessionDao = new UserSessionDao(conn1);
			userSessionDao = new UserSessionOldLogDao(conn);
			try{
				
				psChapterSess = conn.prepareStatement("SELECT c.chapteTitle, s.sessionTitle, c.chapterSysName " +
						" FROM CourseChapter c join CourseChapterSession s " +
						" on c.courseName = s.courseName and c.chapterSysName = s.chapterSysName " + 
						" where s.sessionSysName = ?  and c.courseName = ?");
				
				psComment = conn.prepareStatement("SELECT commentableSysId, commentType from CourseForums where commentSysId = ? and courseName = ?");
				
				psCourseChapterSess = conn.prepareStatement("SELECT sessionSysName from CourseChapterSession where chapterSysName =  ? and courseName = ?");
/*				psProblem = conn.prepareStatement("SELECT c.chapteTitle, c.chapterSysName,p.quizTitle, s.sessionTitle, s.sessionSysName  FROM CourseProblems p, " +
						" CourseChapterSession s, CourseChapter c where p.sessionSysName = s.sessionSysName and " + 
						" s.chapterSysName = s.chapterSysName and p.courseName = s.courseName " +
						" and s.chapterSysName = c.chapterSysName and s.courseName = c.courseName " + 
						" and p.quizSysName = ? and p.courseName = ?");
*/				
				psVertical = conn.prepareStatement("SELECT sessionSysName from CourseVertical where verticalSysName = ? and courseName = ?");
				
				psJump_to = conn.prepareStatement("SELECT chapterSysName, discussionTitle from courseDiscussions where discussionSysName = ? and courseName = ?");
				psCourseForum = conn.prepareStatement("SELECT lmsName,orgName,courseName from CourseForums where commentSysId = ?");
				psUserSession = conn.prepareStatement("SELECT lmsUserId FROM User where name= ?");
				
				
				psProblem = conn.prepareStatement("SELECT chapterSysName,quizTitle FROM CourseProblems " +
						" where quizSysName = ? and courseName = ?");

				psChapter = conn.prepareStatement("SELECT chapteTitle FROM CourseChapter where courseName = ? and chapterSysName = ?");
				psVideo = conn.prepareStatement("SELECT v.videoTitle, c.chapteTitle " +
						" FROM CourseVideos v, CourseChapter c where v.courseName = c.courseName and v.chapterSysName = c.chapterSysName"
						+ " and v.courseName = ? and v.videoSysName = ?");
				psDiscuss = conn.prepareStatement("SELECT discussionTitle FROM CourseDiscussions where discussionSysId = ?" +
						" and courseName = ?");
				
			} catch (SQLException e) {      
				System.out.println("ERror in SQL " + e.toString());
				e.printStackTrace();
			}

			//geteventId = new GetLastId(conn);
			//eventVideoInteractDao = new EventVideoInteractDao(conn);
		} catch (NamingException ne) {
			ne.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String propFileName = "EdxParams.properties";
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(propFileName);
			Properties properties = new Properties();
			properties.load(inputStream);

		//	logDateFormat = new SimpleDateFormat(properties.getProperty("SQLDATEFORMAT"));
			logDateFormat = new SimpleDateFormat(properties.getProperty("LOGDATEFORMAT"));
			timeFormat = new SimpleDateFormat(
					properties.getProperty("TIMEFORMAT"));
			logFileDir = properties.getProperty("TMPDIR");
			lmsName = properties.getProperty("LMSNAME");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		SimpleDateFormat videoPosFormat = new SimpleDateFormat("HH:mm:ss");
		BufferedReader br = null;
		JSONObject contextJson = null;
		JSONObject videoPostJson = null;
		String event;
		String sCurrentLine = null;

	/*	//File dir = new File(logFileDir);
		System.out.println("filePath: " + dir.getName());
		File[] files = dir.listFiles();
		if (dir.length() == 0) {
			System.out.println("Directory is empty!!");
		} else {
			for (File f : files) {
				System.out.println("The available files are: " + f.getName());
			}
		}
*/
		/** Iterate over the obtained files */

	//	for (File f : files) {
		//	if (f.isFile()) {
			//String filePath = request.getSession().getServletContext().getRealPath("/input")  
		//	String filePath = request.getSession().getServletContext().getContextPath() + "/input"
		//			+ File.separator + "tracking.log";
				//File logFile = new File("/home/hduser/edxlogdata/tracking.log");
		//File logFile = new File("/home/hduser/testData/tracking.log-20150224-1424773021");
		//File logFile = new File("/home/hduser/testData/tracking.log-20150224-1424762221");
		//File logFile = new File("/home/hduser/testData/tracking.log-20150224-1424765821");
		//File logFile = new File("/home/hduser/testData/tracking.log-20150224-1424769421");
		//File logFile = new File("/home/shadowwalker/mm/tracking.log");
		
		String path = "";
		int ni = 0;
		 File folder = new File("//home//hduser//mm");
	     File[] listOfFiles = folder.listFiles();
	     for (File file : listOfFiles) {
	    	 
	                if (file.isFile()) {
	                	path = file.getName();
	                path = "//home//hduser//mm//" + path;
	         		File logFile = new File(path);		
		
		try {
			BufferedWriter out1 = null;
		    FileWriter fstream = new FileWriter("//home//hduser//IITBackupProcessor//Resources//test.txt", true); //true tells to append data.
		    out1 = new BufferedWriter(fstream);		
		    
		    BufferedWriter out2 = null;
		    FileWriter fstream1 = new FileWriter("//home//hduser//IITBackupProcessor//Resources//log.txt", true); //true tells to append data.
		    out2 = new BufferedWriter(fstream1);		
			
		    
		    int count = 0;
		    //File logFile = new File("/home/hduser/testData/vv");
			//System.out.println("file: " + logFile.getName());
				try {
					br = new BufferedReader(new FileReader(logFile));
					int iloop = 0;
					irec = 0;
				    con = 0;
					
					//while (((sCurrentLine = br.readLine()) != null) && (iloop <= 2)){
					//while (((sCurrentLine = br.readLine()) != null) && (irec < 2637)){
					while ((sCurrentLine = br.readLine()) != null) {
							
						try {	
							
							userSession = new UserSessionOld();
							System.out.println("(sCurrentLine) " + sCurrentLine);
							mainRecJson = (JSONObject) parserJson.parse(sCurrentLine);
							
							
							userSession.setDataSource("SQL");
							eventSource = mainRecJson.get("event_source").toString();
							userSession.setEventSource(eventSource);
							
							userSession.setHostName(mainRecJson.get("host").toString());
							userSession.setIpAddress(mainRecJson.get("ip").toString());
							
							
							userSession.setUserName(mainRecJson.get("username").toString());
							
					//	System.out.println(" mainRecJson " + mainRecJson);
						/** RECORD NUMBER */
						irec++;
					//	System.out.println("Record number:\t\t  " + irec);

						/** SESSION SYSTEM NAME */
						if(mainRecJson.get("session") != null)
							userSession.setSessionSysName((String) mainRecJson.get("session"));
						//else
						
						/** COMMAND NAME */
						//commandName = (String) mainRecJson.get("session");
						
						/** LMS NAME */
						lmsName = "EDX";
						//System.out.println("Lms name is:\t\t  " + lmsName);
						userSession.setLmsName(lmsName);

						/** ORGANIZATION NAME */
						contextJson = (JSONObject) mainRecJson.get("context");
						userSession.setOrgName((String) contextJson.get("org_id"));

						/** COURSE NAME AND COURSE RUN */
						courseName = (String) contextJson.get("course_id");
						if (courseName.length() != 0) {
							tmpStr = courseName.split("/");
							courseName = tmpStr[1];
							userSession.setCourseName(courseName);
							userSession.setCourseRun(tmpStr[2]);
						}
						
						
						//System.out.println("Course name:\t\t  " + courseName);
						//System.out.println("Course run:\t\t  " + courseRun);
						//if (contextJson.containsKey("module")) 
							//System.out.println("MODULE : " + contextJson.get("module"));
						//else
							//System.out.println("MODULE IS NULL: ");
						
						/** LMS USER ID */
						if (contextJson.containsKey("user_id")) {
							String user_id = contextJson.get("user_id")
									.toString().trim();
							if (user_id.length() != 0) {
								userSession.setLmsUserId(Long.parseLong(user_id));
							}
						}
						try {
							createDateTime = new java.sql.Timestamp(logDateFormat.parse(mainRecJson.get("time").toString()).getTime());
							userSession.setCreateDateTime(createDateTime);
						} catch (java.text.ParseException e1) {
							e1.printStackTrace();
						}
						userSession.setDataSource("LOG");
						/** EVENT NAME */
						eventTypeStr = (String) mainRecJson.get("event_type");
						//System.out.println("eventTypeStr " + eventTypeStr);
						eventStr = mainRecJson.get("event").toString();
						if (mainRecJson.get("event")  instanceof JSONObject) 
							eventJson = (JSONObject) new JSONParser().parse(eventStr);
						else
							eventJson = null;
						
						String str[] = eventTypeStr.split("/");
					//	System.out.println("eventStr " + eventStr);
					//	System.out.println("eventTypeStr " + eventTypeStr);
						
						if (eventTypeStr.contains("jump_to")) {
							String substr[] = eventTypeStr.split("/");
							String check = substr[substr.length - 2];
							userSession.setEventType("navigation");
							
							
							if (check.equals("vertical")) {
								String sysId = substr[substr.length - 1];
								userSession.setModuleSysName(sysId);
								userSession.setEventName("jump_to_vertical");
								psVertical.setString(1, userSession.getModuleSysName());
								psVertical.setString(2, courseName);
								
								String sessionSys = "";
								ResultSet rs4 = null;
								rs4 = psVertical.executeQuery();
								if (rs4.next()) {
									sessionSys = rs4.getString(1);
								}
								psChapterSess.setString(1, sessionSys);
								psChapterSess.setString(2, courseName);
								
								rs4 = null;
								rs4 = psChapterSess.executeQuery();
								
								if (rs4.next()) {
									userSession.setChapterTitle(rs4.getString(1));
									userSession.setSessTitle(rs4.getString(2));
									userSession.setChapterSysName(rs4.getString(3));
								}
							}
							if (check.equals("discussion")) {
								userSession.setEventName("jump_to_discussion");
								String sysId = substr[substr.length - 1];
								userSession.setModuleSysName(sysId);
								psJump_to.setString(1, userSession.getModuleSysName());
								psJump_to.setString(2, courseName);
								
								ResultSet rs = null;
								rs = psJump_to.executeQuery();
								String chapterSys = "";
								
								if (rs.next()) {
									chapterSys = rs.getString(1);
									userSession.setChapterSysName(chapterSys);
									userSession.setModuleTitle(rs.getString(2));
								
								
								
								psChapter.setString(1,courseName );
								psChapter.setString(2, chapterSys);
								ResultSet rs1 = null;
								rs1 = psChapter.executeQuery();
								
								
								if (rs1.next()) {
									userSession.setChapterTitle(rs.getString(1));
								}
								
								psCourseChapterSess.setString(1, chapterSys);
								psCourseChapterSess.setString(2, courseName);
								rs1 = null;
								rs1 = psCourseChapterSess.executeQuery();
								
								String sessionSysName = "";
								if (rs1.next()) {
									sessionSysName = rs.getString(1);
									userSession.setSessionSysName(sessionSysName);
								}
								
								psChapterSess.setString(1, sessionSysName);
								psChapterSess.setString(2, courseName);
								rs1 = null;
								rs1 = psChapterSess.executeQuery();
								
								if (rs1.next()) {
									userSession.setSessTitle(rs.getString(2));
								}
								}
								}
							}
 						
						if((eventTypeStr.contains("all_courses")) || (eventTypeStr.equals("/courses/all")) || (eventTypeStr.equals("/courses")) || (eventTypeStr.equals("/courses/"))) {  
							 
							 if ((eventTypeStr.contains("all")) || (eventTypeStr.equals("/courses")) || (eventTypeStr.equals("/courses/"))) {
								eventType = "navigation";
								eventName = "view_courses";
								userSession.setEventType(eventType);
								userSession.setEventName(eventName);
							}									
						}

						 /*------------------------------------------*Event problem interact starts here*--------------------------------------------------------------*/				 
						 
						if ((eventTypeStr.contains("problem_check")) || (eventTypeStr.equals("problem_check_fail")) || 
								(eventTypeStr.equals("problem_reset")) || (eventTypeStr.equals("problem_rescore")) ||
								(eventTypeStr.equals("problem_rescore_fail")) || (eventTypeStr.contains("problem_save"))  ||
								(eventTypeStr.contains("problem_show")) || (eventTypeStr.equals("reset_problem")) ||
								(eventTypeStr.equals("reset_problem_fail")) || (eventTypeStr.equals("save_problem_success")) ||
								(eventTypeStr.equals("problem_graded"))||(eventTypeStr.contains("showanswer")) ||
								(eventTypeStr.contains("save_problem_fail")) || (eventTypeStr.contains("problem_get")) || 
								(eventTypeStr.contains("problem"))){
									eventType="problem";
									if (eventTypeStr.contains("problem_check")) {
										eventName = "problem_check";
									}
									else if (eventTypeStr.contains("problem_get")) {
										eventName = "problem_get";
									} 
									else if (eventTypeStr.contains("problem_save")) {
										eventName = "problem_save";
									}
									else if (eventTypeStr.contains("problem_show")) {
										eventName = "problem_show";
									}
									else if ((eventTypeStr.contains("problem")) && (eventTypeStr.contains("input_ajax"))) {
										eventName = "problem_get";
									} 
									else
										eventName = eventTypeStr;
									userSession.setEventName(eventName);
									userSession.setEventType(eventType);
									processProblem();
						}
						/*--------------------------processing instructor task ---------*/
						else if (eventTypeStr.contains("instructor")) {   
							eventType = "instructor";
							eventName = "list_instructor_tasks";
							userSession.setEventType(eventType);
							userSession.setEventName(eventName);
						}
						
						else if ((eventTypeStr.contains("load_video"))
						
						/*------------------------------------------*Event Video interact starts here*--------------------------------------------------------------*/				
						
								|| (eventTypeStr.contains("pause_video"))
								|| (eventTypeStr.contains("play_video"))
								|| (eventTypeStr.contains("seek_video"))
								|| (eventTypeStr.contains("speed_change_video"))
								|| (eventTypeStr.contains("stop_video"))
								|| (eventTypeStr.contains("hide_transcript"))
								|| (eventTypeStr.contains("show_transcript"))
								|| (eventTypeStr.contains("save_user_state"))
								|| (eventTypeStr.contains("transcript_translation"))
								|| (eventTypeStr.contains("transcript_download")) 
								|| (eventTypeStr.contains("/transcript/translation/")) 
								|| (eventTypeStr.contains("/transcript/download"))) {
							//System.out.println("BEF proceeVideo eventTypeStr " + eventTypeStr);
									eventType = "video";
									if (eventTypeStr.contains("save_user_state"))
											eventName = "save_user_state";
									else if (eventTypeStr.contains("/transcript/translation/")) 
										eventName = "transcript_translation";
									else if (eventTypeStr.contains("/transcript/download")) 
										eventName = "transcript_download";
									else
										eventName = eventTypeStr;
									userSession.setEventName(eventName);
									userSession.setEventType(eventType);							
									processVideo();
						} // End of if for video
						/*------------Event openAssessment--------------------------------------*/
						else if (eventTypeStr.contains("_openassessment")) {
							if (eventTypeStr.contains("render_self_assessment")) {
								eventType = "_openassessment";
								eventName = "render_self_assessment";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);								
							}
							if (eventTypeStr.contains("render_submission")) {
								eventType = "_openassessment";
								eventName = "render_submission";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);								
							}
							if (eventTypeStr.contains("handler/save_submission")) {
								eventType = "_openassessment";
								eventName = "save_submission";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);
								
							}
							if (eventTypeStr.contains("handler/submit_feedback")) {
								eventType = "_openassessment";
								eventName = "submit_feedback";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);
								
							}
							if (eventTypeStr.contains("handler/training_assess")) {
								eventType = "_openassessment";
								eventName = "training_assess";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);
								
							}
							
							if (eventTypeStr.contains("handler/save_submission")) {
								eventType = "_openassessment";
								eventName = "save_submission";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);
								
							}
							if (eventTypeStr.contains("handler/submit")) {
								eventType = "_openassessment";
								eventName = "submit";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);
								
							}
							if (eventTypeStr.contains("render_leaderboard")) {
								eventType = "_openassessment";
								eventName = "render_leaderboard";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);								
							}
							if (eventTypeStr.contains("render_peer_assessment")) {
								eventType = "_openassessment";
								eventName = "render_peer_assessment";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);								
							}
							if (eventTypeStr.contains("render_message")) {
								eventType = "_openassessment";
								eventName = "render_message";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);								
							}
							if (eventTypeStr.contains("render_grade")) {
								eventType = "_openassessment";
								eventName = "render_grade";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);								
							}
							if (eventTypeStr.contains("peer_assess")) {
								eventType = "_openassessment";
								eventName = "peer_assess";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);								
							}
							if (eventTypeStr.contains("render_student_training")) {
								eventType = "_openassessment";
								eventName = "render_student_training";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);								
							}
							if (eventTypeStr.contains("render_staff_info")) {
								eventType = "_openassessment";
								eventName = "render_staff_info";
								userSession.setEventName(eventName);
								userSession.setEventType(eventType);								
							}
							
							String sub[] = eventTypeStr.split("/");
							String openSys = sub[sub.length - 3];
							String subType[] = openSys.split(";");
							String openSys1 = subType[subType.length - 1];
							String openSysName = openSys1.substring(1);
							userSession.setModuleSysName(openSysName);
							
							
							
						
							
						}
						
						/*------------------------------------------*Event Course interact starts here*--------------------------------------------------------------*/				
						
						
						else if ((eventTypeStr.contains("seq_goto")) || (eventTypeStr.equals("seq_next")) || 
								(eventTypeStr.equals("seq_prev")) || (eventTypeStr.equals("page_close")) ||
								(eventTypeStr.contains("goto_position"))){
								//(eventTypeStr.contains("courses")) || (eventTypeStr.contains("courseware"))){
									eventType="navigation";
									if (eventTypeStr.contains("goto_position")) 
										eventName = "goto_position";
									else
										eventName = eventTypeStr;
									userSession.setEventName(eventName);
									userSession.setEventType(eventType);
									processNavig();
								
						} 
						
						else if (eventTypeStr.contains("courses")){
							
							
							if(eventTypeStr.contains("discussion/threads")){
								if (eventTypeStr.contains("threads")) 
									eventType = "discussion";
								
								if (eventTypeStr.contains("reply")) {
									eventName = "reply";
								}
								if (eventTypeStr.contains("follow")) {
									eventName = "follow";
								}
								if (eventTypeStr.contains("unfollow")) {
									eventName = "unfollow";
								}
								
									String eve[] = eventTypeStr.split("/");
									
									String commentSysId = eve[eve.length - 2];
									
									psCourseForum.setString(1, commentSysId);
									
									
									
									
									
									ResultSet rs = null;
									rs = psCourseForum.executeQuery();
									
									if (rs.next()) {
										userSession.setLmsName(rs.getString("lmsName"));
										userSession.setOrgName(rs.getString("orgName"));
										userSession.setCourseName(rs.getString("courseName"));
									
										psComment.setString(1, commentSysId);
										psComment.setString(2, userSession.getCourseName());
										
										ResultSet rs1 = null;
										
										rs1 = psComment.executeQuery();
										if (rs1.next()) {
											userSession.setCommentableId(rs1.getString(1));
											userSession.setCommentType(rs1.getString(2));
											
										}
									
									}
									
									userSession.setEventName(eventName);
									userSession.setEventType(eventType);
								
							}
							if((eventTypeStr.contains("discussion/comments")) || ((eventTypeStr.contains("discussion/threads")))){
						
									eventType = "discussion";
								
								if (eventTypeStr.contains("upvote")) {
									eventName = "upvote";
								}
								else if (eventTypeStr.contains("flagAbuse")) {
									eventName = "flagAbuse";
								}
									
									String eve[] = eventTypeStr.split("/");
									
									String commentSysId = eve[eve.length - 2];
									
									psCourseForum.setString(1, commentSysId);
									
									ResultSet rs = null;
									rs = psCourseForum.executeQuery();
									
									if (rs.next()) {
										userSession.setLmsName(rs.getString("lmsName"));
										userSession.setOrgName(rs.getString("orgName"));
										userSession.setCourseName(rs.getString("courseName"));
										

										psComment.setString(1, commentSysId);
										psComment.setString(2, userSession.getCourseName());
										
										ResultSet rs1 = null;
										
										rs1 = psComment.executeQuery();
										if (rs1.next()) {
											userSession.setCommentableId(rs1.getString(1));
											userSession.setCommentType(rs1.getString(2));
											
										}
									}
									
									userSession.setEventName(eventName);
									userSession.setEventType(eventType);
								
							}
							
							if (eventTypeStr.contains("discussion")) {
								if (eventTypeStr.contains("threads/create")) {
									
									
									eventType = "Discussion";
									eventName = "thread_create";
									userSession.setEventType(eventType);
									userSession.setEventName(eventName);
									
									eventJson = (JSONObject) new JSONParser().parse(eventStr);
									JSONObject getEvent = null;
									String anonymous = "", anonymousToPeers = "";
									getEvent = (JSONObject) eventJson.get("POST");
									if(eventStr.contains("anonymous")){
									anonymous = getEvent.get("anonymous").toString();
									if (anonymous.equals("[\"false\"]"))
										userSession.setAnonymous("N");
									else if (anonymous.equals("[\"true\"]"))
										userSession.setAnonymous("Y");
									
									}
									if(eventStr.contains("anonymous_to_peers")){
										anonymousToPeers = getEvent.get("anonymous_to_peers").toString();
										if (anonymousToPeers.equals("[\"false\"]"))
											userSession.setAnonymousToPeers("N");
										else if (anonymousToPeers.equals("[\"true\"]"))
											userSession.setAnonymousToPeers("Y");									
										}
									
								}
								if (eventTypeStr.contains("upload")) {
									eventType = "Discussion";
									eventName = "Uploads";
									userSession.setEventName(eventName);
									userSession.setEventType(eventType);
								}
							}
							 
							if((eventTypeStr.contains("pdfbook")) || (eventTypeStr.contains("book"))) {
								
								eventJson = (JSONObject) new JSONParser().parse(eventStr);
								JSONObject getEvent = null;
								getEvent = (JSONObject) eventJson.get("GET");
								
								String eve = getEvent.get("file").toString();
								//System.out.println("Here2");
								//System.out.println(eve);
								
								String part[] = eve.split("/");
								
								String bk = part[part.length - 1];	
								String bk1 = bk.substring(0, (bk.length() - 2));
								
								
								
								eventType = "textbook_interaction";
								eventName = "book";
								userSession.setOtherTitle(bk1);
								userSession.setEventName(eventName);
								userSession.setEventType("discussion");
								//Setting The Viewer Type attribute as listed under Text Book Interaction in ReadTheDocs
								{
								if((eventTypeStr.contains("pdfbook")))
									userSession.setOtherType("PDF");
								else if((eventTypeStr.contains("book")))
									userSession.setOtherType("PNG");
								}
							
						}
						   if (eventTypeStr.contains("progress")) {
								
								eventType = "navigation";
								eventName = "progress";
								userSession.setEventType(eventType);
								userSession.setEventName(eventName);
							
							}
							
							if(eventTypeStr.contains("discussion/users")) {
							 
									eventType = "discussion";
									eventName = "view";
									userSession.setEventName(eventName);
									userSession.setEventType("discussion");
							}
							
							if(eventTypeStr.contains("discussion/forum")){
									eventType = "discussion";
									tmpStr = eventTypeStr.split("/");
									if(eventTypeStr.contains("threads")){
										eventName = "threads";
										userSession.setModuleSysName(tmpStr[tmpStr.length - 3]);
										psDiscuss.setString(1, userSession.getModuleSysName());
										psDiscuss.setString(2, courseName);
										rsDiscuss = psDiscuss.executeQuery();
										if(rsDiscuss.next()){
											userSession.setModuleTitle(rsDiscuss.getString(1));
										}
									}
									else {
										eventName = "users";
									}
									userSession.setEventName(eventName);
									userSession.setEventType(eventType);
									//processDiscussions();
							}
									
							else if(eventTypeStr.contains("courseware")){
								eventType="courses";
								userSession.setEventType(eventType);									
								processCourses();
							} else if (str[str.length - 1].equals("progress")) {
								
								eventType = "navigation";
								eventName = "progress";
								userSession.setEventType(eventType);
								userSession.setEventName(eventName);
							
							}
								/*else if (eventTypeStr.contains("progress")) {   
								eventType = "navigation";
								eventName = "progress";
								userSession.setEventType(eventType);
								userSession.setEventName(eventName);
							}*/ else if (eventTypeStr.contains("wiki")) {   
								eventType = "wiki";
								eventName = "wiki";
								userSession.setEventType(eventType);
								userSession.setEventName(eventName);
							}
							else {
									// about 
								if(eventTypeStr.contains("about")){
									userSession.setEventType("courses");
									userSession.setEventName("about");
									userSession.setChapterTitle("about");
										
								} else if(eventTypeStr.contains("info")){
									userSession.setEventType("courses");
									userSession.setEventName("info");
									userSession.setChapterTitle("info");
										
								}else {
									
									processCourses();
								}
							}
						}
						else {
							
							
							processOthers();
						}
				/*		System.out.println("USER RECORD STARTS HERE");
						System.out.println("sessionSysName; " + userSession.getSessionSysName());
						System.out.println("orgName; " + userSession.getOrgName());
						System.out.println("courseName;" + userSession.getCourseName());
						System.out.println("courseRun; " + userSession.getCourseRun());
						System.out.println("lmsUserId; " + userSession.getLmsUserId());
						System.out.println("eventType; " + userSession.getEventType());
						System.out.println("eventSource; " + userSession.getEventSource());
						System.out.println("eventName; " + userSession.getEventName());
						System.out.println("hostName; " + userSession.getHostName());
						System.out.println(" ipAddress; " + userSession.getIpAddress());     
						System.out.println("createDateTime; " + userSession.getCreateDateTime());
					//	System.out.println("lastModDateTime;" + userSession.getlastModDateTime());
						System.out.println("agent; " + userSession.getAgent());
						System.out.println("dataSource; " + userSession.getDataSource());
						System.out.println("userName; " + userSession.getUserName());
						//System.out.println("url; " + userSession.getUrl());
						System.out.println("oldVideoSpeed; " + userSession.getOldVideoSpeed());
						System.out.println("currVideoSpeed; " + userSession.getCurrVideoSpeed());
						System.out.println("oldVideoTime; " + userSession.getOldVideoTime());
						System.out.println("currVideoTime; " + userSession.getCurrVideoTime());
						System.out.println("oldGrade; " + userSession.getOldGrade());
						System.out.println("currGrade; " + userSession.getCurrGrade());
						System.out.println("maxGrade; " + userSession.getMaxGrade());
						System.out.println("attempts; " + userSession.getAttempts());
						System.out.println("maxNoAttempts; " + userSession.getMaxNoAttempts());
						System.out.println("hintAvailable; " + userSession. getHintAvailable());
						System.out.println("hintUsed; " + userSession. getHintUsed());
						System.out.println("moduleSysName; " + userSession. getModuleSysName());
						System.out.println("currPosition; " + userSession.getCurrPosition());
						System.out.println("oldPosition; " + userSession.getOldPosition());
						System.out.println("videoNavigType " + userSession.getVideoNavigType());
						System.out.println("chapterSysName; " + userSession. getChapterSysName());
						System.out.println("AnswerChoice " + userSession.getAnswerChoice());
						System.out.println("success " + userSession.getSuccess());
						if(userSession. getChapterTitle() != null)
							System.out.println("chapterTitle " + userSession. getChapterTitle().replaceAll("\\s+"," "));
						else
							System.out.println("chapterTitle is null" );
						if(userSession. getModuleTitle() != null)
							System.out.println("moduleTitle " + userSession. getModuleTitle().replaceAll("\\s+"," "));
						else
							System.out.println("moduleTitle is null");
						if(userSession.getSessTitle() != null)
							System.out.println("sessTitle " + userSession. getSessTitle().replaceAll("\\s+"," "));
						else
							System.out.println("sessTitle is null" );
						if(userSession.getSessSysName() != null)
							System.out.println("sessSysName " + userSession.getSessSysName());
						else
							System.out.println("sessSysName is null");
					//	System.out.println("moduleType " + userSession. getModuleType());
						//System.out.println("WRITING RECNO " + irec);
					*/
						if(userSession.getEventType() != null){
							convertEventToNumber();
							userSessionDao.insertRec(userSession);
							con++;
						}
						else {
							
							
							//   out.write("sessionSysName; " + userSession.getSessionSysName() + "\n");
							    count++;
							   
						//	    out1.write("total count is " + count + "\n");
							    out1.write(" -----------------------------------------------------------------------\n");
							    out1.write(sCurrentLine);
							    /*    out.write("orgName; " + userSession.getOrgName() + "\n");
								out.write("courseName;" + userSession.getCourseName() + "\n");
								out.write("courseRun; " + userSession.getCourseRun() + "\n");
								out.write("lmsUserId; " + userSession.getLmsUserId() + "\n" ); 
								out.write("eventType; " + userSession.getEventType() + "\n");
								out.write("eventSource; " + userSession.getEventSource() + "\n");
								out.write("eventName; " + userSession.getEventName() + "\n");
								out.write("hostName; " + userSession.getHostName() + "\n");
								out.write(" ipAddress; " + userSession.getIpAddress() + "\n");  
							*/
							    /*out.write("createDateTime; " + userSession.getCreateDateTime() + "\n");
							//	System.out.println("lastModDateTime;" + userSession.getlastModDateTime());
								out.write("agent; " + userSession.getAgent() + "\n");
								out.write("dataSource; " + userSession.getDataSource() + "\n");
								out.write("userName; " + userSession.getUserName() + "\n");
								//System.out.println("url; " + userSession.getUrl());
								out.write("oldVideoSpeed; " + userSession.getOldVideoSpeed() + "\n");
								out.write("currVideoSpeed; " + userSession.getCurrVideoSpeed() + "\n");
								out.write("oldVideoTime; " + userSession.getOldVideoTime() + "\n");
								out.write("currVideoTime; " + userSession.getCurrVideoTime() + "\n");
								out.write("oldGrade; " + userSession.getOldGrade() + "\n");
								out.write("currGrade; " + userSession.getCurrGrade() + "\n");
								out.write("maxGrade; " + userSession.getMaxGrade() + "\n");
								out.write("attempts; " + userSession.getAttempts() + "\n");
								out.write("maxNoAttempts; " + userSession.getMaxNoAttempts() + "\n");
								out.write("hintAvailable; " + userSession. getHintAvailable() + "\n");
								out.write("hintUsed; " + userSession. getHintUsed() + "\n");
								out.write("moduleSysName; " + userSession. getModuleSysName() + "\n");
								out.write("currPosition; " + userSession.getCurrPosition() + "\n");
								out.write("oldPosition; " + userSession.getOldPosition() + "\n");
								out.write("videoNavigType " + userSession.getVideoNavigType() + "\n");
								out.write("chapterSysName; " + userSession. getChapterSysName() + "\n");
								out.write("AnswerChoice " + userSession.getAnswerChoice() + "\n");
								out.write("success " + userSession.getSuccess() + "\n");
								if(userSession. getChapterTitle() != null)
									out.write("chapterTitle " + userSession. getChapterTitle().replaceAll("\\s+"," ") + "\n");
								else
									out.write("chapterTitle is null \n" );
								if(userSession. getModuleTitle() != null)
									out.write("moduleTitle " + userSession. getModuleTitle().replaceAll("\\s+"," ") + "\n");
								else
									out.write("moduleTitle is null \n");
								if(userSession.getSessTitle() != null)
									out.write("sessTitle " + userSession. getSessTitle().replaceAll("\\s+"," ") + "\n");
								else
									out.write("sessTitle is null \n" );
								if(userSession.getSessSysName() != null)
									out.write("sessSysName " + userSession.getSessSysName() + "\n");
								else
									out.write("sessSysName is null \n"); */
							
							
							
							
							   
							
						//	System.out.println("RecNo " + irec);
						
						}	//iloop++;
					
				 }catch (org.json.simple.parser.ParseException e) {
					System.out.println("ERror in parse " + e.toString());
				}catch (Exception e1) {      
					System.out.println("ERror in SQL " + e1.toString());
					e1.printStackTrace(); 
					}
						
					}
				}
				catch (IOException e) {
					 System.err.println("Error: " + e.getMessage());
				}
				finally {
				
					
					out2.write("total records scanned : " + irec+ "\n");
					out2.write("total records written : " + con + "\n");
					System.out.println("Processing Complete for file: "+ path +"\n");
					out2.write("file name is " + path + "\n");
					out1.write("file name is " + path + "\n");
					 if(out1 != null) {
					        out1.close();
					        out2.close();
					    
					}
				}
					
				}catch(java.io.FileNotFoundException e2){
					System.out.println("ERror in file i/o " + e2.toString());
				
				}catch (Exception e1) {      
					System.out.println("ERror in SQL " + e1.toString());
					e1.printStackTrace(); 
				}
		
			}
		}
				
		//	}
		//}
	}
	private void convertEventToNumber(){
		// VIDEO 10 - 25
		
		if(eventName.equals("load_video")){
			eventNo = 11;
		}
		 if(eventName.equals("pause_video")){
			 eventNo = 12;
		 }
		 if(eventName.equals("play_video")){
			 eventNo = 13;
		 }							
		 if(eventName.equals("seek_video")){
			 eventNo = 14;
		 }							
		 if(eventName.equals("speed_change_video")){
			 eventNo = 15;
		 }					
		 if(eventName.equals("stop_video")){
			 eventNo = 16;
		 }						
		 if(eventName.equals("hide_transcript")){
			 eventNo = 17;
		 }					
		 if(eventName.equals("show_transcript")){
			 eventNo = 18;
		 }						
		 if(eventName.equals("save_user_state")){
			 eventNo = 19;
		 }						
		 if(eventName.equals("transcript_translation")){
			 eventNo = 20;
		 }				
		 if(eventName.equals("transcript_download")){
			 eventNo = 21;
		 }					
		// PROBLEM 45 - 65
		 
		 if(eventName.equals("problem_check")){
			 eventNo = 45;
		 }						
		 if(eventName.equals("problem_check_fail")){
			 eventNo = 46;
		 }					
		 if(eventName.equals("problem_reset")){
			 eventNo = 47;
		 }					
		 if(eventName.equals("problem_rescore")){
			 eventNo = 48;
		 }						
		 if(eventName.equals("problem_rescore_fail")){
			 eventNo = 49;
		 }				
		 if(eventName.equals("problem_save")){
			 eventNo = 50;
		 }							
		 if(eventName.equals("problem_show")){
			 eventNo = 51;
		 }							
		 if(eventName.equals("reset_problem")){
			 eventNo = 52;
		 }						
		 if(eventName.equals("reset_problem_fail")){
			 eventNo = 53;
		 }					
		 if(eventName.equals("save_problem_success")){
			 eventNo = 54;
		 }					
		 if(eventName.equals("problem_graded")){
			 eventNo = 55;
		 }						
		 if(eventName.equals("showanswer")){
			 eventNo = 56;
		 }					
		 if(eventName.equals("save_problem_fail")){
			 eventNo = 57;
		 }					
		 if(eventName.equals("problem_get")){
			 eventNo = 58;
		 }	
		 
		 // NAVIGATION - 80 - 105
		 if(eventName.equals("seq_goto")){
			 eventNo = 80;
		 }								
		 if(eventName.equals("seq_next")){
			 eventNo = 81;
		 }				
		 if(eventName.equals("seq_prev")){
			 eventNo = 82;
		 }				
		 if(eventName.equals("page_close")){
			 eventNo = 83;
		 }				
		 if(eventName.equals("goto_position")){
			 eventNo = 84;
		 }				
		 if(eventName.equals("dashboard")){
			 eventNo = 85;
		 }				
		 if(eventName.equals("/")){
			 eventNo = 86;
		 }				
		 if(eventName.equals("jsi18n")){
			 eventNo = 87;
		 }				
		 if(eventName.equals("i18n")){
			 eventNo = 88;
		 }				
		 else if (eventName.equals("howItWorks")){
			 eventNo = 93;
		 }
		 else if (eventName.equals("calculate")){
			 eventNo = 94;
		 }else if(eventName.equals("logout")){
			 eventNo = 92;
		 }else if(eventName.equals("city")){
			 eventNo = 95;
		 }else if (eventName.equals("jump_to_discussion")) {
			 eventNo = 89;
		 }
		 else if (eventName.equals("jump_to_vertical")) {
			 eventNo = 96;
		 }else if(eventName.equals("login")){
			 eventNo = 97;
		 }
		 else if(eventName.equals("progress")){
			 eventNo = 90;
		 }
		 if(eventName.equals("view_courses")){
			 eventNo = 91;
		 }
		 // DISCUSSION 120 - 135
		 else if(eventName.equals("reply")){
			 eventNo = 122;
		 }else if (eventName.equals("thread_create")) {
			 eventNo = 123;
		 }else if(eventName.equals("wiki")){
			 eventNo = 175;
		 }
		 else if(eventName.equals("threads")){
			 eventNo = 120;
		 } else if(eventName.equals("users")){
			 eventNo = 121;
		 }else if (eventName.equals("forum_searched")){
			 eventNo = 129;
		 } 
		 else if (eventName.equals("upvote")){
			 eventNo = 124;
		 } 
		 else if (eventName.equals("flagAbuse")){
			 eventNo = 125;
		 } 
		 else if (eventName.equals("follow")){
			 eventNo = 126;
		 } 
		 else if (eventName.equals("unfollow")){
			 eventNo = 127;
		 }
		 else if (eventName.equals("upload")){
			 eventNo = 128;
		 } 
		 
		 
		 // COURSE 150 - 160
		 if(eventName.equals("courseware")){
			 eventNo = 150;
		 }				
		 if(eventName.equals("chapter")){
			 eventNo = 151;
		 }				
		 if(eventName.equals("session")){
			 eventNo = 152;
		 }
		 if(eventName.equals("courses_chapter")){
			 eventNo = 153;
		 }
		 if(eventName.equals("courses_access")){
			 eventNo = 154;
		 }
		 
		 
		 //ENROLLMENT - 255 - 265
		 if(eventName.equals("edx.course.enrollment.activated")){
			 eventNo = 255;
		 }	
		 if(eventName.equals("edx.course.enrollment.deactivated")){
			 eventNo = 256;
		 }	
		 if(eventName.equals("edx.course.enrollment.mode_changed")){
			 eventNo = 257;
		 }	
		 if(eventName.equals("change_enrollment")){
			 eventNo = 258;
		 }					
		 if(eventName.equals("register")){
			 eventNo = 259;
		 }		
		 else if(eventName.equals("create_account")){
			 eventNo = 260;
		 } 
		
		 
		 // INSTRUCTOR 195 - 205
		 if(eventName.equals("list-staff")){
			 eventNo = 196;
		 } else if(eventName.equals("dump-graded-assignments-config")){
			 eventNo = 197;
		 }
		 else if (eventName.equals("list_instructor_tasks")) {
			 eventNo = 195;
		 
		 }
		// ADMIN 101 - 125
		 else if(eventName.equals("admin")){
			 eventNo = 290;
		 }
		 else if(eventName.equals("pref_status")){
			 eventNo = 291;
		 }
		 else if (eventName.equals("passwordReset")){
			 eventNo = 292;
		 }else if (eventName.equals("passwordResetConfirm")){
			 eventNo = 293;
		 }
		 // Process Others
		
		 // OpenAssessment 220 - 240
		 if (eventName.equals("render_self_assessment")) {
			 eventNo = 220;
		 }
		 else if (eventName.equals("render_submission")) {
			 eventNo = 221;
		 }
		 else if (eventName.equals("render_leaderboard")) {
			 eventNo = 222;
		 }
		 else if (eventName.equals("render_peer_assessment")) {
			 eventNo = 223;
		 }
		 else if (eventName.equals("render_message")) {
			 eventNo = 224;
		 }
		 else if (eventName.equals("render_grade")) {
			 eventNo = 225;
		 }
		 else if (eventName.equals("render_student_training")) {
			 eventNo = 226;
		 }
		 else if (eventName.equals("save_submission")) {
			 eventNo = 227;
		 }
		 else if (eventName.equals("submit_feedback")) {
			 eventNo = 228;
		 }
		 else if (eventName.equals("training_assess")) {
			 eventNo = 229;
		 }
		 else if (eventName.equals("peer_assess")) {
			 eventNo = 230;
		 }
		 else if (eventName.equals("submit")) {
			 eventNo = 231;
		 }
		 else if (eventName.equals("render_staff_info")) {
			 eventNo = 232;
		 }
		 
		 
		//TEXTBOOK interaction starts here 
		 
		 if (eventName.equals("book")) {
			 eventNo = 280;
		 }
		 
		 
		 
		 
			
		 userSession.setEventNo(eventNo);
	}
	private void processCourses(){
		//String[] tmpStr;
		ResultSet rs;
	//	System.out.println("IN COURSE");
		try {
		//		System.out.println(eventTypeStr);
				tmpStr = eventTypeStr.split("/");
			//	System.out.println(tmpStr.length);
				if (tmpStr.length > 0) {
					if(tmpStr[tmpStr.length - 1].equals("courseware")){
					//	System.out.println("IN COURSE");
						userSession.setModuleSysName("courseware");
						userSession.setEventName("courseWare");
						eventName = "courseWare";
						userSession.setChapterTitle("courseware");
					}
				} 
			 if (tmpStr.length > 1) {
			 if(tmpStr[tmpStr.length - 2].equals("courseware") ){
			//		System.out.println("IN COURSE 1"); 
					userSession.setModuleSysName(tmpStr[tmpStr.length - 1]);
					userSession.setEventName("chapter");
					eventName = "chapter";
					//moduleSysName = tmpStr[tmpStr.length - 1];
					psChapter.setString(1, courseName);
					psChapter.setString(2, userSession.getModuleSysName());
					//psChapter.setString(2, moduleSysName);
					rs = psChapter.executeQuery();
				//	rs.next();
					//userSession.setModuleType("chapter");
					
					userSession.setChapterSysName(tmpStr[tmpStr.length - 1]);
					if (rs.next()) {
						userSession.setChapterTitle(rs.getString(1));
					}
				}
			 }
			 if (tmpStr.length > 2) {
				 if(tmpStr[tmpStr.length - 3].equals("courseware")){
				//	System.out.println("IN COURSE 2 ");
					userSession.setModuleSysName(tmpStr[tmpStr.length - 1]);
					eventName = "session";
					userSession.setEventName("session");
					moduleSysName = tmpStr[tmpStr.length - 1];
				//	System.out.println("Session " + userSession.getModuleSysName());
					//userSession.setModuleType("session");
					psChapterSess.setString(1, userSession.getModuleSysName());
					//psChapterSess.setString(1, moduleSysName);
					psChapterSess.setString(2, courseName);
					rsChapterSess = psChapterSess.executeQuery();
				
					
					
					if (rsChapterSess.next()) {
						userSession.setChapterTitle(rsChapterSess.getString(1));
						userSession.setSessTitle(rsChapterSess.getString(2));
						userSession.setChapterSysName(rsChapterSess.getString(3));
					}
					userSession.setSessSysName(userSession.getModuleSysName());
					
				}
			 }
			 if (tmpStr.length > 4) {
				 if (tmpStr[tmpStr.length - 5].equals("courses") ) {
				//	System.out.println("IN COURSE 3 ");
					userSession.setEventType("courses");
					userSession.setModuleSysName(tmpStr[tmpStr.length - 1]);
					userSession.setEventName("courses_chapter");
					//eventName = "courses_chapter";
					//moduleSysName = tmpStr[tmpStr.length - 1];
					psChapter.setString(1, courseName);
					psChapter.setString(2, userSession.getModuleSysName());
					//psChapter.setString(2, moduleSysName);
					rs = psChapter.executeQuery();
			
					//userSession.setModuleType("chapter");
					userSession.setChapterSysName(tmpStr[tmpStr.length - 1]);
					if (rs.next()) {
					userSession.setChapterTitle(rs.getString(1));
					}
				}
			 }
			 if (tmpStr.length > 3) {
				if (tmpStr[tmpStr.length - 4].equals("courses")	) {
					
					userSession.setEventType("courses");
					userSession.setModuleSysName("courses");
					userSession.setEventName("courses_access");
					//eventName = "courses_access";
					userSession.setChapterTitle("courses");
				}
			 }
			 
			 if (tmpStr.length > 2) {
				 if (tmpStr[tmpStr.length - 3].equals("courses")) {
						userSession.setEventType("courses");
						userSession.setEventName("courses_access");
						userSession.setCourseName(tmpStr[tmpStr.length - 1]);
						userSession.setOrgName(tmpStr[tmpStr.length - 2]);
						//eventName = "courses_access";
					}
			 }
			 
			 if (tmpStr.length > 1) {
				 if (tmpStr[tmpStr.length - 2].equals("courses")) {
						userSession.setEventType("courses");
						userSession.setEventName("courses_access");
						userSession.setOrgName(tmpStr[tmpStr.length - 1]);
						//eventName = "courses_access";
					}
			 }
			 
			 
				
				
				else {
				//	System.out.println("IN PROCESS COURSES NO MATCH eventTypeSt " + eventTypeStr);
				}
			//	System.out.println("eventName " + eventName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void processOthers() throws ParseException{
		/*
		 * ==list-staff==
		 * "server", "event_type": "list-staff; "event": {},
		 * ==dump-graded-assignments-config==
		 * "server", "event_type": "dump-graded-assignments-config", "event": {}
		 * ==edx.course.enrollment.activated==
		 * server :"event_type": "edx.course.enrollment.activated", "event": {"course_id": "edX/DemoX/Demo_Course", "user_id": 1, "mode": "honor"}
		 * 		next rec same event
		 * 		"event": {"course_id": "edX/DemoX/Demo_Course", "user_id": 2, "mode": "audit"}
		 *  server :"event_type": "edx.course.enrollment.activated" "event": {"course_id": "edX/DemoX/Demo_Course", "user_id": 3, "mode": "verified"}
		 * ==edx.course.enrollment.ifmode_changed==
		 * "server" "event_type": "edx.course.enrollment.mode_changed", "event": {"course_id": "edX/DemoX/Demo_Course", "user_id": 2, "mode": "audit"},
		 * 		"name": "edx.course.enrollment.mode_changed" in main json string, "username": "", context.command  "./manage.py create_user"
		 *  server : "event_type": "edx.course.enrollment.mode_changed", "event": {"course_id": "edX/DemoX/Demo_Course", "user_id": 3, "mode": "verified"},
		 *  =="/"==
		 *  Server: "event_type": "/", "event": "{\"POST\": {}, \"GET\": {}}""username": """user_id": "", "org_id": "", "course_id": "", "path": "/"
		 *  =="/jsi18n/"==
		 *  "server", "event_type": "/jsi18n/", "context": {"user_id": "", "org_id": "",event": "{\"POST\": {}, \"GET\": {}}",
		 *  =="/i18n.js"==
		 *  server", "event_type": "/i18n.js", "context": {"user_id": "", "org_id": "", "course_id": "","event": "{\"POST\": {}, \"GET\": {}}"
		 *  ==change_enrollment==
		 *  "server", "event_type": "/change_enrollment" "event": "{\"POST\": {\"course_id\": [\"edX/DemoX/Demo_Course\"], \"enrollment_action\": [\"enroll\"]}, \"GET\": {}}"
		 *  ==/register==
		 *  "server", "event_type": "/register" "event": "{\"POST\": {}, \"GET\": {\"course_id\": [\"edX/DemoX/Demo_Course\"], \"enrollment_action\": [\"enroll\"]}}"
		 *  ==/create_account==
		 *  server", "event_type": "/create_account","event": "{\"POST\": {\"username\": [\"sukla\"], \"name\": [\"Sukla Nag\"], \"gender\": [\"f\"], \"year_of_birth\": [\"1950\"], \"level_of_education\": [\"m\"], \"goals\": [\"Test\"], \"terms_of_service\": [\"true\"], \"csrfmiddlewaretoken\": [\"V2Spq9PUPs1TaL0p4A6X7dvTWvUTaOns\"], \"password\": \"********\", \"mailing_address\": [\"Powai\"], \"email\": [\"sukla80@yahoo.co.in\"]}, \"GET\": {}}
		 *  "server", "event_type": "/create_account "event": "{\"POST\": {\"username\": [\"sukla\"], \"name\": [\"Sukla Nag\"], \"gender\": [\"f\"], \"year_of_birth\": [\"1950\"], \"level_of_education\": [\"m\"], \"goals\": [\"Test\"], \"honor_code\": [\"true\"], \"terms_of_service\": [\"true\"], \"csrfmiddlewaretoken\": [\"V2Spq9PUPs1TaL0p4A6X7dvTWvUTaOns\"], \"password\": \"********\", \"mailing_address\": [\"Powai\"], \"email\": [\"sukla80@yahoo.co.in\"]}, \"GET\": {}}
		 *  /dashboard
		 *  server", "event_type": "/dashboard", "event": "{\"POST\": {}, \"GET\": {\"signin\": [\"initial\"]}}"
		 *  			"username": "sukla"
		 *  ==/logout"
		 *  server", "event_type": "/logout; "event": "{\"POST\": {}, \"GET\": {}}"
		 *  ==/admin/
		 *  "server", "event_type": "/admin/" "event": "{\"POST\": {}, \"GET\": {}}"
		 */
		JSONObject tmpJson;
		String[] tmpStr;
		ResultSet rs;
		if(eventTypeStr.equals("/admin/")){
			eventType = "admin";
			eventName = "admin";	

/* changed eventtype to instructor from admin in next two ---------*/
		} else if(eventTypeStr.equals("list-staff")) {
			eventType = "instructor";
			eventName = "list-staff";

/* changed eventtype to instructor from admin in next two ---------*/
		} else if (eventTypeStr.equals("dump-graded-assignments-config")) {
			eventType = "instructor";
			eventName = "dump-graded-assignments-config";
		}  else if ((eventTypeStr.equals("edx.course.enrollment.mode_changed")) || 
				(eventTypeStr.equals("edx.course.enrollment.activated")) ||
				(eventTypeStr.equals("edx.course.enrollment.deactivated"))){
			try {
				eventJson = (JSONObject) new JSONParser().parse(eventStr);
				eventName = eventTypeStr;
				eventType = "enrollment";
				userSession.setEnrolmentMode(eventJson.get("mode").toString());
				
				if (eventJson.containsKey("user_id")) {
					String user_id = eventJson.get("user_id")
							.toString().trim();
					if (user_id.length() != 0) {
						userSession.setLmsUserId(Long.parseLong(user_id));
					}
				}
				
				courseName = (String) eventJson.get("course_id");
				if (courseName.length() != 0) {
					tmpStr = courseName.split("/");
					courseName = tmpStr[1];
					userSession.setCourseName(courseName);
					userSession.setCourseRun(tmpStr[2]);
				}
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (eventTypeStr.equals("/accounts/login")){
			try {
				eventJson = (JSONObject) new JSONParser().parse(eventStr);
				eventName = eventTypeStr.substring(eventTypeStr.lastIndexOf("/") + 1);
				//System.out.println("eventName " + eventName);
				eventType = "navigation";
				tmpJson = (JSONObject) new JSONParser().parse(
						((JSONObject) new JSONParser().parse(eventJson.get("GET").toString())).toJSONString());
				tmpStr1 = tmpJson.get("next").toString();
				tmpStr = tmpStr1.substring(3,tmpStr1.lastIndexOf("\"]")).split("/");
				if(tmpJson.toString().contains("courseware")){
					if(tmpStr[tmpStr.length - 1].contains("courseware")){
						userSession.setModuleSysName("courseware");
						userSession.setChapterTitle("courseware");
						//userSession.setModuleType("courseware");
					}else if(tmpStr[tmpStr.length - 2].contains("courseware")){
						userSession.setModuleSysName(tmpStr[tmpStr.length - 1].substring(0,tmpStr[tmpStr.length - 1].length() -1));
						psChapter.setString(1, courseName);
						psChapter.setString(2, userSession.getModuleSysName());
						rs = psChapter.executeQuery();
						if(rs.next()){
							userSession.setChapterTitle(rs.getString(1));
						}
						//userSession.setModuleType("chapter");
						userSession.setChapterSysName(tmpStr[tmpStr.length - 1]);
						
					}
					else if(tmpStr[tmpStr.length - 3].contains("courseware")){
						userSession.setModuleSysName(tmpStr[tmpStr.length - 1].substring(0,tmpStr[tmpStr.length - 1].length() -1));
						//System.out.println("Session " + userSession.getModuleSysName());
						//userSession.setModuleType("session");
						psChapterSess.setString(1, userSession.getModuleSysName());
						psChapterSess.setString(2, courseName);
						rsChapterSess = psChapterSess.executeQuery();
						
						if(rsChapterSess.next()) {
							userSession.setChapterTitle(rsChapterSess.getString(1));
							userSession.setModuleTitle(rsChapterSess.getString(2));
							userSession.setChapterSysName(rsChapterSess.getString(3));
						}
						
					}
				} else {
					userSession.setChapterTitle(tmpStr[tmpStr.length -1]);
				}
			//	System.out.println("tuserSession.setModuleSysName " + userSession.getModuleSysName());
			//	System.out.println("INFO title " + userSession.getChapterTitle() + " Module " +
				//		userSession.getModuleTitle() + " chapt " + userSession.getChapterSysName());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (eventTypeStr.equals("/")){
			eventType="navigation";
			eventName = "/";		
		}else if (eventTypeStr.equals("/jsi18n/")){
			eventType="navigation";
			eventName = "jsi18n";
			
		}else if (eventTypeStr.equals("/i18n.js")){
			eventType="navigation";
			eventName = "i18n";					
		}else if (eventTypeStr.equals("/dashboard")){
			eventType = "navigation";
			eventName = "dashboard";					
		}else if (eventTypeStr.equals("/change_enrollment")){
			eventType="enrollment";
			eventName = "change_enrollment";		
		} else if (eventTypeStr.equals("edx.forum.searched")){
			eventType="discussion";
			eventName = "forum_searched";
			eventJson = (JSONObject) new JSONParser().parse(eventStr);
			
			
			
			if ((eventStr.contains("query")) && (eventStr.contains("total_results"))) {
				String queryText = eventJson.get("query").toString();
				Integer noOfResults = Integer.parseInt(eventJson.get("total_results").toString());
				userSession.setQueryText(queryText);
				userSession.setNoOfResults(noOfResults);
			}
			
			
		}else if (eventTypeStr.contains("/notification_prefs/status/")){
			eventType="admin";
			eventName = "pref_status";
			
		}else if (eventTypeStr.equals("/register")){
			eventType="enrollment";
			eventName = "register";
			
		}else if (eventTypeStr.contains("/activate/")){
			eventType="enrollment";
			eventName = "activate";
			
		}  else if (eventTypeStr.equals("/create_account")){
			//event.post.username
	
			eventType="enrollment";
			eventName = "create_account";
		
			
			int i, j;
			
			String username = "";
		
			String events[] = eventStr.split(",");
		
			
			for (i = 0; i < events.length; i++) {
				
				String shorteve[] = events[i].split(":");
				
				if (shorteve.length > 0) {
					if (shorteve[0].contains("aadhar_id")) {
						if (shorteve.length > 1) {
						if (shorteve[1].length() > 10) {
							String aadharId = shorteve[1].substring(3, shorteve[1].length()-2);
							userSession.setCourseName(aadharId);
						}
						}
				
					}
				}
				
				if (shorteve.length > 0) {
					if (shorteve[0].contains("level_of_education")) {
						if (shorteve[1].length() > 4) {
							String eduLevel = shorteve[1].substring(3, shorteve[1].length()-2);
							userSession.setEduLevel(eduLevel);
						}
				
					}
				}
				
				if (shorteve.length > 0) {
					if (shorteve[0].contains("level_of_education")) {
						if (shorteve[1].length() > 4) {
							String eduLevel = shorteve[1].substring(3, shorteve[1].length()-2);
							userSession.setEduLevel(eduLevel);
						}
				
					}
				}
				
				if (shorteve.length > 0) {
					if (shorteve[0].contains("gender")) {
						if (shorteve[1].length() > 4) {
							String gender = shorteve[1].substring(3, shorteve[1].length()-2);
							userSession.setGender(gender);
						}
					}
				}
				
				if (shorteve.length > 1)
				if (shorteve[1].contains("username")) {
				
					if (shorteve[2].length() > 6)
						username = shorteve[2].substring(3, shorteve[2].length()-2);
						userSession.setUserName(username);
					if (username.equals("")) {
						
					}
					else {
					
						try {
							psUserSession.setString(1,username);
							rs = psUserSession.executeQuery();
							
							if (rs.next()) {
							
								userSession.setLmsUserId(rs.getLong(1));
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				
				
			}
					
			}
			
		
			userSession.setEventName("create_account");
			userSession.setEventType("enrollment");
			
			
			
			
			//userSession.setUserName(event.post.username);
		} 
		else if (eventTypeStr.equals("/how_it_works")){
			eventType="navigation";
			eventName = "howItWorks";
			
		}else if (eventTypeStr.equals("/calculate")){
			eventType="navigation";
			eventName = "calculate";
			
		}else if (eventTypeStr.equals("/password_reset/")){
			eventType="accounts";
			eventName = "passwordReset";
			
		}else if (eventTypeStr.contains("/password_reset_confirm/")){
			eventType="accounts";
			eventName = "passwordResetConfirm";
			
		}else if (eventTypeStr.equals("/logout")){
			eventType="navigation";
			eventName = "logout";
			
		}else if (eventTypeStr.contains("/city_ajax")){
			eventType="navigation";
			eventName = "city";
			
		} 
		else {
		//	System.out.println("OTHER eventTypeStr " + eventTypeStr );
		//	System.out.println("OTHER eventStr " + eventStr );
			// /notification_prefs/status/ 2504
			// /register  2487
			// /activate/984e816ccc2140569897c4a9dfb71cdb 2271
			// /create_account 2278, 2301
			//  /how_it_works 2275
			// /calculate 2320, 2324
			// /password_reset/ 2325, 2366
			// /password_reset_confirm/lck-3zj-f1fdee807001483849c6/
			// /logout 2337
			// /city_ajax 2406
		}
		userSession.setEventName(eventName);
		userSession.setEventType(eventType);
			
	}
	private void processProblem(){
	
		/* problem_get
		 * Server : event_Type :"/courses/IITBombayX/ME209x/2T2014/xblock/i4x:;_;_IITBombayX;_ME209x;_problem;_79e01498049b4b3ca33dcf0207482cb9/handler/xmodule_handler/problem_get"
		 *          "event": "{\"POST\": {}, \"GET\": {}}",
		 * problem_save
		 * browser : "event_type": "problem_save", event : "event": "\"input_i4x-IITBombayX-ME209x-problem-0b229e942cf443bbb1125e7dea061320_2_1=choice_1\"",
		 * Server: "event_type": contains problem_save", "event": "{\"POST\": {\"input_i4x-IITBombayX-ME209x-problem-f859acfd3afa4270819ac05eed62bc3c_2_1\": [\"choice_1\"]}, \"GET\": {}}"
		 *          context."module": {"display_name": "Problem 6"},
		 * problem_check
		 * browser: "event_type": "problem_check","event": "\"input_i4x-IITBombayX-CS101_1x-problem-fb36f0c1fdb84e5ab32a5d7306ae8c5f_2_1=choice_1&input_i4x-IITBombayX-CS101_1x-problem-fb36f0c1fdb84e5ab32a5d7306ae8c5f_3_1=choice_3&input_i4x-IITBombayX-CS101_1x-problem-fb36f0c1fdb84e5ab32a5d7306ae8c5f_4_1=choice_0&input_i4x-IITBombayX-CS101_1x-problem-fb36f0c1fdb84e5ab32a5d7306ae8c5f_5_1=choice_1&input_i4x-IITBombayX-CS101_1x-problem-fb36f0c1fdb84e5ab32a5d7306ae8c5f_6_1=choice_3&input_i4x-IITBombayX-CS101_1x-problem-fb36f0c1fdb84e5ab32a5d7306ae8c5f_7_1%5B%5D=choice_3&input_i4x-IITBombayX-CS101_1x-problem-fb36f0c1fdb84e5ab32a5d7306ae8c5f_8_1%5B%5D=choice_0&input_i4x-IITBombayX-CS101_1x-problem-fb36f0c1fdb84e5ab32a5d7306ae8c5f_8_1%5B%5D=choice_1&input_i4x-IITBombayX-CS101_1x-problem-fb36f0c1fdb84e5ab32a5d7306ae8c5f_8_1%5B%5D=choice_3\"",
		 * Server : event_type": "problem_check", event has submission, grade, success, correct_map, state, answers, "attempts","max_grade",problem_id
		 *          context."module": {"display_name": "Problem 6"},
		 * problem_check_fail
		 * Not Found
		 * 
		 * problem_reset
		 * "session": "5d20d5da1cde4c9eb02e78f9a7dee2e8", (context.session
		 * Browser : "event_type": "problem2reset", "event": "\"input_i4x-IITBombayX-CS101_1x-problem-8ea9b0e7ee46492795207a64752477b2_2_1=48\"",
		 * 
		 * problem_rescore
		 * Not Found
		 * 
		 * reset_problem
		 * Not Found
		 * 
		 * reset_problem_fail
		 * 
		 * save_problem_success
		 * Server : "event_type": "save_problem_success", event has "state":,(JSON), stateJson has "student_answers": {}, "seed": 1,
            		"done": null, "correct_map": {}, "input_state": {"i4x-IITBombayX-ME209x-problem-0b229e942cf443bbb1125e7dea061320_2_1": {}},
            		"problem_id": "i4x://IITBombayX/ME209x/problem/0b229e942cf443bbb1125e7dea061320","answers": {"i4x-IITBombayX-ME209x-problem-0b229e942cf443bbb1125e7dea061320_2_1": "choice_1"
        			context string has module with display_name for problem
		 * 
		 * problem_graded
		 * Browser :"event_type": "problem_graded", event : whole long html string, search for "-problem-" to get the quizsysname
		 * Server : No instance Found
		 * showanswer
		 * server : "event_type": "showanswer", event : "event": {"problem_id": "i4x://IITBombayX/CS101.1x/problem/fb36f0c1fdb84e5ab32a5d7306ae8c5f"},
		 *          context."module": {"display_name": "Problem 6"},
		 * browser : no instance
		 * problem_show
		 * browser :"event_type": "problem_show","event": "{\"problem\":\"i4x://IITBombayX/CS101.1x/problem/fb36f0c1fdb84e5ab32a5d7306ae8c5f\"}"
		 * server : "event_type": "/courses/IITBombayX/CS101.1x/2T2014/xblock/i4x:;_;_IITBombayX;_CS101.1x;_problem;_fb36f0c1fdb84e5ab32a5d7306ae8c5f/handler/xmodule_handler/problem_show",
		 *          "event": "{\"POST\": {}, \"GET\": {}}",
		 * show_answer
		 * Instance Not found
		 */
		JSONObject stateJson = null, submissionJson = null, correctJson = null, answerJson = null, otherJson = null;
		
		String[] inputkeys;
		ResultSet rs;
		Set KeySet = null;
	//	System.out.println("Event Type:\t\t  " + eventType + " eventName " + eventName);
		try {
		//	System.out.println("eventJson " + eventJson);
			// Get all for problems submission, grade, success, correct_map, state, answers, "attempts","max_grade",problem_id
			if(eventName.equals("problem_get")){
				userSession.setModuleSysName(eventTypeStr.substring(eventTypeStr.indexOf("_problem;_") + 10, eventTypeStr.indexOf("/handler")));
				//moduleSysName = eventTypeStr.substring(eventTypeStr.indexOf("_problem;_") + 10, eventTypeStr.indexOf("/handler"));
			//	System.out.println("PROBLEMGET :: userSession.getModuleSysName(0 " + userSession.getModuleSysName());
			} else if(eventName.equals("problem_save")){
				if(eventSource.equals("browser")){
					//tmpStr1 = eventStr.substring(eventStr.indexOf("_") + 1);
					if(eventStr.indexOf("-problem-") > 0){
					tmpStr1 = eventStr.substring(eventStr.indexOf("-problem-") + 9);
				//	System.out.println("userSession.getModuleSysName(  tmpStr1 " + tmpStr1);
					userSession.setModuleSysName(tmpStr1.substring(0,tmpStr1.indexOf("_")));
			//		System.out.println("userSession.getModuleSysName( " + userSession.getModuleSysName());
					//userSession.setModuleSysName(tmpStr1.substring(0,tmpStr1.indexOf("_")));
					//moduleSysName = tmpStr1.substring(0,tmpStr1.indexOf("_"));
					}
				}else {
					userSession.setModuleSysName(eventTypeStr.substring(eventTypeStr.indexOf("_problem;_") + 10, eventTypeStr.indexOf("/handler")));
					//moduleSysName = eventTypeStr.substring(eventTypeStr.indexOf("_problem;_") + 10, eventTypeStr.indexOf("/handler"));
				}
			} else if((eventName.equals("problem_check_fail")) || (eventName.equals("problem_check")) ){
				if((eventSource.equals("browser")) && (eventName.equals("problem_check"))){
				//	System.out.println("eventStr " + eventStr);
					if(eventStr.indexOf("-problem-") > 0){
						tmpStr1 = eventStr.substring(eventStr.indexOf("-problem-") + 9);
						userSession.setModuleSysName(tmpStr1.substring(0,tmpStr1.indexOf("_")));
						//moduleSysName = tmpStr1.substring(0,tmpStr1.indexOf("_"));
						if(tmpStr1.length() > tmpStr1.indexOf("=") + 3)
							userSession.setAnswerChoice(tmpStr1.substring(tmpStr1.indexOf("=") + 1, tmpStr1.length() - 2));
					}
					else {
						inputkeys = ((String)mainRecJson.get("page")).split("/");
						if(mainRecJson.get("page").toString().contains("courseware")){
						
						if(inputkeys[inputkeys.length - 1].equals("courseware")){
							userSession.setModuleSysName("courseware");
							userSession.setChapterTitle("courseware");
							//userSession.setModuleType("courseware");
						}else if(inputkeys[inputkeys.length - 2].equals("courseware")){
							userSession.setModuleSysName(inputkeys[inputkeys.length - 1]);
							//moduleSysName = inputkeys[inputkeys.length - 1];
							psChapter.setString(1, courseName);
							psChapter.setString(2, userSession.getModuleSysName());
							rs = psChapter.executeQuery();
							//rs.next();
							//userSession.setModuleType("chapter");
							userSession.setChapterSysName(inputkeys[inputkeys.length - 1]);
							if (rs.next()) {
								userSession.setChapterTitle(rs.getString(1));
							}
						}
						else if(inputkeys[inputkeys.length - 3].equals("courseware")) {
							userSession.setModuleSysName(inputkeys[inputkeys.length - 1]);
							//moduleSysName = inputkeys[inputkeys.length - 1];
/*							System.out.println("Session " + moduleSysName);
							//userSession.setModuleType("session");
							psChapterSess.setString(1, userSession.getModuleSysName());
							psChapterSess.setString(2, courseName);
							rsChapterSess = psChapterSess.executeQuery();
							rsChapterSess.next();
							userSession.setChapterTitle(rsChapterSess.getString(1));
							userSession.setModuleTitle(rsChapterSess.getString(2));
							userSession.setChapterSysName(rsChapterSess.getString(3));*/
						}
						else if(inputkeys[inputkeys.length - 4].equals("courseware")) {
							userSession.setModuleSysName(inputkeys[inputkeys.length - 2]);
							/*moduleSysName = inputkeys[inputkeys.length - 2];
							System.out.println("Session " + moduleSysName);
							//userSession.setModuleType("session");
							psChapterSess.setString(1, userSession.getModuleSysName());
							psChapterSess.setString(2, courseName);
							rsChapterSess = psChapterSess.executeQuery();
							rsChapterSess.next();
							userSession.setChapterTitle(rsChapterSess.getString(1));
							userSession.setModuleTitle(rsChapterSess.getString(2));
							userSession.setChapterSysName(rsChapterSess.getString(3));*/
						}
						}
					}
				//	System.out.println("ModuleSysName " + userSession.getModuleSysName());
				}else {
					
					eventJson = (JSONObject) new JSONParser().parse(eventStr);
					
					if ((!eventTypeStr.equals("problem_check")) && (!eventTypeStr.equals("problem_check_fail"))) {
				//		System.out.println("eventTypeStr " + eventTypeStr);
						if(eventTypeStr.length() > 0){
							userSession.setModuleSysName(eventTypeStr.substring(eventTypeStr.indexOf("_problem;_") + 10, eventTypeStr.indexOf("/handler")));
							//moduleSysName = eventTypeStr.substring(eventTypeStr.indexOf("_problem;_") + 10, eventTypeStr.indexOf("/handler"));
						//inputkeys = eventJson.get("problem_id").toString().split("/");
						//userSession.setModuleSysName(inputkeys[inputkeys.length-1]);
							answerJson = (JSONObject)eventJson.get("POST");
						}
					} else {
						// moduleSysName
						tmpStr1 = eventJson.get("problem_id").toString();
						userSession.setModuleSysName(tmpStr1.substring(tmpStr1.lastIndexOf("/")+1));
						// done
						stateJson = (JSONObject) eventJson.get("state");
						if(stateJson.get("done") == null)
							userSession.setDone(null);
						else
							userSession.setDone(stateJson.get("done").toString());
						// correctness, hints
						if(eventTypeStr.equals("problem_check_fail"))
							correctJson = (JSONObject) stateJson.get("correct_map");
						else
							correctJson = (JSONObject) eventJson.get("correct_map");
						KeySet = correctJson.keySet();					
						inputkeys = (String[]) KeySet.toArray(new String[KeySet.size()]);
						tmpStr1 = ""; tmpStr2="";tmpStr3="";
						for (int i = 0; i < inputkeys.length; i++) {
						//	System.out.println("correct_map KEYS ARE " + correctJson.get(inputkeys[i]));
							otherJson = (JSONObject) correctJson.get(inputkeys[i]);
							if(i==0){
								tmpStr1 += otherJson.get("hint").toString();
								if(otherJson.get("hintmode") == null)
									tmpStr2 += null;
								else
									tmpStr2 += otherJson.get("hintmode").toString();
								tmpStr3 += otherJson.get("correctness").toString();
							}
							else {
								tmpStr1 += "," + otherJson.get("hint").toString();
								if(otherJson.get("hintmode") == null)
									tmpStr2 += ",null";
								else
									tmpStr2 += "," + otherJson.get("hintmode").toString();
								tmpStr3 += "," + otherJson.get("correctness").toString();
							} 
						}
						userSession.setHintAvailable(tmpStr1);
						
						userSession.setHintUsed(tmpStr2);
						
						userSession.setSuccess(tmpStr3);
								//attempts, grade, maxgrade
						if(eventTypeStr.equals("problem_check")){
							userSession.setCurrGrade(Float.parseFloat(eventJson.get("grade").toString()));
							userSession.setSuccess(eventJson.get("success").toString());
							userSession.setAttempts(Integer.parseInt(eventJson.get("attempts").toString()));
							userSession.setMaxGrade(Float.parseFloat(eventJson.get("max_grade").toString()));
						}
						//answers
						answerJson = (JSONObject)eventJson.get("answers");
					}
					KeySet = answerJson.keySet();					
					inputkeys = (String[]) KeySet.toArray(new String[KeySet.size()]);
					tmpStr1 ="";tmpStr2="";
					for (int i = 0; i < inputkeys.length; i++) {
						tmpStr1 = answerJson.get(inputkeys[i]).toString();
						if(tmpStr1.contains("choice"))
							tmpStr1 = tmpStr1.substring(2,tmpStr1.length() - 2);
						//System.out.println("tmpStr1 " + tmpStr1);
						if(i==0)
							tmpStr2 += tmpStr1;
						else
							tmpStr2 += "," + tmpStr1;
					}
					
					userSession.setAnswerChoice(tmpStr2);
				}
			} else if(eventName.equals("problem_reset")){
				
			} else if(eventName.equals("problem_rescore")){
				
			}else if(eventName.equals("reset_problem_fail")){
				
			} else if(eventName.equals("reset_problem")){
	
			}else if(eventName.equals("save_problem_success")){
				
				eventJson = (JSONObject) new JSONParser().parse(eventStr);
				// answers, Takes care of CS101.x
				answerJson = (JSONObject) eventJson.get("answers");
				KeySet = answerJson.keySet();					
				inputkeys = (String[]) KeySet.toArray(new String[KeySet.size()]);
				String answers = "";
				for (int i = 0; i < inputkeys.length; i++) {
				//	System.out.println("POST KEYS ARE " + answerJson.get(inputkeys[i]));
					if(i==0)
						answers+= answerJson.get(inputkeys[i]).toString();
					else
						answers+= "," + answerJson.get(inputkeys[i]).toString();
				}
				userSession.setAnswerChoice(answers);
				// Done
				stateJson = (JSONObject) eventJson.get("state");
				if(stateJson.get("done") == null)
					userSession.setDone(null);
				else
					userSession.setDone(stateJson.get("done").toString());
				// correctness, hint, hintavailable
				correctJson = (JSONObject) stateJson.get("correct_map");
				KeySet = correctJson.keySet();					
				inputkeys = (String[]) KeySet.toArray(new String[KeySet.size()]);
				tmpStr1 = ""; tmpStr2="";tmpStr3="";
				for (int i = 0; i < inputkeys.length; i++) {
				//	System.out.println("correct_map KEYS ARE " + answerJson.get(inputkeys[i]));
					otherJson = (JSONObject) correctJson.get(inputkeys[i]);
					if(i==0){
						tmpStr1 += otherJson.get("hint").toString();
						if(otherJson.get("hintmode") == null)
							tmpStr2 += null;
						else
							tmpStr2 += otherJson.get("hintmode").toString();
						tmpStr3 += otherJson.get("correctness").toString();
					}
					else {
						tmpStr1 += "," + otherJson.get("hint").toString();
						if(otherJson.get("hintmode") == null)
							tmpStr2 += ",null";
						else
							tmpStr2 += "," + otherJson.get("hintmode").toString();
							
						tmpStr3 += "," + otherJson.get("correctness").toString();
					} 
				//	System.out.println("CORRECTNESS " + tmpStr3);
				}
				userSession.setHintAvailable(tmpStr1);
				userSession.setHintUsed(tmpStr2);
				userSession.setSuccess(tmpStr3);
				// moduleSysName
				tmpStr1 = eventJson.get("problem_id").toString();
				userSession.setModuleSysName(tmpStr1.substring(tmpStr1.lastIndexOf("/")+1));
				//
			}else if(eventName.equals("problem_graded")){
				tmpStr1 = eventStr.substring(eventStr.indexOf("-problem-") + 9);
				userSession.setModuleSysName(tmpStr1.substring(0,tmpStr1.indexOf("_")));
			}else if(eventName.equals("problem_show")){
				if(eventSource.equals("server"))
					userSession.setModuleSysName(eventTypeStr.substring(eventTypeStr.indexOf("_problem;_") + 10, eventTypeStr.indexOf("/handler")));
				else{
					eventJson = (JSONObject) new JSONParser().parse(eventStr);
					inputkeys = eventJson.get("problem").toString().split("/");
					userSession.setModuleSysName(inputkeys[inputkeys.length - 1]);
				}
			} else if(eventName.equals("showanswer")){
				inputkeys = eventJson.get("problem_id").toString().split("/");
				userSession.setModuleSysName(inputkeys[inputkeys.length - 1]);
			}
		//	System.out.println(" BEFORE COMMON GET TITLES");
		//	System.out.println("userSession.getModuleSysName() " + userSession.getModuleSysName() + " courseName " + courseName);
			
			psProblem.setString(1,userSession.getModuleSysName());
			psProblem.setString(2,courseName);
			rsPS = psProblem.executeQuery();
			if(rsPS.next()) {
				
				userSession.setChapterTitle(rsPS.getString(1));
			//	userSession.setChapterSysName(rsPS.getString(2));
				userSession.setModuleTitle(rsPS.getString(2));
			//	userSession.setSessTitle(rsPS.getString(4));
			//	userSession.setSessSysName(rsPS.getString(5));
			}
			if(mainRecJson.get("metadata") != null ){
				JSONObject metadata = (JSONObject)mainRecJson.get("metadata");
				if(metadata.get("max_attempts") != null)
					userSession.setMaxNoAttempts(Integer.parseInt(metadata.get("max_attempts").toString()));
				if(metadata.get("display_name") != null)
						userSession.setModuleTitle(metadata.get("display_name").toString());
				if(metadata.get("weight") != null)
					userSession.setMaxGrade(Float.parseFloat(metadata.get("weight").toString()));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			/*
	submissionJson = (JSONObject) eventJson.get("submission");
			
			
			answerJson = (JSONObject) eventJson.get("answers");
			
			success = (String) eventJson.get("success");
			grade = Float.parseFloat((String) eventJson.get("grade"));
			maxGrade = Float.parseFloat((String) eventJson.get("max_grade"));
			attempts = Integer.parseInt((String) eventJson.get("attempts"));
			quizSysName = (String) eventJson.get("problem_id");
			quizSysName = quizSysName.substring(quizSysName.lastIndexOf("/") + 1);
			
			
			done = stateJson.get("done").toString();
			inputJson = (JSONObject) stateJson.get("input_state");// eventJson
			if (inputJson instanceof JSONObject) {
				KeySet = inputJson.keySet();
				System.out.println("keyset==" + KeySet);
				String[] inputkeys = (String[]) KeySet.toArray(new String[KeySet.size()]);
				for (int i = 0; i < inputkeys.length; i++) {
					key = inputkeys[i];
					if (eventName.contains("problem_check")) {
						correctJson = (JSONObject) eventJson.get("correct_map");
					} else {
						correctJson = (JSONObject) stateJson
								.get("correct_map");
						System.out.println("eventName="
								+ eventName);
					}
					JSONObject InsideCorrect = (JSONObject) correctJson
							.get(key);
					if (InsideCorrect instanceof JSONObject) {
						hintAvailabilty = (String) InsideCorrect
								.get("hintmode");
						System.out
								.println("hintAvailabilty"
										+ hintAvailabilty);
						hintAvailable = (String) InsideCorrect
								.get("hint");
						System.out.println("hintAvailable"
								+ hintAvailable);
						// add other fields here as
						// required, such as npoints,msg etc
					}
			
										// contains the
										// "event" element
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}	
							try {
								String key = null;
								Set KeySet = null;
								eventJson = (JSONObject) new JSONParser()
										.parse(event);
								
								
										problemId = (eventJson
												.get("problem_id").toString());
										System.out.println("problemId"
												+ problemId);
										if ((eventName.contains("problem_show"))) {
											if (eventJson
													.containsKey("problem")) {
												problemId = (eventJson
														.get("problem")
														.toString());
											}
										}

										try {
											submissionJson = (JSONObject) eventJson
													.get("submission"); // submissionJson
																		// contains
																		// the
																		// "submission"
																		// element
											// if submission element is not null
											JSONObject InsideSubmission = (JSONObject) submissionJson
													.get(key);
											if (InsideSubmission instanceof JSONObject) {
												inputType = InsideSubmission
														.get("input_type")
														.toString();
												responseType = InsideSubmission
														.get("response_type")
														.toString();
											}
											attempts = (Integer
													.parseInt(eventJson.get(
															"attempts")
															.toString()));
											maxGrade = (Float
													.parseFloat(eventJson
															.get("max_grade")
															.toString()));
											grade = (Integer
													.parseInt(eventJson.get(
															"grade").toString()));
											success = eventJson
													.get("success").toString();
										}
			userSession.setAttempts(attempts);
		userSession.setHintAvailable(hintAvailable);
		userSession.setHintUsed(hintUsed);
		userSession.setMaxNoAttempts(maxNoAttempts);
		userSession.setMaxGrade(rs.getDouble(8));
		
		userSession.setOldGrade(oldGrade);
		
		userSession.setCurrGrade(currGrade);*/
	}
	private void se(){
		/*
		 * ==/courses/edX/DemoX/Demo_Course/about"==
		 * "server" event_type": "/courses/edX/DemoX/Demo_Course/about"; "event": "{\"POST\": {}, \"GET\": {}}"
		 * ==/courses==
		 * server", "event_type": "/courses "event": "{\"POST\": {}, \"GET\": {}}"
		 * =="/courses/edX/DemoX/Demo_Course/info==
		 * "server", "event_type": "/courses/edX/DemoX/Demo_Course/info"; "event": "{\"POST\": {}, \"GET\": {}}"
		 * ==event_type": "/courses/edX/DemoX/Demo_Course/courseware/d8a6192ade314473a78242dfeedfbf5b/edx_introduction/"; "event": "{\"POST\": {}, \"GET\": {}}"
		 * =="/courses/edX/DemoX/Demo_Course/courseware"
		 * server", "event_type": "/courses/edX/DemoX/Demo_Course/coursewareDemoX/Demo_Course", 
		 * 		"path": "/courses/edX/DemoX/Demo_Course/courseware/d8a6192ade314473a78242dfeedfbf5b/edx_introduction/",
		 * 		"event": "{\"POST\": {}, \"GET\": {}}",
		 * ==/courses/edX/DemoX/Demo_Course/about"==
		 * "server" event_type": "/courses/edX/DemoX/Demo_Course/about"; "event": "{\"POST\": {}, \"GET\": {}}"
		 * ==/courses==
		 * server", "event_type": "/courses "event": "{\"POST\": {}, \"GET\": {}}"
		 * =="/courses/edX/DemoX/Demo_Course/info==
		 * "server", "event_type": "/courses/edX/DemoX/Demo_Course/info"; "event": "{\"POST\": {}, \"GET\": {}}"
		 * ==event_type": "/courses/edX/DemoX/Demo_Course/courseware/d8a6192ade314473a78242dfeedfbf5b/edx_introduction/"; "event": "{\"POST\": {}, \"GET\": {}}"
		 * =="/courses/edX/DemoX/Demo_Course/courseware"
		 * server", "event_type": "/courses/edX/DemoX/Demo_Course/coursewareDemoX/Demo_Course", 
		 * 		"path": "/courses/edX/DemoX/Demo_Course/courseware/d8a6192ade314473a78242dfeedfbf5b/edx_introduction/",
		 * 		"event": "{\"POST\": {}, \"GET\": {}}",
		 * ==page_close browser "event_type": "page_close" "event": ""
		 * ==seq_next browser "event_type": "seq_next" "event": "{\"old\":1,\"new\":2,\"id\":\"i4x://edX/DemoX/sequential/simulations\"}"
		 * ==goto_position "event_type": "/courses/edX/DemoX/Demo_Course/xblock/i4x:;_;_edX;_DemoX;_sequential;_simulations/handler/xmodule_handler/goto_position",
		 * 		"event": "{\"POST\": {\"position\": [\"2\"]}, \"GET\": {}}
		 * 		server", "event_type": "/courses/edX/DemoX/Demo_Course/xblock/i4x:;_;_edX;_DemoX;_sequential;_simulations/handler/xmodule_handler/goto_position
		 * =="seq_prev"browser "event_type": "seq_prev" "event": "{\"old\":2,\"new\":1,\"id\":\"i4x://edX/DemoX/sequential/simulations\"}",
		 * 		"event": "{\"POST\": {\"position\": [\"1\"]}, \"GET\": {}}",
		 * ==seq_goto"
		 * ==courses"
					
		 */
		/*userSession.setCurrPosition(currPosition);
		userSession.setNewPosition(newPosition);*/
	}
	private void processNavig(){
		String[] tmpStr;
		/*
		 * 
		 * ==page_close browser "event_type": "page_close" "event": "", session, page has page info
		 * ==seq_next browser "event_type": "seq_next" "event": "{\"old\":1,\"new\":2,\"id\":\"i4x://edX/DemoX/sequential/simulations\"}"
		 * ==goto_position "event_type": "/courses/edX/DemoX/Demo_Course/xblock/i4x:;_;_edX;_DemoX;_sequential;_simulations/handler/xmodule_handler/goto_position",
		 * 		"event": "{\"POST\": {\"position\": [\"2\"]}, \"GET\": {}}
		 * 		server", "event_type": "/courses/edX/DemoX/Demo_Course/xblock/i4x:;_;_edX;_DemoX;_sequential;_simulations/handler/xmodule_handler/goto_position
		 * =="seq_prev"browser "event_type": "seq_prev" "event": "{\"old\":2,\"new\":1,\"id\":\"i4x://edX/DemoX/sequential/simulations\"}",
		 * 		"event": "{\"POST\": {\"position\": [\"1\"]}, \"GET\": {}}",
		 * ==seq_goto"
		 * "event_type": "seq_goto","event": "{\"old\":3,\"new\":2,\"id\":\"i4x://IITBombayX/ME209x/sequential/4ace645c962b452d840894d2b88c7d03\"}"
					
		 */
		ResultSet rs;
		try {
			if(eventName.equals("page_close")){
				
				tmpStr = ((String)mainRecJson.get("page")).split("/");
				if(mainRecJson.get("page").toString().contains("courseware")){
				
				if(tmpStr[tmpStr.length - 1].equals("courseware")){
					userSession.setModuleSysName("courseware");
					//userSession.setModuleType("courseware");
					userSession.setChapterTitle("courseware");
				}else if(tmpStr[tmpStr.length - 2].equals("courseware")){
					userSession.setModuleSysName(tmpStr[tmpStr.length - 1]);
					//moduleSysName = tmpStr[tmpStr.length - 1];
					psChapter.setString(1, courseName);
					psChapter.setString(2, userSession.getModuleSysName());
					//psChapter.setString(2, moduleSysName);
					rs = psChapter.executeQuery();
				//	rs.next();
					//userSession.setModuleType("chapter");
					userSession.setChapterSysName(tmpStr[tmpStr.length - 1]);
					if (rs.next()) {
					userSession.setChapterTitle(rs.getString(1));
					}
				}
				else if(tmpStr[tmpStr.length - 3].equals("courseware")) {
					userSession.setModuleSysName(tmpStr[tmpStr.length - 1]);
					//moduleSysName = tmpStr[tmpStr.length - 1];
				//	System.out.println("Session " + userSession.getModuleSysName());
					//userSession.setModuleType("session");
					psChapterSess.setString(1, userSession.getModuleSysName());
					psChapterSess.setString(2, courseName);
					rsChapterSess = psChapterSess.executeQuery();
					if (rsChapterSess.next()) {
						userSession.setChapterTitle(rsChapterSess.getString(1));
						userSession.setModuleTitle(rsChapterSess.getString(2));
						userSession.setChapterSysName(rsChapterSess.getString(3));
					}
					}
				else if(tmpStr[tmpStr.length - 4].equals("courseware")) {
					userSession.setModuleSysName(tmpStr[tmpStr.length - 2]);
					//moduleSysName = tmpStr[tmpStr.length - 2];
				//	System.out.println("Session " + userSession.getModuleSysName());
				//	userSession.setModuleType("session");
					psChapterSess.setString(1, userSession.getModuleSysName());
					psChapterSess.setString(2, courseName);
					rsChapterSess = psChapterSess.executeQuery();
					if (rsChapterSess.next()) {
					userSession.setChapterTitle(rsChapterSess.getString(1));
					userSession.setModuleTitle(rsChapterSess.getString(2));
					userSession.setChapterSysName(rsChapterSess.getString(3));
					}
					}
				}
				//userSession.setChapterSysName(tmpStr[tmpStr.length -2]);
				//userSession.setModuleSysName(tmpStr[tmpStr.length -1]);
			}
			if((eventName.equals("seq_next")) || (eventName.equals("seq_prev")) || (eventName.equals("seq_goto"))){
				eventJson = (JSONObject) new JSONParser().parse(eventStr);
				userSession.setCurrPosition(Integer.parseInt(eventJson.get("new").toString()));
				if(eventJson.get("old") != null)
					userSession.setOldPosition(Integer.parseInt(eventJson.get("old").toString()));
				tmpStr = ((String)eventJson.get("id")).split("/");
			//	userSession.setModuleType(tmpStr[tmpStr.length -2]);
				userSession.setModuleSysName(tmpStr[tmpStr.length -1]);
			}
			if(eventName.equals("goto_position")){
				eventJson = (JSONObject) new JSONParser().parse(eventStr);
				//System.out.println("goto_position eventJson " + eventJson.toString());
				JSONObject postJson = (JSONObject) parserJson.parse(eventJson.get("POST").toString());
				userSession.setCurrPosition(Integer.parseInt(((postJson.get("position").toString()).replace("[\"","")).replace("\"]","")));
				//System.out.println("goto_position eventStr " + eventStr);
				tmpStr = eventTypeStr.split(";_");
				//userSession.setModuleType(tmpStr[tmpStr.length - 2]);
				userSession.setModuleSysName(tmpStr[tmpStr.length - 1].substring(0,tmpStr[tmpStr.length - 1].indexOf("/")));
				//moduleSysName = tmpStr[tmpStr.length - 1].substring(0,tmpStr[tmpStr.length - 1].indexOf("/"));
			}
		//	System.out.println("userSession.getModuleSysName() " +  moduleSysName);
		//	System.out.println("courseName " + courseName);

			psChapterSess.setString(1, userSession.getModuleSysName());
			psChapterSess.setString(2, courseName);
			rsChapterSess = psChapterSess.executeQuery();
			if(rsChapterSess.next()){
			userSession.setChapterTitle(rsChapterSess.getString(1));
			userSession.setModuleTitle(rsChapterSess.getString(2));
			userSession.setChapterSysName(rsChapterSess.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			// Event Video Interactions starts here
	private void processVideo() {
		//JSONObject eventJson = null;
		//System.out.println("video eventJson " + eventJson);
		ResultSet rs;
		try {
			eventJson = (JSONObject)parserJson.parse(eventStr);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/** VIDEO SYS NAME */
		try {
			if (eventJson.containsKey("id")) {
				String tmpStr = eventJson.get("id").toString();
				userSession.setModuleSysName(tmpStr.split("-")[4]);
			} else {
				if ((eventTypeStr.contains("/transcript/download")) || (eventTypeStr.contains("/transcript/translation/"))){
					userSession.setModuleSysName(eventTypeStr.substring(eventTypeStr.indexOf("_video;_") + 8, 
							eventTypeStr.indexOf("/handler/")));
				}
				else
					userSession.setModuleSysName(null);
			}
		//	System.out.println("VIDEOSYSNAME :" + userSession.getModuleSysName());
			if(userSession.getModuleSysName() != null){
				if(courseName.startsWith("W"))
						courseName = courseName.substring(1);
				psVideo.setString(1, courseName);
				psVideo.setString(2, userSession.getModuleSysName());
				rs = psVideo.executeQuery();
				if(rs.next()){
				userSession.setModuleTitle(rs.getString(1));
				userSession.setChapterTitle(rs.getString(2));
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if (eventJson.get("currentTime") != null){
			
			currSeekTime = Float.parseFloat(eventJson.get("currentTime").toString());
		}
		else if (eventTypeStr.contains("speed_change_video")) {
			//System.out.println("HERE cuurtime " + eventJson.get("current_time").toString());
			currSeekTime = Float.parseFloat(eventJson.get("current_time").toString());
		}
		else
			currSeekTime = 0f;
		/** event = seek_video */
		if (eventTypeStr.contains("seek_video")) {
			currSeekTime = Float.parseFloat(eventJson.get(
					"new_time").toString());
			oldSeekTime = Float.parseFloat(eventJson.get(
					"old_time").toString());
			if(eventJson.get("type") != null)
				videoNavigType = eventJson.get("type").toString();
			else
				videoNavigType = null;
		} else { 
		//	oldSeekTime = (float) 0;
			oldSeekTime = 0f;
			videoNavigType = null;
		}
		
		
		/** OLD SPEED AND CURRENT SPEED */
		if (eventTypeStr.contains("speed_change_video")) {
			oldSpeed = Float.parseFloat(eventJson.get(
					"old_speed").toString());
			currSpeed = Float.parseFloat(eventJson.get(
					"new_speed").toString());
		} else {
			oldSpeed = (float) 0;
			currSpeed = (float) 0;
		}
				/** VIDEO POSITION  == currVideoTime*/
		//videoPosition = null;
		if (eventTypeStr.contains("save_user_state")) {
		//	System.out.println( " IN save_user_state");
			videoPostString = eventJson.get("POST").toString();
			//System.out.println( " videoPostString "+ videoPostString);
			try {
				videoPostJson = (JSONObject) new JSONParser().parse(videoPostString);
			
				//System.out.println("videoPostJson " + videoPostJson);
				if (videoPostJson.containsKey("saved_video_position")) {
					//System.out.println("IN SAVED_VDEO_POSTION");
					
					// convert hh:mm:ss to secs 
					String tmpStr = videoPostJson.get("saved_video_position").toString().substring(2, 10);
					//System.out.println("SAVED VIDEO POS tmpStr " + tmpStr);
					// two formats ("0-1:0-1:0-12"] & other 00:00:56
					if(!tmpStr.contains("-")){
						currSeekTime = Float.parseFloat(String.valueOf(Integer.parseInt(tmpStr.substring(0, 2)) * 3600 + 
							Integer.parseInt(tmpStr.substring(3, 5)) * 60 + 
							Integer.parseInt(tmpStr.substring(6, 8))));
					}else {
						tmpStr = videoPostJson.get("saved_video_position").toString();
						String tmpStr1 = tmpStr.substring(tmpStr.indexOf(":") + 1);
						currSeekTime = Float.parseFloat(String.valueOf(Integer.parseInt(tmpStr.substring(tmpStr.indexOf("-") +1, tmpStr.indexOf(":"))) * 3600 + 
								Integer.parseInt(tmpStr.substring(tmpStr.indexOf("-")+1, tmpStr.indexOf(":"))) * 60 + 
								Integer.parseInt(tmpStr.substring(tmpStr.lastIndexOf("-")+1, tmpStr.length() -2) )));
						
					}
				}
				else{
				//	System.out.println("IN YOUTUBEAVAL");
				}
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
		}
		
		//System.out.println("Video Position:\t\t  "+ currSeekTime);
		if(currSeekTime != null)
			userSession.setCurrVideoTime(currSeekTime);
		if(oldSeekTime != null)
			userSession.setOldVideoTime(oldSeekTime);
		if(currSpeed != null)
			userSession.setCurrVideoSpeed(currSpeed);
		if(oldSpeed != null)
			userSession.setOldVideoSpped(oldSpeed);
		if(videoNavigType != null)
			userSession.setVideoNavigType(videoNavigType);
		
		return ;
	}

}
