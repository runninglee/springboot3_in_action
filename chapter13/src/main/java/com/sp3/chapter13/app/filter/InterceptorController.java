package com.sp3.chapter13.app.filter;

import com.sp3.chapter13.util.api.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/interceptor")
public class InterceptorController {

    @GetMapping("user/info")
    public ResultJson<Object> getUser() {
        return ResultJson.success("拦截器");
    }

    @GetMapping("user/login")
    public ResultJson<Object> login() {
        return ResultJson.unauthorized("拦截器，您未登录，请重新登录");
    }
}
