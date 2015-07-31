
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import LMSDataLoader.Dao.CourseChapterDao;
import LMSDataLoader.Dao.CourseChapterSessionDao;
import LMSDataLoader.Dao.CourseDao;
import LMSDataLoader.Dao.CourseDiscussionsDao;
import LMSDataLoader.Dao.CourseOthersDao;
import LMSDataLoader.Dao.CourseProblemsDao;
import LMSDataLoader.Dao.CourseVerticalDao;
import LMSDataLoader.Dao.CourseVideosDao;
import LMSDataLoader.dataModels.Course;
import LMSDataLoader.dataModels.CourseChapter;
import LMSDataLoader.dataModels.CourseChapterSession;
import LMSDataLoader.dataModels.CourseData;
import LMSDataLoader.dataModels.CourseDiscussions;
import LMSDataLoader.dataModels.CourseOthers;
import LMSDataLoader.dataModels.CourseProblems;
import LMSDataLoader.dataModels.CourseVertical;
import LMSDataLoader.dataModels.CourseVideos;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;










//import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
// Extend HttpServlet class
@WebServlet("/EdxMongoReader")
public class EdxMongoReader extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1260045197860625975L;
	private MongoClient mongoClient;
	private String mongoDBName, courseName = null;
	private String lmsName = null;
	private DBCollection edxCollection = null;
	private Course edxCourse = null;
	private String chapterSysName;
	private SimpleDateFormat mongoDateFormat;
	
	//private String quizTitle, quizType,showAnswer;
	private String  quizType,showAnswer, quizTitle;
	private Short correctChoice;
	
	
	String orgName, courseTitle, language, currencyCode;
	private String courseRun = null;
	private String currCourseConcept, prevCourseConcept, currChapterConcept, chapterTitle;
	String startDtStr, endDtStr;
	private CourseDao courseDao = null;
	private CourseOthersDao courseOthersDao = null;
	private CourseVerticalDao courseVerticalDao = null;
	private CourseChapterDao courseChapterDao = null;
	private CourseProblemsDao courseProblemsDao = null;
	private CourseVideosDao courseVideosDao = null;
	private CourseDiscussionsDao courseDiscussDao = null;
	private CourseChapterSessionDao courseChapterSessionDao = null;
	private String sessionType= null;
	
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
			
			courseDao = new CourseDao(conn);
			courseChapterDao = new CourseChapterDao(conn);
			courseProblemsDao = new CourseProblemsDao(conn);
			courseVideosDao = new CourseVideosDao(conn);
			courseDiscussDao = new CourseDiscussionsDao(conn);
			courseChapterSessionDao = new CourseChapterSessionDao(conn);
			courseVerticalDao = new CourseVerticalDao(conn);
			courseOthersDao = new CourseOthersDao(conn);
			
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
		  //System.out.println("MongoClient open " );
		  // Read Property File
		 // String propFileName = lmsName +  "DataLoader.properties";
		  String propFileName = "EdxParams.properties";
		  InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);  
		  Properties properties = new Properties();  
	  	  properties.load(inputStream); 
		  mongoDBName = properties.getProperty("MONGODBNAME");
	   	  mongoDateFormat = new SimpleDateFormat(properties.getProperty("MONGODATEFORMAT"));
	   	//System.out.println("TODAY " + mongoDateFormat.format(new java.util.Date()));
	   	 // System.out.println("mongoDBName " + mongoDBName + "DATE FORMAT " + properties.getProperty("MONGODATEFORMAT"));
	   	  DB db = mongoClient.getDB(mongoDBName);
	   	 edxCollection = db.getCollection("modulestore");
	  } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
		  System.out.println("MongoDB Not open " + e.toString());
			e.printStackTrace();
	  }
	  processCourses();
	  response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<h1>Done</h1>");
  }
 private void processCourses(){
	  // Taking only courseTitle, startDate & endDate	  
	  java.util.Date endDate = null, startDate = null;
	  Map<String,String> courseCourseQry = new HashMap<String, String>();
	  courseCourseQry.put("_id.category","course");
	  //courseCourseQry.put("_id.course",courseName);
	  //BasicDBObject aboutQuery = new BasicDBObject("_id.category","about");
	  BasicDBObject courseQuery = new BasicDBObject(courseCourseQry);
	  DBCursor courseCursor = edxCollection.find(courseQuery);
	  //System.out.println(" course and course count " + courseCursor.count());
	  while (courseCursor.hasNext()){
		  	edxCourse = new Course();
	    	DBObject courseDo = courseCursor.next();
	    	//System.out.println("cousrdo " + courseDo.toString());
	    	DBObject dbId = (DBObject) courseDo.get("_id");
	    	courseName = dbId.get("course").toString();
	    	orgName = dbId.get("org").toString();
	    	courseRun = dbId.get("name").toString();
	    	//System.out.println("course Run " + courseRun);
	    	//System.out.println("course Name " + courseName);
	    	DBObject defs = (DBObject) courseDo.get("definition");
	    	
	    	String childStr = defs.get("children").toString();
	    	// Process Children
	    	String[] childStrs = childStr.split(",");
	    	
	    	//courseRun = new String(courseDo.get("_id.name").toString());
	    	DBObject meta = (DBObject) courseDo.get("metadata");
	    	//System.out.println(" meta data " + meta.toString());
	    	courseTitle = meta.get("display_name").toString();
	    	//System.out.println(" courseTitle " + courseTitle);
	    	try {
	    		if(meta.get("end") != null)
	    			endDate  = mongoDateFormat.parse(meta.get("end").toString());
	    		else
	    			endDate = null;
	    		if(meta.get("start") != null)
	    			startDate = mongoDateFormat.parse(meta.get("start").toString());
	    		else
	    			startDate = null;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//System.out.println(" endDate " + endDate);
	    	//System.out.println(" startDate " + startDate);
	    	processAboutShort();
	    	//edxCourse.setAuthorUserId(authorUserId);
	    	//edxCourse.setCourseId(courseId);
	    	edxCourse.setCourseName(courseName);
	    	edxCourse.setCourseTitle(courseTitle);
	    	edxCourse.setCurrConcepts(currCourseConcept);
	    	//edxCourse.setCurrencyCode(childStr);
	    	edxCourse.setEndDate(endDate);
	    	//edxCourse.setLanguage(childStr);
	    	edxCourse.setLmsName(lmsName);
	    	edxCourse.setMinPrice(0);
	    	edxCourse.setOrgName(orgName);
	    	edxCourse.setPrevConcepts(prevCourseConcept);
	    	edxCourse.setStartDate(startDate);
	  	  	edxCourse.setStartDate(startDate);
	  	  	edxCourse.setEndDate(endDate);

	    	/* For Concept get about */
	    	
	    	courseDao.insertRec(edxCourse);
	    	processChapters(childStrs);
	    }
	  //System.out.println("courseTitle " + courseTitle );
	//  edxCourse.setCourseRun(courseRun);
 }
 private void processAboutShort(){
	  // Taking only preconcept & courseAboutConcept
	 currCourseConcept= null;
	 prevCourseConcept = null;
	  Map<String,String> aboutCourseQry = new HashMap<String, String>();
	  aboutCourseQry.put("_id.category","about");
	  aboutCourseQry.put("_id.course",courseName);
	  aboutCourseQry.put("_id.name","short_description");
	  //aboutCourseQry.put("_id.course",courseName);
	  //BasicDBObject aboutQuery = new BasicDBObject("_id.category","about");
	  BasicDBObject aboutQuery = new BasicDBObject(aboutCourseQry);
	  DBCursor aboutCursor = edxCollection.find(aboutQuery);
	 //System.out.println(" course and about count " + aboutCursor.count());
	  while (aboutCursor.hasNext()){
	    	DBObject aboutDo = aboutCursor.next();
	    	//DBObject dbId = (DBObject) aboutDo.get("_id");
	    	
	    	DBObject dbDef = (DBObject) aboutDo.get("definition");
	    	DBObject abt =  (DBObject)dbDef.get("data");
	    	//System.out.println("abtStr " + abt.toString());
	    	String abtStr = abt.get("data").toString();
	    	//System.out.println("abtStr " + abtStr);
	    	String tmpStr[] = null;
	    	String tmpStr1[] = null;
	    	String tmpStr2[] = null;
	    	if(abtStr.length() != 0) {
	    		String abtSplitStr = "<section class=\"about\"";
	    		String preSplitStr = "<section class=\"prerequisites\"";
		    	if(abtStr.contains(abtSplitStr)){
		    		tmpStr = abtStr.split(abtSplitStr);
		    		//tmpStr1 = tmpStr[1].split("<p>");
		    		tmpStr1 = tmpStr[1].split("<p align=justify>");
		    		tmpStr2 = tmpStr1[1].split("</p>");
		    		currCourseConcept = tmpStr2[0];
		    	}
		    	if(abtStr.contains(preSplitStr)){
		    		tmpStr = abtStr.split(preSplitStr);
		    		//tmpStr1 = tmpStr[1].split("<p>");
		    		tmpStr1 = tmpStr[1].split("<p align=justify>");
		    		//System.out.println("tmpStr[1] " + tmpStr[1]);
		    		tmpStr2 = tmpStr1[1].split("</p>");
		    		prevCourseConcept = tmpStr2[0];
		    	}
		    	//System.out.println(" prevCourseConcept " + prevCourseConcept);
		    	//System.out.println(" currCourseConcept " + currCourseConcept);
		    	//System.out.println(" orgName " + orgName);
		    }
	    }
 }
	  
  private void processChapters(String[] childStrs){
	  // Will enter as many chapters as available
	  //System.out.println(" No. Of Children Are  " + childStrs.length);
	  int position =0;
	  java.util.Date chapterStartDate = null;
	if(((childStrs.length == 1) && (childStrs[0].contains("chapter"))) || (childStrs.length > 1)) {
  	for (int iCourseChild=0; iCourseChild < childStrs.length; iCourseChild++){
  		chapterSysName = childStrs[iCourseChild].substring(childStrs[iCourseChild].indexOf("chapter") + 8, childStrs[iCourseChild].indexOf("chapter") + 40);
  		//String chapterName = childStrs[iCourseChild].substring(childStrs[iCourseChild].indexOf("chapter"));
  		//System.out.println(" chapter Names ARE " + chapterSysName);
  		Map<String,String> courseChapterQry = new HashMap<String, String>();
  		courseChapterQry.put("_id.category","chapter");
  		courseChapterQry.put("_id.course",courseName);
  		courseChapterQry.put("_id.name",chapterSysName);
  		//BasicDBObject aboutQuery = new BasicDBObject("_id.category","about");
  		BasicDBObject chapterQuery = new BasicDBObject(courseChapterQry);
  		DBCursor chapterCursor = edxCollection.find(chapterQuery);
  		//System.out.println(" chapter Sequential count " + chapterCursor.count());
  		position ++;
  		while (chapterCursor.hasNext()){
		    CourseChapter courseChapter = new CourseChapter();
	    	DBObject courseDo = chapterCursor.next();
	    	DBObject meta = (DBObject) courseDo.get("metadata");
	    	DBObject id = (DBObject) courseDo.get("_id");
	    	DBObject def = (DBObject) courseDo.get("definition");
	    	//List<Object> children = Arrays.asList(def.get("children"));
	    	String childChapterStr = def.get("children").toString();
	    	//System.out.println("Children " + childChapterStr); 
	    	String tmpStr[] = childChapterStr.split(",");
	    	//System.out.println("No Of Sequential Children " + tmpStr.length);
	    	// Loop over no. Of Sequentials / Sections of a CXhapter
	    	String[] seqArray = new String[tmpStr.length];
	    	Boolean childFlag = false;
	    	for(int iSeq =0; iSeq < tmpStr.length; iSeq++){
	    		if(iSeq != tmpStr[iSeq].length()){
	    			if(tmpStr[iSeq].lastIndexOf("/") > 0){
	    				seqArray[iSeq] = tmpStr[iSeq].substring(tmpStr[iSeq].lastIndexOf("/")+ 1, tmpStr[iSeq].lastIndexOf("\""));
	    				childFlag = true;
	    			}
	    		}
	    		else{
	    			if(tmpStr[iSeq].lastIndexOf("/") > 0){
	    				seqArray[iSeq] = tmpStr[iSeq].substring(tmpStr[iSeq].lastIndexOf("/") + 1, (tmpStr[iSeq].lastIndexOf("\"")) - 1);
	    				childFlag = true;
	    			}
	    		}		
	    	}
	    	// Process Sequentials within a chapter
	    	if(childFlag == true) {
		    	for(int iSeq =0; iSeq < seqArray.length; iSeq++){
		    		//System.out.println("Sew aer " + seqArray[iSeq].toString());
		    		processSequential(seqArray[iSeq],iSeq);
		    	// Loop over each Sequential to find the verticals
		    	}
	    	}
	    	
	    	//System.out.println("Children size " + tmpStr.length + " String " + tmpStr[0] + " String 2 " + tmpStr[1]);
	    	// Find Children
	    	 
	    	chapterTitle = meta.get("display_name").toString();
	    	try {
	    		if(meta.get("start") != null)
	    			chapterStartDate = mongoDateFormat.parse(meta.get("start").toString());
	    		else
	    			chapterStartDate = null;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//System.out.println("Chapter TchapterSysName " + chapterSysName);
	    	currChapterConcept =chapterTitle; 
	    	chapterSysName = id.get("name").toString();
	    	courseChapter.setchapterSysName(chapterSysName);
	    	courseChapter.setChapterTitle(chapterTitle);
	    	courseChapter.setCourseName(courseName);
	    	courseChapter.setLmsName(lmsName);
	    	courseChapter.setOrgName(orgName);
	    	courseChapter.setPosition(position);
	    	courseChapter.setChapterStartDate(chapterStartDate);
	 // insert in CourseChapter Data   
	    	courseChapterDao.insertRec(courseChapter);
	  }
  	}
	  }
  }
  private void processSequential(String seqName, int position){
	  // Will enter as many chapters as available
	  Map<String,String> courseSeqQry = new HashMap<String, String>();
	  courseSeqQry.put("_id.category","sequential");
	  courseSeqQry.put("_id.course",courseName);
	  courseSeqQry.put("_id.name",seqName);
      //BasicDBObject aboutQuery = new BasicDBObject("_id.category","about");
	  BasicDBObject seqQuery = new BasicDBObject(courseSeqQry);
	  DBCursor seqCursor = edxCollection.find(seqQuery);
	  //System.out.println(" course and course count " + seqCursor.count());
	  //System.out.println("NSequentila Name " + seqName);
	  while (seqCursor.hasNext()){
	    	DBObject seqDo = seqCursor.next();
	    	DBObject meta = (DBObject) seqDo.get("metadata");
	    	String seqTitle = meta.get("display_name").toString();
	    	//System.out.println("seqName " + seqName + " seqTitle " + seqTitle);
	    	CourseChapterSession courseChapterSession = new CourseChapterSession();
	    	courseChapterSession.setChapterSysName(chapterSysName);
	    	courseChapterSession.setCourseName(courseName);
	    	courseChapterSession.setLmsName(lmsName);
	    	courseChapterSession.setOrgName(orgName);
	    	courseChapterSession.setPosition((short)position);
	    	//System.out.println("start date " + meta.get("start"));
	    	//courseChapterSession.setSessionId(sessionId);
	    	if(meta.get("start") != null){
	    	try {
				courseChapterSession.setSessionStartDate(mongoDateFormat.parse(meta.get("start").toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	}
	    	else
	    		courseChapterSession.setSessionStartDate(null);
	    	courseChapterSession.setSessionSysName(seqName);
	    	courseChapterSession.setSessionTitle(seqTitle);
	    	courseChapterSessionDao.insertRec(courseChapterSession);
	    	DBObject def = (DBObject) seqDo.get("definition");
	    	//List<Object> children = Arrays.asList(def.get("children"));
	    	String childStr = def.get("children").toString();
	    	//System.out.println("Children " + childStr); 
	    	String tmpStr[] = childStr.split(",");
	    	
	    	//System.out.println("No Of Children " + tmpStr.length);
	    	// Loop over no. Of Sequentials / Sections of a CXhapter
	    	String[] vertArray = new String[tmpStr.length];
	    	for(int iSeq =0; iSeq < tmpStr.length; iSeq++){
	    		//System.out.println("iseq " + iSeq);
	    		//System.out.println("tmpStr[iSeq] " + tmpStr[iSeq]);
	    		if(tmpStr[iSeq].lastIndexOf("/") != -1){
	    		if(iSeq != tmpStr[iSeq].length())
	    			vertArray[iSeq] = tmpStr[iSeq].substring(tmpStr[iSeq].lastIndexOf("/")+ 1, tmpStr[iSeq].lastIndexOf("\""));
	    		else
	    			vertArray[iSeq] = tmpStr[iSeq].substring(tmpStr[iSeq].lastIndexOf("/") + 1, (tmpStr[iSeq].lastIndexOf("\"")) - 1);
	    		}
	    		//System.out.println(" vertArray[iSeq " + vertArray[iSeq]);
	    	}
	    	// Process verticals within a sequential
	    	
	    	for(int iSeq =0; iSeq < vertArray.length; iSeq++){
	    		processVertical(vertArray[iSeq], seqName);
	    	// Loop over each Sequential to find the verticals
	    	}
	    }
  }
  private void processVertical(String vertName, String seqName){
	  // Will enter as many chapters as available
	  Map<String,String> courseVertQry = new HashMap<String, String>();
	  courseVertQry.put("_id.category","vertical");
	  courseVertQry.put("_id.course",courseName);
	  courseVertQry.put("_id.name",vertName);
      //BasicDBObject aboutQuery = new BasicDBObject("_id.category","about");
	  BasicDBObject vertQuery = new BasicDBObject(courseVertQry);
	  DBCursor vertCursor = edxCollection.find(vertQuery);
	  //System.out.println(" course and course count " + vertCursor.count());
	  while (vertCursor.hasNext()){
		  DBObject seqDo = vertCursor.next();
	    	DBObject meta = (DBObject) seqDo.get("metadata");
	    	String vertTitle = meta.get("display_name").toString();
	    	//System.out.println("vertName " + vertName + " vertTitle " + vertTitle);
	    	DBObject def = (DBObject) seqDo.get("definition");
	    	//List<Object> children = Arrays.asList(def.get("children"));
	    	String childStr = def.get("children").toString();
	    	
	    	CourseVertical courseVertical = new CourseVertical();
	    	
	    	courseVertical.setCourseName(courseName);
	    	courseVertical.setLmsName(lmsName);
	    	courseVertical.setOrgName(orgName);
	    	
	    	courseVertical.setSessionSysName(seqName);
	    	courseVertical.setVerticalSysName(vertName);
	    	
	    	courseVerticalDao.insertRec(courseVertical);
	    	
	    	if(courseName.equals("CS101.1x")){
	    		//System.out.println("CS101.1x Vertical Children " + childStr);
	    		} 
	    	String tmpStr[] = childStr.split(",");
	    //	System.out.println("No Of Children " + tmpStr.length);
	    	// Loop over no. Of Sequentials / Sections of a CXhapter
	    	String[] vertArray = new String[tmpStr.length];
	    	for(int iSeq =0; iSeq < tmpStr.length; iSeq++){
	    		String tmpStr1[] = tmpStr[iSeq].split("/");
	    		Integer noOfSplits = tmpStr1.length; 
	    	//	System.out.println("noOfSplits " + noOfSplits);
	    		if(noOfSplits >=2 ){
		    		String vertType = tmpStr1[noOfSplits - 2];
		    		vertArray[iSeq] = tmpStr1[noOfSplits - 1].substring(0,tmpStr1[noOfSplits - 1].indexOf("\"") );
		    		// Find problems 
		    	//	System.out.println("vertType " + vertType);
		    		if(noOfSplits >=2 ){
		    		if(vertType.equals("problem")) {
		    			correctChoice = 0;
		    			if(courseName.equals("CS101.1x"))
		    			{}//System.out.println("CS101.1x  vertArray[iSeq] " + vertArray[iSeq]  + "  Vertical " + vertName );
		    			if(vertArray[iSeq].equals("cf22e34046d646c5bb107f459c1ed589")){
		    				{}//System.out.println("FOUND COURSE " + courseName + " Vertical " + vertName );
		    			}
		    			processProblem(vertArray[iSeq]);
		    		}
		    		else if(vertType.equals("html")){
		    			processHtmls(vertArray[iSeq], vertName, seqName);
		    		}
		    		else if(vertType.equals("discussion")){
		    			processDiscuss(vertArray[iSeq]);
		    		}
		    		else if(vertType.equals("video")){
		    			processVideos(vertArray[iSeq]);
		    		}
		    	
		    		}
	    		}
	    		//else
	    		//	System.out.println(" noOfSplits is < 2 total No " + tmpStr1.length + " Actual data " + tmpStr[iSeq]);
	    	}
	    }
  }
  private void processHtmls(String htmlName, String vertName, String seqName){
	  Map<String,String> courseHtmlQry = new HashMap<String, String>();
	  courseHtmlQry.put("_id.category","html");
	  courseHtmlQry.put("_id.course",courseName);
	  courseHtmlQry.put("_id.name",htmlName); 
	  BasicDBObject htmlQuery = new BasicDBObject(courseHtmlQry);
	  DBCursor htmlCursor = edxCollection.find(htmlQuery);
	  //System.out.println(" html and html count " + htmlCursor.count());
	  while (htmlCursor.hasNext()){
	    	DBObject htmlDo = htmlCursor.next();
	    	DBObject defDO = (DBObject) htmlDo.get("definition");
	    	//System.out.println("HTML DEDo " + defDO);
	    	DBObject metaDO = (DBObject) htmlDo.get("metadata");
	    	//System.out.println("HTML metaDO " + metaDO);
	    	
	    	DBObject dataDO = (DBObject) defDO.get("data");
	    	String source = "";
	    	
	    	if(dataDO.get("data") != null)
	    	{
	    	source = dataDO.get("data").toString();
	    	}
	 
	    	String type = null;
	    	
	    	if (source.contains(".pdf\\")) {
	    		type = "pdf";
	    	}
	    	else if (source.contains(".jpg\\")) {
	    		type = "jpg";
	    	}
	    	else {
	    		type = "html";
	    	}
	    	String vertTitle="";
	    	
	    	if(metaDO.get("display_name") != null)
	    	{
	    	vertTitle = metaDO.get("display_name").toString();
	    	}	
		    	
		    	
	    	CourseOthers courseOthers = new CourseOthers();
	    	
	    	courseOthers.setType(type);
	    	courseOthers.setTitle(vertTitle);
	    	courseOthers.setCourseName(courseName);
	    	courseOthers.setLmsName(lmsName);
	    	courseOthers.setOrgName(orgName);
	    	courseOthers.setChapterSysName(chapterSysName);
	    	courseOthers.setHtmlSysName(htmlName);
	    	
	    	courseOthers.setSessionSysName(seqName);
	    	courseOthers.setVerticalSysName(vertName);
	    	
	    	courseOthersDao.insertRec(courseOthers);
	    	
	  }
  }
  private void processDiscuss(String discussName){
	  String discussTitle = null,  discussSysId = null;
	  
	  Map<String,String> courseDiscussQry = new HashMap<String, String>();
	  courseDiscussQry.put("_id.category","discussion");
	  courseDiscussQry.put("_id.course",courseName);
	  courseDiscussQry.put("_id.name",discussName);
	  
	  BasicDBObject DiscussQuery = new BasicDBObject(courseDiscussQry);
	  DBCursor DiscussCursor = edxCollection.find(DiscussQuery);
	 // System.out.println(" Discuss and Discuss count " + DiscussCursor.count());
	  while (DiscussCursor.hasNext()){
		  	CourseDiscussions courseDiscuss = new CourseDiscussions();
		 	DBObject discussDo = DiscussCursor.next();
	    	DBObject defDO = (DBObject) discussDo.get("definition");
	    	//System.out.println("Discuss DEDo " + defDO);
	    	DBObject metaDO = (DBObject) discussDo.get("metadata");
	    //	System.out.println("Discuss metaDO " + metaDO);
	    	if(metaDO.get("discussion_target") != null)
	    		discussTitle = metaDO.get("discussion_target").toString();
	    	else
	    		discussTitle = "";
			discussSysId = metaDO.get("discussion_id").toString();
		
			courseDiscuss.setLmsName(lmsName);
		  	courseDiscuss.setOrgName(orgName);
		  	courseDiscuss.setCourseName(courseName);
		  	courseDiscuss.setDiscussionTitle(discussTitle);
		  	courseDiscuss.setChapterSysName(chapterSysName);
	    	courseDiscuss.setDiscussionSysName(discussName);
			courseDiscuss.setDiscussSysId(discussSysId);
			
			//System.out.println("Before discuss insertrec courseName " + courseName + " discussTitle " + discussTitle);
			//System.out.println("Before discuss insertrec discussName " + discussName + "discussSysId " + discussSysId);
			courseDiscussDao.insertRec(courseDiscuss);
	  }
  }
  private void processVideos(String videoName){
	  String uTube1 = null, uTube2 = null,uTube3 = null,uTube4 = null;
	  String videoTitle = null;
	  Integer videoDownLoad = 0, videoTrackDownLoad = 0;
	  Map<String,String> courseVideoQry = new HashMap<String, String>();
	  courseVideoQry.put("_id.category","video");
	  courseVideoQry.put("_id.course",courseName);
	  courseVideoQry.put("_id.name",videoName); 
	  BasicDBObject videoQuery = new BasicDBObject(courseVideoQry);
	  DBCursor videoCursor = edxCollection.find(videoQuery);
	 // System.out.println(" Video and Video count " + videoCursor.count() + " videoName " + videoName);
	  while (videoCursor.hasNext()){
		  	CourseVideos courseVideos = new CourseVideos();
	    	DBObject videoDo = videoCursor.next();
	    	DBObject defDO = (DBObject) videoDo.get("definition");
	    //	System.out.println("Video DEDo " + defDO);
	    	DBObject metaDO = (DBObject) videoDo.get("metadata");
	    //	System.out.println("Video metaDO " + metaDO);
	    	if(metaDO.get("youtube_id_1_0") != null)
	    		uTube1 = metaDO.get("youtube_id_1_0").toString();
	    	else
	    		uTube1 = null;
	    	
	    	if(metaDO.get("youtube_id_1_25") != null)
	    		uTube2 = metaDO.get("youtube_id_1_25").toString();
	    	else
	    		uTube2 = null;
	    	if(metaDO.get("youtube_id_1_5") != null)
	    		uTube3 = metaDO.get("youtube_id_1_5").toString();
	    	else
	    		uTube2 = null;
	    	if(metaDO.get("youtube_id_0_75") != null)
	    		uTube4 = metaDO.get("youtube_id_0_75").toString();
	    	else
	    		uTube4 = null;
	    	if(metaDO.get("display_name") != null)
	    		videoTitle= metaDO.get("display_name").toString();
	    	else
	    		videoTitle = null;
	    	
	    //	System.out.println("Video download " + metaDO.get("download_video").toString());
	    	if (metaDO.get("download_video") != null){
	    		if (metaDO.get("download_video").toString().equals("true"))
	    			videoDownLoad = 1;
	    		else
	    			videoDownLoad = 0;
	    	}
	    	else
	    		videoTrackDownLoad = -1;
	    	if (metaDO.get("download_track") != null){
	    		if (metaDO.get("download_track").toString().equals("true"))
	    			videoTrackDownLoad = 1;
	    		else
	    			videoTrackDownLoad = 0;
	    	}
	    	else
	    		videoTrackDownLoad = -1;
	    	courseVideos.setLmsName(lmsName);
	    	courseVideos.setOrgName(orgName);
	    	courseVideos.setCourseName(courseName);
	    	courseVideos.setChapterSysName(chapterSysName);
	    	courseVideos.setVideoTitle(videoTitle);
	    	courseVideos.setVideoUTubeId(uTube1);
	    	courseVideos.setVideoUTubeId125(uTube2);
	    	courseVideos.setVideoUTubeId15(uTube3);
	    	courseVideos.setVideoUTubeId075(uTube4);
	    	courseVideos.setVideoSysName(videoName);
	    	courseVideos.setVideoDownload(videoDownLoad);
	    	courseVideos.setVideoTrackDownLoad(videoTrackDownLoad);
	    	courseVideosDao.insertRec(courseVideos);
	     }
  }
  private void processProblem(String problemName){
	  // Will enter as many chapters as available
	  Map<String,String> courseProblemQry = new HashMap<String, String>();
	  courseProblemQry.put("_id.category","problem");
	  courseProblemQry.put("_id.course",courseName);
	  courseProblemQry.put("_id.name",problemName);
      //BasicDBObject aboutQuery = new BasicDBObject("_id.category","about");
	  BasicDBObject problemQuery = new BasicDBObject(courseProblemQry);
	  DBCursor problemCursor = edxCollection.find(problemQuery);
	//  System.out.println(" course and course count " + problemCursor.count() + " problemName " + problemName);
	  while (problemCursor.hasNext()){
		  	CourseProblems courseProblems = new CourseProblems();
	    	DBObject problemDo = problemCursor.next();
	    	DBObject defDO = (DBObject) problemDo.get("definition");
	    	DBObject metaDO = (DBObject) problemDo.get("metadata");
	    	//System.out.println(" weight " + metaDO.get("weight").toString());
	    	Integer maxAttempt;
	    	if(metaDO.get("max_attempts") != null)
	    		maxAttempt = Integer.parseInt(metaDO.get("max_attempts").toString().trim());
	    	else
	    		maxAttempt = null;
	    	Float weight;
	    	//System.out.println("metaDo " + metaDO);
	    	if(metaDO.get("weight") != null){
	    		//System.out.println("WEIGHT NOT NULL " + metaDO.get("weight").toString());
	    		weight = Float.parseFloat(metaDO.get("weight").toString().trim());
	    	}
	    	else
	    		weight = null;
	    	if(metaDO.get("showanswer") != null){
	    		//System.out.println("Showanswer " + metaDO.get("showanswer").toString());
	    		if(metaDO.get("showanswer").toString().trim().equals("never"))
	    			courseProblems.setHintAvailable((short)0);
	    		else {
	    			courseProblems.setHintAvailable((short)1);
	    			courseProblems.setHintMode(metaDO.get("showanswer").toString().trim());
	    		}
	    	}
	    	//System.out.println("BEFORE quizTitle1 " + quizTitle1);
	    	DBObject dataDO = (DBObject) defDO.get("data");
	    	courseProblems.setLmsName(lmsName);
	    	courseProblems.setOrgName(orgName);
	    	courseProblems.setCourseName(courseName);
	    	courseProblems.setChapterSysName(chapterSysName);
	    	//courseProblems.setChapterTitle(chapterTitle);
	    	quizType = null;
	    	quizTitle = null;
	    	correctChoice = null;
	    	if(dataDO.get("data") != null)
	    		parseXMLString(dataDO.get("data").toString());
	    	//System.out.println("dataDO.get('data') " + dataDO.get("data"));
	    	//System.out.println("QUIZTITLE " + quizTitle);
	    	if(metaDO.get("display_name") != null){
	    		if (quizTitle != null)
	    			courseProblems.setQuizTitle(metaDO.get("display_name").toString() + " " + quizTitle);
	    		else
	    			courseProblems.setQuizTitle(metaDO.get("display_name").toString());
	    	}
	    	else{
	    		if (quizTitle != null)
    			courseProblems.setQuizTitle(quizTitle);
    		else
	    		courseProblems.setQuizTitle(null);
	    	}
	    	//System.out.println("QUIZTITLE courseProblems.setQuizTitle( " + courseProblems.getQuizTitle());
	    	//courseProblems.setChapterName(chapterName);
	    	
	    	//courseProblems.setCourseConcept(courseAboutConcept);
	    	
	    	courseProblems.setQuizType(quizType);
	    	courseProblems.setQuizWeight(weight);
	    	courseProblems.setNoOfAttemptsAllowed(maxAttempt);
	    	//courseProblems.setChapterConcept(currChapterConcept);
	    	courseProblems.setQuizMaxMarks(weight);
	    	courseProblems.setCorrectChoice(correctChoice);
	    	//courseProblems.setQuizConcept(quizConcept);
	    	
	    	courseProblems.setQuizSysName(problemName);
	    	
	    	//System.out.println(" course " + courseName + " quizType " + quizType + " hintAvailable "
	    	//		+ courseProblems.getHintAvailable() + " hint Mode " + courseProblems.getHintMode());
	    	
	 // insert in CourseProblems Data   		    	
	    	courseProblemsDao.insertRec(courseProblems); 	
	    }
  }
  public void parseXMLString(String strXML){
	  SAXParserFactory factory = SAXParserFactory.newInstance();
      try {
		SAXParser saxParser = factory.newSAXParser();
		ProblemStringHandler probStrHandle = new ProblemStringHandler();
		ByteArrayInputStream in = new ByteArrayInputStream(strXML.getBytes());
		InputSource is = new InputSource();
		is.setByteStream(in);
		saxParser.parse(is, probStrHandle); 
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
  }
  public void destroy()
  {
      // do nothing.
  }
  class ProblemStringHandler extends DefaultHandler
      {
	  
	  Short choiceNo = 0;
	  Boolean problemFlag = false, problemPFlag = false, choiceGroupFlag = false;
	  Boolean choiceFlag = false;
	  
       
          public void startDocument() throws SAXException
          {
              //System.out.println("start of the document   : ");
          }
       
          public void endDocument() throws SAXException
          {
              //System.out.println("end of the document document     : ");
          }
       
          public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
          {
              if ("problem".equals(qName))
            	  problemFlag = true;
              else if("p".equals(qName)) {
            	  if(problemFlag == true)
            		  problemPFlag = true;
            	  else
            		  problemPFlag = false;
              }
              else 
            	  problemFlag = false;
              for(int i=0; i < attributes.getLength(); i++){
            	  if (attributes.getLocalName(i).equals("type"))
            		  quizType= attributes.getValue(i);
              }
              if ("choicegroup".equals(qName)){
            	  choiceGroupFlag = true;
            	  for(int i=0; i < attributes.getLength(); i++){
            		  if (attributes.getLocalName(i).equals("type"))
            			  quizType= attributes.getValue(i);
            		  if (attributes.getLocalName(i).equals("label"))
            			  quizTitle= attributes.getValue(i);
            	  }
              }
              if ("choice".equals(qName)){
            	  choiceFlag = true;
            	  choiceNo++;
            	  for(int i=0; i < attributes.getLength(); i++){
            		  if (attributes.getLocalName(i).equals("correct")){
            			 // System.out.println("Correct Value " + attributes.getValue(i) + " Choice No " + choiceNo);
            			  if(attributes.getValue(i).equals("true"))
            				  correctChoice = choiceNo;
            		  }
            			  
            	  }
              }
          }
          public void endElement(String uri, String localName, String qName) throws SAXException
          {
              //User instance has been constructed so pop it from object stack and push in userList
        	  if("p".equals(qName)) {
            	  if(problemPFlag == true)
            		  problemPFlag = false;
              }
        	  //User instance has been constructed so pop it from object stack and push in userList
        	  if ("choicegroup".equals(qName))
            	  choiceGroupFlag = false;
        	  if ("choice".equals(qName)){
            	  choiceFlag = false;
        	  }
          }
       
          public void characters(char[] ch, int start, int length) throws SAXException
          {
              String value = new String(ch, start, length).trim();
       
             /* if( (problemPFlag == true) && (problemPFlag ==true)){
            	  quizTitle = value.trim();
              }*/
             // System.out.println("quizTitle IN PARSE " + quizTitle);
       
          }
      }
}
