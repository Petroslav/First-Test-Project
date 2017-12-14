package model;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import model.db.CommentDAO;
import model.db.PostDAO;

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
		PostManager.getInstance().getPost(c.getParentPost().getId()).addComment(c);
		this.allComments.put(c.getId(), c);
	}
	
	public synchronized void deleteComment(Comment c){
		CommentDAO.getInstance().deleteComment(c);
		PostManager.getInstance().getPost(c.getParentPost().getId()).removeComment(c);
		this.allComments.remove(c.getId());
	}
	
	public Comment getComment(int id){
		return this.allComments.get(id);
	}
	
	public ConcurrentHashMap<Integer, Comment> getAllComments() {
		return this.allComments;
	}
	
}
