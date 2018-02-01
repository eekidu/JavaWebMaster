package com.kee.db.dao;

import com.kee.BaseTest;
import com.kee.db.model.LoginQrcode;
import com.kee.db.model.User;
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
        LoginQrcode loginQrcode = loginQrcodeDao.selectLoginQrcode("33439d13-bc8a-4d35-8676-91fc783210aa");
        System.out.println(loginQrcode);
    }

    @Test
    public void update() throws Exception {
        LoginQrcode loginQrcode = new LoginQrcode();
        loginQrcode.setQrcode("33439d13-bc8a-4d35-8676-91fc783210aa");
        loginQrcode.setState(1);
        loginQrcodeDao.update(loginQrcode);
    }

}