package com.moomanow.web.security.authen.dao;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public interface UserOpenIdDao {

	public UserBean findUserByUsername(String username) throws NonRollBackException, RollBackException;


	public UserBean findUserByIdUser(Long id) throws RollBackException, NonRollBackException;


}
