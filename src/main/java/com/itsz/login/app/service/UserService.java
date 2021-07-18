package com.itsz.login.app.service;

import com.itsz.login.app.domain.User;

public interface UserService {
    User findUserByUserName(String username);
}
