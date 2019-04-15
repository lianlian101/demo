package com.mvn.test.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String username;
    
    private String password;
    
    private Date createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", createTime=" + createTime + "]";
    }
    
    
}
