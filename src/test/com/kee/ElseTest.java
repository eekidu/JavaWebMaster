package com.kee;

import com.kee.db.model.LoginQrcode;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by kee on 2018/1/10.
 */
public class ElseTest {

    private final static Logger logger = LoggerFactory.getLogger(ElseTest.class);

    /**
     * 测试日志系统,配合配置文件logback.xml调整
     */
    @Test
    public void testLog() {
        logger.debug("logback debug");
        logger.info("logback info");
        logger.warn("logback info");
        logger.error("logback error");
    }

    @Test
    public void testSql() {
        LoginQrcode loginQrcode = new LoginQrcode();
        loginQrcode.setQrcode("caohaikuan");
        loginQrcode.setState(0);

        int b = 1;
        updateInt(b);
        System.out.println(b);

        String abc = new String("caohaikuan");
        updateString(abc);
        System.out.println(abc);

        System.out.println(loginQrcode);
        update(loginQrcode);
        System.out.println(loginQrcode);

    }

    private void updateString(String abc) {
        abc = "kee";
    }

    private void update(LoginQrcode loginQrcode) {
        loginQrcode.setQrcode("kee");
        loginQrcode.setState(1);
    }

    private void updateInt(int a) {
        System.out.println(a);
        a = 10;
    }

}
