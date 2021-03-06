package com.example.demo.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.example.demo.service.domain.UserService;

@Configuration
@EnableWebSecurity
 @EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		
		.authorizeRequests()
								//.anyRequest().permitAll()
								.antMatchers("/","/public/**","/actuator/**","/publicTasks/**","/browser/**","/login","/register","/testRegister","/style/**").permitAll()
								//.antMatchers("/api/test").hasAnyRole("ADMIN")
								.anyRequest().authenticated()
			.and()
								
			
			 			  
             .csrf().disable()
			 .cors()
			 .and()
			 //.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			 //.addFilter(new JWTAuthorizationFilter(authenticationManager(),this.userService))
			 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
             
			;
	}

	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

}
