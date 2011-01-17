package com.allenzheng.twittersyn.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserInfo {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	public Long id;
	
	@Persistent
	public String userName;
	
	@Persistent
	public String passWd;
	
	public UserInfo(String userName, String passWd){
		
		this.userName = userName;
		this.passWd = passWd;
		
	}
	
	
	

}
