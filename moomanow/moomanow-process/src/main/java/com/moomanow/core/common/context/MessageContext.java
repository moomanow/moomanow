package com.moomanow.core.common.context;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.moomanow.core.common.bean.IMessage;
import com.moomanow.core.common.processhandler.Context;

public class MessageContext extends Context {

	private List<IMessage> messageList = new LinkedList<>();
	@Override
	public void load(HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		
	}

	public List<IMessage> messageList() {
		return messageList;
	}

}
