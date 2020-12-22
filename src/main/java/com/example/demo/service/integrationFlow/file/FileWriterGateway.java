package com.example.demo.service.integrationFlow.file;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

import com.example.demo.domainEntity.SaleOrder;

@MessagingGateway(defaultReplyChannel = "testTransformerChannel")
public interface FileWriterGateway {
	
	@Gateway(requestChannel = "testTransformerChannel")
	void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String payload );
	
	@Gateway(requestChannel = "testRouterChannel")
	void router(@Header(FileHeaders.FILENAME) String fileName, SaleOrder payload );
	
	
	@Gateway(requestChannel = "testTransformerCountChannel_in", replyChannel = "testTransformerCountChannel_out")
	int countStringLength(String inString);
	
	@Gateway(requestChannel = "customPrintConsoleChannel")
	void printConsoleChannel(String inString);
	
	
	@Gateway(requestChannel = "saleOrderChannel")
	void split(@Header(FileHeaders.FILENAME) String fileName,SaleOrder payload);
	
}


