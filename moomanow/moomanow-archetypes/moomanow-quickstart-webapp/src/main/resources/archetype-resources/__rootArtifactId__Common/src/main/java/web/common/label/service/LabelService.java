package com.moomanow.web.common.label.service;

import java.util.Map;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;


public interface LabelService {

	public Map<String, String> getLabel(String string);

	public void refresh()throws RollBackException ,NonRollBackException;

	public Map<String, String> getLabelByPage(String lang, String page)throws RollBackException ,NonRollBackException;

}
