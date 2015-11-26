package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import backend.classes.Comment;
import backend.classes.Course;
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
	
	public static List<Professor> profStats(List<Comment> comments){
		
		HashMap<String, Professor> profsMap = new HashMap<String, Professor>();
		for(Comment c : comments){
			
			if(profsMap.containsKey(c.getProf())){
				profsMap.get(c.getProf()).incrementComments();
				profsMap.get(c.getProf()).addRatings(c.getRating());
				profsMap.get(c.getProf()).addHotness(c.getHotness());
				profsMap.get(c.getProf()).addTexts(c.getTexts());
				profsMap.get(c.getProf()).addGrade(c.getGrade());
				profsMap.get(c.getProf()).addSleep(c.getSleep());
			}
			else{
				Professor p = new Professor(c.getProf());
				p.incrementComments();
				p.addRatings(c.getRating());
				p.addHotness(c.getHotness());
				p.addTexts(c.getTexts());
				p.addGrade(c.getGrade());
				p.addSleep(c.getSleep());
				profsMap.put(p.getName(), p);
			}
		}
		
		List<Professor> profs = new ArrayList<Professor>();
		profs.addAll(profsMap.values());
		return profs;
	}
	
	public static Map<String, Double> topProfs(List<Professor> profs, boolean byRating){
		
		Map<String, Double> profStats = new HashMap<String, Double>();
		if(byRating){
			for(Professor p : profs){
				profStats.put(p.getName(), p.getAvgRating());
			}
		}
		else{
			for(Professor p : profs){
				profStats.put(p.getName(), p.getAvgHotness());
			}
		}
		
		profStats = sortByValue(profStats);
		return profStats;
	}
	
	public static Map<String, Double> topCourses(List<Course> courses){
		
		Map<String, Double> courseRatings = new HashMap<String, Double>();
		for(Course c : courses){
			
			List<Comment> comments = JdbcCommentDAO.selectStatic(c.getCid());
			double avgRating = 0;
			for(Comment cc : comments){
				avgRating += cc.getRating();
			}
			avgRating /= comments.size();
			if(comments.size() == 0){
				avgRating = 0;
			}
			
			courseRatings.put(c.getName(), avgRating);
		}
		
		courseRatings = sortByValue(courseRatings);        
		return courseRatings;
	}
	
	private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map){
		
		Map<K,V> result = new LinkedHashMap<>();
		Stream<Entry<K,V>> st = map.entrySet().stream();

		st.sorted(Map.Entry.<K, V>comparingByValue().reversed())
			.forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
		
		return result;
	}
}
