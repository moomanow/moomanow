package com.moomanow.fps.dynamicbean;

import java.io.Serializable;

public class DynamicBeanPro implements Serializable {
	
	private String key;
	private DynamicBeanConverter dynamicBeanConverter;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public DynamicBeanConverter getDynamicBeanConverter() {
		return dynamicBeanConverter;
	}
	public void setDynamicBeanConverter(DynamicBeanConverter dynamicBeanConverter) {
		this.dynamicBeanConverter = dynamicBeanConverter;
	}
	
	
}
