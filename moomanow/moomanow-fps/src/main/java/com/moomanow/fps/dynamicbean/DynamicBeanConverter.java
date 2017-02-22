package com.moomanow.fps.dynamicbean;

public interface DynamicBeanConverter<T> {

	
	public T put(Object[] args);
	
	public Object[] get(T t);
}
