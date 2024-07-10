package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contact;

import service.ContactService;
import service.DBConnection;

/**
 * Servlet implementation class ContactServlet
 */
//@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactService contactService = new ContactService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
       
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("contact servlet started");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Retrieve the userEmail attribute from the request
		 Cookie[] cookies = request.getCookies();
		    String userEmail = null;

		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if ("userEmail".equals(cookie.getName())) {
		                userEmail = cookie.getValue();
		                break;
		            }
		        }
		    }
		 
		
		
		
		System.out.println("this is user email upper="+userEmail);
		
		try { 
			 int user_id = contactService.getUserIdByEmail(userEmail);
	            List<Contact> contacts = contactService.getAllContacts(user_id);
	            request.setAttribute("contacts", contacts);
	            
	            RequestDispatcher rd = request.getRequestDispatcher("/ContactsPage.jsp");
	            rd.forward(request, response);
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		 String operation = request.getParameter("operation");
		 Cookie[] cookies = request.getCookies();
		    String userEmail = null;

		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if ("userEmail".equals(cookie.getName())) {
		                userEmail = cookie.getValue();
		                break;
		            }
		        }
		    }
	        System.out.println("this is user email lower="+userEmail);
	        
	        if ("update".equals(operation)) {
	            doPut(request, response);
	        } else if ("delete".equals(operation)) {
	        	doDelete(request, response);
	        } else {
	            // Handle add operation
	       
	            String name = request.getParameter("name");
	            String address = request.getParameter("address");
	            String number = request.getParameter("number");
	           
	            
	          
	            
	            Contact newContact = new Contact();
	            newContact.setName(name);
	            newContact.setAddress(address);
	            newContact.setNumber(number);
	            ServletContext servletContext = getServletConfig().getServletContext();
	            try {
	            	
	            	int user_id = contactService.getUserIdByEmail(userEmail);
					if(contactService.addNewContact(newContact,user_id)) //adding new contact
					{
						servletContext.setAttribute("alertType","success");
		            	servletContext.setAttribute("message","Contact Added Successfully");
		             response.sendRedirect("/ContactManager/contacts");
					}  
					else{
						servletContext.setAttribute("alertType","danger");
		            	servletContext.setAttribute("message","Addition of new Contact Failed ");
		             response.sendRedirect("/ContactManager/contacts");
					}
						
					
	            } catch (ClassNotFoundException | SQLException e) {
	                e.printStackTrace();
	                // Handle error
	            }
	        }
	    }
	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Cookie[] cookies = request.getCookies();
		    String userEmail = null;

		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if ("userEmail".equals(cookie.getName())) {
		                userEmail = cookie.getValue();
		                break;
		            }
		        }
		    }
		
		
		
		 ServletContext servletContext = getServletConfig().getServletContext();
		String name = request.getParameter("name");
	        String number = request.getParameter("number");
	        String address = request.getParameter("address");
	        String oldNumber = request.getParameter("oldnumber");

	        Contact updatedContact = new Contact();
	        updatedContact.setName(name);
	        updatedContact.setNumber(number);
	        updatedContact.setAddress(address);
	       // System.out.println(updatedContact +" ----  "+oldName);
	        try {
	        	int user_id = contactService.getUserIdByEmail(userEmail);
	        	
	        	if(contactService.updateContact(updatedContact, oldNumber,user_id)) {
	        		servletContext.setAttribute("alertType","success");
	            	servletContext.setAttribute("message","Contact Updated Successfull");
	             response.sendRedirect("/ContactManager/contacts");
	             }
	        	else {
	        		servletContext.setAttribute("alertType","danger");
	            	servletContext.setAttribute("message","Update failed");
	            	response.sendRedirect("/ContactManager/contacts");
	        	}
	        	
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 Cookie[] cookies = request.getCookies();
		    String userEmail = null;

		    if (cookies != null) {
		        for (Cookie cookie : cookies) {
		            if ("userEmail".equals(cookie.getName())) {
		                userEmail = cookie.getValue();
		                break;
		            }
		        }
		    }
		
		
       String numberToDelete = request.getParameter("oldnumber");
     System.out.println("deletContact "+"----  "+numberToDelete);
       ServletContext servletContext = getServletConfig().getServletContext();
        try {
        	int user_id = contactService.getUserIdByEmail(userEmail);
			
        	if(contactService.deleteContact(numberToDelete,user_id)) {
			System.out.println("contact deleted");
			servletContext.setAttribute("alertType","success");
        	servletContext.setAttribute("message","Contact Deleted Successfully");
            response.sendRedirect("/ContactManager/contacts")
            ;}
			else {
				System.out.println("contact not deleted");
				servletContext.setAttribute("alertType","danger");
            	servletContext.setAttribute("message","Contact not Deleted");
            	response.sendRedirect("/ContactManager/contacts");
            	
            
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle error
        }
	}

}
