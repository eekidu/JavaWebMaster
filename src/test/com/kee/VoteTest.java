package com.kee;

import com.kee.utill.IPUtil;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by kee on 2017/8/4.
 */
public class VoteTest extends BaseTest {
    @Test
    public void normalTouPiao() {
        for (int j = 0; j < 200; j++) {
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("uid", "196");
            String ip;
            Request request = new Request.Builder().url(" http://www.1jiyi.com/addtongji.php")

                    .addHeader("X-Forwarded-For", ip = IPUtil.getRandomIp())
                    .post(builder.build()).
                            build();
            System.out.println(ip);
            for (int i = 0; i < 5; i++) {
                Call call = okHttpClient.newCall(request);
                try {
                    Response execute = call.execute();
                    String string = execute.body().string();
                    System.out.println(string);
                    Thread.sleep(new Random().nextInt(2000));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void BaYue() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                //设置写超时
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                //设置读超时
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS).build();

        for (int j = 0; j < 300; j++) {
            String ip;
            int position;
            Request request = new Request.Builder().url("http://jiaoyu.schoolma.net/plugin.php?id=tom_weixin_zl:ajax&act=zhuli&act_id=1&zlkey=92&openid=&subscribe=1&formkey=7e25ea2d858e7860848332238561d669&formhash=280b0f83")
                    .addHeader("X-Forwarded-For", ip = IPUtil.getHanDanRandomIp())
                    .addHeader("Cookie", "XCxw_2132_saltkey=fIV0v0G7; XCxw_2132_lastvisit=1509661111; PHPSESSID=" + createSeeid(24) + "; XCxw_2132_sid=EHN11b; XCxw_2132_lastact=1501639399%09plugin.php%09")
                    .get().build();
            for (int i = 0; i < 1; i++) {
                Call call = okHttpClient.newCall(request);
                try {
                    Response execute = call.execute();
                    String string = execute.body().string();
                    System.out.println("返回结果：" + string);
                    Thread.sleep(2 * 60 * 1000 + new Random().nextInt(1000 * 60 * 15));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static String createSeeid(int length) {
        String substring = UUID.randomUUID().toString().replace("-", "").substring(0, length);
        return substring;
    }


    @Test
    public void testHash() {
        String temp = "d4ajnusjqa8iprhtoj66a24fc5";
        String s = Integer.toHexString(temp.hashCode());
        System.out.println(s);

    }

    @Test
    public void NewBaYue() {
//        String firstUrl = "http://jiaoyu.schoolma.net/plugin.php?id=tom_weixin_zl&act_id=1&zlkey=92";
        String firstUrl = "http://jiaoyu.schoolma.net/plugin.php?id=tom_weixin_zl&act_id=1&zlkey=246";
        String sessionid;
        String url2;

        for (int i = 0; i < 300; i++) {
            String baseCookie = "XCxw_2132_saltkey=" + createSeeid(8) + "; XCxw_2132_lastvisit=" + (new Date().getTime() / 1000 - new Random().nextInt(1000 * 60 * 60 * 24 * 3)) + "; XCxw_2132_sid=" + createSeeid(6) + "; XCxw_2132_lastact=" + new Date().getTime() / 1000 + "%09plugin.php%09; ";
            String ipfinal = IPUtil.getHanDanRandomIp();
            String weixinClientName = IPUtil.weixinClient[new Random().nextInt(IPUtil.weixinClient.length)];

            Request request = new Request.Builder().url(firstUrl)
                    .addHeader("X-Forwarded-For", ipfinal)
                    .addHeader("User-Agent", weixinClientName)
                    .addHeader("Cookie", baseCookie)
                    .get().build();
            Call call = okHttpClient.newCall(request);
            try {
                Response execute = call.execute();
                sessionid = execute.header("Set-Cookie");
                sessionid = sessionid.substring(0, sessionid.indexOf(";"));
                System.out.println("本次session = " + sessionid);
                String string = execute.body().string();
                String source = string.substring(string.indexOf("type: \"GET\","), string.indexOf("dataType : \"json\","));
                url2 = source.substring(source.indexOf("http"), source.lastIndexOf("\""));
                System.out.println("投票地址　＝　" + url2);

                String newCookie = baseCookie + sessionid;
                System.out.println(newCookie);

                Request request2 = new Request.Builder().url(url2)
                        .addHeader("X-Forwarded-For", ipfinal)
//                        .addHeader("X-Real-IP", ipfinal)
//                        .addHeader("WL-Proxy-Client-IP", ipfinal)
//                        .addHeader("Client_Ip", ipfinal)
                        .addHeader("Referer", firstUrl)
                        .addHeader("Cookie", newCookie)
                        .addHeader("User-Agent", weixinClientName)
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                        .get().build();

                Call call2 = okHttpClient.newCall(request2);
                Response execute2 = call2.execute();
                String string2 = execute2.body().string();
                System.out.println("ip = " + ipfinal + "\n返回结果：" + string2);
//
//                Request request6 = new Request.Builder().url("http:192.168.8.199:8080/api/cookie")
//                        .addHeader("X-Forwarded-For", ipfinal)
//                        .addHeader("Referer", firstUrl)
//                        .addHeader("Cookie", newCookie)
//                        .addHeader("User-Agent", weixinClientName)
//                        .addHeader("X-Requested-With", "XMLHttpRequest")
//                        .get().build();
//
//                okHttpClient.newCall(request6).execute().body().string();
                Thread.sleep(60 * 1000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
