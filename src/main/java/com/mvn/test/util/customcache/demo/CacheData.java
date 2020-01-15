package com.mvn.test.util.customcache.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CacheData implements Cache{
    
    private static Logger log = LoggerFactory.getLogger(CacheData.class);
    
    /**
     * 所有缓存数据
     */
    private static final Map<String, Entity> map = new ConcurrentHashMap<>();

    /**
     * 缓存数据
     */
    @Override
    public boolean set(String key, Object obj) {
        try {
            Entity entity = new Entity();
            entity.setData(obj);
            map.put(key, entity);
            return true;
        } catch (Exception e) {
            log.error("缓存数据异常：{}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 缓存数据，有期限
     */
    @Override
    public boolean set(String key, Object obj, long expired) {
        try {
            Entity entity = new Entity();
            entity.setData(obj);
            entity.setExpired(expired);
            map.put(key, entity);
            return true;
        } catch (Exception e) {
            log.error("缓存数据异常：{}", e.getMessage(), e);
        }
        return false;
    }

    /**
     * 获取缓存数据
     */
    @Override
    public Object get(String key) {
        if(!map.containsKey(key)){
            return null;
        }
        Entity entity = map.get(key);
        long expired = entity.getExpired();
        if(expired == -1L){
            return entity.getData();
        }
        long dataTime = entity.getDataTime();
        long nowTime = System.currentTimeMillis();
        if(nowTime - dataTime <= expired)
            return entity.getData();
        return null;
    }

    /**
     * 获取所有key
     */
    @Override
    public Set<String> getKeyAll(){
        return map.keySet();
    }
    
    /**
     * 获取所有缓存value
     */
    @Override
    public List<Object> getValueAll(){
        List<Object> list = new ArrayList<Object>();
        Collection<Entity> values = map.values();
        if(CollectionUtils.isNotEmpty(values)){
            values.forEach(v -> {
                list.add(v.getData());
            });
        }
        return list;
    }
    
    /**
     * 删除缓存数据
     */
    @Override
    public void del(String key){
        if(map.containsKey(key))
            map.remove(key);
    }
    
    /**
     * 判断是否过期
     */
    @Override
    public boolean expired(String key) {
        if(!map.containsKey(key))
            return true;
        Entity entity = map.get(key);
        long expired = entity.getExpired();
        if(expired == -1L)
            return false;
        long dataTime = entity.getDataTime();
        long nowTime = System.currentTimeMillis();
        if(nowTime - dataTime <= expired)
            return false;
        return true;
    }

    /**
     * 获取剩余缓存时间
     */
    @Override
    public long expiredTime(String key) {
        if(!map.containsKey(key))
            return 0;
        Entity entity = map.get(key);
        long expired = entity.getExpired();
        if(expired == -1L)
            return -1L;
        long dataTime = entity.getDataTime();
        long nowTime = System.currentTimeMillis();
        long interval = nowTime - dataTime;
        return interval <= expired ? interval : 0;
    }
    
    
}