package com.example.demo.service.microservice.receiver;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demo.domainEntity.Burger;

//This is push model
//@Component
public class JmsTemplateListener {
	
	//Disable @JmsListener(destination = "myJmsBroker.defaultQueue")
	public void receiveBurger(Burger burger) {
		
		System.out.println("1.received by Push burger with id: "+burger.getBurger_id()+" count ingredients:"+ burger.getIngredients().size());
		
	}
	
	//Disable @JmsListener(destination = "myJmsBroker.defaultQueue")
	public void receiveBurger2(Burger burger) {
		
		System.out.println("2.received by Push burger with id: "+burger.getBurger_id()+" count ingredients:"+ burger.getIngredients().size());
		
	}
	
	
	
	
	
}
