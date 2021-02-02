package com.example.demo.reactive;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@Component
public class ReactiveTransformation {
	
	
	public void FluxToMonoMAP() {
		
		Mono<Map<Integer,String>> mono = Flux.just("val1","val22","val333").collectMap( el -> el.length()  );
		
		mono.subscribe(System.out::println);
		
		
		
	}
	
	
	
	public void bufferFlatMap() {
		
		Flux<String> flux = Flux.just("Ivan Popov","Georgi Petrov","Sami Telferov","Mitko Burov");
		
		Flux<List<String>> buffer = flux.buffer(2);
		
		 buffer.flatMap(el -> Flux.fromIterable(el)
							     .map(x -> {String[] splited = x.split("\\s");
							         	    return new Person(splited[0], splited[1]);
							    	 })
							     .subscribeOn(Schedulers.parallel())
							     .log()).subscribe();
		
		
		
		//flatMap.subscribe(System.out::println);
		
		
	}
	
	
	public void buffer() {
		
		Flux<String> flux = Flux.just("Ivan Popov","Georgi Petrov","Sami Telferov","Mitko Burov");
		
		Flux<List<String>> buffer = flux.buffer(2);
		
		buffer.subscribe(System.out::println);
		
		
	}
	
	
	public void flatMap() {
		
		Flux<Person> flatMap = Flux.just("Ivan Popov","Georgi Petrov","Sami Telferov","Mitko Burov")
				.flatMap( el -> Mono.just(el).map( e -> {String[] splited = e.split("\\s");
														return new Person(splited[0], splited[1]);
														})
											 .subscribeOn(Schedulers.parallel())
						);
		
		flatMap.subscribe(System.out::println);
				 
	}					
						
				
		
		
		
	
	
	
	
	public void map() {
		
		Flux<String> map = Flux.just(1,2,3,4,5)
		.map(el-> new String("Mapped to " + String.valueOf(el)));
		
		map.subscribe(System.out::println);
		
		
	}
	
	
	public void filter_distinct() {
		//Get val44
		Flux<String> filter = Flux.just("val1","val2","val3","val44")
				.filter(el -> el.length()>4);
		
		Flux<String> distinct = Flux.just("val1","val2","val3","val4","val2")
				.distinct();
		
	
	}
	
	
	public void skip_take() {
		
		// get val3, val4
		Flux<String> skip = Flux.just("val1","val2","val3","val4").skip(2);
		
		// get val1, val2
		Flux<String> takeByNum = Flux.just("val1","val2","val3","val4").take(2);
		
		// get val1, val2
		Flux<String> takeByTime = Flux.just("val1","val2","val3","val4")
				.delayElements(Duration.ofSeconds(1L))
				.take(Duration.ofMillis(2500));
	
	}
	
	
	

	class Person {
		String name;
		String surname;
		public Person(String name, String surname) {
			this.name=name;
			this.surname=surname;
		}
		public String getName() {
			return name;
		}
		public String getSurname() {
			return surname;
		}
		@Override
		public String toString() {
			
			return name + " " +surname ;
		}
		
		
		
	}
	
}
