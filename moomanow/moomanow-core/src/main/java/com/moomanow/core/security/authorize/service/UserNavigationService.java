package com.moomanow.core.security.authorize.service;

import java.util.List;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.ServiceResult;
import com.moomanow.core.security.authorize.bean.MenuBean;
import com.moomanow.core.security.authorize.io.NavigationBean;

public interface UserNavigationService {
	public ServiceResult<NavigationBean> generateNavigationList(String namespace, String actionName, String url)throws NonRollBackException , RollBackException;

	public void refresh();
}
