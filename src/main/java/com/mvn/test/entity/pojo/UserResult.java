package com.mvn.test.entity.pojo;

import com.mvn.test.entity.User;

public class UserResult extends User{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "UserResult [code=" + code + ", msg=" + msg + "]";
    }
    

}
