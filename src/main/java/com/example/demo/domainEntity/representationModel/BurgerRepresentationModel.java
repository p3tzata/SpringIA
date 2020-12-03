package com.example.demo.domainEntity.representationModel;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.Ingredient;
import com.example.demo.domainEntity.representationModelAssembler.IngredientRepresentationModelAssembler;

import lombok.Getter;
import lombok.Setter;

public class BurgerRepresentationModel extends RepresentationModel<BurgerRepresentationModel>{
	
	private IngredientRepresentationModelAssembler ingredientRepresentationModelAssembler;
	@Getter @Setter
	private Long burger_id;
	@Getter @Setter
	private String burger_name;
	@Getter @Setter
	//private List<Ingredient> ingredients;
	private CollectionModel<IngredientRepresentationModel> ingredients;
	@Getter @Setter
	private Date creation_date;
	
	public BurgerRepresentationModel() {
		
		
	}
	
	
	public BurgerRepresentationModel(Burger burger) {
		ingredientRepresentationModelAssembler = new IngredientRepresentationModelAssembler();
		this.burger_id=burger.getBurger_id();
		this.burger_name=burger.getBurger_name();
		
		this.ingredients= ingredientRepresentationModelAssembler.toCollectionModel(burger.getIngredients());
		
	}
	
	
	

}
