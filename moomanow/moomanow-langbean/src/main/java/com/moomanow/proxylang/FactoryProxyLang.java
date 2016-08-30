package com.moomanow.proxylang;


import net.sf.cglib.proxy.Enhancer;

public class FactoryProxyLang {

	public static <T extends Object> T proxyBean(T s) {
		try {
			ProxyContext proxyContext = CurrentThreadProxyContext.getProcessContext();
			if (proxyContext == null) {

				proxyContext = new ProxyContext();
				CurrentThreadProxyContext.setProcessContext(proxyContext);
			}
			ProxyLangInterceptor proxyLang = new ProxyLangInterceptor(s);
			Enhancer e = new Enhancer();
			e.setSuperclass(s.getClass());
			e.setCallback(proxyLang);
			Object object = e.create();
			proxyContext.getMap().put(object.hashCode(), proxyLang);
			return (T) object;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}

	public static <T extends Object> void proxyBeanLang(T s, String lang) {
		try {
			ProxyContext proxyContext = CurrentThreadProxyContext.getProcessContext();
			proxyContext.getMap().get(s.hashCode()).setLang(lang);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new Error(e.getMessage());
		}

	}
}
