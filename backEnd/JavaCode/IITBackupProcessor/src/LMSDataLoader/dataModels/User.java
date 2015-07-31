package LMSDataLoader.dataModels;

public class User {
	
	
	Long   userId ;
	Long   lmsUserId ;
	String   lmsName ;
	String   orgName ;
	String   name ;
	String   gender ;
	java.util.Date registrationDate ;
	String   emailId ;
	String   mothertounge ;
	String   highestEduDegree ;
	String   goals ;
	//String location;
	String   city ;
	//String   majorCity ;
	String   state ;
	//String   country ;
	//String   timezone ;
	Short   active ;
//	java.util.Date firstAccesDate  ;
	java.util.Date  lastAccessDate ;
	//String userFlag;
	//String address;
	Short allowCert;
//	Integer consecutiveVisits;
	Integer yearOfBirth;
	Integer pincode;
	Integer aadharId;

	public Long   getUserId(){
		return this.userId;
	}
	public Long   getLmsUserId(){
		return this.userId;
	}
	public String getLmsName(){
		return this.lmsName ;
	}
	public String getOrgName(){
		return this.orgName ;
	}
	public  String getName(){
		return this.name ;
	}
	public  String getGender(){
		return this.gender ;
	}
	//public  java.util.Date getBirthDate(){
		//return this.birthDate ;
	//}
	public  java.util.Date getRegistrationDate(){
		return this.registrationDate ;
	}
	public  String getEmailId(){
		return this.emailId ;
	}
	public  String getMothertounge(){
		return this.mothertounge ;
	}
	public  String getHighestEduDegree(){
		return this.highestEduDegree ;
	}
	public  String getGoals(){
		return this.goals ;
	}
	/*public  String getLocation(){
		return this.location ;
	}*/
	public  String getCity(){
		return this.city ;
	}
	/*public  String getMajorCity(){
		return this.majorCity ;
	}*/
	public  String getState(){
		return this.state ;
	}
	/*public  String getCountry(){
		return this.country ;
	}
	/*public  String getTimezone(){
		return this.timezone ;
	}*/
	public  Short getActive(){
		return this.active ;
	}
	/*public  String getUserFlag(){
		return this.userFlag ;
	}
	/*public  String getIsSuperUser(){
		return this.isSuperUser ;
	}
	public  java.util.Date getFirstAccesDate (){
		return this.firstAccesDate ;
	}*/
	public  java.util.Date  getLastAccessDate(){
		return this.lastAccessDate ;
	}
/*	public  String getAddress(){
		return this.address ;
	}*/
	public  Short getAllowCert(){
		return this.allowCert ;
	}
/*	public  Integer  getConsecutiveVisits(){
		return this.consecutiveVisits ;
	}*/
	public  Integer  getYearOfBirth(){
		return this.yearOfBirth ;
	}
	public  Integer  getPincode(){
		return this.pincode ;
	}
	public  Integer  getAadharId(){
		return this.aadharId ;
	}

	public void setUserId(Long  userId ){
		this.userId = userId;
	}
	public void setLmsUserId(Long  userId ){
		this.userId = userId;
	}
	public void setLmsName(String currVar ){
		this.lmsName  = currVar;
	}
	public void setOrgName(String currVar){
		this.orgName  = currVar;
	}
	public  void setName(String currVar){
		this.name  = currVar;
	}
	public  void setGender(String currVar){
		this.gender  = currVar;
	}
//	public void setBirthDate( java.util.Date  currVar){
	//	this.birthDate  = currVar;
	//}
	public  void setRegistrationDate(java.util.Date  currVar){
		this.registrationDate  = currVar;
	}
	public  void setEmailId(String currVar){
		this.emailId  = currVar;
	}
	public  void setMothertounge(String currVar){
		this.mothertounge  = currVar;
	}
	public  void setHighestEduDegree(String currVar){
		this.highestEduDegree  = currVar;
	}
	public  void setGoals(String currVar){
		this.goals  = currVar;
	}
	/*public  void setLocation(String currVar){
		this.location  = currVar;
	}*/
	public  void setCity(String currVar){
		this.city  = currVar;
	}
/*	public  void setMajorCity(String currVar){
		this.majorCity  = currVar;
	}*/
	public  void setState(String currVar){
		this.state  = currVar;
	}
	/*public  void setCountry(String currVar){
		this.country  = currVar;
	}
	/*public  void setTimezone(String currVar){
		this.timezone  = currVar;
	}*/
	public  void setActive(Short currVar){
		this.active  = currVar;
	}
	/*public  void setUserFlag(String currVar){
		this.userFlag  = currVar;
	}
	/*public  void setIsSuperUser(String currVar){
		this.isSuperUser  = currVar;
	}
	public  void setAddress(String currVar){
		this.address  = currVar;
	}*/
	public  void setAllowCert(Short currVar){
		this.allowCert  = currVar;
	}
	/*public  void setFirstAccesDate (java.util.Date  currVar){
		this.firstAccesDate  = currVar;
	}*/
	public  void setLastAccessDate(java.util.Date   currVar){
		this.lastAccessDate  = currVar;
	}
/*	public  void setConsecutiveVisits(Integer   currVar){
		this.consecutiveVisits  = currVar;
	}*/
	public  void setYearOfBirth(Integer   currVar){
		this.yearOfBirth  = currVar;
	}
	public  void setPincode(Integer   currVar){
		this.pincode  = currVar;
	}
	public  void setAadharId(Integer   currVar){
		this.aadharId  = currVar;
	}
}
