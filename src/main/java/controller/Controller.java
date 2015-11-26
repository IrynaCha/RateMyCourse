package controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.Backend;
import backend.classes.Comment;
import backend.classes.Course;
import backend.classes.Department;
import backend.classes.Professor;
import backend.classes.University;

@RestController
public class Controller {
	
	@RequestMapping("/getUniversities")
	public List<University> getUniversities(){
		
		return Application.universityDAO.select();
	}

    @RequestMapping("/getUniversitiesByState")
    public List<University> getUniversitiesByState(@RequestParam(value="state", required=true) String state){
    	
        return Application.universityDAO.select(state);
    }
    
    @RequestMapping("/getDepartments")
    public List<Department> getDepartments(){
    	
    	return Application.departmentDAO.select();
    }
    
    @RequestMapping("/getCoursesByUniv")
    public List<Course> getCoursesByUniv(@RequestParam(value="uid", required=true) Integer uid){
    	
    	return Application.courseDAO.select(uid);
    }
    
    @RequestMapping("/getCoursesByUnivAndDept")
    public List<Course> getCoursesByUnivAndDept(@RequestParam(value="uid", required=true) Integer uid, @RequestParam(value="dname", required=true) String dname){
    	
    	return Application.courseDAO.select(uid, dname);
    }
    
    @RequestMapping("/getComments")
    public List<Comment> getComments(@RequestParam(value="cid", required=true) Integer cid){
    	
    	return Application.commentDAO.select(cid);
    }
    
    @RequestMapping("/getProfComments")
    public List<Comment> getProfComments(@RequestParam(value="cid", required=true) Integer cid, @RequestParam(value="prof", required=true) String prof){
    	
    	List<Comment> comments = Application.commentDAO.select(cid);
    	return Backend.filterComments(comments, prof);
    }
    
    @RequestMapping("/getTopProfs")
    public List<Professor> getTopProfs(@RequestParam(value="cid", required=true) Integer cid){
    	
    	List<Comment> comments = Application.commentDAO.select(cid);
    	return Backend.topProfs(comments, true);
    }
    
    @RequestMapping("/getHotProfs")
    public List<Professor> getHotProfs(@RequestParam(value="cid", required=true) Integer cid){
    	
    	List<Comment> comments = Application.commentDAO.select(cid);
    	return Backend.topProfs(comments, false);
    }
    
    @RequestMapping("/getTopCoursesInDept")
    public Map<String, Double> getTopCoursesInDept(@RequestParam(value="uid", required=true) Integer uid, @RequestParam(value="dname", required=true) String dname){
    	
    	List<Course> courses = Application.courseDAO.select(uid, dname);
    	return Backend.topCourses(courses);
    }
    
    @RequestMapping("/getTopCoursesInUniv")
    public Map<String, Double> getTopCoursesInDept(@RequestParam(value="uid", required=true) Integer uid){
    	
    	List<Course> courses = Application.courseDAO.select(uid);
    	return Backend.topCourses(courses);
    }
    
    @RequestMapping(value="/addCourse", method=RequestMethod.POST)
    public ResponseEntity<Course> addCourse(@RequestBody Map<String, Object> json){
    	
    	if(json != null){
    		
    		try{
    			
	    		String name = (String) json.get("name");
	    		String num = (String) json.get("num");
	    		Integer uid = (Integer) json.get("uid");
	    		String dname = (String) json.get("dname");
	    		Course course = new Course(name, num, uid, dname);
	    		Application.courseDAO.insert(course);
	    		return new ResponseEntity<Course>(course, HttpStatus.OK);
	    		
    		} catch (Exception e){
    			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    		}
    	}
    	return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value="/addComment", method=RequestMethod.POST)
    public ResponseEntity<Comment> addComment(@RequestBody Map<String, Object> json){
    	
    	if(json != null){
    		
    		try{
    			
	    		Integer cid = (Integer) json.get("cid");
	    		Double rating = (Double) json.get("rating");
	    		String commentString = (String) json.get("comment");
	    		String prof = (String) json.get("prof");
	    		Integer texts = (Integer) json.get("texts");
	    		Integer hotness = (Integer) json.get("hotness");
	    		String grade = (String) json.get("grade");
	    		String sleep = (String) json.get("sleep");
	    		Comment comment = new Comment(cid, rating, commentString, prof, texts, hotness, grade, sleep);
	    		Application.commentDAO.insert(comment);
	    		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	    		
    		} catch (Exception e){
    			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    		}
    	}
    	return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
