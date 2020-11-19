package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domainEntity.SaleOrder;

public interface SaleOrderRepo extends JpaRepository<SaleOrder, Long> {

}
