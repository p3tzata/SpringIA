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
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping(path="/public",produces = "application/json")
@CrossOrigin(origins="*")
public class HomeController {

	@Autowired
	private Gson gson;
	@Autowired
	private DemoService demoService;

	@GetMapping("/")
	public ResponseEntity<?> getHome() {
		
		 return new ResponseEntity<>(gson.toJson(new ResponseMessageOK("test")),HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/generateDataDemo")
	public ResponseEntity<?> generateDataDemo() {
		
		demoService.generateDemoData();
		
		 return new ResponseEntity<>(gson.toJson(new ResponseMessageOK("test")),HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
}
