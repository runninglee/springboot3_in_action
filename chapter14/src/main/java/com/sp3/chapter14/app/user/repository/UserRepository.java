package com.sp3.chapter14.app.user.repository;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    public Long getOrgId(Long id) {
        return id + 1;
    }

}
