package com.sp3.chapter16.config;

import com.sp3.chapter16.common.schedulers.tigger.Sp3TriggerListener;
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
    private Sp3TriggerListener sp3TriggerListener;

    
    @PostConstruct
    public void registerListeners() {
        try {
            scheduler.getListenerManager().addTriggerListener(sp3TriggerListener);
        } catch (SchedulerException e) {
            throw new RuntimeException("Failed to register trigger listener", e);
        }
    }
}
