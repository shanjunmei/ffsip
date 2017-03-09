//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ffzx.ffsip.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class JsonConverter extends ObjectMapper {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(JsonConverter.class);
    private static JsonConverter mapper = new JsonConverter();

    private JsonConverter() {
        this(Include.NON_EMPTY);
    }

    private JsonConverter(Include include) {
        if (include != null) {
            this.setSerializationInclusion(include);
        }

       /* DeserializationConfig cfg =  this.getDeserializationConfig();*/
        //设置JSON时间格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       /* cfg.with(dateFormat);*/
        this.setDateFormat(dateFormat);
        this.enableSimple();
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer() {
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
                jgen.writeString("");
            }
        });

        this.setTimeZone(TimeZone.getDefault());
    }

    public static JavaType createCollectionType(Class<?> collectionClass, Class... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    public static <T> T convert(Object src, JavaType type) {
        return mapper.convertValue(src, type);
    }

    public static <T> T convert(Object src, Class<T> type) {
        return mapper.convertValue(src, type);
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T from(String json,Class<T> cls){
        try {
            return mapper.readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException("json convert fail ",e);
        }
    }

    public JsonConverter enableSimple() {
        this.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return this;
    }
}
