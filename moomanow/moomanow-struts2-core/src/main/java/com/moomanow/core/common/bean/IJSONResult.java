package com.moomanow.core.common.bean;


import java.util.List;

import com.moomanow.web.struts2.bean.IMessage;

public interface IJSONResult<T>  {

//	public Long getiTotalRecords();
	public T getResult();

	public void setResult(T result);
	
	public boolean isSuccess();
	
	public String getStatus();
	public void setStatus(String status);
	
	public void setMessages(List<IMessage> messages);
	
	public List<IMessage> getMessages();
	
	public IMessage getMessage();

//	public IPagingBean getPagingBean();
//
//	public void setPagingBean(IPagingBean pagingBean);
	
	
}