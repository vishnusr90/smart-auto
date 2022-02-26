package com.smartauto.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.lang3.*;
import com.smartauto.demo.common.SalesStatus;
import com.smartauto.demo.repository.CarInventoryRepository;
import com.smartauto.demo.repository.CarRepository;
import com.smartauto.demo.repository.dto.CarDTO;
import com.smartauto.demo.repository.entity.Car;
import com.smartauto.demo.repository.entity.CarInventory;


// all logs
@Service
public class InventoryService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarInventoryRepository carInventoryRepository;

    public List<CarDTO> getAllCars() {
        List<CarInventory> carInventoryList = carInventoryRepository.findAll();
        List<String> carIds = carInventoryList.stream()
            .map(CarInventory::getId)
            .collect(Collectors.toList());
        Map<String, Integer> carRemainingMapping = carInventoryList.stream().collect(Collectors.toMap(CarInventory::getCarId, CarInventory::getRemaining));


        List<Car> carList = carRepository.findAllById(carIds);
        // List<Car> carList = carRepository.findAllAvailableCars();
        List<CarDTO> list = carList.stream()
            .map(c -> {
                System.out.println(c);
                return CarDTO.builder()
                    .id(c.getId())
                    .brand(c.getBrand())
                    .model(c.getModel())
                    .color(c.getColor())
                    .price(c.getPrice())
                    .year(c.getYear())
                    .remaining(carRemainingMapping.get(c.getId()))
                    .build();
            })
            .collect(Collectors.toList());

        System.out.println(list);

        return list;
    }

    @Transactional
    public void addCar(CarDTO carDTO) {
        Optional<Car> carOptional = carRepository.findCarByBrandAndModelAndColorAndYear(carDTO.getBrand(), carDTO.getModel(), carDTO.getColor(), carDTO.getYear());

        if (carOptional.isPresent()) {
            // Increase the inventory 
            Car existingCar = carOptional.get();
            carInventoryRepository.incrementCount(existingCar.getId());
        } else {
            // Create new entry in DB
            Car car = Car.builder()
                .brand(carDTO.getBrand())
                .model(carDTO.getModel())
                .carType(carDTO.getCarType().toString())
                .price(carDTO.getPrice())
                .year(carDTO.getYear())
                .createdOn(LocalDate.now())
                .build();
            Car savedCar = carRepository.save(car);

            CarInventory carInventory = CarInventory.builder()
                .carId(savedCar.getId())
                .remaining(1)
                .build();
            carInventoryRepository.save(carInventory);
        }
    }

    // public void updateCar(CarDTO carDTO) {
    //     Optional<Car> carOptional = carRepository.findById(carDTO.getId());
    //     if (carOptional.isPresent()) {
    //         Car car = carOptional.get();
    //         car.setBrand(!StringUtils.isBlank(carDTO.getBrand()) ? carDTO.getBrand() : car.getBrand());
    //         car.setModel(!StringUtils.isBlank(carDTO.getModel()) ? carDTO.getModel() : car.getModel());
    //         car.setYear(!StringUtils.isBlank(carDTO.getYear()) ? carDTO.getYear() : car.getYear());
    //         car.setPrice(carDTO.getPrice() != null ? carDTO.getPrice() : car.getPrice());
    //         car.setModifiedOn(LocalDate.now());
    //         carRepository.save(car);
    //     } else {
    //         System.out.println("Not present");
    //     }
    // }

    @Transactional
    public void deleteCar(String id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            System.out.println("Car present for deletion "+carOptional.get());
            carInventoryRepository.decrementCount(id);
        } else {
            System.out.println("Car not present !!!!");
        }
    }

    // brand, model, color
    public void buyCar() {
        System.out.println("Buying car");

    }
    
}
