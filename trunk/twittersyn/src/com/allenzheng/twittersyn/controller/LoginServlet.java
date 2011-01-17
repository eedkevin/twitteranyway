package com.allenzheng.twittersyn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allenzheng.twittersyn.common.JsonSerializer;

public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4244421300251919328L;
	
	public final String myUserName = "xudongzheng";
	public final String myPassWd = "liWACuk8";
	
	private JsonSerializer jsonSerializer;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		doPost(request, response);
	}
	
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		
		String userName = null;
		String passWd = null;
		
		Cookie cookies[] = request.getCookies();
		int i ;
		for(i = 0; i<cookies.length; i++){
			if(cookies[i].getName().equals("userName")){
				userName = cookies[i].getValue();
			}
			if(cookies[i].getName().equals("passWd")){
				passWd = cookies[i].getValue();
			}
			if(userName != null && passWd != null){
//				request.setAttribute("userName", userName);
//				request.setAttribute("passWd", passWd);
//				request.getRequestDispatcher("")
				
				//Login
			}else{
				
			}
				
		}
		
		
//		String userName = request.getParameter("userName");
//		String passWd = request.getParameter("passWd");
		if(userName.equals(myUserName) && 
				passWd.equals(myPassWd)){
			response.sendRedirect("main.jsp");
			
		}
		
	}

}
