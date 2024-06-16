package com.sp3.chapter15.console.job;

import com.sp3.chapter15.app.batch.entity.ApiLog;
import com.sp3.chapter15.app.batch.repository.ApiLogRepository;
import jakarta.annotation.Resource;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Configuration
@EnableBatchProcessing
public class ImportCsvConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Resource
    private ApiLogRepository apiLogRepository;

    @Autowired
    public ImportCsvConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job importCsvJob() {
        return new JobBuilder("import:csv", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(importCsvStep1())
                .next(importCsvStep2())
                .build();
    }

    @Bean
    public Step importCsvStep1() {
        return new StepBuilder("import:csv:step1", jobRepository)
                .tasklet(importCsvTasklet1(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet importCsvTasklet1() {
        return (contribution, chunkContext) -> {
            apiLogRepository.deleteAll();
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step importCsvStep2() {
        return new StepBuilder("import:csv:step2", jobRepository)
                .tasklet(importCsvTasklet2(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet importCsvTasklet2() {
        return (contribution, chunkContext) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //1、读取CSV
            ClassPathResource resource = new ClassPathResource("db/csv/api_logs.csv");
            Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            CSVParser csvParser = CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(true).build().parse(reader);
            ArrayList<ApiLog> apiLogList = new ArrayList<>();
            //2、解析CSV
            for (CSVRecord csvRecord : csvParser) {
                ApiLog apiLog = new ApiLog();
                apiLog.setUuid(csvRecord.get("uuid"));
                apiLog.setCategory(csvRecord.get("category"));
                apiLog.setTask(csvRecord.get("task"));
                apiLog.setName(csvRecord.get("name"));
                apiLog.setRel_type(csvRecord.get("rel_type"));
                apiLog.setRel_id(csvRecord.get("rel_id"));
                apiLog.setHost(csvRecord.get("host"));
                apiLog.setHeader(csvRecord.get("header").equals("NULL") ? null : csvRecord.get("header"));
                apiLog.setParam(csvRecord.get("param"));
                apiLog.setResponse(csvRecord.get("response"));
                apiLog.setCreated_at(LocalDateTime.parse(csvRecord.get("created_at"), formatter));
                apiLog.setUpdated_at(LocalDateTime.parse(csvRecord.get("updated_at"), formatter));
                apiLogList.add(apiLog);
            }
            //3、保存CSV
            apiLogRepository.saveAll(apiLogList);
            return RepeatStatus.FINISHED;
        };
    }
}