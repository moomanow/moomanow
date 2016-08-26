
package com.moomanow.logger;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class MDC implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3908803341329013252L;

	private static Method put = getMethod("put", String.class, Object.class);
	private static Method get = getMethod("get", String.class);
	private static Method remove = getMethod("remove", String.class);
	private static Method getMap = getMethod("getMap");


	private static Method getMethod(String name, Class<?>... parameterTypes) {
		List<String> lList = Arrays.asList(new String[] { "org.jboss.logging.MDC", "org.apache.log4j.MDC" });
		Class<?> clazz = null;
		try {
			for (String class1 : lList) {
				try{
					clazz = Class.forName(class1);
					break;
				}catch (Exception e) {
				}
				
			}
			if(clazz == null) return null;
			return clazz.getMethod(name, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
//			e.printStackTrace();
		}
		return null;

	}

	public static Object put(String key, Object val) {
		try {
			return put.invoke(null, key, val);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		}
		return null;
	}

	public static Object get(String key) {
		try {
			return get.invoke(null, key);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		}
		return null;
	}

	public static void remove(String key) {
		try {
			remove.invoke(null, key);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		}
	}

	public static Map<String, Object> getMap() {
		try {
			return (Map<String, Object>) getMap.invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		}
		return null;
	}
}
