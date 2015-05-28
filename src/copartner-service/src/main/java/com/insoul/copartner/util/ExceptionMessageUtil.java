package com.insoul.copartner.util;

import java.util.Properties;

public final class ExceptionMessageUtil {
    private static Properties exceptionMessageProperties;

    static {
        exceptionMessageProperties = PropertiesUtil.getProperties("/exception_message.properties");
    }

    private ExceptionMessageUtil() {
    }

    public static String getExceptionMessage(final int code) {
        return PropertiesUtil.get(exceptionMessageProperties, code + "");
    }
}
