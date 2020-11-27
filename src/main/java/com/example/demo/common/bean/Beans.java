package com.example.demo.common.bean;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.example.demo.domainEntity.representationModelProcessor.BurgerRepresentationModelProcessor;
import com.google.gson.Gson;

@Configuration
//@Profile({"developmentEnvironment","qaEnvironment"})
@Profile({"!productionEnvironment"})
public class Beans {

	@Bean
    public CsrfTokenRepository getCsrfTokenRepository() {
        return CookieCsrfTokenRepository.withHttpOnlyFalse();
    }
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public Gson getGson() {
		return new Gson();
	}
	
	@Bean
	public BurgerRepresentationModelProcessor getBurgerRepresentationModelProcessor() {
		return new BurgerRepresentationModelProcessor();
	}
	
	
}
