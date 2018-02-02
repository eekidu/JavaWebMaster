package com.kee.controller;

import com.kee.db.model.LoginQrcode;
import com.kee.service.LoginQrcodeService;
import com.kee.utill.ZxingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by kee on 2018/2/2.
 */
@Controller()
@RequestMapping(value = "/login")
public class LoginQrcodeController {
    @Autowired
    LoginQrcodeService loginQrcodeService;

    private static int TIME_OUT = 1000 * 20;
    private static long PRE_QR_VALID_TIME = 1000 * 20;

    @ResponseBody
    @RequestMapping(value = "getQrcode")
    public void getQrcode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String TEMPLIVEURL = "%s://%s:%s";
        String templiveurl = String.format(TEMPLIVEURL, httpServletRequest.getScheme(), httpServletRequest.getServerName(), httpServletRequest.getServerPort());

        LoginQrcode loginQrcode = loginQrcodeService.createLoginQrcode();
        try {
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setHeader("Cache-Control", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setHeader("last-modified", new Date().toString());

            Cookie cookie = new Cookie("qrcode", loginQrcode.getQrcode());
            cookie.setMaxAge(1000 * 60);
            httpServletResponse.addCookie(cookie);
            httpServletResponse.setContentType("image/png");

            String urltarget = templiveurl + "/login/postQrcode?qr=" + loginQrcode.getQrcode();

            System.out.println(urltarget);
            ZxingHandler.encode2ToStream(urltarget, 400, 400, httpServletResponse.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "looperQrcode")
    public String looperQrcode(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Date startDate = new Date();
        String qrcode = "";
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("qrcode")) {
                qrcode = cookie.getValue();
            }
        }

        try {
            if (loginQrcodeService.checkQrValid(qrcode)) {
                while (new Date().getTime() - startDate.getTime() < TIME_OUT) {
                    if (loginQrcodeService.isInQrSuccessList(qrcode)) {
                        loginQrcodeService.updateQrState(qrcode, LoginQrcodeService.QrcodeState.RETURN_TO_WEB_SUCCESS);
                        return "扫码成功，登录成功";
                    }
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "还没有扫码";
    }


    @ResponseBody
    @RequestMapping(value = "postQrcode")
    public String postQrcode(String qr) {
        boolean isValid = false;
        try {
            if (loginQrcodeService.checkQrValid(qr))
                loginQrcodeService.updateQrState(qr, LoginQrcodeService.QrcodeState.USER_QR_SUCCESS);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "登录成功";
    }

}
