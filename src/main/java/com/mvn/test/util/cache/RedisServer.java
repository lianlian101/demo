package com.mvn.test.util.cache;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * 创建时间: 2019年5月9日 创建人 zhb 说明: 缓存工具类
 *
 */
//@Component
public class RedisServer {

    // @Autowired
    // private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // ==================================================String=================================================

    /**
     * 获取缓存
     * 
     * @param key
     * @return Object
     */
    public Object get(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存 注：直接转为对象
     * 
     * @param key
     * @param clazz
     * @return clazz
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        return (T) redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存
     * 
     * @param key
     * @param retain
     *            是否保留
     * @return Object
     */
    public Object get(String key, boolean retain) {
        Object obj = redisTemplate.boundValueOps(key).get();
        if (!retain) {
            redisTemplate.delete(key);
        }
        return obj;
    }

    /**
     * 将value写入缓存
     * 
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 将value写入缓存
     * 
     * @param key
     * @param value
     * @param time
     *            失效时间（毫秒）
     * 
     */
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value);
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 写入缓存，有效期一天
     * 
     * @param key
     * @param value
     */
    public void setExpireOneDay(String key, Object value) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(now.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.DATE, 1);// 前一天
        long time = cal.getTimeInMillis() - now.getTimeInMillis();
        set(key, value, time);
    }
    
    // ============================================================hash==============================================

    /**
     * 获取hash
     * 
     * @param key
     * @param clazz
     * @return
     */
    public <T> Map<String, T> hmGet(String key, Class<T> clazz) {
        BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.entries();
    }

    /**
     * 获取hash
     * 
     * @param key
     * @param hashKey
     * @return
     */
    public <T> Object hmGet(String key, T hashKey) {
        HashOperations<String, Object, T> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * 获取hash中hkey对应的hvalue
     * 
     * @param key
     * @param hkey
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T hmGetForHkey(String key, String hkey, Class<T> clazz) {
        return (T) redisTemplate.boundHashOps(key).get(hkey);
    }

    /**
     * 缓存hash
     * 
     * @param key
     * @param map
     */
    public <T> void hmSet(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向key对应的map中添加缓存对象
     * 
     * @param key
     *            cache对象key
     * @param field
     *            map对应的hkey
     * @param obj
     *            map对应的hvalue
     */
    public <T> void hmSet(String key, String hkey, T obj) {
        redisTemplate.opsForHash().put(key, hkey, obj);
    }

    // ============================================================list==============================================

    /**
     * 添加list
     * 
     * @param k
     * @param v
     */
    public void lrightPush(String k, Object v) {
        redisTemplate.opsForList().rightPush(k, v);
    }

    /**
     * 获取list
     * 
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lrange(String k, long start, long end) {
        return redisTemplate.opsForList().range(k, start, end);
    }

    // ============================================================set==============================================

    /**
     * 添加set
     * 
     * @param key
     * @param value
     */
    public void sset(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 添加set
     * 
     * @param key
     * @param value
     */
    public void sset(String key, String... value) {
        redisTemplate.boundSetOps(key).add(value);
    }

    /**
     * 获取set
     * 
     * @param key
     * @return
     */
    public Set<Object> sgetMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 判断 set 集合里是否包含当前这个 key
     * 
     * @param setKey
     * @param key
     */
    public Boolean sexist(String setKey, String key) {
        return redisTemplate.boundSetOps(setKey).isMember(key);
    }

    /**
     * set重命名
     * 
     * @param oldkey
     * @param newkey
     */
    public void srename(String oldkey, String newkey) {
        redisTemplate.boundSetOps(oldkey).rename(newkey);
    }

    // ============================================================zset==============================================

    /**
     * 有序集合获取
     * 
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 有序集合添加
     * 
     * @param key
     * @param value
     * @param scoure
     */
    public void zadd(String key, Object value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    // ==========================================================其他操作==================================================

    /**
     * 判断缓存中是否有对应的value
     * 
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 指定缓存的失效时间
     *
     * @param key
     *            缓存key
     * @param timeout
     *            失效时间（秒）
     */
    public void expire(String key, int timeout) {
        if (timeout > 0) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }
    
    /**
     *  指定缓存的失效时间
     * 
     * @param key 缓存key
     * @param timeout  失效时间（毫秒）
     */
    public void expire(String key, long timeout){
        if(timeout > 0){
            redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 递减操作
     * 
     * @param key
     * @param by
     * @return
     */
    public double decr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, -by);
    }

    /**
     * 递增操作
     * 
     * @param key
     * @param by
     * @return
     */
    public double incr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, by);
    }

    /**
     * 删除缓存 根据key精确匹配删除
     * 
     * @param key
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    /**
     * 批量删除 （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
     * 
     * @param pattern
     */
    public void batchDel(String... pattern) {
        for (String kp : pattern) {
            // redisTemplate.delete(redisTemplate.keys(kp + "*"));
            redisTemplate.delete(kp);
        }
    }

    /**
     * 删除map中的某个对象
     * 
     * @param key
     *            map对应的key
     * @param field
     *            map中该对象的key
     */
    public void mdelForHkey(String key, String... hkey) {
        BoundHashOperations<String, String, ?> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.delete(hkey);
    }

    /**
     * 删除set集合中的对象
     * 
     * @param key
     * @param value
     */
    public void sdel(String key, String... value) {
        redisTemplate.boundSetOps(key).remove(value);
    }

    /**
     * 模糊查询keys
     * 
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

}
