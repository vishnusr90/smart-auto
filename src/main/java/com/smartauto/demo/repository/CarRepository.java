package com.smartauto.demo.repository;

import java.util.Optional;

import com.smartauto.demo.repository.entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    Optional<Car> findCarByBrand(String brand);
    
}