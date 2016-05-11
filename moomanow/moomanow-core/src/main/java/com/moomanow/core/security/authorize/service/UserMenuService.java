package com.moomanow.core.security.authorize.service;

import java.util.List;
import java.util.Set;

import com.moomanow.core.common.bean.MenuVO;
import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.ServiceResult;
import com.moomanow.core.security.authorize.bean.MenuBean;

public interface UserMenuService {
	public ServiceResult<MenuVO> generateMenuList(UserBean userBean)throws NonRollBackException , RollBackException;
	public ServiceResult<MenuVO> generateMenuList(Set<String> privileges)throws NonRollBackException , RollBackException;
	public ServiceResult<MenuVO> generateMenuGuest()throws NonRollBackException , RollBackException;

}
