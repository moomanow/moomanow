package com.moomanow.core.common.dao;

import java.util.List;
import java.util.Map;

import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public interface MessageDao {
	public Map<String,IMessage> getMessageMap()throws RollBackException ,NonRollBackException;
	public List<IMessage> getMessageList( String messageType, String messageLang )throws RollBackException ,NonRollBackException;
	public void clearMessageCache()throws RollBackException ,NonRollBackException;
}
