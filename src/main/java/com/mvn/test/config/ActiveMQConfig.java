package com.mvn.test.config;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * 
 * 创建时间: 2019年5月10日 创建人 zhb 说明: 可以不写这个配置类，直接则需要发送消息的方法new ActiveMQQueue("com.mvn.test")一个
 *
 */
//@Configuration
//@EnableJms
public class ActiveMQConfig {

    @Bean
    public Destination getDestination(){
        return new ActiveMQQueue("com.mvn.test");
    }
    
}
