package com.smartauto.demo.repository;

import com.smartauto.demo.repository.entity.Sales;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, String> {

}
