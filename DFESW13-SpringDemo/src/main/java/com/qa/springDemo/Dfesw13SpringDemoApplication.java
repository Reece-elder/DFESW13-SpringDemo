package com.qa.springDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Dfesw13SpringDemoApplication {

	// Normally we don't mess with this, unless we have some very specific configurations to change
	public static void main(String[] args) {
		
		// ApplicationContext name = SpringApplication stuff
		// Import org.springframework.context.ApplicationContext
		ApplicationContext context = SpringApplication.run(Dfesw13SpringDemoApplication.class, args);
		
		// Saving an object called byName that is equal to the bean of name "returnHello"
		// Not saving a String (what the method returns) but saving the Bean object
		Object byName = context.getBean("helloWorld");
		
		System.out.println(byName);
		
	}

}
