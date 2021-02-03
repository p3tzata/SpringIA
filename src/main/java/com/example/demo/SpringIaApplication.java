package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIaApplication {

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(SpringIaApplication.class);
        app.setWebApplicationType(WebApplicationType.REACTIVE);
        app.run(args);
        //SpringApplication.run(SpringIaApplication.class, args);
	}

}
