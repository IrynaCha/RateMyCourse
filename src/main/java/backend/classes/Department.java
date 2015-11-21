package backend.classes;

public class Department {

	private String name;
	
	public Department(String name){
		
		this.name = name;
	}
	
	public String toString(){
		
		return this.name;
	}

	public String getName() {
		return this.name;
	}
}
