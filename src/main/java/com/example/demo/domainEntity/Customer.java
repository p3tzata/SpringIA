package com.example.demo.domainEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.data.rest.core.annotation.RestResource;

import lombok.Data;

@Entity
@Data
@RestResource(rel="customers69",path="customers69")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customer_id;
	
	
	@Size(min = 1,max = 100)
	private String customer_name;
	
	
}
