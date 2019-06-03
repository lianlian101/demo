package com.mvn.test.test.dao;

import java.util.ArrayList;
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
    public void addUsers(){
        try {
            List<User> list = new ArrayList<>();
            User user1 = new User(null,"添加1","good",null);
            User user2 = new User(null,"添加2","good",null);
            User user3 = new User(null,"添加3","good",null);
            list.add(user1);
            list.add(user2);
            list.add(user3);
            Integer m = userDao.addUsers(list);
            System.out.println(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void updateUsers(){
        try {
            List<User> list = new ArrayList<>();
            User user1 = new User(1,"zs","haha",null);
            User user2 = new User(2,"ls","haha",null);
            list.add(user1);
            list.add(user2);
            Integer m = userDao.updateUsers(list);
            System.out.println(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
}
