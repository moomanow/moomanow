package com.moomanow.core.common.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.moomanow.core.common.dao.ConfigDao;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public class ConfigServiceImpl implements ConfigService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConfigServiceImpl.class);

	private ConfigDao configDao;
	@Autowired
	@Required
	public void setConfigDao(ConfigDao configDao)throws RollBackException ,NonRollBackException {
		this.configDao = configDao;
	}
	
	private static Map<String, String> config;
	
	@Override
	@PostConstruct
	public synchronized void initConfig(){
		try {
			config = configDao.getConfigMap();
		} catch (RollBackException | NonRollBackException e) {
			logger.error("initConfig()", e);
		}
	}
	
	@Override
	public void refreshConfig()throws RollBackException ,NonRollBackException{
		config = configDao.getConfigMap();
	}
	
	@Override
	public String get(String key) {
		try{
			String returnString = config.get(key);
			return returnString;
		}catch(Exception e){
			logger.error("get(String)", e);
		}
		return null;
	}


}
