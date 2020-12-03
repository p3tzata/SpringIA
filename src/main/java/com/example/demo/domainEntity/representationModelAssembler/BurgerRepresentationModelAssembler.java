package com.example.demo.domainEntity.representationModelAssembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.example.demo.controller.BurgerController;
import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.representationModel.BurgerRepresentationModel;

public class BurgerRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Burger, BurgerRepresentationModel> {

	public BurgerRepresentationModelAssembler() {
		super(BurgerController.class, BurgerRepresentationModel.class);
	}

	
	
	
	@Override
	protected BurgerRepresentationModel instantiateModel(Burger entity) {
		
		return new  BurgerRepresentationModel(entity);
	}
	
	@Override
	public BurgerRepresentationModel toModel(Burger entity) {
		return createModelWithId(entity.getBurger_id(), entity);
	}

	
	
	

	
	

}
