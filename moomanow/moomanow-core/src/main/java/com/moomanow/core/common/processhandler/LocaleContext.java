package com.moomanow.core.common.processhandler;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class LocaleContext extends Context {

	private Locale locale;
	public LocaleContext() {
	}
	public LocaleContext(Locale english) {
		this();
		locale = english;
	}

	@Override
	public void load(HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		
	}
	
	public Locale getLocale() {
		return locale;
	}

}
