package com.insoul.copartner.exception;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.insoul.copartner.constant.ResponseCode;

public class CExceptionFactory {

    public static final Logger LOGGER = Logger.getLogger(CExceptionFactory.class);

    private CExceptionFactory() {
    }

    public static <T extends CException> T getException(Class<T> exceptionClass, ResponseCode code, String... messages) {
        T t = null;
        if (null != messages) {
            List<String> messageList = Arrays.asList(messages);
            t = getException(exceptionClass, code, messageList);

        }
        return t;
    }

    public static <T extends CException> T getException(Class<T> exceptionClass, ResponseCode code,
            List<String> messages) {
        T t = null;
        try {
            t = exceptionClass.newInstance();
            for (String message : messages) {
                t.addErrorMessage(message);
            }
            t.setCode(code);
        } catch (InstantiationException e) {
            LOGGER.debug(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOGGER.debug(e.getMessage(), e);
        }
        return t;
    }
}
