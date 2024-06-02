package com.sp3.chapter9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Chapter9Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter9Application.class, args);
    }

}
