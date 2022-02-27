package com.smartauto.demo.controller;

import java.security.Principal;

import com.smartauto.demo.service.SalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    
    @Autowired
    private SalesService salesService;

    @PostMapping("/cars/buy/{id}")
    public void buyCar(@PathVariable String id, Principal principal) {
        String name = principal.getName();
        salesService.buyCar(id, name);
    }

    @GetMapping("/{id}")
    public Integer getTotalSalesByCarId(@PathVariable String id) {
        return salesService.getTotalSalesByCarId(id);
    }

}
