package com.example.demo.common.bean;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import com.example.demo.domainEntity.Burger;

@Configuration
public class MicroserviceBean {


		@Bean
		public Destination getDefautJmsDestination() {
			
			return new ActiveMQQueue("myJmsBroker.defaultQueue");
		}
		
		// We can use this converter instead of default SimpleMesageConverter.
		@Bean
		public MappingJackson2MessageConverter  getMessageConverterJms() {
			 MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
			 converter.setTypeIdPropertyName("_typeId");
			 Map <String,Class<?>> mappingTypes = new HashMap<>();
			 mappingTypes.put("burger", Burger.class);
			 
			 
			 return converter;
		}
		
		@Bean
		public Jackson2JsonMessageConverter  getMessageConverterRabbit() {
			Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
			 //converter.setTypeIdPropertyName("_typeId");
			 //Map <String,Class<?>> mappingTypes = new HashMap<>();
			 //mappingTypes.put("burger", Burger.class);
			 
			 
			 return converter;
		}
	
		/* 
		String DEFAULT_BROKER_URL = "tcp://localhost:61616";

		
		
		public ActiveMQConnectionFactory connectionFactory() throws JMSException {
		  ActiveMQConnectionFactory connectionFactory =  new ActiveMQConnectionFactory();
		  connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		  connectionFactory.setUser("admin");
		  connectionFactory.setPassword("123456");
		  //connectionFactory.setTrustedPackages(Arrays.asList("app.dto","java.lang"));
		  return connectionFactory;
		}
		
		*/
		/*
		@Bean
		public JmsTemplate jmsTemplate() throws JMSException{
		  JmsTemplate template = new JmsTemplate();
		  template.setConnectionFactory(connectionFactory());
		  template.setMessageConverter(getMessageConverter());
		  template.setDefaultDestination(getDefautJmsDestination());
		  
		  return template;
		}
	*/
		/*
		@Bean(name = "default_container")
		public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory () throws JMSException {
			DefaultJmsListenerContainerFactory lFactory = new DefaultJmsListenerContainerFactory();
			lFactory.setConnectionFactory(this.connectionFactory());
			lFactory.setPubSubDomain(true);
			lFactory.setSessionTransacted(true);
			
			
			return lFactory;
			
		}
		*/
		
		
		

		
	
}
