package com.moomanow.proxylang;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class FactoryProxyLang {

	public static <T extends Object> T proxyBean(final ProxyLangService proxyLangService ,final T object, final String lang) {
		try {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(object.getClass());
			final Object objectLang;
			if ("ENG".equalsIgnoreCase(lang)) {
				objectLang = object;
			} else {
				objectLang = proxyLangService.modifyObjetLang(object, lang);

			}
			enhancer.setCallback(new MethodInterceptor() {

				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					
					
					if (method.getReturnType().isAssignableFrom(String.class)) {
						return method.invoke(objectLang, args);
					}
					Object objectOut =  method.invoke(object, args);
					if (method.getReturnType().isPrimitive()) {
						return objectOut;
					}
					if (method.getReturnType().isAssignableFrom(Date.class)) {
						return objectOut;
					}
					if (method.getReturnType().isAssignableFrom(BigDecimal.class)) {
						return objectOut;
					}
					if (method.getReturnType().isAssignableFrom(Calendar.class)) {
						return objectOut;
					}
					return FactoryProxyLang.proxyBean(proxyLangService,objectOut, lang);
				}
			});

			return (T) enhancer.create();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}

}
