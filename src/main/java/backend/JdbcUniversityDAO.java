package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import backend.classes.University;

public class JdbcUniversityDAO implements UniversityDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public University select(String name, String city){

		String sql = "SELECT * FROM universities WHERE uname=? AND city=?";
		Connection conn = null;

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, city);
			University university = null;
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				university = new University(rs.getString("uname"), rs.getString("city"), rs.getString("state"));
				university.setUid(rs.getInt("uid"));
			}
			rs.close();
			ps.close();
			return university;
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

	public University select(Integer uid){

		String sql = "SELECT * FROM universities WHERE uid=?";
		Connection conn = null;

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			University university = null;
			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				university = new University(rs.getString("uname"), rs.getString("city"), rs.getString("state"));
				university.setUid(rs.getInt("uid"));
			}
			rs.close();
			ps.close();
			return university;
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
