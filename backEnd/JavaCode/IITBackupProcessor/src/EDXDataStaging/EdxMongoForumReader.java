
/* This module extracts data from modulestore, The distinct categories of modulestores are 
 * 	"about",
	"chapter", names are same as in course, coded & children have sequential, every chapter has a strart date. display_name & xml file name
	"discussion",
	"course", wiki-slug, GARDE_CUTOFFS, GRADER & Chapters as children & tabs for the course
	"html",
	"problem",
	"sequential", have verticals as children, 
	"vertical", have problems, discussion, html, video etc
	"course_info", - name : handouts, & updates , handouts have textbook 
	"video",
	"combinedopenended",
	"peergrading",
	"static_tab"
	"openassessment",
	tag quizzes with concept problem->vertical->sequential->chapter
 */
package EDXDataStaging;

import java.io.*;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import LMSDataLoader.Dao.CourseForumsDao;
import LMSDataLoader.dataModels.Course;
import LMSDataLoader.dataModels.CourseForums;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

// Extend HttpServlet class
@WebServlet("/EdxMongoForumReader")
public class EdxMongoForumReader extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1260045197860625975L;
	private MongoClient mongoClient;
	private String mongoDBName, courseName = null;
	private String lmsName = null;
	private DBCollection edxCollection = null;
	private Course courseForums = null;
	private String chapterSysName;
	private SimpleDateFormat mongoDateFormat;
	
	//private String quizTitle, quizType,showAnswer;
	private String  quizType,showAnswer;
	private Short correctChoice;
	
	
	String orgName, courseTitle, language, currencyCode;
	private String courseRun = null;
	private String currCourseConcept, prevCourseConcept, currChapterConcept, chapterTitle;
	String startDtStr, endDtStr;
	private CourseForumsDao courseForumsDao = null;
	
	public void init() throws ServletException
	{
      // Get Mongo Connection
	  //static Logger edxLog = Logger.getLogger(EdxMongoReader.class);
	  // get LMS Data Connection
		
		try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/IITBxDb");
			Connection conn = ds.getConnection();
			
			courseForumsDao = new CourseForumsDao(conn);
			System.out.println("DAOS ASSIGNED");
		} catch (NamingException ne) {             
			System.out.println("ERror in Naming " + ne.toString());
			ne.printStackTrace();
		} catch (SQLException e) {      
			System.out.println("ERror in SQL " + e.toString());
			e.printStackTrace();
		}
	  //static Logger edxLog = Logger.getLogger(EdxMongoReader.class);
  }

  public void doPost(HttpServletRequest request,
          HttpServletResponse response)
  throws ServletException, IOException
{
	  doGet(request, response);
}
 public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
	 lmsName = "EDX"; //(String)request.getParameter("lmsName");
	 
	 try {
		  mongoClient = new MongoClient( "localhost" , 27017 );
		  String propFileName = "EdxParams.properties";
		  InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);  
		  Properties properties = new Properties();  
	  	  properties.load(inputStream); 
		  mongoDBName = "cs_comments_service"; //properties.getProperty();
	   	  mongoDateFormat = new SimpleDateFormat(properties.getProperty("MONGODATEFORMAT"));
	   	  lmsName = properties.getProperty("LMSNAME");
	   	  DB db = mongoClient.getDB(mongoDBName);
	   	  edxCollection = db.getCollection("contents");
	  } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
		  System.out.println("MongoDB Not open " + e.toString());
			e.printStackTrace();
	  }
	  processContents();
	  response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<h1>Done</h1>");
  }
 private void processContents(){
	  // Taking only courseTitle, startDate & endDate	  
	  java.util.Date endDate = null, startDate = null;
	  String tmpStr;
	  String[] tmpStrArr;
	  String isoDateFormatStr = "EEE, MMM dd HH:mm:ss IST yyyy";
	  //SimpleDateFormat sdf = new SimpleDateFormat(isoDateFormatStr);
	  CourseForums courseForums = new CourseForums();
	  courseForums.setLmsName(lmsName);
	  Map<String,String> forumContentQry = new HashMap<String, String>();
	  forumContentQry.put("_id.category","course");
	  //forumContentQry.put("_id.course",courseName);
	  //BasicDBObject aboutQuery = new BasicDBObject("_id.category","about");
	  BasicDBObject courseQuery = new BasicDBObject(forumContentQry);
	  DBCursor forumCursor = edxCollection.find();
	  //System.out.println(" course and course count " + forumCursor.count());
	  try {
		  while (forumCursor.hasNext()){
		  	DBObject forumDO = forumCursor.next();
	    	Object tmpObj = (Object) forumDO.get("_id");
	    	System.out.println("dbId." + tmpObj.toString() + " dbId." + 
	    			tmpObj.toString().substring(String.valueOf("ObjectId(").length(), tmpObj.toString().length()-2));
	    	courseForums.setCommentSysId(tmpObj.toString());
	    	courseForums.setCommentType((String)forumDO.get("_type"));
	    	
	    	System.out.println("courseForums.getCommentType() " + courseForums.getCommentType());
	    	//System.out.println(" Abuse Flaggers " + (String)forumDO.get("abuse_flaggers")); //Gave Error it is a JSON String 
	    	if((Boolean)forumDO.get("anonymous") == false){
	    		courseForums.setAnonymousMode("N");
	    	} else
	    		courseForums.setAnonymousMode("Y");
	    	
	    	courseForums.setLmsAuthorId(Long.parseLong((String)forumDO.get("author_id")));
	    	courseForums.setLmsAuthorName((String)forumDO.get("author_username"));
	    	//courseForums.setTitle((String)forumDO.get("body"));
	    	courseForums.setClosed((Boolean)forumDO.get("closed"));
	    	//courseForums.setCommentCount(Integer.parseInt((String)forumDO.get("comment_count"))); // It is an integer
	    	courseForums.setCommentCount((Integer)forumDO.get("comment_count"));
	    	courseForums.setCommentableSysId((String)forumDO.get("commentable_id"));
	    	tmpStrArr = ((String)forumDO.get("course_id")).split("/");
	    	courseForums.setOrgName(tmpStrArr[0]);
	    	courseForums.setCourseName(tmpStrArr[1]);
	    	courseForums.setCourseRun(tmpStrArr[2]);
	    	System.out.println("Endorsed " + (Boolean) forumDO.get("endorsed"));
	    	courseForums.setEndorsed((Boolean) forumDO.get("endorsed"));
	    	System.out.println("created_at " + forumDO.get("created_at"));
	    	System.out.println("created_at " + new java.util.Date(((Date)forumDO.get("created_at")).getTime()));
	    	courseForums.setCreateDateTime((Date)forumDO.get("created_at"));
	    	courseForums.setLastModDateTime((Date)forumDO.get("last_activity_at"));
	    	System.out.println("THREAD TYPE " + (String)forumDO.get("thread_type"));
	    	courseForums.setThreadType((String)forumDO.get("thread_type"));
	    	courseForums.setTitle((String)forumDO.get("title"));
	    	courseForums.setVisible((Boolean)forumDO.get("visible"));
	    	System.out.println("votes.count " + forumDO.get("votes.count"));
	    	courseForums.setTotVoteCount((Integer)forumDO.get("votes.count"));
	    	courseForums.setUpVoteCount((Integer)forumDO.get("votes.up_count"));
	    	courseForumsDao.insertRec(courseForums);
		  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 }
