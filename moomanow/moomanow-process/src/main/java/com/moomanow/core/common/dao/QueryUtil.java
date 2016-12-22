package com.moomanow.core.common.dao;


public class QueryUtil {

	public static String getCurrentDateTimeCommand( String dbVendor ){
		String command = null;
		if(dbVendor.equals("ORACLE")) {
			command = "SYSDATE";
		}
		else if( dbVendor.equals("SQLSERVER") ){
			command = "GETDATE()";		
		}
		else if( dbVendor.equals("MYSQL") ){
			command = "NOW()";
		}
		else{
			throw new RuntimeException("Unknown Database vendor : " + dbVendor );
		}
		return command;
	}
}
