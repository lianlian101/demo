package com.demo.util;

import org.junit.Test;

import com.mvn.test.util.HttpClientUtil;

public class HttpClientTest {

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: 模拟get请求
     *
     */
    @Test
    public void demo1(){
        String url = "http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js";
        String param = "code=123";
        String result = HttpClientUtil.sendGet(url, param);
        System.out.println(result);
    }
    
}
