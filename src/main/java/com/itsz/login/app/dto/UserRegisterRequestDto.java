package com.itsz.login.app.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public final class UserRegisterRequestDto {

    @NotBlank(message = "username not blank")
    private String username;

    @NotBlank(message = "email not blank")
    private String email;

    @NotBlank(message = "password not blank")
    private String password;

    @NotBlank(message = "passwordConfirm not blank")
    private String passwordConfirm;


}
