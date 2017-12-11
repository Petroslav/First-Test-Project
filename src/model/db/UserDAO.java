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
	private static final String SAVE_USER_TO_DB = "INSERT INTO users (username, passwrd, first_name, last_name, age) VALUES(?, ?, ?, ?, ?)";
	private static final String GET_ALL_USERS = "SELECT username, passwrd, first_name, last_name, age FROM users";
	
	private static UserDAO instance;
	
	public synchronized static UserDAO getInstance(){
		if(instance == null) instance = new UserDAO();
		return instance;
	}
	
	public Set<User> getAllUsers(){
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
						userset.getInt("age")
						));
			}
		} catch (SQLException e) {
			System.out.println("Could not fetch users!");
			e.printStackTrace();
		}
		System.out.println("Users loaded successfully.");
		return users;
	}
	
	public void saveUser(User u){
		try {
			PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(SAVE_USER_TO_DB);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setInt(5, u.getAge());
			ps.executeUpdate();
			UsersManager.getInstance().registerNewUser(u);
			System.out.println("User saved to DB");
		} catch (SQLException e) {
			System.out.println("Could not save user to DB");
		}
	}
}
