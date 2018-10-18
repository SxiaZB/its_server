package com.its.utils.json;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Disc:将json串中的日期字符串转换为bean中的Date
 * Created by xinxinran on 14/11/25.
 */
public class DateMorpher  extends AbstractObjectMorpher {
    /*** 日期字符串格式 */
    private String[] formats;

    public DateMorpher(String[] formats) {
        this.formats = formats;
    }

    @Override
    public Object morph(Object value) {
        if (value == null) {
            return null;
        }
        if (Date.class.isAssignableFrom(value.getClass())) {
            return value;
        }
        if (!supports(value.getClass())) {
            throw new MorphException(value.getClass() + " 是不支持的类型");
        }
        String strValue = (String) value;
        SimpleDateFormat dateParser = null;
        for (int i = 0; i < formats.length; i++) {
            if (null == dateParser) {
                dateParser = new SimpleDateFormat(formats[i]);
            } else {
                dateParser.applyPattern(formats[i]);
            }
            try {
                return new Date(dateParser.parse(strValue.toLowerCase())
                        .getTime());
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Class morphsTo() {
        return Date.class;
    }

    @Override
    public boolean supports(Class clazz) {
        return String.class.isAssignableFrom(clazz);
    }

}
