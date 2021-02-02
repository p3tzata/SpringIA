package com.example.demo.controller.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseMessageOK;
import com.example.demo.reactive.ReactiveCombination;
import com.example.demo.reactive.ReactiveCreation;
import com.example.demo.reactive.ReactiveLogical;
import com.example.demo.reactive.ReactiveTransformation;

@RestController
@RequestMapping(path = "/public/reactive",produces = "application/json")
public class ReactiveController {

	@Autowired
	private ReactiveCreation reactiveCreation;
	@Autowired
	private ReactiveCombination reactiveCombination;
	@Autowired
	private ReactiveTransformation reactiveTransformation;
	@Autowired
	private ReactiveLogical reactiveLogical;
	
	@GetMapping("/testLogical")
	public ResponseMessageOK testLogical(){
		
		reactiveLogical.all_any();
		
		return new ResponseMessageOK("see in console");
	}
	
	
	@GetMapping("/testFluxToMonoMAP")
	public ResponseMessageOK testFluxToMonoMAP(){
		
		reactiveTransformation.FluxToMonoMAP();
		
		return new ResponseMessageOK("see in console");
	}
	
	
	@GetMapping("/testBufferFlatMap")
	public ResponseMessageOK testBufferFlatMap(){
		
		reactiveTransformation.bufferFlatMap();
		
		return new ResponseMessageOK("see in console");
	}
	
	@GetMapping("/testBuffer")
	public ResponseMessageOK testBuffer(){
		
		reactiveTransformation.buffer();
		
		return new ResponseMessageOK("see in console");
	}
	
	@GetMapping("/testFlatMap")
	public ResponseMessageOK testFlatMap(){
		
		reactiveTransformation.flatMap();
		
		return new ResponseMessageOK("see in console");
	}
	
	@GetMapping("/testMap")
	public ResponseMessageOK testMap(){
		
		reactiveTransformation.map();
		
		return new ResponseMessageOK("see in console");
	}
	
	
	@GetMapping("/testFluxCombineByZip")
	public ResponseMessageOK testFluxCombineByZip(){
		
		reactiveCombination.combineByZip();
		
		return new ResponseMessageOK("see in console");
	}
	
	@GetMapping("/testFluxWergeWith")
	public ResponseMessageOK testFluxWergeWith(){
		
		reactiveCombination.combineByMergeWith();
		
		return new ResponseMessageOK("see in console");
	}
	
	
	@GetMapping("/testFluxInterval")
	public ResponseMessageOK testFluxInterval(){
		
		reactiveCreation.createFluxFrom_interval();
		
		return new ResponseMessageOK("see in console");
	}
	
	
	
	
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
