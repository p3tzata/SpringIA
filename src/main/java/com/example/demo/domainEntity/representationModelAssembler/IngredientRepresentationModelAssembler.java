package com.example.demo.domainEntity.representationModelAssembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.example.demo.controller.domain.BurgerController;
import com.example.demo.controller.domain.IngredientController;
import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.Ingredient;
import com.example.demo.domainEntity.representationModel.BurgerRepresentationModel;
import com.example.demo.domainEntity.representationModel.IngredientRepresentationModel;

public class IngredientRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientRepresentationModel> {

	public IngredientRepresentationModelAssembler() {
		super(IngredientController.class, IngredientRepresentationModel.class);
	}

	@Override
	protected IngredientRepresentationModel instantiateModel(Ingredient entity) {
		
		return new  IngredientRepresentationModel(entity);
	}
	
    @Override
	public IngredientRepresentationModel toModel(Ingredient entity) {
		return createModelWithId(entity.getIngredientId(), entity);
	}

	

	
	

}
