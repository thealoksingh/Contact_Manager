package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.UserAlreadyExistException;
import model.User;

public class UserService {
	public boolean userLogin(User user) throws ClassNotFoundException, SQLException {

		
		Connection conn = DBConnection.getConnection();
//		String query = "insert into users(email,password,name) values("+email+","+password+","+name+")";

		PreparedStatement ps = conn.prepareStatement("select * from users where email = ? and password = ?");

		ps.setString(1, user.getEmail());

		ps.setString(2, user.getPassword());

		ResultSet rs = ps.executeQuery();

		return rs.next();

	
}
	
public int userRegister(User user) throws SQLException, ClassNotFoundException, UserAlreadyExistException {
		
		if( !(isEmailExist(user.getEmail())) ) {
			
		
		Connection conn = DBConnection.getConnection();
//		String query = "insert into users(email,password,name) values("+email+","+password+","+name+")";
		PreparedStatement ps = conn.prepareStatement("insert into users(email,password,name) values(?,?,?)");

		ps.setString(1, user.getEmail());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getName());

		return ps.executeUpdate();
		}
		
		throw new UserAlreadyExistException("User with email = "+user.getEmail()+" already exist");
	}

	private boolean isEmailExist(String email) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
//		String query = "insert into users(email,password,name) values("+email+","+password+","+name+")";
		PreparedStatement ps = conn.prepareStatement("select * from users where email = ?");
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		return rs.next();
		
	}}
