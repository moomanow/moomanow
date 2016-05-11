package com.moomanow.core.common.bean;

import org.hibernate.bytecode.javassist.FieldHandler;

public abstract class NoProxyEntityBean implements EntityBean, org.hibernate.bytecode.javassist.FieldHandled  {
	private FieldHandler fieldHandler;

//	public abstract String getStatus();
//
//	public abstract void setStatus(String rowStatus);
//
//	public abstract Date getCreateDate();
//
//	public abstract void setCreateDate(Date timeCreate);
//
//	public abstract Date getUpdateDate();
//
//	public abstract void setUpdateDate(Date timeUpdate);
//
//	public abstract String getCreateUser();
//
//	public abstract void setCreateUser(String userCreate);
//
//	public abstract String getUpdateUser();
//
//	public abstract void setUpdateUser(String userUpdate);

	public FieldHandler getFieldHandler() {
		return fieldHandler;
	}

	public void setFieldHandler(FieldHandler fieldHandler) {
		this.fieldHandler = fieldHandler;
	}
	
	
	
//	public void setFieldHandler(FieldHandler handler){
//		
//	}
//	
//	public FieldHandler getFieldHandler(){
//		
//	}
}
