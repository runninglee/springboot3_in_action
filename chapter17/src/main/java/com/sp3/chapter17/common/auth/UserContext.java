package com.sp3.chapter17.common.auth;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class UserContext {
    private HttpServletRequest request;

    public UserContext(HttpServletRequest request) {
        this.request = request;
    }

    public Claims getCurrentUser() {
        return (Claims) request.getAttribute("user");
    }
}
