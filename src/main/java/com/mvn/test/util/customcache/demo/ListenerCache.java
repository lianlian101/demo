package com.mvn.test.util.customcache.demo;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ListenerCache implements ApplicationRunner{
    
    @Autowired
    private CacheData cacheData;

    @Override
    public void run(ApplicationArguments args) throws Exception {
            timer();
    }
    
    /**
     * 定时器
     */
    public void timer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                clearKey();
            }
        };
        Timer timer = new Timer();
        long delay = 0; // 任务执行前延迟时间
        long period = 3600000; // 时间间隔，一小时
        timer.scheduleAtFixedRate(task, delay, period);
    }
    
    /**
     * 清除过期的key
     */
    private void clearKey(){
        Set<String> set = cacheData.getKeyAll();
        if(CollectionUtils.isNotEmpty(set)){
            set.forEach(key->{
                if(cacheData.expired(key)){
                    cacheData.del(key);
                }
            });
        }
    }
    

}
