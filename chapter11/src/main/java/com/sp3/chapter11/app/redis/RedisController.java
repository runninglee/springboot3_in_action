package com.sp3.chapter11.app.redis;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp3.chapter11.util.api.ResultJson;
import com.sp3.chapter11.util.redis.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/redis")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/{id}")
    @Cacheable(value = "sp3:cache", key = "#id")
    public ResultJson<Object> index(@PathVariable("id") String id) {
        try {
            redisUtil.set("sp3:user:1", "Spring Boot3 In Action", 60);
            redisUtil.hmset("sp3:user:2", Map.of("name", "HuiLee", "age", Math.random()), 120);
            redisUtil.sSetAndTime("sp3:user:3", 120, "Apple", "Samsung", "HuaWei");
            redisUtil.lSet("sp3:user:4", List.of("A", "B", "C", "B"), 180);
            return ResultJson.success(new HashMap<>() {{
                put("sp3:user:1", redisUtil.get("sp3:user:1"));
                put("sp3:user:2", redisUtil.hmget("sp3:user:2"));
                put("sp3:user:3", redisUtil.sGet("sp3:user:3"));
                put("sp3:user:3的长度是", redisUtil.sGetSetSize("sp3:user:3"));
                put("sp3:user:4的长度是", redisUtil.lGetListSize("sp3:user:4"));
                put("总数", redisUtil.search("sp3:user:*"));
            }});
        } catch (Exception e) {
            return ResultJson.failed();
        }
    }
}
