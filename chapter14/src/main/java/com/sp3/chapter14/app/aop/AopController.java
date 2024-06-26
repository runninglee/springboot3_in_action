package com.sp3.chapter14.app.aop;

import com.sp3.chapter14.app.aop.request.UserQuery;
import com.sp3.chapter14.app.client.entity.Client;
import com.sp3.chapter14.app.source.entity.Source;
import com.sp3.chapter14.common.annotation.DataPermission;
import com.sp3.chapter14.common.annotation.HandlePermission;
import com.sp3.chapter14.util.api.ResultJson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/aop")
public class AopController {

    @GetMapping
    public ResultJson<Object> index() {
        return ResultJson.success();
    }

    @GetMapping("handle")
    @HandlePermission("s.create_source")
    public ResultJson<Object> handle() {
        return ResultJson.success();
    }

    @GetMapping("source-data")
    @DataPermission(value = "s.view_source", entity = Source.class, id = "#id")
    public ResultJson<Object> data(@RequestParam("id") String id) {
        return ResultJson.success(id);
    }

    @GetMapping("client-data")
    @DataPermission(value = "c.view_client", entity = Client.class, id = "#query.id")
    public ResultJson<Object> data2(UserQuery query) {
        return ResultJson.success(query);
    }
}
