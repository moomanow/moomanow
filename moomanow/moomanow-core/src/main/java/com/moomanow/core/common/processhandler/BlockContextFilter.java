package com.moomanow.core.common.processhandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.moomanow.core.common.context.CurrentBlockContextThread;

public class BlockContextFilter implements Filter{

	private List<Class<? extends Context>> conextClassList = new ArrayList<>(); 
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)throws IOException, ServletException {
		BlockContext blockContext = CurrentBlockContextThread.getBlockContext();

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if(blockContext == null){
			blockContext = new BlockContext();
			for (Class<? extends Context> conextClass : conextClassList) {
				
				try {
					Context context = conextClass.newInstance();
					context.load(httpServletRequest);
					blockContext.setContext(context);
				} catch (InstantiationException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String paramValue = filterConfig.getInitParameter("conextStringClassList");
		String[] conextStringClassList = paramValue.split(",");
		for (String contextClass : conextStringClassList) {
			try {
				conextClassList.add((Class<? extends Context>) Class.forName(contextClass));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
