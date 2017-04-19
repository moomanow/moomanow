package com.moomanow.core.common.processhandler;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

import com.moomanow.core.common.exception.ProcessException;
import com.moomanow.core.common.exception.TechnicalException;

@Aspect
public class LogProcessFilter {
	private static final Logger logger = Logger.getLogger(LogProcessFilter.class);
	public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		try{
			long start = System.currentTimeMillis();
			logger.info("[Service Start]\tcall:" +proceedingJoinPoint.getSignature().toShortString() );
			Object s = proceedingJoinPoint.proceed();
			long end = System.currentTimeMillis();
			logger.info("[Service End  ]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + "\tTIME:\t" + (end - start));
			return s;
			
		} catch (Throwable e) {
			if(e instanceof TechnicalException){
				TechnicalException te = (TechnicalException) e;
				if(logger.isDebugEnabled())
					logger.debug("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+te.getMessageCode()+"messageText :"+te.getMessage(), te.getThrowable());
				else
					logger.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+te.getMessageCode()+"messageText :"+te.getMessage());
				
			}else if(e instanceof ProcessException){
				ProcessException se =  (ProcessException) e;
				if(logger.isDebugEnabled())
					logger.debug("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+se.getMessageCode()+"messageText :"+se.getMessage(), se.getThrowable());
				else
					logger.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " messageCode : "+se.getMessageCode()+"messageText :"+se.getMessage());
				
			}else{
				logger.error("[Service Error]\tcall:" + proceedingJoinPoint.getSignature().toShortString() + " : "+e.getMessage(), e);
			}
			throw e;
		}
		
	}
}
