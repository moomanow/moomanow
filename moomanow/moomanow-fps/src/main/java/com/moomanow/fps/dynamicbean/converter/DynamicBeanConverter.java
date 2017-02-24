package com.moomanow.fps.dynamicbean.converter;

public interface DynamicBeanConverter<T> {

	
	public T put(Object[] args);
	
	public Object[] get(T t);
}
