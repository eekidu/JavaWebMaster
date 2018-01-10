package com.kee.controller;


import com.kee.service.UserService;
import com.kee.utill.IPUtil;
import com.kee.utill.ServletUtils;
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
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "hello")
    public String HelloWord() {
        userService.insertSomeUser(5*10000);
        return "helloword";
    }
}
