package com.moomanow.core.security.authen.service;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.cookie.bean.CookieOrm;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.io.LoginIO;
import com.moomanow.core.common.processhandler.ServiceResult;
import com.moomanow.core.security.authen.bean.IUserDefault;

public interface LoginService {

	public ServiceResult<UserBean> performLogin(String username, String password)throws NonRollBackException,RollBackException;
	public ServiceResult<LoginIO> performLoginAndPutDataSession(String username, String password,CookieOrm cookieOrm)throws NonRollBackException,RollBackException;
//	public ServiceResult<LoginIO> performLoginAndPutDataSession(String authorizationCode,String state,Map<String, Object> session)throws NonRollBackException,RollBackException;
//	public ServiceResult<AuthRequestBean> startSSO()throws NonRollBackException,RollBackException;
//	public ServiceResult<AuthRequestBean> startSSO(String identifier)throws NonRollBackException,RollBackException;
//	public ServiceResult<AuthRequestBean> startSSO(String identifier,String redirectUri)throws NonRollBackException,RollBackException;
	public ServiceResult<LoginIO> performLoginWithOutPasswordAndPutDataSession(String username,CookieOrm cookieOrm) throws NonRollBackException, RollBackException;
	public ServiceResult<LoginIO> performLoginWithOutPasswordAndPutDataSession(Long userId,CookieOrm cookieOrm) throws NonRollBackException, RollBackException;
	public ServiceResult<LoginIO> performLoginAndPutDataSession(UserBean userBean,IUserDefault userDefault) throws NonRollBackException,RollBackException;

}
