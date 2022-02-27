package com.smartauto.demo.repository;

import com.smartauto.demo.repository.entity.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalesRepository extends JpaRepository<Sales, String> {
    
    @Query(
        value = "select count(*) from sales where carId=?1",
        nativeQuery = true
    )
    Integer getSalesCountByCarId(String id);
}
