package com.mvn.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvn.test.util.aop.AspectLog;

@Controller
@RequestMapping("/aspect/")
public class AspectController {

    @AspectLog("正常测试")
    @RequestMapping("common")
    @ResponseBody
    public String common(){
        return "success";
    }
    
    @AspectLog("异常测试")
    @RequestMapping("ex")
    @ResponseBody
    public String ex(){
        int i = 3/0;
        return "ex";
    }
    
}
