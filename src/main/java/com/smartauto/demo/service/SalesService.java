package com.smartauto.demo.service;

import java.util.Optional;

import com.smartauto.demo.repository.CarInventoryRepository;
import com.smartauto.demo.repository.CarRepository;
import com.smartauto.demo.repository.SalesRepository;
import com.smartauto.demo.repository.UserRepository;
import com.smartauto.demo.repository.dto.CarDTO;
import com.smartauto.demo.repository.entity.Car;
import com.smartauto.demo.repository.entity.Sales;
import com.smartauto.demo.repository.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalesService {
    
    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarInventoryRepository carInventoryRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void buyCar(String id, String username) {
        System.out.println("Reached byuy !!!!");
        Optional<Car> carOptional = carRepository.findById(id);
        // Optional<Car> carOptional = carRepository.findCarByBrandAndModelAndColorAndYear(carDTO.getBrand(), carDTO.getModel(), carDTO.getColor(), carDTO.getYear());

        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            int remaining = carInventoryRepository.getCarCount(car.getId());
            System.out.println("Remaing for "+car.getId() +" "+ remaining);
            if (remaining > 0) {
                // Reduce the stock
                carInventoryRepository.decrementCount(car.getId());
                Optional<User> userOptional = userRepository.findByUsername(username);
                User user = userOptional.get();
                // Add in Sales table
                Sales sales = Sales
                    .builder()
                    .userId(user.getId())
                    .carId(car.getId())
                    .build();
                
                System.out.println(sales);
                salesRepository.save(sales);
            }
        }
    }
}
