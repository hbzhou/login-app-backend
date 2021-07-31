package com.itsz.login.app.controller;

import com.itsz.login.app.service.UserService;
import com.itsz.login.app.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<UserView>> getUsers() {
        List<UserView> userList = userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);

    }
}
