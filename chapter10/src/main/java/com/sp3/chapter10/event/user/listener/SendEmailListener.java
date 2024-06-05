package com.sp3.chapter10.event.user.listener;

import com.sp3.chapter10.app.user.entity.User;
import com.sp3.chapter10.event.user.CreateUserEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendEmailListener {

    @Async
    @Order(0)
    @EventListener
    public void sendEmail(CreateUserEvent event) {
        User user = event.getUser();
        //发邮件的逻辑
        log.info("给用户[{}]发邮件通知", user.getUsername());
    }
}