package com.smartauto.demo.controller;

import com.smartauto.demo.repository.dto.CarDTO;
import com.smartauto.demo.service.InventoryService;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/admin/car/create")
    public void createCar(@RequestBody CarDTO carDTO) {
        carService.addCar(carDTO);
    }

    @PutMapping("/admin/car/update")
    public void updateCar(@RequestBody CarDTO carDTO) {
        carService.updateCar(carDTO);
    }

    @DeleteMapping("/admin/car/delete/{id}")
    public void deleteCar(@PathVariable String id) {
        carService.deleteCar(id);
    }

    @PutMapping("/buy/{id}")
    public void buyCar() {
        carService.buyCar();
    }
    
}
