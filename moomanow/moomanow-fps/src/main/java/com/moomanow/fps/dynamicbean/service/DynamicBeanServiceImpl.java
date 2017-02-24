package com.moomanow.fps.dynamicbean.service;

import com.moomanow.fps.dynamicbean.bean.DynamicBeanPro;

public class DynamicBeanServiceImpl implements DynamicBeanService {

	public DynamicBeanPro getKey(String methodName,String name) {
		String key = null;
		if (methodName.startsWith("get")) {
			key=  methodName.substring(methodName.indexOf("get") + 3);
		} else if (methodName.startsWith("set")) {
			key=  methodName.substring(methodName.indexOf("set") + 3);
		} else if (methodName.startsWith("is")) {
			key=  methodName.substring(methodName.indexOf("is") + 2);
		}
		DynamicBeanPro dynamicBeanPro = new DynamicBeanPro();
		dynamicBeanPro.setKey(key);
		return dynamicBeanPro;
	}

}
