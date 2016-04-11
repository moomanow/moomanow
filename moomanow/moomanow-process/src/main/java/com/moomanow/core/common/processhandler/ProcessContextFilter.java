/**
 * 
 */
package com.moomanow.core.common.processhandler;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.moomanow.core.common.bean.UserBean;
import com.moomanow.core.common.constant.CommonConstant;
import com.moomanow.core.common.context.CurrentThread;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public class ProcessContextFilter implements Filter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProcessContextFilter.class);
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ProcessContext processContext = CurrentThread.getProcessContext();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpServletRequest.getSession(true);	
		MDC.put(CommonConstant.LOG.CONTEXT_PATH, (String)((HttpServletRequest) request).getContextPath());
		MDC.put(CommonConstant.LOG.SERVER_NAME,(String) request.getServerName());
		MDC.put(CommonConstant.LOG.SERVER_PORT, request.getServerPort());
		MDC.put(CommonConstant.LOG.SERVER_INSTANCE_SERVER_NAME, InetAddress.getLocalHost().getHostName());
		MDC.put(CommonConstant.LOG.SERVER_INSTANCE_NAME, System.getProperty("com.sun.aas.instanceName"));
		MDC.put(CommonConstant.LOG.SERVER_INSTANCE_IP, InetAddress.getLocalHost().getHostAddress());
		MDC.put(CommonConstant.LOG.SESSION_ID, ((HttpServletRequest) request).getSession().getId());
		MDC.put(CommonConstant.LOG.USER_ID, httpSession.getAttribute(CommonConstant.SESSION.USER_ID)==null?"guest"+getRealIp(request):httpSession.getAttribute(CommonConstant.SESSION.USER_ID));
		MDC.put(CommonConstant.LOG.USER_NAME, httpSession.getAttribute(CommonConstant.SESSION.USER_NAME)==null?"guest"+getRealIp(request):httpSession.getAttribute(CommonConstant.SESSION.USER_NAME));
		if (logger.isDebugEnabled()) {
			logger.debug("[RequestURI Start]\t" + httpServletRequest.getRequestURI());
		}
		if(processContext == null){

			processContext = new ProcessContext();
			
			UserBean userBean = (UserBean) httpSession.getAttribute(CommonConstant.SESSION.USER_BEAN_KEY);
			
			if(userBean!=null){
				processContext.userBean = (userBean);
				String userId = processContext.userBean.getUserId()==null?"guest"+getRealIp(request):processContext.userBean.getUserId();
				String userName = processContext.userBean.getUserName()==null?"guest"+getRealIp(request):processContext.userBean.getUserName();
				httpSession.setAttribute(CommonConstant.SESSION.USER_ID, userId);
				httpSession.setAttribute(CommonConstant.SESSION.USER_NAME, userName);
				MDC.put(CommonConstant.LOG.USER_ID, userId);
				MDC.put(CommonConstant.LOG.USER_NAME, userName);
			}
			
			CurrentThread.setProcessContext(processContext);
		}

		chain.doFilter(request,response);
		if (logger.isDebugEnabled()) {
			logger.debug("[RequestURI End  ]\t" + httpServletRequest.getRequestURI());
		}
		CurrentThread.remove();
	}

	private String getRealIp(ServletRequest request){
		String ipAddress = null;
		ipAddress = ((HttpServletRequest) request).getHeader("X-FORWARDED-FOR");
		if (ipAddress == null)
			ipAddress = ((HttpServletRequest) request).getHeader("HTTP_X_FORWARDED_FOR");
		if (ipAddress == null)
			ipAddress = request.getRemoteAddr();
		
		return ipAddress;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
