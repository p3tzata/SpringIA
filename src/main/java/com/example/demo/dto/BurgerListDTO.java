package com.example.demo.dto;

import java.util.List;

import com.example.demo.domainEntity.Burger;


import lombok.Data;


@Data
public class BurgerListDTO {

	private List<Burger> burgers;
	
}
