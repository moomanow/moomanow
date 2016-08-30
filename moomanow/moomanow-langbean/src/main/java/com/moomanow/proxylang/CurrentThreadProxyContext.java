package com.moomanow.proxylang;


public class CurrentThreadProxyContext {
	private static final ThreadLocal<ProxyContext> contextThreadLocal = new ThreadLocal<ProxyContext>();
	
	
	public static ProxyContext getProcessContext(){
		return contextThreadLocal.get();
	}
	
	public static void setProcessContext(ProxyContext processContext){
		contextThreadLocal.set(processContext);
	}
	
	
	public static void remove(){
		contextThreadLocal.remove();
	}
}
