package com.moomanow.core.common.processhandler;

import java.util.HashMap;
import java.util.Map;

public class BlockContext {

	private Map<String, Context> map = new HashMap<String, Context>();
	
	@SuppressWarnings("unchecked")
	public <T> T getContext(Class<T> class1){
		return (T) map.get(class1.getName());
	}
	
	public <T extends Context> void setContext(T t){
		map.put(t.getClass().getName(), t);
	}
}
