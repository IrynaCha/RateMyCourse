package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import backend.classes.Course;

public class JdbcCourseDAO implements CourseDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public void insert(Course course) {

		String sql = "INSERT INTO courses (dname, uid, name, num) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, course.getDname());
			ps.setInt(2, course.getUid());
			ps.setString(3, course.getName());
			ps.setString(4, course.getNum());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e){

			throw new RuntimeException(e);
		} finally{

			if(conn != null){
				try{
					conn.close();
				} catch (SQLException e){
					return;
				}
			}
		}
	}

	public List<Course> select(Integer uid, String dname) {

		String sql = "SELECT * FROM courses WHERE uid=? AND dname=?";
		Connection conn = null;
		List<Course> courses = new ArrayList<Course>();

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.setString(2, dname);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Course course = new Course(rs.getString("name"), rs.getString("num"), rs.getInt("uid"), rs.getString("dname"));
				course.setCid(rs.getInt("cid"));
				courses.add(course);
			}
			rs.close();
			ps.close();
			return courses;
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

	public List<Course> select(Integer uid) {

		String sql = "SELECT * FROM courses WHERE uid=?";
		Connection conn = null;
		List<Course> courses = new ArrayList<Course>();

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Course course = new Course(rs.getString("name"), rs.getString("num"), rs.getInt("uid"), rs.getString("dname"));
				course.setCid(rs.getInt("cid"));
				courses.add(course);
			}
			rs.close();
			ps.close();
			return courses;
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
