package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domainEntity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
