/**
 * 
 */
package com.moomanow.core.common.processhandler;

import java.util.List;

import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.bean.PagingBean;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public interface IProcessResult<T extends Object> {

//	public IProcessResult(T t);
//	public IProcessResult(T t,PagingBean pagingBean);
//	
//	public IProcessResult();
	
	public T getResult() ;
	public void setResult(T result);
	public boolean isSuccess();
	public String getStatus();
	public void setStatus(String status);
	
	public List<IMessage> getMessages();
	
	public IMessage getMessage();
	
	void setMessages(List<IMessage> messages);
	public PagingBean getPagingBean();
	public void setPagingBean(PagingBean pagingBean);
	
}
