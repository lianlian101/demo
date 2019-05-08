package com.mvn.test.util.cache;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 
 * 创建时间: 2019年5月7日 创建人 zhb 说明: 监听缓存,项目启动成功后就开始监控
 *
 */
@Component
public class CacheListener implements ApplicationRunner {

    @Autowired
    private DataCache dataCache;
    
    private Set<String> keys;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //startListen();
        timer();
    }

    /**
     * 清除过期的数据 
     *
     */
    public void clearKey(){
        keys = dataCache.getKeyAll();
        keys.forEach(key -> {
            if (dataCache.isTimeOut(key)) {
                dataCache.clearByKey(key);
                System.out.println("清除缓存：" + key);
            }
        });
    }
    
//    /**
//     * 一直监控，cpu压力大，不推荐使用
//     *
//     */
//    public void startListen() {
//        // 开启一个线程监控缓存中所有的key，清除过期的key
//        new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    clearKey();
//                }
//            }
//        }.start();
//    }
    
    /**
     * 定时执行任务，cpu压力有所减小，但是当有异常时，定时器就结束了，需要人工干预再次启动
     *
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
        long period = 1000; // 时间间隔
        timer.scheduleAtFixedRate(task, delay, period);
    }
    

}
