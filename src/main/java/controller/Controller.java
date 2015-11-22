package controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.classes.Comment;
import backend.classes.Course;
import backend.classes.Department;
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
    
    @RequestMapping("/getUniversity")
    public University getUniversity(@RequestParam(value="uid", required=true) Integer uid){
    	
    	return Application.universityDAO.select(uid);
    }
    
    @RequestMapping("/getDepartments")
    public List<Department> getDepartments(){
    	
    	return Application.departmentDAO.select();
    }
    
    @RequestMapping("/getCourse")
    public Course getCourse(@RequestParam(value="cid") Integer cid){
    	
    	return Application.courseDAO.select(cid);
    }
    
    @RequestMapping("/getComments")
    public List<Comment> getComments(@RequestParam(value="cid") Integer cid){
    	
    	return Application.commentDAO.select(cid);
    }
}
