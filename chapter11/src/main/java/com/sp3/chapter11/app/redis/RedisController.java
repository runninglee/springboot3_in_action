package com.sp3.chapter11.app.redis;


import com.sp3.chapter11.util.api.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/redis")
public class RedisController {

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    public ResultJson<Object> index() {
        redisTemplate.opsForValue().set("sp3:user:" + Math.random(), "Hui Lee", 50, TimeUnit.SECONDS);
        return ResultJson.success(redisTemplate.opsForValue().get("user:1"));
    }
}
