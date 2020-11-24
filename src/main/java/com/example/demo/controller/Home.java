package com.example.demo.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resources;
import javax.transaction.Transactional;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.common.prop.GlobalProp;
import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.Ingredient;
import com.example.demo.dto.BurgerListDTO;
import com.example.demo.dto.IngredientListDTO;
import com.example.demo.dto.ResponseMessageOK;
import com.example.demo.service.BurgerService;
import com.example.demo.service.DemoService;
import com.example.demo.service.IngredientService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
@RequestMapping(path="/public",produces = "application/json")
@CrossOrigin(origins="*")
public class Home {

	@Autowired
	private Gson gson;
	@Autowired
	private DemoService demoService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired 
	private BurgerService burgerService;
	@Autowired 
	private GlobalProp globalProp; 
	
	@GetMapping("/")
	public ResponseEntity<?> getHome() {
		
		 return new ResponseEntity<>(gson.toJson(new ResponseMessageOK("test")),HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/generateDataDemo")
	public ResponseEntity<?> generateDataDemo() {
		
		demoService.generateDemoData();
		
		 return new ResponseEntity<>(gson.toJson(new ResponseMessageOK("test")),HttpStatus.OK);
	}
	
	@GetMapping("/getTopIngredient")
	public ResponseEntity<?> getTopIngredient() {
		
		Pageable pageable = PageRequest.of(0, globalProp.getIngrediantPageSize());
		IngredientListDTO allIngredients = ingredientService.getAllIngredients(pageable);
		
		 return new ResponseEntity<>(gson.toJson(allIngredients),HttpStatus.OK);
	}
	
	@PostMapping(path = "/ingredient",consumes = {"application/json"})
	public ResponseEntity<?> postIngredient(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<>(gson.toJson(ingredientService.save(ingredient)),HttpStatus.CREATED);
	
	}
	
	@PutMapping(path = "/ingredient",consumes = {"application/json"})
	public ResponseEntity<?> putIngredient(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<>(gson.toJson(ingredientService.save(ingredient)),HttpStatus.OK);
	
	}
	
	//Patch is common used in partially update entity
	@PatchMapping(path = "/ingredient",consumes = {"application/json"})
	public ResponseEntity<?> patchIngredient(@RequestBody Ingredient ingredientPatch) {
		
		Ingredient ingredient = ingredientService.findById(ingredientPatch.getIngredientId());
		if (ingredientPatch.getIngredientName()!= null) {
			ingredient.setIngredientName(ingredientPatch.getIngredientName());
		}
		
		if (ingredientPatch.getIngredientDescr()!=null) {
			ingredient.setIngredientDescr(ingredientPatch.getIngredientDescr());
		}
		
		
		return new ResponseEntity<>(gson.toJson(ingredientService.save(ingredient)),HttpStatus.OK);
	
	}
	
	@DeleteMapping(path = "/ingredient/{ingredientId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteIngredient (@PathVariable Long ingredientId) {
		
		ingredientService.delete(ingredientId);
		
	}
	
	@GetMapping("/burger/getAllBurger")
	
	public ResponseEntity<?> getAllBurger() {
	//public CollectionModel<EntityModel<Burger>> getAllBurger() {
		
		Type listType = new TypeToken<List<Burger>>() {}.getType();
		List<Burger> findAllBurger = burgerService.findAllBurger();
		CollectionModel<EntityModel<Burger>> burgerCollectionModel = CollectionModel.wrap(findAllBurger);
		;
		burgerCollectionModel.add(new Link("/burger/getAllBurger","getAllBurger"));
		//return burgerCollectionModel;
		return new ResponseEntity<>((burgerCollectionModel), HttpStatus.OK);
		
	}
	
	@GetMapping("/burger/{burgerId}")
	public ResponseEntity<?> getBurger(@PathVariable Long burgerId) {
		
		return new ResponseEntity<>(gson.toJson( burgerService.findById(burgerId)),HttpStatus.OK);
		
	}
	
	
	
	
	
}
