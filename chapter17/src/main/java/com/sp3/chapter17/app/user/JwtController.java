package com.sp3.chapter17.app.user;

import com.sp3.chapter17.util.api.ResultJson;
import com.sp3.chapter17.util.jwt.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("jwt")
public class JwtController {

    @Resource
    private JwtUtil jwtUtil;

    @GetMapping("user/welcome")
    public ResultJson<Object> welcome() {
        return ResultJson.success();
    }


    @PostMapping("/user/login")
    public ResultJson<Object> login(HttpServletRequest request, HttpServletResponse response) {
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        if ("18937166730".equals(mobile) && "123456".equals(password)) {
            String token = jwtUtil.getToken("1");
            response.setHeader(jwtUtil.getHeader(), token);
            return ResultJson.success(Map.of("access_token", token, "expired_at", jwtUtil.getExpiredAt(), "type", jwtUtil.getTokenPrefix()));
        } else {
            return ResultJson.failed("账户或密码错误,请检查");
        }
    }

    @GetMapping("user/info")
    public ResultJson<Object> info(@RequestParam("token") String token) {
        return ResultJson.success(jwtUtil.getSubject(token));
    }

    @GetMapping("user/logout")
    public ResultJson<Object> logout(HttpSession session) {
        session.invalidate();
        return ResultJson.unauthorized("您已经退出登录");
    }


}
