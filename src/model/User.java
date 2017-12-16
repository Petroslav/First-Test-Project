package model;

import java.util.ArrayList;
import java.util.Collections;

public class User {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private int user_id;
	private ArrayList<Comment> postHistory;
	
	public User(String username, String password, String firstName, String lastName, int age) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.postHistory = new ArrayList<>();
	}

	//GETTERS:
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getAge() {
		return age;
	}
	public ArrayList<Comment> getPostHistory() {
		return (ArrayList<Comment>) Collections.unmodifiableList(postHistory);
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	//SETTERS:
	public void changePassword(String oldPassword, String newPassword) {
		if(this.password.equalsIgnoreCase(oldPassword) && !this.password.equalsIgnoreCase(newPassword)){
			this.password = newPassword;	
		}
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}