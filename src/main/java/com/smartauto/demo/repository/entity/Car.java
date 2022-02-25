package com.smartauto.demo.repository.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "Car")
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String brand;

    private String model;

    private String carType;

    private Integer price;

    private Integer topSpeed;

    private String year;

    private String soldStatus;

    private LocalDate createdOn;

    private LocalDate modifiedOn;
}
