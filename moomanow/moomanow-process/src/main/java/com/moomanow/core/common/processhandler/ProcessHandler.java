/**
 * 
 */
package com.moomanow.core.common.processhandler;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
@Aspect
public class ProcessHandler {
	
	private static final Logger logger = Logger.getLogger(ProcessHandler.class);
	private static final Logger logger2 = Logger.getLogger("org.kanomchan.core.common.processhandler.ProcessHandler.error.view.level");

	
	public Object doAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//logger.info("[Service Start]\tcall:" +proceedingJoinPoint.getSignature().toShortString() );
		
//		ProcessContext processContext = CurrentThread.getProcessContext();
//		MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
//		Method targetInterfaceMethod = methodSignature.getMethod();

		try{
			
			return proceedingJoinPoint.proceed();
			
		} catch (Throwable e) {
			
		}
		return null;
	}
}
