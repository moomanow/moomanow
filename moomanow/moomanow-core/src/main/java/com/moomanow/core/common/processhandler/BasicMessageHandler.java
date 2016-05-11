package com.moomanow.core.common.processhandler;

import java.util.LinkedList;
import java.util.List;

import com.moomanow.core.common.bean.Message;
import com.moomanow.core.common.constant.CommonConstant;
import com.moomanow.core.common.context.CurrentThread;
import com.moomanow.core.common.exception.BaseException;
import com.moomanow.core.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public class BasicMessageHandler implements MessageHandler {

	private MessageService messageService;
	@Autowired
	@Required
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	@Override
	public <T> ServiceResult<T> addMessage(ServiceResult<T> serviceResult) {
		ProcessContext processContext = CurrentThread.getProcessContext();
		List<Message> messageOutList = new LinkedList<Message>();
		List<Message> messageList = processContext.messageList;
		if(messageList!=null && messageList.size()>0){
			for (Message message : messageList) {
				Message messageOut = messageService.getMessage(message.getMessageCode(),message.getPara());
				if(messageOut!=null)
				messageOutList.add(messageOut);
			}
			serviceResult.setMessages(messageOutList);
		}
		if(processContext.status==null||"".equals(processContext.status)){
			serviceResult.setStatus(CommonConstant.SERVICE_STATUS_SUCCESS);
		}else{
			serviceResult.setStatus(processContext.status);
		}
		
		return serviceResult;
	}


	@Override
	public <T> ServiceResult<T> addMessage(ServiceResult<T> serviceResult,BaseException baseException){
		List<Message> messageOutList = new LinkedList<Message>();
		if(baseException!=null&&baseException.getMessageCode()!=null){
				Message messageOut = messageService.getMessage(baseException.getMessageCode().getCode(),new String[]{});
				if(messageOut!=null)
				messageOutList.add(messageOut);
			serviceResult.setMessages(messageOutList);
		}
		
		return serviceResult;
	}
}
