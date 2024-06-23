package com.sp3.chapter17.app.user.request;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String mobile;
    private String password;
}
