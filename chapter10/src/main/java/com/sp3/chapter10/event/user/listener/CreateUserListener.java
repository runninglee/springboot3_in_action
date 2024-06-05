package com.sp3.chapter10.event.user.listener;

import com.sp3.chapter10.app.user.entity.User;
import com.sp3.chapter10.event.user.CreateUserEvent;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CreateUserListener {

    @EventListener
    public void createUser(CreateUserEvent event) {
        User user = event.getUser();
        //发邮件的逻辑
        log.info("给微信用户[{}]发邮件通知", user.getUsername());
    }

}
