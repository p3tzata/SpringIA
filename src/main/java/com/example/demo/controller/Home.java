package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.common.prop.GlobalProp;
import com.example.demo.domainEntity.Ingredient;
import com.example.demo.dto.IngredientListDTO;
import com.example.demo.dto.ResponseMessageOK;
import com.example.demo.service.DemoService;
import com.example.demo.service.IngredientService;
import com.google.gson.Gson;


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
	
	
}
