package com.example.demo.reactive;

import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;


import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@Component
public class ReactiveCombination {

public void combineByZip() {
		
		
		Flux<String> countryFlux = Flux.just("Spain","Bulgaria","Russia")
		.delayElements(Duration.ofMillis(100));
		
		Flux<String> cityFlux = Flux.just("Madrid","Plovdiv","Moscow")
		.delayElements(Duration.ofMillis(100))
		.delaySubscription(Duration.ofMillis(50)); //Startup Offset
		
		Flux<Tuple2<String,String>> zip = Flux.zip(countryFlux, cityFlux);
		
		Flux<String> zip2 = Flux.zip(countryFlux, cityFlux, 
				(el1,el2) -> "Test combineByZip with Lambda:" + el2 +" in "+ el1 );
		
		
		zip.subscribe( el -> System.out.println("Test combineByZip:" + el.getT2() +" in "+ el.getT1()));
		zip2.subscribe(System.out::println);
		
	}
	
	
	
	public void combineByMergeWith() {
		
		//MergeWith don't guarantee a perfect back.
		
		Flux<String> countryFlux = Flux.just("Spain","Bulgaria","Russia")
		.delayElements(Duration.ofMillis(100));
		
		Flux<String> cityFlux = Flux.just("Madrid","Plovdiv","Moscow")
		.delayElements(Duration.ofMillis(100))
		.delaySubscription(Duration.ofMillis(50)); //Startup Offset
		
		Flux<String> mergeWith = countryFlux.mergeWith(cityFlux);
		
		mergeWith.subscribe( el -> System.out.println("Test MergeWith:" + el));
		
		
	}
	
	
}
