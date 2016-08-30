package com.moomanow.proxylang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProxyContext {

	private Map<Integer, ProxyLangInterceptor> map = new ConcurrentHashMap<Integer, ProxyLangInterceptor>();

	public Map<Integer, ProxyLangInterceptor> getMap() {
		return map;
	}
}
