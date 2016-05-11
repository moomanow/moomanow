package com.moomanow.web.struts2.bean;

import java.io.Serializable;

public interface MessageAction extends Serializable {

	
	public String getMessageCode();
	public String[] getPara();
	public String getMessageLang();
	public String getDisplayText();
	public String getMessageDesc();
	public String getMessageType();
	public String getMessageTypeCss();
	public String getSolution();
	
	
}
