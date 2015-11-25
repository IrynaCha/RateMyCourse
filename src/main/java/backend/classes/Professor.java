package backend.classes;

import java.util.Comparator;

public class Professor {
	
	private String name;
	private int comments;
	private double hotnessSum;
	private double ratingsSum;
	
	public Professor(String name){
		
		this.name = name;
	}
	
	public double getAvgHotness(){
		return this.hotnessSum/this.comments;
	}
	
	public double getAvgRating(){
		return this.ratingsSum/this.comments;
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
	
	public static Comparator<Professor> compareByRatings = new Comparator<Professor>(){
		public int compare(Professor p1, Professor p2){
			return Double.compare(p2.getAvgRating(), p1.getAvgRating());
		}
	};
	
	public static Comparator<Professor> compareByHoteness = new Comparator<Professor>(){
		public int compare(Professor p1, Professor p2){
			return Double.compare(p2.getAvgHotness(), p1.getAvgHotness());
		}
	};
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return this.name + " (Average Rating: " + this.getAvgRating() + "; Average Hotness: " + this.getAvgHotness() + ")";
	}
}
