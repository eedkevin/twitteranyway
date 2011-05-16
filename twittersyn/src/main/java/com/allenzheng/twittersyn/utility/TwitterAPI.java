package com.allenzheng.twittersyn.utility;

import twitter4j.TwitterException;

public interface TwitterAPI {
	
	public String getConsumerKey();
	
	public String getConsumerSecret();
	
	public String getAccTokenUrl();
	
	public String getCallBackUrl();
	
	public String getAuthorisationUrl() throws TwitterException;

}
