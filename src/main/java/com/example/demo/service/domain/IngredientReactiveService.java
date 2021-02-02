package com.example.demo.service.domain;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.domainEntity.Ingredient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IngredientReactiveService {

	
	public Mono<Ingredient> findById(Long id) {
		
		return null;
		
	}
	
	public Flux<Ingredient> findAll() {
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setIngredientId(1L);
		ingredient1.setIngredientName("name11111");
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setIngredientId(2L);
		ingredient2.setIngredientName("name22222");
		ArrayList<Ingredient> list = new ArrayList<>();
		list.add(ingredient1);list.add(ingredient2);
		
		Flux<Ingredient> fluxIngredient = Flux.fromIterable(list);
		return fluxIngredient;
	}
	
	
}
