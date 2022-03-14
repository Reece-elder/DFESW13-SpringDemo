package com.qa.springDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// In order for spring to treat this class as a Controller
// Use the correct beans and methods for this class to control data 
@RestController
public class ControllerDemo {
	
	// Using an annotation, I can tell Spring to check if a browser sends a request
	// If this request is sent, trigger this method
	
	// If the app detects '/hello' do this method
	@GetMapping("/zipzoop")
	public String helloWorld() {
		System.out.println("Read the console!");
		return "Hello from Eclipse, this is now a full stack app :o ";
	}
	
	@GetMapping("/newNum")
	public int returnNum() {
		return 123;
	}

}
