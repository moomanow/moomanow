package com.moomanow.web.common.label.dao;

import java.util.List;
import java.util.Map;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.web.common.label.bean.Label;


public interface LabelDao {

	public Map<String, Map<String, String>> getLabelStrMap()throws RollBackException ,NonRollBackException;

	public Map<String, List<Label>> getLabelMap() throws RollBackException, NonRollBackException;

}
