
package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthFilter
 */
//@WebFilter(description = "This filter is used for Authentication purpose", urlPatterns = { "/*" })
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("Filter is Destroyed");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//here it required to convert ServletRequest to typecasting into HttpServletRequest
		  HttpServletRequest httpRequest = (HttpServletRequest) request;
	      HttpServletResponse httpResponse = (HttpServletResponse) response;
	       
		Cookie[] cookies = httpRequest.getCookies();
		
		if (cookies != null) {
			for (Cookie ck : cookies) {
				if (ck.getName() != null && ck.getName().equals("userEmail")) {
					chain.doFilter(request, response);
				}
			}
		}
		
		response.getWriter().print("<H1> you are unauthorized user </H1> <br>"
				+ "<a href='/SampleServletProject/'> click here to login </a>");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filter is instantiated");
	}

}