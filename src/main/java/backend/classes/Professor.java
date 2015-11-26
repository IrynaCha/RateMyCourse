package backend.classes;

public class Professor {
	
	private String name;
	private int comments;
	private double hotnessSum;
	private double ratingsSum;
	private double textsSum;
	private double gradesSum;
	private double sleepSum;
	
	public Professor(String name){
		
		this.name = name;
	}
	
	public double getAvgHotness(){
		return this.hotnessSum/this.comments;
	}
	
	public double getAvgRating(){
		return this.ratingsSum/this.comments;
	}
	
	public double getAvgTexts(){
		return this.textsSum/this.comments;
	}
	
	public String getAvgGrade(){
		
		double gradesAvg = this.gradesSum/this.comments;
		if(gradesAvg >= 90){
			return "A";
		}
		else if(gradesAvg >= 80){
			return "B";
		}
		else if(gradesAvg >= 70){
			return "C";
		}
		else if(gradesAvg >= 60){
			return "D";
		}
		else{
			return "F";
		}
	}
	
	public double getSleepPercent(){
		return (this.sleepSum/this.comments)*100;
	}
	
	public void incrementComments(){
		this.comments++;
	}
	
	public void addHotness(double hotness){
		this.hotnessSum += hotness;
	}
	
	public void addRatings(double rating){
		this.ratingsSum += rating;
	}
	
	public void addTexts(int text){
		this.textsSum += text;
	}
	
	public void addGrade(String grade){
		
		switch (grade){
			case "A": this.gradesSum += 95; break;
			case "B": this.gradesSum += 85; break;
			case "C": this.gradesSum += 75; break;
			case "D": this.gradesSum += 65; break;
			case "F": this.gradesSum += 55; break;
			default: break;
		}
	}
	
	public void addSleep(String sleep){
		
		if(sleep.equals("y")){
			this.sleepSum++;
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return this.name + " (Average Rating: " + this.getAvgRating() + "; Average Hotness: " + this.getAvgHotness() + 
				"; Average Texts: " + this.getAvgTexts() + "; Average Grade: " + this.getAvgGrade() + "; Sleep Percentage: " + this.getSleepPercent() + ")";
	}
}
