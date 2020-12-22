package com.example.demo.common.bean.integration.file;


import java.io.File;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.example.demo.common.prop.GlobalProp;
import com.example.demo.domainEntity.SaleOrder;



@Configuration
public class IntegrationFlowBean_router {
	@Autowired
	private GlobalProp globalProp;

	
	@Bean
	@Router(inputChannel = "testRouterChannel")
	public AbstractMessageRouter bigSmallIntegerRouter(){
		
		return new AbstractMessageRouter() {

			@Override
			protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
				
				SaleOrder payload =  (SaleOrder) message.getPayload();
				
				if (payload.getSaleOrder_id()>1000) {
					return Collections.singleton(testBigIntegerChannel());
				} else {
					return Collections.singleton(testSmallIntegerChannel());
				}
				
			}
			
			
		};
		
	}
	
	
	
	
	
	
	@Bean
	public MessageChannel testSmallIntegerChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel testBigIntegerChannel() {
		return new DirectChannel();
	}
	
	@Bean
 	@ServiceActivator(inputChannel = "testSmallIntegerChannel")
	public MessageHandler fileSmallIntegerTestWriter() {
		
		return msg -> {
		SaleOrder payloadOrder = (SaleOrder) msg.getPayload();
						System.out.println("Small int:" + payloadOrder.getSaleOrder_id());};
	}
	
	@Bean
 	@ServiceActivator(inputChannel = "testBigIntegerChannel")
	public MessageHandler fileBigIntegerTestWriter() {
		
		return msg -> {
			SaleOrder payloadOrder = (SaleOrder) msg.getPayload();
							System.out.println("Big int:" + payloadOrder.getSaleOrder_id());};
	}
	
	
//	@Bean
//	@ServiceActivator(inputChannel = "testSmallIntegerChannel")
//	public FileWritingMessageHandler fileSmallIntegerTestWriter() {
//		
//		FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File(globalProp.getBaseIntegrationDir()+"/testDirSmallInteger"));
//		fileWritingMessageHandler.setExpectReply(false);
//		fileWritingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);
//		fileWritingMessageHandler.setAppendNewLine(true);
//		
//		return fileWritingMessageHandler;
//		
//	}
//	
//	
//	@Bean
//	@ServiceActivator(inputChannel = "testBigIntegerChannel")
//	public FileWritingMessageHandler fileBigIntegerTestWriter() {
//		
//		FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File(globalProp.getBaseIntegrationDir()+"/testDirBigInteger"));
//		fileWritingMessageHandler.setExpectReply(false);
//		fileWritingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);
//		fileWritingMessageHandler.setAppendNewLine(true);
//		
//		return fileWritingMessageHandler;
//		
//	}
	
	@Bean
	//This can be missed
	public MessageChannel testDefChannel() {
		return new DirectChannel();
	}
	
	@Bean
	//This can be missed
	public MessageChannel fileWriteTestChannel() {
		return new DirectChannel();
	}
	
	
}
