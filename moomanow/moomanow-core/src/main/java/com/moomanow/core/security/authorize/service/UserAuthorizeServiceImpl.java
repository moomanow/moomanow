package com.moomanow.core.security.authorize.service;

import java.util.Set;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.constant.CommonMessageCode;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.exception.RollBackProcessException;
import com.moomanow.core.common.processhandler.ServiceResult;
import com.moomanow.core.security.authorize.dao.UserAuthorizeDao;
import com.moomanow.core.security.authorize.service.UserAuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class UserAuthorizeServiceImpl implements UserAuthorizeService {

	
	private UserAuthorizeDao userAuthorizeDao;
	@Autowired
	@Required
	public void setUserAuthorizeDao(UserAuthorizeDao userAuthorizeDao) {
		this.userAuthorizeDao = userAuthorizeDao;
	}
	@Override
	public ServiceResult<UserBean> addRolesUser(UserBean userBean) throws NonRollBackException, RollBackException {
		Set<String> roles = userAuthorizeDao.getUserRoles(userBean.getUserId());
		if(roles.size()<=0){
			throw new RollBackProcessException(CommonMessageCode.ATZ2002);
		}
		Set<String> privileges = userAuthorizeDao.getUserPrivileges(userBean.getUserId());
		userBean.setRoles(roles);
		userBean.setPrivileges(privileges);
		return new ServiceResult<UserBean>(userBean);
	}

}
