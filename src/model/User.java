package model;

import java.util.ArrayList;
import java.util.Collections;

public class User {

	private String username;
	private String firstName;
	private String lastName;
	private int age;
	private ArrayList<Post> postHistory;
	public User(String username, String firstName, String lastName, int age) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.postHistory = new ArrayList<>();
	}
	
	public String getUsername() {
		return username;
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
	public ArrayList<Post> getPostHistory() {
		return (ArrayList<Post>) Collections.unmodifiableList(postHistory);
	}
	
	
	
}
