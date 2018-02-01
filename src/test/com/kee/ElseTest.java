package com.kee;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by kee on 2018/1/10.
 */
public class ElseTest extends BaseTest {

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
        System.out.println("hah");
    }
}
