package model.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.User;
import model.UsersManager;

public class UserDAO {
	private static final String UPDATE_USER_INFO = "UPDATE users SET first_name = ?, last_name = ?, age = ? pic = ? WHERE username = ?";
	private static final String SAVE_USER_TO_DB = "INSERT INTO users (username, passwrd, first_name, last_name, age, pic) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_USERS = "SELECT username, passwrd, first_name, last_name, age, pic FROM users";
	
	private static UserDAO instance;
	
	public synchronized static UserDAO getInstance(){
		if(instance == null) instance = new UserDAO();
		return instance;
	}
	
	public synchronized Set<User> getAllUsers(){
		Set<User> users = new HashSet<>();
		try {
			Statement state = DBManager.getInstance().getConnection().createStatement();
			ResultSet userset = state.executeQuery(GET_ALL_USERS);
			while(userset.next()){
				users.add(new User(
						userset.getString("username"),
						userset.getString("passwrd"),
						userset.getString("first_name"),
						userset.getString("last_name"),
						userset.getInt("age"),
						userset.getString("pic")
						));
			}
			System.out.println("Users loaded successfully.");
		} catch (SQLException e) {
			System.out.println("Could not fetch users!");
			e.printStackTrace();
		}
		return users;
	}
	
	public synchronized void saveUser(User u){
		try {
			PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(SAVE_USER_TO_DB);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setInt(5, u.getAge());
			ps.setString(6, u.getPic());
			ps.executeUpdate();
			UsersManager.getInstance().registerNewUser(u);
			System.out.println("User saved to DB");
		} catch (SQLException e) {
			System.out.println("Could not save user to DB");
			e.printStackTrace();
		}
	}
	
	public synchronized void updateUserInfo(User u, String fn, String ln, int age, String pic){
		try{
			PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(UPDATE_USER_INFO);
			ps.setString(1, fn);
			ps.setString(2, ln);
			ps.setInt(3, age);
			ps.setString(4, pic);
			ps.setString(5, u.getUsername());
			ps.executeUpdate();
			UsersManager.getInstance().updateUser(u, fn, ln, age, pic);
			System.out.println(u.getUsername() + " has been updated.");
		}
		catch (SQLException e) {
			System.out.println("Could not update user");
			e.printStackTrace();
		}
	}
}
