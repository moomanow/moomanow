/**
 * 
 */
package com.moomanow.core.common.context;

import com.moomanow.core.common.processhandler.ProcessContext;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class CurrentThread {
	private static final ThreadLocal<ProcessContext> contextThreadLocal = new ThreadLocal<ProcessContext>();
	
	
	public static ProcessContext getProcessContext(){
		return contextThreadLocal.get();
	}
	
	public static void setProcessContext(ProcessContext processContext){
		contextThreadLocal.set(processContext);
	}
	
	
	public static void remove(){
		contextThreadLocal.remove();
	}

}
