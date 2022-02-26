package com.smartauto.demo.controller;

import java.security.Principal;

import com.smartauto.demo.repository.dto.CarDTO;
import com.smartauto.demo.service.SalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SalesController {
    
    @Autowired
    private SalesService salesService;

    @PostMapping("/car/buy/{id}")
    public void buyCar(@PathVariable String id, Principal principal) {
        String name = principal.getName();
        salesService.buyCar(id, name);
    }

}
