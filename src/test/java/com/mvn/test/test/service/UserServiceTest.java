package com.mvn.test.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mvn.test.Application;
import com.mvn.test.entity.User;
import com.mvn.test.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;
    
    @Test
    public void getUser(){
        try {
            User user = userService.getUser(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
