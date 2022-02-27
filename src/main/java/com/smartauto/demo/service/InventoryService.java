package com.smartauto.demo.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.smartauto.demo.exception.CarNotFoundException;
import com.smartauto.demo.exception.InSufficientInfoException;
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
            .map(CarInventory::getCarId)
            .collect(Collectors.toList());
        Map<String, Integer> carRemainingMapping = carInventoryList.stream()
            .collect(Collectors.toMap(CarInventory::getCarId, CarInventory::getRemaining));


        List<Car> carList = carRepository.findAllById(carIds);
        System.out.println(carList);
        List<CarDTO> list = carList.stream()
            .map(car -> {
                return CarDTO.builder()
                    .id(car.getId())
                    .brand(car.getBrand())
                    .model(car.getModel())
                    .color(car.getColor())
                    .price(car.getPrice())
                    .year(car.getYear())
                    .remaining(carRemainingMapping.get(car.getId()))
                    .build();
            })
            .collect(Collectors.toList());
        return list;
    }

    @Transactional
    public void addCar(CarDTO carDTO) {

        if (!validateCarObject(carDTO)) {
            throw new InSufficientInfoException("Insufficent information to add new car");
        }
        Optional<Car> carOptional = carRepository.findCarByBrandAndModelAndColorAndYearAndPrice(carDTO.getBrand(), carDTO.getModel(), carDTO.getColor(), carDTO.getYear(), carDTO.getPrice());

        if (carOptional.isPresent()) {
            // Increase the inventory 
            Car existingCar = carOptional.get();
            carInventoryRepository.incrementStockByCarId(existingCar.getId());
        } else {
            // Create new entry in DB
            Car car = Car.builder()
                .brand(carDTO.getBrand())
                .model(carDTO.getModel())
                .price(carDTO.getPrice())
                .year(carDTO.getYear())
                .color(carDTO.getColor())
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

    @Transactional 
    public void restockCar(String id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            carInventoryRepository.incrementStockByCarId(id);
        } else {
            throw new CarNotFoundException("Cannot find this car to restock");
        }
    }

    @Transactional
    public void decrementStock(String id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            carInventoryRepository.decrementStockByCarId(id);
        } else {
            throw new CarNotFoundException("Cannot find this car to restock");
        }
    }

    // Check if all fields are populated from front end
    private boolean validateCarObject(CarDTO carDTO) {
        return carDTO != null && 
         !StringUtils.isBlank(carDTO.getBrand()) &&
         !StringUtils.isBlank(carDTO.getModel()) &&
         !StringUtils.isBlank(carDTO.getColor()) &&
         !StringUtils.isBlank(carDTO.getYear());
    }

    public CarDTO getCarDetails(String id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return CarDTO.builder()
                    .id(car.getId())
                    .brand(car.getBrand())
                    .model(car.getModel())
                    .color(car.getColor())
                    .price(car.getPrice())
                    .year(car.getYear())
                    .build();
        }
        return CarDTO.builder().build();
    }
}
