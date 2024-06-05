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
public class SendSpELListener {

    @Async
    @Order(3)
    @EventListener(condition = "#event.user.id > 5")
    public void send(CreateUserEvent event) {
        User user = event.getUser();
        //SpEL条件发站内信的逻辑
        log.info("SpEL条件模式给用户[{}]发站内信通知", user.getUsername());
    }
}
