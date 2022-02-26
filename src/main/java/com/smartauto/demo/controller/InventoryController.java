package com.smartauto.demo.controller;

import com.smartauto.demo.repository.dto.CarDTO;
import com.smartauto.demo.service.InventoryService;

import java.security.Principal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InventoryController {

    @Autowired
    private InventoryService carService;

    @GetMapping("/test")
    public String test() {
        return "Ok running !";
    }  

    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/car")
    public void createCar(@RequestBody CarDTO carDTO) {
        carService.addCar(carDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/car")
    public void updateCar(@RequestBody CarDTO carDTO) {
        // carService.updateCar(carDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/car/{id}")
    public void deleteCar(@PathVariable String id, Principal user) {
        System.out.println(user.getName());
        carService.deleteCar(id);
    }

    @PreAuthorize("hasRole('ROLE_BUYER')")
    @PutMapping("/car/buy/{id}")
    public void buyCar(@PathVariable String id) {
        System.out.println("Going to buy");
        carService.buyCar();
    }
    
}
