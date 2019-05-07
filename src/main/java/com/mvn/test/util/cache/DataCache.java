package com.mvn.test.util.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * 
 * 创建时间: 2019年5月7日 创建人 zhb 说明: 缓存实现类
 *
 */
@Component
public class DataCache implements IDataCache {

    /**
     * 存放缓存数据
     */
    private static ConcurrentHashMap<String, EntityCache> caches = new ConcurrentHashMap<String, EntityCache>();

    /**
     * 存入缓存
     */
    @Override
    public void putCache(String key, EntityCache cache) {
        if (cache != null) {
            if (cache.getTimeOut() != 0) {
                caches.put(key, cache);
            }
        }
    }

    /**
     * 存入缓存
     */
    @Override
    public void putCache(String key, Object datas, long timeOut) {
        if (timeOut == 0) {
            if (isContains(key)) {
                clearByKey(key);
            }
        } else {
            timeOut = timeOut > 0 ? timeOut : -1L;
            putCache(key, new EntityCache(datas, timeOut, System.currentTimeMillis()));
        }
    }

    /**
     * 获取对应缓存
     */
    @Override
    public EntityCache getCacheByKey(String key) {
        return caches.get(key);
    }

    /**
     * 获取对应缓存
     */
    @Override
    public Object getCacheDataByKey(String key) {
        if (isContains(key)) {
            return caches.get(key).getDatas();
        }
        return null;
    }

    /**
     * 获取所有缓存
     */
    @Override
    public Map<String, EntityCache> getCacheAll() {
        return CollectionUtils.sizeIsEmpty(caches) ? null : caches;
    }

    /**
     * 获取所有key
     */
    @Override
    public Set<String> getKeyAll() {
        return caches.keySet();
    }

    /**
     * 获取所有的value
     */
    @Override
    public Collection<EntityCache> getValueAll() {
        return caches.values();
    }

    /**
     * 判断是否在缓存中
     */
    @Override
    public boolean isContains(String key) {
        return caches.get(key) != null;
    }

    /**
     * 缓存是否超时失效
     */
    @Override
    public boolean isTimeOut(String key) {
        if (isContains(key)) {
            EntityCache cache = caches.get(key);
            long timeOut = cache.getTimeOut();
            long createTime = cache.getCreateTime();
            long interval = System.currentTimeMillis() - createTime;
            if (timeOut == -1L || interval < timeOut) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取指定key的剩余过期时间
     */
    @Override
    public long getCacheSurplusTimeOut(String key) {
        if (isContains(key)) {
            EntityCache cache = caches.get(key);
            long timeOut = cache.getTimeOut();
            if (timeOut == -1L) {
                return -1L;
            }
            long createTime = cache.getCreateTime();
            long interval = System.currentTimeMillis() - createTime;
            if (timeOut > interval) {
                return timeOut - interval;
            }
        }
        return -2L;
    }

    /**
     * 清除对应缓存
     */
    @Override
    public void clearByKey(String key) {
        if (isContains(key)) {
            caches.remove(key);
        }
    }

    /**
     * 清除所有缓存
     */
    @Override
    public void clearAll() {
        caches.clear();
    }

}
