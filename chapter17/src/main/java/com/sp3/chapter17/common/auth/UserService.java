package com.sp3.chapter17.common.auth;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserContext userContext;

    public UserService(UserContext userContext) {
        this.userContext = userContext;
    }

    public Claims getCurrentUser() {
        return this.userContext.getCurrentUser();
    }
}
