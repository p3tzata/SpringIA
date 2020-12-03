package com.example.demo.controller.restConsuming;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domainEntity.Customer;
import com.example.demo.dto.ResponseMessageFAIL;

@RestController
@RequestMapping(path = "/public/Traverson",produces = "application/json")
public class Traversion {

	private final String testRestApi="http://127.0.0.1:8080/public/api"; 
	
	private Traverson traverson;
	@Autowired
	private RestTemplate restTemplate;

	public Traversion() {
		
		traverson = new Traverson(URI.create(testRestApi),MediaTypes.HAL_JSON);
		
		
	}
	
	@GetMapping("/follow")
	public ResponseEntity<?> follow() {
		
		ParameterizedTypeReference<Object> parameterizedTypeReference = new ParameterizedTypeReference<Object>() {};
		
		try {
		Object list = traverson.follow("customers69")
			//	.follow("self69")
				.toObject(parameterizedTypeReference);
		return new ResponseEntity<>(list,HttpStatus.OK);
		} catch (IllegalStateException e) {
			return new ResponseEntity<>(new ResponseMessageFAIL("follow not found"),HttpStatus.OK);
		}
		
	}
	
	/*
	 * Since Traversion doesn't offer method for write or delete. It must me used with RestTemplate.
	 */
	
	@GetMapping("/traverionAndRestTemplate")
	public ResponseEntity<?> traverionAndRestTemplate() {
		
		try {
			Customer customer = new Customer();
			
			customer.setCustomer_name("Ivo ET");
			
			
			String href = traverson.follow("customers69")
					               .asLink()
					               		.getHref();
			
			ResponseEntity<Customer> postForEntity = restTemplate.postForEntity(href, customer, Customer.class);
			
			
			return new ResponseEntity<>(postForEntity.getBody(),HttpStatus.OK);
			} catch (IllegalStateException e) {
				return new ResponseEntity<>(new ResponseMessageFAIL("follow not found"),HttpStatus.OK);
			}
		
		
	}
	
	
	
}
