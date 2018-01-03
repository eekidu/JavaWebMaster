package com.kee.dao;

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
        for (int i = 0; i < 10 * 10000; i++) {
            User user = new User();
            user.setName("haikuan"+i);
            int j = userDao.inserUser(user);
            System.out.println(i+"");
        }
    }

}