/**
 * 
 */
package com.moomanow.authentication.bean;

import java.util.Set;

/**
 * @author Jaurpong.w(Kwan)package com.moomanow.core.common.bean;
 *
 */
public interface UserAuthenticationBean {
	
	
	
	public Set<String> getRoles();
	public void setRoles(Set<String> roles);
	
	public String getRole();
	public String getRoleName();
	
	
	public void setRole(String role);
	
	public Set<String> getPrivileges();
	public void setPrivileges(Set<String> Privileges);
	
	public void setRoleName(String userRoleName);
	public String getUserId();
}
