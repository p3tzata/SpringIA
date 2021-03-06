package com.example.demo.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domainEntity.Ingredient;
@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
	
	
	List<Ingredient> findAllByOrderByIngredientNameDesc(Pageable pageable);

}
