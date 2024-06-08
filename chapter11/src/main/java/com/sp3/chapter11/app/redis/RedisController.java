package com.sp3.chapter11.app.redis;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp3.chapter11.util.api.ResultJson;
import com.sp3.chapter11.util.redis.RedisUtil;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/redis")
@NoArgsConstructor
public class RedisController implements Serializable {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/{id}")
    @Cacheable(cacheNames = "cache", key = "#id")
    public ResultJson<Object> index(@PathVariable("id") long id) {
        try {
            redisUtil.set("sp3:user:1", "Spring Boot3 In Action", 60);
            redisUtil.hmset("sp3:user:2", Map.of("name", "HuiLee", "age", Math.random()), 120);
            redisUtil.sSetAndTime("sp3:user:3", 120, "Apple", "Samsung", "HuaWei");
            redisUtil.lSet("sp3:user:4", List.of("A", "B", "C", "B"), 180);
            Map<String, Object> data = Map.of(
                    "sp3:user:1",
                    redisUtil.get("sp3:user:1"),
                    "sp3:user:2",
                    redisUtil.hmget("sp3:user:2"),
                    "sp3:user:3",
                    redisUtil.sGet("sp3:user:3"),
                    "sp3:user:3:length",
                    redisUtil.sGetSetSize("sp3:user:3"),
                    "sp3:user:4:length",
                    redisUtil.lGetListSize("sp3:user:4"),
                    "sp3:user:sum",
                    redisUtil.search("sp3:user:*"),
                    "custom",
                    Map.of("Name", "AWALONG", "Age", 36)
            );
            return ResultJson.success(data);
        } catch (Exception e) {
            return ResultJson.failed();
        }
    }
}
