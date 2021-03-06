package com.moomanow.core.common.service;

import com.moomanow.core.common.exception.NonRollBackException;
import com.moomanow.core.common.exception.RollBackException;

public interface CheckService {
	public boolean checkNeedleList(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkTableLang(String tableName)throws RollBackException, NonRollBackException;
	public boolean checkColumnNameInTableLang(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkClearableList(String tableName, String columnName)throws RollBackException, NonRollBackException;
	public boolean checkIncludeMinusOne(String name, String columnName)throws RollBackException, NonRollBackException;
	public String getPkTableLangByTableName(String tableName) throws RollBackException, NonRollBackException;
}
