package com.moomanow.core.common.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.bean.MessageDefault;
import com.moomanow.core.common.constant.CommonConstant;
import com.moomanow.core.common.constant.MessageCode;
import com.moomanow.core.common.context.CurrentThread;
import com.moomanow.core.common.dao.ConfigDao;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;
import com.moomanow.core.common.processhandler.ProcessContext;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

public class MessageServiceImpl implements MessageService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MessageServiceImpl.class);

    private ConcurrentMap<MessageFormatKey, MessageFormat> messageFormats = new ConcurrentHashMap<MessageFormatKey, MessageFormat>();
	private ConfigDao configDao;
	@Autowired
	@Required
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}
	


	@Override
	public void load() {
		try {
			configDao.getMessageMap();
		} catch (RollBackException | NonRollBackException e) {
			logger.error("load()", e);
		}
	}

	@Override
	public void clearCache() {
		messageFormats = new ConcurrentHashMap<MessageFormatKey, MessageFormat>(); 
		try {
			configDao.clearMessageCache();
		} catch (RollBackException | NonRollBackException e) {
			logger.error("clearCache()", e);
		}
	}



	@Override
	public List<IMessage> getMessageList() {
		return getMessageList( null, null );
	}


	@Override
	public List<IMessage> getMessageList(String lang) {
		return getMessageList(lang,null);
	}
	
	@Override
	public List<IMessage> getMessageList(String lang, String messageType) {
		try {
			return configDao.getMessageList(messageType, lang);
		} catch (RollBackException | NonRollBackException e) {
			logger.error("getMessageList(String, String)", e);
		}
		return new ArrayList<IMessage>();
	}
	
	
	@Override
	public IMessage getMessage(String messageCode, Locale locale,String[] para) {
		if(locale == null){locale = CommonConstant.DEFAULT_LOCALE;}
		return getMessage(messageCode, locale.getISO3Language().toUpperCase(),para);
	}

	@Override
	public IMessage getMessage(String messageCode, String lang,String[] para){
		if(lang == null){lang = CommonConstant.DEFAULT_LOCALE.getISO3Language().toUpperCase();}
		Map<String, IMessage> messageMap;
		try {
			messageMap = configDao.getMessageMap();
		} catch (RollBackException | NonRollBackException e) {
			messageMap = new  HashMap<String, IMessage>();
			logger.error("getMessage(String, String, String[])", e);
		}
		Object objt = org.apache.commons.lang.SerializationUtils.clone(messageMap.get(messageCode+"_"+lang));
		IMessage message = (IMessage) objt;
		if(para!=null&&para.length>0){
			MessageFormat mf = buildMessageFormat(message.getDisplayText(), CommonConstant.DEFAULT_LOCALE);
			String  out = formatWithNullDetection(mf, para);
			message.setDisplayText(out);
		}
		
		if(message == null){
			message = messageMap.get(messageCode+"_"+CommonConstant.DEFAULT_LOCALE.getISO3Language().toUpperCase());
			if(message == null){
				MessageDefault message2  = new MessageDefault();
				message2.setMessageCode(messageCode);
				return message2;
			}
		}
		return message;
	}
	@Override
	public IMessage getMessage(String messageCode,String[] para) {
		ProcessContext processContext = CurrentThread.getProcessContext();
		Locale locale = processContext.getLocale();
		if(locale==null){
			locale = CommonConstant.DEFAULT_LOCALE;
		}
		return getMessage(messageCode,locale,para);
	}

	

	@Override
	public IMessage getMessage(MessageCode messageCode,String[] para) {
		return getMessage(messageCode.getCode(),para);
	}

	@Override
	public IMessage getMessage(MessageCode messageCode, Locale locale,String[] para) {
		return getMessage(messageCode.getCode(),locale,para);
	}

	@Override
	public IMessage getMessage(MessageCode messageCode, String lang,String[] para) {
		return getMessage(messageCode.getCode(),lang,para);
	}

	
	public String getMessageListToJson(){
		
//		Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();
		return"";
	}



	@Override
	public Map<String, IMessage> getMessageMap(String lang) {
		List<IMessage> list;
		list = getMessageList(lang);
		HashMap<String, IMessage> map = new HashMap<String, IMessage>();
		if(list!=null){
			for (IMessage message : list) {
				map.put(message.getMessageCode(), message);
			}
		}
		
		return map;
	}
    private static String formatWithNullDetection(MessageFormat mf, Object[] args) {
    	LinkedList<Object> argList = new LinkedList<Object>(Arrays.asList(args));
        String message = mf.format(argList.toArray());
        if ("null".equals(message)) {
            return null;
        } else {
            return message;
        }
    }
	
    private MessageFormat buildMessageFormat(String pattern, Locale locale) {
        MessageFormatKey key = new MessageFormatKey(pattern, locale);
        MessageFormat format = messageFormats.get(key);
        if (format == null) {
            format = new MessageFormat(pattern);
            format.setLocale(locale);
            format.applyPattern(pattern);
            messageFormats.put(key, format);
        }

        return format;
    }
    
    static class MessageFormatKey {

        String pattern;
        Locale locale;

        MessageFormatKey(String pattern, Locale locale) {
            this.pattern = pattern;
            this.locale = locale;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MessageFormatKey)) return false;

            final MessageFormatKey messageFormatKey = (MessageFormatKey) o;

            if (locale != null ? !locale.equals(messageFormatKey.locale) : messageFormatKey.locale != null)
                return false;
            if (pattern != null ? !pattern.equals(messageFormatKey.pattern) : messageFormatKey.pattern != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            result = (pattern != null ? pattern.hashCode() : 0);
            result = 29 * result + (locale != null ? locale.hashCode() : 0);
            return result;
        }
    }

}
