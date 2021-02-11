package com.example.demo.controller.reactiveController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.Ingredient;
import com.example.demo.service.domain.BurgerService;
import com.example.demo.service.domain.IngredientMockedReactiveService;
import com.example.demo.service.domain.IngredientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/public/reactiveAPI",produces = "application/json")
public class ReactiveAPI {
	// THIS is not full reactive stack because I don't use ReactiveCrudRepository.
	// Because jdbc is not reactive component.
	// One option would be to use alternative SQL clients that are fully non-blocking.  
	// I will leave this for the future.
	

	
	private IngredientMockedReactiveService ingredientReactiveService;
	
	@Autowired
	public ReactiveAPI(IngredientMockedReactiveService ingredientReactiveService) {
		super();
		this.ingredientReactiveService = ingredientReactiveService;
	}

	@GetMapping("/getIngrediant/{ingrediantId}")
	public Mono<Ingredient> getIngrediant(@PathVariable Long ingrediantId) throws InterruptedException {
		
		Mono<Ingredient> mono = ingredientReactiveService.findById(ingrediantId);
		
		return mono;
		
	}
	
	@GetMapping("/getAllIngrediant")
	public Flux<Ingredient> getAllIngrediant() {
	
		Flux<Ingredient> flux = ingredientReactiveService.findAll();
		
		return flux;
		
	}
	
	
	//Mocked
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping(path = "/createIngredient",consumes = {"application/json"})
	public Mono<Ingredient> postIngredient(@RequestBody Mono<Ingredient> ingredientMono) {
		
		return ingredientMono;
	
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@GetMapping("/getIngrediantNotFound/{ingrediantId}")
	public Mono<Ingredient> getIngrediantNotFound(@PathVariable Long ingrediantId) {
		
		return null;
		
	}
	
	
	
	
}
