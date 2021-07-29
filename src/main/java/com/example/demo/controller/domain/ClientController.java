package com.example.demo.controller.domain;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domainEntity.Customer;
import com.example.demo.repository.CustomerRepo;
/*
Overriding Spring Data REST Response Handlers
Sometimes you may want to write a custom handler for a specific resource. 
To take advantage of Spring Data REST’s settings, message converters, exception handling, and more.
This add prefix spring.data.rest.base-path, but returned value from handler methods doesn't write response 
to body, we should return ResponseEntity. 

*/
@RepositoryRestController
public class ClientController {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@GetMapping(path="/customers69/cli", produces = "application/hal+json")
	@ResponseBody
	public ResponseEntity<?> getAllCustomer() {
		List<Customer> findAll = customerRepo.findAll();
		
		return new ResponseEntity<>(findAll,HttpStatus.OK);
	}
	
	
}
