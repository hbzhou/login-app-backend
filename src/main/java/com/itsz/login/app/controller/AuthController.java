package com.itsz.login.app.controller;


import com.itsz.login.app.domain.User;
import com.itsz.login.app.dto.UserLoginRequestDto;
import com.itsz.login.app.dto.UserLoginResponseDto;
import com.itsz.login.app.dto.UserRegisterRequestDto;
import com.itsz.login.app.service.JWTService;
import com.itsz.login.app.service.UserService;
import com.itsz.login.app.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private UserService userService;


    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserRegisterRequestDto userRegisterDto) {
        User user = userService.findUserByUserName(userRegisterDto.getUsername());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already existed");
        }
        userService.register(userRegisterDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody @Valid UserLoginRequestDto request) {
        User user = userService.findUserByUserName(request.getUsername());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "username not existed");
        }
        if (!user.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password is not correct");
        }
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        UserLoginResponseDto response = new UserLoginResponseDto();
        response.setToken(jwtService.generateToken(authenticate));
        response.setAuthentication(authenticate);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }


}
