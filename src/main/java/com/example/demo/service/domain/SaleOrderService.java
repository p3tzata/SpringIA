package com.example.demo.service.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domainEntity.SaleOrder;
import com.example.demo.repository.SaleOrderRepo;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor

public class SaleOrderService {

	@Autowired
	private SaleOrderRepo saleOrderRepo;
	
	@Transactional
	public List<SaleOrder> findAll() {
		return saleOrderRepo.findAll();
	}
	
}
