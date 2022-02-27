package com.smartauto.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.smartauto.demo.mock.MockCar;
import com.smartauto.demo.mock.MockCarInventory;
import com.smartauto.demo.repository.CarInventoryRepository;
import com.smartauto.demo.repository.CarRepository;
import com.smartauto.demo.repository.dto.CarDTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {
    
    @Mock
    private CarRepository carRepository;

    @Mock
    private CarInventoryRepository carInventoryRepository;
    
    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void testGetAllCars() {
        when(carInventoryRepository.findAll())
            .thenReturn(MockCarInventory.buildCarInventoryList());

        when(carRepository.findAllById(MockCarInventory.buildCarIds()))
            .thenReturn(MockCar.buildCarList());

        List<CarDTO> carList = inventoryService.getAllCars();

        Assertions.assertEquals(3, carList.size());
        Assertions.assertEquals("car1", carList.get(0).getId());
        Assertions.assertEquals("brand1", carList.get(0).getBrand());
        Assertions.assertEquals("model1", carList.get(0).getModel());

        Assertions.assertEquals("car2", carList.get(1).getId());
        Assertions.assertEquals("brand2", carList.get(1).getBrand());
        Assertions.assertEquals("model2", carList.get(1).getModel());

        Assertions.assertEquals("car3", carList.get(2).getId());
        Assertions.assertEquals("brand3", carList.get(2).getBrand());
        Assertions.assertEquals("model3", carList.get(2).getModel());
    }

    @Test
    void testGetCarDetails(){
        when(carRepository.findById(""))
            .thenReturn(MockCar.buildOptionalCar());
        CarDTO car = inventoryService.getCarDetails("");

        Assertions.assertEquals("1", car.getId());
        Assertions.assertEquals("abc", car.getBrand());
        Assertions.assertEquals("model", car.getModel());
        Assertions.assertEquals("123", car.getYear());
        Assertions.assertEquals("red", car.getColor());
        Assertions.assertEquals(9876, car.getPrice());
    }
}   
