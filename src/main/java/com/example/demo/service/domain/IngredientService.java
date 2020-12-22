package com.example.demo.service.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	public List<Ingredient> getAllIngredients(Pageable pageable) {
		List<Ingredient> findAllOrderByiIngredient_name = ingredientRepo.findAllByOrderByIngredientNameDesc(pageable);
		//IngredientListDTO ingredientListDTO = new IngredientListDTO();
		//ingredientListDTO.setIngredients( findAllOrderByiIngredient_name );
		return findAllOrderByiIngredient_name; 
		
	}
	
	public Ingredient findById(Long id) {
		if (ingredientRepo.findById(id).isPresent()) {
			
			return ingredientRepo.findById(id).get();
		}
		return null;
		
	}
	
	public Ingredient save(Ingredient ingredient) {
		return ingredientRepo.save(ingredient);
	}
	
	public void delete(Long ingredientId) {
		try {
			ingredientRepo.deleteById(ingredientId);
	
		} catch (EmptyResultDataAccessException ex) {
			
		}
	}
	
	
}
