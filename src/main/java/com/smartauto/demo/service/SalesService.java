package com.smartauto.demo.service;

import java.util.*;

import com.smartauto.demo.exception.CarNotFoundException;
import com.smartauto.demo.repository.CarInventoryRepository;
import com.smartauto.demo.repository.CarRepository;
import com.smartauto.demo.repository.SalesRepository;
import com.smartauto.demo.repository.UserRepository;
import com.smartauto.demo.repository.entity.Car;
import com.smartauto.demo.repository.entity.Sales;
import com.smartauto.demo.repository.entity.User;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SalesService {
    
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CarInventoryRepository carInventoryRepository;

    public void getSalesInformation() {
        // List<Sales> salesList = salesRepository.findAll();

        // List<String> userIds = salesList.stream().map(Sales::getUserId).collect(Collectors.toList());

        // Map<String, User> userMapping =  userRepository
        //     .findAllById(userIds)
        //     .stream()
        //     .collect(Collectors.toMap(User::getId, Function.identity()));

        // salesList.stream()
        //     .map(s -> {
        //         return SalesDTO.builder()
        //             .brand(brand)
        //             .build();
        //     })
    }


    @Transactional
    public void buyCar(String id, String username) {
        Optional<Car> carOptional = carRepository.findById(id);

        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            int remaining = carInventoryRepository.getStockCountByCarId(car.getId());
            if (remaining > 0) {
                // Reduce the stock
                carInventoryRepository.decrementStockByCarId(car.getId());
                Optional<User> userOptional = userRepository.findByUsername(username);
                User user = userOptional.get();
                // Add in Sales table
                Sales sales = Sales
                    .builder()
                    .userId(user.getId())
                    .carId(car.getId())
                    .build();
                
                salesRepository.save(sales);
            }
        } else {
            throw new CarNotFoundException("Cannot find this car to restock");
        }
    }
}
