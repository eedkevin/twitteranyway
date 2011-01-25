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
package com.allenzheng.twittersyn.twitter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.allenzheng.twittersyn.controller.AccountServlet;

import twitter4j.*;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

/**
 * 利用API对Twitter进行相关操作
 * @author Steven Wang <http://steven-wang.appspot.com>
 */
public class TwitterAPI 
{
	private static String callbackUrl;
	private static final Log logger = LogFactory.getLog(TwitterAPI.class);

	
	public TwitterAPI(){}
	
	/**
	* 登录loginTwitter
	* @param userName，twitter账号
	* @param userPwd，twitter密码
	 * @throws TwitterException 
	* @return，返回登录是否成功
	*/
	
	
	public String getConsumerKey(){
		
		Properties prop = loadProperties();
		
		return prop.getProperty("twitter.consumerkey");
		
	}
	
	public String getConsumerSecret(){
		Properties prop = loadProperties();
		
		return prop.getProperty("twitter.consumersecret");
	
	}
	
	public String getReqTokenUrl(){
		Properties prop = loadProperties();
		
		return prop.getProperty("twitter.reqtokenurl");
	}
	
	public String getAccTokenUrl(){
		Properties prop = loadProperties();
		
		return prop.getProperty("twitter.acctokenurl");
	}
	
	public String getCallBackUrl(){
		
		Properties prop = loadProperties();
		
		return prop.getProperty("twitter.callbackurl");
	}
	
	public void loginTwitterOAuth() throws TwitterException{
		Properties prop = loadProperties();
		
			Twitter twitter = new Twitter();
			twitter.setOAuthConsumer(prop.getProperty("twitter.consumerkey"), 
					prop.getProperty("twitter.consumersecret"));
			try {
				RequestToken requestToken;
				if (callbackUrl == null) {
					requestToken = twitter.getOAuthRequestToken();
				} else {
					requestToken = twitter
							.getOAuthRequestToken(callbackUrl);
				}
				String authorisationUrl = requestToken
						.getAuthorizationURL();
//				session.setAttribute(ATTR_TWITTER, twitter);
//				session.setAttribute(ATTR_REQUEST_TOKEN, requestToken);

				logger.debug("Redirecting user to " + authorisationUrl);
//				response.sendRedirect(authorisationUrl);
			} catch (TwitterException e) {
				logger.error("Sign in with Twitter failed - "
						+ e.getMessage());
				e.printStackTrace();
				throw new TwitterException(e);
			}
		
		
	}
	public boolean loginTwitter(String userName, String userPwd)
	{
		try
		{
			User user = new Twitter(userName,userPwd).verifyCredentials();
			if(user != null)
				return true;
		}
		catch(TwitterException e){}
		return false;
//		return true;
	}
	
	
	
	/**
	* 发布Twitter消息
	* @param twitterUserName，twitter账号
	* @param twitterUserPwd，twitter密码
	* @param publishContent，发布的内容
	* @return，返回发布是否成功
	*/
	public boolean publishTwitter(String twitterUserName, String twitterUserPwd, String publishContent)
	{
		Twitter twitter = new Twitter(twitterUserName,twitterUserPwd);
		try
		{
			twitter = new Twitter(twitterUserName,twitterUserPwd);
//			twitter.setClientURL("http://twittersina.appspot.com");
//			twitter.setClientVersion("http://twittersina.appspot.com");
//			twitter.setSource("twittersina");
			twitter.updateStatus(publishContent);
			return true;
		}
		catch(TwitterException e)
		{
			return false;
		}
//		return false;
	}
	
	private Properties loadProperties(){
		InputStream inputStream = this.getClass().
			getClassLoader().getResourceAsStream("oauth.properties");
		Properties prop = new Properties();
		try{
			prop.load(inputStream);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		return prop;
	}
}
