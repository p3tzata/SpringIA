package com.example.demo;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ControllerSpec;

import com.example.demo.controller.reactiveController.ReactiveAPI;
import com.example.demo.domainEntity.Ingredient;
import com.example.demo.service.domain.IngredientMockedReactiveService;
import com.example.demo.service.domain.IngredientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@SpringBootTest

class SpringIaApplicationTests {

	
	
	
	@Test
	public void testCreateIngredinet() {
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setIngredientId(1L);
		ingredient1.setIngredientName("name1");
		Mono<Ingredient> monoIngredient = Mono.just(ingredient1);
		WebTestClient webTestClient = WebTestClient.
				bindToController(new ReactiveAPI(new IngredientMockedReactiveService())).build();
		
		webTestClient
		.post()
		.uri("/public/reactiveAPI/createIngredient")
		.contentType(MediaType.APPLICATION_JSON)
		.body(monoIngredient,Ingredient.class)
		.exchange()
		.expectStatus()
		.isCreated()
		.expectBody(Ingredient.class)
			.isEqualTo(ingredient1);
	}
	
	@Test
	public void testReturnIngredinetList() {
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setIngredientId(1L);
		ingredient1.setIngredientName("name1");
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setIngredientId(2L);
		ingredient2.setIngredientName("name2");
		ArrayList<Ingredient> list = new ArrayList<>();
		list.add(ingredient1);list.add(ingredient2);
		
		Flux<Ingredient> fluxIngredient = Flux.fromIterable(list);
		
		IngredientMockedReactiveService mockedIngredientService = Mockito.mock(IngredientMockedReactiveService.class);
		
		Mockito.when(mockedIngredientService.findAll()).thenReturn(fluxIngredient);
		
		WebTestClient webTestClient = WebTestClient.bindToController(new ReactiveAPI(mockedIngredientService)).build();
		//WebTestClient webTestClient = WebTestClient.bindToController(new ReactiveAPI(new IngredientReactiveService())).build();
		
		/*
		FluxExchangeResult<String> returnResult = webTestClient
		.get()
		.uri("/public/reactiveAPI/getAllIngrediant")
		.exchange()
		.returnResult(String.class);
		 String stringResponseBody = returnResult.getResponseBody().blockFirst();
		 System.out.print(stringResponseBody);
		 */
		 
		
		webTestClient
		.get()
		.uri("/public/reactiveAPI/getAllIngrediant")
		.exchange()
		.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$[0].ingredientId").isEqualTo(list.get(0).getIngredientId())
			;
		
		
		
	}
	
	
	
	@Test
	void contextLoads() {
	}

}
