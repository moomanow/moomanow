package com.moomanow.core.common.dao;

import java.util.List;
import java.util.Map;

import com.moomanow.core.common.bean.IMessage;

public interface MessageDao {
	public Map<String,IMessage> getMessageMap();
	public List<IMessage> getMessageList( String messageType, String messageLang );
	public void clearMessageCache();
}
