package com.example.demo.controller.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.Ingredient;
import com.example.demo.dto.ResponseMessageOK;
import com.example.demo.service.microservice.receiver.JmsTemplateReceiver;
import com.example.demo.service.microservice.sender.JmsTemplateSenderService;
import com.example.demo.service.microservice.sender.KafkaSenderService;
import com.example.demo.service.microservice.sender.RabbitTemplateSenderService;


@RestController
@RequestMapping(path = "/public/message",produces = "application/json")
public class microserviceController {

	private JmsTemplateSenderService jmsTemplateSenderService;
	private JmsTemplateReceiver jmsTemplateReceiver;
	private RabbitTemplateSenderService rabbitTemplateSenderService;
	private KafkaSenderService kafkaSenderService;
	
	@Autowired
	public microserviceController(KafkaSenderService kafkaSenderService,JmsTemplateSenderService jmsTemplateSenderService,JmsTemplateReceiver jmsTemplateReceiver,RabbitTemplateSenderService rabbitTemplateSenderService) {
		this.jmsTemplateSenderService = jmsTemplateSenderService;
		this.jmsTemplateReceiver = jmsTemplateReceiver;
		this.rabbitTemplateSenderService = rabbitTemplateSenderService;
		this.kafkaSenderService=kafkaSenderService;
	}

	@GetMapping("/testJmsTemplateSend")
	public ResponseMessageOK testJmsTemplateSend(){
		
		jmsTemplateSenderService.sendBurger();
		
		return new ResponseMessageOK("converted");
	}
	
	@GetMapping("/testRabbitTemplateSend")
	public ResponseMessageOK testRabbitTemplateSend(){
		
		rabbitTemplateSenderService.sendBurger();
		
		return new ResponseMessageOK("converted");
	}
	
	
	/////// Receiver
	@GetMapping("/testJmsTemplateReceiver")
	public ResponseMessageOK testJmsTemplateReceiver(){
		
		Burger burger = jmsTemplateReceiver.receiveBurger();
		
		
		return new ResponseMessageOK("1.received by Pull burger with id: "+burger.getBurger_id()+" count ingredients:"+ burger.getIngredients().size());
	}
	
	
	@GetMapping("/testKafkaSender")
	public ResponseMessageOK testKafkaSender(){
		
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientId(999L);
		kafkaSenderService.sendIngredient(ingredient);
		
		return new ResponseMessageOK("converted");
	}
	
	
}
