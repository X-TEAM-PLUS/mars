package org.xteam.plus.mars.common;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-01-13
 * Time: 20:53
 * 功能: 日期格式转换工具类
 */
public class DateTimeConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }
        String format = "yyyy-MM-dd HH:mm:ss";
        if (source.length() == 10) {
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}