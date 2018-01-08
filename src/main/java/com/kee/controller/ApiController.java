package com.kee.controller;

import com.kee.dao.UserDao;
import com.kee.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wosyo on 2018/1/8.
 */
@Controller
@RequestMapping(value = "/")
public class ApiController {

    @Autowired
    UserDao userDao;

    @ResponseBody
    @RequestMapping(value = "hello")
    public String HelloWord() {
        User user = new User();
        user.setName("haikuan");
        int i = userDao.inserUser(user);
        return "Hello,world" + i;
    }
}
