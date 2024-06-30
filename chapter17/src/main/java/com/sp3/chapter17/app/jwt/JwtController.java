package com.sp3.chapter17.app.jwt;

//import com.sp3.chapter17.common.auth.UserService;

import com.sp3.chapter17.app.jwt.service.UserJwtService;
import com.sp3.chapter17.common.auth.UserContext;
import com.sp3.chapter17.util.api.ResultJson;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jwt")
public class JwtController {

    @Resource
    private UserJwtService userJwtService;

    @GetMapping("user/welcome")
    public ResultJson<Object> welcome() {
        return ResultJson.success(UserContext.getUser());
    }


    @PostMapping("/user/login")
    public ResultJson<Object> login(HttpServletRequest request, HttpServletResponse response) {
        return userJwtService.login(request, response);
    }

    @GetMapping("user/info")
    public ResultJson<Object> info() {
        return ResultJson.success(userJwtService.info());
    }

    @GetMapping("user/logout")
    public ResultJson<Object> logout(@RequestHeader("Authorization") String token) {
        userJwtService.logout(token);
        return ResultJson.unauthorized("您已经退出登录");
    }
}
