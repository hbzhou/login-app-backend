package com.itsz.login.app.service;

import com.itsz.login.app.dto.UserRegisterRequestDto;


public interface UserRegisterService {

    void register(UserRegisterRequestDto userRegisterDto);
}
