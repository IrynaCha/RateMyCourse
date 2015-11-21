package backend;

import backend.classes.Department;

public interface DepartmentDAO {

	public Department select(String name);
}
