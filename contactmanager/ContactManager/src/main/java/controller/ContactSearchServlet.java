package controller;

import java.io.IOException;
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

import model.Contact;
import service.ContactService;

/**
 * Servlet implementation class ContactSearchServlet
 */
//@WebServlet("/ContactSearchServlet")
public class ContactSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactService contactService = new ContactService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		  String name = request.getParameter("name");
		 
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
		    try {
		    	int user_id = contactService.getUserIdByEmail(userEmail);
				List<Contact> contacts = contactService.searchContact(name,user_id);
				System.out.println(contacts);
				ServletContext context = getServletConfig().getServletContext();
				
				request.setAttribute("contacts", contacts);
				
				RequestDispatcher rd = request.getRequestDispatcher("/ContactsPage.jsp");
				rd.forward(request, response);
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
