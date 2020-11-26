package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domainEntity.Customer;

@RepositoryRestResource
public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
