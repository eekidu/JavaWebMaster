package com.kee.service;

import com.kee.BaseTest;
import com.kee.db.model.LoginQrcode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by kee on 2018/2/2.
 */
public class LoginQrcodeServiceTest extends BaseTest {
    @Autowired
    LoginQrcodeService loginQrcodeService;

    @Test
    public void createLoginQrcode() throws Exception {
        LoginQrcode loginQrcode = loginQrcodeService.createLoginQrcode();
        System.out.println(loginQrcode.getQrcode());
    }

}