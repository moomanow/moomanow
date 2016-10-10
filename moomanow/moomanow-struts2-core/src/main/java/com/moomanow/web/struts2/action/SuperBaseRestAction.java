/**
 * 
 */
package com.moomanow.web.struts2.action;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.RestActionSupport;
import org.springframework.beans.factory.BeanNameAware;

import com.moomanow.core.common.bean.IJSONResult;
import com.moomanow.core.common.bean.IMessage;
import com.moomanow.web.struts2.bean.Button;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ValidationWorkflowAware;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public abstract class SuperBaseRestAction extends BaseRestAction {

	

	public abstract HttpHeaders index()throws Exception ;
	public abstract HttpHeaders editNew()throws Exception;
	public abstract HttpHeaders edit()throws Exception ;
	public abstract HttpHeaders create() throws Exception ;
	public abstract HttpHeaders destroy()throws Exception ;
	public abstract HttpHeaders show()throws Exception ;
	public abstract HttpHeaders update()throws Exception ;

	
}
