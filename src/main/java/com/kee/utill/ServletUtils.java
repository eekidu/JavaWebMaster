package com.kee.utill;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by kee on 2017/7/13.
 */
public class ServletUtils {

    public static void showAllParames(HttpServletRequest request) {
        System.out.println("*****************" + new Date().toLocaleString() + "*****************");

        System.out.println("/--------------  HeardNames  --------------/");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headname = headerNames.nextElement();
            String header = request.getHeader(headname);
            System.out.println(headname + "  :  " + header);
        }
        System.out.println("/-------------- ParameterNames  --------------/");

        Enumeration<String> attributeNames = request.getParameterNames();
        while (attributeNames.hasMoreElements()) {
            String pname = attributeNames.nextElement();
            String parameter = request.getParameter(pname);
            System.out.println(pname + ":" + parameter);
        }
        // 注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。
        // 浏览器提交Cookie时只会提交name与value属性。maxAge属性只被浏览器用来判断Cookie是否过期
        System.out.println("/--------------  Cookie  --------------/");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ":" + cookie.getValue());
            }
        } else {
            System.out.println("No Cookie");
        }
        System.out.println("****************  END  *****************");
    }

    public static void showResponse(HttpServletResponse response) {
        System.out.println("*****************  showResponse" + new Date().toLocaleString() + "*****************");
        Collection<String> headerNames = response.getHeaderNames();
        for (String headerName : headerNames) {
            String header = response.getHeader(headerName);
            System.out.println(headerName + " : " + header);
        }
    }


    /**
     * 根据Cookie名称获得对应Cookie值
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
