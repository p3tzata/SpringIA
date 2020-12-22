package com.example.demo.service.microservice.receiver;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.domainEntity.Burger;

//Pull method
@Component
public class JmsTemplateReceiver {

	private JmsTemplate jmsTemplate;
	private Destination destination;

	@Autowired
	public JmsTemplateReceiver(JmsTemplate jmsTemplate,Destination destination) {
		super();
		this.jmsTemplate = jmsTemplate;
		this.destination = destination;
	}
	
	
	public Burger receiveBurger() {
		Burger burger = (Burger) jmsTemplate.receiveAndConvert(destination);
		return burger;
	}
	
	
	
}
