package com.itsz.login.app.service.impl;

import com.itsz.login.app.dao.UserRepository;
import com.itsz.login.app.domain.User;
import com.itsz.login.app.dto.UserRegisterRequestDto;
import com.itsz.login.app.service.UserService;
import com.itsz.login.app.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void register(UserRegisterRequestDto userRegisterDto) {
        User user = convertDto2Domain(userRegisterDto);
        userRepository.save(user);
    }

    @Override
    public List<UserView> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(UserServiceImpl::convertDomain2View).collect(Collectors.toList());
    }


    static User convertDto2Domain(UserRegisterRequestDto userRegisterDto) {
        User user = new User();
        user.setUsername(userRegisterDto.getUsername());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setPasswordConfirm(userRegisterDto.getPasswordConfirm());
        return user;
    }

    static UserView convertDomain2View(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setUsername(user.getUsername());
        userView.setEmail(user.getEmail());
        return userView;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUserName(username);
        return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"))
                .build();
    }
}
