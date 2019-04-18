package com.mvn.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/method/")
public class RequestMappingMethodController {

    @RequestMapping(value = "method_test")
    @ResponseBody
    public String methodTest(@RequestBody String data){
        
        return data;
    }
    
    @RequestMapping("/demo1")
    @ResponseBody
    public String demo1(@RequestParam String code){
        System.out.println(code);
        return code;
    }
    
    @RequestMapping("/demo2")
    @ResponseBody
    public String demo2(@RequestParam String code){
        System.out.println(code);
        String code2 = demo1(code);
        System.out.println(code2);
        return code;
    }
    
}
