package com.moomanow.core.common.context;

import javax.servlet.http.HttpServletRequest;

import com.moomanow.core.common.processhandler.Context;

public class FlowContext extends Context {

	private boolean startProcess;
	private String status;
	@Override
	public void load(HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		
	}

	public boolean getStartProcess() {
		return startProcess;
	}
	
	public void setStartProcess(boolean startProcess) {
		this.startProcess = startProcess;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
}
