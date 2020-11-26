package com.example.demo.domainEntity.representationModel;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.example.demo.domainEntity.Ingredient;

import lombok.Getter;

@Relation(value = "Ingredient69",collectionRelation = "IngredientList69")
public class IngredientRepresentationModel extends RepresentationModel<IngredientRepresentationModel> {
	@Getter
	private Long ingredientId;
	@Getter
	private String ingredientName;
	@Getter
	private String ingredientDescr;
	public IngredientRepresentationModel(Ingredient entity) {
		ingredientId=entity.getIngredientId();
		ingredientName=entity.getIngredientName();
		ingredientDescr=entity.getIngredientDescr();
	}
	
	
	
	
}
