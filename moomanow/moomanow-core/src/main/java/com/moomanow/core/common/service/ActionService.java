package com.moomanow.core.common.service;

import java.util.Set;

import com.moomanow.core.common.bean.ActionBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public interface ActionService {

	public Set<String> getAuthorizeCodeByAction(String namespace, String actionName) throws RollBackException, NonRollBackException;
	
	public void refresh() throws RollBackException, NonRollBackException;

	public ActionBean findAction(String namespace, String actionName) throws RollBackException, NonRollBackException;

}
