package com.mvn.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan(basePackages = "com.mvn.test.dao")
@EnableConfigurationProperties
public class Application implements ApplicationRunner{
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 程序启动后执行指定方法
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^启动后执行的方法^^^^^^^^^^^^^^^^^^^^^");
    }

}
