package com.moomanow.core.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.bean.MessageDefault;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public class ConfigDaoImpl extends JdbcCommonDaoImpl implements ConfigDao {
	
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

	public static final String SQL_QUERY_MESSAGE = 
			" SELECT MESSAGE_CODE, MESSAGE_LANG, DISPLAY_TEXT, MESSAGE_DESC, " +
			" MESSAGE_TYPE, SOLUTION " +
			" FROM SYS_M_MESSAGE WHERE STATUS = 'A' ";
	@Override
	@Cacheable(cacheName = "getMessageMap")
	public Map<String, IMessage> getMessageMap() {
		Map<String, IMessage> messageMap = new ConcurrentHashMap<String, IMessage>();
		
		List<IMessage> messageList;
		try {
			messageList = nativeQuery(SQL_QUERY_MESSAGE, MESSAGE_MAPPER);
		} catch (RollBackException | NonRollBackException e) {
			messageList = new ArrayList<IMessage>();
			logger.error("getMessageMap()", e);
		}//(SQL_QUERY_CONFIG, new configMapper());
		
		for (IMessage message : messageList) {
			if (logger.isInfoEnabled()) {
				logger.info("Message loading... " + message);
			}			
			messageMap.put(message.getMessageCode()+"_"+message.getMessageLang(), message);
		}
		return messageMap;
	}
	
	private static final MessageMapper<IMessage> MESSAGE_MAPPER = new MessageMapper<IMessage>();
	public static final class MessageMapper<T extends IMessage> implements RowMapper<IMessage> {

	    public IMessage mapRow(ResultSet rs, int num)throws SQLException {
	    	MessageDefault message = new MessageDefault(); 
	    	message.setMessageCode(rs.getString("MESSAGE_CODE"));
	    	message.setMessageLang(rs.getString("MESSAGE_LANG"));
	    	message.setDisplayText(rs.getString("DISPLAY_TEXT"));
	    	message.setMessageDesc(rs.getString("MESSAGE_DESC"));
	    	message.setMessageType(rs.getString("MESSAGE_TYPE"));
	    	message.setSolution(rs.getString("SOLUTION"));
	        return message;
	    }
    }
	
	@Override
	public List<IMessage> getMessageList(String messageType, String messageLang)throws RollBackException ,NonRollBackException {
		
		StringBuilder whereClause = new StringBuilder();
		Map<String,Object> params = new ConcurrentHashMap<String, Object>();
		
		if( messageType != null && messageType.length() > 0 ){
			whereClause.append(" AND MESSAGE_TYPE LIKE :messageType ");
			params.put("messageType", "%"+messageType+"%");
		}
		if( messageLang != null && messageLang.length() > 0 ){
			whereClause.append(" AND MESSAGE_LANG = :messageLang ");
			params.put("messageLang", messageLang);
		}
		return nativeQuery(SQL_QUERY_MESSAGE+whereClause.toString(), MESSAGE_MAPPER, params);
	}

	
	@Override
	@TriggersRemove(cacheName="getActionByActionId", removeAll=true)
	public void clearConfigCache() throws RollBackException ,NonRollBackException{
		
	}

	@Override
	@TriggersRemove(cacheName="getMessageMap", removeAll=true)
	public void clearMessageCache() throws RollBackException ,NonRollBackException{
		
	}
	
	public static final String SQL_QUERY_PAGE_FIELD_VALIDATORS = 
			" SELECT * " +
			" FROM SYS_M_FIELD_VALIDATOR  WHERE STATUS = 'A' ";
	
	
	@Override
	public Map<String, String> getActionInputResult() throws RollBackException ,NonRollBackException{
		Map<String, String> actionInputResult = new ConcurrentHashMap<String, String>();
		List<ActionBean> actionBeans = nativeQuery(SQL_QUERY_ACTION_INPUT_RESULT,ACTION_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		
		for (ActionBean actionBean : actionBeans) {
			if(actionBean.getInputResultName()!=null&&(actionBean.getActionName()!=null||actionBean.getNameSpace()!=null))
			actionInputResult.put(actionBean.getNameSpace()+"/"+actionBean.getActionName(), actionBean.getInputResultName());
		}
		return actionInputResult;
	}
	public static final String SQL_QUERY_ACTION_INPUT_RESULT = "SELECT * FROM SYS_M_ACTION  WHERE STATUS = 'A' ";
	
	private static final ActionMapper<ActionBean> ACTION_MAPPER = new ActionMapper<ActionBean>();
	public static final class ActionMapper<T extends ActionBean> implements RowMapper<ActionBean> {

	    public ActionBean mapRow(ResultSet rs, int num)throws SQLException {
	    	ActionBean displayFiled = new ActionBean();
	    	displayFiled.setActionName(rs.getString("ACTION_NAME"));
	    	displayFiled.setNameSpace(rs.getString("NAME_SPACE"));
	    	displayFiled.setInputResultName(rs.getString("INPUT_RESULT_NAME"));
//	    	displayFiled.setParameter(rs.getString("PARAMETER"));
//	    	displayFiled.setMessage(rs.getString("MESSAGE"));
//	    	displayFiled.setMessageParameter(rs.getString("MESSAGE_PARAMETER"));
	        return displayFiled;
	    }
    }
	
	private static final String SQL_QUERY_CONFIG_BY_DATE = "SELECT ID_REF_DATA, CONFIG_KEY, CONFIG_VALUE FROM SYS_M_CONFIG_BY_DATE WHERE STATUS = 'A' ";
	
	@Override
	public Map<String, String> getConfigDateMap() throws RollBackException, NonRollBackException {
		Map<String, String> configMap = new ConcurrentHashMap<String, String>();
		List<ConfigByDate> configList = nativeQuery(SQL_QUERY_CONFIG_BY_DATE, CONFIG_BY_DATE_MAPPER);//(SQL_QUERY_CONFIG, new configMapper());
		for (ConfigByDate systemConfig : configList) {
			if (logger.isInfoEnabled()) {
				logger.info("Config loading... " + systemConfig);
			}	
			configMap.put(systemConfig.getKey(), systemConfig.getValue());
		}
		return configMap;
	}
	
	private static final ConfigByDateMapper<ConfigByDate> CONFIG_BY_DATE_MAPPER = new ConfigByDateMapper<ConfigByDate>();
	public static final class ConfigByDateMapper<T extends ConfigByDate> implements RowMapper<ConfigByDate> {

	    public ConfigByDate mapRow(ResultSet rs, int num)throws SQLException {
	    	ConfigByDate configByDate = new ConfigByDate();
	    	configByDate.setKey( rs.getString("ID_REF_DATA")+ "_"+rs.getString("CONFIG_KEY"));
	    	configByDate.setValue( rs.getString("CONFIG_VALUE"));
	    	configByDate.setIdRefData(Long.parseLong(rs.getString("ID_REF_DATA")));
	    	
	        return configByDate;
	    }
    } 
}
