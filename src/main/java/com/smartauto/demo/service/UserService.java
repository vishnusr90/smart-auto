package com.smartauto.demo.service;

import java.util.*;

import com.smartauto.demo.exception.InSufficientInfoException;
import com.smartauto.demo.exception.UserNotFoundException;
import com.smartauto.demo.repository.UserRepository;
import com.smartauto.demo.repository.dto.UserDTO;
import com.smartauto.demo.repository.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
