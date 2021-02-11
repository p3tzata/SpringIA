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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@NoArgsConstructor
public class IngredientConvertReactiveService {

	
	@Autowired
	private IngredientRepo ingredientRepo;
	
	
	public Mono<Ingredient> findById(Long id) {
		if (ingredientRepo.findById(id).isPresent()) {
			
			return Mono.just(ingredientRepo.findById(id).get());
		}
		return null;
		
	}
	
	
	public Mono<Ingredient> save(Mono<Ingredient> ingredient) {
		
		Ingredient ingredientOfMono = ingredient.block();
		return Mono.just(ingredientRepo.save(ingredientOfMono));

	}
	
	
	public void saveAll(Flux<Ingredient> ingredients) {
		
		//Less best approach
		Iterable<Ingredient> ingredientOfFlux = ingredients.toIterable();
		ingredientRepo.saveAll(ingredientOfFlux);
		
		//More best approach
		ingredients.subscribe( el ->  ingredientRepo.save(el) ); 
		
		

	}
	
	
	
	
}
