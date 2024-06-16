package com.sp3.chapter4.app.api;

import com.sp3.chapter4.util.api.exception.GraceException;
import com.sp3.chapter4.util.api.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResultController {

    @GetMapping("success")
    public ResultJson<String> success() {
        return ResultJson.success("请求成功");
    }

    @GetMapping("failed")
    public ResultJson<String> failed() {
        return ResultJson.failed("请求失败");
    }

    @GetMapping("validate")
    public ResultJson<String> validateFailed() {
        return ResultJson.validateFailed("身份证格式不正确");
    }

    @GetMapping("unauthorized")
    public ResultJson<String> unauthorized() {
        return ResultJson.unauthorized("对不起，您已经登出，请重新登录");
    }

    @GetMapping("forbidden")
    public ResultJson<String> forbidden() {
        return ResultJson.forbidden("对不起，您无访问权限");
    }

    @GetMapping("exception")
    public void exception() {
        GraceException.display("我这里抛出了一个异常");
    }
}
