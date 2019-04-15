package com.mvn.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/httpclient")
public class HttpClientController {

    @RequestMapping("/sendGet")
    @ResponseBody
    public String sendGet(HttpServletRequest request){
        String code = request.getParameter("code");
        System.out.println("获取到的数据为: " + code);
        
        String json = "{\"id\":\"qwe\",\"name\":\"zhangsan\"}";
        return json;
        
    }
    
}
