package com.mvn.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/restful/")
public class RestfulController {

    @RequestMapping("/index/{str}")
    @ResponseBody
    public String index(@PathVariable String str){
        
        System.out.println("接收到的数据：" + str);
        
        return "index";
    }
    
    
}
