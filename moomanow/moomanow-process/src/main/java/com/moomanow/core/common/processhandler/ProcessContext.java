/**
 * 
 */
package com.moomanow.core.common.processhandler;

import java.util.List;

import com.moomanow.core.common.bean.Message;
import com.moomanow.core.common.bean.UserBean;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class ProcessContext {
	protected String transId;

//	protected TransactionStatus txnStatus;

	protected UserBean userBean;
	
	protected List<Message> messageList;
	
	protected String lang;
}
