package model;

import java.util.ArrayList;
import java.util.Date;

public class Thread {

	private ArrayList<Post> comments;
	private String content;
	private String title;
	private User op;
	private Date postDate;
	private int likes;
	
	public Thread(String title, String content, User op) {
		this.title = title;
		this.content = content;
		this.op = op;
		this.likes = 0;
		this.postDate = new Date();
		this.comments = new ArrayList<Post>();
	}
	
	
	//Getters:
	
	public ArrayList<Post> getComments() {
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


	//Methods:
	public void likeThread(){
		this.likes++;
	}
	
	public void unlikeThread(){
		this.likes--;
	}
	
	public void addComment(String text, User author){
		this.comments.add(new Post(text, author));
	}
	
	
}
