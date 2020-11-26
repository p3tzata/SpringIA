package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domainEntity.Burger;


@Transactional
@Repository
public interface BurgerRepo extends JpaRepository<Burger, Long> {

}
