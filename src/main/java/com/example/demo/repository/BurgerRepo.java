package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domainEntity.Burger;


@Transactional
public interface BurgerRepo extends JpaRepository<Burger, Long> {

}
