package com.example.demo.common.bean.integration.file;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHandler;

import com.example.demo.domainEntity.SaleOrder;
import com.example.demo.repository.SaleOrderRepo;



@Configuration
public class IntegrationFlowBean_customServiceActivator {

	@Bean
	@ServiceActivator(inputChannel = "customPrintConsoleChannel")
	public MessageHandler customPrintConsoleHandler() {
		
		return msg -> {System.out.println("Message arrived: " + msg.getPayload());};
	}
	
	@Bean
	@ServiceActivator(inputChannel = "customChannel_in",outputChannel = "customChannel_out")
	public GenericHandler<SaleOrder> customInputOutputServiceActivator(SaleOrderRepo saleOrderRepo) {
		
 		return (payload,headers) -> 
 			   {return saleOrderRepo.save(payload);};
 		
	}
	
}
