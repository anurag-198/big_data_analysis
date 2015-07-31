package LMSDataLoader.dataModels;

public class CourseVideos {
	
	Long videoId;
	String lmsName;
	String orgName;
	String courseName;
	String chapterSysName;
//	String sessionSysName;
	String videoSysName;
	String videoUTubeId;
//	String videoPath;
	Integer videoDownload;
	Integer videoTrackDownLoad;
//	String videoSubTitle;
	String videoTitle;
	String videoUTubeId075;
	String videoUTubeId125;
	String videoUTubeId15;
			  
	public Long getVideoId(){
		return this.videoId;
	}
	public String getLmsName(){
		return this.lmsName;
	}
	public String getOrgName(){
		return this.orgName;
	}
	public String getCourseName(){
		return this.courseName;
	}
	public String getChapterSysName(){
		return this.chapterSysName;
	}
/*	public String getSessionSysName(){
		return this.sessionSysName;
	}*/
	public String getVideoSysName(){
		return this.videoSysName;
	}
	public String getVideoUTubeId(){
		return this.videoUTubeId;
	}
/*	public String getVideoPath(){
		return this.videoPath;
	}
*/	public Integer getVideoDownload(){
		return this.videoDownload;
	}
	public Integer getVideoTrackDownLoad(){
		return this.videoTrackDownLoad;
	}
	/*public String getVideoSubTitle(){
		return this.videoSubTitle;
	}*/
	public String getVideoTitle(){
		return this.videoTitle;
	}
	public String getVideoUTubeId075(){
		return this.videoUTubeId075;
	}
	public String getVideoUTubeId125(){
		return this.videoUTubeId125;
	}
	public String getVideoUTubeId15(){
		return this.videoUTubeId15;
	}
	
	public void setVideoId(Long videoId){
		 this.videoId = videoId;
	}
	public  void setLmsName(String lmsName){
		 this.lmsName = lmsName;
	}
	public  void setOrgName(String orgName){
		 this.orgName = orgName;
	}
	public  void setCourseName(String courseName){
		 this.courseName = courseName;
	}
	public void setChapterSysName(String chapterSysName){
		this.chapterSysName = chapterSysName;
	}
/*	public void setSessionSysName(String sessionSysName){
		this.sessionSysName = sessionSysName;
	}*/
	public  void setVideoSysName(String videoSysName) {
		 this.videoSysName = videoSysName;
	}
	public  void setVideoUTubeId(String videoUTubeId){
		 this.videoUTubeId = videoUTubeId;
	}
	/*public  void setVideoPath(String videoPath){
		 this.videoPath = videoPath;
	}*/
	public void setVideoDownload(Integer videoDownload){
		 this.videoDownload = videoDownload;
	}
	public void setVideoTrackDownLoad(Integer videoTrackDownLoad){
		 this.videoTrackDownLoad = videoTrackDownLoad;
	}
/*	public  void setVideoSubTitle(String videoSubTitle){
		 this.videoSubTitle = videoSubTitle;
	}*/
	public  void setVideoTitle(String videoTitle){
		 this.videoTitle = videoTitle;
	}
	public  void setVideoUTubeId075(String videoUTubeId075){
		 this.videoUTubeId075 = videoUTubeId075;
	}
	public  void setVideoUTubeId125(String videoUTubeId125){
		 this.videoUTubeId125 = videoUTubeId125;
	}
	public  void setVideoUTubeId15(String videoUTubeId15) {
		 this.videoUTubeId15 = videoUTubeId15;
	}

}
