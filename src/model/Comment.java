package model;

import java.util.Date;

public class Comment {

	private String content;
	private User author;
	private Post parentPost;
	private Date postDate;
	private int likes;
	private int dislikes;
	private int id;

//	public Comment(String username, int parent, String content, Date date, int likes, int dislikes){
//		this.author = UsersManager.getInstance().getUser(username);
//		this.parentPost = PostManager.getInstance().getPost(parent);
//		this.content = content;
//		this.postDate = date;
//		this.likes = likes;
//		this.dislikes = dislikes;
//	}
	
	public Comment(User author, String content, int pID){
		this.author = author;
		this.content = content;
		this.parentPost = PostManager.getInstance().getPost(pID);
		this.likes = 0;
		this.dislikes = 0;
		this.postDate = new Date();
	}
	
	public Comment(int id, String username, int parent, String content, long date_millis, int likes, int dislikes){
		this.author = UsersManager.getInstance().getUser(username);
		this.parentPost = PostManager.getInstance().getPost(parent);
		this.content = content;
		this.postDate = new Date(date_millis);
		this.likes = likes;
		this.dislikes = dislikes;
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public User getAuthor() {
		return author;
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
	
	public Post getParentPost() {
		return parentPost;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
		
}
