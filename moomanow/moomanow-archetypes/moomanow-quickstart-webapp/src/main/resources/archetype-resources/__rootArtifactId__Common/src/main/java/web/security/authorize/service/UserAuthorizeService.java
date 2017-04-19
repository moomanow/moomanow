package com.moomanow.web.security.authorize.service;


import com.moomanow.authentication.bean.UserAuthenticationBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.IProcessResult;

public interface UserAuthorizeService {
	public IProcessResult<UserAuthenticationBean> addRolesUser(UserAuthenticationBean userBean) throws NonRollBackException , RollBackException;
}
