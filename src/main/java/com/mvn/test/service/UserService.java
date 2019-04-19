package com.mvn.test.service;

import java.util.List;

import com.mvn.test.entity.User;

public interface UserService {

    User getUser(Integer id);
    
    List<User> getUsers(String names);
    
    Integer addUser(User user);
    
    List<User> fuzzySearch(String param);
    
}
