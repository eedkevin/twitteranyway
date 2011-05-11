package com.allenzheng.twittersyn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.allenzheng.twittersyn.bean.User;
import com.allenzheng.twittersyn.common.JsonSerializer;
import com.allenzheng.twittersyn.exception.LoginException;
import com.allenzheng.twittersyn.service.LoginService;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

@Controller
public class LoginController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4244421300251919328L;
	private LoginService loginServ;
	
	public final String myUserName = "xudongzheng";
	public final String myPassWd = "liWACuk8";
	
	
	@RequestMapping(value="/login.json", method = RequestMethod.GET)
	public void doBrowserLogin(@CookieValue("userName") String userName, 
			@CookieValue("passWd") String passWd){
		
		if(userName != null && passWd != null){
			User user = new User(userName, passWd);
			try{
				loginServ.validateUser(user);
			}catch(LoginException ex){
				
			}
			
		}
		
	}
	
	@RequestMapping(value="/login.json", method=RequestMethod.POST)
	public void doClientLogin(@Valid User user, BindingResult result, ModelMap modelMap){
		
		if(!result.hasErrors()){
			try{
				loginServ.validateUser(user);
			}catch(LoginException ex){
				// not implement yet
				
			}
			
			modelMap.addAttribute(user);
			
		}
		
	}
	
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//		throws IOException, ServletException{
//		doPost(request, response);
//	}
//	
//	@SuppressWarnings("unchecked")
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException{
//		
//		String userName = null;
//		String passWd = null;
//		
//		Cookie cookies[] = request.getCookies();
//		int i ;
//		for(i = 0; i<cookies.length; i++){
//			if(cookies[i].getName().equals("userName")){
//				userName = cookies[i].getValue();
//			}
//			if(cookies[i].getName().equals("passWd")){
//				passWd = cookies[i].getValue();
//			}
//			if(userName != null && passWd != null){
////				request.setAttribute("userName", userName);
////				request.setAttribute("passWd", passWd);
////				request.getRequestDispatcher("")
//				
//				//Login
//			}else{
//				
//			}
//				
//		}
//		
//		
////		String userName = request.getParameter("userName");
////		String passWd = request.getParameter("passWd");
//		if(userName.equals(myUserName) && 
//				passWd.equals(myPassWd)){
//			response.sendRedirect("main.jsp");
//			
//		}
//		
//	}
	
//	@Autowired
//	public void setLoginService(LoginService loginServ){
//		this.loginServ = loginServ;
//	}

}
