package com.kee.service;

import com.kee.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by kee on 2018/1/9.
 */
public class UserServiceTest extends BaseTest{
    @Autowired
    UserService userService;

    @Test
    public void insertSomeUser() throws Exception {
        userService.insertSomeUser(5*10000);
    }

}