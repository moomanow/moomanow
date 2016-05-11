/**
 * 
 */
package com.moomanow.core.common.constant;

import java.util.Locale;

/**
 * @author Jaurpong.w(Kwan)
 *
 */
public interface CommonConstant {

	public static final String PROCESS_STATUS_SUCCESS = "S";
    public static final String PROCESS_STATUS_FAIL = "F";
    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    
    public static interface SESSION {
    	
    	public static final String USER_ID = "SESSION_USER_ID";
    	public static final String USER_NAME = "SESSION_USER_NAME";
		public static final String USER_BEAN_KEY = "SESSION_USER_BEAN_KEY";
    }
    
    public static interface LOG {
		public static final String SESSION_ID = "SESSION_ID";
		public static final String CONTEXT_PATH = "CONTEXT_PATH";
		public static final String USER_ID = "USER_ID";
		public static final String USER_NAME = "USER_NAME";
		public static final String SERVER_NAME = "SERVER_NAME";
		public static final String SERVER_PORT = "SERVER_PORT";
		public static final String SERVER_INSTANCE_IP = "SERVER_INSTANCE_IP";
		public static final String SERVER_INSTANCE_NAME = "SERVER_INSTANCE_NAME";
		public static final String SERVER_INSTANCE_SERVER_NAME = "SERVER_INSTANCE_SERVER_NAME";
		
    }

}
