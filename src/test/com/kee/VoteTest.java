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
    public void test1223() throws IOException, InterruptedException {
        for (int i = 0; i < 600
                ; i++) {
            int mid = 30000+new Random().nextInt(10000);
            FormBody formBody = new FormBody.Builder()
                    .add("vid", "70")
                    .add("vpid", "2388")
                    .add("mid", mid+"")
                    .add("wxcid", "12")//固定
                    .add("mdstr", "E45E000D76A7C5A461EEBF9701C4F543")//固定
                    .build();
            Request request = new Request.Builder().url("http://www.myradio8.cn/myradio/mobile/vote/dovote")
                    .post(formBody)
                    .addHeader("User-Agent", IPUtil.getWeixinClient())
                    .addHeader("X-Forwarded-For", IPUtil.getHanDanRandomIp())
                    .addHeader("Cookie", "openId_12=on8N0w9z7KAOysB9rhp7_Q3R7tU0;oauth_12=9BAE1D3EEC493216829701FE75E9EF7D;mid_12=" + mid + "; fid_12=" + mid + "; wxcid_12=021EFL4A0r8Lrh1XNH6A04BT4A0EFL4D;wxcid_12_20171228=12;")
//                .addHeader("Cookie","oauth_12=75A8170C813A3819E579FE8D360D2771; openId_12=on8N0w-hIcZStZcVXUKqQ3uL5dO8; mid_12=407453; fid_12=407453; wxcid_12=061pGYDZ1WQ2h01afgEZ1tTkEZ1pGYDi; wxcid_12_20171224=12;")
                    .build();

            Call call = okHttpClient.newCall(request);
            Response execute = call.execute();
            String string = execute.body().string();
            System.out.println("返回是：" + string);
            long l = new Random().nextInt(10) * 1000L;
            Thread.sleep(l);
        }
    }


    class Person {

        /**
         * id : 15
         * title : 吴庆波
         * thumb : http://www.1jiyi.com/uploadfile/2017/0728/20170728100536894.jpg
         * tongjil : 847
         * depart : 万浩家园店
         * identifier : 002
         */

        private String id;
        private String title;
        private String thumb;
        private String tongjil;
        private String depart;
        private String identifier;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getTongjil() {
            return tongjil;
        }

        public void setTongjil(String tongjil) {
            this.tongjil = tongjil;
        }

        public String getDepart() {
            return depart;
        }

        public void setDepart(String depart) {
            this.depart = depart;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", tongjil='" + tongjil + '\'' +
                    ", depart='" + depart + '\'' +
                    ", identifier='" + identifier + '\'' +
                    ", thumb='" + thumb + '\'' +
                    '}';
        }
    }

    @Test
    public void startSee() {
        while (true) {
            getList();
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getList() {
        FormBody formBody = new FormBody.Builder()
                .add("pagesize", "311")
                .add("currpage", "1")
                .build();
        Request request = new Request.Builder().url("http://www.1jiyi.com/votenew.php")
                .post(formBody)
                .addHeader("User-Agent", IPUtil.getWeixinClient())
                .addHeader("X-Forwarded-For", IPUtil.getHanDanRandomIp())
                .build();

        try {
            String string = okHttpClient.newCall(request).execute().body().string();
            JsonObject parse = new JsonParser().parse(string).getAsJsonObject();
            JsonArray alldata = parse.get("alldata").getAsJsonArray();
            Gson gson = new Gson();
            List<Person> peoples = gson.fromJson(alldata, new TypeToken<ArrayList<Person>>() {
            }.getType());
            Collections.sort(peoples, new Comparator<Person>() {
                public int compare(Person o1, Person o2) {
                    return Integer.valueOf(o2.getTongjil()) - Integer.valueOf(o1.getTongjil());
                }
            });

            Person guojing = null;
            for (int i = 0; i < 15; i++) {
                Person person = peoples.get(i);
                System.out.println(i + 1 + " : " + person);
                if (person.getId().equals("196")) {
                    guojing = person;
                }
            }
//            Person first = peoples.get(1);
//            if (!first.getId().equals("196")) {
//                normalTouPiaoNum(Integer.valueOf(first.getTongjil()) - Integer.valueOf(guojing.getTongjil()));
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void normalTouPiaoNum(int def) {
        int i = (def / 25) + 1;
        try {
            normalTouPiao(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static int count = 0;

    @Test
    public void toupiao() {
        try {
            normalTouPiao(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void normalTouPiao(final int kee) throws InterruptedException {
        okHttpClient.dispatcher().setMaxRequestsPerHost(20);
        final FormBody.Builder builder = new FormBody.Builder();
//        builder.add("uid", "145");
        builder.add("uid", "196");
        long starttime = new Date().getTime();
        for (int k = 0; k < 10; k++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 2 * kee; j++) {
                        String ip;
                        final Request request = new Request.Builder().url(" http://www.1jiyi.com/addtongji.php")
                                .addHeader("User-Agent", IPUtil.getWeixinClient())
                                .addHeader("X-Forwarded-For", ip = IPUtil.getRandomIp())
                                .post(builder.build()).
                                        build();
                        System.out.println(ip);
                        for (int i = 0; i < 5; i++) {
                            Call call = okHttpClient.newCall(request);
                            call.enqueue(new Callback() {
                                public void onFailure(Call call, IOException e) {

                                }

                                public void onResponse(Call call, Response response) throws IOException {
                                    String string = response.body().string();
                                    System.out.println(string);
                                    count++;
                                }
                            });
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                        }
                    }
                }
            }.start();
        }
        while (true) {
            Thread.sleep(1000);
            System.out.println("共投票 = " + count + " 正在执行的数量 = " + okHttpClient.dispatcher().runningCallsCount());

            if (okHttpClient.dispatcher().runningCallsCount() == 0) {
                long costtime = 0;
                System.out.println("共投票 = " + count + "  共耗时 = " + (costtime = new Date().getTime() - starttime) + "\n"
                        + "每票耗时 = " + costtime / count
                );
                getList();
                break;
            }
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
