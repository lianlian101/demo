package com.demo.obj;

public class Child extends Base{

    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Child [sex=" + sex + "]";
    }

    
    
}
