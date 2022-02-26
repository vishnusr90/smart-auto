package com.smartauto.demo.controller;

import java.util.*;

import com.smartauto.demo.repository.dto.UserDTO;
import com.smartauto.demo.repository.entity.User;
import com.smartauto.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;  
    
    @GetMapping("/role")
    public Collection<? extends GrantedAuthority> get(Authentication authentication) {
        authentication.getAuthorities().stream().forEach(it -> System.out.print(it));
        return authentication.getAuthorities();
    }

    @PostMapping("/create")
    public void createUser(@RequestBody UserDTO user) {
        userService.createUser(user);
    }
}
