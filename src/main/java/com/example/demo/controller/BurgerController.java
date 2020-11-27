package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.representationModel.BurgerRepresentationModel;
import com.example.demo.domainEntity.representationModelAssembler.BurgerRepresentationModelAssembler;
import com.example.demo.service.BurgerService;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping(value = "/public/burger",produces = "application/json")
@CrossOrigin(origins="*")
public class BurgerController {

	@Autowired 
	private BurgerService burgerService;
	
	@GetMapping("/getAllBurger")
	public CollectionModel<BurgerRepresentationModel> getAllBurger() {
	
		
		List<Burger> findAllBurger = burgerService.findAllBurger();
		
		
		//CollectionModel<EntityModel<Burger>> burgerCollectionModel = CollectionModel.wrap(findAllBurger);
		//Link link = new Link("/burger/getAllBurger","getAllBurger")
		//Link link = WebMvcLinkBuilder.linkTo(BurgerController.class).slash("burger/getAllBurger").withRel("getAllTopBurger");
		
		
		Link link = WebMvcLinkBuilder.linkTo(methodOn(BurgerController.class).getAllBurger()).withRel("getAllTopBurger");
		
		CollectionModel<BurgerRepresentationModel> collectionModel = new  BurgerRepresentationModelAssembler().toCollectionModel(findAllBurger);
		collectionModel.add(link);
		

		return collectionModel;
		
	}
	
	@GetMapping("/{burgerId}")
	public BurgerRepresentationModel getBurger(@PathVariable Long burgerId) {
		Burger burger = burgerService.findById(burgerId);
		
		BurgerRepresentationModelAssembler burgerRepresentationModelAssembler = new BurgerRepresentationModelAssembler();
		BurgerRepresentationModel model = burgerRepresentationModelAssembler.toModel(burger);
		
		
		return model;
		
	}
	
}
