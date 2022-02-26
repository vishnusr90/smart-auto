package com.smartauto.demo.service;

import java.util.*;

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

    public void createUser(UserDTO userDTO) {
        if (userDTO != null) {
            User user = User.builder()
                .username(userDTO.getUserName())
                .password(userDTO.getPassword())
                .build();
            userRepository.save(user);
        } else {

        }
    }

    public void deleteUser(String id) {
        Optional<User> userOptional = userRepository.findById(id);  
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
        } else {
             throw new UserNotFoundException("User not found");
        }
    }
}
