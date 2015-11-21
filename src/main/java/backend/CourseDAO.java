package backend;

import backend.classes.Course;

public interface CourseDAO {
	
	public void insert(Course course);
	
	public Course select(Integer cid);
}
