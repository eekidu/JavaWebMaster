package com.kee;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.kee.utill.IPUtil;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by kee on 2017/8/4.
 */
public class VoteTest extends BaseTest {

    @Test
    public void getCookie() throws IOException {
//        String targetURL = "http://2018.weireju.com/app/index.php?c=entry&do=show&m=gtf_xiaof_toupiao&i=6&sid=21&id=318&wxref=mp.weixin.qq.com&from=timeline";
        String targetURL = "http://2018.weireju.com/app/index.php?i=6&c=utility&a=visit&do=showjs&m=gtf_xiaof_toupiao";
        Request request = new Request.Builder().url(targetURL)
                .get()
                .addHeader("User-Agent", IPUtil.getWeixinClient())
                .addHeader("User-Agent", ": Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400")
                .addHeader("X-Forwarded-For", IPUtil.getHanDanRandomIp()).build();


        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        String cookies = execute.header("Cookies");
        System.out.println("Cookies = " + cookies);
        Headers headers = execute.headers();
        for (String s : headers.names()) {
            System.out.println(s);
        }
        String string = execute.body().string();
        System.out.println("返回是：" + string);
    }


    /**
     * 107:您今天已经给编号61投过票了，明天再来吧
     *
     * @throws IOException
     */
    @Test
    public void toupiaoOne() throws IOException {
//        http://2018.weireju.com/app/index.php?c=entry&do=vote&m=gtf_xiaof_toupiao&i=6&type=good&id=213
        String targetURL = "http://2018.weireju.com/app/index.php?c=entry&do=vote&m=gtf_xiaof_toupiao&i=6&type=good&id=318";
        // 微信web
//        String session = "8a839a041da2727a395e4f7b01893e72";
//        String yunsuo = "c985d2a30fd43cefaeb4fb1f07e5b29e";

// 手机微信
//        String session = "98348b822020b08f7db107431ca70462";
//        String yunsuo = "02a83fcc095cf317b72c7f7fcc250a1e";

        String session = "98348b822020b08f7db107431ca70462";
        String yunsuo = "55fb9d05938a2953600ef7bdae012e21";

//         String session = "b3531a192ab1733cc2593af308327e90";
//        String yunsuo = "4024d0979f5f444b73e1794106fe5e62";
        Request request = new Request.Builder().url(targetURL)
                .get()
                .addHeader("User-Agent", IPUtil.getWeixinClient())
                .addHeader("Cookie", "PHPSESSID=" + session + "; yunsuo_session_verify=" + yunsuo)
                .addHeader("X-Forwarded-For", IPUtil.getHanDanRandomIp()).build();

        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        String string = execute.body().string();
        System.out.println("返回是：" + string);
    }


    @Test
    public void test1223() throws IOException, InterruptedException {
        for (int i = 0; i < 600
                ; i++) {
            int mid = 1000 + new Random().nextInt(40000);
            FormBody formBody = new FormBody.Builder()
                    .add("vid", "70")
                    .add("vpid", "2388")
                    .add("mid", mid + "")
                    .add("wxcid", "12")//固定
                    .add("mdstr", "E45E000D76A7C5A461EEBF9701C4F543")//固定
                    .build();
            Request request = new Request.Builder().url("http://www.myradio8.cn/myradio/mobile/vote/dovote")
                    .post(formBody)
                    .addHeader("User-Agent", IPUtil.getWeixinClient())
                    .addHeader("X-Forwarded-For", IPUtil.getHanDanRandomIp())
                    .addHeader("Cookie", "openId_12=on8N0w9z7KAOysB9rhp7_Q3R7tU0;oauth_12=9BAE1D3EEC493216829701FE75E9EF7D;mid_12=" + mid + "; fid_12=" + mid + "; wxcid_12=021EFL4A0r8Lrh1XNH6A04BT4A0EFL4D;wxcid_12_20171229=12;")
//                .addHeader("Cookie","oauth_12=75A8170C813A3819E579FE8D360D2771; openId_12=on8N0w-hIcZStZcVXUKqQ3uL5dO8; mid_12=407453; fid_12=407453; wxcid_12=061pGYDZ1WQ2h01afgEZ1tTkEZ1pGYDi; wxcid_12_20171224=12;")
                    .build();

            Call call = okHttpClient.newCall(request);
            Response execute = call.execute();
            String string = execute.body().string();
            System.out.println("返回是：" + string);
            long l = new Random().nextInt(1) * 1000L;
            Thread.sleep(l);
        }
    }

    @Test
    public void test() throws IOException, InterruptedException {
        String ip;
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("uid", "196");
        for (int i = 0; i < 500; i++) {
            for (int j = 0; j < 5; j++) {
                Request request = new Request.Builder().url(" http://www.1jiyi.com/addtongji.php")
                        .addHeader("User-Agent", IPUtil.getWeixinClient())
                        .addHeader("X-Forwarded-For", ip = IPUtil.getHanDanRandomIp())
                        .post(builder.build()).
                                build();
                String result = okHttpClient.newCall(request).execute().body().string();
                System.out.println(result);
                if (result.equals("您今天已经投过5次票了")) {
                    break;
                }
            }
            Thread.sleep(1000 * 10);
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
            String weixinClientName = IPUtil.getWeixinClient();

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
                        .addHeader("Referer", firstUrl)
                        .addHeader("Cookie", newCookie)
                        .addHeader("User-Agent", weixinClientName)
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                        .get().build();

                Call call2 = okHttpClient.newCall(request2);
                Response execute2 = call2.execute();
                String string2 = execute2.body().string();
                System.out.println("ip = " + ipfinal + "\n返回结果：" + string2);

                Thread.sleep(60 * 1000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
