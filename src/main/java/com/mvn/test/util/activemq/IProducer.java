package com.mvn.test.util.activemq;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQTextMessage;

/**
 * 
 * 创建时间: 2019年5月10日 创建人 zhb 说明: 消息生产者
 *
 */
public interface IProducer {

    /**
     * 创建日期: 2019年5月10日 创建人: zhb 说明: 发送消息
     * 
     * @param obj 待发送的消息
     */
    public void sendMessage(Object obj);
    
    /**
     * 创建日期: 2019年5月10日 创建人: zhb 说明: 发送消息
     * 
     * @param destination 发送到的队列
     * @param obj 待发送的消息
     */
    public void sendMessage(Destination destination, Object obj);
    
    /**
     * 创建日期: 2019年5月10日 创建人: zhb 说明: 接收消息
     * 
     * @param obj 接收到的消息
     */
    public void receiveMessage(ActiveMQTextMessage amtm);
    
}
