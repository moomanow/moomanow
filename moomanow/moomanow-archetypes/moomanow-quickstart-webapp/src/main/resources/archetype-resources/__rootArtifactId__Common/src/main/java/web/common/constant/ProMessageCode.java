package com.moomanow.web.common.constant;

import com.moomanow.core.common.constant.MessageCode;

public enum ProMessageCode implements MessageCode {

	ATZ2001("Unauthorized Operation Business Exception")
	, ATC2001(""), ATC2002(""), ATZ2002(""),ATC2012(""),ATC2013("");




	private ProMessageCode(String dec){
		this.dec = dec;
	}
	private String dec;
	
	public String getCode() {
		return name();
	}
	
	@Override
	public String toString() {
		return "code : "+name()+" dec : "+dec;
	}

}
