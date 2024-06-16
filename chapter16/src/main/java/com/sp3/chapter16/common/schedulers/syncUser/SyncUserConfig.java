package com.sp3.chapter16.common.schedulers.syncUser;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SyncUserConfig {
    @Bean
    public JobDetail syncUserJobDetail() {
        return JobBuilder.newJob(SyncUserJob.class)
                .withIdentity("syncUserJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger syncUserTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(30)
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(syncUserJobDetail())
                .withIdentity("syncUserTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public SchedulerFactoryBean syncUserSchedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobDetails(syncUserJobDetail());
        factory.setTriggers(syncUserTrigger());
        return factory;
    }


}
