package com.sp3.chapter14.app.user.service.impl;

import com.sp3.chapter14.app.user.entity.User;
import com.sp3.chapter14.app.user.repository.UserRepository;
import com.sp3.chapter14.app.user.service.UserService;
import jakarta.annotation.Resource;

public class UserServiceImpl implements UserService {


    @Resource
    private UserRepository userRepository;

    public Boolean getHandlePermission(String permission) {

        return true;
    }


    public Boolean getDataPermission(String permission, Class<?> entity, String id, String key) {
        return true;
    }


    public Boolean isAdmin(User user){
        return true;
    }


}
