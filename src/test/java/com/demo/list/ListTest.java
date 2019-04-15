package com.demo.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class ListTest {

    @Test
    public void demo1(){
       ArrayList<String> list = new ArrayList<String>();
       list.add("zhangsan");
       list.add("zhangsan");
       list.add("lisi");
       list.add("wangwu");
       list.add("wangwu");
       ArrayList<String> list2 = new ArrayList<String>();
       for (int i = 0; i < list.size(); i++) {
           String name = list.get(i);
           if(Collections.frequency(list, name) > 1){
               list2.add(name);
           }
       }
       HashSet<String> set = new HashSet<>(list2);
       list2.clear();
       list2.addAll(set);
       System.out.println(list2);
    }
    
    @Test
    public void demo2(){
       ArrayList<String> list = new ArrayList<String>();
       list.add("zhangsan");
       list.add("zhangsan");
       list.add("lisi");
       list.add("wangwu");
       list.add("wangwu");
       String s = JSON.toJSONString(list);
       System.out.println(s);
       String str = StringUtils.join(list.toArray(), ",");
       System.out.println(str);
    }
    
    
}
