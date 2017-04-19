package com.moomanow.core.common.processhandler;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.moomanow.core.common.context.CurrentBlockContextThread;
import com.moomanow.core.common.context.TransactionContext;
import com.moomanow.core.common.exception.NonRollBackException;

@Aspect
public class TransactionProcessFilter {

	private static final Logger logger = Logger.getLogger(TransactionProcessFilter.class);
	private PlatformTransactionManager platformTransactionManager;
	@Autowired
	@Required
	public void setPlatformTransactionManager(PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}
	private int timeout;
	
	
	public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		TransactionContext transactionContext = CurrentBlockContextThread.getContext(TransactionContext.class);
		boolean isTxnProcess = isTxnProcess(proceedingJoinPoint);
		try{
			if(isTxnProcess)
				beginTxn(transactionContext);
			Object ss = proceedingJoinPoint.proceed();
			return ss;
		}catch (Exception e) {
			if(isTxnProcess){
				if(e instanceof NonRollBackException){
					commitTxn(transactionContext);
//				}else if(e instanceof ){
//					rollbackTxn(serviceContext);
				}else{
					rollbackTxn(transactionContext);
				}
//				processContext.txnStatus = (null);
			}
			throw e;
		}
	}
	

	
	private boolean isTxnProcess(ProceedingJoinPoint joinPoint){
		
		
		//== STEP 1 : Check is Class declare @Transactional 
		Class<?> targetInterface = joinPoint.getSignature().getDeclaringType();
		if( targetInterface.isAnnotationPresent(Transactional.class) ){
			return true;
		}
		
		Class<? extends Object> targetImplClass = joinPoint.getTarget().getClass();
		if( targetImplClass.isAnnotationPresent(Transactional.class) ){
			return true;
		}

		//== STEP 2 : Check is Method declare @Transactional
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Method targetInterfaceMethod = methodSignature.getMethod();
				
		if( targetInterfaceMethod != null && targetInterfaceMethod.isAnnotationPresent(Transactional.class) ){
			return true;
		}

		try {
			Method targetImplMethod = targetImplClass.getMethod(targetInterfaceMethod.getName(), targetInterfaceMethod.getParameterTypes());
			if( targetImplMethod != null && targetImplMethod.isAnnotationPresent(Transactional.class) ){
				return true;
			}
		} 
		catch (SecurityException e) {
			logger.error("isTxnProcess(ProceedingJoinPoint)", e);
		} 
		catch (NoSuchMethodException e) {
			logger.error("isTxnProcess(ProceedingJoinPoint)", e);
		}
		
		//if targetClass and targetMethod have no @Transactional -> return false	
		return false;
	}

	private void beginTxn(TransactionContext transactionContext) {
		try {
			if( transactionContext.getTransactionStatus() == null ){
				if (logger.isDebugEnabled()) {
					logger.debug("beginTxn(ProcessContext) - beginTxn");
				}
				DefaultTransactionDefinition txnDefinition = new DefaultTransactionDefinition(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
				if(timeout>0){
					txnDefinition.setTimeout(timeout);
				}
				TransactionStatus txnStatus = platformTransactionManager.getTransaction(txnDefinition);	
//				logger.debug("TransactionStatus.isCompleted "+txnStatus.isCompleted());
				transactionContext.setTransactionStatus(txnStatus);
			}
		} 
		catch( Exception e ) {
			logger.error("beginTxn(ServiceContext)", e); //$NON-NLS-1$
		}
		
	}
	private void commitTxn(TransactionContext transactionContext){
		TransactionStatus txnStatus = transactionContext.getTransactionStatus();
		if( txnStatus != null ){
			transactionContext.setTransactionStatus(null);
			if (logger.isDebugEnabled()) {
				logger.debug("commitTxn(ProcessContext) - commitTxn");
			}
			platformTransactionManager.commit(txnStatus);
			
		}
		
		
	}
	
	
	
	

	private void rollbackTxn(TransactionContext transactionContext){
		TransactionStatus txnStatus = transactionContext.getTransactionStatus();
		if( txnStatus != null ){
			transactionContext.setTransactionStatus(null);
			if (logger.isDebugEnabled()) {
				logger.debug("rollbackTxn(ProcessContext) - rollback");
			}
			platformTransactionManager.rollback(txnStatus);
		}
		
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
