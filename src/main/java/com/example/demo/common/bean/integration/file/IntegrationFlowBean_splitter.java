package com.example.demo.common.bean.integration.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.router.MessageRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.example.demo.common.prop.GlobalProp;
import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.Customer;
import com.example.demo.domainEntity.SaleOrder;
import com.google.gson.Gson;

@Configuration 
public class IntegrationFlowBean_splitter {

	@Autowired
	private GlobalProp globalProp;
	
	@Autowired
	private Gson gson;
	
	@Bean
	@Splitter(inputChannel = "saleOrderChannel",outputChannel = "splitSaleOrderRouterChannel")
	public SaleOrderSpliter saleOrderSpliter() {
		
		return new SaleOrderSpliter();
	}
	
	@Bean
	@Router(inputChannel = "splitSaleOrderRouterChannel")
	public MessageRouter splitSaleOrderRouter() {
		PayloadTypeRouter payloadTypeRouter = new PayloadTypeRouter();
		payloadTypeRouter.setChannelMapping(Customer.class.getName(), "customerSplitedChannel");
		payloadTypeRouter.setChannelMapping(List.class.getName(), "burgersSplitedChannel");
		payloadTypeRouter.setChannelMapping(SaleOrder.class.getName(), "saleOrderSplitedTransformerChannel");
		return payloadTypeRouter;
		
	}
	
	@Bean
	@Transformer(inputChannel = "saleOrderSplitedTransformerChannel",outputChannel = "saleOrderBurgerChannel")
	public GenericTransformer<SaleOrder, String> saleOrderSplitedTransformer() {
		return payload -> gson.toJson(payload);
	}
	
	
	@Splitter(inputChannel = "burgersSplitedChannel", outputChannel = "burgerSplitedChannel")
	public List<Burger> itemSplitter(List<Burger> items) {
		
		return items;
	}
	
	@Bean
	@ServiceActivator(inputChannel = "customerSplitedChannel")
	public MessageHandler customerSplitedMessageHandler() {
		
		return msg -> {
			Customer payloadOrder = (Customer) msg.getPayload();
							System.out.println("Customer id: " + payloadOrder.getCustomer_id());};
		
	}
	
	@Bean
	@ServiceActivator(inputChannel = "burgerSplitedChannel")
	public MessageHandler burgerSplitedMessageHandler() {
		
		return msg -> {
			Burger payloadOrder = (Burger) msg.getPayload();
							System.out.println("Burger id: " + payloadOrder.getBurger_id());};
		
	}
	
	
	@Bean
	@ServiceActivator(inputChannel = "saleOrderBurgerChannel")
	public FileWritingMessageHandler fileTestSaleOrderIdWriter() {
		
		FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File(globalProp.getBaseIntegrationDir()+"/testSplitSaleOrderDir"));
		fileWritingMessageHandler.setExpectReply(false);
		fileWritingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);
		fileWritingMessageHandler.setAppendNewLine(true);
		
		return fileWritingMessageHandler;
		
	}
	
	@Bean
	public MessageChannel splitSaleOrderRouterChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel customerSplitedChannel() {
		return new DirectChannel();
	}
	
	
	@Bean
	public MessageChannel burgersSplitedChannel() {
		return new DirectChannel();
	}
	
	
}


class SaleOrderSpliter {
	
	public Collection<Object> split(SaleOrder order) {
		ArrayList<Object> arrayList = new ArrayList<>();
		arrayList.add(order.getCustomer());
		arrayList.add(order.getBurgers());
		arrayList.add(order);
		return arrayList;
		
		
	}
	
	
}