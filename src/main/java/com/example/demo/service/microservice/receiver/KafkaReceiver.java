package com.example.demo.service.microservice.receiver;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;

import com.example.demo.domainEntity.Ingredient;

@Component
public class KafkaReceiver {

	//Disable @KafkaListener(groupId = "testGroupID", topics = "cloud.ingredient")
	public void handleIngredient(String ingredient, Message<String> message) {
		
		 System.out.println("Kafka listener:"+ingredient );
		
		
	}
	
	
}
