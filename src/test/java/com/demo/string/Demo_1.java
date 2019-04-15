package com.demo.string;

import org.junit.Test;

public class Demo_1 {
    
    @Test
    public void demo1(){
        String str = "zhangsan";
        boolean b = str.isEmpty();
        System.out.println(b);
    }
    
    @Test
    public void demo2(){
        String str = "zhangsan";
        CharSequence sequence = str.subSequence(0, 3);
        System.out.println(sequence.toString());
        System.out.println(str);
    }
    
    @Test
    public void demo3(){
        String str = "zhangsan";
        String substring = str.substring(0, 3);
        System.out.println(substring);
        System.out.println(str);
    }

    @Test
    public void demo4(){
        String str = "zhangsan ";
        System.out.println("zhangsan".equals(str));
        System.out.println("zhangsan".equals(str.trim()));        
    }
    
}
