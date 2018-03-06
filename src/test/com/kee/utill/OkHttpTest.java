package com.kee.utill;

import com.kee.BaseTest;
import com.kee.utill.cookie.CookieJarImpl;
import com.kee.utill.cookie.MemoryCookieStore;
import io.reactivex.exceptions.Exceptions;
import okhttp3.*;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kee on 2018/3/6.
 */
public class OkHttpTest extends BaseTest {
    @Test
    public void Test01() throws IOException {
        OkHttpClient client = okHttpClient.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                System.out.println(request);
                return chain.proceed(request);
            }
        })
                .cookieJar(new CookieJarImpl(new MemoryCookieStore()))
             /*   .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        System.out.println("httpUrl = " + httpUrl);
                        System.out.println("cookie size =" + list.size());
                        for (Cookie cookie : list) {
                            System.out.println(cookie.name() + "  " + cookie.value());
                        }

                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        return new ArrayList<Cookie>();
                    }
                })*/
                .build();

        Request request = new Request.Builder().url("http://localhost:8080/api/showCookies")
                .get().build();
        Call call = client.newCall(request);
        Response execute = call.execute();
        System.out.println(execute.body().string());

        Request request1 = new Request.Builder().url("http://localhost:8080/api/showCookies")
                .get().build();
        Call call1 = client.newCall(request1);
        Response execute1 = call1.execute();
        System.out.println(execute1.body().string());

    }

    @Test
    public void tokenTest() {

    }
}
