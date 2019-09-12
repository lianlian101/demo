package com.demo.serialize;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

public class User implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -2072708072107785590L;

    private int id;
    
    private String name;
    
    @Transient
    private String pwd;

    public User() {
        super();
    }

    public User(int id, String name, String pwd) {
        super();
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + "]";
    }
    
}
