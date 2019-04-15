package com.mvn.test.response;

import java.io.Serializable;

public class AjaxResult implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer code = 1;
    
    private String msg = "操作成功";
    
    private Object data = null;

    public AjaxResult() {
        super();
    }

    public AjaxResult(Integer code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }
    
}
