package com.example.demo.common.bean.integration.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.router.MessageRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.GenericMessage;

import com.example.demo.common.prop.EmailProp;
import com.example.demo.common.prop.GlobalProp;
import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.Customer;
import com.example.demo.domainEntity.SaleOrder;
import com.google.gson.Gson;

@Configuration 
public class IntegrationFlowBean_inboundAdapter {

	@Autowired
	private GlobalProp globalProp;
	
	@Autowired
	private EmailProp emailProp;
	
	@Autowired
	private Gson gson;
	
	@Bean
	@InboundChannelAdapter(channel = "sendGenericMessageChannel",poller = @Poller(fixedRate = "999999000"))
	public MessageSource<String> sendGenericMessage(){
		
		return () -> {
			return new GenericMessage<>("test message ");
		};
		
	}
	//Monitoring directory for files.
	@Bean
	@InboundChannelAdapter(channel = "fileReadingChannel",poller = @Poller(fixedRate = "999999000"))
	public MessageSource<File> fileReading(){
		
		FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
		fileReadingMessageSource.setDirectory(new File(globalProp.getBaseIntegrationDir()+"/testInboundAdaper"));
		return fileReadingMessageSource;
		
	}
	
	
	
	@Bean
	@ServiceActivator(inputChannel = "sendGenericMessageChannel")
	public MessageHandler sendGenericMessageHandler() {
		
		return msg -> {
			String payloadOrder = (String) msg.getPayload();
							System.out.println("Inbound adapter generic: " + payloadOrder);};
		
	}
	
	@Bean
	@ServiceActivator(inputChannel = "fileReadingChannel")
	public MessageHandler fileReadingHandler() {
		
		return msg -> {
			File payloadOrder = (File) msg.getPayload();
							System.out.println("Inbound adapter file: " + payloadOrder.getAbsolutePath());};
		
	}
	
	//
	
	
	
	
}


