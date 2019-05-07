package com.mvn.test.util.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 创建时间: 2019年5月7日 创建人 zhb 说明: 缓存接口
 *
 */
public interface IDataCache {
    
    /**
     * 存入缓存
     * @param key
     * @param cache
     */
    void putCache(String key, EntityCache cache);
   
    /**
     * 存入缓存
     * @param key
     * @param cache
     */
    void putCache(String key, Object datas, long timeOut);
   
    /**
     * 获取对应缓存
     * @param key
     * @return
     */
    EntityCache getCacheByKey(String key);
   
    /**
     * 获取对应缓存
     * @param key
     * @return
     */
    Object getCacheDataByKey(String key);
   
    /**
     * 获取所有缓存
     * @param key
     * @return
     */
    Map<String, EntityCache> getCacheAll();
    
    /**
     * 获取所有key
     * @return
     */
    Set<String> getKeyAll();
    
    /**
     * 获取所有value
     * 
     * @return
     */
    Collection<EntityCache> getValueAll();
   
    /**
     * 判断是否在缓存中
     * @param key
     * @return
     */
    boolean isContains(String key);
    
    /**
     * 缓存是否超时失效
     * @param key
     * @return
     */
    boolean isTimeOut(String key);
   
    /**
     * 获取指定key的剩余过期时间 
     * 
     * @param key
     * @return
     */
    long getCacheSurplusTimeOut(String key);
    
    /**
     * 清除对应缓存
     * @param key
     */
    void clearByKey(String key);
    
    /**
     * 清除所有缓存
     */
    void clearAll();
   
}
