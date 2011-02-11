package com.allenzheng.twittersyn.bean;

public class User {
	
	private String userName;
	private String passWd;
	
	public User(String userName, String passWd){
		this.setUserName(userName);
		this.setPassWd(passWd);
		
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param passWd the passWd to set
	 */
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	/**
	 * @return the passWd
	 */
	public String getPassWd() {
		return passWd;
		
	}
	
	public boolean isEmpty(){
		if(userName == null && passWd == null){
			return false;
		}else{
			return true;
		}
	}
	

}
