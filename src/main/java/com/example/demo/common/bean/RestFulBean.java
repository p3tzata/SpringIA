package com.example.demo.common.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domainEntity.representationModelProcessor.BurgerRepresentationModelProcessor;

@Configuration
public class RestFulBean {
	
	@Bean
	public BurgerRepresentationModelProcessor getBurgerRepresentationModelProcessor() {
		return new BurgerRepresentationModelProcessor();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
