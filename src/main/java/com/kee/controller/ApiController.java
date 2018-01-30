package com.kee.controller;


import com.kee.base.api.ResultBean;
import com.kee.service.UserService;
import com.kee.utill.IPUtil;
import com.kee.utill.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by wosyo on 2018/1/8.
 */
@Controller
@RequestMapping(value = "/api")
public class ApiController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApiController.class);


    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "hello")
    public ResultBean HelloWord(Date date, Long date2, HttpServletResponse httpServletResponse) {
        ResultBean resultBean = ResultBean.getDefaultResultBean();
        resultBean.addExtraInfo("ChineseTest", "中文测试");
        resultBean.addExtraInfo("DateTest", new Date());
        if (date != null)
            resultBean.addExtraInfo("inputDateTimeStamp", date.getTime());
        if (date2 != null)
            resultBean.addExtraInfo("inputDateTimeStamp2", date2);
        Cookie cookie = new Cookie("hello", "1234");
        Cookie cookie1 = new Cookie("hello1", "12345");
        httpServletResponse.addCookie(cookie);
        httpServletResponse.addCookie(cookie1);
        return resultBean;
    }

    /**
     * 读取所有cookie
     * 注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。浏览器提交Cookie时只会提交name与value属性。maxAge属性只被浏览器用来判断Cookie是否过期
     *
     * @param request
     * @param response
     */
    @RequestMapping("/showCookies")
    public void showCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null == cookies) {
            System.out.println("没有cookie=========");
        } else {
            for (Cookie cookie : cookies) {
                System.out.println("showCookies name:" + cookie.getName() + ",value:" + cookie.getValue());
            }
        }
    }


    /**
     * 统一异常处理
     *
     * @param request
     * @param response
     * @param exception
     */
    @ExceptionHandler
    public ModelAndView exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        LOGGER.error("统一异常处理：", exception);
        request.setAttribute("ex", exception);
        if (null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            request.setAttribute("requestHeader", "ajax");
        }
//        // shiro没有权限异常
//        if (exception instanceof UnauthorizedException) {
//            return "/403.jsp";
//        }
//        // shiro会话已过期异常
//        if (exception instanceof InvalidSessionException) {
//            return "/error.jsp";
//        }
        ModelAndView index = new ModelAndView("500");
        return index;
    }

}
