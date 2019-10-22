//package com.mvn.test.util.timer;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//
//import com.mvn.test.dao.UserDao;
//
//// 主要用于标记配置类，兼备Component的效果。
//@Configuration
//// 开启定时任务
//@EnableScheduling
//public class ScheduleTaskMapper implements SchedulingConfigurer {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(
//                // 添加任务内容(Runnable)
//                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now() + ", userDao -> " + userDao),
//                // 设置执行周期(Trigger)
//                triggerContext -> {
//                    // 返回执行周期(Date)
//                    return new CronTrigger("0/5 * * * * ?").nextExecutionTime(triggerContext);
//                });
//    }
//
//    
//}
