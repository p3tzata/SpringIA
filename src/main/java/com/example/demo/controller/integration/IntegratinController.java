package com.example.demo.controller.integration;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domainEntity.Burger;
import com.example.demo.domainEntity.Customer;
import com.example.demo.domainEntity.SaleOrder;
import com.example.demo.service.integrationFlow.email.EmailGateway;
import com.example.demo.service.integrationFlow.file.FileWriterGateway;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/public/integration",produces = "application/json")
@CrossOrigin(origins="*")
public class IntegratinController {
	
	@Autowired
	FileWriterGateway gw;
	
	@Autowired
	EmailGateway emailGateway;
	
	@Autowired
	Gson gson;
	
	@GetMapping("/testIntegration_sendEmail")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> testIntegration_sendEmail() {
		
		emailGateway.sendEmail("test body 69");
		
		return new ResponseEntity<>("test body 123",HttpStatus.OK);
	}
	
	
	@GetMapping("/testIntegration_countLength")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> testIntegration_countLength() {
		
		int countStringLength = gw.countStringLength("123test456");
		
		return new ResponseEntity<>(countStringLength,HttpStatus.OK);
	}
	
	@GetMapping("/testIntegration_printConsole")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> testIntegration_printConsole() {
		
		gw.printConsoleChannel("sfsfsdsdfs");
		
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
	
	@GetMapping("/testIntegration_writeToFile")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> testIntegration_writeToFile() {
		
		gw.writeToFile("test.log", "kfjtestlsfjlf");
		
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
	
	@GetMapping("/testIntegration_router")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> testIntegration_writeToFile_router() {
		
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setSaleOrder_id(1155L);
		gw.router("int.log", saleOrder);
		
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
	
	@GetMapping("/testIntegration_spliter")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> testIntegration_spliter() {
		
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setSaleOrder_id(1155L);
		ArrayList<Burger> arrayList = new ArrayList<Burger> ();
		Burger burger = new Burger();
		burger.setBurger_id(33L);
		
		Burger burger1 = new Burger();
		burger1.setBurger_id(55L);
		arrayList.add(burger1);arrayList.add(burger);
		
		saleOrder.setBurgers(arrayList);
		Customer customer = new Customer();
		customer.setCustomer_id(66L);
				
		saleOrder.setCustomer(customer);
		gw.split("saleOrder.log",saleOrder);
		
		return new ResponseEntity<>("OK",HttpStatus.OK);
	}
	
	
}
