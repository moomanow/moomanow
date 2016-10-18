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
public abstract class BaseRestAction extends RestActionSupport implements RequestAware,SessionAware,ServletRequestAware,ServletResponseAware,PrincipalAware,BeanNameAware,ValidationWorkflowAware {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1859485375890690768L;
	protected static final String MESSAGE = "message";
	protected HttpServletRequest httpServletRequest;
	protected HttpServletResponse httpServletResponse;
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	protected List<IMessage> messageList;
	protected List<Button> buttonList;
	protected String nextUrl;
	protected String backUrl;
	protected String nextNamespace;
	protected String nextAction;
	protected String dynamicParameterValue;
	protected String dynamicParameterName;
	protected String inputResultName;
	protected String action;
	protected String namespace;
	
	
	protected String beanName;
	
	protected PrincipalProxy principalProxy;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		httpServletRequest =arg0;
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		httpServletResponse = arg0;
	}
	
	@Override
	public void setBeanName(String arg0) {
		beanName = arg0;
	}

	public String getActionName(){
		return beanName.replaceAll("Action", "");
	}
	
	
	@Override
	public void setPrincipalProxy(PrincipalProxy arg0) {
		principalProxy = arg0;
	}
	
	@Override
	public String getInputResultName() {
		return inputResultName;
	}
	
	public void setInputResultName(String inputResultName) {
		this.inputResultName = inputResultName;
	}

	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public String getNextNamespace() {
		return nextNamespace;
	}

	public void setNextNamespace(String nextNamespace) {
		this.nextNamespace = nextNamespace;
	}


	public String getDynamicParameterValue() {
		return dynamicParameterValue;
	}

	public void setDynamicParameterValue(String dynamicParameterValue) {
		this.dynamicParameterValue = dynamicParameterValue;
	}

	public String getDynamicParameterName() {
		return dynamicParameterName;
	}

	public void setDynamicParameterName(String dynamicParameterName) {
		this.dynamicParameterName = dynamicParameterName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setMessageList(List<IMessage> messageList) {

		this.messageList = messageList;

	}

	public List<IMessage> getMessageList() {

		return messageList;

	}

	public IMessage getMessage() {

		return (messageList == null || messageList.size() <= 0 ? null : messageList.get(messageList.size() - 1));

	}
	
	public void setButtonList(List<Button> buttonList) {
		this.buttonList = buttonList;
	}
	public List<Button> getButtonList() {
		return buttonList;
	}
}
