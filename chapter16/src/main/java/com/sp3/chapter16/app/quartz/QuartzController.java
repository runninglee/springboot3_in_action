package com.sp3.chapter16.app.quartz;

import com.sp3.chapter16.util.api.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quartz")
public class QuartzController {

    @GetMapping
    public ResultJson<Object> index() {
        return ResultJson.success();
    }
}
