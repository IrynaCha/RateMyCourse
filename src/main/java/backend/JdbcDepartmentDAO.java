package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import backend.classes.Department;

public class JdbcDepartmentDAO implements DepartmentDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public List<Department> select(){

		String sql = "SELECT * FROM departments";
		Connection conn = null;
		List<Department> departments = new ArrayList<Department>();

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Department department = new Department(rs.getString("dname"));
				departments.add(department);
			}
			rs.close();
			ps.close();
			return departments;
		} catch(SQLException e){

			throw new RuntimeException(e);
		} finally{

			if(conn != null){
				try{
					conn.close();
				} catch (SQLException e){
					return null;
				}
			}
		}
	}
}
