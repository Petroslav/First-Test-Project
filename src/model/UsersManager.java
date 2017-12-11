package model;

import java.util.concurrent.ConcurrentHashMap;

import model.db.UserDAO;

public class UsersManager {

	private ConcurrentHashMap<String, User> allUsers = new ConcurrentHashMap<>();
	
	private static UsersManager instance;
	
	private UsersManager(){
		this.allUsers = new ConcurrentHashMap<>();
		for(User u : UserDAO.getInstance().getAllUsers()){
			this.allUsers.put(u.getUsername(), u);
		}
	}
	
	public synchronized static UsersManager getInstance() {
		if(instance == null) instance = new UsersManager();
		return instance;
	}
	
	public boolean validateReg(String u, String p1, String p2){
		if(this.allUsers.containsKey(u)){
			System.out.println("SUCCESS");
			return false;

		}
		if(!p1.equalsIgnoreCase(p2)) {
			System.out.println("FAIL");
			return false;
		}
		return true;
	}
	
	public boolean validateLogin(String u, String p){
		if(!this.allUsers.containsKey(u) || !p.equalsIgnoreCase(this.allUsers.get(u).getPassword()) || p.length() < 2) {
			System.out.println("VALIDATE FAILED");
			return false;
		}
		else {
			System.out.println("VALIDATE SUCCESSFUL");
			return true;
		}
	}
	
	public void registerNewUser(User u){
		this.allUsers.put(u.getUsername(), u);
	}

}
