/*
 *  Copyright (c) 2009, Steven Wang
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *      
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *  twitterSina at http://twitterSina.appspot.com
 *  twitterSina code at http://twitterSina.googlecode.com
 * 	
 */
package com.allenzheng.twittersyn.controller;

import java.io.*; 
import java.net.*;
import java.util.*; 
import java.util.logging.Logger;
import javax.servlet.*; 
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

import com.allenzheng.twittersyn.*;
import com.allenzheng.twittersyn.common.*;
import com.allenzheng.twittersyn.twitter.TwitterAPI;



/**
 * 用户账户相关的Servlet
 * @author Steven Wang <http://steven-wang.appspot.com>
 */
public class AccountServlet extends HttpServlet
{
	private static String fileToken = File.separator + "WEB-INF"
		+ File.separator + "token.txt";
	private static String callbackUrl;
	
	private static final long serialVersionUID = -2632036842196276788L;
	private static final Log logger = LogFactory.getLog(AccountServlet.class);
	
	private static final String PARAM_ACTION = "action";
	private static final String ACTION_TWITTER_SIGN_IN = "twitter_sign_in";
	private static final String PARAM_OAUTH_TOKEN = "oauth_token";
	private static final String PARAM_OAUTH_VERIFIER = "oauth_verifier";
	
	private static final String ATTR_TWITTER = "twitter";
	private static final String ATTR_REQUEST_TOKEN = "request_token";
	
	private static final String COOKIE_TWITTER_ID = "twitter_id";
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

//		consumerKey = config.getInitParameter(INIT_PARAM_CONSUMER_KEY).trim();
//		consumerSecret = config.getInitParameter(INIT_PARAM_CONSUMER_SECRET)
//				.trim();
//		logger.debug("Consumer key retrieved from web.xml");
//		logger.debug("Consumer secret retrieved from web.xml");

//		callbackUrl = config.getInitParameter(INIT_PARAM_CALLBACK_URL);
		TwitterAPI twitterapi = new TwitterAPI();
		twitterapi.getCallBackUrl();
		if (callbackUrl != null && !callbackUrl.trim().equals("")) {
			callbackUrl = callbackUrl.trim();
			logger.debug("Callback support is enabled, callback url: "
					+ callbackUrl);
		}

		fileToken = getServletContext().getRealPath("/") + fileToken;
		logger.debug("File to store / load access tokens: " + fileToken);
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
	{
		doPost(request, response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{
		String action = request.getParameter(PARAM_ACTION);
		
		HttpSession session = request.getSession();
		
		if(ACTION_TWITTER_SIGN_IN.equals(action)){
			logger.debug("Signing in with Twitter...");
			TwitterAPI twitterapi = new TwitterAPI();
			
			String id = null;
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie;
				cookie = cookies[i];
				if (COOKIE_TWITTER_ID.equals(cookie.getName())) {
					id = cookie.getValue();
				}
			}
			
			AccessToken accessToken = null;
			if (id != null){
				
			}
			
			
		}
		
		String oauthToken = request.getParameter(PARAM_OAUTH_TOKEN);
		if(oauthToken != null){
			logger.debug(PARAM_OAUTH_TOKEN + " received from Twitter");
			try {
				Twitter twitter = (Twitter) session.getAttribute(ATTR_TWITTER);
				RequestToken requestToken = (RequestToken) session
						.getAttribute(ATTR_REQUEST_TOKEN);
				AccessToken accessToken;
				if (callbackUrl == null) {
					accessToken = twitter.getOAuthAccessToken(requestToken);
				} else {
					String oauthVerifier = request
							.getParameter(PARAM_OAUTH_VERIFIER);
					logger.debug(PARAM_OAUTH_VERIFIER
							+ " received from Twitter");
					accessToken = twitter.getOAuthAccessToken(requestToken
							.getToken(), requestToken.getTokenSecret(),
							oauthVerifier);
				}
				twitter.setOAuthAccessToken(accessToken);
				session.removeAttribute(ATTR_REQUEST_TOKEN);
				session.setAttribute(ATTR_TWITTER, twitter);

				int id = twitter.verifyCredentials().getId();
				logger.debug("Access token retrieved for user " + id
						+ " from Twitter");
//				storeAccessToken(id, accessToken);
				Cookie cookie = new Cookie(COOKIE_TWITTER_ID, "" + id);
				cookie.setMaxAge(63072000); // Valid for 2 years
				response.addCookie(cookie);
				logger.debug("Cookie set for user " + id);

				// Get last status and friends' timelines
//				getMyLastStatusAndStoreInSession(session);
//				getFriendsTimelinesAndStoreInSession(session);

				// Go to the update status page
//				request.getRequestDispatcher(PAGE_UPDATE_STATUS).forward(
//						request, response);
			} catch (TwitterException e) {
				logger.error("Failed to retrieve access token - "
						+ e.getMessage());
				throw new ServletException(e);
			}
		}
	}
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//		throws IOException, ServletException
//	{
//		String twitterUserName = request.getParameter("twitterUserNameTxt");
//		String twitterUserPwd = request.getParameter("twitterUserPwdTxt");
//		String sinaUserName = request.getParameter("sinaUserNameTxt");
//		String sinaUserPwd = request.getParameter("sinaUserPwdTxt");
//		String renrenUserName = request.getParameter("renrenUserNameTxt");
//		String renrenUserPwd = request.getParameter("renrenUserPwdTxt");
//		String diguUserName = request.getParameter("diguUserNameTxt");
//		String diguUserPwd = request.getParameter("diguUserPwdTxt");
//		String zuosaUserName = request.getParameter("zuosaUserNameTxt");
//		String zuosaUserPwd = request.getParameter("zuosaUserPwdTxt");
//		if(twitterUserName == null || twitterUserPwd == null ||
//				sinaUserName == null || sinaUserPwd == null ||
//				renrenUserName == null || renrenUserPwd == null ||
//				diguUserName == null || diguUserPwd == null ||
//				zuosaUserName == null || zuosaUserPwd == null)
//		{
//			response.sendRedirect("account.jsp");
//		}
//		
//		//将各个同步源的账号信息放入一个集合中
//		List<Account> accountList = new ArrayList<Account>();
//		Account account = null;
//		StringBuffer userNameSb = new StringBuffer();   //for write log
//		
//		if(twitterUserName.length() > 0 && twitterUserPwd.length() > 0)
//		{
//			account = new Account();
//			account.setAccountType(AccountType.TWITTER);
//			account.setUserName(twitterUserName);
//			account.setUserPwd(twitterUserPwd);
//			accountList.add(account);
//			
//			userNameSb.append(AccountType.TWITTERNAME);
//			userNameSb.append("-");
//			userNameSb.append(account.getUserName());
//			userNameSb.append(",");
//		}
//		
//		if(sinaUserName.length() > 0 && sinaUserPwd.length() > 0)
//		{
//			account = new Account();
//			account.setAccountType(AccountType.SINA);
//			account.setUserName(sinaUserName);
//			account.setUserPwd(sinaUserPwd);
//			accountList.add(account);
//			
//			userNameSb.append(AccountType.SINANAME);
//			userNameSb.append("-");
//			userNameSb.append(account.getUserName());
//			userNameSb.append(",");
//		}
//		
//		if(renrenUserName.length() > 0 && renrenUserPwd.length() > 0)
//		{
//			account = new Account();
//			account.setAccountType(AccountType.RENREN);
//			account.setUserName(renrenUserName);
//			account.setUserPwd(renrenUserPwd);
//			accountList.add(account);
//			
//			userNameSb.append(AccountType.RENRENNAME);
//			userNameSb.append("-");
//			userNameSb.append(account.getUserName());
//			userNameSb.append(",");
//		}
//		
//		if(diguUserName.length() > 0 && diguUserPwd.length() > 0)
//		{
//			account = new Account();
//			account.setAccountType(AccountType.DIGU);
//			account.setUserName(diguUserName);
//			account.setUserPwd(diguUserPwd);
//			accountList.add(account);
//			
//			userNameSb.append(AccountType.DIGUNAME);
//			userNameSb.append("-");
//			userNameSb.append(account.getUserName());
//			userNameSb.append(",");
//		}
//		
//		if(zuosaUserName.length() > 0 && zuosaUserPwd.length() > 0)
//		{
//			account = new Account();
//			account.setAccountType(AccountType.ZUOSA);
//			account.setUserName(zuosaUserName);
//			account.setUserPwd(zuosaUserPwd);
//			accountList.add(account);
//			
//			userNameSb.append(AccountType.ZUOSANAME);
//			userNameSb.append("-");
//			userNameSb.append(account.getUserName());
//			userNameSb.append(",");
//		}
//		
//		String checkResult = checkAccountLogin(accountList);   //验证账户
//		writeLog(userNameSb.toString(), checkResult, request);
//		request.getSession().setAttribute("accountList", accountList);
//		request.getSession().setAttribute("accountCheckResult", checkResult);
//		writeCookies(accountList, response);
//		
//		if(checkResult == null)
//		{
//			response.sendRedirect("main.jsp");
//		}
//		else
//		{
//			response.sendRedirect("account.jsp");
//		}
//	}
//	
//	private String checkAccountLogin(List<Account> accountList)
//	{
//		StringBuffer sb = new StringBuffer();
//		boolean fail = false;
//		String result = null;
//		for(Account account : accountList)
//		{
//			switch(account.getAccountType())
//			{
//				case AccountType.TWITTER:
//					if(!new TwitterAPI().loginTwitter
//							(account.getUserName(), account.getUserPwd()))
//					{
//						fail = true;
//						sb.append(AccountType.TWITTERNAME);
//						sb.append(",");
//						account.setAuth(false);
//					}
//					else
//					{
//						account.setAuth(true);
//					}
//					break;
//				case AccountType.SINA:
//					if(new SinaHttp().loginSina
//							(account.getUserName(), account.getUserPwd()) == null)
//					{
//						fail = true;
//						sb.append(AccountType.SINANAME);
//						sb.append(",");
//						account.setAuth(false);
//					}
//					else
//					{
//						account.setAuth(true);
//					}
//					break;
//				case AccountType.RENREN:
//					if(new RenrenHttp().loginRenren
//							(account.getUserName(), account.getUserPwd()) == null)
//					{
//						fail = true;
//						sb.append(AccountType.RENRENNAME);
//						sb.append(",");
//						account.setAuth(false);
//					}
//					else
//					{
//						account.setAuth(true);
//					}
//					break;
//				case AccountType.DIGU:
//					if(!new DiguAPI().loginDigu
//							(account.getUserName(), account.getUserPwd()))
//					{
//						fail = true;
//						sb.append(AccountType.DIGUNAME);
//						sb.append(",");
//						account.setAuth(false);
//					}
//					else
//					{
//						account.setAuth(true);
//					}
//					break;
//				case AccountType.ZUOSA:
//					if(!new ZuosaAPI().loginZuosa
//							(account.getUserName(), account.getUserPwd()))
//					{
//						fail = true;
//						sb.append(AccountType.ZUOSANAME);
//						sb.append(",");
//						account.setAuth(false);
//					}
//					else
//					{
//						account.setAuth(true);
//					}
//					break;
//			}
//		}
//		if(fail)
//		{
//			result = sb.toString();
//			result = result.substring(0, result.length() - 1);
//			result += "身份验证失败！";
//		}
//		return result;
//	}
	
	private void writeCookies(List<Account> accountList, HttpServletResponse response)
	{
		try
		{
			String accountListCode = JsonSerializer.serialize(accountList);
			Cookie cookie = null;
			cookie = new Cookie("accountList",URLEncoder.encode(accountListCode, "utf-8"));
			cookie.setMaxAge(315360000);
			response.addCookie(cookie);
		}
		catch(Exception e){}
	}
	
	private void writeLog(String userName, String result, HttpServletRequest request)
	{
		if(result == null)
			result = "验证成功！";
		Logger log = Logger.getLogger(this.getClass().getName());
		log.info("SaveAccount:" + new Date() + "," + userName + "@" + result + 
			". From " + request.getRemoteAddr() + ":" + request.getHeader("User-Agent"));
	}
}
