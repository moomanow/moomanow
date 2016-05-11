package com.moomanow.core.common.service;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.bean.UserFacebook;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.ServiceResult;

 

public interface SocialNetworkService {
	public ServiceResult<UserFacebook> getProfileFacebook(String urlGetAccess,String access_token,String expiry) throws RollBackException,NonRollBackException;
	public ServiceResult<UserBean> checkUser(UserFacebook userFacebook) throws RollBackException,NonRollBackException;
}
