package com.moomanow.core.security.authen.service;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.bean.UserSocialNetworkBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.ServiceResult;

public interface LoginWithSocialMetworkService {

	/**login with email for social network :tong**/
 	public ServiceResult<UserBean> performLoginWithEmailSocialId(UserSocialNetworkBean userSocial)throws NonRollBackException,RollBackException;
 	public UserSocialNetworkBean checkuserSocialNetwork(UserSocialNetworkBean userSocial)throws NonRollBackException,RollBackException;
 	
 	
}
