
package EDXDataStaging;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
import LMSDataLoader.Dao.UserSessionOldDao;
import LMSDataLoader.dataModels.City;
import LMSDataLoader.dataModels.EventCourseInteract;
import LMSDataLoader.dataModels.EventProbInteract;
import LMSDataLoader.dataModels.EventVideoInteract;
import LMSDataLoader.dataModels.States;
import LMSDataLoader.dataModels.StudentCourseEnrolment;
import LMSDataLoader.dataModels.User;
import LMSDataLoader.dataModels.UserSession;
import LMSDataLoader.dataModels.UserSessionOld;
@WebServlet("/EdxSqlReader")
public class EdxSqlReader extends HttpServlet {
		private static final long serialVersionUID = 1260045197860645956L;
		private static final String lmsName="IITEDX";
		private StudentCourseEnrolmentDao studentCourseEnrolmentDao = null;
		private StudentCourseEnrolment studentCourseEnrolment = new StudentCourseEnrolment();
		private StateDao stateDao = null;
		private States state = new States();
		private CityDao cityDao = null;
		private SimpleDateFormat mongoDateFormat;
		//private EventCourseInteractDao eventCourseInteractDao = null;
		//private EventProbInteractDao eventProbInteractDao = null;
		//private EventVideoInteractDao eventVideoInteractDao = null;
		private UserSessionOldDao userSessionDao = null;
		private Long eventId = -1l;
		private EventProbInteract eventProbInteract = new EventProbInteract();
		private StringBuilder answers = new StringBuilder();
		private EventVideoInteract eventVideoInteract = new EventVideoInteract();
		private EventCourseInteract eventCourseInteract = new EventCourseInteract();
		private UserSessionOld userSession = new UserSessionOld();
		private String moduleType, moduleId, stateStr, key;
		private String[] inputkeys;
		private Float currSeekTime;
		private PreparedStatement psVideo=null, psVideoInteract = null, psChapterSess = null;
		private PreparedStatement psProblem = null,psChapter = null, psDiscuss = null;
		private PreparedStatement psCourseInteract = null,psProbInteract = null, psUserSession = null;
		private ResultSet rsVideo = null, rsTmp;
		private String sqlStmt=null;
		private Short evnt_Save_Video_Position;
		private Properties properties = new Properties();
		String[] tmpStrArr;
		Set keySet = null;
		Integer recNo = 0;
		private JSONObject correctMapJson = null, studentAnswerJSon = null, correctMapKeyValJSon = null;
		//private JSONObject inputJson=null;
		private JSONObject stateJson = null;
		private JSONParser jparse = new JSONParser();		
		private City city = new City();
		Boolean firstFlag ;
		User user = new User();
		UserDao userDao = null;
		private Short currEventNo = 0;
		//StudentCourseGrades studentCourseGrades =  new StudentCourseGrades();
		//StudentCourseGradeDao studentCourseGradeDao = new StudentCourseGradeDao();
		SimpleDateFormat birthDatefrmt;
		
		private Connection connIIT = null;
		private Connection connEDX = null;
		private Statement stmt= null;
		private SimpleDateFormat dsf = new SimpleDateFormat("HH:mm:ss");
		private java.util.Date tmpDate = null;
		private GetLastId getLastId =null;
		private Iterator itr = null, itr1=null;
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
				getLastId = new GetLastId(connIIT);
				System.out.println("Database Initialised");
			} catch (NamingException ne) {             
				System.out.println("ERror in Naming " + ne.toString());
				//ne.printStackTrace();
			} catch (SQLException e) {      
				System.out.println("ERror in SQL " + e.toString());
				//e.printStackTrace();
			}
		 
	}
	  public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
		{
			  doGet(request, response);
		}
	 @SuppressWarnings("deprecation")
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	 {
		 System.out.println("IN DO GET");
		 ResultSet rs = null;
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("EdxParams.properties");  
		     
		    System.out.println("BEFORE PROP LOAD");
		  	properties.load(inputStream);
		  	System.out.println("AFTER PROP LOAD");
		  	birthDatefrmt = new SimpleDateFormat(properties.getProperty("BIRTHDATEFORMAT"));
		  	mongoDateFormat = new SimpleDateFormat(properties.getProperty("MONGODATEFORMAT"));
		  	System.out.println("Before getEvent");
		  	evnt_Save_Video_Position = Short.parseShort(properties.getProperty("EVNT_SAVE_VIDEO_POSITION"));
		  	
		  	//getEventNos(properties);
		  	
			//eventCourseInteractDao = new EventCourseInteractDao(connIIT);
			//eventProbInteractDao = new EventProbInteractDao(connIIT);
			//eventVideoInteractDao = new EventVideoInteractDao(connIIT);
			userSessionDao = new UserSessionOldDao(connIIT);
			System.out.println("BEFORE PREP STA");
			psVideo = connIIT.prepareStatement("SELECT v.videoTitle, v.chapterSysName,c.chapteTitle " +
						" FROM CourseVideos v, CourseChapter c where v.courseName = c.courseName and v.chapterSysName = c.chapterSysName"
						+ " and v.courseName = ? and v.videoSysName = ?");
			psChapterSess = connIIT.prepareStatement("SELECT c.chapteTitle, s.sessionTitle, c.chapterSysName " +
					" FROM CourseChapter c join CourseChapterSession s " +
					" on c.courseName = s.courseName and c.chapterSysName = s.chapterSysName " + 
					" where  c.courseName = ? and s.sessionSysName = ? ");
			psProblem = connIIT.prepareStatement("sELECT p.chapterSysName,c.chapteTitle, p.quizTitle FROM CourseProblems p" +
					" join CourseChapter c on p.chapterSysName = c.chapterSysName " +
					" where p.courseName = ? and quizSysName = ?");

			psChapter = connIIT.prepareStatement("SELECT chapteTitle FROM CourseChapter where courseName = ? and chapterSysName = ?");
			psDiscuss = connIIT.prepareStatement("SELECT discussionTitle FROM CourseDiscussions where discussionSysId = ?" +
					" and courseName = ?");
			
			psVideoInteract = connIIT.prepareStatement("insert into EventVideoInteract( sessionSysName, lmsName, orgName, courseName, courseRun, lmsUserId,"
					+ "eventName,eventNo, videoSysName, videoTitle, chapterSysName,chapterTitle, oldSeekTime, currSeekTime, videoNavigType, "
					+ " oldSpeed, currSpeed, source,createDateTime, lastModDateTime) "
					+ " values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)");
			
			psCourseInteract = connIIT.prepareStatement("insert into EventCourseInteract ( lmsName, orgName, courseName, courseRun, lmsUserId," +
					"eventName,eventNo,  moduleType, moduleSysName,moduleTitle,chapterSysName,chapterTitle," +
					"createDateTime, modDateTime, oldPosition,  curPosition,  source) " +
					" values (?,?,?,?,?,  ?,?,?,?,?,  ?,?,?,?,?,  ?,?)");
			
			psProbInteract = connIIT.prepareStatement("insert into EventProbInteract(	lmsName,orgName, courseName,lmsUserId," +
					"eventName,eventNo,quizzSysName,quizzTitle,chapterSysName,chapterTitle,hintAvailable,hintMode,inputType,responseType," +
					"variantId,oldScore,newScore,maxGrade,attempts,maxAttempts,choice, success, " +
					"source, probSubTime,done,createDateTime,lastModDateTime,courseRun) " +
					" values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,? , ?,?,?)");
			psUserSession = connIIT.prepareStatement("insert into UserSession (sessionSysName, lmsName, orgName, courseName,courseRun, lmsUserId, userName," + 
					"agent,	hostName, ipAddress, url, createDateTime, eventType, eventSource, eventName," + 
					"eventId, lastModDateTime,dataSource,eventNo) values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,  ?,?,?,?)");
			
			System.out.println(psCourseInteract);
			stmt = connEDX.createStatement();	
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("I/O Exception " + e1.toString());
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("SQL Exception " + e.toString());
		}
		// Query edx tables courseware_studentmodule, Create all Event Tables table of LMS
		
	//	/******** End Of Insert into User Table of LMS
		
		
		String sqlStmt = "select module_type,module_id,student_id,state,grade,created,modified,max_grade," +
					"done, course_id,userName from courseware_studentmodule s join auth_user u " +
				//	"done, course_id,userName from tmpCS s join auth_user u " +
					"on s.student_id = u.id where student_id in (4,14,15,17,18,19,23,24,25,28,30,31,32,33,60,65,69,72,78,88,90,102,116,118,132,133,139,145,149,158,181,193,228,237,250,269,286,287,290,301,324,342,348,351,353,367,378,402,404,405,409,429,434,442,448,461,469,476,499,510,556,571,624,634,660,680,683,686,721,732,742,753,776,781,784,791,818,831,833,865,866,887,891,897,902,920,924,985,1015,1016,1027,1031,1056,1106,1120,1133,1150,1161,1169,1182,1197,1204,1210,1221,1237,1238,1243,1266,1270,1280,1282,1291,1299,1300,1322,1336,1344,1345,1352,1355,1365,1366,1368,1370,1379,1387,1389,1390,1408,1414,1420,1423,1474,1481,1521,1525,1546,1556,1558,1566,1571,1573,1581,1588,1605,1613,1618,1626,1687,1724,1740,1750,1762,1781,1788,1790,1804,1825,1827,1831,1836,1842,1845,1851,1862,1863,1868,1886,1888,1894,1895,1897,1907,1908,1909,1910,1915,1917,1922,1926,1933,1943,1944,1957,1962,1963,1972,1973,1982,1990,2005,2016,2029,2043,2079,2093,2099,2107,2114,2119,2121,2156,2214,2216,2221,2227,2229,2233,2240,2242,2243,2245,2249,2274,2276,2284,2310,2316,2325,2328,2335,2347,2379,2382,2395,2400,2419,2420,2438,2453,2510,2536,2540,2567,2577,2579,2581,2586,2591,2592,2593,2601,2608,2654,2666,2675,2678,2699,2719,2746,2757,2768,2812,2819,2824,2840,2853,2863,2866,2893,2905,2909,2915,2923,2939,2950,2953,2954,2969,2975,3008,3011,3022,3031,3035,3037,3046,3079,3106,3112,3113,3121,3123,3126,3142,3156,3159,3161,3165,3177,3188,3194,3217,3223,3242,3256,3261,3268,3269,3279,3281,3303,3309,3334,3365,3384,3395,3399,3414,3462,3466,3489,3491,3492,3536,3585,3589,3596,3600,3601,3606,3645,3652,3690,3716,3749,3753,3777,3786,3792,3807,3836,3855,3883,3896,3900,3918,3929,3934,3950,3961,3969,3984,3991,4014,4029,4071,4100,4113,4119,4130,4194,4235,4250,4301,4338,4346,4365,4374,4389,4395,4397,4399,4409,4443,4459,4509,4543,4545,4563,4564,4572,4589,4601,4613,4656,4658,4694,4711,4714,4801,4833,4867,4903,4905,4921,4922,4953,4958,4991,4993,5001,5015,5030,5034,5078,5098,5102,5163,5178,5189,5206,5232,5262,5322,5339,5355,5379,5412,5480,5486,5514,5547,5551,5706,5711,5715,5720,5759,5815,5955,5997,6025,6068,6108,6128,6162,6185,6197,6218,6228,6235,6257,6270,6276,6279,6284,6297,6323,6330,6396,6407,6409,6415,6448,6455,6476,6487,6499,6503,6529,6541,6560,6633,6654,6699,6791,6792,6849,6850,6851,6857,6863,6904,6906,6934,6947,6951,6952,6997,7054,7092,7155,7181,7191,7229,7238,7301,7343,7364,7372,7377,7383,7417,7511,7520,7652,7661,7687,7694,7719,7736,7742,7752,7784,7825,7901,7961,8152,8203,8305,8358,8402,8417,8443,8559,8575,8584,8593,8615,8670,8750,8753,8771,8788,8798,8802,8858,8892,8956,8959,8968,9003,9100,9101,9111,9118,9144,9162,9205,9212,9252,9265,9276,9389,9400,9415,9416,9418,9475,9491,9505,9552,9571,9576,9618,9773,9814,9859,9861,9870,9877,9880,9886,9928,9937,10071,10172,10196,10202,10228,10251,10339,10353,10376,10438,10456,10497,10547,10618,10640,10683,10718,10780,11015,11079,11081,11083,11085,11100,11158,11171,11172,11190,11214,11216,11219,11235,11326,11366,11419,11439,11537,11540,11729,11742,11784,11980,12221,12374,12388,12443,12459,12523,12579,12637,12638,12650,12720,12734,12776,12783,12871,12878,12900,12983,12987,12992,13000,13003,13018,13020,13036,13059,13065,13067,13081,13087,13099,13143,13166,13177,13184,13263,13317,13341,13344,13350,13352,13391,13443,13591,13601,13642,13659,13846,13881,13891,13925,13927,13954,13957,13961,13987,14001,14025,14027,14032,14040,14122,14127,14166,14169,14228,14241,14246,14251,14256,14264,14291,14303,14326,14332,14361,14369,14372,14378,14383,14398,14404,14424,14465,14471,14494,14511,14524,14544,14553,14569,14664,14665,14666,14718,14825,14933,15006,15008,15017,15133,15350,15489,15511,15580,15623,15658,15687,15691,15695,15710,15711,15728,15761,15810,15825,15843,15868,15941,15953,15995,16019,16050,16130,16174,16177,16274,16329,16377,16396,16463,16534,16537,16582,16641,16657,16665,16675,16708,16748,16752,16757,16802,16808,16822,16851,16854,16859,16887,16898,16923,16962,16964,17011,17083,17135,17188,17240,17246,17408,17428,17429,17439,17452,17522,17531,17535,17593,17626,17642,17676,17684,17724,17809,17842,17857,17883,17895,18098,18100,18264,18326,18402,18403,18420,18432,18447,18476,18512,18519,18538,18540,18582,18591,18615,18626,18826,18898,18956,18973,18993,19042,19106,19135,19137,19149,19181,19203,19236,19237,19291,19323,19327,19385,19393,19461,19538,19546,19571,19572,19573,19576,19595,19597,19598,19599,19600,19607,19620,19673,19687,19734,19802,19929,20025,20153,20157,20185,20202,20240,20324,20354,20390,20435,20441,20482,20494,20569,20575,20643,20706,20735,20744,20778,20824,20882,20884,20889,20905,20914,20938,20939,20943,20945,20967,20979,20980,20988,20992,20999,21003,21005,21010,21014,21057,21121,21127,21145,21177,21305,21326,21343,21348,21356,21361,21370,21377,21385,21389,21391,21418,21456,21477,21496,21534,21587,21629,21638,21646,21694,21730,21739,21746,21765,21801,21818,21821,21826,21845,21874,21875,21902,21928,21932,21957,22055,22155,22186,22211,22227,22228,22232,22235,22244,22250,22252,22253,22254,22258,22280,22293,22299,22322,22324,22334,22340,22344,22353,22358,22392,22422,22493,22501,22502,22526,22529,22546,22561,22573,22574,22585,22597,22605,22606,22613,22615,22645,22667,22680,22686,22706,22710,22740,22752,22768,22869,22890,22895,22908,22929,22957,22988,23001,23061,23141,23168,23181,23219,23232,23261,23345,23369,23425,23426,23429,23444,23473,23493,23505,23559,23560,23565,23617,23620,23626,23648,23666,23757,23764,23782,23797,23829,23831,23885,23931,23933,23935,23941,23969,24003,24008,24020,24026,24032,24084,24097,24107,24124,24135,24175,24194,24332,24377,24415,24424,24444,24445,24459,24472,24489,24570,24581,24608,24612,24614,24615,24616,24642,24651,24671,24713,24748,24787,24789,24791,24794,24800,24815,24823,24930,24931,24935,24936,24945,24948,24982,25028,25031,25033,25075,25116,25137,25146,25172,25195,25230,25255,25260,25279,25302,25328,25504,25510,25513,25541,25562,25629,25669,25671,25686,25687,25710,25752,25758,25789,25797,25798,25802,25810,25868,25896,25957,25967,25999,26019,26031,26045,26075,26105,26109,26123,26134,26142,26159,26177,26178,26187,26236,26293,26307,26350,26376,26526,26529,26540,26559,26563,26618,26700,26707,26735,26740,26750,26752,26754,26765,26814,26819,26841,26842,26843,26845,26895,26921,26946,26958,26959,26960,26975,26983,26993,27009,27106,27119,27176,27326,27328,27331,27332,27334,27373,27382,27395,27408,27440,27478,27486,27509,27523,27542,27578,27584,27603,27623,27629,27663,27728,27757,27765,27784,27792,27820,27831,27974,27989,28089,28103,28112,28147,28194,28213,28253,28255,28256,28258,28277,28288,28297,28326,28352,28353,28381,28424,28433,28439,28443,28480,28495,28528,28591,28634,28658,28727,28780,28786,28794,28822,28849,28850,28880,29010,29052,29065,29071,29089,29100,29202,29207,29246,29275,29290,29292,29332,29340,29348,29356,29425,29589,29631,29646,29649,30120,30132,30160,30190,30360,30481,30492,30558,30592,30610,30613,30635,30644,30653,30662,30663,30681,30687,30691,30692,30698,30704,30733,30742,30748,30749,30750,30751,30754,30765,30785,30786,30795,30798,30804,30814,30815,30839,30843,30848,30860,30861,30870,30885,30888,30889,30911,30949,30975,30979,30991,30993,30994,30997,31010,31012,31015,31016,31017,31018,31019,31030,31038,31051,31059,31065,31073,31075,31076,31094,31105,31106,31108,31110,31116,31117,31119,31122,31127,31138,31141,31190,31195,31202,31208,31231,31238,31264,31270,31275,31276,31282,31284,31347,31350,31353,31355,31375,31407,31412,31426,31430,31438,31459,31467,31472,31474,31475,31482,31485,31496,31497,31501,31508,31509,31513,31519,31520,31521,31522,31531,31532,31533,31534,31536,31538,31539,31541,31542,31598,31603,31611,31616,31620,31623,31627,31642,31656,31658,31660,31664,31666,31671,31674,31691,31703,31720,31725,31729,31759,31762,31770,31773,31775,31785,31790,31792,31801,31802,31807,31809,31813,31816,31819,31823,31827,31833,31835,31839,31840,31841,31842,31847,31851,31852,31857,31863,31866,31868,31871,31877,31879,31932,31939,31975,31982,32004,32006,32076,32084,32090,32093,32097,32146,32149,32157,32165,32185,32192,32193,32200,32203,32207,32209,32215,32216,32222,32224,32226,32228,32232,32233,32237,32243,32244,32246,32249,32258,32288,32299,32307,32309,32310,32346,32355,32366,32368,32373,32376,32382,32384,32394,32408,32410,32412,32420,32421,32422,32423,32427,32434,32448,32450,32468,32470,32472,32473,32476,32477,32478,32479,32480,32481,32482,32483,32484,32486,32501,32551,32553,32558,32563,32570,32579,32589,32605,32607,32627,32628,32629,32634,32641,32646,32649,32657,32659,32660,32667,32672,32686,32687,32690,32694,32697,32704,32719,32720,32723,32745,32750,32751,32755,32756,32772,32783,32784,32785,32788,32790,32791,32792,32804,32805,32806,32807,32810,32811,32813,32814,32820,32826,32840,32842,32850,32858,32894,32903,32905,32912,32914,32935,32944,32947,32964,32977,32988,33006,33011,33023,33027,33031,33058,33064,33087,33091,33094,33119,33120,33124,33128,33133,33137,33143,33144,33145,33146,33164,33168,33182,33330,33430,33534,33540,33573,34214,34237,34310)order by student_id";
		// Get userName from auth_user
		
		
		System.out.println("sqlSTmt " + sqlStmt);
		try {
			rs = stmt.executeQuery(sqlStmt);
			System.out.println("RECORD NO : rs executed" + recNo);
			while (rs.next()){
				currEventNo = 0;				
				userSession.setDataSource("SQL");
				userSession.setLmsName("EDX");
				recNo++;
				System.out.println("RECORD NO : " + recNo);
				moduleType = rs.getString(1);
				System.out.println("MODULE TYPE : " + moduleType);
				moduleId = rs.getString(2);
				userSession.setLmsUserId(Long.parseLong(rs.getString(3)));
				stateStr = rs.getString(4);
				stateJson = (JSONObject)jparse.parse(stateStr);
				
				userSession.setCreateDateTime(rs.getTimestamp(6));
				userSession.setLastModDateTime(rs.getTimestamp(7));
				userSession.setUserName(rs.getString(11));
				tmpStrArr = rs.getString(10).split("/");
				userSession.setOrgName(tmpStrArr[0]);
				userSession.setCourseName(tmpStrArr[1]);
				userSession.setCourseRun(tmpStrArr[2]);
				
				
				if((moduleType.equals("chapter")) || (moduleType.equals("course")) || (moduleType.equals("sequential"))){
					System.out.println("moduleType is course");
					userSession.setEventType("course");
					eventCourseInteract.setLmsName(lmsName);
					eventCourseInteract.setOrgName(userSession.getOrgName());
					eventCourseInteract.setCourseName(userSession.getCourseName());
					eventCourseInteract.setLmsUserId(userSession.getLmsUserId());
					eventCourseInteract.setEventName(moduleType);
					userSession.setEventName(moduleType);
					eventCourseInteract.setModuleType(moduleType);
					eventCourseInteract.setModuleSysName(moduleId.substring(moduleId.lastIndexOf("/")+1));
					System.out.println(eventCourseInteract.getModuleSysName());
					userSession.setModuleSysName(eventCourseInteract.getModuleSysName());
					currEventNo = Short.parseShort(properties.getProperty("EVNT_GOTO_POSITION"));
					if(moduleType.equals("chapter")){
						eventCourseInteract.setChapterSysName(eventCourseInteract.getModuleSysName());
						psChapter.setString(1,userSession.getCourseName());
						psChapter.setString(2,eventCourseInteract.getModuleSysName());
						rsTmp = psChapter.executeQuery();
						if(rsTmp.next())
							{eventCourseInteract.setChapterTitle(rsTmp.getString(1));
							userSession.setChapterTitle(rsTmp.getString(1));}
					}else if(moduleType.equals("session")){
						eventCourseInteract.setChapterSysName(eventCourseInteract.getModuleSysName());
						userSession.setChapterSysName(eventCourseInteract.getChapterSysName());
						psChapterSess.setString(1,userSession.getCourseName());
						psChapterSess.setString(2,eventCourseInteract.getModuleSysName());
						rsTmp = psChapterSess.executeQuery();
						if(rsTmp.next()){
							String chapTitle = rsTmp.getString(1);
							String moduleTitle = rsTmp.getString(2);
							String chapSysName = rsTmp.getString(3);
							eventCourseInteract.setChapterTitle(chapTitle);
							eventCourseInteract.setModuleTitle(moduleTitle);
							eventCourseInteract.setChapterSysName(chapSysName);
							userSession.setChapterTitle(chapTitle);
							userSession.setModuleTitle(moduleTitle);
							userSession.setChapterSysName(chapSysName);
							
							
						}
					}else if(moduleType.equals("course")){
						    String chapTitle = userSession.getCourseName();
						    String moduleTitle = userSession.getCourseName();
						    String chapSysName = eventCourseInteract.getModuleSysName();
						
							eventCourseInteract.setChapterSysName(eventCourseInteract.getModuleSysName());
							eventCourseInteract.setChapterTitle(userSession.getCourseName());
							eventCourseInteract.setModuleTitle(userSession.getCourseName());
							
							userSession.setChapterTitle(chapTitle);
							userSession.setModuleTitle(moduleTitle);
							userSession.setChapterSysName(chapSysName);
					}
					eventCourseInteract.setEventNo(currEventNo);
					userSession.setEventNo(currEventNo);
					//System.out.println("stateJson.get(position).toString() " + stateJson.get("position").toString());
					eventCourseInteract.setCurPosition(Short.parseShort(stateJson.get("position").toString()));
					userSession.setCurrPosition((int)Short.parseShort(stateJson.get("position").toString()));
					eventCourseInteract.setSource("SQL");
					userSession.setEventSource("SQL");
					eventCourseInteract.setCourseRun(userSession.getCourseRun());
					eventCourseInteract.setCreateDateTime(userSession.getCreateDateTime());
					eventCourseInteract.setModDateTime(userSession.getLastModDateTime());
					insertCourse();
					//eventId = eventCourseInteractDao.insertRec(eventCourseInteract, getLastId);
				}else if(moduleType.equals("openassessment")){
					userSession.setEventType("openassess");
				}
				
///************************************Problem ********************				
				else if(moduleType.equals("problem")){
					System.out.println("moduleType is problem");
					userSession.setEventType("problem");
					userSession.setEventName("Quizz");
					// Event Interact
					eventProbInteract.setDone(rs.getString(9));
					userSession.setDone(rs.getString(9));
					eventProbInteract.setLmsName(lmsName);
					eventProbInteract.setOrgName(userSession.getOrgName());
					eventProbInteract.setCourseName(userSession.getCourseName());
					eventProbInteract.setLmsUserId(userSession.getLmsUserId());
					eventProbInteract.setEventName("problem");
					eventProbInteract.setQuizzSysName(moduleId.substring(moduleId.lastIndexOf("/")+1));
					userSession.setModuleSysName(eventProbInteract.getQuizzSysName());
					psProblem.setString(1,userSession.getCourseName());
					psProblem.setString(2,eventProbInteract.getQuizzSysName());
					rsTmp =  psProblem.executeQuery();
					if(rsTmp.next()){
						eventProbInteract.setChapterSysName(rsTmp.getString(1));
						eventProbInteract.setChapterTitle(rsTmp.getString(2));
						eventProbInteract.setQuizzTitle(rsTmp.getString(3));
						userSession.setChapterSysName(rsTmp.getString(1));
						userSession.setChapterTitle(rsTmp.getString(2));
						userSession.setModuleTitle(eventProbInteract.getQuizzTitle());
												
					}
					eventProbInteract.setMaxGrade(rs.getDouble(8));
					eventProbInteract.setNewScore(rs.getDouble(5));
					eventProbInteract.setSource("SQL");
					eventProbInteract.setCourseRun(userSession.getCourseRun());
					eventProbInteract.setCreateDateTime(userSession.getCreateDateTime());
					eventProbInteract.setLastModDateTime(userSession.getLastModDateTime());
					userSession.setMaxGrade((rs.getFloat(8)));
					userSession.setCurrGrade(rs.getFloat(5));
					userSession.setEventSource("SQL");
				
					
								
					studentAnswerJSon = (JSONObject)stateJson.get("student_answers");
					correctMapJson = (JSONObject)stateJson.get("correct_map");
				//	inputStateJson = (JSONObject)stateJson.get("input_state");
					if(stateJson.get("last_submission_time") != null){
						//System.out.println("stateJson.get('last_submission_time')" + stateJson.get("last_submission_time"));
						eventProbInteract.setProbSubTime(new java.sql.Timestamp(
								mongoDateFormat.parse(stateJson.get("last_submission_time").toString()).getTime()));
								userSession.setProbSubmissionTime(eventProbInteract.getProbSubTime()); 
					}
					if(stateJson.get("attempts") != null){
						//System.out.println("stateJson.get('attempts')" + stateJson.get("attempts"));
						
						eventProbInteract.setAttempts(Integer.parseInt(stateJson.get("attempts").toString()));
						userSession.setAttempts(eventProbInteract.getAttempts());
					}
					if(stateJson.get("done") == null) {
						eventProbInteract.setDone(null);
						userSession.setDone(null);
					}
					else if(stateJson.get("done").toString().equals("false")) {
						eventProbInteract.setDone("N");
						userSession.setDone("N");
					}
					else if(stateJson.get("done").toString().equals("true")){
						//System.out.println("stateJson.get('done')" + stateJson.get("done"));
						eventProbInteract.setDone("Y");
						userSession.setDone("Y");
					}
					if(correctMapJson != null){
						keySet = correctMapJson.keySet();
						//System.out.println("keyset==" + keySet);
						if(!keySet.isEmpty()) {
							//inputkeys = (String[]) keySet.toArray(new String[keySet.size()]);
							itr = keySet.iterator();
							while(itr.hasNext()){
								correctMapKeyValJSon = (JSONObject)correctMapJson.get(itr.next());
								if(correctMapKeyValJSon != null){
									//System.out.println("correctMapKeyValJSon.get(hint) " + correctMapKeyValJSon.get("hint"));
									//System.out.println("correctMapKeyValJSon.get(hintmode) " + correctMapKeyValJSon.get("hintmode"));
									//System.out.println("correctMapKeyValJSon.get(correctness) " + correctMapKeyValJSon.get("correctness"));
									if(correctMapKeyValJSon.get("correctness") != null)
									{
									if(correctMapKeyValJSon.get("correctness").toString().equals("correct")) {
										eventProbInteract.setSuccess("Y");
										userSession.setSuccess("Y");
									}
									else {
										eventProbInteract.setSuccess("N");
										userSession.setSuccess("N");
									}
									if(correctMapKeyValJSon.get("hint") != null){
									eventProbInteract.setHintAvailable(correctMapKeyValJSon.get("hint").toString());
									userSession.setHintAvailable(eventProbInteract.getHintAvailable());}
									if(correctMapKeyValJSon.get("hintmode") != null) {
										eventProbInteract.setHintMode(correctMapKeyValJSon.get("hintmode").toString());
										userSession.setHintMode(eventProbInteract.getHintMode());
									}
								}
								}
							}
						}
					}
					if(studentAnswerJSon != null){
						keySet = studentAnswerJSon.keySet();
						System.out.println("keyset==" + keySet);
						if(!keySet.isEmpty()) {
							//inputkeys = (String[]) keySet.toArray(new String[keySet.size()]);
							itr = keySet.iterator();
							firstFlag = true;
							answers.delete(0, answers.length());
							//answers = new StringBuffer();
							while(itr.hasNext()){
								if(firstFlag){
									answers.append(studentAnswerJSon.get(itr.next()).toString());
									firstFlag = false;
								}
								else{
									answers.append(",");
									answers.append(studentAnswerJSon.get(itr.next()).toString());
								}
							}
							System.out.println("answers " + answers);
							eventProbInteract.setChoice(answers.toString());
							userSession.setAnswerChoice(eventProbInteract.getChoice());
						}else 
							System.out.println("studentAnswerJSon..get(key) is empty ");
						if((stateJson.get("done") == null) || (stateJson.get("done").toString().equals("false"))){
							currEventNo = Short.parseShort(properties.getProperty("EVNT_PROBLEM_SAVE"));
							eventProbInteract.setEventNo(currEventNo);
							userSession.setEventNo(currEventNo);
							
						} else if(stateJson.get("done").toString().equals("true")){
							currEventNo = Short.parseShort(properties.getProperty("EVNT_SAVE_PROBLEM_SUCCESS"));
							eventProbInteract.setEventNo(currEventNo);
							userSession.setEventNo(currEventNo);
						}
					} else {
						currEventNo = Short.parseShort(properties.getProperty("EVNT_PROBLEM_GET"));
						eventProbInteract.setEventNo(currEventNo);
						userSession.setEventNo(currEventNo);
					}
					System.out.println("Evemt No " + currEventNo + " stateJson.get(done) " + stateJson.get("done"));
					insertProbRec();
					//eventId =eventProbInteractDao.insertRec(eventProbInteract, getLastId);
					//eventProbInteractDao.insertRec(eventProbInteract);
					//System.out.println("Returned eventId Problem " + eventId);
				}
///************************************Problem End ********************
				
				else if(moduleType.equals("video")){
					userSession.setEventType("video");
					//eventVideoInteract = new EventVideoInteract();
					eventVideoInteract.setEventName("video");
				
					if(stateJson.get("saved_video_position") != null){
						currEventNo = evnt_Save_Video_Position;
						userSession.setEventName("saved_video_position");
						userSession.setEventNo(currEventNo);
						eventVideoInteract.setEventNo(currEventNo);
						Date tmpDate = dsf.parse(stateJson.get("saved_video_position").toString());
						
						eventVideoInteract.setCurrSeekTime(Float.parseFloat(String.valueOf(tmpDate.getHours()*3600 + tmpDate.getMinutes()*600 + tmpDate.getSeconds())));
						userSession.setCurrentSeekTime(eventVideoInteract.getCurrSeekTime());
					}
					if(stateJson.get("speed") != null){
						eventVideoInteract.setCurrSpeed(Float.parseFloat(stateJson.get("speed").toString()));
						userSession.setCurrVideoSpeed(eventVideoInteract.getCurrSpeed());
					}
					eventVideoInteract.setLmsName(lmsName);
					eventVideoInteract.setOrgName(userSession.getOrgName());
					eventVideoInteract.setCourseName(userSession.getCourseName());
					eventVideoInteract.setLmsUserId(userSession.getLmsUserId());
					eventVideoInteract.setEventName(moduleType);
					eventVideoInteract.setVideoSysName(moduleId.substring(moduleId.lastIndexOf("/")+1));
					userSession.setEventName(moduleType);
					userSession.setModuleSysName(eventVideoInteract.getVideoSysName());
					
					psVideo.setString(1,userSession.getCourseName());
					psVideo.setString(2,eventVideoInteract.getVideoSysName());
					rsVideo = psVideo.executeQuery();
					if(rsVideo.next()){
						eventVideoInteract.setVideoTitle(rsVideo.getString(1));
						eventVideoInteract.setChapterSysName(rsVideo.getString(2));
						eventVideoInteract.setChapterTitle(rsVideo.getString(3));
						userSession.setModuleTitle(eventVideoInteract.getVideoTitle());
						userSession.setChapterSysName(eventVideoInteract.getChapterSysName());
						userSession.setChapterTitle(eventVideoInteract.getChapterTitle());
					}
					eventVideoInteract.setSource("SQL");
					eventVideoInteract.setCourseRun(userSession.getCourseRun());
					eventVideoInteract.setCreateDateTime(userSession.getCreateDateTime());
					eventVideoInteract.setLastModDateTime(userSession.getLastModDateTime());
					userSession.setEventSource("SQL");
					
					
					//eventId = eventVideoInteractDao.insertRec(eventVideoInteract, getLastId,psVideoInteract);
					insertVideoRec();
				}
				// Write in UseSession
				userSession.setSessionId(eventId);
				userSession.setEventNo(currEventNo);
				System.out.println("BEFORE USERSESSION RECORD NO " + recNo + "eventId " + eventId);
				System.out.println("BEFORE USERSESSION currEventNo " + currEventNo + "moduleType " + userSession.getEventType());
				userSessionDao.insertRec(userSession);
				//insertUserSession();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL Exception " + e.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Parse Exception " + e.toString());
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Text Parse Exception " + e.toString());//e.printStackTrace();
		}finally {
			try {
				if (psVideo != null) {
					psVideo.close();
				}
				if (psVideoInteract != null) {
					psVideoInteract.close();
				}
				if (connIIT != null) {
					connIIT.close();
					
				}
				if (connEDX != null) {
					connEDX.close();
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("SQL EXCEPTION " + e.toString());
			
			}
		}
		
	 }
	 public void insertVideoRec(){
			try {
				//if (ps == null)
					//ps = conn.prepareStatement(sqlStmt);
				
				psVideoInteract.setString(1, eventVideoInteract.getSessionSysName());
				psVideoInteract.setString(2, eventVideoInteract.getLmsName());
				psVideoInteract.setString(3, eventVideoInteract.getOrgName());
				psVideoInteract.setString(4, eventVideoInteract.getCourseName());
				psVideoInteract.setString(5, eventVideoInteract.getCourseRun());
				psVideoInteract.setLong(6, eventVideoInteract.getLmsUserId());
				psVideoInteract.setString(7, eventVideoInteract.getEventName());
				if(eventVideoInteract.getEventNo() == null)
					psVideoInteract.setNull(8, Types.SMALLINT);
				else
					psVideoInteract.setShort(8, eventVideoInteract.getEventNo());
				psVideoInteract.setString(9, eventVideoInteract.getVideoSysName());
				psVideoInteract.setString(10, eventVideoInteract.getVideoTitle());
				psVideoInteract.setString(11, eventVideoInteract.getChapterSysName());
				psVideoInteract.setString(12, eventVideoInteract.getChapterTitle());
				if ( eventVideoInteract.getOldSeekTime() == null)
					psVideoInteract.setNull(13, Types.FLOAT);
				else
					psVideoInteract.setFloat(13, eventVideoInteract.getOldSeekTime());
				if ( eventVideoInteract.getCurrSeekTime() == null)
					psVideoInteract.setNull(14, Types.FLOAT);
				else
					psVideoInteract.setFloat(14, eventVideoInteract.getCurrSeekTime());
				psVideoInteract.setString(15, eventVideoInteract.getVideoNavigType());
				if ( eventVideoInteract.getOldSpeed() == null)
					psVideoInteract.setNull(16, Types.FLOAT);
				else
					psVideoInteract.setFloat(16, eventVideoInteract.getOldSpeed());
				if ( eventVideoInteract.getCurrSpeed() == null)
					psVideoInteract.setNull(17, Types.FLOAT);
				else
					psVideoInteract.setFloat(17, eventVideoInteract.getCurrSpeed());
				psVideoInteract.setString(18, eventVideoInteract.getSource());
		/*		if (videoInteract.getVideoPosition() == null)
					psVideoInteract.setNull(15, Types.TIME);
				else
					psVideoInteract.setTime(15, new java.sql.Time(videoInteract
							.getVideoPosition().getTime()));*/
				
					psVideoInteract.setTimestamp(19, eventVideoInteract.getCreateDateTime());
					psVideoInteract.setTimestamp(20, eventVideoInteract.getLastModDateTime());
					psVideoInteract.execute();
					//eventId= getLastId.copyLastId("EventVideoInteract");
				//return -1L;

			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("Insert Video Error " + e.toString());
				
			}
		}
	 	public void insertCourse() {
	 		/*	System.out.println("lmsName " + eventCourseInteract.getLmsName());
	 			System.out.println("orgName " + eventCourseInteract.getOrgName());
	  			System.out.println("courseName " + eventCourseInteract.getCourseName());
	 			System.out.println("courseRun " + eventCourseInteract.getCourseRun());
	 			System.out.println("lmsUserId " + eventCourseInteract.getLmsUserId());
	 			System.out.println("eventName " + eventCourseInteract.getEventName());
	 			System.out.println("eventNo " + eventCourseInteract.getEventNo());
	 			System.out.println("moduleType " + eventCourseInteract.getModuleType());
	 			System.out.println("moduleSysName " + eventCourseInteract.getModuleSysName());
	 			System.out.println("moduleTitle " + eventCourseInteract.getModuleTitle());
	 			System.out.println("chapterSysName " + eventCourseInteract.getChapterSysName());
	 			System.out.println("chapterTitle " + eventCourseInteract.getChapterTitle());
	 		   	System.out.println("createDateTime " + eventCourseInteract.getCreateDateTime());
	 		   	System.out.println("modDateTime " + eventCourseInteract.getModDateTime());
	 			System.out.println("oldPosition " + eventCourseInteract.getOldPosition());
	 			System.out.println("curPosition " + eventCourseInteract.getCurPosition());
	 			System.out.println("Source " + eventCourseInteract.getSource());*/
	 		try {
				psCourseInteract.setString(1, eventCourseInteract.getLmsName());
				psCourseInteract.setString(2, eventCourseInteract.getOrgName()); 
				psCourseInteract.setString(3, eventCourseInteract.getCourseName()); 
				psCourseInteract.setString(4, eventCourseInteract.getCourseRun());
				if(eventCourseInteract.getLmsUserId() == null)
					psCourseInteract.setNull(5, java.sql.Types.BIGINT);
				else
					psCourseInteract.setLong(5, eventCourseInteract.getLmsUserId()); 
				psCourseInteract.setString(6, eventCourseInteract.getEventName());
				if(eventCourseInteract.getEventNo() == null)
					psCourseInteract.setNull(7, Types.SMALLINT);
				else
					psCourseInteract.setShort(7, eventCourseInteract.getEventNo());
				psCourseInteract.setString(8, eventCourseInteract.getModuleType());
				psCourseInteract.setString(9, eventCourseInteract.getModuleSysName());
				psCourseInteract.setString(10, eventCourseInteract.getModuleTitle());
				psCourseInteract.setString(11, eventCourseInteract.getChapterSysName());
				psCourseInteract.setString(12, eventCourseInteract.getChapterTitle());
				psCourseInteract.setTimestamp(13, eventCourseInteract.getCreateDateTime());
				psCourseInteract.setTimestamp(14, eventCourseInteract.getModDateTime());
				if(eventCourseInteract.getOldPosition() == null)
					psCourseInteract.setNull(15, java.sql.Types.SMALLINT);
				else
					psCourseInteract.setShort(15, eventCourseInteract.getOldPosition());
				if(eventCourseInteract.getCurPosition() == null)
					psCourseInteract.setNull(16, java.sql.Types.SMALLINT);
				else
					psCourseInteract.setShort(16, eventCourseInteract.getCurPosition()); 
				psCourseInteract.setString(17, eventCourseInteract.getSource());
				System.out.println("BEFORE EXECUTE");
				psCourseInteract.execute();
				System.out.println("AFTER EXECUTE");
			    //eventId = getLastId.copyLastId("EventCourseInteract");
			    System.out.println("eventId " + eventId);
				//return lastid;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				   System.out.println(e.toString());
				
				
			}
			
		}	
	 	private void insertProbRec() {
			try {
				 
				psProbInteract.setString(1, eventProbInteract.getLmsName());
				psProbInteract.setString(2, eventProbInteract.getOrgName()); 
				psProbInteract.setString(3, eventProbInteract.getCourseName());
				psProbInteract.setLong(4, eventProbInteract.getLmsUserId());
				psProbInteract.setString(5, eventProbInteract.getEventName());
				if(eventProbInteract.getEventNo() ==null)
					psProbInteract.setNull(6, Types.SMALLINT);
				else
					psProbInteract.setShort(6, eventProbInteract.getEventNo());
				psProbInteract.setString(7, eventProbInteract.getQuizzSysName());
				psProbInteract.setString(8, eventProbInteract.getQuizzTitle());
				psProbInteract.setString(9, eventProbInteract.getChapterSysName());
				psProbInteract.setString(10, eventProbInteract.getChapterTitle());
				psProbInteract.setString(11, eventProbInteract.getHintAvailable());;
				psProbInteract.setString(12, eventProbInteract.getHintMode()); 
				psProbInteract.setString(13, eventProbInteract.getInputType());;
				psProbInteract.setString(14, eventProbInteract.getResponseType()); 
				psProbInteract.setString(15, eventProbInteract.getVariantId());
				if (eventProbInteract.getOldScore() == null)
					psProbInteract.setNull(16, Types.DOUBLE);
				else
					psProbInteract.setDouble(16, eventProbInteract.getOldScore());
				if (eventProbInteract.getNewScore() == null)
					psProbInteract.setNull(17, Types.DOUBLE);
				else
					psProbInteract.setDouble(17, eventProbInteract.getNewScore());
				if (eventProbInteract.getMaxGrade() == null)
					psProbInteract.setNull(18, Types.DOUBLE);
				else
					psProbInteract.setDouble(18, eventProbInteract.getMaxGrade());
				
				if (eventProbInteract.getAttempts() == null)
					psProbInteract.setNull(19, Types.INTEGER);
				else
					psProbInteract.setInt(19, eventProbInteract.getAttempts());
				if (eventProbInteract.getMaxAttempts() == null)
					psProbInteract.setNull(20, Types.INTEGER);
				else
					psProbInteract.setInt(20, eventProbInteract.getMaxAttempts());
				psProbInteract.setString(21, eventProbInteract.getChoice());
				psProbInteract.setString(22, eventProbInteract.getSuccess());
				psProbInteract.setString(23, eventProbInteract.getSource());
				if (eventProbInteract.getProbSubTime() == null)
					psProbInteract.setNull(24, Types.DATE);
				else
					psProbInteract.setDate(24, new java.sql.Date(eventProbInteract.getProbSubTime().getTime()));
				if(eventProbInteract.getDone() ==null)
					psProbInteract.setNull(25, Types.VARCHAR);
				else
					psProbInteract.setString(25, eventProbInteract.getDone());
				psProbInteract.setDate(26, new java.sql.Date(eventProbInteract.getCreateDateTime().getTime()));
				psProbInteract.setDate(27, new java.sql.Date(eventProbInteract.getLastModDateTime().getTime()));
				psProbInteract.setString(28, eventProbInteract.getCourseRun());
				psProbInteract.execute();
			
				//eventId = getLastId.copyLastId("EventProbInteract");
				//return -1l;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				System.out.println("SQL ERROR IN INSERTING REC IN EVENPROBINTERACT " + e.toString());
				
			}
		}	
	 	public void insertUserSession(){
	 		try{
	 		psUserSession.setString(1, userSession.getSessionSysName());
			psUserSession.setString(2, userSession.getLmsName());
			psUserSession.setString(3, userSession.getOrgName()); 
			psUserSession.setString(4, userSession.getCourseName()); 
			psUserSession.setString(5, userSession.getCourseRun());
			psUserSession.setLong(6, userSession.getLmsUserId());
			psUserSession.setString(7, userSession.getUserName()); 
			psUserSession.setString(8, userSession.getAgent()); 
			psUserSession.setString(9, userSession.getHostName());
			psUserSession.setString(10, userSession.getIpAddress());
		//	psUserSession.setString(11, userSession.getUrl());
			psUserSession.setDate(12, new java.sql.Date(userSession.getCreateDateTime().getTime()));
			psUserSession.setString(13, userSession.getEventType());
			psUserSession.setString(14, userSession.getEventSource());
			psUserSession.setString(15, userSession.getEventName());
			if(userSession.getSessionId() == null)
				psUserSession.setNull(16, Types.BIGINT);
			else
				psUserSession.setLong(16, userSession.getSessionId());
			psUserSession.setDate(17, new java.sql.Date(userSession.getLastModDateTime().getTime()));
			psUserSession.setString(18, userSession.getDataSource());
			psUserSession.setShort(19, userSession.getEventNo());
			psUserSession.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("SQL ERROR IN INSERT REC userSession " + e.toString());
		}	
	 	}
	/* private void getEventNos(Properties properties){
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
	 }*/
}
