package com.smartauto.demo.repository.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

    private String color;

    private String carType;

    private Integer price;

    private String year;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "car_inventory_id", referencedColumnName = "id")
    // private CarInventory inventory;

    private LocalDate createdOn;

    private LocalDate modifiedOn;
}
