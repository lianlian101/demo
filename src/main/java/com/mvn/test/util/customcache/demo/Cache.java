package com.mvn.test.util.customcache.demo;

import java.util.List;
import java.util.Set;

public interface Cache {

    /**
     * 日期：2020年1月15日
     * 作者：zhb
     * 说明：缓存数据
     * 
     * @param key
     * @param obj
     * @return
     */
    boolean set(String key, Object obj);
    
    /**
     * 日期：2020年1月15日
     * 作者：zhb
     * 说明：缓存数据，有期限
     * 
     * @param key
     * @param obj
     * @param expired
     * @return
     */
    boolean set(String key, Object obj, long expired);
    
    /**
     * 日期：2020年1月15日
     * 作者：zhb
     * 说明：获取缓存数据
     * 
     * @param key
     * @return
     */
    Object get(String key);
    
    /**
     * 日期：2020年1月15日
     * 作者：zhb
     * 说明：获取所有缓存key
     * 
     * @return
     */
    Set<String> getKeyAll();
    
    /**
     * 日期：2020年1月15日
     * 作者：zhb
     * 说明：获取所有缓存value
     * 
     * @return
     */
    List<Object> getValueAll();
    
    /**
     * 日期：2020年1月15日
     * 作者：zhb
     * 说明：删除缓存数据
     * 
     * @param key
     */
    void del(String key);
    
    /**
     * 日期：2020年1月15日
     * 作者：zhb
     * 说明：判断是否过期
     * 
     * @param key
     * @return
     */
    boolean expired(String key);
    
    /**
     * 日期：2020年1月15日
     * 作者：zhb
     * 说明：获取剩余缓存时间
     * 
     * @param key
     * @return
     */
    long expiredTime(String key);
    
    
}
