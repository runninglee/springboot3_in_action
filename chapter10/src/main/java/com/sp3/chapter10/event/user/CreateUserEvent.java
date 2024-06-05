package com.sp3.chapter10.event.user;

import com.sp3.chapter10.app.user.entity.User;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class CreateUserEvent extends ApplicationEvent {

    private  User user;

    public CreateUserEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public CreateUserEvent(Object source) {
        super(source);
    }
}
