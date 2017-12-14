package model.db;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import model.Comment;

public class CommentDAO {

	private static final String DELETE_COMMENT = "DELETE FROM comments WHERE id = ?";
	private static final String SAVE_COMMENT = "INSERT INTO comments(id, c_author, c_parent, c_content, c_post_date, c_likes, c_dislikes) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String PULL_COMMENTS = "SELECT id, c_author, c_content, c_post_date, c_likes, c_dislikes FROM comments ORDER BY id";
	
	private static CommentDAO instance;
	
	public synchronized static CommentDAO getInstance(){
		if(instance == null) instance = new CommentDAO();
		return instance;
	}
	
	public synchronized Set<Comment> loadComments(){
		Set<Comment> comments = new HashSet<>();
		try {
			Statement query = DBManager.getInstance().getConnection().createStatement();
			ResultSet allComments = query.executeQuery(PULL_COMMENTS);
			SimpleDateFormat datef = new SimpleDateFormat();
			Date postDate = null;
			while(allComments.next()){
				try {
					postDate = datef.parse(allComments.getString("c_post_date"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				comments.add(new Comment(
						allComments.getInt("id"),
						allComments.getString("c_author"),
						allComments.getInt("c_parent"),
						allComments.getString("c_content"),
						allComments.getLong("c_post_date"),
						allComments.getInt("c_likes"),
						allComments.getInt("c_dislikes")
					));
	
			}
			System.out.println("Comments loaded successfully.");
		} catch (SQLException e) {
			System.out.println("Could not load comments");
			e.printStackTrace();
		}
		return comments;
	}
	
	public synchronized void saveComment(Comment c){
		PreparedStatement ps = null;
		try { 
			ps = DBManager.getInstance().getConnection().prepareStatement(SAVE_COMMENT, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getAuthor().getUsername());
			ps.setString(3, c.getContent());
			ps.setInt(4, c.getParentPost().getId());
			ps.setLong(5, c.getPostDate().getTime());
			ps.setInt(6, c.getLikes());
			ps.setInt(7, c.getDislikes());
			ps.executeUpdate();
			ResultSet id = ps.getGeneratedKeys();
			id.next();
			c.setId(id.getInt(1));
			System.out.println("Comment saved to DB");
		} catch (SQLException e) {
			System.out.println("Could not save comment");
			e.printStackTrace();
		} finally {
			if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Something went wrong while trying to close the statement after saving the commen to the DB");
					e.printStackTrace();
				}
		}
	}
	
	public synchronized void deleteComment(Comment c){
		PreparedStatement ps = null;
		try{
			ps = DBManager.getInstance().getConnection().prepareStatement(DELETE_COMMENT);
			ps.setInt(1, c.getId());
			ps.executeUpdate();
			System.out.println("Comment deleted");
		} catch(SQLException e) {
			System.out.println("Could not delete comment");
			e.printStackTrace();
		} finally {
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Something went wrong while trying to close the statement after deleting the comment from the DB");
					e.printStackTrace();
				}
			}
		}
	}
}
