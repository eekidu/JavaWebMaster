package com.kee;


import okhttp3.OkHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by wosyo on 2017/8/1.
 */

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-normalTouPiao,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class BaseTest {

    public static long DEFAULT_TIME_OUT = 30;
    public static OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            //设置写超时
            .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            //设置读超时
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS).build();


    @Test
    public void test() throws AWTException, IOException {


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image, "png", new File("D:\\pasht"));


    }

}

