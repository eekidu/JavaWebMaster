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


}
