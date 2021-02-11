package com.example.demo.controller.reactiveController;

import static org.hamcrest.CoreMatchers.nullValue;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.controller.reactiveController.exception.Ingredient4xxClientErrorException;
import com.example.demo.controller.reactiveController.exception.IngredientNotFoundException;
import com.example.demo.domainEntity.Ingredient;
import com.example.demo.dto.ResponseMessageOK;

import ch.qos.logback.core.status.Status;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/public/reactiveConsumingAPI",produces = "application/json")
public class ReactiveConsumingApi {

	@Autowired
	private WebClient webClient;
	
	//This will get also header and cookie values
	@GetMapping("/exchangeTest")
	public ResponseEntity<?>  exchangeTest(){
		
		Mono<Ingredient> monoIntredient = webClient.get()
				 .uri("/public/reactiveAPI/getIngrediant/{id}",2)
				 .exchange()
				 .flatMap(cr-> {
					 if(cr.headers().header("XXXX").equals("true") ) {
						 return Mono.empty();
					 } 
					 return Mono.just(cr);
				 })
				 .flatMap(cr->cr.bodyToMono(Ingredient.class));
		
		
		monoIntredient.subscribe(System.out::println,err-> {System.out.println("Error to long");} );
		

		return new ResponseEntity<>("see in console",HttpStatus.OK);
	}
	
	
	
	@GetMapping("/notFoundErrorHandlerTest")
	public ResponseEntity<?>  notFoundErrorHandlerTest(){
		Mono<Ingredient> monoIntredient=null;
		
		 monoIntredient = webClient.get()
				 .uri("/public/reactiveAPI/getIngrediantNotFound/{id}",2)
				 .retrieve()
				 .onStatus(status -> status==HttpStatus.NOT_FOUND, 
						   response -> Mono.just(new IngredientNotFoundException())  )
				 .onStatus(HttpStatus::is4xxClientError, 
				   response ->  Mono.just(new Ingredient4xxClientErrorException()))
		 
				 .bodyToMono(Ingredient.class);
		 
		 monoIntredient.doOnError(ex-> System.out.println(ex.getMessage()))
		 				.subscribe(System.out::println);
		 
		return new ResponseEntity<>("see in console",HttpStatus.OK);
	}
	
	
	@PostMapping("/postTest")
	public ResponseEntity<?> postTest(){
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setIngredientId(1L);
		ingredient1.setIngredientName("name123123");
		
		
		 Mono<Ingredient> result = webClient.post()
				 .uri("/public/reactiveAPI/createIngredient")
				 .contentType(MediaType.APPLICATION_JSON)
				 .body(Mono.just(ingredient1),Ingredient.class)
				 .retrieve()
				 .bodyToMono(Ingredient.class);

		result.subscribe(System.out::println );
		
		
		return new ResponseEntity<>("see in console",HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getTest")
	public ResponseEntity<?> getTest(){
		
		
		Mono<Ingredient> monoIntredient = webClient.get()
				 .uri("/public/reactiveAPI/getIngrediant/{id}",2)
				 .retrieve()
				 .bodyToMono(Ingredient.class);
		
		monoIntredient
					  .timeout(Duration.ofNanos(1000))
					  //.timeout(Duration.ofSeconds(1))
					  .subscribe(System.out::println,err-> {System.out.println("Error to long");} );
		
		
		return new ResponseEntity<>("see in console",HttpStatus.OK);
	}
	
	
}
