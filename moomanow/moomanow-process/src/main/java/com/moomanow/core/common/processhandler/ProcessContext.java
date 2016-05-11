/**
 * 
 */
package com.moomanow.core.common.processhandler;

import java.util.List;
import java.util.Locale;

import org.springframework.transaction.TransactionStatus;

import com.moomanow.core.common.bean.Message;
import com.moomanow.core.common.bean.UserBean;
import com.moomanow.web.struts2.bean.MessageAction;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class ProcessContext {
	protected String transId;

	protected TransactionStatus txnStatus;

	protected UserBean userBean;
	
	protected List<Message> messageList;
	
	protected String status;
	
	protected Locale locale;
	
	protected String lang;
	
	public TransactionStatus getTxnStatus() {
		return txnStatus;
	}
	
	public String getUserName(){
		if(userBean==null||userBean.getUserName()==null||"".equals(userBean.getUserName()))
			return "GUEST";
		return userBean.getUserName();
	}
	
	public void setLocale(Locale locale) {
		this.locale =locale;
	}
	public Locale getLocale() {
		return locale;
	}
}
