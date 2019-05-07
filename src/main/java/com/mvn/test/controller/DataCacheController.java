package com.mvn.test.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvn.test.util.cache.DataCache;

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
    public String setData(@RequestParam(required = false) String param){
        if(!StringUtils.isEmpty(param)){
            dataCache.putCache(param, param, 5*1000);
        }
        return "success";
    }
    
    /**
     * 创建日期: 2019年5月7日 创建人: zhb 说明: 获取缓存中的值
     * 
     * @return
     */
    @RequestMapping("getData")
    public Object getData(@RequestParam(required = false) String param){
        Object obj = null;
        if(!StringUtils.isEmpty(param)){
            obj = dataCache.getCacheDataByKey(param);
        }
        return obj;
    }
    
    /**
     * 创建日期: 2019年5月7日 创建人: zhb 说明: 获取过期时间
     * 
     * @return
     */
    @RequestMapping("getCacheSurplusTimeOut")
    public long getCacheSurplusTimeOut(@RequestParam(required = false) String param){
        return dataCache.getCacheSurplusTimeOut(param);
    }
    
}
