package com.itsz.login.app.dto;

import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
public class UserLoginResponseDto {

    private String token;

    private Authentication authentication;
}
