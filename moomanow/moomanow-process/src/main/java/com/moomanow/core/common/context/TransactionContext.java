package com.moomanow.core.common.context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.TransactionStatus;

import com.moomanow.core.common.processhandler.Context;

public class TransactionContext extends Context{

	private TransactionStatus transactionStatus;
	@Override
	public void load(HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
}
