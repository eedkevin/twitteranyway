package com.allenzheng.twittersyn.service.impl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.allenzheng.twittersyn.bean.ExtAccounts;
import com.allenzheng.twittersyn.service.ExtAccService;
import com.allenzheng.twittersyn.utility.TwitterAPI;

public class ExtAccServiceImpl implements ExtAccService{
	
	private static final Log logger = LogFactory.getLog(ExtAccServiceImpl.class);
	private TwitterAPI twitterAPI;
	
	private static final String ACTION_TWITTER_SIGN_IN = "twitter_sign_in";

	public void loginExtAcc(ExtAccounts extAccForm) {
		// TODO Auto-generated method stub
		
		
		
	}
	
	
	@Autowired
	public void setTwitterAPI(TwitterAPI twitterAPI){
		this.twitterAPI = twitterAPI;
	}

}
