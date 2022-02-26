package com.smartauto.demo.repository;

import java.util.*;

import com.smartauto.demo.repository.entity.Car;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    Optional<Car> findCarByBrand(String brand);

    @Query(
        value = "select *, ci.remaining from car c inner join car_inventory ci where c.id = ci.car_id and ci.remaining > 0",
        nativeQuery = true
    )
    List<Car> findAllAvailableCars();

    Optional<Car> findCarByBrandAndModelAndColorAndYear(String brand, String model, String color, String year);
    
}