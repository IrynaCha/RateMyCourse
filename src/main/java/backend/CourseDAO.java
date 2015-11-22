package backend;

import java.util.List;

import backend.classes.Course;

public interface CourseDAO {
	
	public void insert(Course course);
	
	public List<Course> select(Integer uid, String dname);
	
	public List<Course> select(Integer uid);
}
