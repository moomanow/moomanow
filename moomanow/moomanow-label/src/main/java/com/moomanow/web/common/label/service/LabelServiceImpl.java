package com.moomanow.web.common.label.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.googlecode.ehcache.annotations.Cacheable;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.web.common.label.dao.LabelDao;

public class LabelServiceImpl implements LabelService  {

	private LabelDao labelDao;
	
	@Autowired
	@Required
	public void setLabelDao(LabelDao labelDao) {
		this.labelDao = labelDao;
	}
//	public void setConfigDao(ConfigDao configDao) {
//		this.configDao = configDao;
//	}
	
	
	private Map<String, Map<String, String>> labelMap = new HashMap<String, Map<String,String>>();
	@Override
	@Cacheable(cacheName = "labelService.getLabel")
	public Map<String, String> getLabel(String lang) {
		return labelMap.get(lang);
	}
	
	@Override
	@Cacheable(cacheName = "labelService.getLabelByPage")
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
	@PostConstruct
	public void refresh(){
		try {
			labelMap = labelDao.getLabelStrMap();
		} catch (RollBackException | NonRollBackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
