/**
 * 
 */
package com.moomanow.core.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware {
	private static ApplicationContext appContext;
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		ApplicationContextUtil.appContext =appContext;
	}
	public static ApplicationContext getApplicationContext() {
	    return appContext;
	 }
	
	public static Object getBean(String beanName){
		return appContext.getBean(beanName);
	}
	
	public static <T> T getBean(String beanName,Class<T> class1){
		return appContext.getBean(beanName,class1);
	}
	
	public static <T> T getBean(Class<T> class1){
		return appContext.getBean(class1);
	}
}
