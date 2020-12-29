package com.example.demo.service.microservice.receiver;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demo.domainEntity.Burger;

//This is push model
//Disable @Component
public class RabbitTemplateListener {
	
	
	@RabbitListener(queues  = "sampleQueue")
	public void receiveBurger(Burger burger) {
		
		System.out.println("1.received by Rabbit Push burger with id: "+burger.getBurger_id()+" count ingredients:"+ burger.getIngredients().size());
		
	}
	
	/*
	@RabbitListener(queues  = "sampleQueue")
	public void receiveBurger2(Burger burger) {
		
		System.out.println("2.received by Rabbit Push burger with id: "+burger.getBurger_id()+" count ingredients:"+ burger.getIngredients().size());
		
	}
	*/
	
	
	
	
	
}
