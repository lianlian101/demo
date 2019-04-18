package com.mvn.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvn.test.util.IP;
import com.mvn.test.util.IPUtil;

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
    
    @RequestMapping("/getIp")
    @ResponseBody
    public String getIp(HttpServletRequest request){
        String remoteIp = IP.getRemoteIp(request);
        System.out.println("remoteIp: " + remoteIp);
        
        String userIp = IP.getUserIP(request);
        System.out.println("客户端ip: " + userIp);
        
        String ip = IPUtil.getIpAdd(request);
        System.out.println("客户端ip: " + ip);
        
        return null;
    }
    
}
