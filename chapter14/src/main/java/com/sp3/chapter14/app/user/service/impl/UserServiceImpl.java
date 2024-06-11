package com.sp3.chapter14.app.user.service.impl;

import com.sp3.chapter14.app.client.entity.Client;
import com.sp3.chapter14.app.user.entity.User;
import com.sp3.chapter14.app.user.repository.UserRepository;
import com.sp3.chapter14.app.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    public Boolean getHandlePermission(String permission) {
        User currentUser = new User(2L);
        if (isAdmin(currentUser)) {
            return true;
        } else {
            return permission.equals("s.create_source");
        }
    }

    public Boolean getDataPermission(String permission, Class<?> entity, String id, String key) {

        System.out.println(id);
        User currentUser = new User(2L);
        if (isAdmin(currentUser)) {
            return true;
        } else {
            if (entity == Client.class) {
                return permission.equals("s.view_source") && id.equals("1");
            } else {
                return false;
            }
        }
    }


    public Boolean isAdmin(User user) {
        return user.getId() == 1;
    }
}
