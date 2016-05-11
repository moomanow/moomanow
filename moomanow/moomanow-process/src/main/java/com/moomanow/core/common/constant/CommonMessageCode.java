package com.moomanow.core.common.constant;
/**
 * @author Jaurpong.w(Kwan)
 *
 */
public enum CommonMessageCode implements MessageCode {
	COM0000("Transaction Complete.")
	,COM0001("Success! Insert {1} Complete.")
	,COM0002("Success! Update {1} Complete.")
	,COM0003("Success! Delete {1} Complete.")
	, COM4999("Exction"), COM2001(""), COM4994(""), COM4993(""), COM4998(""), COM0004(""), COM4886("")
	, COM4991("")
	, COM4992("")
	, COM4987("PK LANG NUll")
	, COM4986("fack id")
	, COM4893("File Not Found");



	private CommonMessageCode(String dec){
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
