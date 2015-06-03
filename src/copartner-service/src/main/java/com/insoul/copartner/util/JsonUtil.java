package com.insoul.copartner.util;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JsonUtil {
    private static final Logger logger = LogManager.getLogger(JsonUtil.class);

    private static ObjectMapper jsonObjectMapper;
    static {
        jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        jsonObjectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String json, Class<T> valueType) {
        Object obj = null;
        try {
            obj = jsonObjectMapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            logger.error(e);
        } catch (JsonMappingException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }

        return (T) obj;
    }

    public static String serialize(Object obj) {
        String json = "";
        try {
            json = jsonObjectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error(e);
        }

        return json;
    }
}
