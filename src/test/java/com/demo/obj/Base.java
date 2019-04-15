package com.demo.obj;

public class Base {

    private String name;

    public Base() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Base(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Base [name=" + name + "]";
    }

    
    
}
