package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import backend.CommentDAO;
import backend.CourseDAO;
import backend.DepartmentDAO;
import backend.UniversityDAO;

@SpringBootApplication
public class Application {
	
	public static UniversityDAO universityDAO;
	public static DepartmentDAO departmentDAO;
	public static CourseDAO courseDAO;
	public static CommentDAO commentDAO;

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);

		try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml")){

			universityDAO = (UniversityDAO) context.getBean("universityDAO");
			departmentDAO = (DepartmentDAO) context.getBean("departmentDAO");
			courseDAO = (CourseDAO) context.getBean("courseDAO");
			commentDAO = (CommentDAO) context.getBean("commentDAO");
		}
	}
}
