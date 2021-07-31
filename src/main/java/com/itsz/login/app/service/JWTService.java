package com.itsz.login.app.service;

import org.springframework.security.core.Authentication;

public interface JWTService {

    String generateToken(Authentication authentication);

    boolean validateToken(String token);

    String getUsernameByToken(String token);
}


