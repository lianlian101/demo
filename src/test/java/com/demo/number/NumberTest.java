package com.demo.number;

import org.junit.Test;

public class NumberTest {

    /**
     * 创建日期: 2019年5月6日 创建人: zhb 说明: <<、>> 运算
     *
     */
    @Test
    public void demo1(){
        int a = 3;
        int b = a << 2; // 乘以2的平方
        System.out.println(b);
        a = 3;
        b = a << 3; // 乘以2的3次方
        System.out.println(b);
        
        a = 32;
        b = a >> 2; // 除以2的平方
        System.out.println(b);
        a = 32;
        b = a >> 3; // 除以2的三次方
        System.out.println(b);
    }
    
    /**
     * 创建日期: 2019年5月6日 创建人: zhb 说明: >>> 运算, 无符号右移
     *
     */
    @Test
    public void demo2(){
        int a = 32;
        int b = a >>> 2;
        System.out.println(b);
    }
    
    /**
     * 创建日期: 2019年5月6日 创建人: zhb 说明: 亦或运算
     *
     */
    @Test
    public void demo3(){
        int a = 2;
        int b = 3;
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("a = "+ a + ", b = " + b);
        
        a = 2;
        b = 3;
        int c = a ^ b;
        a = a ^ c;
        b = b ^ c;
        System.out.println("a = "+ a + ", b = " + b);
    }
    
    
}
