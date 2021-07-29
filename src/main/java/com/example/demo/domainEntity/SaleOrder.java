package com.example.demo.domainEntity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class SaleOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long saleOrder_id;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToMany
	private List<Burger> burgers;
	
	

}
