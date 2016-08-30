package com.moomanow.proxylang;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyLangInterceptor implements MethodInterceptor {

	private String lang="ENG";
	private Map<String, Object> mapLang = new HashMap<String, Object>();
	public ProxyLangInterceptor(Object s) {
		mapLang.put(lang, s);
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Object retValFromSuper;
		Object landObject= mapLang.get(lang);
		if(landObject == null)
			return null;
		retValFromSuper = method.invoke(landObject, args);
		
		
		return retValFromSuper;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

}
