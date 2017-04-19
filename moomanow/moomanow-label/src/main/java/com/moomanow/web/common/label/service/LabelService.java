package com.moomanow.web.common.label.service;

import java.util.Map;


public interface LabelService {

	public Map<String, String> getLabel(String string);

	public void refresh();

	public Map<String, String> getLabelByPage(String lang, String page);

}
