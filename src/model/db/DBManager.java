package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.Comment;
import model.CommentManager;
import model.PostManager;
import model.UsersManager;

public class DBManager {
	private static final String DB_IP = "localhost";
	private static final String DB_PORT = "3306";
	private static final String DB_NAME = "test";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "1234";
	private static final String DB_URL = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME + "?autoReconnect=true&useSSL=false";

	private static DBManager instance;
	private Connection con;
	
	
	private DBManager(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find driver");
			e.printStackTrace();;
		}

		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			System.out.println("Connection established!");
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
		
	}
	
	public static DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
		}
		
		return instance;
	}
	
	public Connection getConnection(){
		
		return con;
	}
}
