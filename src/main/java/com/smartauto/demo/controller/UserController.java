package com.smartauto.demo.controller;

import java.util.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/role")
    public Collection<? extends GrantedAuthority> get(Authentication authentication) {
        return authentication.getAuthorities();
    }
}
