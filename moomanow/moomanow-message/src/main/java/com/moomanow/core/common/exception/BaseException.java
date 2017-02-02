package com.moomanow.core.common.exception;

import java.util.List;

import com.moomanow.core.common.constant.MessageCode;

public interface BaseException {
	
	
	public MessageCode getMessageCode();
	public List<String> getPara();
	public Throwable getThrowable();
	public String getMessage();
	
}
