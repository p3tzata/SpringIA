package com.example.demo.dto;

import java.util.List;

import com.example.demo.domainEntity.Ingredient;

import lombok.Data;


@Data
public class IngredientListDTO {

	private List<Ingredient> ingredients;
	
}
