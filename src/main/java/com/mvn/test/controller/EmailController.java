package com.mvn.test.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.mvn.test.util.Email;

@Controller
@RequestMapping("/email")
public class EmailController {
    
    @RequestMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(){
        
        HashMap<String,String> map = new HashMap<>();
        
        map.put("username", "noreply@snfy360.com");
        map.put("password", "Gatebim118118118");
        map.put("fromUser", "noreply@gatebim.com");
        map.put("toUser", "13612107952@163.com");
        map.put("title", "测试企业邮箱");
        map.put("content", "测试邮箱发送4");
        map.put("nick", "GateBim系统验证邮件");
        
        //Email.sendEmail(map);
        
        return "1";
    }

}
