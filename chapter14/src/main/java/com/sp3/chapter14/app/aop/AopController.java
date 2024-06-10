package com.sp3.chapter14.app.aop;

import com.sp3.chapter14.app.aop.entity.User;
import com.sp3.chapter14.app.aop.request.UserQuery;
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

    @GetMapping("data")
    @DataPermission(value = "s.view_source", entity = User.class, id = "#id")
    public ResultJson<Object> data(@RequestParam("id") long id) {
        return ResultJson.success(id);
    }

    @GetMapping("data2")
    @DataPermission(value = "s.view_source", entity = User.class, id = "#query.id")
    public ResultJson<Object> data2(UserQuery query) {
        return ResultJson.success(query);
    }
}
