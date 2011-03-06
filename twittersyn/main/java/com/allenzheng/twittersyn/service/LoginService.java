package com.allenzheng.twittersyn.service;

import com.allenzheng.twittersyn.bean.User;
import com.allenzheng.twittersyn.exception.LoginException;

public interface LoginService {
	
	public void validateUser(User user) throws LoginException;

}
