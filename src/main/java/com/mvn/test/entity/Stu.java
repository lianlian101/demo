package com.mvn.test.entity;

import java.util.Date;

public class Stu {

    private int id;
    
    private Date time;

    public Stu() {
        super();
    }

    public Stu(int id, Date time) {
        super();
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Stu [id=" + id + ", time=" + time + "]";
    }
    
}
