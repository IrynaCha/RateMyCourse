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

    @RequestMapping("/getUniversity")
    public University getUniversity(@RequestParam(value="name") String name, @RequestParam(value="city") String city){
    	
        return Application.universityDAO.select(name, city);
    }
    
    @RequestMapping("/getDepartment")
    public Department getDepartment(@RequestParam(value="name") String name){
    	
    	return Application.departmentDAO.select(name);
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
