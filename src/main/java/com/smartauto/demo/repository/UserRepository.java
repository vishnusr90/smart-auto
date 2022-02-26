package com.smartauto.demo.repository;

import java.util.Optional;

import com.smartauto.demo.repository.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{
    
    Optional<User> findByUsername(String name);
}
