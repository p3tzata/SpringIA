package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.prop.GlobalProp;
import com.example.demo.domainEntity.Ingredient;
import com.example.demo.domainEntity.representationModel.IngredientRepresentationModel;
import com.example.demo.domainEntity.representationModelAssembler.IngredientRepresentationModelAssembler;
import com.example.demo.dto.IngredientListDTO;
import com.example.demo.service.domain.IngredientService;

@RestController
@RequestMapping(value = "/public/ingredient",produces = "application/json")
@CrossOrigin(origins="*")
public class IngredientController {
	
	@Autowired 
	private GlobalProp globalProp; 
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/getTopIngredient")
	@ResponseStatus(code = HttpStatus.OK)
	public CollectionModel<IngredientRepresentationModel> getTopIngredient() {
		
		Pageable pageable = PageRequest.of(0, globalProp.getIngrediantPageSize());
		List<Ingredient> allIngredients = ingredientService.getAllIngredients(pageable);
		
		CollectionModel<IngredientRepresentationModel> collectionModel = new IngredientRepresentationModelAssembler().toCollectionModel(allIngredients);
		
		return collectionModel;
		
	}
	
	@GetMapping("/getAllIngredient")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Ingredient> getAllIngredient() {
		List<Ingredient> findAll = ingredientService.findAll();
		
		return findAll;
		
	}
	
	
	
	@PostMapping(path = "/",consumes = {"application/json"})
	public ResponseEntity<?> postIngredient(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<>((ingredientService.save(ingredient)),HttpStatus.CREATED);
	
	}
	
	
	@PutMapping(path = "/",consumes = {"application/json"})
	public ResponseEntity<?> putIngredient(@RequestBody Ingredient ingredient) {
		return new ResponseEntity<>((ingredientService.save(ingredient)),HttpStatus.OK);
	
	}
	
	//Patch is common used in partially update entity
	@PatchMapping(path = "/",consumes = {"application/json"})
	public ResponseEntity<?> patchIngredient(@RequestBody Ingredient ingredientPatch) {
		
		Ingredient ingredient = ingredientService.findById(ingredientPatch.getIngredientId());
		if (ingredientPatch.getIngredientName()!= null) {
			ingredient.setIngredientName(ingredientPatch.getIngredientName());
		}
		
		if (ingredientPatch.getIngredientDescr()!=null) {
			ingredient.setIngredientDescr(ingredientPatch.getIngredientDescr());
		}
		
		
		return new ResponseEntity<>((ingredientService.save(ingredient)),HttpStatus.OK);
	
	}
	
	@DeleteMapping(path = "/{ingredientId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteIngredient (@PathVariable Long ingredientId) {
		
		ingredientService.delete(ingredientId);
		
	}
	
	
	
}
