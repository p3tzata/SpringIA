package com.example.demo.service.integrationFlow.email;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

import com.example.demo.domainEntity.SaleOrder;

@MessagingGateway()
public interface EmailGateway {
	
	@Gateway(requestChannel = "receivingPop3MailSendMailChannel")
	void sendEmail(String payload );
	
	
}


