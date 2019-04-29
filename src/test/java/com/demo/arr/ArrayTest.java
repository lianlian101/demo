package com.demo.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class ArrayTest {

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
 
    
    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: Arrays.asList(list); list.toArray(str);
     *
     */
    @Test
    public void demo3() {
        String[] arrayStr = { "java", "sql", "html", "" };
        List<String> list = Arrays.asList(arrayStr);
        System.out.println(list);
        arrayStr[3] = "javascript";

        System.out.println(list);

        List<String> list2 = new ArrayList<>(list);
        list2.add("js");
        System.out.println(list2);

        String[] str = new String[list.size()];
        String[] array = list.toArray(str);
        System.out.println(Arrays.asList(array));

    }
    
    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: ArrayUtils.reverse(str);
     *
     */
    @Test
    public void demo4(){
        String[] str = {"java","mysql","javascript"};
        ArrayUtils.reverse(str);
        System.out.println(Arrays.asList(str));
    }
    
    
}
