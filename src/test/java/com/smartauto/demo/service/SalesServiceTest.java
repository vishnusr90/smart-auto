package com.smartauto.demo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.smartauto.demo.exception.CarNotFoundException;
import com.smartauto.demo.repository.CarInventoryRepository;
import com.smartauto.demo.repository.CarRepository;
import com.smartauto.demo.repository.SalesRepository;
import com.smartauto.demo.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SalesServiceTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SalesRepository salesRepository;

    @Mock
    private CarInventoryRepository carInventoryRepository;

    @InjectMocks
    private SalesService salesService;

    @Test
    public void testBuyCarWhenCarNotPresent() {
        assertThrows(CarNotFoundException.class, () -> {
            salesService.buyCar("", "");
        });
    }
}
