package model;

import java.io.File;
import java.util.Iterator;
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
		for(Iterator<Comment> it = p.getComments().iterator(); it.hasNext();){
			Comment c = it.next();
			it.remove();
			CommentManager.getInstance().deleteComment(c);
		}
		PostDAO.getInstance().delPost(p);
		if(p.getPic().length() > 5){ 
			File pic = new File(p.getPic());
			pic.delete();
		}
		this.allPosts.remove(p.getId());
	}
	
}
