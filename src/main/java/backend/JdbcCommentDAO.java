package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import backend.classes.Comment;

public class JdbcCommentDAO implements CommentDAO{

	private static DataSource dataSource;

	public void setDataSource(DataSource dataSource){
		JdbcCommentDAO.dataSource = dataSource;
	}

	public void insert(Comment comment) {

		String sql = "INSERT INTO comments (cid, rating, comment, prof, texts, hotness, grade, sleep) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getCid());
			ps.setDouble(2, comment.getRating());
			ps.setString(3, comment.getComment());
			ps.setString(4, comment.getProf());
			ps.setInt(5, comment.getTexts());
			ps.setInt(6, comment.getHotness());
			ps.setString(7, comment.getGrade());
			ps.setString(8, comment.getSleep());
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
	
	public List<Comment> select(Integer cid){
		
		return selectStatic(cid);
	}

	static List<Comment> selectStatic(Integer cid) {

		String sql = "SELECT * FROM comments WHERE cid=?";
		Connection conn = null;
		List<Comment> comments = new ArrayList<Comment>();

		try{

			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Comment c = new Comment(rs.getInt("cid"), rs.getDouble("rating"), rs.getString("comment"), rs.getString("prof"),
										rs.getInt("texts"), rs.getInt("hotness"), rs.getString("grade"), rs.getString("sleep"));
				comments.add(c);
			}
			rs.close();
			ps.close();
			return comments;
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
