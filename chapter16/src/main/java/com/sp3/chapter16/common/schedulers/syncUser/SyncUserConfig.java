package com.sp3.chapter16.common.schedulers.syncUser;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SyncUserConfig {
    @Bean
    public JobDetail syncUserJobDetail() {
        return JobBuilder.newJob(SyncUserJob.class)
                .withIdentity("syncUserJob")
                .storeDurably()
                .build();
    }


//    @Bean
//    public Trigger syncUserTrigger() {
//        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
//                .withIntervalInSeconds(30)
//                .repeatForever();
//        return TriggerBuilder.newTrigger()
//                .forJob(syncUserJobDetail())
//                .withIdentity("syncUserTrigger")
//                .withSchedule(scheduleBuilder)
//                .build();
//    }

//    @Bean
//    public Trigger syncUserTrigger() {
//        return TriggerBuilder.newTrigger()
//                .forJob(syncUserJobDetail())
//                .withIdentity("syncUserTrigger")
//                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
//                .build();
//    }

    @Bean
    public Trigger syncUserTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(syncUserJobDetail())
                .withIdentity("syncUserTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30).repeatForever())
                .build();
    }


}
