package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domainEntity.SaleOrder;

@Transactional
public interface SaleOrderRepo extends JpaRepository<SaleOrder, Long> {

	
	
}
