package com.moomanow.core.common.processhandler;

import javax.servlet.http.HttpServletRequest;

import com.moomanow.core.common.bean.UserBean;

public class UserBeanContext extends Context {


	protected UserBean userBean;
	@Override
	public void load(HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		
	}
	
	public String getUserName(){
		if(userBean==null||userBean.getUserName()==null||"".equals(userBean.getUserName()))
			return "GUEST";
		return userBean.getUserName();
	}

}
