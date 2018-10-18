package com.its.utils.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 禁止json转换时，时间转化为对象
 * @author xinxinran
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
    private String datePattern = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.UK);

    
    public JsonDateValueProcessor() {
        super();
    }
    public JsonDateValueProcessor(String format) {
        super();
        this.datePattern = format;
        sdf = new SimpleDateFormat(datePattern);
    }
    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }
    @Override
    public Object processObjectValue(String key, Object value,
                                     JsonConfig jsonConfig) {
        return process(value);
    }
    private Object process(Object value) {
        try {
            if (value instanceof Timestamp) {
                return sdf.format((Timestamp) value);
            }else if (value instanceof Date){
                return sdf.format((Date) value);
            }
            return value == null ? "" : value.toString();
        } catch (Exception e) {
            return "";
        }
    }
    public String getDatePattern() {
        return datePattern;
    }
    public void setDatePattern(String pDatePattern) {
        datePattern = pDatePattern;
    }

}

