package com.example.demo.reactive;

import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;


import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Component
public class ReactiveCreation {

	public void createFluxFrom_interval() {
		
		Flux<Long> fluxInterval = Flux.interval(Duration.ofSeconds(1L)).take(8);
		
		fluxInterval.subscribe( el -> System.out.println("Test interval:" + el));
		
		
		
		
	}
	
	
	
	public void createFluxFrom_List_Array_Stream() {
		
		ArrayList<String> list = new ArrayList<>();
		list.add("val1");
		list.add("val2");
		
		String[] array =new  String[] {"val1","val2"};
		
		Stream<String> stream = Stream.of("val1","val2");
		
		
		Flux<String> fromIterableFlux = Flux.fromIterable(list);
		
		Flux<String> fromArrayFlux = Flux.fromArray(array);
		Flux<String> fromStreamFlux = Flux.fromStream(stream);
		
		
		
		
		
		
	}
	
	
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
