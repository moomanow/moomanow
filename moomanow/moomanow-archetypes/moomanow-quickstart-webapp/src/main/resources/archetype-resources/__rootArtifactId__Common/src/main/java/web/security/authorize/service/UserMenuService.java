package com.moomanow.web.security.authorize.service;

import java.util.Set;

import com.moomanow.authentication.bean.UserAuthenticationBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.IProcessResult;
import com.moomanow.web.security.authen.bean.MenuVO;


public interface UserMenuService {
	public IProcessResult<MenuVO> generateMenuList(UserAuthenticationBean userBean)throws NonRollBackException , RollBackException;
	public IProcessResult<MenuVO> generateMenuList(Set<String> privileges)throws NonRollBackException , RollBackException;
	public IProcessResult<MenuVO> generateMenuGuest()throws NonRollBackException , RollBackException;

}
