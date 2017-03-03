package com.moomanow.fps.dynamicbean.proxy;

import java.lang.reflect.Method;
import java.util.Map;

import org.mockito.cglib.proxy.InvocationHandler;
import org.mockito.cglib.proxy.Proxy;
import org.springframework.context.ApplicationContext;

import com.moomanow.core.common.context.ApplicationContextUtil;
import com.moomanow.fps.dynamicbean.bean.DynamicBeanPro;
import com.moomanow.fps.dynamicbean.service.DynamicBeanService;

public class ProxyDynamicBean implements InvocationHandler {

	private Map<String, Object> map;
	private DynamicBeanService dynamicBeanService;
	private String neuronName;

	public ProxyDynamicBean(Map<String, Object> map, DynamicBeanService dynamicBeanService,String neuronName) {
		this.map = map;
		this.dynamicBeanService = dynamicBeanService;
		this.neuronName = neuronName;
	}



	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		String methodName = m.getName();
		DynamicBeanPro dynamicBeanPro = dynamicBeanService.getKey(methodName,neuronName);
		String key = dynamicBeanPro.getKey();
		if (methodName.startsWith("get")) {
			return map.get(key);
		} else if (methodName.startsWith("set")) {
			map.put(key, args[0]);
			return null;
		} else if (methodName.startsWith("is")) {
			return (map.get(key));
		}
		return null;
	}
//	main function
	public static Object newInstance( Map<String, Object> map,Class[] interfaces,DynamicBeanService dynamicBeanService,String neuronName) {
		return Proxy.newProxyInstance(map.getClass().getClassLoader(), interfaces, new ProxyDynamicBean(map, dynamicBeanService,neuronName));
	}

	public static <T> T newInstance(Map<String, Object> data, Class<T> dataOutClass,DynamicBeanService dynamicBeanService, String neuronName) {
		return (T) newInstance(data, new Class[]{dataOutClass}, dynamicBeanService, neuronName);
	}

	public static Object newInstance(Map<String, Object> data, Class[] interfaces, String neuronName) {
		DynamicBeanService dynamicBeanService = ApplicationContextUtil.getBean(DynamicBeanService.class);
		return newInstance(data, interfaces, dynamicBeanService, neuronName);
	}
	
	public static <T> T newInstance(Map<String, Object> data, Class<T> dataOutClass, String neuronName) {
		DynamicBeanService dynamicBeanService = ApplicationContextUtil.getBean(DynamicBeanService.class);
		return (T) newInstance(data, dataOutClass, dynamicBeanService, neuronName);
	}

}
