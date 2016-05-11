package com.moomanow.core.common.service;

import java.util.List;
import java.util.Map;

import com.moomanow.core.common.bean.FieldValidatorBean;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;


public interface ConfigService {
	
	public String get(String key);
	
	public String getByCountryId(String key, Long idCountry);

	public void refreshConfig()throws RollBackException ,NonRollBackException;
	
	public Map<String, List<FieldValidatorBean>> getFieldValidators(String page)throws RollBackException ,NonRollBackException;

	public void initConfig();

	public List<FieldValidatorBean> getPageValidators(String page);

	public String getInputResultName(String namespace, String name);
	
	public boolean checkNeedleList(String name) throws RollBackException, NonRollBackException;

	public boolean checkClearableList(String name) throws RollBackException, NonRollBackException;
	
	public String getByDate(String key);
}
