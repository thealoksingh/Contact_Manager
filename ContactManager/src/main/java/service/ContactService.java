package service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.UserAlreadyExistException;
import model.Contact;
import model.User;

public class ContactService {
	
	public int getUserIdByEmail(String email) throws ClassNotFoundException, SQLException {
	    Connection conn = DBConnection.getConnection();
	    PreparedStatement ps = conn.prepareStatement("SELECT user_id FROM users WHERE email = ?");
	    ps.setString(1, email);
	    ResultSet rs = ps.executeQuery();
	    
	    if (rs.next()) {
	        return rs.getInt("user_id");
	    } else {
	        throw new SQLException("User not found");
	    }
	}
	public List<Contact> getAllContacts(int user_id) throws ClassNotFoundException, SQLException {

		List<Contact> contacts = new ArrayList<Contact>();
		Connection conn = DBConnection.getConnection();
//		String query = "insert into users(email,password,name) values("+email+","+password+","+name+")";

		PreparedStatement ps = conn.prepareStatement("select * from contact where user_id =? ");
		ps.setInt(1, user_id);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			Contact contact = new Contact();
		
			contact.setName(rs.getString("name"));
			contact.setAddress(rs.getString("address"));
			contact.setNumber(rs.getString("number"));
			
			contacts.add(contact);
			
			System.out.println(contact);
		}

		return contacts;
	}
	public boolean addNewContact(Contact contact,int user_id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();

		PreparedStatement ps = conn.prepareStatement("insert into contact (user_id,name,number,address) values(?,?,?,?)");
		ps.setInt(1,user_id);
		ps.setString(2, contact.getName());
		ps.setString(3, contact.getNumber());
		ps.setString(4,contact.getAddress());
		
		return ps.executeUpdate()>0;
	}
	//updateBook
	 public boolean updateContact(Contact contact, String oldNumber,int user_id ) throws ClassNotFoundException, SQLException {
	        Connection conn = DBConnection.getConnection();
	        
	        PreparedStatement ps = conn.prepareStatement("UPDATE contact SET name = ?, number = ?, address = ? WHERE number = ? and user_id= ? ");
	        
	        ps.setString(1, contact.getName());
	        ps.setString(2, contact.getNumber());
	        ps.setString(3, contact.getAddress());
	        ps.setString(4, oldNumber);
	        ps.setInt(5, user_id);
	        
	        return ps.executeUpdate() > 0;
	    }
//searchBooks()
	
	public List<Contact> searchContact( String name,int user_id) throws ClassNotFoundException, SQLException {
	    Connection conn = DBConnection.getConnection();

	    // Corrected SQL syntax with proper comma separation and spacing
	  
	    List<Contact> contacts = new ArrayList<Contact>();


		PreparedStatement ps = conn.prepareStatement("select * from contact where name like ? and user_id= ? ");
       ps.setString(1,name+'%');
       ps.setInt(2,user_id);
       
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			Contact contact = new Contact();
		
			contact.setName(rs.getString("name"));
			contact.setAddress(rs.getString("address"));
			contact.setNumber(rs.getString("number"));
			
			contacts.add(contact);
			
			//System.out.println(book);
		}

		return contacts;
	

}
	public boolean deleteContact(String oldNumber,int user_id ) throws ClassNotFoundException, SQLException {
	  
		Connection conn = DBConnection.getConnection();
	    System.out.println(oldNumber);
	    PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE number = ? and user_id= ? ");
	    ps.setString(1, oldNumber);
	    ps.setInt(2, user_id);
	    return ps.executeUpdate() > 0;
	}
	
	
	


}