package com.example.demo.service.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CustomerRepo;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	
	
	
}
