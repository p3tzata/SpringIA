package com.example.demo.reactive;

import org.springframework.stereotype.Component;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ReactiveLogical {

	
	public void all_any() {
		
		Mono<Boolean> monoValueAll = Flux.just("val1","val2","val3").all(el->el.length()>2);		

		monoValueAll.subscribe(System.out::println);
		
		Mono<Boolean> monoValueAny = Flux.just("val1","val2","val33333333").any(el->el.length()>6);
		
		monoValueAny.subscribe(System.out::println);
		
		
	}
	
	
	
}
