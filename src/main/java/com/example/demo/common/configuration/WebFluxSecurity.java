package com.example.demo.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebFluxSecurity {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		
		return http
				.csrf().disable()
				.authorizeExchange()
					.pathMatchers("/","/public/**","/publicTasks/**","/browser/**","/login","/register","/testRegister","/style/**")
						.permitAll()
					.anyExchange().authenticated()
				.and().build();
				
				
				
	}
	
	
}
