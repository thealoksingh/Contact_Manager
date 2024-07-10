package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	
	public DBConnection() {
		super();
	}

	public static  Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		
		System.out.println("driver file loaded");
		
		return DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/contact_manager","root","7761");  
	}
}