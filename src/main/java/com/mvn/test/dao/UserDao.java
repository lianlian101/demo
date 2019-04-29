package com.mvn.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mvn.test.entity.User;

public interface UserDao {

    User getUser(@Param("id")Integer id);
    
    List<User> getUsers(@Param("names")String names);
    
    List<User> getUserList();
    
    Integer addUser(User user);
    
    List<User> fuzzySearch(@Param("param")String param);
    
}
