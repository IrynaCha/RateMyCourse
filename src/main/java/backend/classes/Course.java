package backend.classes;

public class Course {

	private String name;
	private String num;
	private Integer cid;
	private Integer uid;
	private String dname;
	
	public Course(String name, String num, Integer uid, String dname){
		
		this.name = name;
		this.num = num;
		this.uid = uid;
		this.dname = dname;
	}
	
	public String toString(){
		return this.cid + ": " + this.name + " (" + this.num + " - " + this.uid + ", " + this.dname + ")";
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public String getNum() {
		return num;
	}

	public Integer getUid() {
		return uid;
	}

	public String getDname() {
		return dname;
	}
}
