package com.mvn.test.util.customcache.demo;

import java.io.Serializable;

public class Entity implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 数据
     */
    private Object data;
    /**
     * 数据创建时间：默认为当前时间
     */
    private long dataTime = System.currentTimeMillis();
    /**
     * 是否过期：-1不过期，默认为-1
     */
    private long expired = -1L;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }
    

}
