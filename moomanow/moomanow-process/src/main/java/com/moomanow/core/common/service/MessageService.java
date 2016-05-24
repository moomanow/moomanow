package com.moomanow.core.common.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.constant.MessageCode;

public interface MessageService {

	public void load();
	public void clearCache();
	public IMessage getMessage( MessageCode messageCode ,String[] para  );
	public IMessage getMessage( MessageCode messageCode, Locale locale ,String[] para);
	public IMessage getMessage( MessageCode messageCode, String lang ,String[] para);
	public IMessage getMessage( String messageCode , String[] para );
	public IMessage getMessage( String messageCode, Locale locale ,String[] para);
	public IMessage getMessage( String messageCode, String lang ,String[] para);
	

	public List<IMessage> getMessageList();
	public List<IMessage> getMessageList(String lang);
	public Map<String,IMessage> getMessageMap(String lang);
	public List<IMessage> getMessageList(String lang,String messageType);
}
