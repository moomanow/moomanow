package com.moomanow.core.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.moomanow.core.common.bean.ActionBean;
import com.moomanow.core.common.bean.Config;
import com.moomanow.core.common.bean.ConfigByCountry;
import com.moomanow.core.common.bean.ConfigByDate;
import com.moomanow.core.common.bean.ConfigDefault;
import com.moomanow.core.common.bean.Message;
import com.moomanow.core.common.bean.MessageDefault;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public class ConfigDaoImpl extends CommonDaoImpl implements ConfigDao {
	
	private static final Logger logger = Logger.getLogger(ConfigDaoImpl.class);
	
	
	private static final String SQL_QUERY_CONFIG = "SELECT CONFIG_KEY, CONFIG_VALUE FROM SYS_M_CONFIG WHERE STATUS = 'A' ";
	@Override
	public Map<String, String> getConfigMap() throws RollBackException ,NonRollBackException {
		Map<String, String> configMap = new ConcurrentHashMap<String, String>();
		
		List<Config> configList = nativeQuery(SQL_QUERY_CONFIG, CONFIG_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		for (Config systemConfig : configList) {
			if (logger.isInfoEnabled()) {
				logger.info("Config loading... " + systemConfig);
			}			
			configMap.put(systemConfig.getKey(), systemConfig.getValue());
		}
		return configMap;
	}
	
	private static final ConfigMapper<Config> CONFIG_MAPPER = new ConfigMapper<Config>();
	public static final class ConfigMapper<T extends Config> implements RowMapper<Config> {

	    public Config mapRow(ResultSet rs, int num)throws SQLException {
	    	ConfigDefault configDefault = new ConfigDefault();
	    	configDefault.setKey( rs.getString("CONFIG_KEY"));
	    	configDefault.setValue( rs.getString("CONFIG_VALUE"));
	        return configDefault;
	    }
    }
	
	private static final String SQL_QUERY_CONFIG_BY_COUNTRY = "SELECT CONFIG_KEY, CONFIG_VALUE, CONFIG_CODE FROM SYS_M_CONFIG_BY_CODE WHERE STATUS = 'A' ";
	@Override
	public Map<Long,Map<String, String>> getConfigCountryMap() throws RollBackException ,NonRollBackException {
		Map<Long,Map<String, String>> configMap = new ConcurrentHashMap<Long,Map<String, String>>();
		Map<String, String> configByCountryMap = new ConcurrentHashMap<String, String>();
		
		List<ConfigByCountry> configList = nativeQuery(SQL_QUERY_CONFIG_BY_COUNTRY, CONFIG_BY_COUNTRY_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		for (ConfigByCountry systemConfig : configList) {
			if (logger.isInfoEnabled()) {
				logger.info("Config loading... " + systemConfig);
			}	
			configByCountryMap.put(systemConfig.getKey(), systemConfig.getValue());
			configMap.put(systemConfig.getIdCountry(), configByCountryMap);
		}
		return configMap;
	}
	
	private static final ConfigByCountryMapper<ConfigByCountry> CONFIG_BY_COUNTRY_MAPPER = new ConfigByCountryMapper<ConfigByCountry>();
	public static final class ConfigByCountryMapper<T extends ConfigByCountry> implements RowMapper<ConfigByCountry> {

	    public ConfigByCountry mapRow(ResultSet rs, int num)throws SQLException {
	    	ConfigByCountry configByCountry = new ConfigByCountry();
	    	configByCountry.setKey( rs.getString("CONFIG_KEY"));
	    	configByCountry.setValue( rs.getString("CONFIG_VALUE"));
	    	configByCountry.setIdCountry(Long.parseLong(rs.getString("CONFIG_CODE")));
	        return configByCountry;
	    }
    }

	@Override
	public void clearConfigCache() throws RollBackException ,NonRollBackException{
		
	}
}
