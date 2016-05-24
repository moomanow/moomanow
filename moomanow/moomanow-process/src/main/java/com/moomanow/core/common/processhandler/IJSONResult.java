package com.moomanow.core.common.processhandler;


import java.util.List;

import com.moomanow.core.common.bean.PagingBean;
import com.moomanow.core.common.bean.IMessage;

public interface IJSONResult<T> {

	public Integer getiTotalDisplayRecords();
	public Long getiTotalRecords();
	
	public T getResult();

	public void setResult(T result);
	
	public boolean isSuccess();
	
	public String getStatus();
	public void setStatus(String status);
	
	public void setMessages(List<IMessage> messages);
	
	public List<IMessage> getMessages();
	
	public IMessage getMessage();

	public PagingBean getPagingBean();

	public void setPagingBean(PagingBean pagingBean);
	
	
}