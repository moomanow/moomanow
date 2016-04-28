/**
 * 
 */
package com.moomanow.core.common.process.Interceptor;

import com.moomanow.core.common.process.ProcessInvocation;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public interface Interceptor {

	void init();
	void destroy();
	String intercept(ProcessInvocation invocation) throws Exception;
}
