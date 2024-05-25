package com.sp3.chapter5.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "teacher")
@PropertySource(value = "classpath:/config/teacher.properties", encoding = "utf-8")
@Data
public class TeacherPropertyConfig {
    private String name;
    private String course;
    private Map<String, String> weekday;
    private List<HashMap<String, String>> students;
}
