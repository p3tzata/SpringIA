package com.example.demo.domainEntity.representationModelProcessor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.demo.controller.BurgerController;
import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.representationModel.BurgerRepresentationModel;

public class BurgerRepresentationModelProcessor implements RepresentationModelProcessor<BurgerRepresentationModel> { 

	  @Override
	  public BurgerRepresentationModel process (BurgerRepresentationModel model) {
		  
		  Link link = WebMvcLinkBuilder.linkTo(methodOn(BurgerController.class).getAllBurger()).withRel("customNewLink");
		  
	    model.add( 
	    		link
	            );

	    return model; 
	  }
	}