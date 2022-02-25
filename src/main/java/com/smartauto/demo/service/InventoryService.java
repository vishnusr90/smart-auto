package com.smartauto.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.lang3.*;
import com.smartauto.demo.common.SalesStatus;
import com.smartauto.demo.repository.CarRepository;
import com.smartauto.demo.repository.dto.CarDTO;
import com.smartauto.demo.repository.entity.Car;


// all logs
@Service
public class InventoryService {

    @Autowired
    private CarRepository carRepository;
    
    public List<CarDTO> getAllCars() {
        List<Car> carList = carRepository.findAll();
        System.out.println("Size "+carList.size());
        return carList.stream()
            .map(c -> {
                return CarDTO.builder()
                    .id(c.getId())
                    .brand(c.getBrand())
                    .model(c.getModel())
                    .price(c.getPrice())
                    .year(c.getYear())
                    .topSpeed(c.getTopSpeed())
                    .status(c.getSoldStatus())
                    .build();
            })
            .collect(Collectors.toList());
    }

    public void addCar(CarDTO carDTO) {
        System.out.println(carDTO.getTopSpeed());
        Car car = Car.builder()
            .brand(carDTO.getBrand())
            .model(carDTO.getModel())
            .carType(carDTO.getCarType().toString())
            .topSpeed(carDTO.getTopSpeed())
            .soldStatus(SalesStatus.OPEN.toString())
            .price(carDTO.getPrice())
            .year(carDTO.getYear())
            .createdOn(LocalDate.now())
            .build();

        carRepository.save(car);
    }

    public void updateCar(CarDTO carDTO) {
        Optional<Car> carOptional = carRepository.findById(carDTO.getId());
        if (carOptional.isPresent()) {
            
            Car car = carOptional.get();

            System.out.println("Updated "+ carDTO);
            System.out.println("Updating before "+ car);
            

            car.setBrand(!StringUtils.isBlank(carDTO.getBrand()) ? carDTO.getBrand() : car.getBrand());
            car.setModel(!StringUtils.isBlank(carDTO.getModel()) ? carDTO.getModel() : car.getModel());
            car.setYear(!StringUtils.isBlank(carDTO.getYear()) ? carDTO.getYear() : car.getYear());
            car.setPrice(carDTO.getPrice() != null ? carDTO.getPrice() : car.getPrice());
            car.setModifiedOn(LocalDate.now());

            System.out.println("After "+ car);
            carRepository.save(car);
        } else {
            System.out.println("Not present");
        }
    }

    public void deleteCar(String id) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            System.out.println("Present "+id);
            carRepository.deleteById(id);
            List<Car> list = carRepository.findAll();
            System.out.println("Total size now "+list.size());
        } else {
            System.out.println("Car not present !!!!");
        }
    }

    public void buyCar() {

    }
    
}
