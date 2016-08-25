package com.moomanow.logger;

import java.security.AccessController;
import java.security.PrivilegedAction;

final class LoggerProviderFactory {

	static final String LOGGING_PROVIDER_KEY = "com.moomanow.logger.provider";
	static final LoggerProvider PROVIDER = find();
	
	private static LoggerProvider find() {
		final ClassLoader cl = LoggerProviderFactory.class.getClassLoader();
		
		
        try {
            // Check the system property
            final String loggerProvider = AccessController.doPrivileged(new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty(LOGGING_PROVIDER_KEY);
                }
            });
            if (loggerProvider != null) {
                if ("jboss".equalsIgnoreCase(loggerProvider)) {
                    return tryJBossLogManager(cl, "system property");
                } else if ("jdk".equalsIgnoreCase(loggerProvider)) {
                    return tryJDK("system property");
                } else if ("log4j2".equalsIgnoreCase(loggerProvider)) {
                    return tryLog4j2(cl, "system property");
                } else if ("log4j".equalsIgnoreCase(loggerProvider)) {
                    return tryLog4j(cl, "system property");
                } else if ("slf4j".equalsIgnoreCase(loggerProvider)) {
                    return trySlf4j("system property");
                }
            }
        } catch (Throwable t) {
            // nope...
        }
        
     // Next try for a service provider
        try {
            final ServiceLoader<LoggerProvider> loader = ServiceLoader.load(LoggerProvider.class, cl);
            final Iterator<LoggerProvider> iter = loader.iterator();
            for (; ; )
                try {
                    if (!iter.hasNext()) break;
                    LoggerProvider provider = iter.next();
                    // Attempt to get a logger, if it fails keep trying
                    logProvider(provider, "service loader");
                    return provider;
                } catch (ServiceConfigurationError ignore) {
                }
        } catch (Throwable ignore) {
            // TODO consider printing the stack trace as it should only happen once
        }

        // Finally search the class path
        try {
            return tryJBossLogManager(cl, null);
        } catch (Throwable t) {
            // nope...
        }
        try {
            // MUST try Log4j 2.x BEFORE Log4j 1.x because Log4j 2.x also passes Log4j 1.x test in some circumstances
            return tryLog4j2(cl, null);
        } catch (Throwable t) {
            // nope...
        }
        try {
            return tryLog4j(cl, null);
        } catch (Throwable t) {
            // nope...
        }
        try {
            // only use slf4j if Logback is in use
            Class.forName("ch.qos.logback.classic.Logger", false, cl);
            return trySlf4j(null);
        } catch (Throwable t) {
            // nope...
        }
        return tryJDK(null);

		
    }
	
    private static LoggerProvider tryJDK(final String via) {
        final JDKLoggerProvider provider = new JDKLoggerProvider();
        logProvider(provider, via);
        return provider;
    }

}
