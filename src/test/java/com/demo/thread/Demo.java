package com.demo.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Demo {
    
    @Test
    public void countDownlatch_test() {
        try {
            
            CountDownLatch stopLatch = new CountDownLatch(3);

            for (int i = 0; i < 3; i ++) {
                new Thread(new Run(stopLatch)).start();
            }

            stopLatch.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Run implements Runnable {

        private CountDownLatch stopLatch;

        public Run(CountDownLatch countDownLatch) {
            this.stopLatch = countDownLatch;
        }

        @Override
        public void run() {
            
            stopLatch.countDown();
            try {
                stopLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println(Thread.currentThread().getName() + " Handler...");
            
        }
    }
    
    @Test
    public void cyclicBarrier_test(){
        try {
            
            CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
            
            for (int i = 0; i < 3; i++) {
                new Thread(new Run2(cyclicBarrier)).start();
            }
            
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            System.out.println("运行结束");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static class Run2 implements Runnable {

        private CyclicBarrier cyclicBarrier;
        
        public Run2(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(3);
                
                cyclicBarrier.await();
                
                System.out.println(Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

}
