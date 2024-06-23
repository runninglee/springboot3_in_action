package com.sp3.chapter16.config;

import com.sp3.chapter16.common.schedulers.listener.Sp3JobListener;
import com.sp3.chapter16.common.schedulers.listener.Sp3TriggerListener;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Configuration;

@Configuration
class QuartzConfig {
    @Resource
    private Scheduler scheduler;

    @Resource
    private Sp3JobListener sp3JobListener;

    @Resource
    private Sp3TriggerListener sp3TriggerListener;

    @PostConstruct
    public void registerListeners() {
        try {
            scheduler.getListenerManager().addJobListener(sp3JobListener);
            scheduler.getListenerManager().addTriggerListener(sp3TriggerListener);
        } catch (SchedulerException e) {
            throw new RuntimeException("Failed to register trigger listener", e);
        }
    }
}
