package com.mvn.test.test.dao;

import java.util.List;

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
    
    @Test
    public void upateUser(){
        User user = new User();
        user.setId(1);
        user.setPassword("你好");
        userDao.updateUser(user);
    }
    
    @Test
    public void listUser(){
        try {
            User user = new User();
            user.setId(1);
            List<User> list = userDao.listUser(user);
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void fuzzySearch2(){
        try {
            List<User> list = userDao.fuzzySearch2("2");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
