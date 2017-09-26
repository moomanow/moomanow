package com.moomanow.core.common.bean;

import java.util.Set;

public interface UserBean {
	public String getUserId();
//	public void setUserId(String userId);
	
	public String getUserName();
//	public void setUserName(String userName);
	
	public String getPassword();
//	public void setPassword(String password);
	
	public String getFirstName();
//	public void setFirstName(String firstName);
	
	public String getLastName();
//	public void setLastName(String lastName);
	
	public String getName();
	
	public String getStatus();
	public Set<String> getPrivileges();
//	public void setStatus(String status);
}
