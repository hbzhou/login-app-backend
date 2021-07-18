package com.itsz.login.app.service.impl;

import com.itsz.login.app.dao.UserRepository;
import com.itsz.login.app.domain.User;
import com.itsz.login.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
