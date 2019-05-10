package com.mvn.test.util.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 
 * 创建时间: 2019年5月10日 创建人 zhb 说明: 消息消费者
 *
 */
//@Component
public class Consumer {
    
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "com.mvn.test")
    @SendTo("com.mvn.test") // 将返回值发送到com.mvn.test对列中
    public String receiveQueue(String text) {
        System.out.println("Consumer收到的消息为：" + text);
        return "来自consumer的消息：" + text;
    }
    
}
