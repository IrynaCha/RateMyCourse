package backend.classes;

public class University {

	private String name;
	private String city;
	private String state;
	private Integer uid;
	
	public University(String name, String city, String state){
		
		this.name = name;
		this.city = city;
		this.state = state;
	}
	
	public String toString(){
		return this.uid + ": " + this.name + " (" + this.city + ", " + this.state + ")";
	}

	public String getName() {
		return this.name;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}
	
	public Integer getUid(){
		return this.uid;
	}
	
	public void setUid(Integer uid){
		this.uid = uid;
	}
}
