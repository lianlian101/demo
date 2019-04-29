package com.demo.fastjson;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mvn.test.entity.pojo.UserResult;

public class FastJsonDemo {

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: FastJSON 解析字符串
     *
     */
    @Test
    public void demo1(){
        String str = "{}";
        JSONObject po = JSON.parseObject(str);
        String email = (String) po.get("email");
        System.out.println(email);
        System.out.println("{}".equals(str));
    }
    
    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: 解析json
     *
     */
    @Test
    public void demo2(){
        String str = "{\"id\":\"1\",\"username\":\"zhangsan\",\"password\":\"123\",\"code\":\"456\",\"msg\":\"测试\"}";
        UserResult userResult = JSON.parseObject(str, UserResult.class);
        System.out.println(userResult.getUsername());
    }
    
}
