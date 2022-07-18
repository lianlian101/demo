package com.demo.obj2class;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Demo {
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class User {
        private int id;
        private String name;
        private String addr;
    }

    private static <T> List<T> objToList(Object obj, Class<T> cla) {
        if (obj instanceof List<?>) {
            List<?> objs = (List<?>) obj;
            List<T> list = new ArrayList<T>(objs.size());
            for (Object o : objs) {
                list.add(cla.cast(o));
            }
            return list;
        }
        return null;
    }
    
    /**
     * 对象转集合
     * 
     * @date 2021-04-02
     * @author zhb
     *
     */
    @Test
    public void objToList() {
        List<User> users = Lists.newArrayList(
                User.builder().id(1).name("张三").addr("北京").build(),
                User.builder().id(2).name("李四").addr("北京").build(),
                User.builder().id(3).name("王五").addr("北京").build());
        Object obj = new Object();
        obj = users;
        
        List<User> list = objToList(obj, User.class);
        System.out.println(list);
    }

}
