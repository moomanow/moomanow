package com.moomanow.web.security.openid.dao;


import org.mitre.oauth2.model.RegisteredClient;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public interface OpenIdDao {

	public RegisteredClient findByIssuer(String issuer)throws RollBackException, NonRollBackException;

	public void saveRegisteredClient(String issuer, RegisteredClient client)throws RollBackException, NonRollBackException;

}
