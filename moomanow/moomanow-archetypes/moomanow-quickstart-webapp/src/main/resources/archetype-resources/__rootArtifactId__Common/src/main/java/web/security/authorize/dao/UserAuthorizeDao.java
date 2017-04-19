package com.moomanow.web.security.authorize.dao;

import java.util.Set;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;


public interface UserAuthorizeDao {
	public Set<String> getUserRoles( String userId ) throws RollBackException, NonRollBackException;
	public Set<String> getUserPrivileges( String userId ) throws RollBackException, NonRollBackException;
	public Set<String> getUserPrivilegesByRoleId( String roleId ) throws RollBackException, NonRollBackException;
}
