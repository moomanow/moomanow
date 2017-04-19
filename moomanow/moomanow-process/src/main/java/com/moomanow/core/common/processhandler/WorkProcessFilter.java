package com.moomanow.core.common.processhandler;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.constant.CommonConstant;
import com.moomanow.core.common.constant.CommonMessageCode;
import com.moomanow.core.common.context.CurrentBlockContextThread;
import com.moomanow.core.common.context.FlowContext;
import com.moomanow.core.common.context.MessageContext;
import com.moomanow.core.common.exception.BaseException;
import com.moomanow.core.common.exception.RollBackTechnicalException;
import com.moomanow.core.common.service.MessageService;

@Aspect
public class WorkProcessFilter {
	private String processResultClass = "com.moomanow.core.common.processhandler.BangProcessResult";
	public void setProcessResultClass(String processResultClass) {
		this.processResultClass = processResultClass;
	}
	public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
		Method targetInterfaceMethod = methodSignature.getMethod();
		

		FlowContext flowContext = CurrentBlockContextThread.getContext(FlowContext.class);
		boolean fristProcess = false;
		Object returnValue=null;
		if(flowContext!=null&&!flowContext.getStartProcess()&&IProcessResult.class.equals(targetInterfaceMethod.getReturnType())){
			fristProcess = true;
			flowContext.setStartProcess(true);
		}
		try{
			returnValue = proceedingJoinPoint.proceed();
			if(returnValue!=null &&returnValue instanceof IProcessResult ){
				
				returnValue = addMessage((IProcessResult) returnValue);
				if(flowContext.getStatus()==null||"".equals(flowContext.getStatus())){
					((IProcessResult) returnValue).setStatus(CommonConstant.PROCESS_STATUS_SUCCESS);
				}else{
					((IProcessResult) returnValue).setStatus(flowContext.getStatus());
				}
			}
		}catch (Throwable e) {
			if (fristProcess&&IProcessResult.class.equals(targetInterfaceMethod.getReturnType())) {

				IProcessResult<Object> ProcessResult = (IProcessResult<Object>) Class.forName(processResultClass).newInstance();

				ProcessResult.setStatus(CommonConstant.PROCESS_STATUS_FAIL);
				if(e instanceof BaseException)
					returnValue = addMessage(ProcessResult, (BaseException)e);
				else
					returnValue = addMessage(ProcessResult,new RollBackTechnicalException(CommonMessageCode.COM4999, e));
			} else {
				throw e;
			}
		}
		return returnValue;
	}
	
	private MessageService messageService;
	@Autowired
	@Required
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	private <T> IProcessResult<T> addMessage(IProcessResult<T> ProcessResult) {
		MessageContext processContext = CurrentBlockContextThread.getContext(MessageContext.class);
		List<IMessage> messageOutList = new LinkedList<IMessage>();
		List<IMessage> messageList = processContext.messageList();
		if(messageList!=null && messageList.size()>0){
			for (IMessage message : messageList) {
				IMessage messageOut = messageService.getMessage(message.getMessageCode(),message.getPara());
				if(messageOut!=null)
				messageOutList.add(messageOut);
			}
			ProcessResult.setMessages(messageOutList);
		}
		
		
		return ProcessResult;
	}


	private <T> IProcessResult<T> addMessage(IProcessResult<T> ProcessResult,BaseException baseException){
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
