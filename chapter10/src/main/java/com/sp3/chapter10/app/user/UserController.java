package com.sp3.chapter10.app.user;

import com.sp3.chapter10.app.user.request.CreateUserRequest;
import com.sp3.chapter10.app.user.service.UserServiceImpl;
import com.sp3.chapter10.util.api.ResultJson;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {


    @Resource
    private UserServiceImpl userService;

    @PostMapping
    public ResultJson<Object> store(@Validated CreateUserRequest request) {
        userService.store(request);
        return ResultJson.success(request);
    }
}
