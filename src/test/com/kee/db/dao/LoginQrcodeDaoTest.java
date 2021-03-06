package com.kee.db.dao;

import com.kee.BaseTest;
import com.kee.db.model.LoginQrcode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Created by wosyo on 2018/2/1.
 */
public class LoginQrcodeDaoTest extends BaseTest {


    @Autowired
    LoginQrcodeDao loginQrcodeDao;


    @Test
    public void insertLoginQrcode() throws Exception {
        LoginQrcode loginQrcode = new LoginQrcode();
        loginQrcode.setQrcode(UUID.randomUUID().toString());
        loginQrcodeDao.insert(loginQrcode);
    }

    @Test
    public void selectLoginQrcode() throws Exception {
        LoginQrcode loginQrcode = loginQrcodeDao.selectLoginQrcodeByQrcode("1876cf3f-9a65-4268-914d-51579beb9d2d");
        System.out.println(loginQrcode);
    }

    @Test
    public void update() throws Exception {
        LoginQrcode loginQrcode = new LoginQrcode();
        loginQrcode.setQrcode("1876cf3f-9a65-4268-914d-51579beb9d2d");
        loginQrcode.setState(1);
        loginQrcodeDao.update(loginQrcode);
    }

}