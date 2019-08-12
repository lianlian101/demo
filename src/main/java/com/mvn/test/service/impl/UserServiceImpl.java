package com.mvn.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value="def", key="#root.methodName+#root.targetClass")
    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }
    
    @CacheEvict(value="def", allEntries=true)
    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> fuzzySearch(String param) {
        return userDao.fuzzySearch(param);
    }
    
    @Override
    public List<User> fuzzySearch2(String param) {
        return userDao.fuzzySearch2(param);
    }

    @Override
    public Integer upateUser(User user) {
        return userDao.updateUser(user);
    }


}
