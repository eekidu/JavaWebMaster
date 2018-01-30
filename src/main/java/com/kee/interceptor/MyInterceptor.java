package com.kee.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kee.utill.*;

/**
 * Created by kee on 2017/6/26.
 */
public class MyInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor---preHandle");
        ServletUtils.showAllParames(request);
        return true;
    }

    /**
     * 如果Controller response有返回，则这里添加Cookie无效
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        Cookie cookie = new Cookie("cookietest", "kee");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
        ServletUtils.showResponse(response);

        System.out.println("MyInterceptor---postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor---afterCompletion");
    }
}
