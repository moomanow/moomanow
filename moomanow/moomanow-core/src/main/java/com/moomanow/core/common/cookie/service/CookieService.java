package com.moomanow.core.common.cookie.service;

import java.util.List;

import javax.servlet.http.Cookie;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.ServiceResult;

import com.moomanow.core.common.cookie.bean.CookieBean;
import com.moomanow.core.common.cookie.bean.CookieOrm;

public interface CookieService {

	public ServiceResult<CookieBean> checkCookie(CookieOrm cookieBean)throws RollBackException,NonRollBackException;

	public ServiceResult<CookieOrm> genCookieByUserBean(UserBean userBean,CookieOrm cookieOrmOld)throws RollBackException,NonRollBackException;

	public ServiceResult<List<Cookie>> genCookieBeanToCookie(CookieOrm cookieOrmOld)throws RollBackException, NonRollBackException;
}
