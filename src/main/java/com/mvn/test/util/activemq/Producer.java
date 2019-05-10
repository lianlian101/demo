package com.mvn.test.util.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * 创建时间: 2019年5月10日 创建人 zhb 说明: 消息生产者
 *
 */
//@Component
public class Producer implements IProducer{

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    
    @Autowired
    private Destination destination;
    
    /**
     * 发送消息
     */
    @Override
    public void sendMessage(Object obj){
        jmsMessagingTemplate.convertAndSend(destination, obj);
    }
    
    /**
     * 发送消息
     */
    @Override
    public void sendMessage(Destination destination, Object obj) {
        jmsMessagingTemplate.convertAndSend(destination, obj);
    }
    
    /**
     * 接收消息 
     */
    @Override
    @JmsListener(destination = "com.mvn.test")
    public void receiveMessage(ActiveMQTextMessage amtm){
        try {
            System.out.println("producer接收到的消息：" + amtm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
