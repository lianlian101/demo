package com.mvn.test.entity.pojo;

import java.io.Serializable;
import java.util.List;

public class UserArr implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long userId;
    
    
    private List<Long> users;
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    
}
