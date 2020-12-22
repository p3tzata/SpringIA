package com.example.demo.service.microservice.sender;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.domainEntity.Burger;
import com.example.demo.service.domain.BurgerService;

@Component
public class JmsTemplateSenderService {

	private JmsTemplate jmsTemplate;
	private Destination defaultDestination;
	private BurgerService burgerService;
	@Autowired
	public JmsTemplateSenderService(JmsTemplate jmsTemplate, Destination defaultDestination, BurgerService burgerService) {
		this.jmsTemplate = jmsTemplate;
		this.defaultDestination = defaultDestination;
		this.burgerService = burgerService;
	}
	
	public void sendBurger() {
		
		Burger burger = burgerService.findById(1L);
		//Burger burger =new Burger();
		//burger.setBurger_id(33L);
		
		//jmsTemplate.setPubSubDomain(true);
		jmsTemplate.convertAndSend("myJmsBroker.defaultQueue",burger,this::addParamToMessage);
	
	}
	
	private Message addParamToMessage(Message message) throws JMSException  {
	message.setStringProperty("X_SOURCE", "jmsTemplate");	
	return message;
	}
	
}
