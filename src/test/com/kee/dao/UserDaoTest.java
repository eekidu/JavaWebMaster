package com.kee.dao;

import com.alibaba.druid.sql.builder.impl.SQLSelectBuilderImpl;
import com.kee.BaseTest;
import com.kee.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by wosyo on 2018/1/3.
 */
public class UserDaoTest extends BaseTest{
    @Autowired
    UserDao userDao;

    @Test
    public void inserUser() throws Exception {

        User user = new User();
        for (int i = 0; i < 5 * 10000; i++) {
            user.setName("haikuan"+i);
            int j = userDao.inserUser(user);
            System.out.println(i+"");
        }

    }

    @Test
    public void inserUserWithMulitThread() throws Exception {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 1 * 10000; i++) {
                        User user = new User();
                        user.setName("haikuan"+i);
                        int j = userDao.inserUser(user);
                        System.out.println(i+"");
                    }
                }
            }).start();
        }

        Thread.sleep(300*1000);
    }
}