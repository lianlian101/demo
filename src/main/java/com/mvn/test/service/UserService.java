package com.mvn.test.service;

import java.util.List;

import com.mvn.test.entity.User;

public interface UserService {

    User getUser(Integer id);
    
    List<User> getUsers(String names);
    
    List<User> getUserList();
    
    Integer addUser(User user);
    
    List<User> fuzzySearch(String param);
    
    List<User> fuzzySearch2(String param);
    
    Integer upateUser(User user);
    
}
