package com.example.demo.service.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.SaleOrderRepo;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class SaleOrderService {

	@Autowired
	private SaleOrderRepo saleOrderRepo;
	
}
