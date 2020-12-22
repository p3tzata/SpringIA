package com.example.demo.service.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.domainEntity.Burger;
import com.example.demo.dto.BurgerListDTO;
import com.example.demo.repository.BurgerRepo;

import lombok.NoArgsConstructor;

@Service

@NoArgsConstructor
public class BurgerService {

	@Autowired
	private BurgerRepo burgerRepo;
	
	@Transactional
	public List<Burger> findAllBurger() {
		
		List<Burger> findAll = burgerRepo.findAll();
		BurgerListDTO burgerListDTO = new BurgerListDTO();

		for (Iterator iterator = findAll.iterator(); iterator.hasNext();) {
			Burger burger = (Burger) iterator.next();
			burger.getIngredients().size();
		}
		
		burgerListDTO.setBurgers(findAll);
		
		return findAll;
	}
	@Transactional
	public Burger findById(Long id) {
		Optional<Burger> findById = burgerRepo.findById(id);
		if (findById.isPresent()) {
			findById.get().getIngredients().size();
			return findById.get();
		}
		return null;
	}
	
}
