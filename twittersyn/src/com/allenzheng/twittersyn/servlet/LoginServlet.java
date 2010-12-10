package com.allenzheng.twittersyn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4244421300251919328L;
	
	public final String myUserName = "xudongzheng";
	public final String myPassWd = "liWACuk8";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		doPost(request, response);
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		
		String userName = request.getParameter("userName");
		String passWd = request.getParameter("passWd");
		if(userName.equals(myUserName) && 
				passWd.equals(myPassWd)){
			response.sendRedirect("main.jsp");
		}
		
	}

}
