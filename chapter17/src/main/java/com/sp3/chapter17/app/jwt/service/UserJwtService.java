package com.sp3.chapter17.app.jwt.service;

import com.sp3.chapter17.common.auth.JwtBlacklist;
import com.sp3.chapter17.common.auth.UserContext;
import com.sp3.chapter17.util.api.ResultJson;
import com.sp3.chapter17.util.jwt.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserJwtService {

    @Resource
    private JwtUtil jwtUtil;

    public ResultJson<Object> login(HttpServletRequest request, HttpServletResponse response) {
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        if ("18812345678".equals(mobile) && "123456".equals(password)) {
            String token = jwtUtil.getToken("1");
            response.setHeader(jwtUtil.getHeader(), token);
            UserContext.setUser(Map.of("id", 1, "username", "HuiLee", "mobile", "18812345678"));
            return ResultJson.success(Map.of("access_token", token, "expired_at", jwtUtil.getExpiredAt(), "type", jwtUtil.getTokenPrefix()));
        } else {
            return ResultJson.failed("账户或密码错误,请检查");
        }
    }

    public Object info() {
        return UserContext.getUser();
    }

    public void logout(String token) {
        JwtBlacklist.add(token.replace(jwtUtil.getTokenPrefix() + " ", ""));
        UserContext.clear();
    }
}
