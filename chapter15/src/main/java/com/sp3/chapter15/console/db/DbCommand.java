package com.sp3.chapter15.console.db;

import com.sp3.chapter15.app.batch.entity.ApiLog;
import com.sp3.chapter15.app.batch.repository.ApiLogRepository;
import jakarta.annotation.Resource;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@ShellComponent
@ShellCommandGroup("db:")
public class DbCommand {

    @Resource
    private ApiLogRepository apiLogRepository;

    @ShellMethod(value = "导入CSV数据到数据表api_logs", key = "db:import-csv")
    public void importCsv() throws IOException {
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
    }
}
