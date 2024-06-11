package com.sp3.chapter14.app.user.service.impl;


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
        User currentUser = new User(2L, 1L, true);
        if (isAdmin(currentUser)) {
            return true;
        } else {
            return permission.equals("s.create_source");
        }
    }

    public Boolean getDataPermission(String permission, Class<?> entity, String id, String key) {
        User currentUser = new User(2L, 1L, false);
        if (isAdmin(currentUser)) {
            return true;
        } else {
            //开放Source 实例 id=1 的数据访问
            String tableName = entity.getSimpleName().toLowerCase() + 's';
            if (tableName.equals("sources")) {
                return permission.equals("s.view_source") && id.equals("1");
                //开放Client 实例 id=2 的数据访问
            } else if (tableName.equals("clients")) {
                return permission.equals("c.view_client") && id.equals("2");
            } else {
                return false;
            }
        }
    }


    public Boolean isAdmin(User user) {
        return user.getIsAdmin();
    }
}
