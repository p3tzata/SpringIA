package com.example.demo.service.microservice.sender;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domainEntity.Burger;
import com.example.demo.service.domain.BurgerService;

@Component
public class RabbitTemplateSenderService {

	private RabbitTemplate rabbitTemplate;
	private BurgerService burgerService;
	@Autowired
	public RabbitTemplateSenderService(RabbitTemplate rabbitTemplate, BurgerService burgerService) {
		this.rabbitTemplate = rabbitTemplate;
		this.burgerService = burgerService;
	}
	
	public void sendBurger() {
		
		Burger burger = burgerService.findById(1L);
		//Burger burger =new Burger();
		//burger.setBurger_id(33L);
		
		
		rabbitTemplate.convertAndSend("sampleQueue", burger, this::addParamToMessage);
		
	
	}
	
	private Message addParamToMessage(Message message)  {
		MessageProperties messageProperties = message.getMessageProperties();
		messageProperties.setHeader("X_SOURCE", "rabbitTemplate");
	return message;
	}
	
}
