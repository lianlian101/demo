package com.demo.arr;

import java.util.Arrays;

import org.apache.xmlbeans.impl.inst2xsd.RussianDollStrategy;
import org.junit.Test;

public class Demo {

    /**
     * 数组的扩容
     */
    @Test
    public void demo1(){
       
        //原数组
        int[] arr = {2,1,5};
        System.out.println("原数组: "+ Arrays.toString(arr));
        
        System.out.println("\n-----------------------\n");
        
        //扩容为原来的2倍
        //方式一
        int[] arr2 = new int[arr.length * 2];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        System.out.println("新数组: " + Arrays.toString(arr2));
        
        System.out.println("\n-----------------------\n");
        
        //方式二
        int[] arr3 = Arrays.copyOf(arr, arr.length*2);
        System.out.println("新数组: " + Arrays.toString(arr3));
           
    }
    
    /**
     * 排序
     */
    @Test
    public void demo2(){
        
        int[] arr = {2,6,1,7,3};
        
        Arrays.sort(arr);
        
        System.out.println(Arrays.toString(arr));
        
    }
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
