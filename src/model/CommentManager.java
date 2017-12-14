package model;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import model.db.CommentDAO;

public class CommentManager {

	private static CommentManager instance;
	
	private ConcurrentHashMap<Integer, Comment> allComments;
	
	private CommentManager(){
		this.allComments = new ConcurrentHashMap<>();
		for(Comment c : CommentDAO.getInstance().loadComments()){
			allComments.put(c.getId(), c);
		}
	}
	
	public static synchronized CommentManager getInstance(){
		if(instance == null) instance = new CommentManager();
		return instance;
	}
	
	public synchronized void saveComment(Comment c){
		CommentDAO.getInstance().saveComment(c);
		this.allComments.put(c.getId(), c);
	}
	
	public synchronized void deleteComment(Comment c){
		CommentDAO.getInstance().deleteComment(c);
		this.allComments.remove(c.getId());
	}
	
	public Comment getComment(Comment c){ 
		return this.allComments.get(c.getId());
	}
	
	public ConcurrentHashMap<Integer, Comment> getAllComments() {
		return this.allComments;
	}
	
}
