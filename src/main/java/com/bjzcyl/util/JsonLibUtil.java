package com.bjzcyl.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by admin on 2016/7/6.
 */
public class JsonLibUtil {
    public static JsonConfig dateConfig(String pattern) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class,new JsonDateProcessor(pattern));
        return config;
    }

    private static class JsonDateProcessor implements JsonValueProcessor {

        private String format ="yyyy-MM-dd";

        public JsonDateProcessor() {
            super();
        }

        public JsonDateProcessor(String format) {
            super();
            this.format = format;
        }

        @Override
        public Object processArrayValue(Object paramObject,
                                        JsonConfig paramJsonConfig) {
            return process(paramObject);
        }

        @Override
        public Object processObjectValue(String paramString, Object paramObject,
                                         JsonConfig paramJsonConfig) {
            return process(paramObject);
        }
        private Object process(Object value){
            if(value instanceof Date){
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
                return sdf.format(value);
            }
            return value == null ? "" : value.toString();
        }


    }

}
