package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domainEntity.Ingredient;
import com.example.demo.dto.IngredientListDTO;
import com.example.demo.repository.IngredientRepo;


import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class IngredientService {

	
	@Autowired
	private IngredientRepo ingredientRepo;
	
	public IngredientListDTO getAllIngredients(Pageable pageable) {
		List<Ingredient> findAllOrderByiIngredient_name = ingredientRepo.findAllByOrderByIngredientNameDesc(pageable);
		IngredientListDTO ingredientListDTO = new IngredientListDTO();
		ingredientListDTO.setIngredients( findAllOrderByiIngredient_name );
		return ingredientListDTO; 
		
	}
	
	public Ingredient save(Ingredient ingredient) {
		return ingredientRepo.save(ingredient);
	}
	
}
