package com.moomanow.core.common.processhandler;

import com.moomanow.core.common.exception.BaseException;

public interface MessageHandler {

	<T extends Object> ServiceResult<T> addMessage(ServiceResult<T> returnValue);

	<T extends Object> ServiceResult<T> addMessage(ServiceResult<T> returnValue,BaseException baseException);

}
