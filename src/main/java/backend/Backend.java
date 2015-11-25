package backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import backend.classes.Comment;
import backend.classes.Professor;

public class Backend {
	
	public static List<Comment> filterComments(List<Comment> comments, String name){
		
		List<Comment> filteredComments = new ArrayList<Comment>();
		for(Comment c : comments){
			
			if(c.getProf().equals(name)){
				filteredComments.add(c);
			}
		}
		
		return filteredComments;
	}
	
	public static List<Professor> topProfs(List<Comment> comments, boolean byRating){
		
		HashMap<String, Professor> profs = new HashMap<String, Professor>();
		for(Comment c : comments){
			
			if(profs.containsKey(c.getProf())){
				profs.get(c.getProf()).incrementComments();
				profs.get(c.getProf()).addRatings(c.getRating());
				profs.get(c.getProf()).addHotness(c.getHotness());
			}
			else{
				Professor p = new Professor(c.getProf());
				p.incrementComments();
				p.addRatings(c.getRating());
				p.addHotness(c.getHotness());
				profs.put(p.getName(), p);
			}
		}
		List<Professor> topProfs = new ArrayList<Professor>();
		for(Professor p : profs.values()){
			topProfs.add(p);
		}
		if(byRating){
			Collections.sort(topProfs, Professor.compareByRatings);
		}
		else{
			Collections.sort(topProfs, Professor.compareByHoteness);
		}
		
		return topProfs;
	}
}
