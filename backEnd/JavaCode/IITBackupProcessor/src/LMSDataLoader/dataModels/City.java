package LMSDataLoader.dataModels;

public class City {
	Integer id;
	String name;
	Integer stateId;
	
	public 	 Integer getId(){
		return id;
	}
	public 	 String getName(){
		return name;
	}
	public 	 Integer getStateId(){
		return this.stateId;
	}
	public void  setId(Integer id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setStateId(Integer stateId){
		this.stateId = stateId;
	}	
}
