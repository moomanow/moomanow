package com.moomanow.core.common.constant;

public enum Status {
	ACTIVE("A")
	,DRAFT("D")
	,WAIT("W")
//	,DELECT()
	,INACTIVE("I")
//	,DRAFT()
	;
	private String name;
	private Status(String name) {
		this.name = name;
	}
	
	public String getShort(){
		return name;
	}
	

}
