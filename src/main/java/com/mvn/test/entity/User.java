package com.mvn.test.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer id;

    private String username;
    
    private String password;
    
    private Date createTime;
    
    private Integer depId;
    
    public User() {
        super();
    }
    
    public User(Integer id, String username, String password, Date createTime, Integer depId) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.createTime = createTime;
        this.depId = depId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", createTime=" + createTime + ", depId=" + depId + "]";
    }


}
