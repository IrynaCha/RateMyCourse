package backend;

import backend.classes.University;

public interface UniversityDAO {
	
	public University select(String name, String city);
	
	public University select(Integer uid);
}
