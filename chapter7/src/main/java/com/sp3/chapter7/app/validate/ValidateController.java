package com.sp3.chapter7.app.validate;

import com.sp3.chapter7.app.validate.request.CreateUserRequest;
import com.sp3.chapter7.app.validate.request.DeleteUserRequest;
import com.sp3.chapter7.app.validate.request.UpdateUserRequest;
import com.sp3.chapter7.app.validate.request.UserQuery;
import com.sp3.chapter7.app.validate.request.group.CreateUserGroup;
import com.sp3.chapter7.app.validate.request.group.UpdateUserGroup;
import com.sp3.chapter7.util.api.ResultJson;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/validate")
public class ValidateController {

    @GetMapping
    public ResultJson<Object> index(@Validated UserQuery query) {
        return ResultJson.success(query);
    }

    @PostMapping
    public ResultJson<Object> store(@Validated(CreateUserGroup.class) CreateUserRequest request) {
        return ResultJson.success(request);
    }

    @PutMapping("/{id}")
    public ResultJson<Object> update(@Validated(UpdateUserGroup.class) UpdateUserRequest request) {
        return ResultJson.success(request);
    }

    @DeleteMapping("/{id}")
    public ResultJson<Object> destroy(@Validated DeleteUserRequest request) {
        return ResultJson.success(request);
    }
}
