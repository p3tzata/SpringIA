package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domainEntity.Ingredient;
import com.example.demo.repository.IngredientRepo;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class DemoService {

	@Autowired
	private IngredientRepo ingredientRepo;
	
	public void generateDemoData() {
		Ingredient ingredientBread = new Ingredient();
		ingredientBread.setIngredientName("bread");
		
		Ingredient ingredientSalt = new Ingredient();
		ingredientSalt.setIngredientName("salt");
		
		ingredientRepo.saveAll(Arrays.asList(ingredientBread,ingredientSalt));
		
	}
	
	
}
