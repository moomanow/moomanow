package com.moomanow.core.common.processhandler;

import com.moomanow.core.common.exception.BaseException;

public interface MessageHandler {

	<T extends Object> IProcessResult<T> addMessage(IProcessResult<T> returnValue);

	<T extends Object> IProcessResult<T> addMessage(IProcessResult<T> returnValue,BaseException baseException);

}
