package EDXDataStaging;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
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
import LMSDataLoader.Dao.CityDao;
import LMSDataLoader.Dao.EventCourseInteractDao;
import LMSDataLoader.Dao.EventProbInteractDao;
import LMSDataLoader.Dao.EventVideoInteractDao;
import LMSDataLoader.Dao.StateDao;
import LMSDataLoader.Dao.StudentCourseEnrolmentDao;
import LMSDataLoader.Dao.UserDao;
import LMSDataLoader.Dao.UserSessionDao;
import LMSDataLoader.dataModels.City;
import LMSDataLoader.dataModels.EventCourseInteract;
import LMSDataLoader.dataModels.EventProbInteract;
import LMSDataLoader.dataModels.EventVideoInteract;
import LMSDataLoader.dataModels.States;
import LMSDataLoader.dataModels.StudentCourseEnrolment;
import LMSDataLoader.dataModels.User;
import LMSDataLoader.dataModels.UserSession;
@WebServlet("/EdxSqlReaderOld")
public class EdxSqlReaderOld extends HttpServlet {
		private static final long serialVersionUID = 1260045197860645956L;
		private static final String lmsName="IITEDX";
		private StudentCourseEnrolmentDao studentCourseEnrolmentDao = null;
		private StudentCourseEnrolment studentCourseEnrolment = new StudentCourseEnrolment();
		private StateDao stateDao = null;
		private States state = new States();
		private CityDao cityDao = null;
		private SimpleDateFormat mongoDateFormat;
		private EventCourseInteractDao eventCourseInteractDao = null;
		private EventProbInteractDao eventProbInteractDao = null;
		private EventVideoInteractDao eventVideoInteractDao = null;
		private UserSessionDao userSessionDao = null;
		private Long eventId = -1l;
		private EventProbInteract eventProbInteract = new EventProbInteract();
		private EventVideoInteract eventVideoInteract = new EventVideoInteract();
		private EventCourseInteract eventCourseInteract = new EventCourseInteract();
		private UserSession userSession = new UserSession();
		private String moduleType, moduleId, stateStr, key;
		private String[] inputkeys;
		private Float currSeekTime;
		private PreparedStatement psVideo=null;
		private ResultSet rsVideo = null;
		private Short evnt_Load_Video,evnt_Pause_Video,evnt_Play_Video,evnt_Seek_Video;
		private Short evnt_Speed_Change_Video,evnt_Stop_Video,evnt_Hide_Transcript;
		private Short evnt_Show_Transcript,	evnt_Save_User_State,evnt_Transcript_Translation,evnt_Transcript_Download;
		private Short evnt_Save_Video_Position;
		private Short evnt_Problem_Check,evnt_Problem_Check_Fail,evnt_Problem_Reset,evnt_Problem_Rescore;
		private Short evnt_Problem_Rescore_Fail,evnt_Problem_Save,evnt_Problem_Show,evnt_Reset_Problem;
		private Short evnt_Reset_Problem_Fail,evnt_Save_Problem_Success,evnt_Problem_Graded;
		private Short evnt_Showanswer,evnt_Save_Problem_Fail,evnt_Problem_Get;
		private Short evnt_Seq_Goto,evnt_Seq_Next,evnt_Seq_Prev,evnt_Page_Close,evnt_Goto_Position;
		private Short evnt_Dashboard,evnt_Root,	evnt_Jsi18n,evnt_I18n_Js;
		private Short evnt_Courseware,evnt_Chapter,evnt_Session;
		private Short evnt_Threads,evnt_Users;
		private Short evnt_List_Staff,evnt_Dump_Graded_Assignments_Config,evnt_Course_Enrollment_Activated;
		private Short evnt_Course_Enrollment_Mode_Changed,evnt_Change_Enrollment,evnt_Register;
		private Short evnt_Create_Account,evnt_Logout,evnt_Admin;

		String[] tmpStrArr;
		Set keySet = null,keySet2 = null;
		Integer recNo = 0;
		private JSONObject correctMapJson = null, studentAnswerJSon = null, correctMapKeyValJSon = null;
		//private JSONObject inputJson=null;
		private JSONObject stateJson = null;
		private JSONParser jparse = new JSONParser();		
		private City city = new City();
		User user = new User();
		UserDao userDao = null;
		private Short currEventNo = 0;
		//StudentCourseGrades studentCourseGrades =  new StudentCourseGrades();
		//StudentCourseGradeDao studentCourseGradeDao = new StudentCourseGradeDao();
		SimpleDateFormat birthDatefrmt;
		private String answers = "";
		private Connection connIIT = null;
		private Connection connEDX = null;
		private Statement stmt= null;
		private SimpleDateFormat dsf = new SimpleDateFormat("HH:mm:ss");
		private java.util.Date tmpDate = null;
		//private String sqlStmt;
	 public void init() throws ServletException
	 {
		      // Get Mongo Connection
			  //static Logger edxLog = Logger.getLogger(EdxMongoReader.class);
			try{
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				// IIT
				DataSource dsIIT = (DataSource)envContext.lookup("jdbc/IITBxDb");
				connIIT = dsIIT.getConnection();
				DataSource dsEDX = (DataSource)envContext.lookup("jdbc/MoocDb");
				connEDX = dsEDX.getConnection();
			
				
			} catch (NamingException ne) {             
				System.out.println("ERror in Naming " + ne.toString());
				ne.printStackTrace();
			} catch (SQLException e) {      
				System.out.println("ERror in SQL " + e.toString());
				e.printStackTrace();
			}
		 
	}
	  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
		{
			  doGet(request, response);
		}
	 public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	 {
		 System.out.println("IN DO GET");
		 ResultSet rs = null;
		try {
			userDao = new UserDao(connIIT);
			stateDao = new StateDao(connIIT);
			cityDao = new CityDao(connIIT);
			studentCourseEnrolmentDao = new StudentCourseEnrolmentDao(connIIT);
			eventCourseInteractDao = new EventCourseInteractDao(connIIT);
			eventProbInteractDao = new EventProbInteractDao(connIIT);
			eventVideoInteractDao = new EventVideoInteractDao(connIIT);
			userSessionDao = new UserSessionDao(connIIT);
			System.out.println("BEFORE PREP STA");

				psVideo = connIIT.prepareStatement("SELECT v.videoTitle, v.chapterSysName,c.chapteTitle " +
						" FROM CourseVideos v, CourseChapter c where v.courseName = c.courseName and v.chapterSysName = c.chapterSysName"
						+ " and v.courseName = ? and v.videoSysName = ?");
			
			stmt = connEDX.createStatement();	
			System.out.println("AFTER PREP STA");
			// edxbackup 
			
	
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("EdxParams.properties");  
		    Properties properties = new Properties(); 
		    System.out.println("BEFORE PROP LOAD");
		  	properties.load(inputStream);
		  	System.out.println("AFTER PROP LOAD");
		  	birthDatefrmt = new SimpleDateFormat(properties.getProperty("BIRTHDATEFORMAT"));
		  	mongoDateFormat = new SimpleDateFormat(properties.getProperty("MONGODATEFORMAT"));
		  	System.out.println("Before getEvent");
		  	getEventNos(properties);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	/******** Insert into User Table of LMS
	 *  
	 */
		String sqlStmt = "select  a.id, a.username, a.email, a.is_active, a.last_login, a.date_joined," +  
				" p.language, p.gender, p.year_of_birth, p.level_of_education, p.goals,p.allow_certificate"+
				",s.name, c.name, m.pincode, m.aadhar_id " +
				" from auth_user a join auth_userprofile p on a.id = p.user_id join student_mooc_person m " +
				" on a.id = m.user_id join student_mooc_city c on m.city_id = c.id " + 
				" join student_mooc_state s on m.state_id = s.id";
		String sqlEnrol = "SELECT user_id,course_id,created,is_active,mode FROM student_courseenrollment";
		String sqlCity = "SELECT name, state_id FROM student_mooc_city";
		String sqlState = "SELECT name  FROM student_mooc_state";
			    ;
				
		System.out.println("sqlSTmt " + sqlStmt);
		
		try {
			// Create User Record 
			rs = stmt.executeQuery(sqlStmt);		
			while (rs.next()){
				user.setLmsUserId(rs.getLong(1));
				user.setName(rs.getString(2));
				user.setEmailId(rs.getString(3));
				user.setActive(rs.getShort(4));
				user.setLastAccessDate(new java.util.Date(rs.getDate(5).getTime()));
				user.setRegistrationDate(new java.util.Date(rs.getDate(6).getTime()));
				user.setMothertounge(rs.getString(7));
				user.setGender(rs.getString(8));
				user.setYearOfBirth(rs.getInt(9));
				user.setHighestEduDegree(rs.getString(10));
				user.setGoals(rs.getString(11));
				user.setAllowCert(rs.getShort(12));
				user.setState(rs.getString(13));
				user.setCity(rs.getString(14));
				if(rs.getString(15) != null)
					user.setPincode(Integer.parseInt(rs.getString(15)));
				if(rs.getString(16) != null)
					user.setAadharId(Integer.parseInt(rs.getString(16)));
				// Not available
				//user.setOrgName(orgName);
				user.setLmsName("IITEDX");
				
				userDao.insertRec(user);
			}
			// Create enrolment Records
			rs = stmt.executeQuery(sqlEnrol);
			
			String[] tmpStrArr;
			while (rs.next()){
				
				studentCourseEnrolment.setLmsUserId(rs.getLong(1));
				tmpStrArr = rs.getString(2).split("/");
				studentCourseEnrolment.setCourseName(tmpStrArr[1]);
				studentCourseEnrolment.setCourseRun(tmpStrArr[2]);
				studentCourseEnrolment.setEnrolmentDate(new java.util.Date(rs.getDate(3).getTime()));
				studentCourseEnrolment.setActive(rs.getInt(4));
				studentCourseEnrolment.setMode(rs.getString(5));
				studentCourseEnrolment.setLmsName("EDX");
				//studentCourseEnrolment.setOrgName("");
				
				studentCourseEnrolmentDao.insertRec(studentCourseEnrolment);
			}
			// Create City & State
			rs = stmt.executeQuery(sqlCity);
			while (rs.next()){
				city.setName(rs.getString(1));
				city.setStateId(rs.getInt(2));;
				cityDao.insertRec(city);
			}
			rs = stmt.executeQuery(sqlState);
			while (rs.next()){
				state.setName(rs.getString(1));
				stateDao.insertRec(state);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// Qery edx tables courseware_studentmodule, Create all Event Tables table of LMS
		
		/******** End Of Insert into User Table of LMS
		 *  *********** Start of StudentCourseGrade Insert
		 */
		
		
		
		}
	 private void getEventNos(Properties properties){
		 evnt_Load_Video = Short.parseShort(properties.getProperty("EVNT_LOAD_VIDEO"));
		 evnt_Pause_Video=Short.parseShort(properties.getProperty("EVNT_PAUSE_VIDEO"));
		 evnt_Play_Video=Short.parseShort(properties.getProperty("EVNT_PLAY_VIDEO"));
		 evnt_Seek_Video =Short.parseShort(properties.getProperty("EVNT_SEEK_VIDEO"));
		 evnt_Speed_Change_Video=Short.parseShort(properties.getProperty("EVNT_SPEED_CHANGE_VIDEO"));
		 evnt_Stop_Video=Short.parseShort(properties.getProperty("EVNT_STOP_VIDEO"));
		 evnt_Hide_Transcript=Short.parseShort(properties.getProperty("EVNT_HIDE_TRANSCRIPT"));
		 evnt_Show_Transcript=Short.parseShort(properties.getProperty("EVNT_SHOW_TRANSCRIPT"));
		 evnt_Save_User_State=Short.parseShort(properties.getProperty("EVNT_SAVE_USER_STATE"));
		 evnt_Transcript_Translation=Short.parseShort(properties.getProperty("EVNT_TRANSCRIPT_TRANSLATION"));
		 evnt_Transcript_Download=Short.parseShort(properties.getProperty("EVNT_TRANSCRIPT_DOWNLOAD"));
		 evnt_Save_Video_Position = Short.parseShort(properties.getProperty("EVNT_SAVE_VIDEO_POSITION"));
		 
		 evnt_Problem_Check= Short.parseShort(properties.getProperty("EVNT_PROBLEM_CHECK"));
		 evnt_Problem_Check_Fail=Short.parseShort(properties.getProperty("EVNT_PROBLEM_CHECK_FAIL"));
		 evnt_Problem_Reset=Short.parseShort(properties.getProperty("EVNT_PROBLEM_RESET"));
		 evnt_Problem_Rescore =Short.parseShort(properties.getProperty("EVNT_PROBLEM_RESCORE"));
		 evnt_Problem_Rescore_Fail=Short.parseShort(properties.getProperty("EVNT_PROBLEM_RESCORE_FAIL"));
		 evnt_Problem_Save=Short.parseShort(properties.getProperty("EVNT_PROBLEM_SAVE"));
		 evnt_Problem_Show=Short.parseShort(properties.getProperty("EVNT_PROBLEM_SHOW"));
		 evnt_Reset_Problem =Short.parseShort(properties.getProperty("EVNT_RESET_PROBLEM"));
		 evnt_Reset_Problem_Fail=Short.parseShort(properties.getProperty("EVNT_RESET_PROBLEM_FAIL"));
		 evnt_Save_Problem_Success=Short.parseShort(properties.getProperty("EVNT_SAVE_PROBLEM_SUCCESS"));
		 evnt_Problem_Graded =Short.parseShort(properties.getProperty("EVNT_PROBLEM_GRADED"));
		 evnt_Showanswer=Short.parseShort(properties.getProperty("EVNT_SHOWANSWER"));
		 evnt_Save_Problem_Fail=Short.parseShort(properties.getProperty("EVNT_SAVE_PROBLEM_FAIL"));
		 evnt_Problem_Get =Short.parseShort(properties.getProperty("EVNT_PROBLEM_GET"));

		 evnt_Seq_Goto=Short.parseShort(properties.getProperty("EVNT_SEQ_GOTO"));
		 evnt_Seq_Next=Short.parseShort(properties.getProperty("EVNT_SEQ_NEXT"));
		 evnt_Seq_Prev=Short.parseShort(properties.getProperty("EVNT_SEQ_PREV"));
		 evnt_Page_Close=Short.parseShort(properties.getProperty("EVNT_PAGE_CLOSE"));
		 evnt_Goto_Position=Short.parseShort(properties.getProperty("EVNT_GOTO_POSITION"));
		 evnt_Dashboard=Short.parseShort(properties.getProperty("EVNT_DASHBOARD"));
		 evnt_Root=	Short.parseShort(properties.getProperty("EVNT_ROOT"));
		 evnt_Jsi18n=Short.parseShort(properties.getProperty("EVNT_JSI18N"));
		 evnt_I18n_Js =Short.parseShort(properties.getProperty("EVNT_I18N_JS"));

		 evnt_Courseware=Short.parseShort(properties.getProperty("EVNT_COURSEWARE"));
		 evnt_Chapter=Short.parseShort(properties.getProperty("EVNT_CHAPTER"));
		 evnt_Session=Short.parseShort(properties.getProperty("EVNT_SESSION"));

		 evnt_Threads=Short.parseShort(properties.getProperty("EVNT_THREADS"));
		 evnt_Users=Short.parseShort(properties.getProperty("EVNT_USERS"));

		 evnt_List_Staff=Short.parseShort(properties.getProperty("EVNT_LIST-STAFF"));
		 evnt_Dump_Graded_Assignments_Config=Short.parseShort(properties.getProperty("EVNT_DUMP-GRADED-ASSIGNMENTS-CONFIG"));
		 evnt_Course_Enrollment_Activated=Short.parseShort(properties.getProperty("EVNT_EDX.COURSE.ENROLLMENT.ACTIVATED"));
		 evnt_Course_Enrollment_Mode_Changed=Short.parseShort(properties.getProperty("EVNT_EDX.COURSE.ENROLLMENT.MODE_CHANGED"));
		 evnt_Change_Enrollment=Short.parseShort(properties.getProperty("EVNT_CHANGE_ENROLLMENT"));
		 evnt_Register=Short.parseShort(properties.getProperty("EVNT_REGISTER"));
		 evnt_Create_Account=Short.parseShort(properties.getProperty("EVNT_CREATE_ACCOUNT"));
		 evnt_Logout=Short.parseShort(properties.getProperty("EVNT_LOGOUT"));
		 evnt_Admin=Short.parseShort(properties.getProperty("EVNT_ADMIN"));
	 }
}
