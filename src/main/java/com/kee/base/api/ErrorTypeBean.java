package com.kee.base.api;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by kee on 2017/6/15.
 */
public class ErrorTypeBean {

    private static Map<Integer, String> mErrorCollection;

    static {
        mErrorCollection = new HashMap();
        mErrorCollection.put(0, "Success");// TODO: 2017/6/15
        mErrorCollection.put(10001, "系统错误");
        mErrorCollection.put(10003, "远程服务错误");
        mErrorCollection.put(10400, "参数异常");
        mErrorCollection.put(20101, "用户不存在");
    }

    public static String getMessage(int code) {
        return mErrorCollection.get(code);
    }
}
