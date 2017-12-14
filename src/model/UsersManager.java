package model;

import java.util.concurrent.ConcurrentHashMap;

import model.db.UserDAO;

public class UsersManager {

	private ConcurrentHashMap<String, User> allUsers = new ConcurrentHashMap<>();
	
	private static UsersManager instance;
	
	private UsersManager(){
		this.allUsers = new ConcurrentHashMap<>();
		for(User u : UserDAO.getInstance().getAllUsers()){
			this.allUsers.put(u.getUsername().toLowerCase(), u);
		}
	}
	
	public synchronized static UsersManager getInstance() {
		if(instance == null) instance = new UsersManager();
		return instance;
	}
	
	public boolean validateReg(String u, String p1, String p2){		
		if(this.allUsers.containsKey(u.toLowerCase())){
			System.out.println("User exsists");
			return false;

		}
		if(!p1.equalsIgnoreCase(p2)) {
			System.out.println("Passwords don't match");
			return false;
		}
		if(p1.length() < 2) {
			System.out.println("Password too short");
			return false;
		}
		return true;
	}
	
	public boolean validateLogin(String u, String p){
		u = u.toLowerCase();
		if(!this.allUsers.containsKey(u) || !p.equalsIgnoreCase(this.allUsers.get(u).getPassword())) {
			System.out.println("VALIDATE FAILED");
			return false;
		}
		return true;
	}
	
	public User getUser(String username){
		return this.allUsers.get(username.toLowerCase());
	}
	
	public synchronized void registerNewUser(User u){
		this.allUsers.put(u.getUsername().toLowerCase(), u);
	}

	public synchronized void updateUser(User u, String fn, String ln, int age) {
		u.setFirstName(fn);
		u.setLastName(ln);
		u.setAge(age);
	}

}
