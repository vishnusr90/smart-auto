package com.smartauto.demo.mock;

import java.util.List;

import com.smartauto.demo.repository.entity.CarInventory;

public class MockCarInventory {

    public static List<String> buildCarIds() {
        return List.of("car1", "car2", "car3");
    }
    
    public static CarInventory buildCarInventory() {
        return CarInventory.builder()
                .id("id")
                .carId("carId")
                .remaining(5)
                .build();
    }

    public static List<CarInventory> buildCarInventoryList() {
        return List.of(
            CarInventory.builder().carId("car1").remaining(1).build(),
            CarInventory.builder().carId("car2").remaining(2).build(),
            CarInventory.builder().carId("car3").remaining(3).build()
        );
    }

    public static List<CarInventory> buildEmptyCarInventoryList() {
        return List.of();
    }
}
