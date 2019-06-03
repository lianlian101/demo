package com.mvn.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mvn.test.entity.User;

public interface UserDao {

    // 获取用户
    User getUser(@Param("id")Integer id);
    
    // 获取多个用户将结果合并
    List<User> getUsers(@Param("names")String names);
    
    // 获取多个用户
    List<User> getUserList();
    
    // 添加用户
    Integer addUser(User user);
    
    // 模糊查询
    List<User> fuzzySearch(@Param("param")String param);
    
    // 更新
    Integer updateUser(User user);
    
    // 批量添加
    Integer addUsers(List<User> users);
    
    // 批量更新
    Integer updateUsers(List<User> users);
    
    // 获取用户
    List<User> listUser(User user);
    
}
