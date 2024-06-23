package com.sp3.chapter16.common.schedulers.tigger;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Sp3TriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return "sampleTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        log.info("Trigger fired: {}", trigger.getKey());
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        log.info("Trigger veto job execution: {}", trigger.getKey());
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("Trigger misfired: {}", trigger.getKey());
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        log.info("Trigger complete: {}", trigger.getKey());
    }
}
