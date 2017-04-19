package com.moomanow.web.common.label.dao;

import java.util.List;
import java.util.Map;

import com.moomanow.web.common.label.bean.Label;


public interface LabelDao {

	public Map<String, Map<String, String>> getLabelStrMap();

	public Map<String, List<Label>> getLabelMap();

}
