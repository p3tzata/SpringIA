package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domainEntity.Ingredient;

public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
	
	
	List<Ingredient> findAllByOrderByIngredientNameDesc(Pageable pageable);

}
