package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.UserAlreadyExistException;
import model.User;
import service.ContactService;
import service.DBConnection;
import service.UserService;
import service.ContactService;

/**
 * Servlet implementation class AuthServlet
 */
//@WebServlet("/users")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	 private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthServlet() {
        super();
        userService = new UserService();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	
		System.out.println("auth servlet started");
		
		User user = new User();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("psw"));

        PrintWriter out = response.getWriter();
        ServletContext servletContext = getServletConfig().getServletContext();
        try {
            if (userService.userLogin(user)) {
          
            	servletContext.setAttribute("alertType","success");
            	servletContext.setAttribute("message","Login Successfull");
            	//response.sendRedirect("/ContactManager/contacts");
            	
            	String userEmail = request.getParameter("email");
        		
            	request.setAttribute("userEmail", userEmail);
            	
            	Cookie ck=new Cookie("userEmail",userEmail);
        		response.addCookie(ck);
                
        		// Forward the request to the ContactServlet
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/contacts");
        		dispatcher.forward(request, response);
            	

            } else {
               // out.println("<h1> Login Failed </h1>");
                servletContext.setAttribute("alertType","danger");
            	servletContext.setAttribute("message","Authentication Failed");
            	response.sendRedirect("/ContactManager/");
                
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h1> Login Failed </h1>");
            e.printStackTrace();
        }
	}
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
		  User user = new User();

	        String email = request.getParameter("email");
	        user.setEmail(email);

	        String pass = request.getParameter("psw");
	        user.setPassword(pass);

	        String name = request.getParameter("name");
	        user.setName(name);

	        response.setContentType("text/html;charset=UTF-8");
	        ServletContext servletContext = getServletConfig().getServletContext();
	       
	        PrintWriter out = response.getWriter();

	        int rows;
	        try {
	            rows = userService.userRegister(user);
	        } catch (ClassNotFoundException e) {
	            System.out.println("Driver class not loaded");
	            e.printStackTrace();
	            return;
	        } catch (SQLException e) {
	            System.out.println("either connection problem occurs or Query execution Failed");
	            e.printStackTrace();
	            return;
	        } catch (UserAlreadyExistException e) {
	            System.out.println(e.getMessage());
	            out.println("<h1> " + e.getMessage() + " </h1>");
	            return;
	        }

	        if (rows > 0) {
	        	
	            System.out.println("User Registered Successfully");
	            servletContext.setAttribute("alertType","success");
            	servletContext.setAttribute("message","User Reistered Successfully");
            	response.sendRedirect("/ContactManager/");
	           // out.println("<h1> User Registered Successfully </h1>");
	        } else {
	        	servletContext.setAttribute("alertType","danger");
            	servletContext.setAttribute("message","User Registration Failed");
	            System.out.println("User registration failed");
	            response.sendRedirect("/ContactManager/");
	          //  out.println("<h1> User Registration Failed </h1>");
	        }
	    }
	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
