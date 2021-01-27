package com.example.demo.controller.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseMessageOK;
import com.example.demo.reactive.ReactiveCreation;

@RestController
@RequestMapping(path = "/public/reactive",produces = "application/json")
public class ReactiveController {

	@Autowired
	private ReactiveCreation reactiveCreation;
	
	
	
	@GetMapping("/testFluxCreation")
	public ResponseMessageOK testFluxCreation(){
		
		reactiveCreation.createFlux();
		
		return new ResponseMessageOK("see in console");
	}
	
	@GetMapping("/testStepVerify")
	public ResponseMessageOK testStepVerify(){
		String responseTextString="No error";
		try {
		reactiveCreation.stepVerifierFlux();
		} catch (AssertionError ae) {
			responseTextString=ae.getMessage();
		}
		return new ResponseMessageOK(responseTextString);
	}
	
	
	
	
}
