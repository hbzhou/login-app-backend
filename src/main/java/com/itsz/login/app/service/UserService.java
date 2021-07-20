package com.itsz.login.app.service;

import com.itsz.login.app.domain.User;
import com.itsz.login.app.dto.UserRegisterRequestDto;
import com.itsz.login.app.view.UserView;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findUserByUserName(String username);

    void register(UserRegisterRequestDto userRegisterDto);

    List<UserView> findAll();
}
