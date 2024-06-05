package com.sp3.chapter10.event.user;

import com.sp3.chapter10.app.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class FilterUserEvent {

    public boolean filterUser(User user) {
        return user.getId() > 8;
    }

}
