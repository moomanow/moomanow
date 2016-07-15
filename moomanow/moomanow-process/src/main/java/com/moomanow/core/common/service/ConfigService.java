package com.moomanow.core.common.service;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;



public interface ConfigService {
	
	public String get(String key);
	
	public void refreshConfig()throws RollBackException ,NonRollBackException;
	
	public void initConfig();
}
