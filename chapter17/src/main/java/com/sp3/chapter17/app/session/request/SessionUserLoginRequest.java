package com.sp3.chapter17.app.session.request;

import lombok.Data;

@Data
class SessionUserLoginRequest {
    private String mobile;
    private String password;
}
