package com.qa.springDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Dfesw13SpringDemoApplication {

	// Normally we don't mess with this, unless we have some very specific configurations to change
	public static void main(String[] args) {
		
		SpringApplication.run(Dfesw13SpringDemoApplication.class, args);
		
		// ApplicationContext name = SpringApplication stuff
		// Import org.springframework.context.ApplicationContext
//		ApplicationContext context = SpringApplication.run(Dfesw13SpringDemoApplication.class, args);
		
//		// Saving an object called byName that is equal to the bean of name "returnHello"
//		// Not saving a String (what the method returns) but saving the Bean object
//		Object byName = context.getBean("returnHello"); // bean of method name returnHello
//		// If grabbing bean by class name name use lowercase
//		Object byClass = context.getBean("helloWorld"); // bean of the method of class name HelloWorld
//		// if trying to create multiple instances of the same bean, Spring tells you off
//		
//		Object randomNum = context.getBean("randomNum");
//		
//		System.out.println(randomNum);
//		
//		System.out.println(byName);
//		
//		System.out.println(byClass);
		
	}

}
