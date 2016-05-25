package com.moomanow.core.common.processhandler;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.moomanow.core.common.constant.CommonConstant;
import com.moomanow.core.common.constant.CommonMessageCode;
import com.moomanow.core.common.context.CurrentThread;
import com.moomanow.core.common.exception.BaseException;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.ProcessException;
import com.moomanow.core.common.exception.RollBackTechnicalException;
import com.moomanow.core.common.exception.TechnicalException;

@Aspect
public class ProcessHandler {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProcessHandler.class);
	private static final Logger logger2 = Logger.getLogger("com.moomanow.core.common.processhandler.ProcessHandler.error.view.level");
	
	private TransactionHandler transactionHandler;
	@Autowired
	@Required
	public void setTransactionHandler(TransactionHandler transactionHandler) {
		this.transactionHandler = transactionHandler;
	}
	private MessageHandler messageHandler;
	@Autowired
	@Required
	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
	
	private String processResultClass = "com.moomanow.core.common.processhandler.BangProcessResult";
	public void setProcessResultClass(String processResultClass) {
		this.processResultClass = processResultClass;
	}
	public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info("[Service Start]\tcall:" +proceedingJoinPoint.getSignature().toShortString() );
		
		ProcessContext processContext = CurrentThread.getProcessContext();
		MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
		Method targetInterfaceMethod = methodSignature.getMethod();
		
		
		boolean fristProcess = false;
		boolean isTxnProcess = false;
		if(processContext!=null&&!processContext.startProcess&&IProcessResult.class.equals(targetInterfaceMethod.getReturnType())){
			fristProcess = true;
			processContext.startProcess = true;
			
		}
		;
		if(processContext!=null&&processContext.txnStatus==null){
			isTxnProcess = transactionHandler.isTxnProcess(proceedingJoinPoint);
		}
		Object returnValue = null;
		long start = System.currentTimeMillis();
		try{
			beforeProcess(processContext, isTxnProcess);
			
			returnValue = proceedingJoinPoint.proceed();
			
			afterProcess( returnValue, processContext, isTxnProcess ,fristProcess );
		} catch (Throwable e) {
			if(e instanceof TechnicalException){
				TechnicalException te = (TechnicalException) e;
				if(logger2.isDebugEnabled())
					logger2.debug("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+te.getMessageCode()+"messageText :"+te.getMessage(), te.getThrowable());
				else
					logger2.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+te.getMessageCode()+"messageText :"+te.getMessage());
				
			}else if(e instanceof ProcessException){
				ProcessException se =  (ProcessException) e;
				if(logger2.isDebugEnabled())
					logger2.debug("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+se.getMessageCode()+"messageText :"+se.getMessage(), se.getThrowable());
				else
					logger2.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+se.getMessageCode()+"messageText :"+se.getMessage());
				
			}else{
				logger2.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " : "+e.getMessage(), e);
			}
			processContext = onException(e, processContext, isTxnProcess);
			if (fristProcess&&IProcessResult.class.equals(targetInterfaceMethod.getReturnType())) {

				IProcessResult<Object> ProcessResult = (IProcessResult<Object>) Class.forName(processResultClass).newInstance();

				ProcessResult.setStatus(CommonConstant.PROCESS_STATUS_FAIL);
				processContext.status=CommonConstant.PROCESS_STATUS_FAIL;
				if(e instanceof BaseException)
					returnValue = messageHandler.addMessage(ProcessResult, (BaseException)e);
				else
					returnValue = messageHandler.addMessage(ProcessResult,new RollBackTechnicalException(CommonMessageCode.COM4999, e));
			} else {
				throw e;
			}
		}
		if(fristProcess){
			processContext.startProcess = false;
		}
		long end = System.currentTimeMillis();
		logger.info("[Service End  ]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + "\tTIME:\t" + (end - start));
		return returnValue;
		
	}

	
	private ProcessContext onException(Throwable e,ProcessContext processContext, boolean isTxnProcess) {
		if(isTxnProcess){
			if(e instanceof NonRollBackException){
				transactionHandler.commitTxn(processContext);
//			}else if(e instanceof ){
//				rollbackTxn(serviceContext);
			}else{
				transactionHandler.rollbackTxn(processContext);
			}
			processContext.txnStatus = (null);
		}
		return processContext;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object afterProcess(Object returnValue,ProcessContext processContext, boolean isTxnProcess,boolean fristProcess) {
		if(isTxnProcess){
			transactionHandler.commitTxn(processContext);
//			transactionHandler.unProxy(returnValue, isTxnProcess);
		}
			
		if(returnValue!=null &&returnValue instanceof IProcessResult ){
			
			returnValue = messageHandler.addMessage((IProcessResult) returnValue);
			
		}
		return returnValue;
	}


	private void beforeProcess(ProcessContext processContext,boolean isTxnProcess) {
		if( isTxnProcess ){
			transactionHandler.beginTxn(processContext);
		}
	}
	
}
