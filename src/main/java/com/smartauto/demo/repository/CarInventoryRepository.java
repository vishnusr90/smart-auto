package com.smartauto.demo.repository;

import com.smartauto.demo.repository.entity.CarInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CarInventoryRepository extends JpaRepository<CarInventory, String> {
    
    @Modifying
    @Query(
        value = "Update car_inventory set remaining = remaining + 1 where car_id=?1",
        nativeQuery = true
    )
    void incrementStockByCarId(String id);

    @Modifying
    @Query(
        value = "Update car_inventory set remaining = remaining - 1 where car_id=?1 and remaining > 0",
        nativeQuery = true
    )
    void decrementStockByCarId(String id);

    @Query(
        value = "select remaining from car_inventory where car_id=?1",
        nativeQuery = true
    )
    int getStockCountByCarId(String id); 
}
