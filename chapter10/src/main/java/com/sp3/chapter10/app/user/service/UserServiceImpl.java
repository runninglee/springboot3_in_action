package com.sp3.chapter10.app.user.service;


import com.sp3.chapter10.app.user.entity.User;
import com.sp3.chapter10.app.user.repository.UserRepository;
import com.sp3.chapter10.app.user.request.CreateUserRequest;
import com.sp3.chapter10.event.user.CreateUserEvent;
import com.sp3.chapter10.event.user.CreateWechatUserEvent;
import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl {

    @Resource
    private ModelMapper modelMapper;

    @Resource
    UserRepository userRepository;

    @Resource
    private ApplicationContext applicationContext;

    @Transactional
    @Modifying
    public void store(CreateUserRequest request) {
        User user = userRepository.save(modelMapper.map(request, User.class));
        applicationContext.publishEvent(new CreateWechatUserEvent(this, user));
    }

}
