package model;

import java.util.concurrent.ConcurrentHashMap;

import model.db.PostDAO;

public class PostManager {

	private ConcurrentHashMap<Integer, Post> allPosts;
	
	private static PostManager instance;
	
	public PostManager() {
		this.allPosts = new ConcurrentHashMap<>();
		
		for(Post p : PostDAO.getInstance().loadPosts()){
			this.allPosts.put(p.getId(), p);
		}
	}
	
	public static PostManager getInstance() {
		if(instance == null) instance = new PostManager();
		return instance;
	}
	
	public ConcurrentHashMap<Integer, Post> getAllPosts() {
		return allPosts;
	}
	
	public synchronized Post getPost(int pID){
		return this.allPosts.get(pID);
	}
	
	public synchronized void savePost(Post p){
		PostDAO.getInstance().savePost(p);
		this.allPosts.put(p.getId(), p);
	}
	
	public synchronized void delPost(Post p){
		PostDAO.getInstance().delPost(p);
		for(Comment c : p.getComments()){
			CommentManager.getInstance().deleteComment(c);
		}
		this.allPosts.remove(p.getId());
	}
	
}
