package com.mvn.test.util.cache;

import java.util.Set;

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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        startListen();
    }

    /**
     * 清除过期的key
     *
     */
    public void startListen() {
        // 开启一个线程监控缓存中所有的key，清除过期的key
        new Thread() {
            @Override
            public void run() {
                Set<String> keys = null;
                while (true) {
                    keys = dataCache.getKeyAll();
                    keys.forEach(key -> {
                        if (dataCache.isTimeOut(key)) {
                            dataCache.clearByKey(key);
                            System.out.println("清除缓存：" + key);
                        }
                    });
                }
            }
        }.start();
    }

//    /**
//     * 定时执行任务
//     *
//     */
//    public void timer() {
//        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
//        scheduled.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("timer");
//                Set<String> keys = null;
//                while (true) {
//                    keys = dataCache.getKeyAll();
//                    keys.forEach(key -> {
//                        if (dataCache.isTimeOut(key)) {
//                            dataCache.clearByKey(key);
//                            System.out.println("清除缓存：" + key);
//                        }
//                    });
//                }
//
//            }
//        }, 0, 1000, TimeUnit.MILLISECONDS);
//    }

}
