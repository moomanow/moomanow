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
import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.bean.MessageDefault;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public class MessageDaoImpl extends JdbcCommonDaoImpl implements MessageDao {
	
	private static final Logger logger = Logger.getLogger(MessageDaoImpl.class);
	public static final String SQL_QUERY_MESSAGE = 
			" SELECT MESSAGE_CODE, MESSAGE_LANG, DISPLAY_TEXT, MESSAGE_DESC, " +
			" MESSAGE_TYPE, SOLUTION " +
			" FROM SYS_M_MESSAGE WHERE STATUS = 'A' ";
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

	
	@Override
	@TriggersRemove(cacheName="getMessageMap", removeAll=true)
	public void clearMessageCache() throws RollBackException ,NonRollBackException{
		
	}


}
