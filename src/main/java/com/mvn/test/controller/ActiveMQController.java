//package com.mvn.test.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.mvn.test.util.activemq.Producer;
//
///**
// * 
// * 创建时间: 2019年5月10日 创建人 zhb 说明: 消息队列测试
// *
// */
//@Controller
//@RequestMapping("/activemq/")
//public class ActiveMQController {
//
//    @Autowired
//    private Producer producer;
//    
//    /**
//     * 创建日期: 2019年5月10日 创建人: zhb 说明: 发送消息
//     * 
//     * @return
//     */
//    @RequestMapping("send")
//    @ResponseBody
//    public String send(@RequestParam String v){
//        
//        //Destination mqQueue = new ActiveMQQueue("com.mvn.test");
//        
//        producer.sendMessage("你好");
//        
//        return "success";
//        
//    }
//    
//}
