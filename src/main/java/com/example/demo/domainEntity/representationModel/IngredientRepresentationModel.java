package com.example.demo.domainEntity.representationModel;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.example.demo.domainEntity.Ingredient;

import lombok.Getter;
import lombok.Setter;

@Relation(value = "ingredients",collectionRelation = "ingredients")
public class IngredientRepresentationModel extends RepresentationModel<IngredientRepresentationModel> {
	@Getter @Setter
	private Long ingredientId;
	@Getter @Setter
	private String ingredientName;
	@Getter @Setter
	private String ingredientDescr;
	public IngredientRepresentationModel(Ingredient entity) {
		ingredientId=entity.getIngredientId();
		ingredientName=entity.getIngredientName();
		ingredientDescr=entity.getIngredientDescr();
	}
	
	public IngredientRepresentationModel() {
		
	}
	
	
}
