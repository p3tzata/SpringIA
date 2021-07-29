package com.example.demo.controller.domain;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domainEntity.Customer;
import com.example.demo.domainEntity.SaleOrder;
import com.example.demo.repository.CustomerRepo;
import com.example.demo.service.domain.SaleOrderService;
/*
Overriding Spring Data REST Response Handlers
Sometimes you may want to write a custom handler for a specific resource. 
To take advantage of Spring Data REST’s settings, message converters, exception handling, and more.
This add prefix spring.data.rest.base-path, but returned value from handler methods doesn't write response 
to body, we should return ResponseEntity. 

*/
@RestController
@RequestMapping(value = "/public/saleorder",produces = "application/json")
public class SorderController {
	
	@Autowired
	private SaleOrderService saleOrderService;
	
	@GetMapping(path="/all", produces = "application/hal+json")
	@ResponseBody
	public ResponseEntity<?> getAllSaleOrder() {
		List<SaleOrder> findAll = saleOrderService.findAll();
		
		return new ResponseEntity<>(findAll,HttpStatus.OK);
	}
	
	
}
