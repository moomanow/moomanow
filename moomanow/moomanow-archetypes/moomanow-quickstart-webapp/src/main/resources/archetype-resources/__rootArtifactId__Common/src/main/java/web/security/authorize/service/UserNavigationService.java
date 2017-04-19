package com.moomanow.web.security.authorize.service;


import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.IProcessResult;
import com.moomanow.web.security.authorize.io.NavigationBean;

public interface UserNavigationService {
	public IProcessResult<NavigationBean> generateNavigationList(String namespace, String actionName, String url)throws NonRollBackException , RollBackException;

	public void refresh();
}
