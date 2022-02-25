package com.smartauto.demo.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    String id;
    String brand;
    String model;
    String carType;
    String year;
    Integer topSpeed;
    Integer price;
    String status;
}
