package com.example.demo.common.bean.integration.file;


import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;

import com.example.demo.common.prop.GlobalProp;

@Configuration
public class IntegrationFlowBean_filterAndTransformer {
	
	@Autowired
	private GlobalProp globalProp;
	
	
	@Bean
	@Transformer(inputChannel = "testTransformerChannel",outputChannel = "testFilterChannel")
	public GenericTransformer<String, String> replaceCharTestTransformer() {
		return text -> text.replace("test", "TEST");
	}
	
	@Bean
	@Transformer(inputChannel = "testTransformerCountChannel_in",outputChannel = "testTransformerCountChannel_out")
	public GenericTransformer<String, Integer> countTestTransformer() {
		return text -> text.length();
	}
	
	
	@Bean
	@ServiceActivator(inputChannel = "fileWriteChannel_filterAndTransformer")
	public FileWritingMessageHandler fileTestWriter() {
		
		FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File(globalProp.getBaseIntegrationDir()+"/testDir"));
		fileWritingMessageHandler.setExpectReply(false);
		fileWritingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);
		fileWritingMessageHandler.setAppendNewLine(true);
		
		return fileWritingMessageHandler;
		
	}
	
	@Bean
	//This can be missed
	public MessageChannel testTransformerChannel() {
		return new DirectChannel();
	}
	
	@Bean
	//This can be missed
	public MessageChannel fileWriteChannel_filterAndTransformer() {
		return new DirectChannel();
	}
	
	@Bean
	//This can be missed
	public MessageChannel testTransformerCountChannel_out() {
		return new DirectChannel();
	}
	
	@Filter(inputChannel = "testFilterChannel",outputChannel = "fileWriteChannel_filterAndTransformer")
	public boolean minTextSizeTestFilter(String payload) {
		
		return payload.length() > 3;
	}
	
}
