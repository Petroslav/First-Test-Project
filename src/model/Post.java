package model;

import java.util.ArrayList;
import java.util.Date;

public class Post {

	private ArrayList<Comment> comments;
	private int id;
	private String content;
	private String title;
	private User op;
	private Date postDate;
	private int likes;
	private int dislikes;
	
	public Post(String title, String content, User op) {
		this.title = title;
		this.content = content;
		this.op = op;
		this.likes = 0;
		this.dislikes = 0;
		this.postDate = new Date();
		this.comments = new ArrayList<Comment>();
	}
	
	public Post(String author, String title, String content, int likes, int dislikes, long date_millis, int id){
		this.op = UsersManager.getInstance().getUser(author);
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.dislikes = dislikes;
		this.postDate = new Date(date_millis);
		this.id = id;
		this.comments = new ArrayList<Comment>();
		
	}
	
	
	//Getters:
	
	public ArrayList<Comment> getComments() {
		return comments;
	}



	public String getContent() {
		return content;
	}



	public String getTitle() {
		return title;
	}



	public User getOp() {
		return op;
	}



	public Date getPostDate() {
		return postDate;
	}



	public int getLikes() {
		return likes;
	}
	
	public int getDislikes() {
		return dislikes;
	}


	//Methods:
	public void likeThread(){
		this.likes++;
	}
	
	public void unlikeThread(){
		this.likes--;
	}
	
	public void addComment(Comment c){
		this.comments.add(c);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
