package com.sp3.chapter16.common.schedulers.syncUser;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SyncUserJob implements Job {

    @Bean
    public JobDetail syncUserJobDetail() {
        return JobBuilder.newJob(SyncUserJob.class)
                .withIdentity("syncUserJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger syncUserTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(syncUserJobDetail())
                .withIdentity("syncUserTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("执行同步用户任务...");
    }
}
