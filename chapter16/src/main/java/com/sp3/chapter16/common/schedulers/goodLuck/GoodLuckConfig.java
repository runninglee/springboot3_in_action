package com.sp3.chapter16.common.schedulers.goodLuck;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoodLuckConfig {

    @Bean
    public JobDetail goodLuckJobDetail() {
        return JobBuilder.newJob(GoodLuckJob.class)
                .withIdentity("goodLuckJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger goodLuckTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(goodLuckJobDetail())
                .withIdentity("goodLuckTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 8 * * ?"))
                .build();
    }
}
