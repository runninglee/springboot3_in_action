package com.sp3.chapter9.app.user.service;

import com.sp3.chapter9.app.user.entity.User;
import com.sp3.chapter9.app.user.repository.UserRepository;
import com.sp3.chapter9.app.user.request.CreateUserRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl {

    @Resource
    UserRepository userRepository;

    @Resource
    private ModelMapper modelMapper;


    @Transactional
    @Modifying
    public void store(CreateUserRequest request) {
        userRepository.save(modelMapper.map(request, User.class));
    }
}
