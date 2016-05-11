package com.moomanow.core.common.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.moomanow.core.common.dao.ConfigDao;

public class LabelServiceImpl implements LabelService  {

	private ConfigDao configDao;
	
	@Autowired
	@Required
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}
	
	
	private Map<String, Map<String, String>> labelMap = new HashMap<String, Map<String,String>>();
	@Override
	public Map<String, String> getLabel(String lang) {
		return labelMap.get(lang);
	}
	
	@Override
	public Map<String, String> getLabelByPage(String lang, String page) {
		Map<String, String> labels = new HashMap<String, String>();
		Map<String, String> mapLang = labelMap.get(lang);
		if(mapLang == null)
			mapLang = labelMap.get("ENG");
		for (Entry<String, String> map : mapLang.entrySet()) {
			if(map.getKey().startsWith(page)){
				labels.put(map.getKey(), map.getValue());
			}
		}
		return labels;
	}
	
	@Override
	public void refresh()throws RollBackException ,NonRollBackException {
		labelMap = configDao.getLabelStrMap();
	}

}
