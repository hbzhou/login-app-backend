package com.itsz.login.app.controller;


import com.itsz.login.app.domain.User;
import com.itsz.login.app.dto.UserRegisterRequestDto;
import com.itsz.login.app.service.UserRegisterService;
import com.itsz.login.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/users")
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    ResponseEntity<Void> registerUser(@RequestBody @Valid UserRegisterRequestDto userRegisterDto) {
        User user = userService.findUserByUserName(userRegisterDto.getUsername());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already existed");
        }
        userRegisterService.register(userRegisterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
