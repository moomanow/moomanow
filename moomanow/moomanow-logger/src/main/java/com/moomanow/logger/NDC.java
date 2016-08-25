/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2010 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moomanow.logger;

public final class NDC {

    private NDC() {
    }

    public static void clear() {
    	LoggerProviderFactory.PROVIDER.clearNdc();
    }

    public static String get() {
        return LoggerProviderFactory.PROVIDER.getNdc();
    }

    public static int getDepth() {
        return LoggerProviderFactory.PROVIDER.getNdcDepth();
    }

    public static String pop() {
        return LoggerProviderFactory.PROVIDER.popNdc();
    }

    public static String peek() {
        return LoggerProviderFactory.PROVIDER.peekNdc();
    }

    public static void push(String message) {
    	LoggerProviderFactory.PROVIDER.pushNdc(message);
    }

    public static void setMaxDepth(int maxDepth) {
    	LoggerProviderFactory.PROVIDER.setNdcMaxDepth(maxDepth);
    }
}
