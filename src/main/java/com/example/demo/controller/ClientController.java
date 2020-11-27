package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domainEntity.Customer;
import com.example.demo.repository.CustomerRepo;

//This add prefix spring.data.rest.base-path, but returned value from handler methods doesn't write response 
//to body, we should return ResponseBody.
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
