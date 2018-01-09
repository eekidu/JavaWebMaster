package com.kee.dao;

import com.alibaba.druid.sql.builder.impl.SQLSelectBuilderImpl;
import com.kee.BaseTest;
import com.kee.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by wosyo on 2018/1/3.
 */
public class UserDaoTest extends BaseTest{
    @Autowired
    UserDao userDao;

    @Transactional
    @Test
    public void inserUser() throws Exception {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 5 * 10000; i++) {
            User user = new User();
            user.setName("haikuan"+i);
            int j = userDao.inserUser(user);
            System.out.println(i+"");
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
                        user.setName(Thread.currentThread().getName()+"haikuan"+i);
                        int j = userDao.inserUser(user);
                        System.out.println(i+"");
                    }
                }
            }).start();
        }

        Thread.sleep(300*1000);
    }
}