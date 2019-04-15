package com.mvn.test.demo;

import org.junit.Test;

import com.mvn.test.util.HttpClientUtil;

public class HttpClientTest {

    @Test
    public void demo1(){
        
        String url = "http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js";
        String param = "code=123";
        
        String result = HttpClientUtil.sendGet(url, param);
        
        System.out.println(result);
        
    }
    
}
