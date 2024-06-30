package com.sp3.chapter17.common.auth;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class UserContext {

    private static final String USER_KEY = "USER";

    public static void setUser(Object user) {
        RequestContextHolder.currentRequestAttributes().setAttribute(USER_KEY, user, RequestAttributes.SCOPE_REQUEST);
    }

    public static Object getUser() {
        return RequestContextHolder.currentRequestAttributes().getAttribute(USER_KEY, RequestAttributes.SCOPE_REQUEST);
    }

    public static void clear() {
        RequestContextHolder.currentRequestAttributes().removeAttribute(USER_KEY, RequestAttributes.SCOPE_REQUEST);
    }
}