package com.smartauto.demo.mock;

import java.util.List;
import java.util.Optional;

import com.smartauto.demo.repository.entity.Car;

public class MockCar {
    
    public static Optional<Car> buildOptionalCar() {
        return Optional.of(Car.builder()
                .id("1")
                .brand("abc")
                .model("model")
                .year("123")
                .color("red")
                .price(9876)
                .build());
    }

    public static List<Car> buildCarList() {
        return List.of(
            Car.builder()
                .id("car1")
                .brand("brand1")
                .model("model1")
                .year("year1")
                .color("color1")
                .build(),
            Car.builder()
                .id("car2")
                .brand("brand2")
                .model("model2")
                .year("year2")
                .color("color2")
                .build(),
            Car.builder()
                .id("car3")
                .brand("brand3")
                .model("model3")
                .year("year3")
                .color("color3")
                .build()    
        );
    }
}
