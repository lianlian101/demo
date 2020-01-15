package com.mvn.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvn.test.util.customcache.demo.CacheData;

@Controller
@RequestMapping("/cache/")
public class CacheController {

    @Autowired
    private CacheData cacheData;
    
    /**
     * 设置缓存
     * 
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("set")
    @ResponseBody
    public Object set(String key, String value){
        try {
            cacheData.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cacheData.get(key);
    }
    
    /**
     * 设置缓存，有期限
     * @param key
     * @param value
     * @param time
     * @return
     */
    @RequestMapping("setExpired")
    @ResponseBody
    public Object set2(String key, String value, long time){
        try {
            cacheData.set(key, value, time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cacheData.get(key);
    }
    
    /**
     * 获取缓存
     * @param key
     * @return
     */
    @RequestMapping("get")
    @ResponseBody
    public Object set(String key){
        try {
            return cacheData.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "exception";
    }
    
    
}
