package com.moomanow.core.common.service;

import java.util.HashSet;
import java.util.Set;

import com.moomanow.core.common.bean.ActionBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.moomanow.core.security.authorize.dao.ActionDao;

public class ActionServiceImpl implements ActionService {

	private ActionDao actionDao;
	@Autowired
	@Required
	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}
	
	@Override
	@Cacheable(cacheName = "actionService.getAuthorizeCodeByAction")
	public Set<String> getAuthorizeCodeByAction(String namespace, String actionName) throws RollBackException, NonRollBackException {
		return new HashSet<String>(actionDao.getAuthorizeCodeByAction(namespace,actionName));
	}

	@Override
	public ActionBean findAction(String namespace, String actionName) throws RollBackException, NonRollBackException {
		return actionDao.findAction(namespace,actionName);
	}

	@Override
	@TriggersRemove(cacheName={"actionService.getAuthorizeCodeByAction"}, removeAll=true)
	public void refresh() throws RollBackException, NonRollBackException {
		
	}

}
