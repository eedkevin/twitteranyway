package com.allenzheng.twittersyn.service.impl;

import com.allenzheng.twittersyn.bean.User;
import com.allenzheng.twittersyn.exception.LoginException;
import com.allenzheng.twittersyn.service.LoginService;
import com.allenzheng.twittersyn.utility.RSAUtils;

public class LoginServiceImpl implements LoginService{
	
	private RSAUtils rsaUtils;
	private String userName;
	private String passWd;
	
	
	public void validateUser(User user) throws LoginException{
		
		try {
			userName = rsaUtils.decryptText(user.getUserName());
			passWd = rsaUtils.decryptText(user.getPassWd());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//just for test
		if(!userName.equals("allen") && !passWd.equals("liWACuk8")){
			throw new LoginException();
		}
		
	}
	
	public void setRSAUtils(RSAUtils rsaUtils){
		this.rsaUtils = rsaUtils;
	} 

}
