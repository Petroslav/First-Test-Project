package model;

import java.util.ArrayList;
import java.util.Date;

public class Comment {

	private String content;
	private User author;
	private Date postDate;
	private int likes;
	private int postID;
	private ArrayList<Comment> replies;
	private static int ID = 1;
	
	public Comment(String content, User author) {
		this.content = content;
		this.author = author;
		this.postDate = new Date();
		this.replies = new ArrayList<>();
		this.postID = ID++;
	}

	public String getContent() {
		return content;
	}
	
	public ArrayList<Comment> getReplies() {
		return replies;
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
	
	public void newReply(Comment reply){
		this.replies.add(reply);
	}
	
		
}
