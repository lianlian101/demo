package com.mvn.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvn.test.util.customcache.DataCache;

@RestController
@RequestMapping("/cache/")
public class DataCacheController {

    @Autowired
    private DataCache dataCache;
    
    /**
     * 创建日期: 2019年5月7日 创建人: zhb 说明: 设置缓存
     * 
     * @return
     */
    @RequestMapping("setData")
    public String setData(@RequestParam String k, @RequestParam String v){
        try {
            dataCache.putCache(k, v, 10*1000);
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
        return "成功";
    }
    
    /**
     * 创建日期: 2019年5月7日 创建人: zhb 说明: 获取缓存中的值
     * 
     * @return
     */
    @RequestMapping("getData")
    public Object getData(@RequestParam String k){
        Object obj = null;
        try {
            obj = dataCache.getCacheDataByKey(k);
        } catch (Exception e) {
            e.printStackTrace();
            return "异常";
        }
        return obj;
    }
    
    /**
     * 创建日期: 2019年5月7日 创建人: zhb 说明: 获取过期时间
     * 
     * @return
     */
    @RequestMapping("getCacheSurplusTimeOut")
    public long getCacheSurplusTimeOut(@RequestParam String k){
        return dataCache.getCacheSurplusTimeOut(k);
    }
    
}
