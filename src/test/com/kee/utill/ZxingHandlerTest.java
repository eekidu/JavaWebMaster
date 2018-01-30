package com.kee.utill;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kee on 2018/1/30.
 */
public class ZxingHandlerTest {
    @Test
    public void encode() throws Exception {
    }

    @Test
    public void decode() throws Exception {
    }

    @Test
    public void encode2() throws Exception {
    }

    @Test
    public void decode2() throws Exception {
        String s = ZxingHandler.decode2("target\\test01.jpg");
        System.out.println(s);
    }

    @Test
    public void main() throws Exception {
    }

}