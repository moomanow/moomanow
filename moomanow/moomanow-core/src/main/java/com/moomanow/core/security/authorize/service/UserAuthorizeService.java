package com.moomanow.core.security.authorize.service;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.ServiceResult;

public interface UserAuthorizeService {
	public ServiceResult<UserBean> addRolesUser(UserBean userBean) throws NonRollBackException , RollBackException;
}
