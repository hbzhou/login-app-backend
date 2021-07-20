package com.itsz.login.app.controller;


import com.itsz.login.app.domain.User;
import com.itsz.login.app.dto.UserLoginRequestDto;
import com.itsz.login.app.dto.UserRegisterRequestDto;
import com.itsz.login.app.service.UserService;
import com.itsz.login.app.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

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
    public ResponseEntity<Void> login(@RequestBody @Valid UserLoginRequestDto request) {
        User user = userService.findUserByUserName(request.getUsername());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "username not existed");
        }
        if (!user.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password is not correct");
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @GetMapping()
    public ResponseEntity<List<UserView>> getUsers() {
        List<UserView> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);

    }


}
