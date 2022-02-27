package com.smartauto.demo.controller;

import com.smartauto.demo.repository.dto.CarDTO;
import com.smartauto.demo.service.InventoryService;

import java.security.Principal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
public class InventoryController {

    @Autowired
    private InventoryService carService;

    @GetMapping("/")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public void createNewCar(@RequestBody CarDTO carDTO) {
        carService.addCar(carDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void decrementStock(@PathVariable String id, Principal user) {
        carService.decrementStock(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/restock/{id}")
    public void reStockCar(@PathVariable String id) {
        carService.restockCar(id);
    }

    @GetMapping("/{id}")
    public CarDTO getStockByCarId(@PathVariable String id) {
        return carService.getCarDetails(id);
    }

}
