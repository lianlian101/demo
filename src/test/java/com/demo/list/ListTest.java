package com.demo.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class ListTest {

    @Test
    public void demo1() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        list.add("wangwu");
        System.out.println(Collections.frequency(list, "wangwu"));
        ArrayList<String> list2 = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            if (Collections.frequency(list, name) > 1) {
                list2.add(name);
            }
        }
        HashSet<String> set = new HashSet<>(list2);
        list2.clear();
        list2.addAll(set);
        System.out.println(list2);
    }

    @Test
    public void demo2() {
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

    @Test
    public void demo3() {
        List<String> list = new ArrayList<String>();
        list.add("java");
        list.add("mysql");
        list.add("sql");
        list.add("js");
        list.add("php");
        list.add("python");
        list.add("go");
        System.out.println(list);
        System.out.println("迭代的方式遍历：");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if ("sql".equals(next)) {
                iterator.remove();
            }
        }
        System.out.println(list);
        System.out.println("Lambda的方式遍历：");
        list.forEach(itea -> System.out.println(itea));

        System.out.println("Lambda的方式遍历：");
        Map<Object, Object> map = new HashMap<>();
        map.put("name", "java");
        map.put("content", "编程");
        map.forEach((k, v) -> {
            System.out.println(k + "-->" + v);
        });

    }

    @Test
    public void demo4() {
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

    @Test
    public void demo5() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        /*for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }*/
        
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            if("1".equals(next)){
                iterator.remove();
            }
        }
        System.out.println(list);
    }
    
    @Test
    public void demo6(){
        String[] str = {"java","mysql","javascript"};
        ArrayUtils.reverse(str);
        System.out.println(Arrays.asList(str));
       
    }

}
