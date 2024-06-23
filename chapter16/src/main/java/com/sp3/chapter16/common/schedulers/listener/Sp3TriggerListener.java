package com.sp3.chapter16.common.schedulers.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sp3TriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return "speTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        log.info("触发器任务未执行: {}", trigger.getKey());
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        log.info("触发器任务即将执行: {}", trigger.getKey());
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("触发器错失执行: {}", trigger.getKey());
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        log.info("触发器任务执行完毕: {}", trigger.getKey());
    }
}
