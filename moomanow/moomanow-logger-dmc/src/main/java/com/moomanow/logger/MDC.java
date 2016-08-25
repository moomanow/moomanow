package com.moomanow.logger;

final class MDC {

	static final MDC MDC_PROVIDER = find();
	
	private static MDC find() {
		final ClassLoader cl = MDC.class.getClassLoader();
		try {
			Class.forName("org.apache.log4j.MDC", false, cl);
		} catch (ClassNotFoundException e) {
		}
		return null;
    }
	

}
