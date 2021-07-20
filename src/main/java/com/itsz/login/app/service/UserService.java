package com.itsz.login.app.service;

import com.itsz.login.app.domain.User;
import com.itsz.login.app.dto.UserRegisterRequestDto;

public interface UserService {
    User findUserByUserName(String username);

    void register(UserRegisterRequestDto userRegisterDto);
}
