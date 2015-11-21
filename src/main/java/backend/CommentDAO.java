package backend;

import java.util.List;

import backend.classes.Comment;

public interface CommentDAO {

	public void insert(Comment comment);
	
	public List<Comment> select(Integer cid);
}
