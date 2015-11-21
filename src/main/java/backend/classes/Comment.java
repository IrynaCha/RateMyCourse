package backend.classes;

public class Comment {

	private Integer cid;
	private Double rating;
	private String comment;
	private String prof;
	private Integer texts;
	private Integer hotness;
	private String grade;
	private String sleep;
	
	public Comment(Integer cid, Double rating, String comment, String prof,
					Integer texts, Integer hotness, String grade, String sleep){
		
		this.cid = cid;
		this.rating = rating;
		this.comment = comment;
		this.prof = prof;
		this.texts = texts;
		this.hotness = hotness;
		this.grade = grade;
		this.sleep = sleep;
	}
	
	public String toString(){
		return this.cid + ": " + this.rating + "/5 (" + this.comment + ")";
	}

	public Integer getCid() {
		return cid;
	}

	public Double getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}

	public String getProf() {
		return prof;
	}

	public Integer getTexts() {
		return texts;
	}

	public Integer getHotness() {
		return hotness;
	}

	public String getGrade() {
		return grade;
	}

	public String getSleep() {
		return sleep;
	}
}
