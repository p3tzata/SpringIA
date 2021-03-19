package com.example.demo.service.microservice.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.domainEntity.Ingredient;

@Service
public class KafkaSenderService {
	
	
	private KafkaTemplate<String, String> kafkaIngredientTemplate;
	
	
	@Autowired
	public KafkaSenderService(KafkaTemplate<String, String> kafkaIngredientTemplate) {
		super();
		this.kafkaIngredientTemplate = kafkaIngredientTemplate;
	}



	public void sendIngredient(Ingredient ingredient) {
		
		kafkaIngredientTemplate.send("cloud.ingredient","testIngredient");
	
	}
	

}
