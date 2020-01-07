package com.demo.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.Test;

public class QueueTest {
    
    /**
     * 日期：2020年1月6日
     * 作者：zhb
     * 说明：并行队列
     * 
     */
    @Test
    public void demo1(){
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        queue.offer("张三");
        queue.offer("李四");
        queue.offer("王五");
        queue.offer("赵六");
        
        String poll = queue.poll();
        System.out.println("poll -> " + poll + ", " + queue);
        String peek = queue.peek();
        System.out.println("peek -> " + peek + ", " + queue);
    }

}
