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
    public List<Comment> getComments(@RequestParam(value="cid") Integer cid){
    	
    	return Application.commentDAO.select(cid);
    }
}
