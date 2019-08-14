package com.demo.optional;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;

import com.mvn.test.entity.User;

public class Demo {

    @Test
    public void demo(){
        User user = new User(1,"zhangsan","123",new Date(), 2);
        Optional.ofNullable(user).ifPresent(u->{
            System.out.println(u.getId());
        });
    }
    
    
}
