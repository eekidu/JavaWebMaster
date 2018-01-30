/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kee.base;

import com.google.gson.GsonBuilder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 1.传入的String类型的日期转换为Date类型
 * 2.返回的Date类型转换为String
 *
 */
public class MyGsonConverter extends GsonHttpMessageConverter implements Converter<String, Date> {
    public MyGsonConverter() {
        //更换Gson转换器
        super.setGson(new GsonBuilder().serializeNulls()//null值属性也格式化
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create());
    }



    /**
     * 对传入时间进行转换为Date对象
     * @param stringDate
     * @return
     */
    @Override
    public Date convert(String stringDate) {
        if (stringDate!=null&&stringDate.length()>0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return simpleDateFormat.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
