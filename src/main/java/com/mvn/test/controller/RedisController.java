//package com.mvn.test.controller;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mvn.test.entity.User;
//import com.mvn.test.util.cache.RedisServer;
//
///**
// * 
// * 创建时间: 2019年5月9日 创建人 zhb 说明: redis缓存测试
// *
// */
//@RestController
//@RequestMapping("/redis/")
//public class RedisController {
//
//    @Autowired
//    private RedisServer redisServer;
//    
//    /**
//     * 创建日期: 2019年5月9日 创建人: zhb 说明: 设置缓存
//     * 
//     * @param key
//     * @param value
//     * @return
//     */
//    @RequestMapping("set")
//    public Object set(@RequestParam String key, @RequestParam String value){
//        redisServer.set(key, value);
//        return redisServer.get(key);
//    }
//    
//    /**
//     * 创建日期: 2019年5月9日 创建人: zhb 说明: 获取缓存
//     * 
//     * @param key
//     * @return
//     */
//    @RequestMapping("get")
//    public Object get(@RequestParam String key){
//        return redisServer.get(key);
//    }
//    
//    /**
//     * 创建日期: 2019年5月9日 创建人: zhb 说明: 设置缓存，值为对象
//     * 
//     * @param key
//     * @param value
//     * @return
//     */
//    @RequestMapping("setObj")
//    public User setObj(){
//        User user = new User();
//        user.setId(1);
//        user.setUsername("哈哈哈");
//        user.setPassword("123");
//        user.setCreateTime(new Date());
//        redisServer.set("user", user);
//        return redisServer.get("user", User.class);
//    }
//    
//    /**
//     * 创建日期: 2019年5月9日 创建人: zhb 说明: 获取缓存，值为对象
//     * 
//     * @param key
//     * @return
//     */
//    @RequestMapping("getObj")
//    public Object getObj(){
//        return redisServer.get("user", User.class);
//    }
//    
//}
