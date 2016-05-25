package com.moomanow.core.common.processhandler;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.constant.CommonConstant;
import com.moomanow.core.common.context.CurrentThread;
import com.moomanow.core.common.exception.BaseException;
import com.moomanow.core.common.service.MessageService;

public class BasicMessageHandler implements MessageHandler {

	private MessageService messageService;
	@Autowired
	@Required
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	@Override
	public <T> IProcessResult<T> addMessage(IProcessResult<T> ProcessResult) {
		ProcessContext processContext = CurrentThread.getProcessContext();
		List<IMessage> messageOutList = new LinkedList<IMessage>();
		List<IMessage> messageList = processContext.messageList;
		if(messageList!=null && messageList.size()>0){
			for (IMessage message : messageList) {
				IMessage messageOut = messageService.getMessage(message.getMessageCode(),message.getPara());
				if(messageOut!=null)
				messageOutList.add(messageOut);
			}
			ProcessResult.setMessages(messageOutList);
		}
		if(processContext.status==null||"".equals(processContext.status)){
			ProcessResult.setStatus(CommonConstant.PROCESS_STATUS_SUCCESS);
		}else{
			ProcessResult.setStatus(processContext.status);
		}
		
		return ProcessResult;
	}


	@Override
	public <T> IProcessResult<T> addMessage(IProcessResult<T> ProcessResult,BaseException baseException){
		List<IMessage> messageOutList = new LinkedList<IMessage>();
		if(baseException!=null&&baseException.getMessageCode()!=null){
				IMessage messageOut = messageService.getMessage(baseException.getMessageCode().getCode(),new String[]{});
				if(messageOut!=null)
				messageOutList.add(messageOut);
			ProcessResult.setMessages(messageOutList);
		}
		
		return ProcessResult;
	}
}
