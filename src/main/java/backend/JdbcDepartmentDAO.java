package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import backend.classes.Department;

public class JdbcDepartmentDAO implements DepartmentDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public Department select(String name){

		String sql = "SELECT * FROM departments WHERE dname=?";
		Connection conn = null;

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			Department department = null;
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				department = new Department(rs.getString("dname"));
			}
			rs.close();
			ps.close();
			return department;
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
