package backend;

import java.util.List;

import backend.classes.University;

public interface UniversityDAO {
	
	public List<University> select();
	
	public List<University> select(String state);
}
