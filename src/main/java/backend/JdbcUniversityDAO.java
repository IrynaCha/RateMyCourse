package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import backend.classes.University;

public class JdbcUniversityDAO implements UniversityDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	
	public List<University> select(){
		
		String sql = "SELECT * FROM universities";
		Connection conn = null;
		List<University> universities = new ArrayList<University>();

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				University university = null;
				university = new University(rs.getString("uname"), rs.getString("city"), rs.getString("state"));
				university.setUid(rs.getInt("uid"));
				universities.add(university);
			}
			rs.close();
			ps.close();
			return universities;
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
	
	public List<University> select(String state){
		
		String sql = "SELECT * FROM universities WHERE state=?";
		Connection conn = null;
		List<University> universities = new ArrayList<University>();

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, state);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				University university = null;
				university = new University(rs.getString("uname"), rs.getString("city"), rs.getString("state"));
				university.setUid(rs.getInt("uid"));
				universities.add(university);
			}
			rs.close();
			ps.close();
			return universities;
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
