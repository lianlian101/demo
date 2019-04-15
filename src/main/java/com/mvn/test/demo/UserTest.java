package com.mvn.test.demo;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.mvn.test.entity.pojo.UserResult;

public class UserTest {

    @Test
    public void demo1(){
        String str = "{\"id\":\"1\",\"username\":\"zhangsan\",\"password\":\"123\",\"code\":\"456\",\"msg\":\"测试\"}";
        UserResult userResult = JSON.parseObject(str, UserResult.class);
        System.out.println(userResult.getUsername());
    }
    
}
