package model;

import java.util.Date;

public class Post {

	private String content;
	private User author;
	private Date postDate;
	private int likes;
	private int postID;
	private static int ID = 0;
	
	public Post(String content, User author) {
		this.content = content;
		this.author = author;
		this.postDate = new Date();
		this.postID = ID++;
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

	public int getPostID() {
		return postID;
	}
	
		
}
