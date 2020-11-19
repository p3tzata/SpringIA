package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domainEntity.Burger;

public interface BurgerRepo extends JpaRepository<Burger, Long> {

}
