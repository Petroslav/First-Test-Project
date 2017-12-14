package model;

import java.util.concurrent.ConcurrentHashMap;

import model.db.PostDAO;

public class PostManager {

	private ConcurrentHashMap<Integer, Post> allPosts;
	
	private static PostManager instance;
	
	public PostManager() {
		this.allPosts = new ConcurrentHashMap<>();
		
		for(Post p : PostDAO.getInstance().loadPosts()){
			for(Comment c : CommentManager.getInstance().getAllComments().values()){
				if(c.getAuthor().getUsername().equalsIgnoreCase(p.getOp().getUsername())){
					p.addComment(c);
				}
			}
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
	
	public synchronized Post getPost(int p){
		return this.allPosts.get(p);
	}
	
	public synchronized void savePost(Post p){
		PostDAO.getInstance().savePost(p);
		this.allPosts.put(p.getId(), p);
	}
	
	public synchronized void delPost(Post p){
		PostDAO.getInstance().delPost(p);
		this.allPosts.remove(p.getId());
	}
	
}
