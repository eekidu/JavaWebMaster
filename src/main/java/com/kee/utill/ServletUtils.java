/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package utill;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by kee on 2017/7/13.
 */
public class ServletUtils {

    public static void showAllParames(HttpServletRequest request) {
        System.out.println("------------------" + new Date().toLocaleString() + "------------------");

        System.out.println("/*****************  HeardNames  ***********/");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headname = headerNames.nextElement();
            String header = request.getHeader(headname);
            System.out.println(headname + "  :  " + header);
        }
        System.out.println("/*****************  ParameterNames  ***********/");

        Enumeration<String> attributeNames = request.getParameterNames();
        while (attributeNames.hasMoreElements()) {
            String pname = attributeNames.nextElement();
            String parameter = request.getParameter(pname);
            System.out.println(pname + ":" + parameter);
        }
        System.out.println("/*****************  Cookie  *******************/");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ":");
                System.out.println("-- cookie.getValue():" + cookie.getValue());
                System.out.println("-- cookie.getComment():" + cookie.getComment());
                System.out.println("-- cookie.getDomain():" + cookie.getDomain());
            }
        } else {
            System.out.println("No Cookie");
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
