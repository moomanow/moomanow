
package com.moomanow.logger;

import java.util.Map;

public final class MDC {

    private MDC() {
    }

    public static Object put(String key, Object val) {
        return LoggerProviderFactory.PROVIDER.putMdc(key, val);
    }

    public static Object get(String key) {
        return LoggerProviderFactory.PROVIDER.getMdc(key);
    }

    public static void remove(String key) {
    	LoggerProviderFactory.PROVIDER.removeMdc(key);
    }

    public static Map<String, Object> getMap() {
        return LoggerProviderFactory.PROVIDER.getMdcMap();
    }
}
