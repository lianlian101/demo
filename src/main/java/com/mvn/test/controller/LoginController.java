package com.mvn.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/login/")
public class LoginController {

    @RequestMapping("/login")
    public String login(HttpSession session){
        session.setAttribute("login", "user");
        return "登录成功";
    }
    
}
