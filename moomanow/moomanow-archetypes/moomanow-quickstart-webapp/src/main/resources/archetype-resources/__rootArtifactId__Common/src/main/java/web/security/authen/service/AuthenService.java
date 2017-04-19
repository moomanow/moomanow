package com.moomanow.web.security.authen.service;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.IProcessResult;

public interface AuthenService {
	
	public IProcessResult<UserBean> login(String username, String password) throws NonRollBackException , RollBackException;

}
