package com.moomanow.core.common.dao;

import java.util.List;
import java.util.Map;

import com.moomanow.core.common.bean.FieldValidatorBean;
import com.moomanow.core.common.bean.Label;
import com.moomanow.core.common.bean.Message;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public interface ConfigDao {
	public Map<String,String> getConfigMap()throws RollBackException ,NonRollBackException;
	public Map<Long,Map<String, String>> getConfigCountryMap()throws RollBackException ,NonRollBackException;
	public Map<String,Message> getMessageMap()throws RollBackException ,NonRollBackException;
	public List<Message> getMessageList( String messageType, String messageLang )throws RollBackException ,NonRollBackException;
	public List<Label> getLabelList()throws RollBackException ,NonRollBackException;
	public Map<String,List<Label>> getLabelMap()throws RollBackException ,NonRollBackException;
	public Map<String, Map<String, String>> getLabelStrMap()throws RollBackException ,NonRollBackException;
	public void clearConfigCache()throws RollBackException ,NonRollBackException;
	public void clearMessageCache()throws RollBackException ,NonRollBackException;
	public Map<String, Map<String, List<FieldValidatorBean>>> getPageFieldValidators()throws RollBackException ,NonRollBackException;
	public Map<String, List<FieldValidatorBean>> getPageValidators()throws RollBackException ,NonRollBackException;
	public Map<String, String> getActionInputResult()throws RollBackException ,NonRollBackException;
	public Map<String, String> getConfigDateMap()throws RollBackException ,NonRollBackException;
}
