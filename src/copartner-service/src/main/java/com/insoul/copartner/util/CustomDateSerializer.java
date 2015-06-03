package com.insoul.copartner.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.insoul.copartner.constant.CommonConstant;

public class CustomDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date dateValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonConstant.DATE_FORMAT_LONG, Locale.ENGLISH);
        String content = simpleDateFormat.format(dateValue);
        jsonGenerator.writeString(content);
    }
}
