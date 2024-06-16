package com.sp3.chapter16.common.schedulers.syncUser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class SyncUserJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("执行同步用户任务...");
    }
}
