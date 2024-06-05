package com.sp3.chapter10.event.user;

import com.sp3.chapter10.app.user.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class CreateWechatUserEvent extends CreateUserEvent {
    
    private final User user;

    public CreateWechatUserEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

}
