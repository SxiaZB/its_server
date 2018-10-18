package com.its.utils.json;

import net.sf.json.JSONNull;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.JSONUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Disc:
 * Created by xinxinran on 14/11/25.
 */
public class JsonValueProcUtil {
    private static Map<String, JsonConfig> processorMap = new HashMap<>();

    private static DefaultValueProcessor defaultValue2Null = new DefaultValueProcessor() {
        @Override
        public Object getDefaultValue(Class aClass) {
            return JSONNull.getInstance();
        }
    };

    public static final String BOOLEAN_PROCESSOR = "boolean_";

    public static final String DATE_PROCESSOR = "date_";

    public static final String DATETIME_PROCESSOR = "datetime_";

    public static JsonConfig getConfig(boolean forceDefault2Null){
        return getConfig(forceDefault2Null, BOOLEAN_PROCESSOR, DATE_PROCESSOR);
    }

    static{
        JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"}));
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"}));
        JSONUtils.getMorpherRegistry().registerMorpher(new BooleanMorpher(Boolean.FALSE), true);
    }

    public static void forceSimpleValue2Null(JsonConfig jsonConfig){
        Class[] classes = new Class[]{Long.class, Integer.class, String.class, Short.class, Byte.class, Character.class, Float.class, Double.class, Boolean.class};
        for(Class clazz : classes){
            jsonConfig.registerDefaultValueProcessor(clazz, defaultValue2Null);
        }
    }

    public static JsonConfig getConfig(boolean forceDefault2Null, String ...values){
        if(values.length == 0){
            return getConfig(forceDefault2Null);
        }else {
            String key = getConfigKey(forceDefault2Null, values);
            if (!processorMap.containsKey(key)) {
                JsonConfig config = new JsonConfig();
                if(forceDefault2Null){
                    forceSimpleValue2Null(config);
                }
                registConfig(config, values);
                processorMap.put(key, config);
            }
            return processorMap.get(key);
        }
    }

    private static String[] getSortedConfigKeys(String[] values){
        String[] str = new String[2];
        for(String v : values){
            switch (v){
                case BOOLEAN_PROCESSOR : str[0] = BOOLEAN_PROCESSOR; break;
                case DATE_PROCESSOR :
                    if(str[1] == null) {
                        str[1] = DATE_PROCESSOR;
                    }
                    break;
                case DATETIME_PROCESSOR :
                    str[1] = DATETIME_PROCESSOR;
                default: break;
            }
        }
        return str;
    }

    private static String getConfigKey(boolean forceDefault2Null, String[] values){
        StringBuilder sb = new StringBuilder(forceDefault2Null ? "t" : "f");
        String[] str = getSortedConfigKeys(values);
        for(String v : str){
            if(v != null && v.length() > 0){
                sb.append(v);
            }
        }
        return sb.toString();
    }

    private static void registConfig(JsonConfig config, String[] values){
        String[] str = getSortedConfigKeys(values);
        for(String v : str){
            switch (v){
                case BOOLEAN_PROCESSOR :

                    break;
                case DATE_PROCESSOR :
                    config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
                    config.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd"));
                    break;
                case DATETIME_PROCESSOR :
                    config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
                    config.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
                    break;
                default: break;
            }
        }
    }

}
