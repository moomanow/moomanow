package com.moomanow.core.security.authen.service;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.ServiceResult;

public interface AuthenService {

	public ServiceResult<UserBean> login(String username, String password) throws NonRollBackException, RollBackException;

	public ServiceResult<UserBean> login(Long userId) throws NonRollBackException,RollBackException;
}
