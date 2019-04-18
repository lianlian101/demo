package com.mvn.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/restful/")
public class RestfulController {

    //@GetMapping("user")
    @RequestMapping(value="user",method=RequestMethod.GET)
    @ResponseBody
    public String getUser(){
        
        System.out.println("获取用户");
        
        return "get";
    }
    
    //@PostMapping("user")
    @RequestMapping(value="user",method=RequestMethod.POST)
    @ResponseBody
    public String addUser(){
        
        System.out.println("添加用户");
        
        return "add";
    }
    
    //@PutMapping("user")
    @RequestMapping(value="user",method=RequestMethod.PUT)
    @ResponseBody
    public String updateUser(){
        
        System.out.println("更新用户");
        
        return "update";
    }
    
    //@DeleteMapping("user")
    @RequestMapping(value="user",method=RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(){
        
        System.out.println("删除用户");
        
        return "delete";
    }
    
    @RequestMapping(value={"path_variable/{str}","path_variable"})
    @ResponseBody
    public String pathVariable(@PathVariable(required=false)String str){
        if(null == str){
            return "无参";
        }
        return str;
    }
    
    @RequestMapping("request_param")
    @ResponseBody
    public String requestParam(@RequestParam(required=false)String str){
        if(null == str){
            return "无参";
        }
        return str;
    }
    
}
