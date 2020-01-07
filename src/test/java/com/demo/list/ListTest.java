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
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.mvn.test.entity.User;

public class ListTest {

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: Collentions.frequency(list, str);
     *
     */
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

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: list.toArray(); StringUtils.join(arr, char);
     *
     */
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

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: list.iterator(); list.forEach(action); list.stream(), list.parallelStream()
     *
     */
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
        list.forEach(item -> System.out.println(item));

        System.out.println("Lambda的方式遍历：");
        Map<Object, Object> map = new HashMap<>();
        map.put("name", "java");
        map.put("content", "编程");
        map.forEach((k, v) -> {
            System.out.println(k + "-->" + v);
        });
        
        System.out.println("单管道stream遍历：");
        list.stream().forEach(item -> System.out.println(item));
        
        System.out.println("多管道stream遍历：");
        list.parallelStream().forEach(item -> {System.out.println(item);});

    }

    /**
     * 创建日期: 2019年4月29日 创建人: zhb 说明: list.iterator(); iterator.remove();
     *
     */
    @Test
    public void demo4() {
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
    
    /**
     * 创建日期: 2019年5月28日 创建人: zhb 说明: 根据对象的某个属性过滤
     *
     */
    @Test
    public void demo5(){
        User user = new User(1,"张三","123", new Date(),1);
        User user2 = new User(2,"张三","456", new Date(),1);
        User user3 = new User(3,"李四","123", new Date(),1);
        List<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user2);
        list.add(user3);
        System.out.println(list);
        List<User> collect = list.stream().filter(item->{
            return !item.getUsername().equals("张三");
        }).collect(Collectors.toList());
        System.out.println(collect);
    }
    
    /**
     * 创建日期: 2019年6月26日 创建人: zhb 说明: 迭代测试
     *
     */
    @Test
    public void demo6(){
        String[] ss = {"java","mysql","js","java","java"};
        List<String> list = Arrays.asList(ss);
        Iterator<String> iterator = list.iterator();
        if(iterator.hasNext()){
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
    
    /**
     * 创建日期: 2019年6月26日 创建人: zhb 说明: 判断数据是否存在集合中
     *
     */
    @Test
    public void demo7(){
        Set<Long> set = new HashSet<Long>();
        set.add(12L);
        set.add(300L);
        set.add(90L);
        
        Set<Long> set2 = new HashSet<Long>();
        set2.add(12L);
        set2.add(300L);
        set2.add(20L);
        
        boolean b = set.contains(300L);
        System.out.println(b);
    }
    
    /**
     * 日期：2019年8月22日
     * 作者：zhb
     * 说明：向集合中添加对象，对象更改，集合中的元素是否改变
     * 
     */
    @Test
    public void demo08(){
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        user.setPassword("123");
        System.out.println("user -> "+user);
        List<User> list = new ArrayList<User>();
        list.add(user);
        System.out.println("list -> "+list);
        user.setUsername("李四");
        System.out.println("user2 -> " + user);
        System.out.println("list2 -> " + list);
        user = null;
        System.out.println("user3 -> " + user);
        System.out.println("list3 -> " + list);
    }
    
    /**
     * 日期：2019年4月22日 作者：zhb 说明：移除符合条件的元素
     * 
     */
    @Test
    public void demo09(){
        // 根据条件过滤
        List<Integer> filterNums = new ArrayList<>(Arrays.asList(1,3,5,7,9));
        // 移除符合条件的元素
        filterNums.removeIf(x -> x > 5);
//        filterNums.removeIf(new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer t) {
//                return t > 5;
//            }
//        });
        System.out.println(filterNums);
    }
    
    /**
     * 日期：2020年1月6日
     * 作者：zhb
     * 说明：求集合中的交集，并集，差集
     * 
     */
    @Test
    public void demo10(){
        List<Integer> a = new ArrayList<>(Arrays.asList(0,2,3,7,8,9));
        List<Integer> b = new ArrayList<>(Arrays.asList(0,1,2,3,5,6));
        // 并集
        List<Integer> union = CollectionUtils.union(a, b).stream().collect(Collectors.toList());
        System.out.println(union);
        // 交集
        List<Integer> intersection = CollectionUtils.intersection(a, b).stream().collect(Collectors.toList());
        System.out.println(intersection);
        // 差集
        List<Integer> disjunction = CollectionUtils.disjunction(a, b).stream().collect(Collectors.toList());
        System.out.println(disjunction);
    }
    
    /**
     * 日期：2020年1月7日
     * 作者：zhb
     * 说明：Collections
     * 
     */
    @Test
    public void demo11(){
        List<String> list = new ArrayList<>(Arrays.asList("java","sql","go","java"));
        // 判断指定元素是否存在于集合，存在返回第一次出现的位置；不存在返回-1
        System.out.println(Collections.binarySearch(list, "java"));
        
        // 判断是否有相同的元素，没有返回true，有返回false
        List<Integer> a = new ArrayList<>(Arrays.asList(0,2,3,7,8,9));
        List<Integer> b = new ArrayList<>(Arrays.asList(0,1,2,3,5,6));
        System.out.println(Collections.disjoint(a, b));
        
        // 判断指定元素在集合中出现的次数
        System.out.println(Collections.frequency(list, "java"));
        
        // 返回一个空列表（不可变的），返回的空列表是一个内部类创建的，不可调用add方法，调用报错
        List<String> emptyList = Collections.emptyList();
        System.out.println(CollectionUtils.isEmpty(emptyList));
    }
    
    
}
