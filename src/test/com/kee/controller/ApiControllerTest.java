package com.kee.controller;

import com.google.gson.Gson;
import com.kee.base.api.ResultBean;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by kee on 2018/1/30.
 */
public class ApiControllerTest {
    @Test
    public void helloWord() throws Exception {
        ResultBean resultBean = ResultBean.getDefaultResultBean();
        resultBean.addExtraInfo("ChineseTest", "中文测试");
        resultBean.addExtraInfo("DateTest", new Date());
        Gson gson = new Gson();
        String s = gson.toJson(resultBean);
        System.out.println(s);
    }

}