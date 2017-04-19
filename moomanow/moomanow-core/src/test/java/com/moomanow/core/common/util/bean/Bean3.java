package com.moomanow.core.common.util.bean;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tableNameBean2")
public class Bean3 {
	
	private String id;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

}
