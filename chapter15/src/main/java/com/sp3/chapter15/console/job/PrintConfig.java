package com.sp3.chapter15.console.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableBatchProcessing
public class PrintConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public PrintConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job printJob() {
        return new JobBuilder("print1", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(printStep1())
                .build();
    }

    @Bean
    public Step printStep1() {
        return new StepBuilder("print:step:1", jobRepository)
                .tasklet(printStep1Tasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet printStep1Tasklet() {
        AtomicInteger count = new AtomicInteger();
        return (contribution, chunkContext) -> {
            count.getAndIncrement();
            System.out.println("Print Spring Batch!");
            System.out.println(count);
            if (count.get() < 5) {
                return RepeatStatus.CONTINUABLE;
            } else {
                return RepeatStatus.FINISHED;
            }
        };
    }
}