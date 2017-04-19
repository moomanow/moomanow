package com.moomanow.web.common.process.bean;

import java.io.Serializable;
import java.util.List;

import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.bean.PagingBean;
import com.moomanow.core.common.constant.CommonConstant;
import com.moomanow.core.common.processhandler.IProcessResult;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class ProcessResult<T extends Object> implements IProcessResult<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5062307198339604396L;
	private T result;
	private String status;
	private List<IMessage> messages;
	private PagingBean pagingBean;
	
	public ProcessResult(T t) {
		result = t;
	}
	public ProcessResult(T t,PagingBean pagingBean) {
		this.pagingBean = pagingBean;
		result = t;
	}
	
	public ProcessResult() {
	}
	
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public boolean isSuccess() {
		return CommonConstant.PROCESS_STATUS_SUCCESS.equals(status);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<IMessage> getMessages() {
		return messages;
	}
	
	public IMessage getMessage(){
		return (messages!=null &&messages.size()>=1)?messages.get(0):null;
	}
	
	public void setMessages(List<IMessage> messages) {
		this.messages = messages;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	
}