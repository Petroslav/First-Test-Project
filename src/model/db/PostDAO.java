package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;


import model.Post;

public class PostDAO {

	private static final String DELETE_POST = "DELETE FROM posts WHERE id = ?";
	private static final String SAVE_TO_DB = "INSERT INTO posts(p_author, p_title, p_content, p_likes, p_dislikes, p_post_date, pic) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String LOAD_POSTS = "SELECT p_author, p_title, p_content, p_likes, p_dislikes, p_post_date, id, pic  FROM posts";
	private static PostDAO instance;
	
	public synchronized static PostDAO getInstance(){
		if(instance == null) instance = new PostDAO();
		return instance;
	}
	
	public synchronized Set<Post> loadPosts(){
		Set<Post> allPosts = new HashSet<Post>();
		Statement query;
		try {
			query = DBManager.getInstance().getConnection().createStatement();
			ResultSet posts = query.executeQuery(LOAD_POSTS);
			while(posts.next()){
				String pic = posts.getString("pic");
				if(pic == null) pic = "";
				allPosts.add(new Post(
						posts.getString("p_author"),
						posts.getString("p_title"),
						posts.getString("p_content"),
						posts.getInt("p_likes"),
						posts.getInt("p_dislikes"),
						posts.getLong("p_post_date"),
						posts.getInt("id"),
						pic
						));
			}
			System.out.println("Posts loaded successfully.");
		} catch (SQLException e) {
			System.out.println("Could not load posts from DB");
			e.printStackTrace();
		}
		return allPosts;
	}
	
	public synchronized void savePost(Post p){
		PreparedStatement saveToDB = null;
		Statement st = null;
		try {
			saveToDB = DBManager.getInstance().getConnection().prepareStatement(SAVE_TO_DB, Statement.RETURN_GENERATED_KEYS);
			saveToDB.setString(1, p.getOp().getUsername());
			saveToDB.setString(2, p.getTitle());
			saveToDB.setString(3, p.getContent());
			saveToDB.setInt(4, p.getLikes());
			saveToDB.setInt(5, p.getDislikes());
			saveToDB.setLong(6, p.getPostDate().getTime());
			saveToDB.setString(7, p.getPic());
			saveToDB.executeUpdate();
			ResultSet id = saveToDB.getGeneratedKeys();
			id.next();
			p.setId(id.getInt(1));
			System.out.println("Post saved to DB");
		} catch (SQLException e) {
			System.out.println("Could not save post");
			e.printStackTrace();
		}
		
	}
	
	public synchronized void addPicToDB(Post p){
		PreparedStatement update = null;
		
		try {
			update = DBManager.getInstance().getConnection().prepareStatement("UPDATE posts SET pic = ? WHERE id = ?");
			update.setString(1, p.getPic());
			update.setInt(2, p.getId());
			update.executeUpdate();
			System.out.println("Update successful post ID: " + p.getId());
		} catch (SQLException e) {
			System.out.println("Could not update post ID: " + p.getId());
			e.printStackTrace();
		}
		
	}
	
	public synchronized void delPost(Post p){
		PreparedStatement del = null;
		try {
			del = DBManager.getInstance().getConnection().prepareStatement(DELETE_POST);
			del.setInt(1, p.getId());
			del.executeUpdate();
			System.out.println("Post deleted from DB");
		} catch (SQLException e) {
			System.out.println("Could not delete post");
			e.printStackTrace();
		}
		
	}
}
