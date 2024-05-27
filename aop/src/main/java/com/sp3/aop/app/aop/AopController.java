package com.sp3.aop.app.aop;

import com.sp3.aop.annotation.DataPermission;
import com.sp3.aop.annotation.HandlePermission;
import com.sp3.aop.app.aop.entity.AopEntity;
import com.sp3.aop.app.aop.request.CreateAopRequest;
import com.sp3.aop.util.api.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/aop")
public class AopController {

    @GetMapping("/handler")
    @HandlePermission("s.create_source")
    public ResultJson<Object> handler() {
        return ResultJson.success();
    }


    @GetMapping("/data")
    @DataPermission(value = "s.create_source", entity = AopEntity.class, id = "id")
    public ResultJson<Object> data(CreateAopRequest request) {
        return ResultJson.success();
    }
}
