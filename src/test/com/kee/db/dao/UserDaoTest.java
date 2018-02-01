package com.kee.db.dao;

import com.kee.BaseTest;
import com.kee.db.dao.UserDao;
import com.kee.db.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.IOException;


/**
 * Created by wosyo on 2018/1/3.
 */
public class UserDaoTest extends BaseTest {
    @Autowired
    UserDao userDao;

    @Test
    public void inserOneUser() throws Exception {
        User user = new User();
        user.setName("kee");
        int i = userDao.inserUser(user);
        System.out.println(i);
    }


    @Transactional
    @Test
    public void inserUser() throws Exception {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 5 * 10000; i++) {
            User user = new User();
            user.setName("haikuan" + i);
            int j = userDao.inserUser(user);
            System.out.println(i + "");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - l);

    }

    @Test
    public void inserUserWithMulitThread() throws Exception {
        final User user = new User();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 1 * 10000; i++) {
                        user.setName(Thread.currentThread().getName() + "haikuan" + i);
                        int j = userDao.inserUser(user);
                        System.out.println(i + "");
                    }
                }
            }).start();
        }
        Thread.sleep(10000);
    }

    public void test() throws AWTException, IOException, InterruptedException {
        super.test();
    }

}