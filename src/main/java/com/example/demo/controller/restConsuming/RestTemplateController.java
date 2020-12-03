package com.example.demo.controller.restConsuming;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import com.example.demo.domainEntity.Ingredient;
import com.example.demo.domainEntity.representationModel.BurgerRepresentationModel;




@RestController
@RequestMapping(path = "/public/RestTemplate",produces = "application/json")
public class RestTemplateController {
	@Autowired
	private RestTemplate restTemplate;
	
	private final String testRestApi="http://127.0.0.1/api";
	
	@GetMapping("/getForEntity")
	public ResponseEntity<?> getForEntity ()
	{
		
		Integer id=1;
		Map<String, String> urlVar = new HashMap<>();
		urlVar.put("id",String.valueOf(id));
		URI url = UriComponentsBuilder.fromHttpUrl(testRestApi + "/get.php?id={id}").build(urlVar);
		
		//ResponseEntity<BurgerRepresentationModel> forEntity = restTemplate.getForEntity(testRestApi + "/get.php?id={id}", BurgerRepresentationModel.class, id);
		ResponseEntity<BurgerRepresentationModel> forEntity = restTemplate.getForEntity(url, BurgerRepresentationModel.class);
		
		BurgerRepresentationModel body = forEntity.getBody();
		//At this time i can't find way to convert RepresentationModel to Entity.
		return new ResponseEntity<>(body,HttpStatus.OK );
	}
	
	
	@GetMapping("/getAllForEntity")
	public ResponseEntity<?> getAllForEntity ()
	{
		
		
		
	    ResponseEntity<Object> forEntity = restTemplate.getForEntity(testRestApi + "/get.php", Object.class);
		Object body = forEntity.getBody();
		//At this time i can't find way to convert RepresentationModel to Entity.
		return new ResponseEntity<>(body,HttpStatus.OK );
	}
	
	@PostMapping("/postForEntity")
	public ResponseEntity<?> postForEntity ()
	{
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientId(69L);
		ingredient.setIngredientName("test ingredient");
		
		ResponseEntity<Ingredient> postForEntity = restTemplate.postForEntity(testRestApi + "/post.php", ingredient,Ingredient.class);
		
		
		return new ResponseEntity<>(postForEntity.getBody(),HttpStatus.OK );
	}
	

}
