package com.sp3.chapter11.app.redis;


import com.sp3.chapter11.util.api.ResultJson;
import com.sp3.chapter11.util.redis.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/redis")
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping
    public ResultJson<Object> index() {
        redisUtil.hmset("sp3:user:1", Map.of("name", "HuiLee", "age", Math.random()));
        return ResultJson.success(redisUtil.hmget("sp3:user:1"));
    }
}
