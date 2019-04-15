package com.mvn.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvn.test.dao.UserDao;
import com.mvn.test.entity.User;
import com.mvn.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Override
    public User getUser(Integer id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> getUsers(String names) {
        return userDao.getUsers(names);
    }

    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

}
