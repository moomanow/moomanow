package com.moomanow.core.common.processhandler;

import javax.servlet.http.HttpServletRequest;

public abstract class Context {
	
	public abstract void load(HttpServletRequest httpServletRequest);

}
