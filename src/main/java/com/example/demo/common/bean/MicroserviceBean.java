package com.example.demo.common.bean;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

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
	
		@Bean
		public ProducerFactory<String, String> producerFactoryKafka() {
			
			String brokerAddress="127.0.0.1";
			Map<String,Object> propsMap=new HashMap<>();
			propsMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerAddress);
			propsMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			propsMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			
			return new DefaultKafkaProducerFactory<>(propsMap);
			
		}
		
		 @Bean
		    public KafkaTemplate<String, String> kafkaTemplate() {
		        return new KafkaTemplate<>(producerFactoryKafka());
		   }
		 
		 @Bean
		    public ConsumerFactory<String, String> consumerFactory() {
			 String brokerAddress="127.0.0.1";
			 String groupId="testGroupID";
		        Map<String, Object> props = new HashMap<>();
		        props.put(
		          ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
		          brokerAddress);
		        props.put(
		          ConsumerConfig.GROUP_ID_CONFIG, 
		          groupId);
		        props.put(
		          ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
		          StringDeserializer.class);
		        props.put(
		          ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
		          StringDeserializer.class);
		        return new DefaultKafkaConsumerFactory<>(props);
		    }
		 
		
		

		
	
}
