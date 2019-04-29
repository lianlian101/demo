package com.demo.obj;

public class Demo {

    public static void main(String[] args) {
        Child child = new Child();
        child.setName("父类");
        child.setSex("男");
        System.out.println(child.getName());
        
        Base base = (Base)new Child();
        System.out.println(base.getName());
        
       Base user = getUser(null);
       System.out.println("对象"+user);
    }
    
    public static Base getUser(Object obj){
        return (Base)obj;
    }
    
}
