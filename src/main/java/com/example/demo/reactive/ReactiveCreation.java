package com.example.demo.reactive;

import java.time.Duration;

import org.springframework.stereotype.Component;


import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifier.FirstStep;

@Component
public class ReactiveCreation {

	
	public void createFlux() {
		
		Flux<String> justFlux = Flux.just("val1","val2","val3");
		justFlux.subscribe( el -> System.out.println("Test:" + el));
		
	}
	
	public void stepVerifierFlux() {
		
		Flux<String> justFlux = Flux.just("val1","val2","val3");
		
		Duration verifyComplete = StepVerifier.create(justFlux).expectNext("val1")
		.expectNext("val3")
		.expectNext("val2")
		.verifyComplete();
		
	}
	
	
	
}
