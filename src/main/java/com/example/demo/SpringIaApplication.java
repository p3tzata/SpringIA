package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

public class SpringIaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		
		//SpringApplication app = new SpringApplication(SpringIaApplication.class);
        //app..setWebApplicationType(WebApplicationType.REACTIVE);
        //app.run(args);
        SpringApplication.run(SpringIaApplication.class, args);
	}

}
