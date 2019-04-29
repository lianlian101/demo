package com.mvn.test.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mvn.test.Application;
import com.mvn.test.dao.UserDao;
import com.mvn.test.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebAppConfiguration
public class UserDaoTest {

    @Autowired
    private UserDao userDao;
    
    @Test
    public void getUser(){
        try {
            User user = userDao.getUser(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
